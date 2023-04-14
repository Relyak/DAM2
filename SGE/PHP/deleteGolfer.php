<?php
require('check_login.php');
require('db.php');
$id = $_GET["id"];

$stmt = $conn->prepare("SELECT * FROM golfistas WHERE id=:id");
$stmt->bindParam(':id', $id);
$stmt->execute();
$result = $stmt->fetch(PDO::FETCH_ASSOC);
//var_dump($result);
?>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar golfista</title>
</head>

<body>
  <form action="borrarGolfer.php" method="post">
    Nombre:
    <?php echo $result["nombre"] ?><br>
    Nacionalidad:
    <?php echo $result["nacionalidad"] ?><br>
    Altura:
    <?php echo $result["altura"] ?><br>
    Debut:
    <?php echo $result["debut"] ?><br>
    Palmarés:
    <?php echo $result["palmares"] ?><br>
    <input type="hidden" name="id" value='<?php echo $id ?>'>
    <input type="submit" value="Borrar" id="btnBorrar">
  </form>
  <a href="golfers.php">Volver sin modificar</a>
  <script>

    document.getElementById("btnBorrar").addEventListener("click", borrar, false);

    function borrar() {
      let text = "Estas seguro de que quieres borrar";
      if (confirm(text) == true) {
        alert("borrado");
        return true;

      } else {
        event.preventDefault();
        return false;
      }
    }
  </script>
</body>

</html>