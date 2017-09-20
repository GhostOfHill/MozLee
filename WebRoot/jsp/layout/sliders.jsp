<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  <%--   <base href="<%=basePath%>"> --%>
    <title>slider滑块</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="/resources/css/ionicons.min.css">
	<link rel="stylesheet" href="/resources/css/ion.rangeSlider.css">
	<link rel="stylesheet" href="/resources/css/ion.rangeSlider.skinNice.css">
	<link rel="stylesheet" href="/resources/css/slider.css">
	<link rel="stylesheet" href="/resources/css/AdminLTE.css">
	<link rel="stylesheet" href="/resources/css/skins/skin-blue.min.css">
  </head>
  <body>
	<div class="container-fluid">
		 <div class="row">
	        <div class="col-xs-12">
	          <div class="box box-primary">
	            <div class="box-header">
	              <h3 class="box-title">离子滑块</h3>
	            </div>
	            <!-- /.box-header -->
	            <div class="box-body">
	              <div class="row margin">
	                <div class="col-sm-6">
	                  <input id="range_1" type="text" name="range_1" value="">
	                </div>
	
	                <div class="col-sm-6">
	                  <input id="range_2" type="text" name="range_2" value="1000;100000" data-type="double" data-step="500" data-postfix=" &euro;" data-from="30000" data-to="90000" data-hasgrid="true">
	                </div>
	              </div>
	              <div class="row margin">
	                <div class="col-sm-6">
	                  <input id="range_5" type="text" name="range_5" value="">
	                </div>
	                <div class="col-sm-6">
	                  <input id="range_6" type="text" name="range_6" value="">
	                </div>
	              </div>
	              <div class="row margin">
	                <div class="col-sm-12">
	                  <input id="range_4" type="text" name="range_4" value="10000;100000">
	                </div>
	              </div>
	            </div>
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>
	        <!-- /.col -->
	      </div>
	      <!-- /.row -->
	
	      <div class="row">
	        <div class="col-xs-12">
	          <div class="box box-primary">
	            <div class="box-header">
	              <h3 class="box-title">引导滑块</h3>
	            </div>
	            <!-- /.box-header -->
	            <div class="box-body">
	              <div class="row margin">
	                <div class="col-sm-6">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="red">
	
	                  <p>data-slider-id="red"</p>
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="blue">
	
	                  <p>data-slider-id="blue"</p>
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="green">
	
	                  <p>data-slider-id="green"</p>
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="yellow">
	
	                  <p>data-slider-id="yellow"</p>
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="aqua">
	
	                  <p>data-slider-id="aqua"</p>
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="horizontal" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="purple">
	
	                  <p style="margin-top: 10px">data-slider-id="purple"</p>
	                </div>
	                <div class="col-sm-6 text-center">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="vertical" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="red">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="vertical" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="blue">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="vertical" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="green">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="vertical" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="yellow">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="vertical" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="aqua">
	                  <input type="text" value="" class="slider form-control" data-slider-min="-200" data-slider-max="200" data-slider-step="5" data-slider-value="[-100,100]" data-slider-orientation="vertical" data-slider-selection="before" data-slider-tooltip="show" data-slider-id="purple">
	                </div>
	              </div>
	            </div>
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>
	        <!-- /.col -->
	      </div>
	      <!-- /.row -->
 	</div>
  <script src="/scripts/jquery-2.2.3.min.js"></script>
  <script src="/scripts/bootstrap.min.js"></script>
  <script src="/scripts/app.min.js"></script>
  <script src="/scripts/ion.rangeSlider.min.js"></script>
  <script src="/scripts/bootstrap-slider.js"></script>
<script>
  $(function () {
    /* BOOTSTRAP SLIDER */
    $('.slider').slider();

    /* ION SLIDER */
    $("#range_1").ionRangeSlider({
      min: 0,
      max: 5000,
      from: 1000,
      to: 4000,
      type: 'double',
      step: 1,
      prefix: "$",
      prettify: false,
      hasGrid: true
    });
    $("#range_2").ionRangeSlider();

    $("#range_5").ionRangeSlider({
      min: 0,
      max: 10,
      type: 'single',
      step: 0.1,
      postfix: " mm",
      prettify: false,
      hasGrid: true
    });
    $("#range_6").ionRangeSlider({
      min: -50,
      max: 50,
      from: 0,
      type: 'single',
      step: 1,
      postfix: "°",
      prettify: false,
      hasGrid: true
    });

    $("#range_4").ionRangeSlider({
      type: "single",
      step: 100,
      postfix: " light years",
      from: 55000,
      hideMinMax: true,
      hideFromTo: false
    });
    $("#range_3").ionRangeSlider({
      type: "double",
      postfix: " miles",
      step: 10000,
      from: 25000000,
      to: 35000000,
      onChange: function (obj) {
        var t = "";
        for (var prop in obj) {
          t += prop + ": " + obj[prop] + "\r\n";
        }
        $("#result").html(t);
      },
      onLoad: function (obj) {
        //
      }
    });
    
  });
</script>
</body>
</html>

  
  
  
  