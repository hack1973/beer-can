package org.launchcode.controllers;

import org.launchcode.models.Can;
import org.launchcode.models.data.CanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Hackman
 */

@Controller
@RequestMapping("can")
public class CanController {

    @Autowired
    private CanDao canDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cans", canDao.findAll());
        model.addAttribute("title", "My Cans");
        return "can/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCanForm(Model model) {
        model.addAttribute("title", "Add Can");
        model.addAttribute(new Can());
        return "can/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCanForm(
            @ModelAttribute @Valid Can newCan,
            Errors errors,
            Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Can");
            return "can/add";
        }

        canDao.save(newCan);
        return "redirect:/can";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteCanForm(Model model) {
        model.addAttribute("cans", canDao.findAll());
        model.addAttribute("title", "Delete Can");
        return "can/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeleteCanForm(@RequestParam int[] canIds) {

        for (int canId : canIds) {
            canDao.deleteById(canId);
        }

        return "redirect:/can";
    }

    @RequestMapping(value = "editIndex")
    public String displayEditIndexForm(Model model) {
        model.addAttribute("cans", canDao.findAll());
        model.addAttribute("title", "Cans To Edit");

        return "can/editIndex";
    }

    @RequestMapping(value = "edit/{canId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int canId) {

        Optional<Can> optional = canDao.findById(canId);
        if (optional.isPresent()){
            model.addAttribute("can", optional.get());
        } else {
            return "can/editIndex";
        }

        return "can/edit";
    }

    @RequestMapping(value = "edit/{canId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int canId, String name, String description)
    {

        Optional<Can> optional = canDao.findById(canId);
        optional.ifPresent(can -> {
            can.setName(name);
            can.setDescription(description);
            canDao.save(can);
        });

        return "redirect:/can";
    }

}
