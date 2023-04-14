<?php
require('check_login.php');
require('db.php');

var_dump($_POST);
$sql = "DELETE FROM golfistas WHERE id = :id";

$stmt = $conn->prepare($sql);
$stmt->bindParam(':id', $_POST["id"], PDO::PARAM_STR); 

if($stmt->execute()==true)
    header("Location: golfers.php");
?>
