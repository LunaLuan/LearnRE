package com.example.asd.learnre.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.asd.learnre.R;
import com.example.asd.learnre.databinding.ListItemBaiHocBinding;
import com.example.asd.learnre.model.obj.BaiHoc;
import com.example.asd.learnre.view.holder.BaiHocHolder;
import com.example.asd.learnre.viewmodel.BaiHocViewModel;

import java.util.List;

/**
 * Created by asd on 9/17/2016.
 */

public class DanhSachBaiHocAdapter extends RecyclerView.Adapter<BaiHocHolder> {

    private List<BaiHoc> danhSachBaiHoc;
    private Context context;


    public DanhSachBaiHocAdapter(Context context, List<BaiHoc> danhSachBaiHoc) {
        this.danhSachBaiHoc= danhSachBaiHoc;
        this.context= context;
    }

    @Override
    public BaiHocHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBaiHocBinding binding=
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_bai_hoc, parent, false);
        return new BaiHocHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaiHocHolder holder, int position) {
        ListItemBaiHocBinding binding= holder.getBinding();
        holder.getBinding().setBaiHocViewModel(new BaiHocViewModel(context, danhSachBaiHoc.get(position)));
    }

    @Override
    public int getItemCount() {
        return danhSachBaiHoc.size();
    }
}
