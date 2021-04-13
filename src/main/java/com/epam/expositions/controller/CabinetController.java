package com.epam.expositions.controller;

import com.epam.expositions.entity.User;
import com.epam.expositions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CabinetController {
    private final UserService userService;

    @GetMapping("/cabinet")
    public String cabinetLogin(HttpSession session, Model model){
        String login = (String) session.getAttribute("login");
        model.addAttribute("login", login);

        User user = userService.findByLogin(login);
        model.addAttribute("role", user.getRole().getName());

        if (user.getRole().getName().equals("admin")) {
            model.addAttribute("userList", userService.findALL());
        }
        model.addAttribute("userData", userService.findByLogin(login));

        return "cabinet";
    }

}
