package com.example.wangxin.mycontentprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedActivity extends AppCompatActivity {

    public static final String PREFS_READ = "PREFS_READ";
    public static final String KEY_READ = "KEY_READ";
    private SharedPreferences sharedPreferences;
    public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;


    EditText etShare;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);
        etShare = (EditText) findViewById(R.id.et_share);
        btnShare = (Button) findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences(PREFS_READ, MODE);
                SharedPreferences.Editor prefsReadEditor = sharedPreferences.edit();
                prefsReadEditor.putString(KEY_READ, String.valueOf(etShare.getText()));
                prefsReadEditor.commit();
                Toast.makeText(getApplicationContext(),sharedPreferences.getString(KEY_READ,"我错了"),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
