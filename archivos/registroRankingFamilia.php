<?php

$hostname="localhost";
$database="pregunta";
$username="root";
$password="";
$json=array();

if( isset($_GET["nombre"])&& isset($_GET["puntaje"])){
    
    $nombre=$_GET["nombre"];
    $puntaje=$_GET["puntaje"];
    
    $conexion=mysqli_connect($hostname,$username,$password,$database);
    $insert="insert into ranking_familia(nombre,puntaje) values('{$nombre}','{$puntaje}')";
	
    $resultado_insert=mysqli_query($conexion,$insert);
    if($resultado_insert)
    {
        $consulta="select*from ranking_familia where nombre='{$nombre}'";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado))
        {
            $json['rking_familia'][]=$registro;

        }
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else{
        
        $resultado["nombre"]="no registra";
        $json['rking_familia'][]=$resultado;
        echo json_encode($json);

    }
    }
    else
    {
        
        $resultado["nombre"]="WS no devuelve";
        $json['rking_familia'][]=$resultado;
        echo json_encode($json);
    }
?>