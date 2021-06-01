package com.example.db_123180101.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db_123180101.R;
import com.example.db_123180101.entity.AppDatabase;
import com.example.db_123180101.entity.DataPenjualan;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{
    private AppDatabase appDatabase;
    private MainPresenter mainPresenter;
    private MainAdapter mainAdapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText etTanggal, etP_kotor, etPengeluaran;

    private int id = 0;
    boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnOK = findViewById(R.id.btn_submit);
        etTanggal = findViewById(R.id.et_tanggal);
        etP_kotor = findViewById(R.id.et_kotor);
        etPengeluaran = findViewById(R.id.et_pengeluaran);
        recyclerView = findViewById(R.id.rc_main);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mainPresenter = new MainPresenter(this);
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this,"Berhasil Menambahkan Data",Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this,"Berhasil Menghapus Data",Toast.LENGTH_SHORT).show();
        mainPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etTanggal.setText("");
        etP_kotor.setText("");
        etPengeluaran.setText("");
        btnOK.setText("Submit");
    }

    @Override
    public void getData(List<DataPenjualan> list) {
        mainAdapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void editData(DataPenjualan item) {
        etTanggal.setText(item.getTanggal());
        etP_kotor.setText(item.getP_kotor());
        etPengeluaran.setText(item.getPengeluaran());
        id = item.getId();
        edit = true;
        btnOK.setText("EDIT DATA");
    }

    @Override
    public void deleteData(DataPenjualan item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetForm();;
                        mainPresenter.deleteData(item,appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .show();
    }

    @Override
    public void onClick(View view) {
        if (view==btnOK){
            if (etTanggal.getText().toString().equals("")||etP_kotor.getText().toString().equals("")||
            etPengeluaran.getText().toString().equals("")){
                Toast.makeText(this, "Harap Isi Seluruh Data!", Toast.LENGTH_SHORT).show();
            }else {
                if (!edit){
                  mainPresenter.insertData(etTanggal.getText().toString(),Integer.parseInt(etP_kotor.getText().toString()),
                          Integer.parseInt(etPengeluaran.getText().toString()),appDatabase);
                }else {
                    mainPresenter.editData(etTanggal.getText().toString(),Integer.parseInt(etP_kotor.getText().toString()),
                            Integer.parseInt(etPengeluaran.getText().toString()),id,appDatabase);
                    edit = false;
                }
            }
        }
    }
}