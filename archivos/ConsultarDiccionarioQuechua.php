	<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();

	if(isset($_GET["dicPalabraQuechua"])){
		$dicPalabraQuechua=$_GET["dicPalabraQuechua"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from diccionario where dicPalabraQuechua= '{$dicPalabraQuechua}'";
		$resultado=mysqli_query($conexion,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["idDic"]=$registro['idDic'];
			$result["dicPalabraQuechua"]=$registro['dicPalabraQuechua'];
			$result["dicSignificado"]=$registro['dicSignificado'];
	
			$result["dicPalabraEspanol"]=$registro['dicPalabraEspanol'];
		
		
			$json['idquechua'][]=$result;
		}else{
			$resultar["idDic"]=0;
			$resultar["dicPalabraQuechua"]='no registra';
			$resultar["dicSignificado"]='no registra';
			$resultar["dicPalabraEspanol"]='no registra';
			
			$json['idquechua'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['idquechua'][]=$resultar;
		echo json_encode($json);
	}
?>