package ru.ifmo.md.lesson2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Anton on 24.09.2014.
 */

public class Image extends SurfaceView implements Runnable {
    SurfaceHolder holder;
    Thread thread = null;
    volatile boolean running = false;
    Bitmap bitmapOrigin;
    Bitmap bitmap;
    int width;
    int height;
    int imageH;
    int imageW;
    Matrix matrix = null;
    volatile  boolean click = false;

    public Image(Context context){
        super(context);
        holder = getHolder();
        bitmapOrigin = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        imageH = bitmapOrigin.getHeight();
        imageW = bitmapOrigin.getWidth();
        matrix = new Matrix();
        matrix.postScale(0.73f, 0.73f);
        bitmap = Bitmap.createBitmap(bitmapOrigin, 0, 0, imageH, imageW, matrix, false);
    }

    public void resume(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause(){
        running = false;
        try{
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClick(){
        if(click == false) {
            Log.i("CLICK", "true");
            click = true;
        } else {
            Log.i("CLICK", "false");
            click = false;
        }
    }

    @Override
    public void run() {
        while(running){
            if(holder.getSurface().isValid()){
                Canvas canvas = holder.lockCanvas();
                onDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
                try{
                    thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH){
        width = w;
        height = h;
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.scale(5, 5);
    }
}
