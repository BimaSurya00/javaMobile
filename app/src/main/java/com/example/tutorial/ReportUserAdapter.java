package com.example.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.widget.LinearLayout;


public class ReportUserAdapter extends RecyclerView.Adapter<ReportUserAdapter.ViewHolder> {
    private List<ReportUserItem> reportUserItems;
    private Context mContext;
    private HistoryAdapterCallback mAdapterCallback;

    public ReportUserAdapter(Context context, List<ReportUserItem> reportUserItems, HistoryAdapterCallback adapterCallback) {
        this.mContext = context;
        this.reportUserItems = reportUserItems;
        this.mAdapterCallback = adapterCallback;
    }


    public void setData(List<ReportUserItem> items) {
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
        ReportUserItem reportUserItem = reportUserItems.get(position);

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

    public interface HistoryAdapterCallback {
        void onDelete(ReportUserItem reportUserItem);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvKategori;
        TextView tvDate;
        TextView tvLaporan;
        TextView tvHP;
        TextView tvStatus;
        CardView cvHistory;
        LinearLayout layoutHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvLaporan = itemView.findViewById(R.id.tvLaporan);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHP = itemView.findViewById(R.id.tvHP);
            tvStatus = itemView.findViewById(R.id.statuslapor);
            cvHistory = itemView.findViewById(R.id.cvHistory);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);


            // Implementasikan interaksi item laporan seperti penghapusan jika diperlukan
            // Misalnya, implementasikan listener onClick untuk menghapus item
            cvHistory.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    ReportUserItem reportUserItem = reportUserItems.get(position);
                    mAdapterCallback.onDelete(reportUserItem);
                }
            });
        }
    }
}
