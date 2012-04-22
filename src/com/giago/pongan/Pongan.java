package com.giago.pongan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;

public class Pongan extends FragmentActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Void> {

    private static final String HOST = "http://pongan-experiment.appspot.com/move";
    private static final String RED_DOWN = HOST + "?code=1";
    private static final String BLUE_DOWN = HOST + "?code=3";
    private static final String RED_UP = HOST + "?code=0";
    private static final String BLUE_UP = HOST + "?code=2";
    private static final String COLOR = "color";
    private static final int RED = 1;
    private static final int BLUE = 2;

    private static int color = 0;

    public static void startRed(Context context) {
        Intent i = new Intent(context, Pongan.class);
        i.putExtra(COLOR, RED);
        context.startActivity(i);
    }

    public static void startBlue(Context context) {
        Intent i = new Intent(context, Pongan.class);
        i.putExtra(COLOR, BLUE);
        context.startActivity(i);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("color", color);
        super.onSaveInstanceState(outState);
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        color = savedInstanceState.getInt("color");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pongan);
        setColor();
        if(color == RED) {
            findViewById(R.id.up).setBackgroundResource(R.drawable.red_arrowup);
            findViewById(R.id.down).setBackgroundResource(R.drawable.red_arrowdown);
        }
        findViewById(R.id.up).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color == RED) {
                    callLoader(RED_UP);
                } else if (color == BLUE) {
                    callLoader(BLUE_UP);
                }
            }
        });

        findViewById(R.id.down).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color == RED) {
                    callLoader(RED_DOWN);
                } else if (color == BLUE) {
                    callLoader(BLUE_DOWN);
                }
            }
        });
    }

    private void callLoader(String url) {
        Bundle b = new Bundle();
        b.putString("url", url);
        getSupportLoaderManager().initLoader(0, b, this);
    }

    private void setColor() {
        Intent i = getIntent();
        color = i.getIntExtra(COLOR, 0);
    }

    @Override
    public Loader<Void> onCreateLoader(int arg, Bundle b) {
        String url = b.getString("url");
        return new MoveLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<Void> arg0, Void arg1) {
        getSupportLoaderManager().destroyLoader(0);

    }

    @Override
    public void onLoaderReset(Loader<Void> arg0) {
        // TODO Auto-generated method stub

    }

}