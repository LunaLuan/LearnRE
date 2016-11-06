package com.example.asd.learnre.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.asd.learnre.BR;
import com.example.asd.learnre.model.obj.ThaoLuan;

/**
 * Created by asd on 9/25/2016.
 */

public class ThaoLuanViewModel extends BaseObservable {

    private ThaoLuan thaoLuan;

    public ThaoLuanViewModel(ThaoLuan thaoLuan) {
        this.thaoLuan= thaoLuan;
    }

    @Bindable
    public ThaoLuan getThaoLuan() {
        return thaoLuan;
    }

    public void setThaoLuan(ThaoLuan thaoLuan) {
        this.thaoLuan= thaoLuan;
        notifyPropertyChanged(BR.thaoLuan);
    }


}
