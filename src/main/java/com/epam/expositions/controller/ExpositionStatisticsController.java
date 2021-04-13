package com.epam.expositions.controller;

import com.epam.expositions.entity.Purchase;
import com.epam.expositions.entity.User;
import com.epam.expositions.service.PurchaseService;
import com.epam.expositions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ExpositionStatisticsController {
    private final PurchaseService purchaseService;
    private final UserService userService;

    @GetMapping("/expositionstats")
    public String getExpositionStats(Model model) {
        String expositionId = String.valueOf(model.getAttribute("expositionid"));
        List<Purchase> purchaseList = purchaseService.findByExpositionId(Long.parseLong(expositionId));
        List<User> userList = purchaseList.stream()
                .map(purchase -> userService.findById(purchase.getUserId()))
                .collect(Collectors.toList());
        model.addAttribute("userList", userList);
        return "expostats";
    }

    @PostMapping("/expositionstats")
    public String deleteUserFromExpositionStats(Model model) {
        purchaseService.deleteByUserId(
                Long.parseLong(
                        String.valueOf(
                                model.getAttribute("userId")
                        )
                )
        );
        return "redirect:/expositionstats";
    }
}
