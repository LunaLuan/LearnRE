package com.example.asd.learnre.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asd.learnre.R;
import com.example.asd.learnre.databinding.FragmentBaiDocBinding;
import com.example.asd.learnre.model.obj.BaiHoc;
import com.example.asd.learnre.viewmodel.BaiHocViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaiDocFragment extends Fragment {

    private BaiHoc baiHoc;

    public BaiDocFragment(BaiHoc baiHoc) {
        // Required empty public constructor
        this.baiHoc= baiHoc;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentBaiDocBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_bai_doc, container, false);
        binding.setBaiHocViewModel(new BaiHocViewModel(getContext(), baiHoc));
        View root= binding.getRoot();
        // Toast.makeText(getContext(), baiHoc.getBaiDoc(), Toast.LENGTH_LONG).show();
        return root;
    }

}
