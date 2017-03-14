<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
    <title>title</title>
    <link rel="stylesheet" type="text/css" href="../css/api.css"/>
    <style>
    	body{
    		
    	}
    	h1{padding-left: 10px;padding-top: 40px;}
    	div{padding-left: 10px;padding-bottom: 20px;padding-right: 10px;}
    	h2{
    		height: 40px;
    	}
    	i{ font-style: normal;font-size: 1.17em;font-weight: bold;}
    	span{ color:red}
    	h3{padding-left: 10px;padding-right: 10px;}
    	.line{border-top:1px dashed #e6e6e6;margin-top: 30px;}
    </style>
</head>
<body>
	<p>
		<div>
			
			<h2>Email Title:${subject}</h2>
	        ${content}
			<p class="line"></p>
		</div>
		<h4>From :${email}</h4>
		<h4>From Tel:${phone}</h4>

	</p>
</body>

</html>