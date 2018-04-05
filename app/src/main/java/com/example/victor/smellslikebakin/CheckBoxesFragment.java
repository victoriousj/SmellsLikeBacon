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

public abstract class CheckBoxesFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "Key_Checked_Boxes";
    private CheckBox[] mCheckboxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.checkBoxesLayout);
        String[] contents = getContents(index);
        mCheckboxes = new CheckBox[contents.length];
        boolean[] checkBoxes = new boolean[mCheckboxes.length];
        if (savedInstanceState != null
                && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);
        }
        setUpCheckBoxes(contents, linearLayout, checkBoxes);
        return view;
    }

    public abstract String[] getContents(int index);

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

    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkBoxes) {
        int i = 0;
        for(String content : contents) {
            mCheckboxes[i] = new CheckBox(getActivity());
            mCheckboxes[i].setPadding(8, 16, 8, 16);
            mCheckboxes[i].setTextSize(20f);
            mCheckboxes[i].setText(content);
            container.addView(mCheckboxes[i]);
            if (checkBoxes[i]) {
                mCheckboxes[i].toggle();
            }
            i++;
        }
    }
}
