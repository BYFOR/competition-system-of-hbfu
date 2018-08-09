<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>河北金融学院学科竞赛查询系统</title>
    <style type="text/css">
	  body{  
		margin:0;  
		padding:0;  
		background-color:#69F;    	
		font-family:KaiTi;		
	  } 
	  .main{
		text-align: center;
		background-color:#FFF;
		border-radius: 20px;
		width: 600px;
		height: 500px;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%,-50%);		
		}
		.h2title{ margin-top:20%}
		.loginform{ margin-top:12%;}
		.blue{ background-color:#6CF; border-radius: 10px; width:80px; height:35px; margin-top:3%}
  </style>
  <script type="text/javascript">
		$(document).ready(function(){
			var msg="";
			if($("#tishi").text!=null){
				msg = $("#tishi").text();
			}
			if(msg!=""){
				alert(msg);
			}
		})
  	 function validate() {
  	 		  var no = document.getElementById("no").value;
              var pwd1 = document.getElementById("pwd1").value;
              var pwd2 = document.getElementById("pwd2").value;
              var pwd2 = document.getElementById("pwd2").value;
              var department = document.getElementById("department").value;
              if(no.length==6){              
	              if(pwd1 == pwd2) {
	              	  document.getElementById("submit").disabled = false;
	                  document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
	                  /* document.getElementById("submit").disabled = false; */
	                  if(pwd1.length==6&&pwd2.length==6){
	                  	 if(department.length == 0){
		                  	alert("请输入所在部门!");
		                  	return false;	                  	 		                  	 
	                  	 }else{
		                  	return true;	              	                  	 
	                  	 }
	                  }else{
	                  	alert("密码长度必须为6位!");
	                  	return false;
	                  }
	                 
	              }
	              else {
	                  alert("两次密码不同!");
	                  /* document.getElementById("submit").disabled = true; */
	                  return false;
	              }
	            }else{
	            	alert("工号长度必须为6位!");
	                return false;
	            }
          }
  </script>
</head>

<body>
	<div class="main">
        <h2 class="h2title"><font size="6px">河北金融学院学科竞赛查询系统</font></h2>
        <form action="${pageContext.request.contextPath }/users/register" method="post" class="loginform" onsubmit='return validate();'>
           <font size="3">请输入您的工号:</font>
            <input type="text" name="Usid" id="no" /><br /><br />
           <font size="3">请输入您的密码:</font>
            <input type="password" name="Uspwd" id="pwd1" /><br /><br />
           <font size="3">请再次输入密码:</font>
            <input type="password" name="Uspwd2" id="pwd2"/><br /><br />
            <font size="3">请输入所在部门:</font> 
            <select name="Usdepartment" id="department">
				<optgroup label="申办单位">
					<option value="金融系"> 金融系</option>
					<option value="会计系"> 会计系</option>
					<option value="经贸系"> 经贸系</option>
					<option value="管理系"> 管理系</option>
					<option value="国际教育学院"> 国际教育学院</option>
					<option value="国际金融服务外包学院"> 国际金融服务外包学院</option>
					<option value="信管系"> 信管系</option>
					<option value="商务外语系"> 商务外语系</option>
					<option value="保险系"> 保险系</option>
					<option value="法律系"> 法律系</option>
					<option value="校团委"> 校团委</option>
					<option value="基础课教学部"> 基础课教学部</option>
					<option value="公共外语教学部"> 公共外语教学部</option>
					<option value="研究生部"> 研究生部</option>
					<option value="学生处"> 学生处</option>
				</optgroup>
			</select>
			<br></br>
            <span id="tishi"></span>
            <br></br>
           <input type="submit" value="注册" class="blue" id="submit" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <input type="reset" value="重置" class="blue" />
       </form>
   </div>
</body>
</html>