package com.vna.change.fragment;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.vna.change.activity.MainActivity;
import com.vna.change.R;

import java.util.ArrayList;

public class ViewPagerFragment extends PagerAdapter {

    Context context;
    ArrayList<String> arrayList;
    ImageView ivHinhAnh, ivCamera, ivFolder;
    public static int TAKE_PICTURE = 123;

    public ViewPagerFragment(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_viewpager, container, false);

        assert view != null;
        ivHinhAnh = (ImageView) view.findViewById(R.id.ivHinhAnh);
        ivCamera = (ImageView) view.findViewById(R.id.ivCamera);
        ivFolder = (ImageView) view.findViewById(R.id.ivFolder);


        //ivHinhAnh.setImageResource(Integer.parseInt(arrayList.get(position)));
        Glide.with(context).load(arrayList.get(position)).into(ivHinhAnh);


        container.addView(view, 0);
        xuLy(view);

        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public void xuLy(final View view){
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Chức năng đang được nâng cấp xin lỗi vì sự bất tiện này", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //getActivity().startActivityForResult(intent, TAKE_PICTURE);

            }
        });
        ivFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.lnTolbar.setVisibility(View.VISIBLE);
                MainActivity.fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
                MainActivity.fragmentTransaction.replace(R.id.flHome, new FolderFragment());
                MainActivity.fragmentTransaction.addToBackStack("Folder");
                MainActivity.fragmentTransaction.commit();

            }
        });
    }
}
