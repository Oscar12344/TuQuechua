<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pregunta";
$username_localhost ="root";
$password_localhost ="";

$json['img']=array();

	//if(true){)
	if(isset($_POST["btn"])){
		
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$ruta="imagenes";
		$archivo=$_FILES['imagen']['tmp_name'];
		echo 'Archivo';
		echo '<br>';
		echo $archivo;
		$nombreArchivo=$_FILES['imagen']['name'];
		echo 'Nombre Archivo';
		echo '<br>';
		echo $nombreArchivo;
		move_uploaded_file($archivo,$ruta."/".$nombreArchivo);
		$ruta=$ruta."/".$nombreArchivo;
		$id=$_POST['id'];
		$palabra=$_POST['palabra'];
		$pregunta=$_POST['pregunta'];
		
		echo '<br>';
		echo 'ID: ';
		echo $id;
		echo '<br>';
		echo 'Palabra: ';
		echo $palabra;
		echo '<br>';
		echo 'Pregunta: ';
		echo $pregunta;
		echo '<br>';
		echo 'ruta :';
		echo $ruta;
		echo '<br>';
		echo 'Tipo Imagen: ';
		echo ($_FILES['imagen']['type']);
		echo '<br>';
		echo '<br>';
		echo "Imagen: <br><img src='$ruta'>";
		echo '<br>';
		echo '<br>';
		echo 'imagen en Bytes: ';
		echo '<br>';
		echo '<br>';
		//echo $bytesArchivo=file_get_contents($ruta);
		echo '<br>';
		
		$bytesArchivo=file_get_contents($ruta);
		$sql="INSERT INTO basico(id,palabra,pregunta,imagen) VALUES (?,?,?,?)";
		$stm=$conexion->prepare($sql);
		$stm->bind_param('isss',$id,$palabra,$pregunta,$bytesArchivo,$ruta);
		
		if($stm->execute()){
			echo 'imagen Insertada Exitosamente ';
			$consulta="select * from basico where id='{$id}'";
			$resultado=mysqli_query($conexion,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['id'].' - '.$row['palabra'].'<br/>';
				array_push($json['img'],array('id'=>$row['id'],'palabra'=>$row['palabra'],'pregunta'=>$row['pregunta'],'photo'=>base64_encode($row['palabra']),'ruta'=>$row['ruta_imagen']));
			}
			mysqli_close($conexion);
			
			echo '<br>';
			echo 'Objeto JSON 2';
			echo '<br>';
			echo json_encode($json);
		}
	}
?>