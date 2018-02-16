package com.udacity.sandwichclub.view.mvp.presenter;


import com.udacity.sandwichclub.view.mvp.view.PresentedView;

/**
 * Created by Sebastian Witasik on 08.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

public interface Presenter<V extends PresentedView> {

	void onAttach(V presented);

	void onDetach();

	boolean isViewAttached();
}
