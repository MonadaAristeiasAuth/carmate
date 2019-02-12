<?php
	$con=include("config.php");
	$id = mysqli_real_escape_string($con,$_GET['id']);
	$result=$con->query("SELECT * FROM users WHERE id=$id");

	foreach($result as $row){
		foreach ($row as $field){
			if($i==0){
				$id=$field;
			}
			if($i==1){
				$name=$field;
			}
			if($i==2){
				$surname=$field;
			}
			if($i==3){
				$email=$field;
			}
			if($i==4){
				//pass
			}
			if($i==5){
				$isDriver=$field;
			}
			if($i==6){
				$routesGoing=$field;
			}
			if($i==7){
				$routesInterested=$field;
			}
			if($i==8){
				$routesDriving=$field;
			}
			$i++;
		}
	}

	echo $name."/".$surname."/".$email."/".$isDriver."/".$routesGoing."/".$routesInterested."/".$routesDriving;


?>
