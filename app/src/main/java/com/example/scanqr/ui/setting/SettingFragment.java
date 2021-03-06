package com.example.scanqr.ui.setting;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.scanqr.R;
import com.example.scanqr.model.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment implements SettingInterface.ui {

    private RecyclerView mRecyclerView;
    private SettingAdapter mSettingAdapter;
    private List<Setting> mArrayList;
    private Toolbar mToolbar;
    private View mRoot;
    private AlertDialog mAlertDialog;
    private CharSequence[] mValues = {" 70 ثانیه ", " 50 ثانیه", " 40 ثانیه "};
    private int mId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.fragment_settings, container, false);

        dataSet();
        init();
        recyclerView();
        toolBar();
        return mRoot;

    }

    private void dataSet() {

        mArrayList = new ArrayList<>();
        mArrayList.add(new Setting("بازه زمانی قفل صفحه", Setting.ONE_TYPE, R.drawable.ic_access_alarm_black_24dp, "60ثانیه"));
        mArrayList.add(new Setting("روش احراز هویت", Setting.ONE_TYPE, R.drawable.ic_forward_10_black_24dp, "الگو"));
        mArrayList.add(new Setting("راهنمای برنامه", Setting.Two_TYPE, R.drawable.ic_home_black_24dp, null));
        mArrayList.add(new Setting("پشتیبانی بانک ها", Setting.Two_TYPE, R.drawable.ic_call_black_24dp, null));

    }

    @Override
    public void change() {
        switch (mArrayList.get(1).getName()) {

            case "الگو":
                mArrayList.get(1).setName("password");
                break;

            case "password":
                mArrayList.get(1).setName("الگو");
                break;
        }

        mSettingAdapter.notifyDataSetChanged();
    }

    @Override
    public void init() {
        mRecyclerView = mRoot.findViewById(R.id.RecyclerView);
        mToolbar = mRoot.findViewById(R.id.Toolbar_setting);
    }

    @Override
    public void initListen(AlertDialog.Builder builder) {
        builder.setSingleChoiceItems(mValues, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:
                        mId = 0;

                        break;
                    case 1:
                        mId = 1;

                        break;
                    case 2:

                        mId = 2;

                        break;
                }

            }
        });
    }

    @Override
    public void recyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSettingAdapter = new SettingAdapter(mArrayList, SettingFragment.this);
        mRecyclerView.setAdapter(mSettingAdapter);
    }

    @Override
    public void toolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("setting");
    }

    @Override
    public void alertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("You want to change ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (mId) {
                    case 0:
                        mArrayList.get(0).setName("70 ثانیه");
                        mSettingAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        mArrayList.get(0).setName("50 ثانیه");
                        mSettingAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        mArrayList.get(0).setName("40 ثانیه");
                        mSettingAdapter.notifyDataSetChanged();
                }


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        initListen(builder);
        mAlertDialog = builder.create();
        mAlertDialog.show();

    }
}


