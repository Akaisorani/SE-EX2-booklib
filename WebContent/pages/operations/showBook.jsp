<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            +request.getServerName() + ":" + request.getServerPort()
            + path +"/";
%>
<base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>图书管理系统 | 书籍信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bower_components/Ionicons/css/ionicons.min.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="index.jsp" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini">LI<b>B</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>图书</b>管理系统</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">DATA</li>

       <li class="treeview">
          <a href="javascript:void(0);">
            <i class="fa fa-table"></i> <span>书库</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="index.jsp"><i class="fa fa-circle-o"></i> 图书查询</a></li>
            <li><a href="pages/tables/benziTable.jsp"><i class="fa fa-circle-o"></i> 画册查询</a></li>
          </ul>
        </li>

        <li class="header">OPERATION</li>
        <li><a href="pages/operations/addBook.jsp"><i class="fa fa-circle-o text-red"></i> <span>添加新书籍</span></a></li>
        <li><a href="pages/operations/addAuthor.jsp"><i class="fa fa-circle-o text-yellow"></i> <span>添加作者</span></a></li>
        <li hidden=true><a href="pages/operations/showBook.jsp"><i class="fa fa-circle-o text-aqua"></i> <span>更新书籍信息</span></a></li>
        <li><a href="pages/operations/addBenzi.jsp"><i class="fa fa-circle-o text-red"></i> <span>添加新画册</span></a></li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>图书查询</h1>
      <ol class="breadcrumb">
        <li><a href="index.jsp"><i class="fa fa-dashboard"></i> 图书管理系统</a></li>
        <li class="active">书籍信息</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">书籍信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- box-body -->
              <div class="box-body">
              <div class="col-xs-10">
                <form class="form-horizontal">
                  <div class="form-group">
                    <label for="ISBN" class="col-sm-2 control-label">ISBN</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="ISBN"><s:property value="ISBN"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="Title" class="col-sm-2 control-label">标题</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Title"><s:property value="Title"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="Author" class="col-sm-2 control-label">作者</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Author"><s:property value="Author"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="Publisher" class="col-sm-2 control-label">出版社</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Publisher"><s:property value="Publisher"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="PublishDate" class="col-sm-2 control-label">出版日期</label>

                    <div class="col-sm-10">                      
                        <p class="form-control-static" id="PublishDate"><s:property value="PublishDate"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="Price" class="col-sm-2 control-label">价格</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Price"><s:property value="Price"/></p>
                    </div>
                  </div>
                </form>
                </div>
                <!-- update book button  -->
                <div class="col-sm-2 row-xs-2">
                <a class="btn btn-app pull-right" href="updBookPreLoad?ISBN=<s:property value="ISBN"/>">
                <i class="fa fa-edit"></i>更新
                </a>
                </div>
               </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
      	  <div class="box">
            <div class="box-header">
              <h3 class="box-title">作者信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- box-body -->
              <div class="box-body">
                <form class="form-horizontal">
                  <div class="form-group">
                    <label for="Author" class="col-sm-2 control-label">姓名</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Author"><s:property value="Author"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="Age" class="col-sm-2 control-label">年龄</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Age"><s:property value="Age"/></p>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="Country" class="col-sm-2 control-label">国籍</label>

                    <div class="col-sm-10">
                      <p class="form-control-static" id="Country"><s:property value="Country"/></p>
                    </div>
                  </div>

                </form>
               </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 0.1.0
    </div>
    <strong>图书管理系统</strong>
  </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- page script -->
<script src="dist/js/dataBook.js"></script>
<!-- date picker -->
<script src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
//Date picker
	$('#inputPublishDate').datepicker({
	  autoclose: true,
	  format : "yyyy-mm-dd"
	})
</script>
</body>
</html>
