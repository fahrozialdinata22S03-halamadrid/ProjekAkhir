package com.fahrozi.projekakhir.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fahrozi.projekakhir.ListMasyarakatActivity;
import com.fahrozi.projekakhir.R;
import com.fahrozi.projekakhir.UpdateActivity;
import com.fahrozi.projekakhir.db.DbHelper;
import com.fahrozi.projekakhir.model.Masyarakat;

import java.io.Serializable;
import java.util.ArrayList;

public class MasyarakatAdapter extends RecyclerView.Adapter<MasyarakatAdapter.MasyarakatViewHolder> {
    private ArrayList<Masyarakat> listMasyarakat = new ArrayList<>();
    private Activity activity;
    private DbHelper dbHelper;

    public MasyarakatAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public static class MasyarakatViewHolder extends RecyclerView.ViewHolder {
        TextView txtNIK, txtNama;
        Button btnUbah, btnHapus;

        public MasyarakatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNIK = itemView.findViewById(R.id.tv_item_nik);
            txtNama = itemView.findViewById(R.id.tv_item_name);
            btnUbah = itemView.findViewById(R.id.btn_ubah);
            btnHapus = itemView.findViewById(R.id.btn_hapus);
        }
    }

    @NonNull
    @Override
    public MasyarakatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_masyarakat, parent, false);
        return new MasyarakatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MasyarakatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtNIK.setText(listMasyarakat.get(position).getNIK());
        holder.txtNama.setText(listMasyarakat.get(position).getNama());

        holder.btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UpdateActivity.class);
                intent.putExtra("data", (Serializable) listMasyarakat.get(position));
                activity.startActivity(intent);
            }
        });

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Konfirmasi hapus");
                builder.setMessage("Apakah yakin akan dihapus?");
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteUser(listMasyarakat.get(position).getId());
                        Toast.makeText(activity, "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(activity, ListMasyarakatActivity.class);
                        activity.startActivity(myIntent);
                        activity.finish();
                    }
                });
                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMasyarakat.size();
    }

    public void setListMasyarakat(ArrayList<Masyarakat> listMasyarakat) {
        this.listMasyarakat.clear();
        this.listMasyarakat.addAll(listMasyarakat);
        notifyDataSetChanged();
    }
}