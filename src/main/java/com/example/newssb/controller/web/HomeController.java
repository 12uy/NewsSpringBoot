package com.example.newssb.controller.web;

import com.example.newssb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private INewsService newsService;

    @GetMapping({"/", "/home"})
    public String homePage(Model model) {
        model.addAttribute("newsList", newsService.findAll(PageRequest.of(0,5)));
        return "index";
    }
}
