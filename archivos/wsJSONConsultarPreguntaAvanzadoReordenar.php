<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["id"])){
		$id=$_GET["id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from avanzado_ordenar where idAvanzado= '{$id}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idAvanzado"]=$registro['idAvanzado'];
			$result["avOracionEsp"]=$registro['avOracionEsp'];
			$result["avOracionQue"]=$registro['avOracionQue'];
			$result["avPalabra1"]=$registro['avPalabra1'];
			$result["avPalabra2"]=$registro['avPalabra2'];
			$result["avPalabra3"]=$registro['avPalabra3'];
			$result["avPalabra4"]=$registro['avPalabra4'];
			$result["avPalabra5"]=$registro['avPalabra5'];
			$json['avanOrac'][]=$result;
		}else{
			$resultar["idAvanzado"]=0;
			$resultar["avOracionEsp"]='no registra';
			$resultar["avOracionQue"]='no registra';
			$resultar["avPalabra1"]='no registra';
			$resultar["avPalabra2"]='no registra';
			$resultar["avPalabra3"]='no registra';
			$resultar["avPalabra4"]='no registra';
			$json['avanOrac'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['avanOrac'][]=$resultar;
		echo json_encode($json);
	}
?>