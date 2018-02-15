package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String raw) throws JSONException {
        return SandwichJsonParser.getParsedSandwich(raw);
    }

    static List<String> jsonArrayToStringList(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        for(int i=0; i<array.length();i++) {
            list.add(array.getString(i));
        }
        return list;
    }
}
