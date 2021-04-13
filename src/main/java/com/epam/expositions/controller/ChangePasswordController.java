package com.epam.expositions.controller;

import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ChangePasswordController {
    private final UserService userService;

    @PostMapping("/changepassword")
    public String changePassword(HttpSession session, Model model) {
        User user = userService.findByLogin(String.valueOf(session.getAttribute("login")));

        String oldPassword = String.valueOf(model.getAttribute("password"));
        String newPassword = String.valueOf(model.getAttribute("newPassword"));
        String reNewPassword = String.valueOf(model.getAttribute("newRePassword"));

        if (!oldPassword.equals(user.getPassword())) {
            throw new InvalidDataException("Wrong password");
        }

        if (!newPassword.equals(reNewPassword)) {
            throw new InvalidDataException("Passwords do not match");
        }

        user.setPassword(newPassword);

        userService.update(user, user.getId());

        return "redirect:/cabinet";
    }

}
