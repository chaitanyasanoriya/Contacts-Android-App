package com.lambton.projects.contact_chaitanya_c0777253_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

public class Utils
{
    public static Bitmap createImageRounded(Context context, int width, int height, String name)
    {
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paintCicle = new Paint();
        Paint paintText = new Paint();

        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        float density = context.getResources().getDisplayMetrics().density;
        float roundPx = 100*density;

        paintCicle.setColor(context.getColor(R.color.colorPrimaryDark));
        paintCicle.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);

// Set Border For Circle
        paintCicle.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paintCicle);

        paintText.setColor(Color.WHITE);
        paintText.setTextSize(72);

        canvas.drawText(name, 75 - 23, 75 + 25, paintText);

        return output;
    }
}
