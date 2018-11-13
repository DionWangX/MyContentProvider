package com.example.wangxin.mycontentprovider;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wangxin.mycontentprovider.provider.MyContentProvider;

public class MainActivity extends AppCompatActivity {

    private Button bt_insert;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et);
        bt_insert = findViewById(R.id.bt_insert);

        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将编辑框内容保存到变量txt中
                String txt = editText.getText().toString();
                //实例化ContentValues，然后添加数据
                ContentValues values = new ContentValues();
                Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_LONG).show();

                values.put("name",txt);
                //将ContentValues传递到数据库中
                getContentResolver().insert(MyContentProvider.URL,values);
            }
        });
    }
}
