package com.epam.expositions.controller;

import com.epam.expositions.exception.InvalidDataException;
import com.epam.expositions.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error/5xx";
    public static final String BAD_REQUEST_ERROR_VIEW = "error/403";

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Unexpected data received.")
    public ModelAndView invalidDataErrorHandler(HttpServletRequest req, InvalidDataException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(BAD_REQUEST_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User was not found.")
    public ModelAndView userNotFoundErrorHandler(HttpServletRequest req, UserNotFoundException e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(BAD_REQUEST_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}