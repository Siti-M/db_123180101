package com.example.db_123180101.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;

@Entity(tableName = "penjualan_db")
public class DataPenjualan {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "tanggal")
    private String tanggal;
    @ColumnInfo(name = "pemasukan_kotor")
    private int p_kotor;
    @ColumnInfo(name = "pengeluaran")
    private int pengeluaran;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getP_kotor() {
        return p_kotor;
    }

    public void setP_kotor(int p_kotor) {
        this.p_kotor = p_kotor;
    }

    public int getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(int pengeluaran) {
        this.pengeluaran = pengeluaran;
    }
}
