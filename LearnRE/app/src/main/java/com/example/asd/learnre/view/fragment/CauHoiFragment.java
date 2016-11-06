package com.example.asd.learnre.view.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.asd.learnre.R;
import com.example.asd.learnre.databinding.DialogKetThucBinding;
import com.example.asd.learnre.databinding.FragmentCauHoiBinding;
import com.example.asd.learnre.model.database.CauHoiDB;
import com.example.asd.learnre.model.obj.CauHoi;
import com.example.asd.learnre.view.activity.DanhSachThaoLuanActivity;

import com.example.asd.learnre.viewmodel.CauHoiViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CauHoiFragment extends Fragment {

    private FragmentCauHoiBinding binding;
    private DialogKetThucBinding ketThucBinding;

    private ArrayList<CauHoi> danhSachCauHoi;

    private CauHoiViewModel cauHoiViewModel;

    private int viTriCauHoiHienTai=0;

    private String checkedItem= null;

    private ArrayList<String> dapAnCuaNguoiDung;

    private int idBaiHoc;

    public CauHoiFragment(int idBaiHoc) {
        // Required empty public constructor
        this.idBaiHoc= idBaiHoc;
        danhSachCauHoi= CauHoiDB.getInstance(getContext()).getDanhSachBaiHoc(idBaiHoc);
        cauHoiViewModel=new CauHoiViewModel(getContext(), danhSachCauHoi.get(viTriCauHoiHienTai));
        checkedItem=null;
        dapAnCuaNguoiDung=new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_cau_hoi, container, false);
        binding.setCauHoiViewModel(cauHoiViewModel);
        setOnClickBtnNext();
        setOnCheckedChangeRadioGroup();

        return binding.getRoot();
    }


    public void setOnCheckedChangeRadioGroup() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbDapAnA)
                    checkedItem="A";
                if(checkedId==R.id.rbDapAnB)
                    checkedItem="B";
                if(checkedId==R.id.rbDapAnC)
                    checkedItem="C";
                if(checkedId==R.id.rbDapAnD)
                    checkedItem="D";
            }
        });

    }

    public void setOnClickBtnNext() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedItem==null) {
                    Snackbar.make(v, "Bạn phải chọn một đáp án...", Snackbar.LENGTH_LONG).show();
                }
                else {
                    dapAnCuaNguoiDung.add(checkedItem);
                    viTriCauHoiHienTai++;
                    if(viTriCauHoiHienTai<danhSachCauHoi.size()) {
                        cauHoiViewModel.setCauHoi(danhSachCauHoi.get(viTriCauHoiHienTai));
                    }
                    else {
                        ketThuc(createThanhTich());
                    }
                }
                binding.radioGroup.clearCheck();
                checkedItem=null;

            }
        });
    }

    private String createThanhTich() {
        String thanhTich="";
        for(int i=0;i<dapAnCuaNguoiDung.size();i++) {
            thanhTich+="Câu "+(i+1)+":\n";
            thanhTich+="- Bạn chọn: "+dapAnCuaNguoiDung.get(i)+"\n";
            thanhTich+="- Kết quả: ";
            // Nếu đáp án của người dùng giống đáp án đúng
            if(dapAnCuaNguoiDung.get(i).equals(danhSachCauHoi.get(i).getDapAnDung())) {
                thanhTich+="Đúng\n";
            }
            else {
                thanhTich+="Sai\n";
            }
        }
        return thanhTich;
    }

    private void ketThuc(String thanhTich) {
        final Dialog ketThucDialog= new Dialog(getContext());
        ketThucBinding= DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_ket_thuc, null, false);

        ketThucDialog.setContentView(ketThucBinding.getRoot());
        ketThucDialog.setTitle("Kết quả");
        ketThucBinding.tvThanhTich.setText(thanhTich);
        ketThucBinding.btnThaoLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DanhSachThaoLuanActivity.class);
                intent.putExtra("idBaiHoc", idBaiHoc);
                startActivity(intent);
                ketThucDialog.dismiss();
                getActivity().finish();
            }
        });
        ketThucDialog.setCanceledOnTouchOutside(false);
        ketThucDialog.show();
    }


}
