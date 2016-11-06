package com.example.asd.learnre.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asd.learnre.model.obj.BaiHoc;

import java.util.ArrayList;

/**
 * Created by asd on 9/17/2016.
 */

public class BaiHocDB {

    private LearnREDatabase db;
    private SQLiteDatabase database;

    private static final String table= LearnREContract.BaiHoc;

    private static BaiHocDB instance;

    public static BaiHocDB getInstance(Context context) {
        if(instance==null) {
            instance=new BaiHocDB(context);
        }
        return instance;
    }


    public BaiHocDB(Context context) {
        db= LearnREDatabase.getInstance(context);
    }

    public Cursor getDanhSachBaiHoc() {
        database = db.getReadableDatabase();
        String columns[] = {LearnREContract.BaiHocEntry.idBaiHoc,
                LearnREContract.BaiHocEntry.chuDe,
                LearnREContract.BaiHocEntry.gioiThieu,
                LearnREContract.BaiHocEntry.baiDoc};

        Cursor cursor = database.query(table, columns, null, null, null, null,
                LearnREContract.BaiHocEntry.idBaiHoc + " ASC", null);
        return cursor;
    }

    public ArrayList<BaiHoc> getDanhBaiHoc() {
        Cursor cursor= getDanhSachBaiHoc();

        if(cursor.moveToFirst()) {
            ArrayList<BaiHoc> danhSachBaiHoc=new ArrayList<>();
            for(int i=0;i<cursor.getCount();i++) {
                // TODO: Lấy các cột trong db ra baiHoc
                BaiHoc baiHoc=new BaiHoc();

                int idBaiHocIndex= cursor.getColumnIndex(LearnREContract.BaiHocEntry.idBaiHoc);
                baiHoc.setIdBaiHoc(cursor.getInt(idBaiHocIndex));

                int chuDeIndex= cursor.getColumnIndex(LearnREContract.BaiHocEntry.chuDe);
                baiHoc.setChuDe(cursor.getString(chuDeIndex));

                int baiDocIndex= cursor.getColumnIndex(LearnREContract.BaiHocEntry.baiDoc);
                baiHoc.setBaiDoc(cursor.getString(baiDocIndex));

                danhSachBaiHoc.add(baiHoc);
                cursor.moveToNext();
            }
            cursor.close();
            database.close();
            return danhSachBaiHoc;
        }
        else {
            cursor.close();
            database.close();
            return null;
        }
    }
}
