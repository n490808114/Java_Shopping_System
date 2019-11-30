package xyz.n490808114.shopWeb.fliter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import xyz.n490808114.shopWeb.util.ShopConstants;
@Component
@WebFilter("/**")
public class UserAuthenticationTokenFilter extends OncePerRequestFilter {
    private static String thisServiceToken = "";
    private static final Log log = LogFactory.getLog(UserAuthenticationTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //如果session中保存有用户信息,那么表示用户已经在这个系统中注册了
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(ShopConstants.SESSION_USER);
        if(userId != null){
            filterChain.doFilter(request,response);
        }else{
            //如果session中查不到User,那么查询是否客户提交的有token
            String queryString = request.getQueryString();
            String[] list = queryString.split("&");
            String token = null;
            for(String str : list){
                if("token".equals(str.split("=")[0])){
                    token = str.split("=")[1];
                }
            }

            //如果客户提交有token,把token发送给SSO核实是否正确
            if(token != null){
                boolean check = checkTokenBySSO(token);
                if(check){
                    filterChain.doFilter(request,response);
                }else{
                    //如果没有token或者token验证不通过，跳转到SSO中心登录
                    //并附带上此次请求的地址和参数，以便登陆之后重新跳转回来
                    response.sendRedirect(ShopConstants.SSO_URL +
                            "?callBack="+request.getRequestURL().toString() +
                            "?callBackQuery="+request.getQueryString());
                }
            }else{
                //如果没有token或者token验证不通过，跳转到SSO中心登录
                //并附带上此次请求的地址和参数，以便登陆之后重新跳转回来
                response.sendRedirect(ShopConstants.SSO_URL +
                        "?callBack="+request.getRequestURL().toString() +
                        "?callBackQuery="+request.getQueryString());
            }

        }
    }
    private boolean checkTokenBySSO(String token){
        try(
            //创建httpClient
            CloseableHttpClient httpClient = HttpClients.createDefault()
        ){
            //创建一个Post请求，向SSO发送自己的Token和用户的token用以做验证
            HttpPost httpPost = new HttpPost(ShopConstants.SSO_CHECK_URL);
            //把自己的Token放进头部
            httpPost.setHeader("Authorization", thisServiceToken);
            //把用户的Token放进Body
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("userToken", token));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
                try(
                    //发送并获得返回的Response
                    CloseableHttpResponse response =  httpClient.execute(httpPost)
                ){
                    //获取Response中的内容
                    String result = EntityUtils.toString(response.getEntity());
                    return "true".equals(result);
                }
        }catch(Exception exception){
            log.info("httpClient 出错");
        }
        return false;
    }

    /**
     * 登录SSO,获取本系统的Token,以便本系统之后向SSO发送其他请求
     * @PostConstruct 该Bean初始化完成之后执行
     */
    @PostConstruct
    public void getThisServiceToken(){
        try (
                //创建httpClient
                CloseableHttpClient httpClient = HttpClients.createDefault();
        ){
            //创建Post请求，访问SSO登录地址，带上本系统用户名密码
            HttpPost post = new HttpPost(ShopConstants.SSO_LOGIN_URL);
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("username",ShopConstants.THIS_USERNAME));
            list.add(new BasicNameValuePair("password",ShopConstants.THIS_PASSWORD));
            post.setEntity(new UrlEncodedFormEntity(list));

            try (
                    //获得response
                    CloseableHttpResponse response = httpClient.execute(post);
            ){
                String[] results = EntityUtils.toString(response.getEntity()).split("[:{}]");
                if("token".equals(results[0])){
                    thisServiceToken = results[1];
                    log.info("本系统Token : " + thisServiceToken);
                }else{
                    log.error("登录SSO失败");
                }
            }
        } catch (IOException ex) {
            log.error("登录SSO失败");
        }
    }
}