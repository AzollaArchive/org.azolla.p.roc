<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Viewport" content="width=device-width, initial-scale=1.0" />
<meta name="keywords" content="Azolla" />
<meta name="Description" content="Azolla" />
<meta name="Author" content="ShaneKing,sk@azolla.org" />
<link rel="shortcut icon" href="/img/favicon.png" />
<link href="/3th/ProwerV4/style.css" rel="stylesheet" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<div id="logo">
				<a href="http://shaneking.org">ShaneKing</a>
			</div>
		</div>
		<div id="descr">
			<ul>
				<li>
					<a id="rss_icon" href="http://shaneking.org">RSS Feed</a>
				</li>
			</ul>
			有梦才有精彩！
		</div>
		<div id="content">
			<div id="nopage">
				<h1>500</h1>
				<div id="search">
					<form id="searchform" method="get" action="http://shaneking.org">
						<input type="text" value="" name="s" id="s" size="30">
						<button type="submit">搜索</button>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/footer.html"></jsp:include>
	</div>
</body>
</html>