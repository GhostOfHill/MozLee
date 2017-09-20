<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <%--   <base href="<%=basePath%>"> --%>
    <title>面板</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="/resources/css/ionicons.min.css">
	<link rel="stylesheet" href="/resources/css/AdminLTE.css">
	<link rel="stylesheet" href="/resources/css/skins/skin-blue.min.css">
  </head>
  <body>
	  <div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
				  <div class="box box-default collapsed-box">
					<div class="box-header with-border">
					  <h3 class="box-title">可扩大的panel</h3>
					  <div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i>
						</button>
					  </div>
					</div>
					<div class="box-body" style="display: none;">
					  内容区
					</div>
				  </div>
				</div>
				<div class="col-md-3">
				  <div class="box box-warning">
					<div class="box-header with-border">
					  <h3 class="box-title">可折叠的panel</h3>
					  <div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
					  </div>
					</div>
					<div class="box-body">
					  内容区
					</div>
				  </div>
				</div>
				<div class="col-md-3">
				  <div class="box box-success">
					<div class="box-header with-border">
					  <h3 class="box-title">可移除的panel</h3>
					  <div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					  </div>
					</div>
					<div class="box-body">
					  内容区
					</div>
				  </div>
				</div>		
				<div class="col-md-3">
				  <div class="box box-danger">
					<div class="box-header">
					  <h3 class="box-title">加载状态的panel</h3>
					</div>
					<div class="box-body">
					  内容区
					</div>
					<!-- 加载（删除以下以停止加载）-->
					<div class="overlay">
					  <i class="fa fa-refresh fa-spin"></i>
					</div>
				  </div>
				</div>
	      </div>
			<div class="row">
				<div class="col-md-3">
				  <div class="box box-default box-solid collapsed-box">
					<div class="box-header with-border">
					  <h3 class="box-title">可扩大的panel</h3>
					  <div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i>
						</button>
					  </div>
					</div>
					<div class="box-body" style="display: none;">
					  内容区
					</div>
				  </div>
				</div>
				<div class="col-md-3">
				  <div class="box box-warning box-solid">
					<div class="box-header with-border">
					  <h3 class="box-title">可折叠的panel</h3>
					  <div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
					  </div>
					</div>
					<div class="box-body">
					  内容区
					</div>
				  </div>
				</div>
				<div class="col-md-3">
				  <div class="box box-success box-solid">
					<div class="box-header with-border">
					  <h3 class="box-title">可移除的panel</h3>
					  <div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					  </div>
					</div>
					<div class="box-body">
					  内容区
					</div>
				  </div>
				</div>    
				<div class="col-md-3">
	          <div class="box box-danger box-solid">
	            <div class="box-header">
	              <h3 class="box-title">加载状态的panel</h3>
	            </div>
	            <div class="box-body">
	              内容区
	            </div>
	            <!-- 加载（删除以下以停止加载）-->
	            <div class="overlay">
	              <i class="fa fa-refresh fa-spin"></i>
	            </div>
	          </div>
	        </div>
	      </div>
			<div class="row">
				<div class="col-md-3">
				  <div class="box box-primary direct-chat direct-chat-primary">
					<div class="box-header with-border">
					  <h3 class="box-title">Direct Chat</h3>
					  <div class="box-tools pull-right">
						<span data-toggle="tooltip" title="" class="badge bg-light-blue" data-original-title="3 New Messages">3</span>
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-widget="chat-pane-toggle" data-original-title="Contacts">
						  <i class="fa fa-comments"></i></button>
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					  </div>
					</div>
					<div class="box-body">
					  <div class="direct-chat-messages">
						内容区
					  </div>
					  <div class="direct-chat-contacts">
					   contacts内容区
					  </div>
					</div>
					<div class="box-footer">
					</div>
				  </div>
				</div>
				<div class="col-md-3">
				  <div class="box box-warning direct-chat direct-chat-warning direct-chat-contacts-open">
					<div class="box-header with-border">
					  <h3 class="box-title">Direct Chat</h3>
					  <div class="box-tools pull-right">
						<span data-toggle="tooltip" title="" class="badge bg-yellow" data-original-title="3 New Messages">3</span>
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-widget="chat-pane-toggle" data-original-title="Contacts">
						  <i class="fa fa-comments"></i></button>
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					  </div>
					</div>
					<div class="box-body">
					  <div class="direct-chat-messages">
						内容区
					  </div>
					  <div class="direct-chat-contacts">
						contacts内容区
					  </div>
					</div>
					<div class="box-footer">
					</div>
				  </div>
				</div>
				<div class="col-md-3">
				  <div class="box box-success direct-chat direct-chat-success">
					<div class="box-header with-border">
					  <h3 class="box-title">Direct Chat</h3>
					  <div class="box-tools pull-right">
						<span data-toggle="tooltip" title="3 New Messages" class="badge bg-green">3</span>
						<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-widget="chat-pane-toggle" data-original-title="Contacts">
						  <i class="fa fa-comments"></i></button>
						<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
					  </div>
					</div>
					<div class="box-body">
					  <div class="direct-chat-messages">
						内容区
					  </div>
					  <div class="direct-chat-contacts">
						contacts内容区
					  </div>
					</div>
				  </div>
				</div>
				<div class="col-md-3">
					  <div class="box box-warning direct-chat direct-chat-warning direct-chat-contacts-open">
						<div class="box-header with-border">
						  <h3 class="box-title">Direct Chat</h3>
						  <div class="box-tools pull-right">
							<span data-toggle="tooltip" title="" class="badge bg-red" data-original-title="3 New Messages">3</span>
							<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
							</button>
							<button type="button" class="btn btn-box-tool" data-toggle="tooltip" title="" data-widget="chat-pane-toggle" data-original-title="Contacts">
							  <i class="fa fa-comments"></i></button>
							<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
						  </div>
						</div>
						<div class="box-body">
						  <div class="direct-chat-messages">
							内容区
						  </div>
						  <div class="direct-chat-contacts">
							contacts内容区
						  </div>
						</div>
					  </div>
					</div>
			  </div>
	 	</div>
  <script src="/scripts/jquery-2.2.3.min.js"></script>
  <script src="/scripts/bootstrap.min.js"></script>
  <script src="/scripts/app.min.js"></script>
</body>
</html>

  
  
  
  