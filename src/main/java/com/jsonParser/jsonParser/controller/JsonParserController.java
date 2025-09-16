package com.jsonParser.jsonParser.controller;

import com.jsonParser.jsonParser.service.JsonParserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JsonParserController {

    private static final Logger logger = LoggerFactory.getLogger(JsonParserController.class);
    private final JsonParserService service;

    @PostMapping("/parse")
    public ResponseEntity<Object> parseJson(@RequestBody String input) {
        logger.info("Received JSON for parsing: {}", input);
        return ResponseEntity.ok(service.parseJson(input));
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateJson(@RequestBody String input) {
        logger.info("Validating JSON...");
        return ResponseEntity.ok(service.validateJson(input));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.info("Health check invoked");
        return ResponseEntity.ok("OK");
    }
}

