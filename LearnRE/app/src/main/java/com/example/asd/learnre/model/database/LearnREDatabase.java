package com.example.asd.learnre.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by asd on 9/17/2016.
 */

public class LearnREDatabase extends SQLiteOpenHelper {

    private static final String name= "LearnRE.db";
    private static String DB_PATH;
    private Context context;

    private static LearnREDatabase instance;

    public static LearnREDatabase getInstance(Context context) {
        if(instance==null) {
            instance= new LearnREDatabase(context);
        }
        return instance;
    }

    public LearnREDatabase(Context context) {
        super(context, name, null, 3);
        if(Build.VERSION.SDK_INT>=17) {
            DB_PATH= context.getApplicationInfo().dataDir+ "/databases/";
        }
        else {
            DB_PATH="/data/data/"+ context.getPackageName()+"/databases/";
        }
        this.context=context;
        if(!checkDatabase())
            createDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!checkDatabase())
            createDatabase();
    }

    private boolean checkDatabase() {
        File f=new File(DB_PATH+name);
        return f.exists();
    }

    public void copyDatabase() throws IOException {
        InputStream inputStream= context.getAssets().open(name);
        String outFileName= DB_PATH+name;
        OutputStream outputStream=new FileOutputStream(outFileName);

        byte mBuffer[] = new byte[1024];
        int mLength;
        while ((mLength=inputStream.read(mBuffer))>0) {
            outputStream.write(mBuffer, 0, mLength);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void createDatabase() {
        if(!checkDatabase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
