$(function(){
/*
*遍历侧边栏
*/
 // alert('123');
  $.getJSON('json/menu.json',function(res){
    
	  var showlist = $('<ul class="sidebar-menu"></ul>');
	  $(".sidebar").append(showlist);	
    showall(res, showlist,true);	
	  //res请求的json数据,parent父级ul
    function showall(res, parent,flag) {
   		 var a = flag ? "fa fa-link" : "fa fa-circle-o";
       for (var i in res) {
          if (res[i].children.length > 0) {
              var li = $('<li><a href="'+res[i].url+'"><i class="'+a+'"></i><span>'+res[i].name+'</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a></li>');          
              $(li).append('<ul class="treeview-menu"></ul>').appendTo(parent);
              showall(res[i].children, $(li).children().eq(1),false);
          }else {
              $('<li><a href="'+res[i].url+'"><i class="'+a+'"></i><span>'+res[i].name+'</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a></li>').appendTo(parent);
          }
       }
    };
	});	
  
});







