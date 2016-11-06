package com.example.asd.learnre.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asd.learnre.R;
import com.example.asd.learnre.databinding.ActivityDanhSachBaiHocBinding;
import com.example.asd.learnre.model.database.BaiHocDB;
import com.example.asd.learnre.model.obj.BaiHoc;
import com.example.asd.learnre.view.adapter.DanhSachBaiHocAdapter;

import java.util.ArrayList;
import java.util.List;

public class DanhSachBaiHocActivity extends AppCompatActivity {

    private RecyclerView rvBaiHoc;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<BaiHoc> danhSachBaiHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDanhSachBaiHocBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_danh_sach_bai_hoc);
        this.setTitle(getString(R.string.title_activity_danh_sach_bai_hoc));

        rvBaiHoc= binding.rvBaiHoc;
        rvBaiHoc.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(this);
        rvBaiHoc.setLayoutManager(mLayoutManager);

        danhSachBaiHoc= BaiHocDB.getInstance(this).getDanhBaiHoc();

        mAdapter=new DanhSachBaiHocAdapter(this, danhSachBaiHoc);
        rvBaiHoc.setAdapter(mAdapter);
    }




}
