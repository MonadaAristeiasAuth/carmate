<?php 
	$con = include("config.php");
	$result = $con->query("SELECT * FROM routes");

	$i=0;
	$id = id;
	$fromLoc = fromLoc;
	$toLoc = toLoc;
	$driver = driver;
	$people = people;
	$capacity = capacity;

	foreach ($result as $row){
		foreach ($row as $field){
			if ($i==0){
				$id = $field;	
			}
			if ($i==1){
				$fromLoc = $field;	
			}
			if ($i==2){
				$toLoc = $field;	
			}
			if ($i==3){
				$driver = $field;	
			}
			if ($i==4){
				$people = $field;	
			}
			if ($i == 5){
				$capacity = $field;	
			}
			if ($i == 6){
				$date = $field;
			}
			$i++;
		}
		$routes.= $id."/".$fromLoc."/".$toLoc."/".$driver."/".$people."/".$capacity."/".$date."//";
		$i=0;
	}

	echo $routes;


?>
