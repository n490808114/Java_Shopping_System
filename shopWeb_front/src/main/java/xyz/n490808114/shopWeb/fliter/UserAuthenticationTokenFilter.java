package xyz.n490808114.shopWeb.fliter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private static final Log log = LogFactory.getLog(UserAuthenticationTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //如果session中保存有用户信息,那么表示用户已经在这个系统中注册了
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute(ShopConstants.SESSION_USER);
        if(userId == null){
            
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
                try(
                    //创建httpClient
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                ){
                    //创建一个Post请求，向SSO发送自己的Token和用户的token用以做验证
                    HttpPost httpPost = new HttpPost(ShopConstants.SSO_URL);
                    //把自己的Token放进头部
                    httpPost.setHeader("Authorization", ShopConstants.THIS_SERVICE_TOKEN);
                    //把用户的Token放进Body
                    List<NameValuePair> nameValuePairs = new ArrayList<>();
                    nameValuePairs.add(new BasicNameValuePair("userToken", token));
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

                    try(
                        //发送并获得返回的Response
                        CloseableHttpResponse httpResponse =  httpClient.execute(httpPost);
                    ){
                        //获取Response中的内容
                        HttpEntity responseEntity = httpResponse.getEntity();
                        String result = EntityUtils.toString(responseEntity);
                        log.info("result："+ result);
                        //获取userId字段中的内容
                        for(String s :result.split(",|;")){
                            if(s.startsWith("userId=")){
                                userId = s.split("=")[1];
                            }
                        }
                        //如果不为空，那么假如到session中,继续执行filterChain
                        if(userId != null){
                            session.setAttribute(ShopConstants.SESSION_USER, userId);
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }catch(Exception exception){
                        log.info("HTTPResponse 出错");
                    }
                }catch(Exception exception){
                    log.info("httpClient 出错");
                }
            }
            //如果没有token或者token验证不通过，跳转到SSO中心登录
            //并附带上此次请求的地址和参数，以便登陆之后重新跳转回来
            response.sendRedirect(ShopConstants.SSO_URL + "?callBack="+request.getRequestURL().toString()+"?callBackQuery="+request.getQueryString());
        }else{
            filterChain.doFilter(request,response);
        }
    }
}