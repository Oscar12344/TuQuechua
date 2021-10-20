<?php

$hostname="localhost";
$database="pregunta";
$username="root";
$password="";
$json=array();

if( isset($_GET["correo"])&& isset($_GET["contra"])){
    
    $correo=$_GET["correo"];
    $contra=$_GET["contra"];
    
    $conexion=mysqli_connect($hostname,$username,$password,$database);
    $insert="insert into usuario(correo,contra) values('{$correo}','{$contra}')";
	
    $resultado_insert=mysqli_query($conexion,$insert);
    if($resultado_insert)
    {
        $consulta="select*from usuario where correo='{$correo}'";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado))
        {
            $json['user'][]=$registro;

        }
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else{
        
        $resultado["correo"]="no registra";
        $json['user'][]=$resultado;
        echo json_encode($json);

    }
    }
    else
    {
        
        $resultado["correo"]="WS no devuelve";
        $json['user'][]=$resultado;
        echo json_encode($json);
    }
?>
 