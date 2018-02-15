package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

interface JsonSandwichExample {
    Sandwich getSandwich();
    JSONObject getJson() throws JSONException;
    List<String> getIngredients() throws JSONException;
    String getImageUrl() throws JSONException;
    String getDescription() throws JSONException;
    String getPlaceOfOrigin() throws JSONException;
    List<String> getKnowAs() throws JSONException;
    String getMainName() throws JSONException;
    JSONObject getNameSection() throws JSONException;
}
