package com.epam.expositions.controller;

import com.epam.expositions.dto.LoginDTO;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String authenticate(HttpSession session) {
        if (session != null && session.getAttribute("login") != null) {
            return "redirect:/cabinet";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String checkLogin(HttpSession session, HttpServletRequest request,@ModelAttribute LoginDTO login) {
        if (session != null) {
            session.invalidate();
        }

        String role = authService.authenticate(login);

        session = request.getSession(true);
        session.setAttribute("login", login.getLogin());
        session.setAttribute("role", role);

        return "redirect:/cabinet";
    }

}