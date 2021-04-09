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

public class SeachFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<String> arrayList = new ArrayList<>();
    HinhAnhAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seach, container, false);
        init(view);
        xuLy();
        return view;
    }

    public SeachFragment(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    private void init(View view){
        recyclerView = view.findViewById(R.id.rvSeach);
        adapter = new HinhAnhAdapter(getContext(), arrayList);
    }

    private void xuLy(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }
}
