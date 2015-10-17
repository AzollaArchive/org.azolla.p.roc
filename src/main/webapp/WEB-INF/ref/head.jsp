<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:if test="${jsp_title != null && jsp_title.trim() != ''}">${jsp_title} - </c:if>${applicationScope.CONFIG_MAP["ROC_TITLE"]}</title>
    <meta name="keywords" content="${applicationScope.KEYWORDS}"/>
    <meta name="Description" content="${applicationScope.CONFIG_MAP["ROC_DESC"]}"/>
    <meta name="Author" content="${applicationScope.CONFIG_MAP["ROC_EMAIL"]}"/>
    <link rel="shortcut icon" href="/img/favicon.png"/>
    <!-- Bootstrap -->
    <link href="/3th/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/3th/bootcss/css/docs.min.css" rel="stylesheet"/>
    <link href="/3th/prettify/prettify.css" rel="stylesheet"/>
    <link href="/css/roc.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <script src="/bower_components/html5shiv/dist/html5shiv.min.js"></script>
    <script src="/bower_components/respond/dest/respond.min.js"></script>
    <![endif]-->
</head>