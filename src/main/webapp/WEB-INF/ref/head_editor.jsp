<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:if test="${jsp_title != null && jsp_title.trim() != '' && jsp_title != 'Index'}">${jsp_title} - </c:if>${applicationScope.CONFIG_MAP["ROC_TITLE"]}</title>
    <meta name="keywords" content="${applicationScope.KEYWORDS}"/>
    <meta name="Description" content="${applicationScope.CONFIG_MAP["ROC_DESC"]}"/>
    <meta name="Author" content="${applicationScope.CONFIG_MAP["ROC_EMAIL"]}"/>
    <link rel="shortcut icon" href="/img/favicon.png"/>
    <!-- Bootstrap -->
    <link href="/3th/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/3th/bootcss/css/docs.min.css" rel="stylesheet"/>
    <link href="/3th/select2/css/select2.min.css" rel="stylesheet"/>
    <link href="/3th/simditor/styles/simditor.css" rel="stylesheet"/>
    <link href="/3th/simditor/styles/simditor-emoji.css" rel="stylesheet"/>
    <link href="/3th/simditor/styles/simditor-html.css" media="screen" rel="stylesheet"/>
    <link href="/css/roc.css" rel="stylesheet"/>
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
