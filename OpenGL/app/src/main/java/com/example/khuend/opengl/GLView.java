package com.example.khuend.opengl;

import android.opengl.GLSurfaceView;
import android.content.Context;

/**
 * Created by KhueND on 4/6/2017.
 */

public class GLView extends GLSurfaceView {
    private final GLRenderer renderer;
    GLView(Context context){
        super(context);
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        renderer = new GLRenderer(context);
        setRenderer(renderer);
    }
}
