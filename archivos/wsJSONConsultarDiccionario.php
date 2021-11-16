	<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["idDic"])){
		$idDic=$_GET["idDic"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from diccionario where idDic= '{$idDic}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idDic"]=$registro['idDic'];
			
			$result["dicPalabraQuechua"]=$registro['dicPalabraQuechua'];
			$result["dicSignificado"]=$registro['dicSignificado'];
			
			
			
	
			
			$json['dicc'][]=$result;
		}else{
			$resultar["idDic"]=0;
			
			$resultar["dicPalabraQuechua"]='no registra';
			
			
			
			$json['dicc'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["idDic"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['dicc'][]=$resultar;
		echo json_encode($json);
	}
?>