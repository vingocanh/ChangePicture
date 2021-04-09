package com.vna.change.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vna.change.adapter.HinhAnhAdapter;
import com.vna.change.R;

import java.util.ArrayList;

public class LoveFragment extends Fragment {

    RecyclerView rvHinhAnh;
    ArrayList<String> arrayList = new ArrayList<>();
    HinhAnhAdapter hinhAnhAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_love, container, false);
        init(view);
        //arrayList.clear();
        xuLy();
        return view;
    }


    public LoveFragment(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    private void init(View view){
        rvHinhAnh = view.findViewById(R.id.rvHinhAnhAnimal);
        hinhAnhAdapter = new HinhAnhAdapter(getContext(), arrayList);
    }

    private  void xuLy(){
        rvHinhAnh.setAdapter(hinhAnhAdapter);
        rvHinhAnh.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}
