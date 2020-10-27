package com.xhe.photoalbum;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xhe.photoalbum.data.PhotoAlbumPicture;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by xhe on 2017/6/20.
 * 预览adapter
 */

public class PreviewAdapter extends PagerAdapter {

    private List<PhotoAlbumPicture> mAlbumImages;

    public PreviewAdapter(List<PhotoAlbumPicture> mAlbumImages) {
        this.mAlbumImages = mAlbumImages;
    }

    @Override
    public int getCount() {
        return mAlbumImages == null ? 0 : mAlbumImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = new PhotoView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(imageView);
        final String path = mAlbumImages.get(position).getPath();
        Glide.with(imageView)
                .load(path)
                .into(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));
    }
}