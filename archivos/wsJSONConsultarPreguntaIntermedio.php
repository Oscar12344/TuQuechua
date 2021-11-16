	<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["id"])){
		$id=$_GET["id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from intermedio where id= '{$id}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["palabra"]=$registro['palabra'];
			$result["pregunta"]=$registro['pregunta'];
			$result["op1Imagen"]=base64_encode($registro['op1Imagen']);
			$result["op2Imagen"]=base64_encode($registro['op2Imagen']);
			$result["op3Imagen"]=base64_encode($registro['op3Imagen']);
			$result["op4Imagen"]=base64_encode($registro['op4Imagen']);
			$result["op1"]=$registro['op1'];
			$result["op2"]=$registro['op2'];
			$result["op3"]=$registro['op3'];
			$result["op4"]=$registro['op4'];
			$result["palabraEspanol"]=$registro['palabraEspanol'];
			$json['intermedios'][]=$result;
		}else{
			$resultar["id"]=0;
			$result["palabra"]=$registro['no registra'];
			$resultar["pregunta"]='no registra';
			$result["op1Imagen"]='no registra';
			$result["op2Imagen"]='no registra';
			$result["op3Imagen"]='no registra';
			$result["op4Imagen"]='no registra';
			$resultar["op1"]='no registra';
			$resultar["op2"]='no registra';
			$resultar["op3"]='no registra';
			$resultar["op4"]='no registra';
			$result["palabraEspanol"]=$registro['no registra'];
			$json['intermedios'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['intermedios'][]=$resultar;
		echo json_encode($json);
	}
?>