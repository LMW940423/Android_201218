package com.android.database01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MemberInfo extends SQLiteOpenHelper {

    final static String TAG = "MemberInfo";

    public MemberInfo(@Nullable Context context) { // Nullable = 꼭 써도 되고 안 써도 된다.
        super(context, "MemberInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate");
        String query = "CREATE TABLE member(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, userid TEXT, passward INTEGER);";
        db.execSQL(query); // 쿼리문에 따른 테이블 생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "onUpgrade");
        String query = "DROP TABLE IF EXISTS member;";
        db.execSQL(query);
        onCreate(db); // 드랍한 다음 다시 만든다.
    }
}
