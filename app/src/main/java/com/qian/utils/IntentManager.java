package com.qian.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Pair;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.qian.R;
import com.qian.activity.MovieDetailActivity;
import com.qian.bean.kaiyan.ItemList;

public class IntentManager {

    public static void flyToMovieDetail(final Activity context,
                                        final ItemList item, final View view) {
        Glide.with(context).load(item.data.cover.detail)
                .listener(new RequestListener<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("item", item);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                context,
                                Pair.create(view, context.getString(R.string.transition_shot)),
                                Pair.create(view, context.getString(R.string.transition_shot_background))
                        );
                        context.startActivity(intent, options.toBundle());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Intent intent = new Intent(context, MovieDetailActivity.class);
                        intent.putExtra("item", item);
                        context.startActivity(intent);
                        return false;
                    }
                });
    }
}
