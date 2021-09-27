<?php

$hostname="localhost";
$database="pregunta";
$username="root";
$password="";
$json=array();

if(isset($_GET["id"])&& isset($_GET["palabra"])
&& isset($_GET["pregunta"]))
{
    $id=$_GET["id"];
    $palabra=$_GET["palabra"];
    $pregunta=$_GET["pregunta"];
   
    $conexion=mysqli_connect($hostname,$username,$password,$database);
    $insert="insert into basico(id
    ,palabra,pregunta) values
    ('{$id}','{$palabra}','{$pregunta}')";
    $resultado_insert=mysqli_query($conexion,$insert);
    if($resultado_insert)
    {
        $consulta="select*from basico where id='{$id}'";
        $resultado=mysqli_query($conexion,$consulta);

        if($registro=mysqli_fetch_array($resultado))
        {
            $json['basico'][]=$registro;

        }
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else{
        $resultado["id"]=0;
        $resultado["palabra"]="no registra";
        $resultado["pregunta"]="no registra";
        $json['basico'][]=$resultado;
        echo json_encode($json);

    }
    }
    else
    {
        $resultado["id"]=0;
        $resultado["palabra"]="WS no devuelve";
        $resultado["pregunta"]="WS no devuelve";
        $json['basico'][]=$resultado;
        echo json_encode($json);
    }
?>
 