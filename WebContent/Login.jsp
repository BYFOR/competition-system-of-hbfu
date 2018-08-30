<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>河北金融学院学科竞赛查询系统</title>
</head>
	<style type="text/css">
		body {
			margin: 0;
			padding: 0;
			background-color: #69F;
			font-family:KaiTi;
		}
		
		.main {
			text-align: center;
			background-color: #FFF;
			border-radius: 20px;
			width: 600px;
			height: 450px;
			position: absolute;
			left: 50%;
			top: 50%;
			transform: translate(-50%, -50%);
		}
		
		.h2title {
			margin-top: 15%
		}
		
		.loginform {
			margin-top: 15%;
		}
		
		.blue {
			background-color: #6CF;
			border-radius: 10px;
			width: 80px;
			height: 30px;
		}
		a{text-decoration:none; color:black;}
	</style>
	
	<script type="text/javascript">
		function validation() {
		    var no = document.getElementById('no').value;          
		    var pwd = document.getElementById('pwd').value;    
		    if(no == '') {
		        alert('请输入您的工号!');
		        return false;
		    }	 
			if(pwd == '') {
		        alert('请输入您的密码!');
		        return false;
		    }
			if(no.length>=6){
				if(pwd.length>=6){
					return true;
				}else{
					alert('密码长度至少为6位!');
			        return false;
				}
			}else{
				alert('工号长度至少为6位!');
		        return false;
			}
		}
	</script>
<body>
	<div class="main">
		<h2 class="h2title"><font size="6px">河北金融学院学科竞赛查询系统</font></h2>	
	   <span><font color="red" size="5px"><b>管 理 员 登 录 页</b></font></span>
		<form action="admin/login" method="post" class="loginform" onsubmit='return validation();'>
			<font>教师工号:</font><input type="text" name="Adid"  id='no' /><br><br>
			<br> 密&nbsp;&nbsp;&nbsp;&nbsp;码:<input type="password" name="Adpwd" id='pwd' /> <br><br>
			<br> <input type="submit" value="登录" class="blue" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%-- <a href="${pageContext.request.contextPath }/admin/Tzreg">注册</a> --%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath }/users/Tzuserlogin">用户登录</a>
		</form>
		<br>
		<br>
	</div>
</body>
</html>