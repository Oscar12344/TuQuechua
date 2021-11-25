<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from ranking_familia_intermedio order by puntaje desc";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			
			$result["idrank"]=$registro['idrank'];
			
			$result["nombre"]=$registro['nombre'];
			$result["puntaje"]=$registro['puntaje'];
			$json['rank_familia_intermedio'][]=$result;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>

