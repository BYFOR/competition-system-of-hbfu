<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>河北金融学院学科竞赛管理系统-竞赛管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1" content="text/html; charset=UTF-8">

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

</head>

<body class="flat-blue">
<div class="app-container">
	<div class="row content-container">
		<nav class="navbar navbar-default navbar-fixed-top navbar-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-expand-toggle"> <i class="fa fa-bars icon"></i></button>
					<ol class="breadcrumb navbar-breadcrumb">
						<li>学科竞赛</li>
						<li class="active">竞赛管理</li>
					</ol>
					<button type="button" class="navbar-right-expand-toggle pull-right visible-xs"> <i class="fa fa-th icon"></i></button>
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
		<div class="side-menu sidebar-inverse">
			<nav class="navbar navbar-default" role="navigation">
				<div class="side-menu-container">
					<div class="navbar-header"> <a class="navbar-brand" href="#">
						<div class="icon fa fa-paper-plane"></div>
						<div class="title">导航栏</div>
						</a>
						<button type="button" class="navbar-expand-toggle pull-right visible-xs"> <i class="fa fa-times icon"></i></button>
					</div>
					<ul class="nav navbar-nav">
						<li> <a href="${pageContext.request.contextPath }/admin/TzMain"> <span class="icon fa fa-tachometer"></span><span class="title">仪表盘</span> </a> </li>
						<li class="active panel panel-default dropdown"><!-- 学科竞赛 --> 
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
						<li class="panel panel-default dropdown"> <a data-toggle="collapse" href="#dropdown-table"> <span class="icon fa fa-table"></span><span class="title">学生管理</span></a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-table" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/student/TzXueShengGuanLi">学生管理</a></li>
										<li><a href="${pageContext.request.contextPath }/student/TzdXueShengChaXun">学生查询</a></li>
									</ul>
								</div>
							</div>
						</li>
						<li class="panel panel-default dropdown"> <a data-toggle="collapse" href="#dropdown-form"> <span class="icon fa fa-file-text-o"></span><span class="title">教师管理</span></a> 
							<!-- Dropdown level 1 -->
							<div id="dropdown-form" class="panel-collapse collapse">
								<div class="panel-body">
									<ul class="nav navbar-nav">
										<li><a href="${pageContext.request.contextPath }/teacher/TzJiaoShiGuanLi">教师管理</a></li>
										<li><a href="${pageContext.request.contextPath }/teacher/TzdJiaoShiChaXun">教师查询</a></li>
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
			<div class="side-body">
				<div class="page-title"> <span class="title">学科竞赛管理</span>
					<div class="description">在此界面查看奖金比例</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="card">
							<div class="card-header">
								<div class="card-title">
									<div class="title">奖金比例列表</div>
								</div>
							</div>
							<!-- 注释：JSTL标签 -->
							<c:choose>
								<c:when test="${requestScope.status=='all' }">
							<c:if test="${empty requestScope.allbonusscalelist}">
								<h4 style="display:block;text-align:center">没有奖金比例!</h4>
							</c:if>
							
							<c:if test="${!empty requestScope.allbonusscalelist }">
							<div class="card-body">
								<table class="datatable table table-striped" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>序号</th>
											<th>最少人数</th>
											<th>最多人数</th>
											<th>奖金比例</th>	
											<th>操作</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>序号</th>
											<th>最少人数</th>
											<th>最多人数</th>
											<th>奖金比例</th>	
											<th>操作</th>												
										</tr>
									</tfoot>
									<tbody>
										<!-- 注释 JSTL 循环 -->
										<c:forEach items="${requestScope.allbonusscalelist}" var="allbslist">
										<tr>
											<td>${allbslist.id }</td>
											<td>${allbslist.minnumber }人</td>
											<td>${allbslist.maxnumber }人</td>
											<td>${allbslist.scale }</td>
											<td><a href="${pageContext.request.contextPath }/bonusscale/TzXiuGaiBonusscale?Id=${allbslist.id }">修改</a></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</c:if>
								</c:when>
							
							
								<c:when test="${requestScope.status=='single' }">
							<c:if test="${empty requestScope.competitionlist}">
								<h4 style="display:block;text-align:center">没有该竞赛信息!</h4>
							</c:if>
							
							<c:if test="${!empty requestScope.competitionlist }">
							<div class="card-body">
								<table class="datatable table table-striped" cellspacing="0" width="100%">
									<thead>
										<tr>
											<th>编号</th>
											<th>比赛名称</th>
											<th>申报单位</th>
											<th>立项时间</th>
											<th>结项时间</th>
											<th>备注</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>编号</th>
											<th>比赛名称</th>
											<th>申报单位</th>
											<th>立项时间</th>
											<th>结项时间</th>
											<th>备注</th>
										</tr>
									</tfoot>
									<tbody>
										<!-- 注释 JSTL 循环 -->
										<c:forEach items="${requestScope.competitionlist}" var="complist">
										<tr>
											<td>${complist.compid }</td>
											 <%-- <a href="matchXinXi.do?matchNM=<%=matchResult.getMatchNM()%>"><%=matchResult.getMatchNM() %></a> --%> 
											<td><a href="${pageContext.request.contextPath }/competition/TzdBiSaiXinXi?Id=${complist.id }">${complist.compname }</a></td>
											<td>${complist.sponsor }</td>
											<td>${complist.starttime }</td>
											<td>${complist.endtime }</td>
											<td>${complist.remark }</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</c:if>
								</c:when>
							</c:choose>
							<!-- 注释：JSTL标签  结束-->
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Main Content --> 
	</div>
	
	<footer class="app-footer">
		<div class="wrapper"> <span class="pull-right">2.1 <a href="#"><i class="fa fa-long-arrow-up"></i></a></span> © 2015 Copyright. </div>
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