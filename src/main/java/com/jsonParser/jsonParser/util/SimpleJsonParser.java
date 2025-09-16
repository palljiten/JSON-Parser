package com.jsonParser.jsonParser.util;

import com.jsonParser.jsonParser.exception.JsonParserException;

import java.util.*;

public class SimpleJsonParser {

    public Object parse(String json) {
        if (json == null || json.isBlank()) {
            throw new JsonParserException("Empty input JSON string");
        }
        return parseValue(new StringTokenizer(json, "{}[]:,\" ", true));
    }

    private Object parseValue(StringTokenizer tokenizer) {
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.equals("{")) return parseObject(tokenizer);
            if (token.equals("[")) return parseArray(tokenizer);
            if (token.startsWith("\"")) return token.replace("\"", "");
            if (token.matches("-?\\d+(\\.\\d+)?")) return Double.valueOf(token);
            if (token.equals("true") || token.equals("false")) return Boolean.valueOf(token);
            if (token.equals("null")) return null;
        }
        throw new JsonParserException("Invalid JSON structure");
    }

    private Map<String, Object> parseObject(StringTokenizer tokenizer) {
        Map<String, Object> map = new LinkedHashMap<>();
        String key = null;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty() || token.equals(",")) continue;
            if (token.equals("}")) break;

            if (key == null) {
                key = token.replace("\"", "");
            } else {
                if (token.equals(":")) {
                    Object value = parseValue(tokenizer);
                    map.put(key, value);
                    key = null;
                }
            }
        }
        return map;
    }

    private List<Object> parseArray(StringTokenizer tokenizer) {
        List<Object> list = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty() || token.equals(",")) continue;
            if (token.equals("]")) break;
            list.add(parseValue(new StringTokenizer(token + tokenizer.nextToken("]"), "{}[]:,\" ", true)));
        }
        return list;
    }
}