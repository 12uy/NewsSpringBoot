package com.example.newssb.controller.web;

import com.example.newssb.converter.RoleConverter;
import com.example.newssb.converter.UserConverter;
import com.example.newssb.dto.NewsDTO;
import com.example.newssb.dto.RoleDTO;
import com.example.newssb.dto.UserDTO;
import com.example.newssb.entity.RoleEntity;
import com.example.newssb.repository.RoleRepository;
import com.example.newssb.repository.UserRepository;
import com.example.newssb.service.ICategoryService;
import com.example.newssb.service.INewsService;
import com.example.newssb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private UserConverter userConverter;

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

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute UserDTO userDTO, HttpServletRequest request) throws ServletException {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleDTOS.add(roleConverter.toDTO(roleRepository.findById(Long.valueOf(2)).get()));
        for (RoleDTO roleDTO : roleDTOS) {
            roleEntities.add(roleConverter.toEntity(roleDTO));
        }
        userDTO.setRoleIds(roleEntities);
        userRepository.save(userConverter.toEntity(userDTO));
        request.login(userDTO.getUserName(), userDTO.getPassword());
        return "redirect:/";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

}
