package com.example.newssb.controller.web;

import com.example.newssb.dto.NewsDTO;
import com.example.newssb.service.ICategoryService;
import com.example.newssb.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping({"/", "/home"})
    public String homePage(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "limit", defaultValue = "10") int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        List<NewsDTO> newsDTOList = newsService.findAll(pageRequest);
        System.out.println(newsDTOList);
        NewsDTO head = newsDTOList.get(0);
        List<NewsDTO> body = newsDTOList.subList(1, 3);
        List<NewsDTO> foot = newsDTOList.subList(3, 10);
        model.addAttribute("headNews", head);
        model.addAttribute("bodyList", body);
        model.addAttribute("footList", foot);
        return "index";
    }
    @GetMapping("/news")
    public String newsPage(Model model, @RequestParam(name = "id") Long id) {
        NewsDTO newsDTO = newsService.findById(id);
        model.addAttribute("news", newsDTO);
        return "news";
    }

    @GetMapping("/category")
    public String categoryPage(Model model, @RequestParam(name = "id") Long id) {
        List<NewsDTO> newsDTOListByCategoryId = newsService.findByCategoryId(id);
        NewsDTO head = newsDTOListByCategoryId.get(0);
        List<NewsDTO> body = newsDTOListByCategoryId.subList(1, 3);
        List<NewsDTO> foot = newsDTOListByCategoryId.subList(3, 10);
        model.addAttribute("headNews", head);
        model.addAttribute("bodyList", body);
        model.addAttribute("footList", foot);
        model.addAttribute("category", categoryService.findById(id));
        return "category";
    }
}
