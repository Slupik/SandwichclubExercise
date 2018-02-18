package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

class SandwichJsonParser {
    private static final String INGREDIENTS = "ingredients";
    private static final String IMAGE = "image";
    private static final String DESC = "description";
    private static final String ORIGIN = "placeOfOrigin";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String MAIN_NAME = "mainName";
    private static final String SECTION_NAME = "name";

    static Sandwich getParsedSandwich(String raw) throws JSONException {
        JSONObject json = new JSONObject(raw);
        Sandwich sandwich = new Sandwich();
        sandwich.setMainName(getMainName(json));
        sandwich.setAlsoKnownAs(getKnowAs(json));
        sandwich.setPlaceOfOrigin(getPlaceOfOrigin(json));
        sandwich.setImage(getImageUrl(json));
        sandwich.setDescription(getDescription(json));
        sandwich.setIngredients(getIngredients(json));
        return sandwich;
    }

    static List<String> getIngredients(JSONObject json) throws JSONException {
        JSONArray ingredients = json.optJSONArray(INGREDIENTS);
        return JsonUtils.jsonArrayToStringList(ingredients);
    }

    static String getImageUrl(JSONObject json) throws JSONException {
        return json.optString(IMAGE);
    }

    static String getDescription(JSONObject json) throws JSONException {
        return json.optString(DESC);
    }

    static String getPlaceOfOrigin(JSONObject json) throws JSONException {
        return json.optString(ORIGIN);
    }

    static List<String> getKnowAs(JSONObject json) throws JSONException {
        JSONObject nameSection = getNameSection(json);
        JSONArray alsoKnownAs = nameSection.optJSONArray(ALSO_KNOWN_AS);
        return JsonUtils.jsonArrayToStringList(alsoKnownAs);
    }

    static String getMainName(JSONObject json) throws JSONException {
        JSONObject nameSection = getNameSection(json);
        return nameSection.optString(MAIN_NAME);
    }

    static JSONObject getNameSection(JSONObject json) throws JSONException {
        return json.optJSONObject(SECTION_NAME);
    }
}
