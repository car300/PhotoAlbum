package com.xhe.photoalbum.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.graphics.BitmapFactory.decodeStream;


/**
 * Created by xhe on 2016/7/13.
 */
@TargetApi(10)
public class ImageDisplay {
    public static ImageDisplayer getDisplayer() {
        return displayer;
    }

    public static void setDisplayer(ImageDisplayer displayer) {
        ImageDisplay.displayer = displayer;
    }

    private static ImageDisplayer displayer;


    public static void load(@NonNull String url, @NonNull ImageView imageView) {
        load(url, imageView,null);
    }


    public static void load(@NonNull String url, @NonNull ImageView imageView, Loader loader) {
        displayer.display(url, imageView, loader);
    }

    public interface ImageDisplayer {
        void display(@NonNull String url, @NonNull final ImageView imageView, Loader loader);

        void pauseRequests(@NonNull Context contexts);

        void resumeRequests(@NonNull Context context);
    }

    public interface Loader {

        void finish(@NonNull Drawable drawable);

    }

    /**
     * 根据图片需要显示的宽和高对图片进行压缩
     *
     * @param path
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        // 获得图片的宽和高，并不把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        options.inPurgeable = true;
        BitmapFactory.decodeFile(path, options);

        int size = 4;
        if (width > 1 && height > 1) {
            size = caculateInSampleSize(options, width, height);
        }
        options.inSampleSize = size;

        // 使用获得到的InSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap =BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
     *
     * @param options
     * @return
     */
    public static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                           int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1.0f / reqWidth);
            int heightRadio = Math.round(height * 1.0f / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);
        }

        return inSampleSize;
    }

}
