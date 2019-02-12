<?php
	$con = include("config.php");
	$id = mysqli_real_escape_string($con,$_GET['id']);
	
	$resultUser=$con->query("SELECT * FROM users WHERE id=$id");
	$i=0;
	foreach($resultUser as $row){
		foreach($row as $field){
			if($i==7){
				$routesInterested=$field;	
			}
			$i++;
		}
	}

	$arrRoutesInterested = explode(",",$routesInterested);
	//echo "--".$routesInterested;
	foreach($arrRoutesInterested as $routeId){
		$resultRoute = $con->query("SELECT * FROM routes WHERE id=$routeId");
		
		$j=0;
		foreach($resultRoute as $row){
			foreach($row as $field){
				if($j==0){
					$id=$field;	
				}
				if ($j==1){
					$fromLoc = $field;
				}
				if($j==2){
					$toLoc = $field;
				}
				if($j==3){
					$driver=$field;
				}
				if($j==4){
					$people=$field;
				}
				if($j==5){
					$capacity=$field;
				}
				if($j==6){
					$date=$field;
				}
				$j++;
			}
			$routesToReturn.=$id."/".$fromLoc."/".$toLoc."/".$driver."/".$people."/".$capacity."/".$date."//";
			$j=0;
		}
	}

	echo $routesToReturn;
?>
