<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["id"])){
		$id=$_GET["id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from avanzadovf where idVF= '{$id}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idVF"]=$registro['idVF'];
			$result["vfOra1"]=$registro['vfOra1'];
			$result["vfOra2"]=$registro['vfOra2'];
			$result["vfOra3"]=$registro['vfOra3'];
			$result["vfOra4"]=$registro['vfOra4'];
			$result["vfResp1"]=$registro['vfResp1'];
			$result["vfResp2"]=$registro['vfResp2'];
			$result["vfResp3"]=$registro['vfResp3'];
			$result["vfResp4"]=$registro['vfResp4'];
			$json['intVF'][]=$result;
		}else{
			$resultar["idVF"]=0;
			$resultar["vfOra1"]='no registra';
			$resultar["vfOra2"]='no registra';
			$resultar["vfOra3"]='no registra';
			$resultar["vfOra4"]='no registra';
			$resultar["vfResp1"]='no registra';
			$resultar["vfResp2"]='no registra';
			$resultar["vfResp3"]='no registra';
			$resultar["vfResp4"]='no registra';
			$json['intVF'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['intVF'][]=$resultar;
		echo json_encode($json);
	}
?>