package com.epam.expositions.controller;

import com.epam.expositions.dto.DurationDTO;
import com.epam.expositions.dto.HallTimetableDTO;
import com.epam.expositions.entity.Exposition;
import com.epam.expositions.entity.Hall;
import com.epam.expositions.entity.Role;
import com.epam.expositions.entity.Status;
import com.epam.expositions.entity.User;
import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.service.ExpositionService;
import com.epam.expositions.service.HallService;
import com.epam.expositions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CreateExpositionController {

    private final ExpositionService expositionService;
    private final UserService userService;
    private final HallService hallService;
/*
    @InitBinder
    public void initBinderUsuriousFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        binder.registerCustomEditor(LocalDateTime.class, new CustomDateEditor(dateFormat, true));
    }*/

    @GetMapping("/newexposition")
    public String newExposition(Model model) {
        model.addAttribute("exposition", new Exposition());
        return "newexpo";
    }

    @PostMapping("/newexposition")
    public String createNewExposition(Model model, @ModelAttribute Exposition exposition,
                                      HttpSession session) {
        User user = userService.findByLogin(String.valueOf(session.getAttribute("login")));

        if (exposition.getDateStart().isAfter(exposition.getDateEnd())) {
            throw new InvalidDataException("Start date is after end date.");
        }

        if (exposition.getCapacity() <= 0) {
            throw new InvalidDataException("Please enter valid number of tickets.");
        }

        if (exposition.getPrice().doubleValue() < 0) {
            throw new InvalidDataException("Please enter valid price.");
        }

        exposition.setHostId(user.getId());
        exposition.setStatusName(Status.WAITING.getName());

        expositionService.create(exposition);

        if (!user.getRole().getName().equals("client") || !user.getRole().getName().equals("admin")) {
            user.setRole(new Role(2L, "client"));
            userService.update(user, user.getId());
        }

        session.setAttribute("exposition", exposition);

        DurationDTO reservationTime = new DurationDTO(exposition.getDateStart(), exposition.getDateEnd());

        List<HallTimetableDTO> hallTimetableList = hallService.findAll();

        hallTimetableList = hallTimetableList.stream()
                .filter(hallTimetableDTO ->
                        hallTimetableDTO.getReservationDateTime().stream().allMatch(unavailableTime ->
                                //if start time is within reserved time
                                !(reservationTime.getDateStart().isAfter(unavailableTime.getDateStart()) &&
                                        reservationTime.getDateStart().isBefore(unavailableTime.getDateEnd()))
                                        &&
                                        //if end time is within reserved time
                                        !(reservationTime.getDateEnd().isBefore(unavailableTime.getDateEnd()) &&
                                                reservationTime.getDateEnd().isAfter(unavailableTime.getDateStart()))))
                .collect(Collectors.toList());

        List<Hall> hallList = hallTimetableList.stream()
                .map(HallTimetableDTO::getHall)
                .collect(Collectors.toList());

        session.setAttribute("hallList", hallList);
        model.addAttribute("hallListCheck", new ArrayList<Hall>());

        return "newexpohall";
    }
}
