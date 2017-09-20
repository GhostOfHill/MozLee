<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="jsp/login/css/style.css">
  </head>
  
  <body>
   <div id="login">
	    <div class="wrapper">
	        <div class="login">
	            <form action="#" method="post" class="container offset1 loginform">
	                <div id="owl-login">
	                    <div class="hand"></div>
	                    <div class="hand hand-r"></div>
	                    <div class="arms">
	                        <div class="arm"></div>
	                        <div class="arm arm-r"></div>
	                    </div>
	                </div>
	                <div class="pad">
	                    <input type="hidden" name="_csrf" value="9IAtUxV2CatyxHiK2LxzOsT6wtBE6h8BpzOmk=">
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="userName" class="control-label fa fa-envelope"></label>
	                            <input id="userName" type="input" name="userName" placeholder="用户名" tabindex="1" autofocus="autofocus" class="form-control input-medium">
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        <div class="controls">
<!-- 	                            <label for="password" class="control-label fa fa-asterisk"></label> -->
	                            <input id="password" type="password" name="password" placeholder="密码" tabindex="2" class="form-control input-medium">
	                        </div>
	                    </div>
	                </div>
	                <div class="form-actions">
	                    <button type="submit" tabindex="4" class="btn btn-primary">登录</button>
	                </div>
	            </form>
	        </div>
	    </div>
	</div>
  <script src="/scripts/jquery-2.2.3.min.js"></script>
  <script src="/scripts/ajax.js"></script>
  <script>
  $(function() {
      $('#login #password').focus(function() {
          $('#owl-login').addClass('password');
      }).blur(function() {
          $('#owl-login').removeClass('password');
      });
      
      $('.btn-primary').click(function(e){
    	  e.preventDefault();
    	  AJAX.Async("/loginController/login",{"userName":$("#userName").val(),"password":$("#password").val()},function(data){
			  data = stringToJson(data);
	    	  location.href = "/jsp/main.jsp?sysState="+data.sysState;
			  console.info(data);
    	  });
      });
      
      function stringToJson(stringValue) {
    		if (!isNull(stringValue)) {
    			try {
    				eval("var theJsonValue = " + stringValue);
    				return theJsonValue;
    			} catch (e) {
    			}
    		}
    		return "";
    	}
  });
  </script>
  </body>
</html>
