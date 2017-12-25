package com.hexiang.photoalbumdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.gengqiquan.adapter.adapter.SBAdapter;
import com.gengqiquan.adapter.interfaces.Converter;
import com.gengqiquan.adapter.interfaces.Holder;
import com.xhe.photoalbum.PhotoAlbum;
import com.xhe.photoalbum.data.ThemeData;
import com.xhe.photoalbum.utils.ImageDisplay;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();
    private SBAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThemeData.init(new ThemeData.ThemeBuilder()
                .spanCount(3)
                .titleBarColor(Color.parseColor("#009def"))
                .titleTextColor(Color.WHITE)
                .backgroundColor(Color.WHITE)
                .checkBoxDrawable(R.drawable.checkbox_style)
                .statusBarColor(getResources().getColor(R.color.main_color))
                .build());
        ImageDisplay.setDisplayer(new ImageDisplay.ImageDisplayer() {
            @Override
            public void display(@NonNull String url, @NonNull final ImageView imageView, final ImageDisplay.Loader loader) {
                Glide.with(MainActivity.this).load(url).centerCrop().into(imageView);
            }

            @Override
            public void pauseRequests(@NonNull Context contexts) {

            }

            @Override
            public void resumeRequests(@NonNull Context context) {

            }
        });
        findViewById(R.id.tv_start_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new PhotoAlbum(MainActivity.this)
                        .addRemovePaths(list)
                        .setShowCamera(true)
                        .setLimitCount(3)
                        .getAlbumIntent(), 1000);
            }
        });

        GridView gridView = (GridView) findViewById(R.id.gridview);
        adapter = new SBAdapter<String>(this)
                .layout(R.layout.item_img)
                .list(list)
                .bindViewData(new Converter<String>() {
                    @Override
                    public void convert(Holder holder, String item) {
                        ImageView imageView = holder.getView(R.id.imageview);
                        ImageDisplay.load(item, imageView);
                    }
                });
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            List<String> paths = PhotoAlbum.parseResult(data);
            list.clear();
            list.addAll(paths);
            adapter.notifyDataChanged();
            Log.d("PhotoAlbum", list.toString());
        }
    }
}
