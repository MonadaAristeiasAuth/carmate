<?php
$con = include("config.php");
$id = $_GET['id'];
$routeId = $_GET['routeId'];

echo $id."-".$routeId;

$resultInterestedRoutes = $con->query("SELECT * FROM users WHERE id=$id");
$i=0;
foreach($resultInterestedRoutes as $row){
	foreach ($row as $field){
		if($i==7){
			$routesInterested = $field;	
		}
		$i++;
	}
}

$updatedRoutes = $routesInterested.",".$routeId;
echo "ur".$updatedRoutes;

$sql="UPDATE users SET routesInterested='$updatedRoutes' WHERE id='$id'";
if($con->query($sql)){
	return $id."-".$routeId;
}
else return 0;

?>
