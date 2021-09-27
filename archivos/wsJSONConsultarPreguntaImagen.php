<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["id"])){
		$id=$_GET["id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from basico where id= '{$id}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["palabra"]=$registro['palabra'];
			$result["pregunta"]=$registro['pregunta'];
			$result["imagen"]=base64_encode($registro['imagen']);
			$json['idpregunta'][]=$result;
		}else{
			$resultar["id"]=0;
			$resultar["palabra"]='no registra';
			$resultar["pregunta"]='no registra';
			$resultar["imagen"]='no registra';
			$json['idpregunta'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['idpregunta'][]=$resultar;
		echo json_encode($json);
	}
?>