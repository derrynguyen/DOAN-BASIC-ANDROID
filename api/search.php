<?php
require 'connection.php';
include "Model.php";

$model = new Model();

$name = $_POST['name'];


$queqy = "SELECT * FROM `product` WHERE product.name_product LIKE '%$name%' ";
$result = $model->fetchAll($queqy);
if (!empty($result)) {

    $result;
} else {
    $arr = [
        'success' => false,
        'massage' => "khong  thanh cong",

    ];
}
print_r(json_encode($result));
