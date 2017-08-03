package org.fossasia.openevent.app.common.app.binding;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.fossasia.openevent.app.R;
import org.fossasia.openevent.app.common.utils.ui.CircleTransform;
import org.fossasia.openevent.app.module.main.MainActivity;

public class BindingAdapters {

    @BindingConversion
    public static String longToStr(Long value) {
        return value != null ? String.valueOf(value) : "";
    }

    @BindingAdapter(value = { "imageUrl", "placeholder" }, requireAll = false)
    public static void bindDefaultImage(ImageView imageView, String url, Drawable drawable) {
        if(TextUtils.isEmpty(url)) {
            if (drawable != null)
                imageView.setImageDrawable(drawable);
            return;
        }

        RequestCreator requestCreator = Picasso.with().load(Uri.parse(url));

        if (drawable != null) {
            requestCreator
                .placeholder(drawable)
                .error(drawable);
        }

        requestCreator
            .tag(MainActivity.class)
            .into(imageView);
    }

    @BindingAdapter("circleImageUrl")
    public static void bindCircularImage(ImageView imageView, String url) {
        if(TextUtils.isEmpty(url)) {
            imageView.setImageResource(R.drawable.ic_photo_shutter);
            return;
        }

        Picasso.with()
            .load(Uri.parse(url))
            .error(R.drawable.ic_photo_shutter)
            .placeholder(R.drawable.ic_photo_shutter)
            .transform(new CircleTransform())
            .tag(MainActivity.class)
            .into(imageView);
    }

    @BindingAdapter("tint")
    public static void setTintColor(ImageView imageView, @ColorInt int color) {
        DrawableCompat.setTint(imageView.getDrawable(), color);
    }

    @BindingAdapter("backgroundTint")
    public static void setBackgroundTintColor(View view, @ColorInt int color) {
        ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(color));
    }

    @BindingAdapter("srcCompat")
    public static void bindSrcCompat(FloatingActionButton fab, Drawable drawable){
        fab.setImageDrawable(drawable);
    }

    @BindingAdapter("srcCompat")
    public static void bindSrcImageView(ImageView imageView, Drawable drawable){
        imageView.setImageDrawable(drawable);
    }

    @BindingAdapter("progress_with_animation")
    public static void bindCircularProgress(CircularProgressBar circularProgressBar, int progress) {
        circularProgressBar.setProgressWithAnimation(progress, 500);
    }

    @BindingAdapter("circular_progress_color")
    public static void bindCircularProgressColor(CircularProgressBar circularProgressBar, String colorName) {
        Context context = circularProgressBar.getContext();
        Resources resources = circularProgressBar.getResources();

        int color = ContextCompat.getColor(context, resources.getIdentifier(colorName + "_500", "color", context.getPackageName()));
        int bgColor = ContextCompat.getColor(context, resources.getIdentifier(colorName + "_100", "color", context.getPackageName()));

        circularProgressBar.setColor(color);
        circularProgressBar.setBackgroundColor(bgColor);
    }

}