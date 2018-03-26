package com.example.victor.smellslikebakin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class IngredientsFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "Key_Checked_Boxes";
    private CheckBox[] mCheckboxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.ingredientsLayout);
        String[] ingredients = Recipes.ingredients[index].split("`");
        mCheckboxes = new CheckBox[ingredients.length];
        boolean[] checkBoxes = new boolean[mCheckboxes.length];
        if (savedInstanceState != null
                && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        setUpCheckBoxes(ingredients, linearLayout, checkBoxes);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckboxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckboxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }

    private void setUpCheckBoxes(String[] ingredients, ViewGroup container, boolean[] checkBoxes) {
        int i = 0;
        for(String ingredient : ingredients) {
            mCheckboxes[i] = new CheckBox(getActivity());
            mCheckboxes[i].setPadding(8, 16, 8, 16);
            mCheckboxes[i].setTextSize(20f);
            mCheckboxes[i].setText(ingredient);
            container.addView(mCheckboxes[i]);
            if (checkBoxes[i]) {
                mCheckboxes[i].toggle();
            }
            i++;
        }


    }
}
