package com.fahrozi.projekakhir;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;

import com.fahrozi.projekakhir.db.DbHelper;

public class MainActivity extends AppCompatActivity {
    DbHelper dbHelper;
    private EditText inNama, inNIK;
    private Button btnSimpan, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        inNIK = findViewById(R.id.inp_nik);
        inNama = findViewById(R.id.inp_nama);
        btnSimpan = findViewById(R.id.btn_submit);
        btnList = findViewById(R.id.btn_list);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inNIK.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error: NIK harus diisi", Toast.LENGTH_SHORT).show();
                } else if (inNama.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Error: Nama Lengkap harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addUserDetail(inNIK.getText().toString(), inNama.getText().toString());
                    inNIK.setText("");
                    inNama.setText("");
                    Toast.makeText(MainActivity.this, "Simpan berhasil", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListMasyarakatActivity.class);
                startActivity(intent);
            }
        });
    }
}