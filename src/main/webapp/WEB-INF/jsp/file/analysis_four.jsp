<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="com.megagao.production.ssm.controller.MyJFreechart.ColumnChartController


"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
String fileName=ColumnChartController.genLineChart(session);
System.out.println(fileName);

%>

	<img src="chart?filename=<%=fileName %>" width="700" height="500"
		border="0" />
</body>
</html>