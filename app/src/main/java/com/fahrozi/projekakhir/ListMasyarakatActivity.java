package com.fahrozi.projekakhir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fahrozi.projekakhir.adapter.MasyarakatAdapter;
import com.fahrozi.projekakhir.db.DbHelper;
import com.fahrozi.projekakhir.model.Masyarakat;

import java.util.ArrayList;

public class ListMasyarakatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MasyarakatAdapter adapter;
    private ArrayList<Masyarakat> masyarakatArrayList;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_masyarakat);

        recyclerView = findViewById(R.id.rtampll);
        adapter = new MasyarakatAdapter(this);

        dbHelper = new DbHelper(this);
        masyarakatArrayList = dbHelper.getAllUsers();
        adapter.setListMasyarakat(masyarakatArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMasyarakatActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        masyarakatArrayList = dbHelper.getAllUsers();
        adapter.setListMasyarakat(masyarakatArrayList);
        adapter.notifyDataSetChanged();
    }
}