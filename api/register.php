<?php
require 'connection.php';
$phone = $_POST['phone'];
$password = md5($_POST['password']);
$fullname = $_POST['fullname'];
$addreas = $_POST['addreas'];


$checkUser = "SELECT * from account WHERE phone='$phone'";
$checkQuery = mysqli_query($con, $checkUser);

if (mysqli_num_rows($checkQuery) > 0) {
  $response['error'] = "403";
  $response['message'] = "User exist";
} else {
  $insertQuery = "INSERT INTO account(phone,password,fullname,addreas) VALUES('$phone','$password','$fullname','$addreas')";
  $result = mysqli_query($con, $insertQuery);

  if ($result) {
    $response['error'] = "200";
    $response['message'] = "Đăng ký thành công!";
  } else {
    $response['error'] = "400";
    $response['message'] = "Đăng ký thất bại!";
  }
}
echo json_encode($response);
?>