package com.atguigu.addsubview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AddSubView addsubview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addsubview = (AddSubView)findViewById(R.id.addsubview);


        addsubview.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void numberChange(int value) {
                Toast.makeText(MainActivity.this, "Number=="+value, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
