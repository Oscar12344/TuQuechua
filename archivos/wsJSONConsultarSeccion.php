	<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["idseccion"])){
		$idseccion=$_GET["idseccion"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from seccion where idseccion= '{$idseccion}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idseccion"]=$registro['idseccion'];
			
			$result["nomsecc1"]=$registro['nomsecc1'];
			$result["nomsecc2"]=$registro['nomsecc2'];
			$result["nomsecc3"]=$registro['nomsecc3'];
			$result["nomsecc4"]=$registro['nomsecc4'];
			$result["imagensecc1"]=base64_encode($registro['imagensecc1']);
			$result["imagensecc2"]=base64_encode($registro['imagensecc2']);
			$result["imagensecc3"]=base64_encode($registro['imagensecc3']);
			$result["imagensecc4"]=base64_encode($registro['imagensecc4']);
			
			$json['secciones'][]=$result;
		}else{
			$resultar["idseccion"]=0;
			
			$resultar["nomsecc1"]='no registra';
			$resultar["nomsecc2"]='no registra';
			$resultar["nomsecc3"]='no registra';
			$resultar["nomsecc4"]='no registra';
			$result["imagensecc1"]='no registra';
			$result["imagensecc2"]='no registra';
			$result["imagensecc3"]='no registra';
			$result["imagensecc4"]='no registra';
			
			$json['secciones'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["idseccion"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['secciones'][]=$resultar;
		echo json_encode($json);
	}
?>