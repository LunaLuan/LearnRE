package com.example.asd.learnre.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.asd.learnre.BR;
import com.example.asd.learnre.R;
import com.example.asd.learnre.model.obj.CauHoi;

import java.util.ArrayList;



/**
 * Created by asd on 9/17/2016.
 */

public class CauHoiViewModel extends BaseObservable {

    private Context context;

    // Cau hoi hien tai
    private int viTriCauHoiHienTai;
    private CauHoi cauHoi;


    public CauHoiViewModel(Context context, CauHoi cauHoi) {
        this.context = context;
        this.cauHoi= cauHoi;

        viTriCauHoiHienTai=0;
    }


    @Bindable
    public CauHoi getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(CauHoi cauHoi) {
        this.cauHoi= cauHoi;
        notifyPropertyChanged(BR.cauHoi);
    }


    public RadioGroup.OnCheckedChangeListener clickRadioButton() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        };
    }

}
