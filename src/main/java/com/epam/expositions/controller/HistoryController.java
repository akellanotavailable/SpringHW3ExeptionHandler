package com.epam.expositions.controller;

import com.epam.expositions.entity.Purchase;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.PurchaseService;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final ExpositionService expositionService;

    @GetMapping("/history")
    public String getPurchaseHistory(HttpSession session, Model model){
        String login = session.getAttribute("login").toString();
        Long userId = userService.findByLogin(login).getId();
        List<Purchase> purchaseList = purchaseService.findByUserId(userId);
        HashMap<String, String> purchaseMap = new HashMap<>();
        for (Purchase purchase:
                purchaseList) {
            purchaseMap.put(expositionService.findById(purchase.getExpositionId()).getTopic(),
                    purchase.getStatus().getName());
        }

        model.addAttribute("purchaseMap",purchaseMap.entrySet());

        return "history";
    }
}
