package com.megagao.production.ssm.util; 


/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月1日 下午1:43:28 
 * 类说明 
 * 自定义响应结果集
 */
public class MyResultUtils {



    // 响应业务状态
    private String status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static MyResultUtils build(String status, String msg, Object data) {
        return new MyResultUtils(status, msg, data);
    }

    public static MyResultUtils ok(Object data) {
        return new MyResultUtils(data);
    }

    public static MyResultUtils ok() {
        return new MyResultUtils(null);
    }

    public MyResultUtils() {

    }

    public static MyResultUtils build(String status, String msg) {
        return new MyResultUtils(status, msg, null);
    }

    public MyResultUtils(String status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public MyResultUtils(Object data) {
        this.status = "200";
        this.msg = "OK";
        this.data = data;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
    
}
