package com.epam.expositions.controller;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Status;
import com.epam.expositions.entity.User;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpositionListController {
    private final ExpositionService expositionService;
    private final UserService userService;

    @GetMapping("/expositionlist")
    public String getExpositionList(Model model, HttpSession session){
        User user = userService.findByLogin(String.valueOf(session.getAttribute("login")));
        List<ExpositionDTO> expositionDTOS = expositionService.findByHostId(user.getId());

        model.addAttribute("expositionList", expositionDTOS);

        return "expolist";
    }

    @GetMapping("/expositionlistdelete")
    public String refundExposition(@RequestParam Long expositionid){
        Exposition e = expositionService.findById(expositionid);
        e.setStatusName(Status.REFUNDED.getName());
        expositionService.update(e, e.getId());
        return "redirect:/expositionlist";
    }
}
