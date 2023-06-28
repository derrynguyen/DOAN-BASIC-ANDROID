package com.example.gofood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gofood.Model.OrdersDetail;
import com.example.gofood.R;

import java.util.List;

public class DetailOrdersAdapter extends RecyclerView.Adapter<DetailOrdersAdapter.DetailViewHolder> {
    private List<OrdersDetail> ordersDetailList;
    private OnDetailClickListener detailClickListener;

    public DetailOrdersAdapter(List<OrdersDetail> ordersDetailList) {
        this.ordersDetailList = ordersDetailList;
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        OrdersDetail ordersDetail = ordersDetailList.get(position);
        holder.bind(ordersDetail);
    }

    @Override
    public int getItemCount() {
        return ordersDetailList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemName;
        private TextView itemPrice;
        private TextView itemSL;

        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.txtNamePro);
            itemPrice = itemView.findViewById(R.id.txtTongGia);
            itemSL = itemView.findViewById(R.id.txtSL);

            itemView.setOnClickListener(this);


        }

        public void bind(OrdersDetail ordersDetail) {
            itemName.setText(ordersDetail.getIdNameProduct());
            itemPrice.setText(String.valueOf(ordersDetail.getTotal()));
            itemSL.setText(String.valueOf(ordersDetail.getAmount()));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && detailClickListener != null) {
                OrdersDetail ordersDetail = ordersDetailList.get(position);
                detailClickListener.onDetailClick(ordersDetail);
            }
        }
    }

    public void setOnDetailClickListener(OnDetailClickListener listener) {
        this.detailClickListener = listener;
    }

    public interface OnDetailClickListener {
        void onDetailClick(OrdersDetail ordersDetail);
    }
}
