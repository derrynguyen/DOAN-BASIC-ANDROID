<?php
  require 'connection.php';

  $phone=$_POST['phone'];
  $password=md5($_POST['password']);

  $checkUser="SELECT * FROM account WHERE phone='$phone'";

  $result=mysqli_query($con,$checkUser);

  if(mysqli_num_rows($result)>0){ 

    $checkUserquery="SELECT id, phone, fullname FROM account WHERE phone='$phone' and password='$password'";
    $resultant=mysqli_query($con,$checkUserquery);

    if(mysqli_num_rows($resultant)>0){

      while($row=$resultant->fetch_assoc())
      
      $response['user']=$row;
      $response['error']="200";
      $response['message']="OK";
    }
    else{
      $response['user']=(object)[];
      $response['error']="400";
      $response['message']="ERROR";

    }  
  }
  else{

    $response['user']=(object)[];
    $response['error']="400";
  	$response['message']="user does not exist";
  }
  echo json_encode($response);   
?>