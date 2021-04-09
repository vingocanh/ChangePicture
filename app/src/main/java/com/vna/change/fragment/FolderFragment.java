package com.vna.change.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.vna.change.activity.MainActivity;
import com.vna.change.R;
import com.vna.change.retrofit.API;
import com.vna.change.retrofit.model.DataReponcesHinhAnh;
import com.vna.change.retrofit.RetrofitDuLieu;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

public class FolderFragment extends Fragment {

    TextView tvNature, tvLove;
    FrameLayout flFolder;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImageView ivHinhAnh;
    //public  static LinearLayout lnFolder;
    API api;
    ArrayList<String> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_folder, container, false);
        init(view);
        xuLy();
        return view;
    }

    public void init(View view){

//        ivHinhAnh = (ImageView) view.findViewById(R.id.ivHinhAnh);
        tvNature = view.findViewById(R.id.tvNature);
        tvLove = view.findViewById(R.id.tvAnime);
//        flFolder = view.findViewById(R.id.flFolder);
        //lnFolder = view.findViewById(R.id.lnFolder);
//        Glide.with(context).load(arrayList.get(position)).into(ivHinhAnh);

    }

    public void xuLy(){

        tvLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvLove.setTypeface(Typeface.DEFAULT_BOLD);
                tvNature.setTypeface(Typeface.DEFAULT);

                arrayList.clear();
                api = RetrofitDuLieu.getInstancHinhAnh().create(API.class);
                Disposable disposable = api.readPicture().subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Consumer<DataReponcesHinhAnh>() {
                                                    @Override
                                                    public void accept(DataReponcesHinhAnh dataReponcesAnimal) throws Exception {
//                                                        System.out.println(dataReponcesAnimal.toString());
                                                        for(int i =0 ; i< 15; i++){
                                                            arrayList.add(dataReponcesAnimal.getImages().get(i).getSource().get(5).getUrl());
                                                               // Log.d("TAG", "accept: "+arrayList.toString());
                                                        }
                                                        MainActivity.fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                                                        MainActivity.fragmentTransaction.replace(R.id.flFolder, new NatureFragment(arrayList));
                                                        MainActivity.fragmentTransaction.commit();

                                                    }
                                                }, new Consumer<Throwable>() {
                                                    @Override
                                                    public void accept(Throwable throwable) throws Exception {
                                                        throwable.printStackTrace();
                                                    }
                                                });

                compositeDisposable.add(disposable);
            }
        });
        tvNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvNature.setTypeface(Typeface.DEFAULT_BOLD);
                tvLove.setTypeface(Typeface.DEFAULT);

                arrayList.clear();
                api = RetrofitDuLieu.getInstancHinhAnh().create(API.class);
                Disposable disposable = api.readNature().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DataReponcesHinhAnh>() {
                            @Override
                            public void accept(DataReponcesHinhAnh dataReponceAnimal) throws Exception {
                                for(int i =0 ; i< 15; i++){
                                    arrayList.add(dataReponceAnimal.getImages().get(i).getSource().get(5).getUrl());
                                    Log.d("TAG", "accept: "+arrayList.toString());
                                }
                                MainActivity.fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                                MainActivity.fragmentTransaction.replace(R.id.flFolder, new LoveFragment(arrayList));
                                MainActivity.fragmentTransaction.commit();

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });

                compositeDisposable.add(disposable);
            }
        });
    }
}
