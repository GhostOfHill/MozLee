var AJAX = {};

/**
 * 用于保存已经请求过的地址及时间
 */
var requestAjaxUrl = {};

/**
 * 避免重复请求，同一请求地址在两秒种内重复发送请求
 * @param url 请求地址
 */
var _resubmitAD = function(url, data) {
	var requestTime = requestAjaxUrl[url];
	if(isNull(requestTime) || requestTime.DateDiff("s", new Date()) > 1) 
		return true;
	alert("请误重复提交数据！");
	return false;
};

/**
 * 向后台传递一个JSON数组，后台使用this.getModelList()接收
 * @param url 请求地址
 * @param data 请求数据
 * @param callback 请求成功回调方法
 */
AJAX.AsyncListdata = function(url,data,callback,load) {
	AJAX.Async(url,{modelList: json2str(data)},callback,load);
};

/**
 * 异步请求数据
 * @param url 请求地址
 * @param data 请求数据
 * @param callback 请求成功回调方法
 */
AJAX.Async = function(url,data,callback,load){
	if(_resubmitAD(url,data,load)) {
		if(load)
			$("body").append('<div class="modal-backdrop fade in" id="ajaxload" style="z-index:99999"><img title="/common/img/ajax-loaders/ajax-loader-7.gif" src="/common/img/ajax-loaders/ajax-loader-7.gif" style="position:absolute;left:46%;top:47%;"/></div>');
		$.ajax({
			type:"POST",
			url:encodeURI(url),
			data:data,
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			async:true,
			dataType:'html',
			traditional:true,
			error: function(data,transport){},
			success:function(msg){
				if(typeof callback == "function")
					callback(msg,data);
				if(load)
					$("#ajaxload").remove();
			}
		});
		
	}
};

/**
 * 不建意使用
 */
AJAX.String = function(url,data,getorpost,load){
	if(_resubmitAD(url, data,load)) {
		url = encodeURI(url);
		getorpost = getorpost||"POST";
		if(load)
			$("body").append('<div class="modal-backdrop fade in" id="ajaxload" style="z-index:99999"><img title="/common/img/ajax-loaders/ajax-loader-7.gif" src="/common/img/ajax-loaders/ajax-loader-7.gif" style="position:absolute;left:46%;top:47%;"/></div>');
		var result = "";
		$.ajax({
			type:getorpost,
			url:url,
			data:data,
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			async:false,
			dataType:'html',
			error: function(data,transport){ 
			},
			success:function(msg){
				result = msg;
			}
		});
		if(load)
			$("#ajaxload").remove();
		return result;
	}
	return "";
};

/**
 *	datatables 专用 post请求 
 */
AJAX.datatables = function(url,data,getorpost,start,end,load){
	if(_resubmitAD(url, data,load)) {
		url = encodeURI(url);
		getorpost = getorpost||"POST";
		if(getorpost == "POST"){
			if(url.split("?").length == 2){
				data  = {};//定义对象
				data['iDisplayStart'] = start;
				data['iDisplayLength'] = end;
				var arr = url.split("?")[1].split("&");
				for (var i = 0; i < arr.length; i++) {
					data[arr[i].split("=")[0]] = arr[i].split("=")[1];
				}
				url = url.split("?")[0];
			}
		}
		if(load)
			$("body").append('<div class="modal-backdrop fade in" id="ajaxload" style="z-index:99999"><img title="/common/img/ajax-loaders/ajax-loader-7.gif" src="/common/img/ajax-loaders/ajax-loader-7.gif" style="position:absolute;left:46%;top:47%;"/></div>');
		var result = "";
		$.ajax({
			type:getorpost,
			url:url,
			data:data,
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			async:false,
			dataType:'html',
			error: function(data,transport){ 
			},
			success:function(msg){
				result = msg;
			}
		});
		if(load)
			$("#ajaxload").remove();
		return result;
	}
	return "";
};


/**
 * 不建议使用
 * 向后台传递一个JSON数组，后台使用this.getModelList()接收
 */
AJAX.StringListdata = function(url,data,getorpost,load) {
	return AJAX.String(url,{modelList: json2str(data)},getorpost,load);
};

/**
 * 不建议使用
 */
AJAX.Html = function(url,data){
	if(_resubmitAD(url, data)) {
		var urlarr = url.split("?");
		urlarr[1] = urlarr[1] == undefined ? "" : urlarr[1].escapePar();
		url = encodeURI(urlarr[0]+"?"+urlarr[1]);
		var msghtml = "";
		$.ajax({
			type:'POST',
			url:url,
			data:data,
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			async:false,
			dataType:'html',
			error: function(data,transport){ 
				msghtml = "";
			},
			success:function(msg){
				msghtml = msg;
			}
		});
		return msghtml;
	}
};

/**
 * 已作废
 */
AJAX.getString = function(url,data,callback){
	var msg = AJAX.String(url,data);
	callback(msg);
};

/**
 * 已作废
 */
AJAX.getXml = function(url,callback){
	if(_resubmitAD(url, data)) {
		var urlarr = url.split("?");
		urlarr[1] = urlarr[1] == undefined ? "" : urlarr[1].escapePar();
		url = encodeURI(urlarr[0]+"?"+urlarr[1]);
		$.ajax({
			type:'POST',
			url:url,
			data:"",
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			async:false,
			cache : false,
			dataType:'xml',
			error: function(data,transport){ 
				window.location.href = "/common/jsp/error/error.html";
			},
			success:function(xml){
				callback(xml);
			}
		});
	}
};
/**
 * 已作废
 */
AJAX.getArray = function(url, callback){
	if(_resubmitAD(url, data)) {
		var urlarr = url.split("?");
		urlarr[1] = urlarr[1] == undefined ? "" : urlarr[1].escapePar();
		url = encodeURI(urlarr[0]+"?"+urlarr[1]);
		$.ajax({
			url : url,
			contentType:"application/x-www-form-urlencoded; charset=utf-8", 
			async:false,
			dataType : "xml",
			success : function(xml){
				var nodes = navigator.appName.indexOf("Microsoft")!=-1 ? xml.selectNodes("/*/*") : xml.firstChild.childNodes;
				var ret = [];
				for ( var i = 0; i < nodes.length; i++){
					var obj = {};
					var node = nodes[i];
					var attrs = node.attributes;
					for ( var j = 0; j < attrs.length; j++){
						var att = attrs[j];
						obj[att.name] = att.value;
					}
					ret.push(obj);
				}
				callback(ret);
			}
		});
	}
};

/**
 * 已作废
 */
AJAX.getJson = function(url,data){
	try{
		return  stringToJson(AJAX.String(url,data));
	}catch(e){
		alert('数据错误',"error");
	}
};


var isNull = function(obj) {
	if (obj === undefined || obj === "undefined" || obj === null || obj === "null" || obj === "" || obj === "{null}")
		return true;
	return false;
};