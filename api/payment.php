<?php
require 'connection.php';

// Nhận dữ liệu từ POST request
$cartList = $_POST['cartList'];

echo $cartList;
$donHang = random_int(111, 999);
$currentDate = date("Y-m-d");
// Chuyển đổi dữ liệu JSON thành mảng
$cartItems = json_decode($cartList, true);

if (is_array($cartItems)) {
    // Lặp qua các món hàng và thêm chúng vào cơ sở dữ liệu
    foreach ($cartItems as $item) {
        $total = 0;
        $id_user = $item['id_user'];
        $id_order = $donHang;
        $name_product = $item['name_product'];
        $price_product = $item['price_product'];
        $imgur = $item['imgur'];
        $amount = $item['amount'];
        $total = $item['price_product'] * $item['amount'];



        $sql = "INSERT INTO order_detail (id_user,id_order,name_product, price_product, imgur, amount,total) VALUES ('$id_user','$id_order','$name_product', '$price_product', '$imgur', '$amount', '$total')";

        if ($con->query($sql) === TRUE) {
            echo $id_user;

        } else {
            echo "Lỗi: " . $sql . "<br>";
        }
    }
} else {
    echo "Dữ liệu JSON không hợp lệ hoặc không tồn tại.";
}


$deleteSql = "DELETE FROM cart WHERE id_user = '$id_user'";
if ($con->query($deleteSql) === TRUE) {
    echo "Xóa thành công.";
} else {
    echo "Lỗi: " . $deleteSql . "<br>";
}

$AddSql = "INSERT INTO orders (id_user,id_detail_order,create_at)  values ('$id_user','$donHang','$currentDate')";
if ($con->query($AddSql) === TRUE) {
    echo "Thêm thành công.";
} else {
    echo "Lỗi: " . $AddSql . "<br>";
}


// Đóng kết nối đến cơ sở dữ liệu
$con->close();
?>