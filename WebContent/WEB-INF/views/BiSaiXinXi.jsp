<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.match.springmvc.entities.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>河北金融学院学科竞赛管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Fonts -->
	<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
	<!-- CSS Libs -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/font-awesome.min.css">
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
		if(window.confirm('你确定要删除"'+del+'"号队伍吗？')){
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
							<a data-toggle="collapse" href="#dropdown-element"> <span class="title">学科竞赛</span> </a> 
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
					<div class="description">在此界面查看、统计、管理历年学科竞赛情况</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="card">
							<div class="card">
								<div class="card-header">
									<div class="card-title">
										<div class="title">
											<c:if test="${!empty requestScope.compinfo }">${compinfo.compname }</c:if>
										</div>
									</div>
								</div>
							</div>
							<div class="card-body no-padding">
								<div role="tabpanel">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs" role="tablist">
										<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">竞赛基本信息</a></li>
										<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">参赛队伍信息</a></li>
										<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">参赛学生信息</a></li>
										<li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">指导教师信息</a></li>
									</ul>
									
									<!-- Tab panes -->
									
									<!-- 程序片注释 -->
									<%-- <%
										Match match=(Match)request.getAttribute("match");
									%> --%>
									<c:if test="${!empty requestScope.compinfo }">
									<div class="tab-content"><!-- 开始 -->
									
										<!-- 竞赛基本信息模块 -->
										<div role="tabpanel" class="tab-pane active" id="home">
											<div>
												<ul class="list-group">
													<!-- 注释1 -->
													<li class="list-group-item">竞赛名称：<a>${compinfo.compname }</a></li>
													<li class="list-group-item">申办单位：<a>${compinfo.sponsor }</a></li>
													<li class="list-group-item">参赛人数：<a>${compinfo.studentNum } </a></li>
													<li class="list-group-item">参赛队伍数：<a>${compinfo.teamNum } </a></li>
													<li class="list-group-item">指导教师数：<a>${compinfo.teacherNum } </a></li>
													<li class="list-group-item">立项时间：${compinfo.starttime }</li>
													<li class="list-group-item">结项时间：${compinfo.endtime }</li>																		
													<li class="list-group-item">备注：${compinfo.remark }</li>
													<!-- <button type="button" class="btn btn-info">导出该比赛套表</button> -->
													<%-- <form action="${pageContext.request.contextPath }/competition/TzdXiuGaiJingSai" method="post"> --%>
														<%-- <input type="hidden" name="Compid" value=${compinfo.compid }> --%>													
														<!-- <button type="submit" class="btn btn-warning">修改比赛信息</button> -->
													<!-- </form> -->
												</ul>
												<a href="${pageContext.request.contextPath }/competition/TzdXiuGaiJingSai?Id=${compinfo.id }" class="btn btn-warning">修改竞赛信息</a>
											</div>
										</div>
										
										<!-- 参赛队伍信息模块 -->
										<div role="tabpanel" class="tab-pane" id="profile">
											<div class="card-body">
												<table class="datatable table table-striped" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th>队伍编号</th>
															<th>参赛学生</th>
															<th>指导教师</th>
															<th>获奖等级</th>
															<th>校内认定</th>
															<th>操作</th>
														</tr>
													</thead>
													
													<tfoot>
														<tr>
															<th>队伍编号</th>
															<th>参赛学生</th>
															<th>指导教师</th>
															<th>获奖等级</th>
															<th>校内认定</th>
															<th>操作</th>
														</tr>
													</tfoot>
											
													<!-- 注释程序片  -->
													<%--<% 
													List<UpdataTem> tems=match.getTems();
													if(tems!=null&&tems.size()>0){
													%>--%>
													<tbody>
													<%--<%
													for(UpdataTem tem: tems){
													%>--%>													
													<c:if test="${!empty requestScope.citeamlist }">
													<c:forEach items="${requestScope.citeamlist}" var="citeamlist">																											
														<tr>														
															<td>${citeamlist.teamid }</td>														
															<td>${citeamlist.smens }</td>
															<td>${citeamlist.tmens }</td>
															<td>${citeamlist.awlevel }</td>
															<td>${citeamlist.sclevel }</td>
															<%-- <td><a href="${pageContext.request.contextPath }/team/TzXiuGaiTeam?Teamid=${citeamlist.teamid }">修改</a></td> --%>
															<td><a href="${pageContext.request.contextPath }/team/TzdDeleteTeam?Teamid=${citeamlist.teamid }&Id=${compinfo.id }" onClick="return delete_confirm('${citeamlist.teamid }')">删除</a></td>
														</tr>																										
													</c:forEach>
													</c:if>
													<%--<%
													 }
													%>--%>
													</tbody>
													<%--<%
													}
													%>--%>
												</table>
											</div>
										</div>
										
										<!-- 参赛学生信息模块 -->
										<div role="tabpanel" class="tab-pane" id="messages">
											<div class="card-body">
												<table class="datatable table table-striped" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th>序号</th>
															<th>学号</th>
															<th>姓名</th>
															<th>院系</th>
															<th>班级</th>
															<th>电话</th>
															<th>操作</th>
														</tr>
													</thead>
													
													<tfoot>
														<tr>
															<th>序号</th>
															<th>学号</th>
															<th>姓名</th>
															<th>院系</th>
															<th>班级</th>
															<th>电话</th>
															<th>操作</th>
														</tr>
													</tfoot> 
													
													<!-- 注释程序片 -->
													<%--<%
													List<UpdataStudent> students=match.getStudents();
													if(tems!=null&&tems.size()>0){
													%>--%>
													<%--<%
													for(UpdataStudent student:students){
													%>--%>
													<% 
														int i=1;																			
													%>
													<tbody>
													<c:if test="${!empty requestScope.cistulist }">
													<c:forEach items="${requestScope.cistulist}" var="cistulist">
														<tr>
															<td> <%--<%=student.getId() %> --%><%=i %></td>
															<td> <%--<%=student.getStudentid() %> --%>${cistulist.stuid }</td>
															<td> <%--<%=student.getName() %> --%>${cistulist.stuname }</td>
															<td> <%-- <%=student.getDepartment() %>--%>${cistulist.studepartment }</td>
															<td>${cistulist.stuclass }</td>
															<td> <%-- <%=student.getTele() %>--%>${cistulist.stutele }</td>
															<td><a href="${pageContext.request.contextPath }/student/TzXiuGaiStuInfo?Stuid=${cistulist.stuid }">查看/修改</a></td>
														</tr>
														<%i=i+1; %>
													</c:forEach>
													</c:if>
														<%--<%
														}
														%>--%>
													</tbody>													
													<%--<% 
													}
													%>--%>
														
												</table>
											</div>
										</div>
										
										<!-- 指导教师信息模块 -->
										<div role="tabpanel" class="tab-pane" id="settings">
											<div class="card-body">
												<table class="datatable table table-striped" cellspacing="0" width="100%">
													<thead>
														<tr>
															<th>序号</th>
															<th>教师工号</th>
															<th>教师姓名</th>
															<th>所在单位</th>
															<th>教师电话</th>
															<th>操作</th>
														</tr>
													</thead>
													
													<tfoot>
														<tr>
															<th>序号</th>
															<th>教师工号</th>
															<th>教师姓名</th>
															<th>所在单位</th>
															<th>教师电话</th>
															<th>操作</th>
															
														</tr>
													</tfoot>
													
													<!-- 注释程序片 --><%-- --%>
													<%--<%
													List<UpdataTeacher> teachers=match.getTeachers();
													if(tems!=null&&tems.size()>0){
													%>--%>
													<% 
														int j=1;																			
													%>
													<tbody>
													<%--<%
													for(UpdataTeacher teacher:teachers){
													%>--%>
													<c:if test="${!empty requestScope.citrlist }">
													<c:forEach items="${requestScope.citrlist}" var="citrlist">
														<tr>
															<td><%=j %></td>
															<td>${citrlist.trid }</td>
															<td>${citrlist.trname }</td>
															<td>${citrlist.trdepartment }</td>
															<td>${citrlist.trtele }</td>
															<td><a href="${pageContext.request.contextPath }/teacher/TzXiuGaiTrInfo?Trid=${citrlist.trid }">查看/修改</a></td>
														</tr>
														<%j=j+1; %>
													</c:forEach>
													</c:if>
													<%--<%
													}
													%>--%>
													</tbody>
													<%--<%
													}
													%>--%>
												</table>
											</div>
										</div>
									</div><!-- 结束 -->
									</c:if>
								</div>
							</div>
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
	<div><span class="icon fa fa-desktop"></span> 
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
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/alert.js"></script> 
	</div>
</div>
</body>
</html>