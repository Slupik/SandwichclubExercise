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
        JSONArray altNames = json.optJSONArray("ingredients");
        return JsonUtils.jsonArrayToStringList(altNames);
    }

    static String getImageUrl(JSONObject json) throws JSONException {
        return json.optString("image");
    }

    static String getDescription(JSONObject json) throws JSONException {
        return json.optString("description");
    }

    static String getPlaceOfOrigin(JSONObject json) throws JSONException {
        return json.optString("placeOfOrigin");
    }

    static List<String> getKnowAs(JSONObject json) throws JSONException {
        JSONObject nameSection = getNameSection(json);
        JSONArray altNames = nameSection.optJSONArray("alsoKnownAs");
        return JsonUtils.jsonArrayToStringList(altNames);
    }

    static String getMainName(JSONObject json) throws JSONException {
        JSONObject nameSection = getNameSection(json);
        return nameSection.optString("mainName");
    }

    static JSONObject getNameSection(JSONObject json) throws JSONException {
        return json.optJSONObject("name");
    }
}
