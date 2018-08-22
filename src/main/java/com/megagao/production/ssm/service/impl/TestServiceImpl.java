package com.megagao.production.ssm.service.impl; 

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsqlparser.expression.StringValue;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.common.Constants;
import com.megagao.production.ssm.controller.file.FileUploader;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.Testdata;
import com.megagao.production.ssm.mapper.TestdataMapper;
import com.megagao.production.ssm.service.TestService;
import com.megagao.production.ssm.util.ExcelUtils;
import com.megagao.production.ssm.util.MyResultUtils;


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年7月30日 上午11:56:39 
 * 类说明 
 */
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	TestdataMapper testdataMapper;
	@Override
	public List<Testdata> find() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EUDataGridResult getList(int page, int rows, Testdata testdata)
			throws Exception {
		      //分页处理
				PageHelper.startPage(page, rows);
				List<Testdata> list = testdataMapper.find();
				//创建一个返回值对象
				EUDataGridResult result = new EUDataGridResult();
				result.setRows(list);
				//取记录总条数
				PageInfo<Testdata> pageInfo = new PageInfo<>(list);
				result.setTotal(pageInfo.getTotal());
				return result;
	}

	@Override
	public Testdata get(String string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResult delete(String string) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResult deleteBatch(String[] ids) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResult insert(Testdata testdata) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResult updateAll(Testdata testdata) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomResult update(Testdata testdata) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Testdata> searchEmployeeByEmployeeName(String employeeName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Testdata> searchEmployeeByEmployeeId(String employeeId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EUDataGridResult searchEmployeeByEmployeeId(Integer page,
			Integer rows, String employeeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EUDataGridResult searchEmployeeByEmployeeName(Integer page,
			Integer rows, String employeeName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EUDataGridResult searchEmployeeByDepartmentName(Integer page,
			Integer rows, String departmentName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 开始往数据中写人excel表格数据 (non-Javadoc)
	 * 
	 * @see
	 * com.people2000.mzadmin.business.write.CommunityWriteManager#writExcelData
	 * ()
	 */
	@Override
	public void writExcelData(String currentPathString) throws IOException {
		List<Map<String, String>> readExcelKeyMap = ExcelUtils
				.readExcelKeyMap(currentPathString);
		int temp = -1;
		int beanNum=1;
		int paramNum=0;
		for (Map<String, String> map : readExcelKeyMap) {
			Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator(); // map.entrySet()得到的是set集合，可以使用迭代器遍历
			Testdata testdata=null;
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				// key值：副标题 value值：110
				// key值：活动编号 value值：110
				// key值：作者 value值：110
				// key值：微信连接 value值：110
				// key值：标题 value值：110
				temp =temp+1;
				System.out.println("key值：" + entry.getKey() + " value值："
						+ entry.getValue());
				if (temp % 4 == 0) {
					testdata = new Testdata();
					 beanNum=beanNum+1;
				}
					if (entry.getKey().equals("姓名")) {
						testdata.setName(entry.getValue());
						
					} else if (entry.getKey().equals("年纪")) {
					testdata.setAge(entry.getValue());
					} else if (entry.getKey().equals("地址")) {
						testdata.setUrl(entry.getValue());
					} else if (entry.getKey().equals("是否发布")) {
						testdata.setIsDeplyed(Integer.parseInt(entry.getValue()));
					}else{
						
					}
					paramNum=paramNum+1;//为5的倍数就是一列数据填充完成，更新数据库
				
				if((beanNum % 2 ==0)&&paramNum % 4 ==0){//默认第一次创建bean 此时为偶数
					testdataMapper.insertSelective(testdata);
				}else if(beanNum % 2==1&&paramNum % 4 ==0){//再次创建此时为奇数
					testdataMapper.insertSelective(testdata);
				}

				

			}
		}
	}
	/**导入数据解析
	 * 
	 */

	@SuppressWarnings("null")
	@Override
	public Map<String, Object> uploadFile(MultipartFile uploadFile) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		File uploadFile2 = null;
		try {
			if (uploadFile != null && uploadFile.getOriginalFilename() != null
					&& uploadFile.getOriginalFilename().length() > 0) {

				// 文件保存路径
				String filePath = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/tmp/")
						+ "/"+UUID.randomUUID().toString()
						+ uploadFile.getOriginalFilename();
				// 转存文件
				uploadFile2 = new File(filePath);
				if (uploadFile2.exists()) {
					uploadFile2.delete();
				}
				
				// 将内存中的文件写入磁盘
				uploadFile.transferTo(uploadFile2);
				writExcelData(filePath);
				//上传成功后删除文件
				uploadFile2.delete();
				
				// 文件上传成功后，将文件的地址写回
				resultMap.put("error", 0);
				resultMap.put("url",filePath);
				return resultMap;
			} else {
				// 返回结果
				uploadFile2.delete();
				resultMap.put("error", 1);
				resultMap.put("message", "文件异常");
				return resultMap;
			}

		} catch (Exception e) {
			uploadFile2.delete();
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public MyResultUtils downloadTemplate(HttpServletResponse response,
			HttpServletRequest request)  {
		//下载模板，调用公用方法，适用windows和linux，templete文件夹下文件的下载
		String path=null;
		try {
			path = ExcelUtils.downloadTemplate(response, request, Constants.EXCEL_COMMIT);
			// 写出姓名，年纪、地址 、发布
			ExcelUtils.writeExcelTitle(path, Constants.EXCEL_COMMITINFO);
			return  MyResultUtils.ok();
		} catch (IOException e) {
			e.printStackTrace();
			return MyResultUtils.build("101", "下载失败");
		}  
	
	
	
	}

	@SuppressWarnings("unused")
	@Override
	public CustomResult export(HttpServletRequest request) {
		 List<Testdata> find = testdataMapper.find();
		 String ctxPath = request.getSession().getServletContext().getRealPath(File.separator)  + "template" + File.separator;
         String path = ctxPath + "测试.xls";
	     if(path!=null){
	    	 try {
				 excelout(find,path);
				 return  CustomResult.ok();
			} catch (IOException e) {
				e.printStackTrace();
				 return  CustomResult.build(102, "导入异常！");
			}
	    	
	     }else{
	    	 return CustomResult.build(101, "文件模板不存在，请先下载模板！");
	     }
		
	}
    /**开始导出
     * 
     * @param find
     * @param path
     * @throws IOException 
     */
	private void excelout(List<Testdata> lsit, String path) throws IOException {

		String[] readExcelTitle = ExcelUtils.readExcelTitle(path);// 读取title
		List<Map<Integer, String>> lists = new ArrayList<Map<Integer, String>>();// 控制行元素
		for (int i = 0; i < lsit.size(); i++) {
			Map<Integer, String> element = new HashMap<Integer, String>();// 控制列元素
			element.put(0, lsit.get(i).getName().toString());
			element.put(1, lsit.get(i).getAge().toString());
			element.put(2, lsit.get(i).getUrl().toString());
			element.put(3, ""+lsit.get(i).getIsDeplyed());
			lists.add(element);
		}

		  ExcelUtils.writeExcel(path, readExcelTitle, lists);
	}

	

}
