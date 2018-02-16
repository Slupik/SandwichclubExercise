package com.udacity.sandwichclub.view.mvp.presenter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;

import com.udacity.sandwichclub.view.mvp.view.PresentedView;

/**
 * Created by Sebastian Witasik on 08.02.2018.
 * E-mail: Sebastian Witasik
 * All rights reserved & copyright ©
 */

public abstract class BasePresenter<V extends PresentedView>
		implements Presenter<V> {
	protected final Context context;
	protected V presented;

	public BasePresenter(Context context) {
		this.context = context.getApplicationContext();
	}

	@Override
	@CallSuper
	public void onAttach(V presented) {
		this.presented = presented;
	}

	@Override
	@CallSuper
	public void onDetach(){
		if(null!= presented) {
			presented = null;
		}
	}

	@Override
	public boolean isViewAttached() {
		return this.presented != null;
	}

	protected String getString(@StringRes int source) {
		return context.getString(source);
	}
}
