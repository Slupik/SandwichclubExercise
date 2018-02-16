package com.udacity.sandwichclub.view.detail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.view.mvp.presenter.BasePresenter;

import org.json.JSONException;

import java.util.List;

import static com.udacity.sandwichclub.view.detail.DetailActivity.EXTRA_POSITION;

/**
 * Created by Sebastian Witasik on 16.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

class DetailPresenterImpl extends BasePresenter<PresentedDetailView> implements DetailPresenter {

    private static final int DEFAULT_POSITION = -1;

    DetailPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public DetailPresenter onIntentReceive(Intent intent) {
        if (intent == null) {
            closeOnError();
            return this;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            positionNotFoundError();
            return this;
        }

        showDataForPosition(position);
        return this;
    }

    private void showDataForPosition(int position) {
        String json = getSandwichData(position);
        try {
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
            populateUI(sandwich);
        } catch (JSONException e) {
            e.printStackTrace();
            invalidDataError();
        }
    }

    private String getSandwichData(int position) {
        String[] sandwiches = context.getResources().getStringArray(R.array.sandwich_details);
        return sandwiches[position];
    }

    private void populateUI(@NonNull Sandwich sandwich) {
        presented.setAltNames(getAltNamesText(sandwich));
        presented.setIngredients(getIngredientsList(sandwich));
        presented.setPlaceOfOrigin(sandwich.getPlaceOfOrigin());
        presented.setImageForUrl(sandwich.getImage());
        presented.setDesc(sandwich.getDescription());
        presented.setMainName(sandwich.getMainName());
    }

    private String getAltNamesText(Sandwich sandwich) {
        return listToString(sandwich.getAlsoKnownAs());
    }

    private String getIngredientsList(Sandwich sandwich) {
        return listToString(sandwich.getIngredients());
    }

    private static String listToString(List<String> list) {
        StringBuilder text = new StringBuilder();
        for(String value:list) {
            if(text.length()==0) {
                text = new StringBuilder(value);
            } else {
                text.append(", ").append(value);
            }
        }
        return text.toString();
    }

    private void invalidDataError() {
        presented.showErrorEndExit(R.string.detail_error_message_invalid_data);
    }

    private void positionNotFoundError() {
        closeOnError();
    }

    private void closeOnError() {
        presented.showErrorEndExit(R.string.detail_error_message_data_not_avaible);
    }
}
