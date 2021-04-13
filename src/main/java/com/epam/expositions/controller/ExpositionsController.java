package com.epam.expositions.controller;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.service.ExpositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpositionsController {
    private final ExpositionService service;

    @GetMapping("/expositions")
    public String getAllAvailableExpositions(Model model) {
        List<ExpositionDTO> expositionList = service.findALL(false);
        model.addAttribute("expositionList", expositionList);
        return "expositions";
    }
}
