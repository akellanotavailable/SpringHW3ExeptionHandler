package com.epam.expositions.controller;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.service.ExpositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final ExpositionService service;

    @GetMapping("/")
    public String index(Model model) {
        List<ExpositionDTO> expositionList = service.findByDate(LocalDateTime.now());
        model.addAttribute("expositionList", expositionList);
        return "main";
    }

}
