package com.example.gofood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gofood.Model.Orders;
import com.example.gofood.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.CartViewHolder> {
    private List<Orders> orderList;
    private OnItemClickListener listener;
    private OrderClickListener orderClickListener;

    public OrderAdapter(List<Orders> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Orders orders = orderList.get(position);
        holder.bind(orders);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnDetailClickListener(OrderClickListener listener) {
        this.orderClickListener = listener;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView oderDetail, createAt;
        private Button btnDetailOrder;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            oderDetail = itemView.findViewById(R.id.txtOrderDetail);
            createAt = itemView.findViewById(R.id.txtCreateAt);
            btnDetailOrder = itemView.findViewById(R.id.btnDetailOrders);

            btnDetailOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && orderClickListener != null) {
                        orderClickListener.onDetailClick(orderList.get(position));
                    }
                }
            });
        }

        public void bind(Orders orders) {
            oderDetail.setText(String.valueOf(orders.getIDDetailOrder()));
            createAt.setText(orders.getCreateAt());
        }
    }

    public void setData(List<Orders> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onDeleteClick(Orders orders);
    }

    public interface OrderClickListener {
        void onDetailClick(Orders orders);
    }
}
