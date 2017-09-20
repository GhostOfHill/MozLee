<%@page contentType="text/html; charset=UTF-8"%>
<footer class="main-footer">
  <div class="pull-right hidden-xs">
    longhe.shen@ipole.com.cn
  </div>
  <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
</footer>
<!-- 添加栏的背景。这个div必须放控制栏后立即 -->
<div class="control-sidebar-bg"></div>
<script src="/scripts/jquery-2.2.3.min.js"></script>
<script src="/scripts/bootstrap.min.js"></script>
<script src="/scripts/app.min.js"></script>
<!-- 可以添加slimscroll和FastClick插件。这两个插件都建议提高用户体验。slimscroll时需要使用的固定布局. -->

 <script>
 	$(function(){
 		$(".sidebar-menu a").click(function(e){
 			e.preventDefault();
 			$("#mainFrame").attr("src",$(this).attr("href"));
 		});
 	});
 </script>