package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

class ValidSandwichJson implements JsonSandwichExample {
    private static final String MAIN_NAME = "the name";
    private static final List<String> ALT_NAMES = getExampleAltNameList();
    private static final String PLACE_OF_ORIGIN = "origin";
    private static final String DESC = "the best desc in the world!";
    private static final String IMAGE_URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG";
    private static final List<String> INGREDIENTS = getExampleIngredients();

    @Override
    public Sandwich getSandwich() {
        Sandwich sandwich = new Sandwich();
        sandwich.setIngredients(INGREDIENTS);
        sandwich.setDescription(DESC);
        sandwich.setImage(IMAGE_URL);
        sandwich.setAlsoKnownAs(ALT_NAMES);
        sandwich.setMainName(MAIN_NAME);
        sandwich.setPlaceOfOrigin(PLACE_OF_ORIGIN);
        return sandwich;
    }

    @Override
    public JSONObject getJson() throws JSONException {
        JSONObject newJson = new JSONObject();
        newJson.put("name", getNameSection());
        newJson.put("placeOfOrigin", PLACE_OF_ORIGIN);
        newJson.put("description", DESC);
        newJson.put("image", IMAGE_URL);
        newJson.put("ingredients", getAsJsonArray(INGREDIENTS));

        return newJson;
    }

    @Override
    public List<String> getIngredients() throws JSONException {
        return INGREDIENTS;
    }

    @Override
    public String getImageUrl() throws JSONException {
        return IMAGE_URL;
    }

    @Override
    public String getDescription() throws JSONException {
        return DESC;
    }

    @Override
    public String getPlaceOfOrigin() throws JSONException {
        return PLACE_OF_ORIGIN;
    }

    @Override
    public List<String> getKnowAs() throws JSONException {
        return ALT_NAMES;
    }

    @Override
    public String getMainName() throws JSONException {
        return MAIN_NAME;
    }

    @Override
    public JSONObject getNameSection() throws JSONException {
        JSONObject nameSection = new JSONObject();
        nameSection.put("mainName", MAIN_NAME);
        nameSection.put("alsoKnownAs", getAsJsonArray(ALT_NAMES));
        return nameSection;
    }

    private static List<String> getExampleAltNameList() {
        List<String> newList = new ArrayList<>();
        newList.add("altName1");
        newList.add("altName2");
        return newList;
    }

    private static List<String> getExampleIngredients() {
        List<String> newList = new ArrayList<>();
        newList.add("Sliced bread");
        newList.add("Cheese");
        newList.add("Ham");
        return newList;
    }

    private static JSONArray getAsJsonArray(List<String> list) {
        JSONArray array = new JSONArray();
        for(String value:list) {
            array.put(value);
        }
        return array;
    }
}
