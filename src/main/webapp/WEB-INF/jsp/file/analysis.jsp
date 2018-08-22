<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>    
<%@ page import="org.jfree.data.general.DefaultPieDataset,org.jfree.chart.ChartFactory
,org.jfree.chart.JFreeChart,org.jfree.chart.servlet.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
     
		<div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="one()">显示饼图(纯jsp页面操作)</a>  
		</div> 
		<div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="two()">显示柱状图(纯jsp页面操作)</a>  
		</div>
		<div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="three()">显示折线图和柱状图结合(纯jsp页面操作)</a>  
		</div>
		<div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="four()">显示折线图(jsp页面配合java类)</a>  
		</div>
		<div id="one" class="easyui-window" title="显示饼图" 
	         data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'fileTest/analysisone'" 
	          style="width:65%;height:65%;padding:10px;">
	    
       </div>
       <div id="two" class="easyui-window" title="显示饼图" 
	         data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'fileTest/analysistwo'" 
	          style="width:65%;height:65%;padding:10px;">
	    
       </div>
        <div id="three" class="easyui-window" title="显示折线图和柱状图结合" 
	         data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'fileTest/analysisthree'" 
	          style="width:65%;height:65%;padding:10px;">
	    
       </div>
       <div id="four" class="easyui-window" title="显示折线图和柱状图结合" 
	         data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save',href:'fileTest/analysisfour'" 
	          style="width:65%;height:65%;padding:10px;">
	    
       </div>
       
	    <script type="text/javascript">
	    
	    function one(){
	    	$("#one").window("open");
	       		
	       	};
	    function two(){
		    	$("#two").window("open");
		       		
		     };
		function three(){
			    	$("#three").window("open");
			       		
		};
		function four(){
	    	$("#four").window("open");
	       		
};
	  
	  </script>
	 


</body>
</html>