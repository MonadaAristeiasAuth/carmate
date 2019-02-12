<?php 
	$con = include("config.php");
	$id = mysqli_real_escape_string($con,$_GET['id']);
	$usersRoutes = $con->query("SELECT * FROM users WHERE id=$id");
	
	$i=0;
	$routesInterested = routes_interested;
	$routesGoing = routes_going;
	$routesDriving = routes_driving;

	foreach($usersRoutes as $row){
		foreach($row as $field){
			if($i == 6){
				$routesGoing = $field;
			}
			if($i == 7){
				$routesInterested = $field;
			}
			if($i == 8){
				$routesDriving = $field;
			}
			$i++;
		}
	}

	return $routesGoing.'/'.$routesInterested.'/'.$routesDriving.'//';
?>
