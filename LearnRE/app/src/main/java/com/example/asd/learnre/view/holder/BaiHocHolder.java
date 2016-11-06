package com.example.asd.learnre.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.asd.learnre.databinding.ListItemBaiHocBinding;

/**
 * Created by asd on 9/17/2016.
 */

public class BaiHocHolder extends RecyclerView.ViewHolder {

    private ListItemBaiHocBinding binding;

    public BaiHocHolder(ListItemBaiHocBinding binding) {
        super(binding.getRoot());
        this.binding= binding;
    }

    public ListItemBaiHocBinding getBinding() {
        return binding;
    }

    public void setBinding(ListItemBaiHocBinding binding) {
        this.binding = binding;
    }
}
