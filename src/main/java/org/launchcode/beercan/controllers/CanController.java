package org.launchcode.beercan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Hackman
 */

@Controller
public class CanController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

}
