<?php
	$con = include("config.php");
	
	$id = mysqli_real_escape_string($con,$_GET['id']);
	$name = mysqli_real_escape_string($con,$_GET['name']);
	$surname = mysqli_real_escape_string($con,$_GET['surname']);
	$email = mysqli_real_escape_string($con,$_GET['email']);
	$password = mysqli_real_escape_string($con,$_GET['password']);

	//$result = $con->query("SELECT * FROM users");
	//$id = count($result) + rand();

	$sql = "INSERT INTO users (id,name,surname,email,password,isDriver,routesGoing,routesInterested,routesDriving) VALUES ('$id','$name','$surname','$email','$password','0','0','0','0')";

	if($con->query($sql)){
		return 1;	
	}
	else return 0;

?>
