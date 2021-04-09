package com.vna.change.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.vna.change.R;
import com.vna.change.activity.MainActivity;
import com.vna.change.retrofit.model.DataReponcesHinhAnh;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;

public class PictureService extends Service {

    public PictureService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");\
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String duLieu = intent.getStringExtra("duLieu");

        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent1, 0);


        Notification notification = new NotificationCompat.Builder(this, MainActivity.CHANEL_ID)
                .setContentTitle("Notification")
                .setContentText(duLieu)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_picture)
                .build();

        startForeground(1, notification);
        ChangeWallpaper(MainActivity.arrayListSevice,0);


        return START_NOT_STICKY;
    }
    private  void ChangeWallpaper(final ArrayList<String> arrayList, int i){
        if(i>=arrayList.size() || i<0)
            i=0;
        final Handler handler = new Handler();
        final int finalI = i;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changePicture(arrayList.get(finalI));
                ChangeWallpaper(arrayList,finalI+1);
            }
        }, 15000);

    }
    private void changePicture(String link){

        final WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(link)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        try {
                            manager.setBitmap(resource);
                            Toast.makeText(getApplicationContext(), "Wallpaper successfully!", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Wallpaper false!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        Log.d("TAG", "onDestroy: ");
        super.onDestroy();

    }
}
