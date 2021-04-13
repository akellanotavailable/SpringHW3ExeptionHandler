package com.epam.expositions.controller;

import com.epam.expositions.dto.ExpositionDTO;
import com.epam.expositions.service.ExpositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ExpositionService expositionService;

    @GetMapping("/search")
    public String getSearch(Model model, String search, String order){
        List<ExpositionDTO> expositionList = expositionService.findALL(true);

        model.addAttribute("search", search);

        List<ExpositionDTO> expositionSearch =
                expositionList.stream()
                        .filter(exposition -> exposition.getTopic().toLowerCase(Locale.ROOT)
                                .contains(search.toLowerCase(Locale.ROOT)))
                        .collect(Collectors.toList());

        expositionSearch = expositionSearch.stream()
                .sorted((o1, o2) -> {
                    if (order != null) {
                        switch (order) {
                            case "date_startDESC":
                                return o1.getDateStart().compareToIgnoreCase(o2.getDateStart());
                            case "date_startASC":
                                return o2.getDateStart().compareToIgnoreCase(o1.getDateStart());
                            case "topicDESC":
                                return o1.getTopic().compareToIgnoreCase(o2.getTopic());
                            case "topicASC":
                                return o2.getTopic().compareToIgnoreCase(o1.getTopic());
                            case "priceDESC":
                                return o1.getPrice().compareTo(o2.getPrice());
                            case "priceASC":
                                return o2.getPrice().compareTo(o1.getPrice());
                        }
                    }
                    return 0;
                })
                .collect(Collectors.toList());

        model.addAttribute("expositionList", expositionSearch);
        return "search";
    }
}
