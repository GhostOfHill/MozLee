<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>MX后台管理系统</title>
	<meta http-equiv="Content-Type" content="application/vnd.wap.xhtml+xml;charset=utf-8" />
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
	<!-- Ionicons 2.0.1 -->
	<link rel="stylesheet" href="/resources/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="/resources/css/AdminLTE.css">
	<!-- AdminLTE 皮肤。默认为蓝色，也可以选择其他皮肤 skin-blue| skin-black| skin-purple| skin-yellow| skin-red| skin-green -->
    <link rel="stylesheet" href="/resources/css/skins/skin-blue.min.css">
</head>
<!--BODY TAG OPTIONS: LAYOUT OPTIONS | fixed | layout-boxed| layout-top-nav| sidebar-collapse| sidebar-mini -->
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
	  <!-- 头部-->
	  <jsp:include page="include/header.jsp"></jsp:include>
	  <!-- 左侧柱。包含标识和侧边栏 -->
	  <jsp:include page="include/left_side.jsp"></jsp:include>
	  <div class="content-wrapper">
	    <!-- 内容标题（页头） -->
	    <!-- <section class="content-header">
	      <h1>
	        Page Header
	        <small>Optional description</small>
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
	        <li class="active">Here</li>
	      </ol>
	    </section> -->
	    <!-- 内容 -->
	    <section class="content">
	    	<iframe id="mainFrame" style="width:100%;"></iframe>
	    </section>
	  </div>
	  <!-- 页脚 -->
	  <jsp:include page="include/footer.jsp"></jsp:include>
	</div>
</body>
</html>
