package com.udacity.sandwichclub.view.detail;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.view.mvp.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity implements PresentedDetailView {

    public static final String EXTRA_POSITION = "extra_position";

    @BindView(R.id.also_known_tv)
    TextView tvAlsoKnow;
    @BindView(R.id.also_known_label_tv)
    TextView tvAlsoKnowLbl;

    @BindView(R.id.ingredients_tv)
    TextView tvIngredients;
    @BindView(R.id.ingredients_label_tv)
    TextView tvIngredientsLbl;

    @BindView(R.id.origin_tv)
    TextView tvOrigin;
    @BindView(R.id.origin_label_tv)
    TextView tvOriginLbl;

    @BindView(R.id.description_tv)
    TextView tvDescription;
    @BindView(R.id.description_label_tv)
    TextView tvDescriptionLbl;

    @BindView(R.id.image_iv)
    ImageView ivSandwichImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        DetailPresenter presenter = new DetailPresenterImpl(this);
        presenter.onAttach(this);
        presenter.onIntentReceive(getIntent());
    }

    @Override
    public void showErrorEndExit(@StringRes int messageId) {
        finish();
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public PresentedDetailView setDesc(String desc) {
        setTextForField(desc, tvDescription, tvDescriptionLbl);
        return this;
    }

    @Override
    public PresentedDetailView setPlaceOfOrigin(String origin) {
        setTextForField(origin, tvOrigin, tvOriginLbl);
        return this;
    }

    @Override
    public PresentedDetailView setAltNames(String altNames) {
        setTextForField(altNames, tvAlsoKnow, tvAlsoKnowLbl);
        return this;
    }

    @Override
    public PresentedDetailView setIngredients(String ingredients) {
        setTextForField(ingredients, tvIngredients, tvIngredientsLbl);
        return this;
    }

    private void setTextForField(String text, TextView field, TextView label) {
        if(text.length()>0) {
            field.setText(text);
            field.setVisibility(View.VISIBLE);
            if(label!=null)
                label.setVisibility(View.VISIBLE);
        } else {
            field.setVisibility(View.GONE);
            if(label!=null)
                label.setVisibility(View.GONE);
        }
    }

    @Override
    public PresentedDetailView setMainName(String mainName) {
        setTitle(mainName);
        return this;
    }

    @Override
    public PresentedDetailView setImageForUrl(String imageUrl) {
        Picasso.with(this)
                .load(imageUrl)
                .into(ivSandwichImage);
        return this;
    }

    @Override
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(message)
                .setTitle(title);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
