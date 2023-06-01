package com.front.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeFrontController {
    private static final Logger logger = LogManager.getLogger("HomeFrontController");

    @RequestMapping("/")
    public String home() {
        logger.info("GET /");

        return "redirect:/patient/list";
    }

}
