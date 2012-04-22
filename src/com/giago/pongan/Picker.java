package com.giago.pongan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Picker extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker);
        
        findViewById(R.id.blue).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Pongan.startBlue(Picker.this);
            }
        });
        
        findViewById(R.id.red).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Pongan.startRed(Picker.this);
            }
        });
    }

}
