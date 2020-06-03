package com.firstbasehq.woofer.Controllers;

import com.firstbasehq.woofer.services.DogService;
import com.firstbasehq.woofer.services.WoofService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final WoofService woofService;
    private final DogService dogService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("woofs", woofService.woofs);
        System.out.println(woofService.woofs);
        return "index";
    }

    @GetMapping("/{id}")
    public String dog(Model model, @PathVariable("id") int id) {
        model.addAttribute("dog", this.dogService.getDogById(id));
        return "dog";
    }
}
