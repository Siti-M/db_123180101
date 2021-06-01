package com.example.db_123180101.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_123180101.R;
import com.example.db_123180101.entity.DataPenjualan;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataPenjualan> list;
    MainContact.view eView;

    public MainAdapter(Context context, List<DataPenjualan> list, MainContact.view eView) {
        this.context = context;
        this.list = list;
        this.eView = eView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataPenjualan item =list.get(position);
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvKotor.setText(item.getP_kotor());
        holder.tvPengeluaran.setText(item.getPengeluaran());
        holder.id.setText(item.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                eView.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvKotor, tvPengeluaran, id;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvKotor = itemView.findViewById(R.id.tv_kotor);
            tvPengeluaran = itemView.findViewById(R.id.tv_pengeluaran);
            id = itemView.findViewById(R.id.tv_id);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
