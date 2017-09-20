<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <%--   <base href="<%=basePath%>"> --%>
    <title>general</title>
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
	<style>
    .color-palette {
      height: 35px;
      line-height: 35px;
      text-align: center;
    }

    .color-palette-set {
      margin-bottom: 15px;
    }

    .color-palette span {
      display: none;
      font-size: 12px;
    }

    .color-palette:hover span {
      display: block;
    }

    .color-palette-box h4 {
      position: absolute;
      top: 100%;
      left: 25px;
      margin-top: -40px;
      color: rgba(255, 255, 255, 0.8);
      font-size: 12px;
      display: block;
      z-index: 7;
    }
  </style>
  </head>
  <body>
	  <div class="container-fluid">
		  <div class="box box-default color-palette-box">
	        <div class="box-header with-border">
	          <h3 class="box-title"><i class="fa fa-tag"></i> 调色板</h3>
	        </div>
	        <div class="box-body">
	          <div class="row">
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Primary</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-light-blue disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-light-blue color-palette"><span>#3c8dbc</span></div>
	                <div class="bg-light-blue-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Info</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-aqua disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-aqua color-palette"><span>#00c0ef</span></div>
	                <div class="bg-aqua-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Success</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-green disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-green color-palette"><span>#00a65a</span></div>
	                <div class="bg-green-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Warning</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-yellow disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-yellow color-palette"><span>#f39c12</span></div>
	                <div class="bg-yellow-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Danger</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-red disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-red color-palette"><span>#f56954</span></div>
	                <div class="bg-red-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Gray</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-gray disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-gray color-palette"><span>#d2d6de</span></div>
	                <div class="bg-gray-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	          </div>
	          <div class="row">
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Navy</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-navy disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-navy color-palette"><span>#001F3F</span></div>
	                <div class="bg-navy-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Teal</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-teal disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-teal color-palette"><span>#39CCCC</span></div>
	                <div class="bg-teal-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Purple</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-purple disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-purple color-palette"><span>#605ca8</span></div>
	                <div class="bg-purple-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Orange</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-orange disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-orange color-palette"><span>#ff851b</span></div>
	                <div class="bg-orange-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Maroon</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-maroon disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-maroon color-palette"><span>#D81B60</span></div>
	                <div class="bg-maroon-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	            <div class="col-sm-4 col-md-2">
	              <h4 class="text-center">Black</h4>
	
	              <div class="color-palette-set">
	                <div class="bg-black disabled color-palette"><span>Disabled</span></div>
	                <div class="bg-black color-palette"><span>#111111</span></div>
	                <div class="bg-black-active color-palette"><span>Active</span></div>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	  
	      <h2 class="page-header">Tabs</h2>
	      <div class="row">
	        <div class="col-md-6">
	        
	          <div class="nav-tabs-custom">
	            <ul class="nav nav-tabs">
	              <li class="active"><a href="#tab_1" data-toggle="tab" aria-expanded="true">Tab 1</a></li>
	              <li class=""><a href="#tab_2" data-toggle="tab" aria-expanded="false">Tab 2</a></li>
	              <li class=""><a href="#tab_3" data-toggle="tab" aria-expanded="false">Tab 3</a></li>
	              <li class="dropdown">
	                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
	                  Dropdown <span class="caret"></span>
	                </a>
	                <ul class="dropdown-menu">
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
	                  <li role="presentation" class="divider"></li>
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
	                </ul>
	              </li>
	              <li class="pull-right"><a href="#" class="text-muted"><i class="fa fa-gear"></i></a></li>
	            </ul>
	            <div class="tab-content">
	              <div class="tab-pane active" id="tab_1">
	                tab1
	              </div>
	              <div class="tab-pane" id="tab_2">
	                tab2
	              </div>
	              <div class="tab-pane" id="tab_3">
	              	tab3
	              </div>
	            </div>
	          </div>
	          
	          
	        </div>
	
	        <div class="col-md-6">
	          <div class="nav-tabs-custom">
	            <ul class="nav nav-tabs pull-right">
	              <li class="active"><a href="#tab_1-1" data-toggle="tab">Tab 1</a></li>
	              <li><a href="#tab_2-2" data-toggle="tab">Tab 2</a></li>
	              <li><a href="#tab_3-2" data-toggle="tab">Tab 3</a></li>
	              <li class="dropdown">
	                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
	                  Dropdown <span class="caret"></span>
	                </a>
	                <ul class="dropdown-menu">
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Action</a></li>
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another action</a></li>
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Something else here</a></li>
	                  <li role="presentation" class="divider"></li>
	                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Separated link</a></li>
	                </ul>
	              </li>
	              <li class="pull-left header"><i class="fa fa-th"></i> Custom Tabs</li>
	            </ul>
	            <div class="tab-content">
	              <div class="tab-pane active" id="tab_1-1">
	                tab1
	              </div>
	              <div class="tab-pane" id="tab_2-2">
	                tab2
	              </div>
	              <div class="tab-pane" id="tab_3-2">
	                tab3
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	      
	      <h2 class="page-header">进度条</h2>
	      <div class="row">
	        <div class="col-md-6">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <h3 class="box-title">进度条不同大小</h3>
	            </div>
	            <div class="box-body">
	              <p><code>.progress</code></p>
	
	              <div class="progress">
	                <div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
	                  <span class="sr-only">40% Complete (success)</span>
	                </div>
	              </div>
	              <p>Class: <code>.sm</code></p>
	
	              <div class="progress progress-sm active">
	                <div class="progress-bar progress-bar-success progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
	                  <span class="sr-only">20% Complete</span>
	                </div>
	              </div>
	              <p>Class: <code>.xs</code></p>
	
	              <div class="progress progress-xs">
	                <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
	                  <span class="sr-only">60% Complete (warning)</span>
	                </div>
	              </div>
	              <p>Class: <code>.xxs</code></p>
	
	              <div class="progress progress-xxs">
	                <div class="progress-bar progress-bar-danger progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
	                  <span class="sr-only">60% Complete (warning)</span>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	        <div class="col-md-6">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <h3 class="box-title">进度条</h3>
	            </div>
	            <div class="box-body">
	              <div class="progress">
	                <div class="progress-bar progress-bar-green" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
	                  <span class="sr-only">40% Complete (success)</span>
	                </div>
	              </div>
	              <div class="progress">
	                <div class="progress-bar progress-bar-aqua" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
	                  <span class="sr-only">20% Complete</span>
	                </div>
	              </div>
	              <div class="progress">
	                <div class="progress-bar progress-bar-yellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
	                  <span class="sr-only">60% Complete (warning)</span>
	                </div>
	              </div>
	              <div class="progress">
	                <div class="progress-bar progress-bar-red" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
	                  <span class="sr-only">80% Complete</span>
	                </div>
	              </div>
	            </div>       
	          </div>
	        </div>
	      </div>
	      <div class="row">
	        <div class="col-md-6">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <h3 class="box-title">不同尺寸的垂直进度条</h3>
	            </div>
	            <div class="box-body text-center">
	              <p>By adding the class <code>.vertical</code> and <code>.progress-sm</code>, <code>.progress-xs</code> or
	                <code>.progress-xxs</code> we achieve:</p>
	
	              <div class="progress vertical active">
	                <div class="progress-bar progress-bar-primary progress-bar-striped" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="height: 40%">
	                  <span class="sr-only">40%</span>
	                </div>
	              </div>
	              <div class="progress vertical progress-sm">
	                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="height: 100%">
	                  <span class="sr-only">100%</span>
	                </div>
	              </div>
	              <div class="progress vertical progress-xs">
	                <div class="progress-bar progress-bar-warning progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="height: 60%">
	                  <span class="sr-only">60%</span>
	                </div>
	              </div>
	              <div class="progress vertical progress-xxs">
	                <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="height: 60%">
	                  <span class="sr-only">60%</span>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	        <div class="col-md-6">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <h3 class="box-title">垂直进度条</h3>
	            </div>
	            <div class="box-body text-center">
	              <p>By adding the class <code>.vertical</code> we achieve:</p>
	
	              <div class="progress vertical">
	                <div class="progress-bar progress-bar-green" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="height: 40%">
	                  <span class="sr-only">40%</span>
	                </div>
	              </div>
	              <div class="progress vertical">
	                <div class="progress-bar progress-bar-aqua" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="height: 20%">
	                  <span class="sr-only">20%</span>
	                </div>
	              </div>
	              <div class="progress vertical">
	                <div class="progress-bar progress-bar-yellow" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="height: 60%">
	                  <span class="sr-only">60%</span>
	                </div>
	              </div>
	              <div class="progress vertical">
	                <div class="progress-bar progress-bar-red" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="height: 80%">
	                  <span class="sr-only">80%</span>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	
	      <h2 class="page-header">手风琴 & 旋转木马</h2>
	      <div class="row">
	        <div class="col-md-6">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <h3 class="box-title">可折叠的手风琴</h3>
	            </div>
	            <div class="box-body">
	              <div class="box-group" id="accordion">
	                <div class="panel box box-primary">
	                  <div class="box-header with-border">
	                    <h4 class="box-title">
	                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
	                        Collapsible Group Item #1
	                      </a>
	                    </h4>
	                  </div>
	                  <div id="collapseOne" class="panel-collapse collapse in">
	                    <div class="box-body">
	                      body1
	                    </div>
	                  </div>
	                </div>
	                <div class="panel box box-danger">
	                  <div class="box-header with-border">
	                    <h4 class="box-title">
	                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
	                        Collapsible Group Danger
	                      </a>
	                    </h4>
	                  </div>
	                  <div id="collapseTwo" class="panel-collapse collapse">
	                    <div class="box-body">
	                      body2
	                    </div>
	                  </div>
	                </div>
	                <div class="panel box box-success">
	                  <div class="box-header with-border">
	                    <h4 class="box-title">
	                      <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
	                        Collapsible Group Success
	                      </a>
	                    </h4>
	                  </div>
	                  <div id="collapseThree" class="panel-collapse collapse">
	                    <div class="box-body">
	                      body3
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	        <div class="col-md-6">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <h3 class="box-title">旋转木马</h3>
	            </div>
	            <div class="box-body">
	              <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	                <ol class="carousel-indicators">
	                  <li data-target="#carousel-example-generic" data-slide-to="0" class=""></li>
	                  <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
	                  <li data-target="#carousel-example-generic" data-slide-to="2" class=""></li>
	                </ol>
	                <div class="carousel-inner">
	                  <div class="item">
	                    <img src="http://placehold.it/900x500/39CCCC/ffffff&amp;text=I+Love+Bootstrap" alt="First slide">
	                    <div class="carousel-caption">
	                      First Slide
	                    </div>
	                  </div>
	                  <div class="item active">
	                    <img src="http://placehold.it/900x500/3c8dbc/ffffff&amp;text=I+Love+Bootstrap" alt="Second slide">
	                    <div class="carousel-caption">
	                      Second Slide
	                    </div>
	                  </div>
	                  <div class="item">
	                    <img src="http://placehold.it/900x500/f39c12/ffffff&amp;text=I+Love+Bootstrap" alt="Third slide">
	                    <div class="carousel-caption">
	                      Third Slide
	                    </div>
	                  </div>
	                </div>
	                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
	                  <span class="fa fa-angle-left"></span>
	                </a>
	                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
	                  <span class="fa fa-angle-right"></span>
	                </a>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	
	      <h2 class="page-header">排版</h2>
	      <div class="row">
	        <div class="col-md-12">
	          <div class="box box-solid">
	            <div class="box-header with-border">
	              <i class="fa fa-text-width"></i>
	
	              <h3 class="box-title">标题</h3>
	            </div>
	            <div class="box-body">
	              box-body
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

  
  
  
  