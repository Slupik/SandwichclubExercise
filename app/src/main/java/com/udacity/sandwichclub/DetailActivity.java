package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.also_known_tv)
    TextView tvAlsoKnow;

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

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
            invalidDataError();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void invalidDataError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message_invalid_data, Toast.LENGTH_SHORT).show();
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message_data_not_avaible, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        setTextForField(sandwich.getDescription(), tvDescription, tvDescriptionLbl);
        setTextForField(sandwich.getPlaceOfOrigin(), tvOrigin, tvOriginLbl);
        setTextForField(getAltNamesText(sandwich), tvAlsoKnow, null);
        setTextForField(getIngredientsList(sandwich), tvIngredients, tvIngredientsLbl);
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
}
