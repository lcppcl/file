 <%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>基于Bootstrup 3可预览的HTML5文件上传插件</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script>
<script src="js/fileinput.js" type="text/javascript"></script>
<script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"
	type="text/javascript"></script>
<script>
	$("#file-0").fileinput({
		'allowedFileExtensions' : [ 'jpg', 'png', 'gif' ],
	});
</script>
</head>
<body>
	<div class="htmleaf-container">
		<header class="htmleaf-header"> </header>

		<div class="container kv-main">
			<div class="page-header">
				<h2>
					Bootstrap File Input Example <small>
				</h2>
			</div>
			<form action="shangchuan" enctype="multipart/form-data"  method="post">
				<input id="file-0a" class="file" type="file" multiple
					data-min-file-count="1" name="Filedata"> <br>
				<button type="submit" class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</form>
		</div>

	</div>
</body>
</html>