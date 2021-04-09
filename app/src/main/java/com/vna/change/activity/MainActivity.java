package com.vna.change.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.vna.change.fragment.SeachFragment;
import com.vna.change.fragment.ViewPagerFragment;
import com.vna.change.R;
import com.vna.change.notificationn.CreateNotification;
import com.vna.change.retrofit.API;
import com.vna.change.retrofit.model.DataReponcesHinhAnh;
import com.vna.change.retrofit.RetrofitDuLieu;
import com.google.android.material.navigation.NavigationView;
import com.vna.change.service.PictureService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayListSeach = new ArrayList<>();
    public static ArrayList<String> arrayListSevice = new ArrayList<>();
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    ViewPager vpHome;
    public static FragmentManager fragmentManager;
    public static FragmentTransaction fragmentTransaction;
    FrameLayout flHome;
    Toolbar tbHome;
    ImageView ivMenu, ivCaiDat, ivSeach;
    public static LinearLayout lnTolbar;
    DrawerLayout dlHome;
    NavigationView navigationView;
    EditText etSeach;
    public static String CHANEL_ID = "change";
    public static API api = RetrofitDuLieu.getInstancHinhAnh().create(API.class);;
    public static CompositeDisposable compositeDisposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateNotification.create(this);
        init();
        loadAnh();
        xuLy();
    }

    private void init(){
        vpHome = findViewById(R.id.vpHome);
        flHome = findViewById(R.id.flHome);
        ivMenu = findViewById(R.id.ivMenu);
        dlHome = findViewById(R.id.dlHome);
        ivCaiDat = findViewById(R.id.ivCaiDat);
        navigationView = findViewById(R.id.navi);
        ivSeach = findViewById(R.id.ivSeach);
        etSeach = findViewById(R.id.etSeach);
        tbHome = (Toolbar) findViewById(R.id.tbHomee);
        lnTolbar = findViewById(R.id.tollbar);
        setSupportActionBar(tbHome);

        fragmentManager = getSupportFragmentManager();

    }


    public void xuLy(){
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlHome.openDrawer(GravityCompat.START);
            }
        });

        ivCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               popUp();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.itemHome:

                        fragmentTransaction = fragmentManager.beginTransaction();
                        SeachFragment seach = (SeachFragment) getSupportFragmentManager().findFragmentByTag("seach");
                        if(seach != null){
                            fragmentTransaction.remove(seach);
                            fragmentTransaction.commit();
                            dlHome.closeDrawer(GravityCompat.START);
                        }else {
                            dlHome.closeDrawer(GravityCompat.START);
                        }

                        break;

                    case R.id.itemYeuThich:
                        break;

                    case R.id.itemCaiDai:
                        //popUp();
                        break;

                    case R.id.itemCSe:
                        break;

                    case R.id.itemThongtTin:
                        break;

                    case R.id.itemDangXuat:
                        break;
                }

                return false;
            }
        });

        etSeach.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            lnTolbar.setVisibility(View.VISIBLE);
                            String dl = etSeach.getText().toString();
                            arrayListSeach.clear();
                            Disposable disposable = api.read(dl).subscribeOn(Schedulers.io())
                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                .subscribe(new Consumer<DataReponcesHinhAnh>() {
                                                                    @Override
                                                                    public void accept(DataReponcesHinhAnh dataReponcesHinhAnh) throws Exception {
                                                                        for (int i = 0; i < 15; i++){
                                                                            arrayListSeach.add(dataReponcesHinhAnh.getImages().get(i).getSource().get(5).getUrl());
                                                                        }
                                                                        fragmentTransaction = fragmentManager.beginTransaction();
                                                                        fragmentTransaction.replace(R.id.flHome, new SeachFragment(arrayListSeach), "seach");
                                                                        //fragmentTransaction.addToBackStack("seach");
                                                                        fragmentTransaction.commit();
                                                                        etSeach.setText("");

                                                                    }
                                                                }, new Consumer<Throwable>() {
                                                                    @Override
                                                                    public void accept(Throwable throwable) throws Exception {
                                                                        Log.d("TAG", "ERROR");
                                                                        throwable.printStackTrace();
                                                                    }
                                                                });

                            compositeDisposable.add(disposable);
                            return true;

                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    public void popUp(){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, ivCaiDat);
        popupMenu.getMenuInflater().inflate(R.menu.conten_setting, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.itemLike:
                        break;

                    case R.id.itemsetTGian:
                        xuLyDialog();
                        readData();
                        break;
                }
                return false;
            }
        });


        popupMenu.show();
    }

    private void xuLyDialog(){
        Button btXacNhan, btHuy;
        final EditText etTime;
        Spinner spTGian;
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Cài đặt");
        dialog.setContentView(R.layout.dialog_time);


        btXacNhan = dialog.findViewById(R.id.btXacNhan);
        btHuy = dialog.findViewById(R.id.btHuy);
        etTime = dialog.findViewById(R.id.etThoiGian);
        spTGian = dialog.findViewById(R.id.spTGian);



        btXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PictureService.class);
                intent.putExtra("duLieu", "Change Wallpager");
                final int tGian = Integer.parseInt(etTime.getText().toString());
                intent.putExtra("duLieu", tGian);
                ContextCompat.startForegroundService(MainActivity.this, intent);

                dialog.dismiss();
            }
        });

        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void addSpinner(Spinner spinner){
        ArrayList<String> arrayList = new ArrayList<>();
    }

    private void readData(){
                 compositeDisposable.add(MainActivity.api.readTime()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DataReponcesHinhAnh>() {
                            @Override
                            public void accept(DataReponcesHinhAnh dataReponcesHinhAnh) throws Exception {

                                for (int i = 0; i < 15; i++){
                                    arrayListSevice.add(dataReponcesHinhAnh.getImages().get(i).getSource().get(5).getUrl());

                                }
                                Log.d("SERVICE", "accept: "+arrayListSevice);


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));

    }

//    public void change(){
//        Intent intent = new Intent(this, PictureService.class);
//        intent.putExtra("duLieu", "Change Wallpaper");
//
//        ContextCompat.startForegroundService(this, intent);
//    }

    private void loadAnh(){

        arrayList.add("https://live.staticflickr.com/65535/50057995368_e8147ae3ad_h.jpg");
        arrayList.add("https://live.staticflickr.com/65535/50427281952_64412548a4_h.jpg");
        arrayList.add("https://images.wallpaperscraft.com/image/mom_love_heart_129072_1350x2400.jpg");
        arrayList.add("https://images.wallpaperscraft.com/image/wolf_muzzle_eyes_predator_59852_1280x960.jpg");
        arrayList.add("https://live.staticflickr.com/65535/49919510298_6ee7eaddf0_h.jpg");
        arrayList.add("https://images.wallpaperscraft.com/image/lion_lion_cub_family_cub_caring_baby_sunshine_40132_1024x768.jpg");
        arrayList.add("https://live.staticflickr.com/65535/50179091411_b59f7f9300_h.jpg");
        arrayList.add("https://images.wallpaperscraft.com/image/silhouettes_couple_love_140301_1350x2400.jpg");
        arrayList.add("https://images.wallpaperscraft.com/image/wolf_howl_predator_122278_1400x1050.jpg");



        ViewPagerFragment adapter = new ViewPagerFragment(this, arrayList);
        vpHome.setAdapter(adapter);;
        NUM_PAGES = arrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                vpHome.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 7000, 7000);

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }



}