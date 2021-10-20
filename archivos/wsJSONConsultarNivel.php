	<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["idnivel"])){
		$idnivel=$_GET["idnivel"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from nivel where idnivel= '{$idnivel}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idnivel"]=$registro['idnivel'];
			
			$result["nomnivel1"]=$registro['nomnivel1'];
			$result["nomnivel2"]=$registro['nomnivel2'];
			$result["nomnivel3"]=$registro['nomnivel3'];
			
			$result["imagennivel1"]=base64_encode($registro['imagennivel1']);
			$result["imagennivel2"]=base64_encode($registro['imagennivel2']);
			$result["imagennivel3"]=base64_encode($registro['imagennivel3']);
	
			
			$json['niveles'][]=$result;
		}else{
			$resultar["idnivel"]=0;
			
			$resultar["nomnivel1"]='no registra';
			
			
			
			$json['niveles'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["idnivel"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['niveles'][]=$resultar;
		echo json_encode($json);
	}
?>