package com.android.database01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "Status";
    Button insertBtn, updateBtn, deleteBtn, selectBtn;
    EditText tvResult;
    MemberInfo memberInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memberInfo = new MemberInfo(MainActivity.this);
        tvResult = findViewById(R.id.tv_result);

        findViewById(R.id.btn_Insert).setOnClickListener(onClickListener);
        findViewById(R.id.btn_Update).setOnClickListener(onClickListener);
        findViewById(R.id.btn_Delete).setOnClickListener(onClickListener);
        findViewById(R.id.btn_Select).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        SQLiteDatabase DB; // 선언해줘야 db를 사용할 수 있다.

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_Insert:
                    try {
                        DB = memberInfo.getWritableDatabase();
                        String query = "INSERT INTO member (username, userid, passward) VALUES ('이민우', 'LMW', '1234');";
                        DB.execSQL(query);

                        memberInfo.close();

                        Toast.makeText(MainActivity.this, "Insert OK!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Insert Error", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btn_Update:
                    try {
                        DB = memberInfo.getWritableDatabase();
                        String query = "UPDATE member SET username = '확인' WHERE userid = 'LMW' and id = 1;";
                        DB.execSQL(query);

                        memberInfo.close();

                        Toast.makeText(MainActivity.this, "Update OK!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Update Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_Select:
                    try {
                        DB = memberInfo.getReadableDatabase();
                        String query = "SELECT username, userid, passward FROM member WHERE id = 2;";
                        Cursor cursor = DB.rawQuery(query, null);
                        StringBuffer stringBuffer = new StringBuffer();


                        while (cursor.moveToNext()){
                            Log.v("TAGTAG", "1-----------------");
                            String username = cursor.getString(0);
                            Log.v("TAGTAG", "2-----------------");
                            String userid = cursor.getString(1);
                            Log.v("TAGTAG", "3-----------------");
                            int passward = cursor.getInt(2);
                            Log.v("TAGTAG", "4-----------------");
                            stringBuffer.append("username : " + username + ", userid : " + userid + ", passward : " + passward);
                            Log.v("TAGTAG", "5-----------------");
                        }
                        tvResult.setText(stringBuffer.toString());
                        Log.v("TAGTAG", "6-----------------");
                        cursor.close();
                        memberInfo.close();

                        Toast.makeText(MainActivity.this, "Select OK!", Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Select Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                    case R.id.btn_Delete:
                        try {
                            DB = memberInfo.getReadableDatabase();
                            String query = "DELETE FROM member WHERE id = 2;";
                            DB.execSQL(query);

                            memberInfo.close();

                            Toast.makeText(MainActivity.this, "Delete OK!", Toast.LENGTH_SHORT).show();

                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Delete Error", Toast.LENGTH_SHORT).show();
                        }
                        break;

            }

        }
    } ;
}//--------------------