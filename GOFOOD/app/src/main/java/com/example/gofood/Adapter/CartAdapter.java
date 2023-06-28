package com.example.gofood.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Model.Cart;
import com.example.gofood.Model.Product;
import com.example.gofood.R;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> cartList;
    private OnItemClickListener listener;

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.bind(cart);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView productPriceTextView;
        private TextView Amount,productIdUser;
        private ImageView imgProduct;
        private Button btnDelete,btnPayment;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productIdUser = itemView.findViewById(R.id.idUser);
            productNameTextView = itemView.findViewById(R.id.txtProductName);
            productPriceTextView = itemView.findViewById(R.id.txtProductPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            Amount = itemView.findViewById(R.id.txtAmount);
            btnDelete = itemView.findViewById(R.id.btnDelete);

            //xoa san pham
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onDeleteClick(cartList.get(position));
                    }
                }
            });
        }

        public void bind(Cart cart) {
            String imageUrl = cart.getImgur();

            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .into(imgProduct);

            productIdUser.setText(String.valueOf(cart.getIDUser()));
            productNameTextView.setText(cart.getNameProduct());
            productPriceTextView.setText(String.valueOf(cart.getPriceProduct()) + "đ");
            Amount.setText(String.valueOf(cart.getAmount()));
        }
    }

    public void setData(List<Cart> cartList) {
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onDeleteClick(Cart cart);
    }


}



