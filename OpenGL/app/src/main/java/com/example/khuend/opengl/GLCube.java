package com.example.khuend.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by KhueND on 4/6/2017.
 */

public class GLCube {
    private final IntBuffer mVertexBuffer;
    private final IntBuffer mTextureBuffer;
    public GLCube(){
        int one = 65536;
        int half = one/2;
        int vertices[] = {
                //Front
                -half, -half, half, half, -half, half,
                -half, half, half, half, half, half,
                //Back
                -half, -half, -half, -half, half, -half,
                half, -half, -half, half, half, -half,
                //LEFT
                -half, -half, half, -half, half, half,
                -half, -half, -half, -half, half, -half,
                //Right
                half, -half, -half, half, half, -half,
                half, -half, half, half, half, half,
                //TOP
                -half, half, half, half, half, half,
                -half, half, -half, half, half, -half,
                //Bottom
                -half, -half, half, -half, -half, -half,
                half, -half, half, half, -half, -half,
        };


        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

    int texCoords[] = {
// FRONT
            0, one, one, one, 0, 0, one, 0,
// BACK
            one, one, one, 0, 0, one, 0, 0,
// LEFT
            one, one, one, 0, 0, one, 0, 0,
// RIGHT
            one, one, one, 0, 0, one, 0, 0,
// TOP
            one, 0, 0, 0, one, one, 0, one,
// BOTTOM
            0, 0, 0, one, one, 0, one, one, };
    // ...
    ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
        tbb.order(ByteOrder.nativeOrder());
    mTextureBuffer = tbb.asIntBuffer();
        mTextureBuffer.put(texCoords);
        mTextureBuffer.position(0);
   }

    static void loadTexture(GL10 gl, Context context, int resource) {
        Bitmap bmp = BitmapFactory.decodeResource(
                context.getResources(), resource);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterx(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        bmp.recycle();
    }
    public void draw(GL10 gl) {
        gl.glEnable(GL10.GL_TEXTURE_2D); // workaround bug 3623
        gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer);
        gl.glVertexPointer(3, GL10.GL_FIXED, 0, mVertexBuffer);

        gl.glColor4f(1, 1, 1, 1);
       gl.glNormal3f(0, 0, 1);
       gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
       gl.glNormal3f(0, 0, -1);
       gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);

        gl.glColor4f(1, 1, 1, 1);
       gl.glNormal3f(-1, 0, 0);
       gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
       gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);

        gl.glColor4f(1, 1, 1, 1);
       gl.glNormal3f(0, 1, 0);
       gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        gl.glNormal3f(0, -1, 0);
       gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
    }
}
