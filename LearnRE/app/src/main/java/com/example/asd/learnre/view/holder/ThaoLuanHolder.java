package com.example.asd.learnre.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.asd.learnre.databinding.ListItemThaoLuanBinding;

/**
 * Created by asd on 9/25/2016.
 */

public class ThaoLuanHolder extends RecyclerView.ViewHolder {

    private ListItemThaoLuanBinding binding;

    public ThaoLuanHolder(ListItemThaoLuanBinding binding) {
        super(binding.getRoot());
        this.binding= binding;
    }

    public ListItemThaoLuanBinding getBinding() {
        return binding;
    }

    public void setBinding(ListItemThaoLuanBinding binding) {
        this.binding = binding;
    }
}
