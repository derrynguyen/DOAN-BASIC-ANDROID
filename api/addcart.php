<?php
require 'connection.php';
$id_user = $_POST['id_user'];
$name_product = $_POST['name_product'];
$price_product = $_POST['price_product'];
$imgur = $_POST['imgur'];

// Kiểm tra xem sản phẩm đã tồn tại trong cơ sở dữ liệu hay chưa
$selectQuery = "SELECT * FROM cart WHERE name_product = '$name_product' and id_user = '$id_user'";
$selectResult = mysqli_query($con, $selectQuery);

if (mysqli_num_rows($selectResult) > 0) {
  // Sản phẩm đã tồn tại, cập nhật giá trị cột "amount"
  $updateQuery = "UPDATE cart SET amount = amount + 1 WHERE name_product = '$name_product'";
  $updateResult = mysqli_query($con, $updateQuery);

  if ($updateResult) {
    $response['error'] = "200";
    $response['message'] = "Cập nhật sản phẩm thành công!";
  } else {
    $response['error'] = "400";
    $response['message'] = "Cập nhật sản phẩm thất bại!";
  }
} else {
  // Sản phẩm chưa tồn tại, thêm mới vào cơ sở dữ liệu
  $insertQuery = "INSERT INTO cart (id_user,name_product, price_product, imgur, amount) VALUES ('$id_user','$name_product', '$price_product', '$imgur', 1)";
  $insertResult = mysqli_query($con, $insertQuery);

  if ($insertResult) {
    $response['error'] = "200";
    $response['message'] = "Thêm sản phẩm thành công!";
  } else {
    $response['error'] = "400";
    $response['message'] = "Thêm sản phẩm thất bại!";
  }
}

echo json_encode($response);
?>