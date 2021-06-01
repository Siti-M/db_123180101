package com.example.db_123180101.view;

import android.view.View;

import com.example.db_123180101.entity.AppDatabase;
import com.example.db_123180101.entity.DataPenjualan;

import java.util.List;

public interface MainContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataPenjualan> list);
        void editData(DataPenjualan item);
        void deleteData(DataPenjualan item);
    }

    interface presenter{
        void insertData(String tanggal, int pemasukan, int pengeluaran, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String tanggal, int pemasukan, int pengeluaran, int id, AppDatabase database);
        void deleteData(DataPenjualan dataPenjualan, AppDatabase database);
    }
}
