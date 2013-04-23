<?php
	include "connexion.php";
	$idexploitation = $_POST['idexploitation'];
	$surface = $_POST['surface'];
	$rprevu = $_POST['rprevu'];
	$rrealise = $_POST['rrealise'];
	$idespece = $_POST['idespece'];
	
	$req="INSERT INTO parcelles(idexploitation,rendementprevu,rendementrealise,surface,especeid) Values('".$idexploitation."','".$rprevu."','".$rrealise."','".$surface."','".$idespece."')";
	$stmt = $pdo->query($req);

	$req2="select idparcelle from parcelles ORDER BY idparcelle DESC LIMIT 0 , 1 ";
	$stmt2 = $pdo->query($req2);
	$ligne = $stmt2->fetch(); 
	echo $ligne['idparcelle'];
	echo "\n";
	echo "true";
?>
