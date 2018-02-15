package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import junit.framework.Assert;

import org.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */
public class JsonUtilsTest {
    private JsonSandwichExample validExample = new ValidSandwichJson();

    @Test
    public void parseSandwichJson() throws Exception {
        Sandwich example = validExample.getSandwich();
        Sandwich returned = JsonUtils.parseSandwichJson(validExample.getJson().toString());
        Assert.assertEquals(example.getAlsoKnownAs(), returned.getAlsoKnownAs());
        Assert.assertEquals(example.getDescription(), returned.getDescription());
        Assert.assertEquals(example.getImage(), returned.getImage());
        Assert.assertEquals(example.getIngredients(), returned.getIngredients());
        Assert.assertEquals(example.getMainName(), returned.getMainName());
        Assert.assertEquals(example.getPlaceOfOrigin(), returned.getPlaceOfOrigin());
    }

    @Test
    public void jsonArrayToStringList() throws Exception {
        JSONArray array = new JSONArray();
        List<String> list = getExampleList();
        for(String value:list) {
            array.put(value);
        }
        List<String> returnedList = JsonUtils.jsonArrayToStringList(array);
        Assert.assertEquals(list, returnedList);
    }

    private static List<String> getExampleList(){
        List<String> list = new ArrayList<>();
        list.add("value1");
        list.add("value2");
        list.add("value3");
        list.add("value4");
        return list;
    }
}