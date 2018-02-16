package com.udacity.sandwichclub.view.detail;

import android.content.Intent;

import com.udacity.sandwichclub.view.mvp.presenter.Presenter;

/**
 * Created by Sebastian Witasik on 16.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

interface DetailPresenter extends Presenter<PresentedDetailView> {
    DetailPresenter onIntentReceive(Intent intent);
}
