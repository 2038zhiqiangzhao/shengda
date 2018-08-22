<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<link href="js/kindeditor-4.1.10/themes/default/default.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/common.js"></script>
<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<table class="easyui-datagrid" id="testlist" title="测试列表"
	data-options="singleSelect:false,collapsible:true,pagination:true,rownumbers:true,url:'test/list',
       	method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_test">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:100">测试编号</th>
			<th data-options="field:'name',align:'center',width:100">测试姓名</th>
			<th data-options="field:'age',align:'center',width:150">测试年龄</th>
			<th data-options="field:'url',align:'center',width:100">测试url</th>
			<th
				data-options="field:'isDeplyed',width:100,align:'center',formatter:formatpublishStatus">发布状态</th>
		</tr>
	</thead>
</table>
<!-- 导航栏 -->
<div id="toolbar_test"
	style="height: 22px; padding: 3px 11px; background: #fafafa;">

		<div style="float: left;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload"
				onclick="employee_reload()">刷新</a>
		</div>
		<div style="float: left;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-print"
				onclick="button_tempdown()">模板下载</a>
		</div>
		<div style="float: left;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-redo"
				onclick="button_import()">导入</a>
		</div>
		<div style="float: left;">
			<a href="#" class="easyui-linkbutton" plain="true" icon="icon-undo"
				onclick="button_export()">导出</a>
		</div>

		<div id="search_employee" style="float: right;">
			<input id="search_text_employee" class="easyui-searchbox"
				data-options="searcher:doSearch_employee,prompt:'请输入...',menu:'#menu_employee'"
				style="width: 250px; vertical-align: middle;"> </input>
			<div id="menu_employee" style="width: 120px">
				<div data-options="name:'employeeId'">员工编号</div>
				<div data-options="name:'employeeName'">员工名称</div>
				<div data-options="name:'departmentName'">部门名称</div>
			</div>
		</div>
</div>

<div id="employeeEditWindow" class="easyui-window" title="编辑员工"
	data-options="modal:true,
	closed:true,resizable:true,iconCls:'icon-save',href:'employee/edit'"
	style="width: 40%; height: 70%; padding: 10px;"></div>
<div id="employeeAddWindow" class="easyui-window" title="添加员工"
	data-options="modal:true,
	closed:true,resizable:true,iconCls:'icon-save',href:'employee/add'"
	style="width: 40%; height: 70%; padding: 10px;"></div>

<div id="empDepartmentInfo" class="easyui-dialog" title="部门信息"
	data-options="modal:true,
	closed:true,resizable:true,iconCls:'icon-save'"
	style="width: 65%; height: 65%; padding: 10px;">
	<form id="empDepartmentEditForm" method="post"
		,enctype="multipart/form-data">
		<input type="hidden" name="departmentId" />
		<table cellpadding="5">
			<tr>
				<td>部门名称:</td>
				<td><input class="easyui-textbox" type="text"
					name="departmentName" data-options="required:true" /></td>
			</tr>
			<tr>
				<td>部门职责:</td>
				<td><textarea
						style="width: 800px; height: 300px; visibility: hidden;"
						name="note"></textarea></td>
			</tr>
		</table>
	</form>
	<div style="padding: 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitEmpDepartmentEditForm()">提交</a>
	</div>
</div>
<div id="importwindow" class="easyui-window" title="导入文件"
	data-options="modal:true,closed:true,resizable:true,iconCls:'icon-save'"
	style="width: 30%; height: 18%; padding: 10px;">
	<div style="padding: 10px 10px 10px 10px">
		<form id="importAddForm" class="orderForm" method="post">
			<table cellpadding="5">

				<tr>
					<td>文件导入:</td>
					<td>
						<div id="importFileUploader">上传文件</div> <input type="hidden"
						id="importFile" name="file" />
					</td>
				</tr>

			</table>
			<input type="hidden" name="orderParams" />
		</form>
	</div>
</div>

