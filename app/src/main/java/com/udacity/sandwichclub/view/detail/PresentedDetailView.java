package com.udacity.sandwichclub.view.detail;

import android.support.annotation.StringRes;

import com.udacity.sandwichclub.view.mvp.view.PresentedView;

/**
 * Created by Sebastian Witasik on 15.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

interface PresentedDetailView extends PresentedView {
    void showErrorEndExit(@StringRes int messageId);
    PresentedDetailView setDesc(String desc);
    PresentedDetailView setPlaceOfOrigin(String origin);
    PresentedDetailView setAltNames(String altNames);
    PresentedDetailView setIngredients(String ingredients);
    PresentedDetailView setMainName(String mainName);
    PresentedDetailView setImageForUrl(String imageUrl);
}
