package com.example.assignment2aug2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FourthFragment extends Fragment {
    ListView lv_menulist;
    ArrayList<String> list;
    ArrayAdapter<String> arrayAdapter;

    public FourthFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fourth,container,false);
        lv_menulist=(ListView)view.findViewById(R.id.lV01);
        showMenuOnListView();
        return view;
    }
    public void showMenuOnListView(){
        list= new ArrayList<String>();
        list.add("Profile");
        list.add("Donor Card");
        list.add("FAQs");
        list.add("How to use INTIansBlood ?");
        list.add("Logout");
        arrayAdapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
        lv_menulist.setAdapter(arrayAdapter);

        lv_menulist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                if(position==0){
                    //profile
                    startActivity(new Intent(getActivity(), Profile.class));
                }else if(position==1){
                    //donor card
                    startActivity(new Intent(getActivity(), Profile.class));
                }else if (position==2){
                    //FAQs
                    startActivity(new Intent(getActivity(), FAQs.class));
                }else if (position==3){
                    //how to use
                    startActivity(new Intent(getActivity(), Profile.class));
                }else if (position==4){
                    //logout
                    startActivity(new Intent(getActivity(), Profile.class));
                }
            }
        });
    }
}