<?php
	include "connexion.php";
	$req="select idexploitation from parcelles where idexploitation ='".$_GET["id"]."'";
	$stmt = $pdo->query($req);
	$ligne = $stmt->fetch(); 
	if ($ligne['idexploitation'] != "") {
		echo "true";
	}
	else {
		echo "false";
	}
?>
