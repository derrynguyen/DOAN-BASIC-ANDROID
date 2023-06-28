<?php
require 'connection.php';
include "Model.php";

$model = new Model();

$queqy = 'SELECT * FROM `product`';
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