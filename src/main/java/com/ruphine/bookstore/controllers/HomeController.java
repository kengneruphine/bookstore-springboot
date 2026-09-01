package com.ruphine.bookstore.controllers;

import com.ruphine.bookstore.dto.BookDto;
import com.ruphine.bookstore.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@RestController
@RequestMapping("/")
@Tag(name = "Home Controller", description = "APIs for managing home page")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping({ "/", "/api", "/api/v1" })
    @Operation(summary = "Home Page", description = "Returns a welcome message for the bookstore application.")
    public ResponseEntity<String> home() {
        logger.info("Accessed home page");
        return ResponseEntity.ok("Welcome to the Bookstore Application!");
    }

    @GetMapping("/api/v1/health")
    @Operation(summary = "Health Check", description = "Checks the health status of the bookstore application.")
    public ResponseEntity<String> healthCheck() {
        logger.info("Health check accessed");
        return ResponseEntity.ok("Bookstore Application is running!");
    }

    @GetMapping("/*")
    @Operation(summary = "Default Handler", description = "Handles default requests for the bookstore application.")
    public ResponseEntity<String> defaultHandler() {
        logger.info("Default handler accessed");
        return ResponseEntity.ok("Default response for the Bookstore Application!");
    }

}
