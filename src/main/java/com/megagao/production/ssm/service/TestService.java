package com.megagao.production.ssm.service; 
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.Testdata;
import com.megagao.production.ssm.util.MyResultUtils;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月30日 上午11:56:11 
 * 类说明 
 */
public interface TestService {
    List<Testdata> find() throws Exception;
	
	EUDataGridResult getList(int page, int rows, Testdata testdata) throws Exception;
	
	Testdata get(String string) throws Exception;
	
	CustomResult delete(String string) throws Exception;

	CustomResult deleteBatch(String[] ids) throws Exception;

	CustomResult insert(Testdata testdata) throws Exception;

    //更新全部字段，不判断非空，直接进行更新
    CustomResult updateAll(Testdata testdata) throws Exception;
    
    CustomResult update(Testdata testdata) throws Exception;
    
    List<Testdata> searchEmployeeByEmployeeName(String employeeName) throws Exception;

	List<Testdata> searchEmployeeByEmployeeId(String employeeId) throws Exception;

	//根据员工id查找员工信息
	EUDataGridResult searchEmployeeByEmployeeId(Integer page, Integer rows,
			String employeeId) throws Exception;

	//根据员工姓名查找员工信息
	EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows,
			String employeeName) throws Exception;

	//根据部门名称查找员工信息
	EUDataGridResult searchEmployeeByDepartmentName(Integer page, Integer rows,
			String departmentName) throws Exception;

	void writExcelData(String currentPathString) throws IOException;

	Map<String, Object> uploadFile(MultipartFile multipartFile);

	MyResultUtils downloadTemplate(HttpServletResponse response,
			HttpServletRequest request) throws IOException;

	CustomResult export(HttpServletRequest request);

	
}
