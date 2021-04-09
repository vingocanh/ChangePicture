package com.vna.change.adapter;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.vna.change.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;


public class HinhAnhAdapter extends RecyclerView.Adapter<HinhAnhAdapter.ViewHodler> {
    Context context;
    ArrayList<String> arrayList;

    public HinhAnhAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_imavege, parent, false);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(final HinhAnhAdapter.ViewHodler holder, final int position) {



        final String url = arrayList.get(position);
        Picasso.with(context).load(url).into(holder.ivPicter);
        holder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLy(holder, position, url);
            }
        });
        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.ivLike.setImageResource(R.drawable.ic_like_244);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        ImageView ivPicter, ivMore, ivLike;
        public ViewHodler(View itemView) {
            super(itemView);
            ivPicter = itemView.findViewById(R.id.ivPicter);
            ivMore = itemView.findViewById(R.id.ivMore);
            ivLike = itemView.findViewById(R.id.ivLike);

        }
    }

    public void xuLy(final ViewHodler hodler, int position, final String url){
        PopupMenu popupMenu = new PopupMenu(context, hodler.ivMore);

        popupMenu.getMenuInflater().inflate(R.menu.poupmenu_content, popupMenu.getMenu());
        final WallpaperManager manager = WallpaperManager.getInstance(context.getApplicationContext());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    //đổi hình nền điện thoại
                    case R.id.itemChangeHNen:
                        Glide.with(context)
                                .asBitmap()
                                .load(url.toString())
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource,Transition<? super Bitmap> transition) {
                                        try {
                                            manager.setBitmap(resource);
                                            Toast.makeText(context, "Wallpaper successfully!", Toast.LENGTH_SHORT).show();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, "Wallpaper false!", Toast.LENGTH_SHORT).show();
                                        }


//                                        Cắt hình nền ở giữa
//                                        try {
//                                            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
//
//                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                                int wallpaperHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
//                                                int wallpaperWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//
//                                                Point start = new Point(0, 0);
//                                                Point end = new Point(resource.getWidth(), resource.getHeight());
//
//                                                if (resource.getWidth() > wallpaperWidth) {
//                                                    start.x = (resource.getWidth() - wallpaperWidth) / 2;
//                                                    end.x = start.x + wallpaperWidth;
//                                                }
//
//                                                if (resource.getHeight() > wallpaperHeight) {
//                                                    start.y = (resource.getHeight() - wallpaperHeight) / 2;
//                                                    end.y = start.y + wallpaperHeight;
//                                                }
//
//                                                wallpaperManager.setBitmap(resource, new Rect(start.x, start.y, end.x, end.y), false);
//                                            } else {
//                                                wallpaperManager.setBitmap(resource);
//                                            }
//                                        } catch (IOException e) {
//                                            e.printStackTrace();
//                                        }

                                    }
                                });
                        break;


                        //đổi hình nền khóa
                    case R.id.itemKhoa:
                        Glide.with(context)
                                .asBitmap()
                                .load(url)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                                        try {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                                manager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
                                                Toast.makeText(context, "Wallpaper lock screen successfully!", Toast.LENGTH_SHORT).show();
                                            }

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, "Wallpaper lock screen false!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        break;

                    case R.id.itemChangeDBa:
                        break;

                    default:
                        break;
                }

                return false;
            }
        });

        popupMenu.show();
    }
}
