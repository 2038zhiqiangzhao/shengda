<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@page import="org.apache.shiro.session.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.megagao.production.ssm.listener.SessionCounter"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/commons/common_js.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/common_css.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盛大天地客户管理系统</title>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<style type="text/css">
.content {
	padding: 10px 10px 10px 10px;
}
.divNorth{
	background:url('image/TitleBackground.jpg') no-repeat center center;
	background-size:100% 100%;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:80px;padding:10px;background:url('./image/header_bg.png') no-repeat right;">
		<div  style="position: absolute;top:25px;left:600px">
		<span style="display:inline-block;font-size:20px;color:#5d98e8;margin:0 0 8px 0;">
		盛大天地客户运维信息系统
		</span><br/>
		</div>
		
		
		<div id="sessionInfoDiv"
			style="position: absolute;right: 5px;top:10px;">
			[<strong>${activeUser.username }</strong>]，欢迎你！
		</div>
		
		<div style="position: absolute; right: 5px; bottom: 10px; ">
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_pfMenu',iconCls:'icon-ok'">更换皮肤</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
		</div>
		<div id="layout_north_pfMenu" style="width: 120px; display: none;">
			<div onclick="changeTheme('default');">default</div>
			<div onclick="changeTheme('gray');">gray</div>
			<div onclick="changeTheme('black');">black</div>
			<div onclick="changeTheme('bootstrap');">bootstrap</div>
			<div onclick="changeTheme('metro');">metro</div>
		</div>
		<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
			<div onclick="editPassword();">修改密码</div>
			<div onclick="showAbout();">联系管理员</div>
			<div class="menu-sep"></div>
			<div onclick="logoutFun();">退出系统</div>
		</div>
	</div>
	
	
	
	
	
	
	<!-- <div data-options="region:'west',title:'功能菜单',split:true"
		style="width:213px;"> -->
	<div id="HomeFuncAccordion" class="easyui-accordion" style="width:213px;"
		data-options="region:'west',title:'功能菜单',split:false">
		<div title="功能搜索"
			data-options="iconCls:'icon-search',collapsed:false,collapsible:false"
			style="padding:10px;">
			<input id="HomeFuncSearch" class="easyui-searchbox" 
				data-options={prompt:'请输入想要搜索的功能'}
				searcher="doSearch" 
				style="width:178px;height:25px;">
			<!---------------------------------------------------->
			<!-- http://www.jeasyui.net/demo/408.html#  ExpandTo-->
			<!---------------------------------------------------->
		</div>

		


		
		
		<div title="人员监控" data-options="selected:true" style="padding:10px">
			<ul id="employeeMonitor" class="easyui-tree"
				data-options="animate:true,lines:true">
				<li><span>人员监控</span>
					<ul>
						<li id=61 data-options="attributes:{'url':'department/find'}">部门管理</li>
					</ul>
					<ul>
						<li id=62 data-options="attributes:{'url':'employee/find'}">员工管理</li>
					</ul>
				</li>
			</ul>
		</div>
		<div title="文件-统计分析测试" data-options="selected:true" style="padding:10px">
			<ul id="fileTest" class="easyui-tree"
				data-options="animate:true,lines:true">
				<li><span>文件-统计分析测试</span>
					<ul>
						<li id=63 data-options="attributes:{'url':'fileTest/excelin'}">excel导入导出</li>
					</ul>
				
					<ul>
						<li id=65 data-options="attributes:{'url':'fileTest/fileUpload'}">文件上传</li>
					</ul>
					
					<ul>
						<li id=67 data-options="attributes:{'url':'fileTest/analysis'}">统计分析</li>
					</ul>
				</li>
			</ul>
		</div>
		
		<c:if test="${activeUser.rolename == '超级管理员' }">
			<div title="系统管理" style="padding:10px;">
	
				<ul id="sysManager" class="easyui-tree"
					data-options="animate:true,lines:true">
					<li><span>系统管理</span>
						<ul>
							<li data-options="attributes:{'url':'user/find'}">用户管理</li>
						</ul>
						<ul>
							<li data-options="attributes:{'url':'role/find'}">角色管理</li>
						</ul>
					</li>
				</ul>
			</div>
		</c:if>
	</div>

	<!-- </div> -->
	<div data-options="region:'center'">
		<div id="tabs" fit="true" class="easyui-tabs" border="false">
			<div title="消息中心" id="subWarp"
				style="width:100%;height:100%;overflow:hidden">
				<iframe src="${pageContext.request.contextPath }/user/homecenter"
				      
					style="width:100%;height:100%;border:0;"></iframe>
				<%--				这里显示公告栏、预警信息和代办事宜--%>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">

		var allItem = [
						["人员监控","部门管理","员工管理"],
						["文件-统计分析测试","excel导入","excel导出","文件上传","文件下载","统计分析"]
						
					  ];
					  
		function isContains(str, substr) {
		    return new RegExp(substr).test(str);
		}
		
		//HomeFuncSearch
		function doSearch(value){
			var subItem;
			var ifElseContain = false;
			for (var i = 0; i < allItem.length; i++) {
				for (var j = 0; j < allItem[i].length; j++) {
					subItem = allItem[i][j];
					if(isContains(subItem,value) && value!=""){
						ifElseContain=true;
						if(j==0){
							switch(i){
								case 0 :
									$('#HomeFuncAccordion').accordion('select',allItem[0][0]);
									var node = $('#employeeMonitor').tree('find',61);
									$('#employeeMonitor').tree('expandTo', node.target).tree('select', node.target);
									break;
								case 1 :
									$("#HomeFuncAccordion").accordion('select',allItem[1][0]);
									var node = $('#fileTest').tree('find',63);
									$('#fileTest').tree('expandTo', node.target).tree('select', node.target);
									break;
								default:
									break; 
							}
						}else if(j>0){
							var k = (i+1)*10+j;
							switch(i){
								case 0 : 
									$('#HomeFuncAccordion').accordion('select',allItem[0][0]);
									var node = $('#employeeMonitor').tree('find',k);
									$('#employeeMonitor').tree('expandTo', node.target).tree('select', node.target);
									break;
									 case 1 :
									$('#HomeFuncAccordion').accordion('select',allItem[1][0]);
									var node = $('#fileTest').tree('find',k);
									$('#fileTest').tree('expandTo', node.target).tree('select', node.target);
									break; 
								default:
									break; 
							}
							
						}
						break;
					}
				}
				if(ifElseContain==true){
					break;
				}
			}
		}  
		
		$(function() {
			/** 人员监控 */
			$('#employeeMonitor').tree({
				onClick : function(node) {
					/* debugger; */
					if ($('#deviceMonitor').tree("isLeaf", node.target)) {
						var tabs2 = $("#tabs");
						var tab2 = tabs2.tabs("getTab", node.text);
						if (tab2) {
							tabs2.tabs("select", node.text);
						} else {
							tabs2.tabs('add', {
								title : node.text,
								href : node.attributes.url,
								closable : true,
								bodyCls : "content"
							});
						}
					}
				}
			});
			/** 文件-统计分析测试 */
			$('#fileTest').tree({
				onClick : function(node) {
					/* debugger; */
					if ($('#fileTest').tree("isLeaf", node.target)) {
						var tabs2 = $("#tabs");
						var tab2 = tabs2.tabs("getTab", node.text);
						if (tab2) {
							tabs2.tabs("select", node.text);
						} else {
							tabs2.tabs('add', {
								title : node.text,
								href : node.attributes.url,
								closable : true,
								bodyCls : "content"
							});
						}
					}
				}
			});
			
			/* 系统管理 */
			$('#sysManager').tree({
				onClick : function(node) {
					if ($('#sysManager').tree("isLeaf", node.target)) {
						var tabs3 = $("#tabs");
						var tab3 = tabs3.tabs("getTab", node.text);
						if (tab3) {
							tabs3.tabs("select", node.text);
						} else {
							tabs3.tabs('add', {
								title : node.text,
								href : node.attributes.url,
								closable : true,
								bodyCls : "content"
							});
						}
					}
				}
			});
			// 页面加载后 右下角 弹出窗口
			/**************/
			window.setTimeout(function(){
				$.messager.show({
					title:"消息提示",
					msg:'欢迎登录，超级管理员！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
					timeout:5000
				});
			},3000);
			/*************/
			
			$("#btnCancel").click(function(){
				$('#editPwdWindow').window('close');
			});
			//为确定按钮绑定事件
			$("#btnEp").click(function(){
				//进行表单校验
				var v = $("#editPasswordForm").form("validate");
				if(v){
					//表单校验通过，手动校验两次输入是否一致
					var v1 = $("#txtNewPass").val();
					var v2 = $("#txtRePass").val();
					if(v1 == v2){
						//两次输入一致，发送ajax请求
						$.post("user/editPassword",{"password":v1},function(data){
							if(data == '1'){
								//修改成功，关闭修改密码窗口
								$("#editPwdWindow").window("close");
								$.messager.alert("提示信息","密码修成功！","info");
							}else{
								//修改密码失败，弹出提示
								$.messager.alert("提示信息","密码修改失败！","error");
							}
						});
					}else{
						//两次输入不一致，弹出错误提示
						$.messager.alert("提示信息","两次密码输入不一致！","warning");
					}
				}
			});
						
	});
		/*******顶部特效 *******/
		/**
		 * 更换EasyUI主题的方法
		 * @param themeName
		 * 主题名称
		 */
		changeTheme = function(themeName) {
			var $easyuiTheme = $('#easyuiTheme');
			var url = $easyuiTheme.attr('href');
			var href = url.substring(0, url.indexOf('themes')) + 'themes/'
					+ themeName + '/easyui.css';
			$easyuiTheme.attr('href', href);
			var $iframe = $('iframe');
			if ($iframe.length > 0) {
				for ( var i = 0; i < $iframe.length; i++) {
					var ifr = $iframe[i];
					$(ifr).contents().find('#easyuiTheme').attr('href', href);
				}
			}
		};
		// 退出登录
		function logoutFun() {
			$.messager
			.confirm('系统提示','您确定要退出本次登录吗?',function(isConfirm) {
				if (isConfirm) {
					location.href = '${pageContext.request.contextPath }/logout';
				}
			});
		}
		// 修改密码
		function editPassword() {
			//打开修改密码窗口
			$('#editPwdWindow').window('open');
		}
		// 版权信息
		function showAbout(){
			$.messager.alert("盛大天地客户运维信息系统","管理员邮箱: zzq@163.com");
		}
	</script>
	<!--修改密码窗口-->
    <div id="editPwdWindow" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" modal="true" closed="true" resizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 160px; padding: 5px;
        background: #fafafa">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
               <form id="editPasswordForm">
	                <table cellpadding=3>
	                    <tr>
	                        <td>新密码：</td>
	                        <td><input  required="true" data-options="validType:'length[4,6]'" id="txtNewPass" type="Password" class="txt01 easyui-validatebox" /></td>
	                    </tr>
	                    <tr>
	                        <td>确认密码：</td>
	                        <td><input required="true" data-options="validType:'length[4,6]'" id="txtRePass" type="Password" class="txt01 easyui-validatebox" /></td>
	                    </tr>
	                </table>
               </form>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
    <div data-options="region:'south',border:false"
		style="height:50px;padding:10px;background:url('./image/header_bg.png') no-repeat right;">
		<table style="width: 100%;">
			<tbody>
				<tr>
					<td style="width: 300px;">
						<div style="color: #999; font-size: 8pt;">
							盛大天地官网 | Powered by <a href="http://www.sdtiandi.com/">www.sdtiandi.com</a>
						</div>
					</td>
					<td style="width: *;" class="co1"><span id="online"
						style="background: url(${pageContext.request.contextPath }/image/online.png) no-repeat left;padding-left:18px;margin-left:3px;font-size:8pt;color:#005590;">
						在线人数:<%=SessionCounter.getActiveSessions() %>
						</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>