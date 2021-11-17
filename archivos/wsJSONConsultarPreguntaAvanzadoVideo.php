<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["id"])){
		$id=$_GET["id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from avanzadovideo where id= '{$id}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["id"]=$registro['id'];
			$result["avURL"]=$registro['avURL'];
			$result["avPre1Rpta"]=$registro['avPre1Rpta'];
			$result["avPre2Rpta"]=$registro['avPre2Rpta'];
			$result["avPre3Rpta"]=$registro['avPre3Rpta'];
			$result["avPre1Op1"]=$registro['avPre1Op1'];
			$result["avPre1Op2"]=$registro['avPre1Op2'];
			$result["avPre1Op3"]=$registro['avPre1Op3'];
			$result["avPre1Op4"]=$registro['avPre1Op4'];
			$result["avPre2Op1"]=$registro['avPre2Op1'];
			$result["avPre2Op2"]=$registro['avPre2Op2'];
			$result["avPre2Op3"]=$registro['avPre2Op3'];
			$result["avPre2Op4"]=$registro['avPre2Op4'];
			$result["avPre3Op1"]=$registro['avPre3Op1'];
			$result["avPre3Op2"]=$registro['avPre3Op2'];
			$result["avPre3Op3"]=$registro['avPre3Op3'];
			$result["avPre3Op4"]=$registro['avPre3Op4'];
			$result["avURL2"]=$registro['avURL2'];
			$result["avURL3"]=$registro['avURL3'];
			$result["avURL4"]=$registro['avURL4'];
			$json['avanVideo'][]=$result;
		}else{
			$resultar["id"]=0;
			$resultar["avURL"]='no registra';
			$resultar["avPre1Rpta"]='no registra';
			$resultar["avPre2Rpta"]='no registra';
			$resultar["avPre3Rpta"]='no registra';
			$resultar["avPre1Op1"]='no registra';
			$resultar["avPre1Op2"]='no registra';
			$resultar["avPre1Op3"]='no registra';
			$resultar["avPre1Op4"]='no registra';
			$resultar["avPre2Op1"]='no registra';
			$resultar["avPre2Op2"]='no registra';
			$resultar["avPre2Op3"]='no registra';
			$resultar["avPre2Op4"]='no registra';
			$resultar["avPre3Op1"]='no registra';
			$resultar["avPre3Op2"]='no registra';
			$resultar["avPre3Op3"]='no registra';
			$resultar["avPre3Op4"]='no registra';
			$resultar["avURL2"]='no registra';
			$resultar["avURL3"]='no registra';
			$resultar["avURL4"]='no registra';
			$json['avanVideo'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['avanVideo'][]=$resultar;
		echo json_encode($json);
	}
?>