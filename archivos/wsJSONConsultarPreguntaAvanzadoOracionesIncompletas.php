<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["id"])){
		$id=$_GET["id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from avanzoracionesincom where idAvaOraInc= '{$id}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idAvaOraInc"]=$registro['idAvaOraInc'];
			$result["aoiPrefijo1"]=$registro['aoiPrefijo1'];
			$result["aoiPalabra1"]=$registro['aoiPalabra1'];
			$result["aoiSufijo1"]=$registro['aoiSufijo1'];
			$result["aoiPrefijo2"]=$registro['aoiPrefijo2'];
			$result["aoiPalabra2"]=$registro['aoiPalabra2'];
			$result["aoiSufijo2"]=$registro['aoiSufijo2'];
			$result["aoiPrefijo3"]=$registro['aoiPrefijo3'];
			$result["aoiPalabra3"]=$registro['aoiPalabra3'];
			$result["aoiSufijo3"]=$registro['aoiSufijo3'];
			$result["aoiPrefijo4"]=$registro['aoiPrefijo4'];
			$result["aoiPalabra4"]=$registro['aoiPalabra4'];
			$result["aoiSufijo4"]=$registro['aoiSufijo4'];
			$result["aoiFraseEsp1"]=$registro['aoiFraseEsp1'];
			$result["aoiFraseEsp2"]=$registro['aoiFraseEsp2'];
			$result["aoiFraseEsp3"]=$registro['aoiFraseEsp3'];
			$result["aoiFraseEsp4"]=$registro['aoiFraseEsp4'];
			$json['avanOraInc'][]=$result;
		}else{
			$resultar["id"]=0;
			$resultar["aoiPrefijo1"]='no registra';
			$resultar["aoiPalabra1"]='no registra';
			$resultar["aoiSufijo1"]='no registra';
			$resultar["aoiPrefijo2"]='no registra';
			$resultar["aoiPalabra2"]='no registra';
			$resultar["aoiSufijo2"]='no registra';
			$resultar["aoiPrefijo3"]='no registra';
			$resultar["aoiPalabra3"]='no registra';
			$resultar["aoiSufijo3"]='no registra';
			$resultar["aoiPrefijo4"]='no registra';
			$resultar["aoiPalabra4"]='no registra';
			$resultar["aoiSufijo4"]='no registra';
			$resultar["aoiFraseEsp1"]='no registra';
			$resultar["aoiFraseEsp2"]='no registra';
			$resultar["aoiFraseEsp3"]='no registra';
			$resultar["aoiFraseEsp4"]='no registra';
			$json['avanOraInc'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['avanOraInc'][]=$resultar;
		echo json_encode($json);
	}
?>