<?php   

$password = $_POST['password'];


$url = "http://localhost:20001/?logic=RsaTest&test=".urlencode($password);
$html = curl_get($url);
echo $password . "\n";
echo $html;

function curl_get($url, $gzip=false){
    $curl = curl_init($url);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($curl, CURLOPT_CONNECTTIMEOUT, 10);
    if($gzip) curl_setopt($curl, CURLOPT_ENCODING, "gzip"); // 关键在这里
    $content = curl_exec($curl);
    curl_close($curl);
    return $content;
}

?>