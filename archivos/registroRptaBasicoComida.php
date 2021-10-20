<?php

$hostname="localhost";
$database="pregunta";
$username="root";
$password="";
$json=array();

if(isset($_GET["rpta1_comida_basico"])
&& isset($_GET["rpta2_comida_basico"])&& isset($_GET["rpta3_comida_basico"])&& isset($_GET["rpta4_comida_basico"])
&& isset($_GET["rpta5_comida_basico"])&& isset($_GET["rpta6_comida_basico"]) )
{
	
    
    $rpta1_comida_basico=$_GET["rpta1_comida_basico"];
	$rpta2_comida_basico=$_GET["rpta2_comida_basico"];
	$rpta3_comida_basico=$_GET["rpta3_comida_basico"];
	$rpta4_comida_basico=$_GET["rpta4_comida_basico"];
	$rpta5_comida_basico=$_GET["rpta5_comida_basico"];
	$rpta6_comida_basico=$_GET["rpta6_comida_basico"];
   
    $conexion=mysqli_connect($hostname,$username,$password,$database);
    $insert="insert into respuestas_basico_comida(rpta1_comida_basico,rpta2_comida_basico,rpta3_comida_basico,rpta4_comida_basico,rpta5_comida_basico,rpta6_comida_basico) values
    ('{$rpta1_comida_basico}','{$rpta2_comida_basico}','{$rpta3_comida_basico}','{$rpta4_comida_basico}','{$rpta5_comida_basico}','{$rpta6_comida_basico}')";
    $resultado_insert=mysqli_query($conexion,$insert);
    if($resultado_insert)
    {
        $consulta="select*from respuestas_basico_comida where rpta1_comida_basico='{$rpta1_comida_basico}'";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado))
        {
            $json['rptabasicocomida'][]=$registro;

        }
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else{
        
        $resultado["rpta1_comida_basico"]="no registra";
        
        $json['rptabasicocomida'][]=$resultado;
        echo json_encode($json);

    }
    }
    else
    {	
       
        $resultado["rpta1_comida_basico"]="WS no devuelve";
        
        $json['rptabasicocomida'][]=$resultado;
        echo json_encode($json);
    }
?>
 