<script type="text/javascript">
	//页面初始化完毕后执行此方法
	$(function() {
		//加载附件上传插件
		initImportFileUpload();

	});

	function doSearch_employee(value, name) { //用户输入用户名,点击搜素,触发此函数  
		if (value == null || value == '') {

			$("#testlist").datagrid({
				title : '员工列表',
				singleSelect : false,
				collapsible : true,
				pagination : true,
				rownumbers : true,
				method : 'get',
				nowrap : true,
				toolbar : "toolbar_test",
				url : 'test/list',
				method : 'get',
				loadMsg : '数据加载中......',
				fitColumns : true,//允许表格自动缩放,以适应父容器
				columns : [ [ {
					field : 'ck',
					checkbox : true
				}, {
					field : 'id',
					width : 100,
					align : 'center',
					title : '测试编号'
				}, {
					field : 'name',
					width : 100,
					align : 'center',
					title : '测试姓名'
				}, {
					field : 'age',
					width : 100,
					align : 'center',
					title : '测试年龄'
				}, {
					field : 'url',
					width : 100,
					align : 'center',
					title : '测试url'
				}, {
					field : 'isDeplyed',
					width : 150,
					title : '发布状态',
					align : 'center',
					formatter : formatpublishStatus
				} ] ],
			});
		} else {
			$("#testlist").datagrid(
					{
						title : '员工列表',
						singleSelect : false,
						collapsible : true,
						pagination : true,
						rownumbers : true,
						method : 'get',
						nowrap : true,
						toolbar : "toolbar_test",
						url : 'employee/search_employee_by_' + name
								+ '?searchValue=' + value,
						loadMsg : '数据加载中......',
						fitColumns : true,//允许表格自动缩放,以适应父容器
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'id',
							width : 100,
							align : 'center',
							title : '测试编号'
						}, {
							field : 'name',
							width : 100,
							align : 'center',
							title : '测试姓名'
						}, {
							field : 'age',
							width : 100,
							align : 'center',
							title : '测试年龄'
						}, {
							field : 'url',
							width : 100,
							align : 'center',
							title : '测试url'
						}, {
							field : 'isDeplyed',
							width : 150,
							title : '发布状态',
							align : 'center',
							formatter : formatpublishStatus
						} ] ],
					});
		}
	}

	//根据index拿到该行值
	function onEmployeeClickRow(index) {
		var rows = $('#testlist').datagrid('getRows');
		return rows[index];

	}

	function formatpublishStatus(value) {
		if (value == 1) {
			return '已发布';
		} else {
			return '未发布';
		}
	}

	/**导入功能*/
	function button_import() {
		$("#importwindow").window("open");

	}
	/**模板下载*/
	function button_tempdown() {
		$.messager.confirm('确认', '确认下载模板吗？', function(r) {
			if (r) {
				$.ajax({

					url : 'test/downloadTemplate',
					type : 'post',

					cache : false,
					data : {

					},
					contentType : "application/json; charset=utf-8",
					dataType : 'jsonp',
					jsonp : "callback",
					contentType : "application/json",
					success : function(data) {
						if (data.status == 200) {
							$.messager.alert('提示', '模板下载成功!', undefined,
									function() {

										$("#testlist").datagrid("reload");
									});
						} else {
							$.messager.alert('提示', '下载失败!', undefined,
									function() {

										$("#testlist").datagrid("reload");
									});
						}
					},
					error : function(data) {
						if (data.status == 200) {
							$.messager.alert('提示', '模板下载成功!', undefined,
									function() {

										$("#testlist").datagrid("reload");
									});
						} else {
							$.messager.alert('提示', '下载失败!', undefined,
									function() {

										$("#testlist").datagrid("reload");
									});
						}
					}
				});
				/*  	$.post("test/downloadTemplate", function(data){
				 		alert(data);
						if(data.status == 200){
							$.messager.alert('提示','模板成功!',undefined,function(){
								
								$("#testlist").datagrid("reload");
							});
						}else{
				          $.messager.alert('提示','下载失败!',undefined,function(){
								
								$("#testlist").datagrid("reload");
							});
						}
					}); */
			}
		});
	}
	//导出
	function button_export() {
		$.messager.confirm('确认', '确认导出所有数据吗？', function(r) {
			if (r) {
				var params = {};
				$.post("test/export", params, function(data) {
					if (data.status == 200) {
						$.messager.alert('提示', '导出成功!', undefined, function() {
							$("#testlist").datagrid("reload");
						});
					} else if (data.status = 102) {
						$.messager.alert('提示', '导出异常!', undefined, function() {
							$("#testlist").datagrid("reload");
						});
					} else if (data.status = 101) {
						$.messager.alert('提示', '文件模板不存在，请先下载模板！', undefined,
								function() {
									$("#testlist").datagrid("reload");
								});
					}
				});
			}
		});
	}
	function employee_reload() {
		$("#testlist").datagrid("reload");
	}
</script>