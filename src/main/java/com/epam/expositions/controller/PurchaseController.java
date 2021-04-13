package com.epam.expositions.controller;

import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Purchase;
import com.epam.expositions.entity.Status;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.ExpositionService;
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

@Controller
@RequiredArgsConstructor
public class PurchaseController {
    private final ExpositionService expositionService;
    private final UserService userService;
    private final PurchaseService purchaseService;
    private User user;
    private Exposition exposition;

    @GetMapping("/purchase")
    public String getPurchase(Model model){
        String username = String.valueOf(model.getAttribute("username"));
        Long expositionId = Long.parseLong(String.valueOf(model.getAttribute("expositionId")));

        user = userService.findByLogin(username);
        exposition = expositionService.findById(expositionId);

        List<Purchase> purchases = purchaseService.findByUserId(user.getId());
        if (purchases.stream().anyMatch(purchase -> purchase.getExpositionId().equals(exposition.getId()))) {
            throw new InvalidDataException("You already have this ticket.");
        }

        if (exposition.getCapacity() == 0){
            throw new InvalidDataException("Sold out.");
        }

        model.addAttribute("username", username);
        model.addAttribute("exposition", exposition);

        return "purchase";
    }

    @PostMapping("/purchase")
    public String purchaseComplete(){
        purchaseService.create(new Purchase(exposition.getId(),user.getId(), Status.PURCHASED));
        exposition.setCapacity(exposition.getCapacity()-1);
        expositionService.update(exposition,exposition.getId());
        return "redirect:/thanks";
    }
}
