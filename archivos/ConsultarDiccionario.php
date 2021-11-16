<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from diccionario order by idDic asc ";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			
			$result["idDic"]=$registro['idDic'];
			
			$result["dicPalabraQuechua"]=$registro['dicPalabraQuechua'];
			$result["dicSignificado"]=$registro['dicSignificado'];
			$json['diccc'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>

