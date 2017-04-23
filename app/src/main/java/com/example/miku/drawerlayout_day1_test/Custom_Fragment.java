package com.example.miku.drawerlayout_day1_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by miku on 2017/4/23.
 */
public class Custom_Fragment extends Fragment {
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.content_layout,container,false);
        textView = (TextView)view.findViewById(R.id.text);
        String text = getArguments().getString("data");
        textView.setText(text);
        return view;
    }
}
