<?php
include("db.php");

$user=$_POST['user'];
$pw=$_POST['password'];
$em=$_POST['email'];
$r=$_POST['rol'];

$pantes = $salt.$pw;
$pcifrado = hash("sha256",$pantes);

$stmt = $conn->prepare("INSERT INTO users (username,password,email,rol) 
VALUES (?,?,?,?)");

$stmt->execute([$user,$pcifrado,$em,$r]);

  echo "New record created successfully";

?>