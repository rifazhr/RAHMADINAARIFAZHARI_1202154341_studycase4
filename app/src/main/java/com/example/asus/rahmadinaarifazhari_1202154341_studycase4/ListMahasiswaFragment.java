package com.example.asus.rahmadinaarifazhari_1202154341_studycase4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asus on 3/18/2018.
 */

public class ListMahasiswaFragment extends Fragment {
    public ListMahasiswaFragment
            (){

    }

    public ListMahasiswa.ItemListView itemListView;
    private Activity activity;

//    public void beginTask(String... ars){
//        itemListView = new ListNamaMahasiswa.ItemListView(activity);
//        itemListView.execute(ars);
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        if (itemListView != null){
            itemListView.onAttach(activity);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (itemListView != null){
            itemListView.onDetach();
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}
