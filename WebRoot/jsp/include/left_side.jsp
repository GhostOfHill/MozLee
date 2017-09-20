<%@page contentType="text/html; charset=UTF-8"%>
<aside class="main-sidebar">
   <section class="sidebar">
     <!-- 搜索形式（可选） -->
     <form action="#" method="get" class="sidebar-form">
       <div class="input-group">
         <input type="text" name="q" class="form-control" placeholder="Search...">
             <span class="input-group-btn">
               <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
               </button>
             </span>
       </div>
     </form>

     <!-- 侧边栏菜单-->
     <ul class="sidebar-menu">
       <li class="header">HEADER</li>
       <!-- 可选地，可以添加图标到链接中 -->
       <li class="treeview active">
         <a href="#"><i class="fa fa-link"></i> <span>HTML</span>
           <span class="pull-right-container">
             <i class="fa fa-angle-left pull-right"></i>
           </span>
         </a>
         <ul class="treeview-menu">
           <li><a href="/jsp/layout/panel.jsp">panel</a></li>
           <li><a href="/jsp/layout/button.jsp">button</a></li>
         </ul>
       </li>
     </ul>
   </section>
 </aside>