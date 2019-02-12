<?php
	$con = include("config.php");
	$id = mysqli_real_escape_string($con,$_GET['id']);

	$result = $con->query("SELECT * FROM drivers WHERE id=$id");
	$i=0;
	foreach ($result as $row){
		foreach ($row as $field){
			if($i==3){
				$routesDriving = $field;
			}
			$i++;
		}
		$i=0;
	}

	$routesDrivingIds = explode(",",$routesDriving);
	foreach($routesDrivingIds as $routeId){
		$resultRoutesInfo = $con->query("SELECT * FROM routes WHERE id=$routeId");
		$j=0;
		foreach($resultRouteInfo as $row){
			foreach($row as $field){
				if($j==4){
					$usersInterested=$field;	
				}
				$j++;
			}
			$j=0;
		}		
	}

	$userIds = explode(",",$usersInterested);
	foreach($userIds as $userId){
		$resultUserInfo = $con->query("SELECT * FROM users WHERE id=$userId");
		$k=0;
		foreach($resultUserInfo as $row){
			foreach($row as $field){
					if($k==1){
						$name=$field;
					}
					if($k==2){
						$surname=$field;
					}
					if($k==3){
						$email=$field;
					}
				$k++;
			}
			$usersInfo.=$name.",".$surname.",".$email."/";
			$k=0;
		}
	}
	echo $routesDriving.$usersInfo."//";
?>
