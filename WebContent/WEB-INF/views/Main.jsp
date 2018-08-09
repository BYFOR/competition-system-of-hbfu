<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>河北金融学院学科竞赛管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
	<!-- Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
	<!-- CSS Libs -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/animate.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap-switch.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/checkbox3.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/dataTables.bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/select2.min.css">
	<!-- CSS App -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/themes/flat-blue.css">
	<!-- 确认是否删除 -->
	<script type="text/javascript">
	function delete_confirm(del) {
		/* var d = $("#d").val(); */
		if(window.confirm('你确定要删除"'+del+'"吗？')){
			return true;
	    }else{
	    	return false;
	    }
	}
	</script>
</head>

<body class="flat-blue">
<div class="app-container">
	<div class="row content-container">
		<nav class="navbar navbar-default navbar-fixed-top navbar-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-expand-toggle"> <i class="fa fa-bars icon"></i> </button>
					<ol class="breadcrumb navbar-breadcrumb">
						<li class="active" >河北金融学院学科竞赛管理系统</li>
					</ol>
					<button type="button" class="navbar-right-expand-toggle pull-right visible-xs"> <i class="fa fa-th icon"></i> </button>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<ol class="breadcrumb navbar-breadcrumb">
						<li class="active" style="font-size:15px">
							<c:choose>
								<c:when test="${!empty sessionScope.adminname }">
									工号：${sessionScope.adminname }
								</c:when>
								<c:otherwise>
									<c:if test="${!empty sessionScope.username }">工号：${sessionScope.username }</c:if>
								</c:otherwise>																
							</c:choose>
						</li>
					</ol>
				</ul>
			</div>
		</nav>
		<!-- 顶部导航栏 -->
		<div class="side-menu sidebar-inverse">
			<nav class="navbar navbar-default" role="navigation">
				<div class="side-menu-container">
					<div class="navbar-header"> <a class="navbar-brand" href="#">
						<div class="icon fa fa-paper-plane"></div>
						<div class="title">导航栏</div>
						</a>
						<button type="button" class="navbar-expand-toggle pull-right visible-xs"> <i class="fa fa-times icon"></i> </button>
					</div>
					<ul class="nav navbar-nav">
						<li class="active"> <a href="${pageContext.request.contextPath }/admin/TzMain"> <span class="icon fa fa-tachometer"></span><span class="title">仪表盘</span> </a></li>
						<li class="panel panel-default dropdown"><!-- 学科竞赛 --> 
							<a data-toggle="collapse" href="#dropdown-element"> <span class="icon fa fa-desktop"></span><span class="title">学科竞赛</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-element" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/admin/TzJingSaiGuanLi">竞赛管理</a> </li>
										<li><a href="${pageContext.request.contextPath }/admin/TzXinjianJingSai">新建竞赛</a> </li>
										<li><a href="${pageContext.request.contextPath }/admin/TzJingSaiChaXun">竞赛查询</a> </li>
										<li><a href="${pageContext.request.contextPath }/criteria/TzCriteriaGuaiLi">评判标准</a></li>
										<li><a href="${pageContext.request.contextPath }/bonusscale/TzBonusscaleGuanLi">奖金比例</a></li>
									</ul>
								</div>
							</div>
						</li>
						<li class="panel panel-default dropdown"> 
							<a data-toggle="collapse" href="#dropdown-table"> <span class="icon fa fa-table"></span><span class="title">学生管理</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-table" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/student/TzXueShengGuanLi">学生管理</a> </li>
										<li><a href="${pageContext.request.contextPath }/student/TzdXueShengChaXun">学生查询</a> </li>
									</ul>
								</div>
							</div>
						</li>
						<li class="panel panel-default dropdown"> 
							<a data-toggle="collapse" href="#dropdown-form"> <span class="icon fa fa-file-text-o"></span><span class="title">教师管理</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-form" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/teacher/TzJiaoShiGuanLi">教师管理</a> </li>
										<li><a href="${pageContext.request.contextPath }/teacher/TzdJiaoShiChaXun">教师查询</a> </li>
									</ul>
								</div>
							</div>
						</li>
						<!-- 队伍管理 -->
						<li class="panel panel-default dropdown"><!-- 队伍管理 --> 
							<a data-toggle="collapse" href="#dropdown-team"> <span class="icon fa fa-desktop"></span><span class="title">队伍管理</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-team" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/team/TzdTeamInfoImport">导入管理</a> </li>
										<li><a href="${pageContext.request.contextPath }/team/TzTeamChaXun">队伍查询</a> </li>
									</ul>
								</div>
							</div>
						</li>
						<!-- 队伍管理 END -->
						<!-- 导出管理 -->
						<li class="panel panel-default dropdown"><!-- 导出管理 --> 
							<a data-toggle="collapse" href="#dropdown-export"> <span class="icon fa fa-table"></span><span class="title">导出管理</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-export" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/export/TzExportGuanLi">导出管理</a> </li>
										<li><a href="${pageContext.request.contextPath }/export/TzExportChaXun">导出查询</a> </li>
									</ul>
								</div>
							</div>
						</li>
						<!-- 导出管理 END -->
						<!-- 管理员管理 -->
						<li class="panel panel-default dropdown"> 
							<a data-toggle="collapse" href="#dropdown-admin"> <span class="icon fa fa-file-text-o"></span><span class="title">管理员管理</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-admin" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/admin/TzChaKanUsers">查看用户</a> </li>
										<li><a href="${pageContext.request.contextPath }/admin/ZhuXiaoDengLu">注销登录</a> </li>
										<li><a href="${pageContext.request.contextPath }/admin/TzXiuGaiPwd">修改密码</a> </li>
									</ul>
								</div>
							</div>
						</li>												
						<!-- 管理员管理结束 -->	
						<!-- 用户操作 -->
						<li class="panel panel-default dropdown"><!-- 用户操作 --> 
							<a data-toggle="collapse" href="#dropdown-users"> <span class="icon fa fa-desktop"></span><span class="title">用户操作</span> </a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-users" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/users/DTzExportChaXun">导出查询（系）</a> </li>
										<li><a href="${pageContext.request.contextPath }/users/UZhuXiaoDengLu">注销登录</a> </li>									
									</ul>
								</div>
							</div>
						</li>
						<!-- 用户操作 END -->											
					</ul>
				</div>
				<!-- /.navbar-collapse --> 
			</nav>
		</div>
		<div class="container-fluid">
			<div class="side-body padding-top">
				<div class="row">
					<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="${pageContext.request.contextPath }/admin/TzJingSaiChaXun">
						<div class="card red summary-inline">
							<div class="card-body"> <i class="icon fa fa-inbox fa-4x"></i> <span class="content">
								<div class="title" style="font-size:30px">竞赛查询</div>
								</span>
								<div class="clear-both"></div>
							</div>
						</div>
						</a> </div>
					<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="${pageContext.request.contextPath }/student/TzdXueShengChaXun">
						<div class="card yellow summary-inline">
							<div class="card-body"> <i class="icon fa fa-comments fa-4x"></i> <span class="content">
								<div class="title" style="font-size:30px">学生查询</div>
								</span>
								<div class="clear-both"></div>
							</div>
						</div>
						</a> </div>
					<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="${pageContext.request.contextPath }/teacher/TzdJiaoShiChaXun">
						<div class="card green summary-inline">
							<div class="card-body"> <i class="icon fa fa-tags fa-4x"></i> <span class="content">
								<div class="title" style="font-size:30px">教师查询</div>
								</span>
								<div class="clear-both"></div>
							</div>
						</div>
						</a> </div>
					<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12"> <a href="${pageContext.request.contextPath }/admin/TzXinjianJingSai">
						<div class="card blue summary-inline">
							<div class="card-body"> <i class="icon fa fa-share-alt fa-4x"></i> <span class="content">
								<div class="title" style="font-size:30px">新建竞赛</div>
								</span>
								<div class="clear-both"></div>
							</div>
						</div>
						</a> </div>
				</div>
				<div class="row  no-margin-bottom">
					<div class="row">
						<div class="col-xs-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">
										<div class="title">近期操作记录</div>
									</div>
								</div>
								<div class="card-body">
									<table class="datatable table table-striped" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>竞赛编号</th>
												<th>比赛名称</th>
												<th>申报单位</th>
												<th>结项时间</th>
												<th>操作名称</th>
												<th>操作名称</th>
												
											</tr>
										</thead>
										<tfoot>
											<tr>
												<th>竞赛编号</th>
												<th>比赛名称</th>
												<th>申报单位</th>
												<th>结项时间</th>
												<th>操作名称</th>
												<th>操作名称</th>
												
											</tr>
										</tfoot>
										<tbody>
										<c:forEach items="${requestScope.allcomp}" var="allcomp">
											<tr>
												<td>${allcomp.compid }</td>
												<td>${allcomp.compname }</td>
												<td>${allcomp.sponsor }</td>
												<td>${allcomp.endtime }</td>
												<td><a href="${pageContext.request.contextPath }/export/TzdExportGuanLi?Compid=${allcomp.compid }" >导出管理</a></td>
												<td><a href="${pageContext.request.contextPath }/competition/ShanChuJingSai?Compid=${allcomp.compid }" onClick="return delete_confirm('${allcomp.compname }');">删除</a></td>
											</tr>
										</c:forEach>											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 左侧导航栏 --> 
	</div>
	
	<footer class="app-footer">
		<div class="wrapper"> <span class="pull-right">2.1 <a href="#"><i class="fa fa-long-arrow-up"></i></a></span>河北金融学院学科竞赛管理系统</div>
	</footer>
	
	<div> 
		<!-- Javascript Libs --> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/Chart.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/bootstrap-switch.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery.matchHeight-min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/jquery.dataTables.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/dataTables.bootstrap.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/select2.full.min.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/ace/ace.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/ace/mode-html.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/style/js/ace/theme-github.js"></script> 
		<!-- Javascript --> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/app.js"></script> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/index.js"></script> 
	</div>
</div>
</body>
</html>