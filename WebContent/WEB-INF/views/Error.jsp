<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
   body {
			font-family:KaiTi;
		}
</style>
<body>
	<h2 style="display:block;text-align:center"><font size="32px"><b><i>ERROR!</i></b></font></h2>
	<p style="display:block;text-align:center"><span><font color="red" size="5px">${requestScope.error}</font></span></p>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);" style="display:block;text-align:center">返回上一页</a>
</body>
</html>