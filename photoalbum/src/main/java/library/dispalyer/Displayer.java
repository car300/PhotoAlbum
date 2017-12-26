package library.dispalyer;

import android.graphics.Bitmap;

import library.data.ImageInfo;

/**
 * Created by Administrator on 2016/11/14.
 */

public interface Displayer {
    void display(Bitmap bm, ImageInfo img);
}
