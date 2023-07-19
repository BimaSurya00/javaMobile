package com.example.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.widget.LinearLayout;


public class RiwayatUserAdapter extends RecyclerView.Adapter<RiwayatUserAdapter.ViewHolder> {

    private List<RiwayatUserItem> reportUserItems;
    private Context mContext;

    public RiwayatUserAdapter(Context context, List<RiwayatUserItem> reportUserItems) {
        this.mContext = context;
        if (reportUserItems == null) {
            this.reportUserItems = new ArrayList<>(); // Initialize with an empty list if it's null
        } else {
            this.reportUserItems = reportUserItems;
        }
    }

    public void setData(List<RiwayatUserItem> items) {
        reportUserItems.clear();
        reportUserItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_riwayat_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatUserItem reportUserItem = reportUserItems.get(position);

        holder.tvKategori.setText(reportUserItem.getTypeReport());
        holder.tvHP.setText(reportUserItem.getPhone());
        holder.tvDate.setText(reportUserItem.getDate());
        holder.tvLaporan.setText(reportUserItem.getContent());
        holder.tvStatus.setText(reportUserItem.getStatus());

        switch (reportUserItem.getTypeReport()) {
            case "Laporan Kerusakan Barang Sekertariat":
                holder.layoutHeader.setBackgroundResource(R.color.red);
                break;
            case "Laporan Kerusakan Barang Lakwas":
                holder.layoutHeader.setBackgroundResource(R.color.blue);
                break;
            case "Laporan Kerusakan Barang Turbin":
                holder.layoutHeader.setBackgroundResource(R.color.purple_700);
                break;
            case "Laporan Kerusakan Barang P2":
                holder.layoutHeader.setBackgroundResource(R.color.teal_200);
                break;
            case "Laporan Kerusakan Barang P3":
                holder.layoutHeader.setBackgroundResource(R.color.green);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return reportUserItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvKategori;
        TextView tvDate;
        TextView tvLaporan;
        TextView tvHP;
        TextView tvStatus;
        LinearLayout layoutHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvLaporan = itemView.findViewById(R.id.tvLaporan);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHP = itemView.findViewById(R.id.tvHP);
            tvStatus = itemView.findViewById(R.id.statuslapor);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);
        }
    }
}

