<?php

$hostname="localhost";
$database="animados";
$username="root";
$password="";
$json=array();
$conexion=mysqli_connect($hostname,$username,$password
,$database);

$consulta="select id,nombre,caracteristicas,idserie from personaje";
$resultado=mysqli_query($conexion,$consulta);
while($registro=mysqli_fetch_array($resultado))
{
    $json['personaje'][]=$registro;
}
mysqli_close($conexion);
echo json_encode($json);




?>