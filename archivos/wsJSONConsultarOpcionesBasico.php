<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["idbasico"])){
		$idbasico=$_GET["idbasico"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from opbasico where idbasico= '{$idbasico}' ";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idbasico"]=$registro['idbasico'];
			$result["op1"]=$registro['op1'];
			$result["op2"]=$registro['op2'];
			$result["op3"]=$registro['op3'];
			$result["op4"]=$registro['op4'];
			$json['idopbasico'][]=$result;
		}else{
			$resultar["idbasico"]=0;
			$resultar["op1"]='no registra';
			$resultar["op2"]='no registra';
			$resultar["op3"]='no registra';
			$resultar["op4"]='no registra';
			
			$json['idopbasico'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['idopbasico'][]=$resultar;
		echo json_encode($json);
	}
?>