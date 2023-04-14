<?php
require('check_login.php');

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir golfista</title>
</head>
<body> 
    <form action="crearGolfer.php" method="post">
        Nombre<input type="text" name="nombre" id="nombre"><br>
        Nacionalidad<input type="text" name="nacionalidad" id="nacionalidad" ><br>
        Altura<input type="text" name="altura" id="altura"><br>
        Debut<input type="text" name="debut" id="debut" ><br>
        Palmarés<input type="text" size="100" name="palmares" id="palmares"><br>
        <input type="submit" value="Insertar">
    </form>
    <a href="golfers.php">Volver sin crear</a>
</body>
</html>
