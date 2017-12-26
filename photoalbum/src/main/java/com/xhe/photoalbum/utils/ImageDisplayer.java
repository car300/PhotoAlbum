package com.xhe.photoalbum.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import static android.graphics.BitmapFactory.decodeStream;


/**
 * Created by xhe on 2016/7/13.
 */
@TargetApi(10)
public class ImageDisplayer {
    public static Displayer getDisplayer() {
        return displayer;
    }

    public static void setDisplayer(Displayer displayer) {
        ImageDisplayer.displayer = displayer;
    }

    private static Displayer displayer;


    public static void load(@NonNull String url, @NonNull ImageView imageView) {
        displayer.display(url, imageView, new CompleteLoader() {
            @Override
            public void complete() {

            }
        });
    }

    public interface CompleteLoader {
        void complete();
    }

    public interface Displayer {
        void display(@NonNull String url, @NonNull final ImageView imageView, final CompleteLoader listener);

        void pauseRequests();

        void resumeRequests();
    }


}
