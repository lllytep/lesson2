package ru.ifmo.md.lesson2;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity {
//    Image image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Image image = new Image(this);
        Canvas canvas = new Canvas();
        image.onDraw(canvas);
/*        image = new Image(this);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.onClick();
            }
        });
        setContentView(image);
*/
    }

    @Override
    protected void onResume(){
        super.onResume();
//        image.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
//        image.pause();
    }
}
