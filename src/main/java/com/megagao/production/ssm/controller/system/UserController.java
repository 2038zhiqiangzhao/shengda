package com.megagao.production.ssm.controller.system;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.megagao.production.ssm.domain.customize.ActiveUser;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.authority.SysUser;
import com.megagao.production.ssm.service.UserService;
import com.megagao.production.ssm.util.MD5Utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/get/{userId}")
	@ResponseBody
	public SysUser getItemById(@PathVariable String userId) throws Exception{
		SysUser sysUser = userService.get(userId);
		return sysUser;
	}
	
	@RequestMapping("/find")
	public String find() throws Exception{
		return "user_list";
	}
	
	@RequestMapping("/role")
	public String userRole() throws Exception{
		return "user_role_edit";
	}
	
	@RequestMapping("/add")
	public String add() throws Exception{
		return "user_add";
	}
	
	@RequestMapping("/edit")
	public String edit() throws Exception{
		return "user_edit";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows, SysUser sysUser) throws Exception{
		EUDataGridResult result = userService.getList(page, rows, sysUser);
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid SysUser user, BindingResult bindingResult) throws Exception {
		CustomResult result;
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(userService.findByUserNameAndId(user.getUsername(), user.getId()).size()>0){
			return CustomResult.build(101, "该用户名已经存在，请更换用户名!");
		}else if(userService.get(user.getId()) != null){
			return CustomResult.build(101, "该用户编号已经存在，请更换用户编号！");
		}
		result = userService.insert(user);
		return result;
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid SysUser user, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return userService.update(user);
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid SysUser user, BindingResult bindingResult) throws Exception {
		CustomResult result; 
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if(userService.findByUserNameAndId(user.getUsername(), user.getId()).size()>0){
			return CustomResult.build(101, "该用户名已经存在，请更换用户名！");
		}
		
		result = userService.updateAll(user);
		return result;
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		CustomResult result = userService.delete(id);
		return result;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids) throws Exception {
		CustomResult result = userService.deleteBatch(ids);
		return result;
	}
	
	@RequestMapping(value="/change_status")
	@ResponseBody
	public CustomResult changeStatus(String[] ids) throws Exception{
		CustomResult result = userService.changeStatus(ids);
		return result;
	}
	
	//根据用户id查找
	@RequestMapping("/search_user_by_userId")
	@ResponseBody
	public EUDataGridResult searchUserByUserId(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = userService.searchUserByUserId(page, rows, searchValue);
		return result;
	}
	
	//根据用户名查找
	@RequestMapping("/search_user_by_userName")
	@ResponseBody
	public EUDataGridResult searchUserByUserName(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = userService.searchUserByUserName(page, rows, searchValue);
		return result;
	}
	
	//搜根据角色名查找
	@RequestMapping("/search_user_by_roleName")
	@ResponseBody
	public EUDataGridResult searchUserByRoleName(Integer page, Integer rows, String searchValue) 
			throws Exception{
		EUDataGridResult result = userService.searchUserByRoleName(page, rows, searchValue);
		return result;
	}
	/**
	 * 修改当前用户密码
	 * @throws IOException 
	 */
	@RequestMapping("/editPassword")
	@ResponseBody
	public String editPassword(HttpServletResponse response,@RequestParam String password) throws IOException {
		String f = "1";
		//获取当前登录用户
		
		try{
			Subject subject = SecurityUtils.getSubject();
			ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
			String userid = activeUser.getUserid();
			SysUser userPO=new SysUser();
			userPO.setId(userid);
			userPO.setPassword(MD5Utils.md5(password));
			userService.update(userPO);
		}catch(Exception e){
			f = "0";
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(f);
		return f;
	}
	/**
	 * 首页预警信息显示
	 * 
	 */
	@RequestMapping("/homecenter")
	public String homeCenter() throws Exception{
		return "homecenter";
	}
}
