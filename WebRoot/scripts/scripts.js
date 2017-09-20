function supportstorage() {
	if (typeof window.localStorage=='object') 
		return true;
	else
		return false;		
}

function handleSaveLayout() {
	var e = $(".demo").html();
	if (!stopsave && e != window.demoHtml) {
		stopsave++;
		window.demoHtml = e;
		saveLayout();
		stopsave--;
	}
}

var layouthistory; 
function saveLayout(){
	var data = layouthistory;
	if (!data) {
		data={};
		data.count = 0;
		data.list = [];
	}
	if (data.list.length>data.count) {
		for (i=data.count;i<data.list.length;i++)
			data.list[i]=null;
	}
	data.list[data.count] = window.demoHtml;
	data.count++;
	if (supportstorage()) {
		localStorage.setItem("layoutdata",JSON.stringify(data));
	}
	layouthistory = data;
	//console.log(data);
	/*$.ajax({  
		type: "POST",  
		url: "/build/saveLayout",  
		data: { layout: $('.demo').html() },  
		success: function(data) {
			//updateButtonsVisibility();
		}
	});*/
}

function downloadLayout(){
	
	$.ajax({  
		type: "POST",  
		url: "/build/downloadLayout",  
		data: { layout: $('#download-layout').html() },  
		success: function(data) { window.location.href = '/build/download'; }
	});
}

function downloadHtmlLayout(){
	$.ajax({  
		type: "POST",  
		url: "/build/downloadLayout",  
		data: { layout: $('#download-layout').html() },  
		success: function(data) { window.location.href = '/build/downloadHtml'; }
	});
}

function undoLayout() {
	var data = layouthistory;
	//console.log(data);
	if (data) {
		if (data.count<2) return false;
		window.demoHtml = data.list[data.count-2];
		data.count--;
		$('.demo').html(window.demoHtml);
		if (supportstorage()) {
			localStorage.setItem("layoutdata",JSON.stringify(data));
		}
		return true;
	}
	return false;
	/*$.ajax({  
		type: "POST",  
		url: "/build/getPreviousLayout",  
		data: { },  
		success: function(data) {
			undoOperation(data);
		}
	});*/
}

function redoLayout() {
	var data = layouthistory;
	if (data) {
		if (data.list[data.count]) {
			window.demoHtml = data.list[data.count];
			data.count++;
			$('.demo').html(window.demoHtml);
			if (supportstorage()) {
				localStorage.setItem("layoutdata",JSON.stringify(data));
			}
			return true;
		}
	}
	return false;
	/*
	$.ajax({  
		type: "POST",  
		url: "/build/getPreviousLayout",  
		data: { },  
		success: function(data) {
			redoOperation(data);
		}
	});*/
}

function handleJsIds() {
	handleModalIds();
	handleAccordionIds();
	handleCarouselIds();
	handleTabsIds()
}
function handleAccordionIds() {
	var e = $(".demo #myAccordion");
	var t = randomNumber();
	var n = "accordion-" + t;
	var r;
	e.attr("id", n);
	e.find(".accordion-group").each(function(e, t) {
		r = "accordion-element-" + randomNumber();
		$(t).find(".accordion-toggle").each(function(e, t) {
			$(t).attr("data-parent", "#" + n);
			$(t).attr("href", "#" + r)
		});
		$(t).find(".accordion-body").each(function(e, t) {
			$(t).attr("id", r)
		})
	})
}
function handleCarouselIds() {
	var e = $(".demo #myCarousel");
	var t = randomNumber();
	var n = "carousel-" + t;
	e.attr("id", n);
	e.find(".carousel-indicators li").each(function(e, t) {
		$(t).attr("data-target", "#" + n)
	});
	e.find(".left").attr("href", "#" + n);
	e.find(".right").attr("href", "#" + n)
}
function handleModalIds() {
	var e = $(".demo #myModalLink");
	var t = randomNumber();
	var n = "modal-container-" + t;
	var r = "modal-" + t;
	e.attr("id", r);
	e.attr("href", "#" + n);
	e.next().attr("id", n)
}
function handleTabsIds() {
	var e = $(".demo #myTabs");
	var t = randomNumber();
	var n = "tabs-" + t;
	e.attr("id", n);
	e.find(".tab-pane").each(function(e, t) {
		var n = $(t).attr("id");
		var r = "panel-" + randomNumber();
		$(t).attr("id", r);
		$(t).parent().parent().find("a[href=#" + n + "]").attr("href", "#" + r)
	})
}

