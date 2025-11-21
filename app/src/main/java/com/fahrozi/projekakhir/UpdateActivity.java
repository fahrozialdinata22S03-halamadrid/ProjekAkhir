package com.fahrozi.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fahrozi.projekakhir.db.DbHelper;
import com.fahrozi.projekakhir.model.Masyarakat;

public class UpdateActivity extends AppCompatActivity {
    private EditText inNIK, inNama;
    private Button btnUpdate;
    private DbHelper dbHelper;
    private Masyarakat masyarakat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        inNIK = findViewById(R.id.inp_nik);
        inNama = findViewById(R.id.inp_nama);
        btnUpdate = findViewById(R.id.btn_update);

        dbHelper = new DbHelper(this);

        // Ambil data yang dikirim dari adapter
        masyarakat = (Masyarakat) getIntent().getSerializableExtra("data");
        if (masyarakat != null) {
            inNIK.setText(masyarakat.getNIK());
            inNama.setText(masyarakat.getNama());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nik = inNIK.getText().toString();
                String nama = inNama.getText().toString();
                if (nik.isEmpty() || nama.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    int update = dbHelper.updateUser(masyarakat.getId(), nik, nama);
                    if (update > 0) {
                        Toast.makeText(UpdateActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(UpdateActivity.this, "Gagal mengupdate data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}