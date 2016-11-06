package com.example.asd.learnre.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asd.learnre.R;

import com.example.asd.learnre.databinding.ListItemThaoLuanBinding;
import com.example.asd.learnre.model.obj.ThaoLuan;
import com.example.asd.learnre.view.holder.ThaoLuanHolder;
import com.example.asd.learnre.viewmodel.ThaoLuanViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by asd on 9/25/2016.
 */

public class DanhSachThaoLuanAdapter extends RecyclerView.Adapter<ThaoLuanHolder> {

    private List<ThaoLuan> danhSachThaoLuan;

    public DanhSachThaoLuanAdapter(List<ThaoLuan> danhSachThaoLuan) {
        this.danhSachThaoLuan = danhSachThaoLuan;
    }

    @Override
    public ThaoLuanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemThaoLuanBinding binding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_thao_luan, parent, false);
        return new ThaoLuanHolder(binding);
    }

    @Override
    public void onBindViewHolder(ThaoLuanHolder holder, int position) {
        ImageView ivAvatar= holder.getBinding().ivAvatar;
        ThaoLuan thaoLuan= danhSachThaoLuan.get(position);
        Picasso.with(ivAvatar.getContext()).load(thaoLuan.getUser().getPhotoUrl()).into(ivAvatar);
        holder.getBinding().
                setThaoLuanViewModel(new ThaoLuanViewModel(danhSachThaoLuan.get(position)));
    }

    @Override
    public int getItemCount() {
        return danhSachThaoLuan.size();
    }

}
