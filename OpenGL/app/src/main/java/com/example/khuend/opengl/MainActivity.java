package com.example.khuend.opengl;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    GLView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new GLView(this);
        setContentView(view);
    }
    @Override
    protected void onPause(){
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        view.onResume();
    }
}
