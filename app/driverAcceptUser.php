<?php
	$con = include("config.php");
	$id = mysqli_real_escape_string($con,$_GET['id']); //id of user that has been accepted for the route from driver within the app
	$routeId = mysqli_real_escape_string($con,$_GET['routeId']); //id of the route that the user is accepted of going

	$result = $con->query("SELECT * FROM users WHERE id=$id");
	$i=0;
	foreach($result as $row){
		foreach($row as $field){
			if($i==6){
				$routesGoing=$field;	
			}
			$i++;
		}
		$i=0;
	}

	$routesGoing.=$routeId;

	$resultRoute = $con->query("SELECT * FROM routes WHERE id=$routeId");
	$j=0;
	foreach ($resultRoute as $row){
		foreach($row as $field){
			if($j==4){
				$peopleInterested=$field;	
			}
			$j++;
		}
		$j=0;
	}
	$sql = ("UPDATE users SET routesGoing=$routesGoing WHERE id=$id");
	if($con->query($sql)){
		return 1;	
	}
	else return 0;
?>