function randomNumber() {
	return randomFromInterval(1, 1e6)
}
function randomFromInterval(e, t) {
	return Math.floor(Math.random() * (t - e + 1) + e)
}
/**
 * 		input输入框 keyup事件 
 * */
function gridSystemGenerator() {
	$(".lyrow .preview input").bind("keyup", function() {
		var e = 0;
		var t = "";
		var n = $(this).val().split(" ", 12);
		$.each(n, function(n, r) {
			e = e + parseInt(r);
			t += '<div class="col-md-' + r + ' column"></div>'
		});
		if (e == 12) {
			$(this).parent().next().children().html(t);
			$(this).parent().prev().show()
		} else {
			$(this).parent().prev().hide()
		}
	})
}
/**
*	组件样式编写方法
*/
function configurationElm(e, t) {
	$(".demo").delegate(".configuration > a", "click", function(e) {
		e.preventDefault();
		var t = $(this).parent().next().next().children();			//
		console.log(t);
		$(this).toggleClass("active");
		t.toggleClass($(this).attr("rel"))
	});
	
	$(".demo").delegate(".configuration .dropdown-menu a", "click", function(e) {
		e.preventDefault();
		var t = $(this).parent().parent();											//.dropdown-menu
	//	var n = t.parent().parent().next().next().children();
		var classFlag = t.parent().parent().next().next().children().find('.flag').length;			//view
		console.log(classFlag);
		if(classFlag) {
			console.log(111);
			var n = t.parent().parent().next().next().find('.flag');
		}else{
			console.log(000);
			var n = t.parent().parent().next().next().children();
		}
		
		t.find("li").removeClass("active");
		$(this).parent().addClass("active");
		var r = "";
		t.find("a").each(function() {
			r += $(this).attr("rel") + " "
		});
		console.log(r);
		t.parent().removeClass("open");
		n.removeClass(r);
		n.addClass($(this).attr("rel"))
	});
	
	$(".demo").delegate("#tabOption a", "click", function(e) {
		var menu = $("#tabOption");
		var goal = $(".goal").find('li');				//目标元素
		var str = '';						//定义空字符串存放class
		menu.find('a').each(function(){		//遍历所有选项的的class存起来
			str += $(this).attr('rel')+' '
		});
		console.log(str);
		
		goal.removeClass(str);
		goal.addClass($(this).attr("rel"));

	});
	
	
	
}

/**
*	删除布局
*/
function removeElm() {
	$(".demo").delegate(".remove", "click", function(e) {
		e.preventDefault();
		$(this).parent().remove();
		if (!$(".demo .lyrow").length > 0) {
			clearDemo()
		}
	})
}
function clearDemo() {
	$(".demo").empty();
	layouthistory = null;
	if (supportstorage())
		localStorage.removeItem("layoutdata");
}
function removeMenuClasses() {
	$("#menu-layoutit li button").removeClass("active")
}

//替换contaniner-fluid 为 contaniner
/*function changeStructure(e, t) {
	$("#download-layout ." + e).removeClass(e).addClass(t)
}*/

function cleanHtml(e) {
	$(e).parent().append($(e).children().html());
}
/**
 * 		生成代码方法
 * */
