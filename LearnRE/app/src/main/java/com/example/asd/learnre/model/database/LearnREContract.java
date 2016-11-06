package com.example.asd.learnre.model.database;

import android.provider.BaseColumns;

/**
 * Created by asd on 9/17/2016.
 */

public class LearnREContract {

    public static final String BaiHoc = "BaiHoc";
    public static final String CauHoi = "CauHoi";

    public class BaiHocEntry implements BaseColumns {
        public static final String idBaiHoc = "idBaiHoc";
        public static final String chuDe = "chuDe";
        public static final String gioiThieu = "gioiThieu";
        public static final String baiDoc = "baiDoc";
    }

    public class CauHoiEntry implements BaseColumns {
        public static final String idCauHoi = "idCauHoi";
        public static final String ndCauHoi = "ndCauHoi";
        public static final String dapAnA = "dapAnA";
        public static final String dapAnB = "dapAnB";
        public static final String dapAnC = "dapAnC";
        public static final String dapAnD = "dapAnD";
        public static final String dapAnDung = "dapAnDung";

        public static final String idBaiHoc = "idBaiHoc";
    }

}
