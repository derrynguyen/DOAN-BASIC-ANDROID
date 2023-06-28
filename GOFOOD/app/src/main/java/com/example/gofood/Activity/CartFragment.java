package com.example.gofood.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gofood.Adapter.CartAdapter;
import com.example.gofood.Config.ConnectAPI;
import com.example.gofood.Config.SharedPrefManager;
import com.example.gofood.Model.Cart;
import com.example.gofood.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {

    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private TextView SoLuong;
    private List<Cart> cartList;

    SharedPrefManager sharedPrefManager;
    private int productCount;
    private double totalPrice;

    private  Button payment;
    public CartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartList = new ArrayList<>();


        recyclerViewCart = view.findViewById(R.id.recyclerViewCart);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(cartList);
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(Cart cart) {
                deleteCartItem(cart.getId());
            }


        });

        payment = view.findViewById(R.id.btnPayment);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPayment();

            }
        });
        recyclerViewCart.setAdapter(cartAdapter);

        SoLuong = view.findViewById(R.id.txtSL);

        loadCart();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCart();
    }

    public void loadCart() {

        sharedPrefManager = new SharedPrefManager(getContext());

        int id_user = sharedPrefManager.getUser().getId(); // Lấy id_user từ SharedPrefManager

        Call<List<Cart>> call = ConnectAPI.getInstance().getApi().getCartList();

        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    cartList = response.body();
                    List<Cart> userCartList = new ArrayList<>(); // Danh sách giỏ hàng của id_user

                    for (Cart cart : cartList) {
                        if (cart.getIDUser() == id_user) { // Kiểm tra điều kiện
                            userCartList.add(cart); // Thêm cart vào danh sách giỏ hàng của id_user
                        }
                    }

                    productCount = userCartList.size();
                    cartAdapter.setData(userCartList);
                    cartAdapter.notifyDataSetChanged();

                    totalPrice = 0;
                    for (Cart cart : userCartList) {
                        totalPrice += cart.getPriceProduct() * cart.getAmount();
                    }
                    SoLuong.setText("Tổng giá: " + String.valueOf(totalPrice) + "đ");

                    Log.d("TAG", "Tổng giá trị: " + totalPrice);
                } else {
                    Log.e("TAG", "Lỗi khi lấy dữ liệu giỏ hàng từ API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Log.e("TAG", "Lỗi khi kết nối đến API: " + t.getMessage());
            }
        });
    }


    private void deleteCartItem(int cartId) {
        Call<ResponseBody> call = ConnectAPI.getInstance().getApi().deleteCartItem(cartId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    loadCart();
                } else {
                    Log.e("TAG", "Lỗi khi xóa sản phẩm khỏi giỏ hàng: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("TAG", "Lỗi khi kết nối đến API: " + t.getMessage());
            }
        });
    }

    private void addPayment() {
        sharedPrefManager = new SharedPrefManager(getContext());
        int id_user = sharedPrefManager.getUser().getId(); // Lấy id_user từ SharedPrefManager

        if (cartList != null) {
            if (!cartList.isEmpty()) {
                List<Cart> validCartList = new ArrayList<>();

                // Lọc ra các sản phẩm có id_user trùng khớp với id_user hiện tại
                for (Cart item : cartList) {
                    if (item.getIDUser() == id_user) {
                        validCartList.add(item);
                    }
                }

                if (!validCartList.isEmpty()) {
                    Gson gson = new GsonBuilder().create();
                    String cartListJson = gson.toJson(validCartList);

                    Log.d("TAG", "Tên sản phẩm: " + cartListJson);

                    Call<ResponseBody> call = ConnectAPI.getInstance().getApi().addPayment(cartListJson);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                cartList.clear();
                                cartAdapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                                loadCart();
                            } else {
                                Log.e("TAG", "Lỗi khi thêm mảng đồ ăn xuống database thông qua API PHP: " + response.message());
                                Toast.makeText(getContext(), "Lỗi khi thêm mảng đồ ăn", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("TAG", "Lỗi khi kết nối đến API: " + t.getMessage());
                            Toast.makeText(getContext(), "Lỗi kết nối đến API", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Không có sản phẩm trong giỏ hàng của người dùng hiện tại", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Không có sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT).show();
            }
        } else {
            // CartList không tồn tại, không thực hiện thanh toán
            Toast.makeText(getContext(), "Không tìm thấy giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    }

}


