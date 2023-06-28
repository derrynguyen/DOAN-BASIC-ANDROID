package com.example.gofood.Adapter;

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
import com.example.gofood.Config.SharedPrefManager;
import com.example.gofood.Model.Product;
import com.example.gofood.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView productPriceTextView;
        private Button addToCartButton;
        private ImageView imgProduct;

        private SharedPrefManager sharedPrefManager;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.txtProductName);
            productPriceTextView = itemView.findViewById(R.id.txtProductPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            addToCartButton = itemView.findViewById(R.id.btnAddToCart);
        }

        public void bind(Product product) {
            String imageUrl = product.getImgur();

            Glide.with(itemView.getContext())
                    .load(imageUrl)
                    .into(imgProduct);



            productNameTextView.setText(product.getNameProduct());
            productPriceTextView.setText(String.valueOf(product.getPriceProduct()) + "đ");

            sharedPrefManager = new SharedPrefManager(itemView.getContext());

            //Them san pham
            addToCartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id_user =sharedPrefManager.getUser().getId();
                    String name_product = product.getNameProduct();
                    int price_product = product.getPriceProduct();
                    String imgur = product.getImgur();

                    //lay du lieu tu cac truong co trong api
                    Call<Product> call = ConnectAPI.getInstance().getApi().Addcart(id_user,name_product, price_product, imgur);
                    call.enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.isSuccessful()) {
                                // Xử lý thành công
                                Toast.makeText(itemView.getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                // Xử lý thất bại
                                Toast.makeText(itemView.getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            // Xử lý lỗi kết nối
                            Toast.makeText(itemView.getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

        }
    }
}

