package com.example.asd.learnre.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.example.asd.learnre.BR;
import com.example.asd.learnre.model.database.LearnREContract;
import com.example.asd.learnre.model.obj.BaiHoc;
import com.example.asd.learnre.view.activity.BaiHocActivity;
import com.example.asd.learnre.view.activity.DanhSachBaiHocActivity;

/**
 * Created by asd on 9/17/2016.
 */

public class BaiHocViewModel extends BaseObservable {

    private BaiHoc baiHoc;
    private Context context;

    public BaiHocViewModel(Context context, BaiHoc baiHoc) {
        this.baiHoc = baiHoc;
        this.context= context;
    }

    @Bindable
    public String getChuDe() {
        return baiHoc.getChuDe();
    }

    public void setChuDe(String chuDe) {
        baiHoc.setChuDe(chuDe);
        notifyPropertyChanged(BR.chuDe);
    }

    @Bindable
    public String getBaiDoc() {
        return baiHoc.getBaiDoc();
    }

    public void setBaiDoc(String baiDoc) {
        baiHoc.setBaiDoc(baiDoc);
        notifyPropertyChanged(BR.baiDoc);
    }

    public View.OnClickListener clickBaiHoc() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof DanhSachBaiHocActivity) {
                    DanhSachBaiHocActivity activity= (DanhSachBaiHocActivity) context;
                    Intent intent=new Intent(activity, BaiHocActivity.class);
                    pushBaiHoc(intent);

                    context.startActivity(intent);
                }
            }
        };
    }

    private void pushBaiHoc(Intent intent) {
        intent.putExtra(LearnREContract.BaiHocEntry.idBaiHoc, baiHoc.getIdBaiHoc());
        intent.putExtra(LearnREContract.BaiHocEntry.chuDe, baiHoc.getChuDe());
        intent.putExtra(LearnREContract.BaiHocEntry.baiDoc, baiHoc.getBaiDoc());
    }

}
