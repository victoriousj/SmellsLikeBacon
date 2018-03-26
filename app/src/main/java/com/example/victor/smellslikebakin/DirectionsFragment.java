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

public class DirectionsFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "Key Checked Boxes";
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_directions, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.directionsLayout);
        String[] directions = Recipes.directions[index].split("`");
        mCheckBoxes = new CheckBox[directions.length];
        boolean[] checkBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null
                && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        setUpCheckBoxes(directions, linearLayout, checkBoxes);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckBoxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }

    private void setUpCheckBoxes(String[] directions, ViewGroup container, boolean[] checkBoxes) {
        int i = 0;
        for (String direction: directions) {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(direction);
            container.addView(mCheckBoxes[i]);
            if (checkBoxes[i]) {
                mCheckBoxes[i].toggle();
            }
            i++;
        }
    }
}
