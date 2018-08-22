package com.megagao.production.ssm.api; 
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.megagao.production.ssm.util.HttpClientUtils;
import com.megagao.production.ssm.util.JsonUtils;
/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2018年8月21日 上午10:31:36 
 * 类说明 
 *  扫码登录controller
 */
public class LoginCodeController {
    // 千万要记住，这个是微信开放平台的APPSECRET
    String WX_PLATFORM_APPSECRET = "XXXXXXXXXX";
    // 拉起微信扫码页地址
    String WX_SCAN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 微信扫码之后获取用户基本信息的地址
    String WX_SCAN_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	String WX_SCAN_CODE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid={APPID}&redirect_uri={REUTL}&response_type=code&scope=snsapi_login&state={STATE}#wechat_redirect";
    // 千万要记住，这个是微信开放平台的APPID
    String WX_PLATFROM_APPID = "XXXXXX";
    // 你的回调地址
    String scanReUrl = "http://你的网址/user/wxLoginCallback";

    /**
     * 微信扫码登陆
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "weixinScanLogin", method = RequestMethod.GET)
    public void weixinRetrun(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取回调url(非必填，只是附带上你扫码之前要进入的网址，具体看业务是否需要)
        String url = request.getParameter("reurl");
        // 拼接扫码登录url
        String wxLoginurl = WX_SCAN_CODE_URL;
        wxLoginurl = wxLoginurl.replace("{APPID}", WX_PLATFROM_APPID).replace("{REUTL}", scanReUrl).replace("{STATE}",
                url);
        wxLoginurl = response.encodeURL(wxLoginurl);
        response.sendRedirect(wxLoginurl);
    }
    /**
     * 微信扫码登录回调
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "wxLoginCallback", method = RequestMethod.GET)
    public void loginCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        if (code == null) {
            // 用户禁止授权
        }
        String url = WX_SCAN_URL.replace("APPID", WX_PLATFROM_APPID).replace("SECRET", WX_PLATFORM_APPSECRET)
                .replaceAll("CODE", code);
        url = response.encodeURL(url);
        try {
            String result = HttpClientUtils.get(url, null);
            Map<String, Object> resultMap = JsonUtils.getMap(result);
            String unionid = (String) resultMap.get("unionid");
            String access_token = (String) resultMap.get("access_token");
            String openid = (String) resultMap.get("openid");
            // 这里可以根据获取的信息去库中判断是否存在库中 如果不存在执行以下方法
            // 如果该用户不存在数据库中
            // 获取用户信息
            url = WX_SCAN_GET_USER_INFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
            url = response.encodeURL(url);
            String userResult = HttpClientUtils.get(url, null);
            Map<String, Object> userResultMap = JsonUtils.getMap(userResult);
            // 注册一个用户
            System.out.println("扫码登录返回值******************:" + userResult);
            String headimgurl = (String) userResultMap.get("headimgurl");
            // 处理微信名特殊符号问题 过滤图标
            String nickname = (String) userResultMap.get("nickname");
            // 把用户信息存入session中
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回地址
        try {
            String newUrl = request.getParameter("state");
            response.sendRedirect(newUrl);
        } catch (IOException e) {
            // logger.error("url:重定向错误");
        }
    }
}
