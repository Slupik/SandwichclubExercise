package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */
public class SandwichJsonParserTest {
    private JsonSandwichExample validExample = new ValidSandwichJson();

    @Test
    public void getParsedSandwich() throws Exception {
        Sandwich example = validExample.getSandwich();
        Sandwich returned = SandwichJsonParser.getParsedSandwich(validExample.getJson().toString());
        Assert.assertEquals(example.getAlsoKnownAs(), returned.getAlsoKnownAs());
        Assert.assertEquals(example.getDescription(), returned.getDescription());
        Assert.assertEquals(example.getImage(), returned.getImage());
        Assert.assertEquals(example.getIngredients(), returned.getIngredients());
        Assert.assertEquals(example.getMainName(), returned.getMainName());
        Assert.assertEquals(example.getPlaceOfOrigin(), returned.getPlaceOfOrigin());
    }

    @Test
    public void getIngredients() throws Exception {
        Assert.assertEquals(validExample.getIngredients(),
                SandwichJsonParser.getIngredients(validExample.getJson()));
    }

    @Test
    public void getImageUrl() throws Exception {
        Assert.assertEquals(validExample.getImageUrl(),
                SandwichJsonParser.getImageUrl(validExample.getJson()));
    }

    @Test
    public void getDescription() throws Exception {
        Assert.assertEquals(validExample.getDescription(),
                SandwichJsonParser.getDescription(validExample.getJson()));
    }

    @Test
    public void getPlaceOfOrigin() throws Exception {
        Assert.assertEquals(validExample.getPlaceOfOrigin(),
                SandwichJsonParser.getPlaceOfOrigin(validExample.getJson()));
    }

    @Test
    public void getKnowAs() throws Exception {
        Assert.assertEquals(validExample.getKnowAs(),
                SandwichJsonParser.getKnowAs(validExample.getJson()));
    }

    @Test
    public void getMainName() throws Exception {
        Assert.assertEquals(validExample.getMainName(),
                SandwichJsonParser.getMainName(validExample.getJson()));
    }

    @Test
    public void getNameSection() throws Exception {
        Assert.assertEquals(
                validExample.getNameSection().toString(),
                SandwichJsonParser.getNameSection(validExample.getJson()).toString());
    }
}