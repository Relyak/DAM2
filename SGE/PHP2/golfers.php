<?php 
require('check_login.php');
require('db.php');

$stmt = $conn->prepare("SELECT * FROM golfistas");
$stmt->execute();

$result = $stmt->fetchAll(PDO::FETCH_ASSOC);
//var_dump($result);
echo '<a href="newGolfer.php"><h1>CREAR GOLFISTA</h1></a></br></br>';
foreach ($result as $row) {
    print $row["id"] ." - " .$row["nombre"] . " - " . $row["nacionalidad"]. " - "; 
    print $row["altura"] ." - ".$row["debut"]." - " .$row["palmares"]. " ";
    print "<a href='editarGolfer.php?id=".$row["id"]."'>Editar</a> <a href='deleteGolfer.php?id=".$row["id"]."'>Borrar</a><br/>" ;
}

?>