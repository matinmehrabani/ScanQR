package com.example.scanqr.ui.setting;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.scanqr.R;
import com.example.scanqr.model.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements SettingInterface.ui {

   private RecyclerView mRecyclerView;
   private SettingAdapter mSettingAdapter;
   private List<Settings> mArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        dataSet();
        mRecyclerView = view.findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSettingAdapter = new SettingAdapter(mArrayList, SettingFragment.this);
        mRecyclerView.setAdapter(mSettingAdapter);


        return view;

    }

    private void dataSet() {

        mArrayList = new ArrayList<>();
        mArrayList.add(new Settings("بازه زمانی قفل صفحه", Settings.ONE_TYPE, R.drawable.ic_access_alarm_black_24dp,"60ثانیه"));
        mArrayList.add(new Settings("روش احراز هویت", Settings.ONE_TYPE, R.drawable.ic_forward_10_black_24dp, "الگو"));
        mArrayList.add(new Settings("راهنمای برنامه", Settings.Two_TYPE, R.drawable.ic_home_black_24dp, null));
        mArrayList.add(new Settings("پشتیبانی بانک ها", Settings.Two_TYPE, R.drawable.ic_call_black_24dp, null));

    }

    @Override
    public void alert(List<Settings> list) {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radio_button);
        List<String> stringList=new ArrayList<>();

        stringList.add("ثانیه" + (40));
        stringList.add("ثانیه" + (50));
        stringList.add("ثانیه" + (30));
        stringList.add("ثانیه" + (35));

        RadioGroup radioGroup =  dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton radioButton=new RadioButton(getContext());
            radioButton.setText(stringList.get(i));
            radioGroup.addView(radioButton);
        }

        dialog.show();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int childCount = group.getChildCount();
                for (int x = 0; x < childCount; x++) {
                    RadioButton radioButton = (RadioButton) group.getChildAt(x);
                    if (radioButton.getId() == checkedId) {

                        Toast.makeText(getContext(), radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
      mArrayList.get(0).setName(radioButton.getText().toString());
                        mSettingAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @Override
    public void change() {
        mArrayList.get(1).setName("password");
        mSettingAdapter.notifyDataSetChanged();
    }

}