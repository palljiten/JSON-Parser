package com.jsonParser.jsonParser.service;

import com.jsonParser.jsonParser.util.SimpleJsonParser;
import org.springframework.stereotype.Service;

@Service
public class JsonParserService {

    private final SimpleJsonParser parser = new SimpleJsonParser();

    public Object parseJson(String input) {
        return parser.parse(input);
    }

    public boolean validateJson(String input) {
        try {
            parser.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