function downloadLayoutSrc() {
	var e = "";
	$("#download-layout").children().html($(".demo").html());		
	var t = $("#download-layout").children();						//container
	t.find(".preview, .configuration, .drag, .remove").remove();	//去掉按钮等其他内容
	t.find(".lyrow").addClass("removeClean");						//
	t.find(".box-element").addClass("removeClean");
	t.find(".lyrow .lyrow .lyrow .lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	 
	t.find(".lyrow .lyrow .lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".lyrow .removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".removeClean").each(function() {
		cleanHtml(this)
	});
	t.find(".removeClean").remove();
	$("#download-layout .column").removeClass("ui-sortable");
	$("#download-layout .row").removeClass("clearfix").children().removeClass("column");
	//---lw
/*	if ($("#download-layout .container").length > 0) {
		changeStructure("row", "row")
	}*/
	
	formatSrc = $.htmlClean($("#download-layout").html(), {
		format: true,
		allowedAttributes: [
			["id"],
			["class"],
			["data-toggle"],
			["data-target"],
			["data-parent"],
			["role"],
			["data-dismiss"],
			["aria-labelledby"],
			["aria-hidden"],
			["data-slide-to"],
			["data-slide"]
		]
	});
	$("#download-layout").html(formatSrc);
	$("#downloadModal textarea").empty();
	$("#downloadModal textarea").val(formatSrc)
	
}

var currentDocument = null;
var timerSave = 1000;
var stopsave = 0;
var startdrag = 0;
var demoHtml = $(".demo").html();
var currenteditor = null;
$(window).resize(function() {
	$("body").css("min-height", $(window).height() - 90);
	$(".demo").css("min-height", $(window).height() - 160);
});

function restoreData(){
	if (supportstorage()) {
		layouthistory = JSON.parse(localStorage.getItem("layoutdata"));
		if (!layouthistory) return false;
		window.demoHtml = layouthistory.list[layouthistory.count-1];
		if (window.demoHtml) $(".demo").html(window.demoHtml);
	}
}

/**
*	栅格布局排序方法
*/
function initContainer(){
	$(".demo, .demo .column").sortable({
		connectWith: ".column",
		opacity: .35,
		handle: ".drag",
		start: function(e,t) {
			if (!startdrag) stopsave++;
			startdrag = 1;
		//	console.info("栅格布局拖拽开始");
		},
		stop: function(e,t) {
			if(stopsave>0) stopsave--;
			startdrag = 0;
		//	console.info("栅格布局拖拽结束");
		}
	});
	configurationElm();
}

$(document).ready(function() {
	CKEDITOR.disableAutoInline = true;
	restoreData();
	
/**
 *		初始化富文本	 
 */
	
	var contenthandle = CKEDITOR.replace( 'contenteditor' ,{
		language: 'zh-cn',
		contentsCss: ['/resources/css/bootstrap.css'],
		allowedContent: true
	});
	$("body").css("min-height", $(window).height() - 90);
	$(".demo").css("min-height", $(window).height() - 160);
	
	$(".sidebar-nav .lyrow").draggable({		//--布局拖拽方法
		connectToSortable: ".demo",		//被拖拽元素放入的对象
		helper: "clone",				//创建自身的一个克隆用于拖拽
		handle: ".drag",				//限制只能在拖拽元素内的指定元素开始拖拽
		start: function(e,t) {			//鼠标开始拖拽时
			if (!startdrag) stopsave++; 
			startdrag = 1;
		},
		drag: function(e, t) {			//当鼠标拖拽移动时
			//debugger
			t.helper.width(400);		//被拖拽的元素
		},
		stop: function(e, t) {			//当鼠标松开时
			$(".demo .column").sortable({	//排序
				opacity: .35,
				connectWith: ".column",
				start: function(e,t) {
					if (!startdrag) stopsave++;
					startdrag = 1;
				},
				stop: function(e,t) {
					if(stopsave>0) stopsave--;
					startdrag = 0;
					
				}
			});
			if(stopsave>0) stopsave--;
			startdrag = 0;
		}
	});
	
	$(".sidebar-nav .wrap").draggable({		//--组件拖拽方法
		connectToSortable: ".column",
		helper: "clone",
		handle: ".drag",
		start: function(e,t) {
			if (!startdrag) stopsave++;
			startdrag = 1;
		},
		drag: function(e, t) {
			t.helper.width(400)
		},
		stop: function() {
			handleJsIds();
			if(stopsave>0) stopsave--;
			startdrag = 0;
			console.info($(".select2:visible").length);
			$(".select2:visible").select2();
		}
	});
	initContainer();
/**
*	组件--编辑按钮
*/	
	$('body.edit .demo').on("click","[data-target=#editorModal]",function(e) {
		e.preventDefault();
		currenteditor = $(this).parent().parent().find('.view');	//获取view			
		var falgAttr = currenteditor.hasClass('falg');			//标识是否是面板 true是 false否
		if(falgAttr) {	
			var clone = currenteditor.children().clone(true);		//copy box			
			clone.children(".box-body").remove();
			var eText = clone.html();
			console.log(eText);
			contenthandle.setData(eText);
		
		}else{
			
			var eText = currenteditor.html();
			console.log(eText);
			contenthandle.setData(eText);
		}
		
	});
		
/**
*	组件--保存按钮
*/
	$("#savecontent").click(function(e) {
		e.preventDefault();
		var falgAttr = currenteditor.hasClass('falg');			
		
		if(falgAttr) {	//面板
			var clone = currenteditor.children().clone(true);		//copy box		
			console.log(clone.parent().html());
			var boxBody = clone.children('.box-body');				
			clone.children(".box-header").remove();						//删除header	
			var boxHeader = contenthandle.getData();
			console.log(boxHeader);
			boxBody.before(boxHeader);
			currenteditor.html(clone);
			currenteditor.children('.box').siblings().remove();
			console.log(currenteditor.children('.box').siblings());
		}else{
			e.preventDefault();
			currenteditor.html(contenthandle.getData());
		}
	});
/**
*	生成方法
*/
	$("[data-target=#downloadModal]").click(function(e) {
		e.preventDefault();
		downloadLayoutSrc();
	});
/**
*	源码方法
*/
	$("[data-target=#sourceModal]").click(function(e) {
		e.preventDefault();
		$('#sourceeditor').val($(".demo").html());
	});
/**
*	可编辑源码保存方法
*/
	$("#savesource").click(function(){
		$('.demo').html($('#sourceeditor').val());
		initContainer();
		return;
	});
	//保存
	$("[data-target=#shareModal]").click(function(e) {
		e.preventDefault();
		handleSaveLayout();
	});
	
	$("#download").click(function() {
		downloadLayout();
		return false;
	});
	
	$("#downloadhtml").click(function() {
		downloadHtmlLayout();
		return false
	});
	
	$("#edit").click(function() {
		$("body").removeClass("devpreview sourcepreview");
		$("body").addClass("edit");
		removeMenuClasses();
		$(this).addClass("active");
		return false
	});
	
/**
*	清空方法
*/
	$("#clear").click(function(e) {
		e.preventDefault();
		clearDemo()
	});
/**
*	布局编辑
*/
	$("#devpreview").click(function() {
		$("body").removeClass("edit sourcepreview");
		$("body").addClass("devpreview");
		removeMenuClasses();
		$(this).addClass("active");
		return false
	});
	
/**
*	预览
*/
	$("#sourcepreview").click(function() {
		$("body").removeClass("edit");
		$("body").addClass("devpreview sourcepreview");
		removeMenuClasses();
		$(this).addClass("active");
		return false
	});
/**
*	生成内容里 自适应宽度按钮
*/
	$("#fluidPage").click(function(e) {
		e.preventDefault();
	//	changeStructure("container", "container-fluid");
		$("#fixedPage").removeClass("active");
		$(this).addClass("active");
		downloadLayoutSrc()
	});
/**
*	生成内容里 固定宽度按钮
*/
	$("#fixedPage").click(function(e) {
		e.preventDefault();
	//	changeStructure("container-fluid", "container");
		$("#fluidPage").removeClass("active");
		$(this).addClass("active");
		downloadLayoutSrc()
	});
	
	$(".nav-header").click(function() {
		$(".sidebar-nav .boxes, .sidebar-nav .rows").hide();
		$(this).next().slideDown()
	});
/**
*	撤销
*/
	$('#undo').click(function(){
		stopsave++;
		if (undoLayout()) initContainer();
		stopsave--;
	});
/**
*	重做
*/
	$('#redo').click(function(){
		stopsave++;
		if (redoLayout()) initContainer();
		stopsave--;
	});
	removeElm();
	gridSystemGenerator();
	setInterval(function() {
		handleSaveLayout()
	}, timerSave)
})