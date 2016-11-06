package com.example.asd.learnre.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.asd.learnre.model.obj.CauHoi;

import java.util.ArrayList;

/**
 * Created by asd on 9/17/2016.
 */

public class CauHoiDB {

    private SQLiteDatabase database;
    private LearnREDatabase db;

    private static final String table= LearnREContract.CauHoi;

    private static CauHoiDB instance;

    public static CauHoiDB getInstance(Context context) {
        if(instance==null) {
            instance=new CauHoiDB(context);
        }
        return instance;
    }

    public CauHoiDB(Context context) {
        db= LearnREDatabase.getInstance(context);
    }

    public ArrayList<CauHoi> getDanhSachBaiHoc(int idBaiHoc) {
        database= db.getReadableDatabase();
        String columns[] = {LearnREContract.CauHoiEntry.ndCauHoi,
                            LearnREContract.CauHoiEntry.dapAnA,
                            LearnREContract.CauHoiEntry.dapAnB,
                            LearnREContract.CauHoiEntry.dapAnC,
                            LearnREContract.CauHoiEntry.dapAnD,
                            LearnREContract.CauHoiEntry.dapAnDung};
        Cursor cursor = database.query(table, columns, "idBaiHoc="+idBaiHoc, null, null,
                null, null);
        Log.d("Đã đến đoạn này", "Với idBaiHoc là:" +idBaiHoc);
        if(cursor.moveToFirst()) {
            ArrayList<CauHoi> danhSachCauHoi=new ArrayList<>();
            Log.d("Kích thước cursor: "+cursor.getCount()," ok");
            for(int i=0;i<cursor.getCount();i++) {
                CauHoi cauHoi=doiCursorThanhCauHoi(cursor);
                danhSachCauHoi.add(cauHoi);
                Log.d("Câu hỏi "+i, cauHoi.getNdCauHoi());
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return danhSachCauHoi;
        }
        else {
            cursor.close();
            database.close();
            Log.d("Đã bị null", "ĐM đời");
            return null;
        }
    }

    private CauHoi doiCursorThanhCauHoi(Cursor cursor) {
        int idCauHoiIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.idCauHoi);
        int ndCauHoiIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.ndCauHoi);

        int dapAnAIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.dapAnA);
        int dapAnBIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.dapAnB);
        int dapAnCIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.dapAnC);
        int dapAnDIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.dapAnD);

        int dapAnDungIndex= cursor.getColumnIndex(LearnREContract.CauHoiEntry.dapAnDung);

        CauHoi cauHoi= new CauHoi();
        cauHoi.setNdCauHoi(cursor.getString(ndCauHoiIndex));
        cauHoi.setDapAnA(cursor.getString(dapAnAIndex));
        cauHoi.setDapAnB(cursor.getString(dapAnBIndex));
        cauHoi.setDapAnC(cursor.getString(dapAnCIndex));
        cauHoi.setDapAnD(cursor.getString(dapAnDIndex));
        cauHoi.setDapAnDung(cursor.getString(dapAnDungIndex));

        return cauHoi;
    }
}
