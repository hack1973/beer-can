package org.launchcode.controllers;

import org.launchcode.models.Can;
import org.launchcode.models.data.CanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

        //Category cat = categoryDao.findOne(categoryId);
        //newCheese.setCategory(cat);

        canDao.save(newCan);
        return "redirect:";
    }

}
