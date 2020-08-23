package com.cookandroid.p9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    MyGraphicView graphicView;
    static float scaleX = 1, scaleY = 1;
    static float angle=0;
    static float color=1;
    static float satur=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        graphicView = new MyGraphicView(this);
        setContentView(graphicView);
        setTitle("미니 포토샵");
        registerForContextMenu(graphicView);

    }

    public void onCreateContextMenu (ContextMenu Menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(Menu, v, menuInfo);

        Menu.add(0, 1, 0, "확대");
        Menu.add(0, 2, 0, "축소");
        Menu.add(0, 3, 0, "회전");
        Menu.add(0, 4, 0, "밝게");
        Menu.add(0, 5, 0, "어둡게");
        Menu.add(0, 6, 0, "그레이영상");
    }

    public boolean onContextItemSelected(MenuItem item){
            switch (item.getItemId()){
                case 1:
                    scaleX = scaleX + 0.2f;
                    scaleY = scaleY + 0.2f;
                    graphicView.invalidate();
                    return true;
                case 2:
                    scaleX = scaleX - 0.2f;
                    scaleY = scaleY - 0.2f;
                    graphicView.invalidate();
                    return true;
                case 3:
                    angle = angle + 20;
                    graphicView.invalidate();
                    return true;
                case 4:
                    color = color + 0.2f;
                    graphicView.invalidate();
                    return true;
                case 5:
                    color = color - 0.2f;
                    graphicView.invalidate();
                    return true;
                case 6:
                    if(satur == 0) satur = 1;
                    else satur = 0;
                    graphicView.invalidate();
                    return true;
            }
            return super.onContextItemSelected(item);
    }


    private static class MyGraphicView extends View{
        public MyGraphicView(Context context){
            super(context);
        }
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);
            Paint paint = new Paint();
            float[] array = { color, 0, 0, 0, 0, 0, color, 0, 0, 0, 0, 0,
                    color, 0, 0, 0, 0, 0, 1, 0 };
            ColorMatrix cm = new ColorMatrix(array);
            if(satur == 0) cm.setSaturation(satur);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, paint);

            picture.recycle();
        }

    }
}
