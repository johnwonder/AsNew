package com.ufida.john.mydrawerlayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by john on 2015/10/11.
 */
public class ContentFragment extends Fragment {

    private TextView tv_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        String  text = getArguments().getString("text");
        tv_content.setText(text);
        return  view;
    }
}
