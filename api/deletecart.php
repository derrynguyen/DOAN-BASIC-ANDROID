<?php
require 'connection.php';

// Lấy ID cart từ phương thức POST hoặc GET
$id_cart = $_POST['id_cart']; // Nếu truyền qua phương thức POST
// $id_cart = $_GET['id_cart']; // Nếu truyền qua phương thức GET

// Kiểm tra nếu ID cart không tồn tại
if (empty($id_cart)) {
    $response = array("status" => "error", "message" => "ID cart không được truyền");
    echo json_encode($response);
    exit; // Dừng thực hiện mã PHP
}

// Tiến hành xóa cart từ cơ sở dữ liệu
$sql = "DELETE FROM cart WHERE id_cart = '$id_cart'";

// Kiểm tra nếu biến $conn đã được khởi tạo và trỏ đúng đến kết nối MySQL
if ($con instanceof mysqli && mysqli_query($con, $sql)) {
    $response = array("status" => "success", "message" => "Xóa sản phẩm thành công");
    echo json_encode($response);
} else {
    $response = array("status" => "error", "message" => "Lỗi khi xóa sản phẩm: " . mysqli_error($conn));
    echo json_encode($response);
}

mysqli_close($con);
?>