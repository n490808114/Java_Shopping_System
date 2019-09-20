package xyz.n490808114.shopWeb.fliter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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
        Object userId =  session.getAttribute(ShopConstants.SESSION_USER);
        if(userId == null){
            //如果session中查不到User,那么查询是否客户提交的有token
            /*
            String authenticationHeader = request.getHeader("Authentication");
            String token = null;
            if(authenticationHeader != null && authenticationHeader.startsWith("Bearer ")){
                token = authenticationHeader.substring(7);
            }*/

            InputStream inputStream =  request.getInputStream();
            byte[] buffer = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            int len;
            while((len = inputStream.read())!= -1){
                inputStream.read(buffer, 0, len);
                stringBuffer.append(new String(buffer));
                buffer = new byte[1024];
            }
            log.info(stringBuffer.toString());
            ObjectMapper mapper = new ObjectMapper();
            MapLikeType mapLikeType = mapper.getTypeFactory().constructMapLikeType(Map.class,String.class,String.class);

            String token = null;

            if(stringBuffer.toString().length() != 0){
                Map<String,String> requestMap = mapper.readValue(stringBuffer.toString(), mapLikeType);
                token = requestMap.get("token");
            }

            //如果客户提交有token,把token发送给SSO核实是否正确
            if(token != null){
                //创建HttpClient
                HttpClient client = HttpClients.createDefault();
                //创建指向SSO_CHECK_URL的POST请求
                HttpPost httpPost = new HttpPost(ShopConstants.SSO_CHECK_URL);
                //请求中设置头问题本服务器的token
                httpPost.addHeader("Authentication",ShopConstants.SSO_TOKEN);
                //请求体重加入客户的token
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("userToken", token));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,"utf8");
                httpPost.setEntity(urlEncodedFormEntity);

                HttpResponse httpResponse = client.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                String resp = EntityUtils.toString(httpEntity,"utf8");

                Map<String,String> map = mapper.readValue(resp, mapLikeType);

                if("true".equals(map.get("result")) && map.get("userId") != null){
                    session.setAttribute(ShopConstants.SESSION_USER, Integer.parseInt(map.get("userId")));
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            response.sendRedirect(ShopConstants.SSO_URL + "?service=");
            return;
        }
        filterChain.doFilter(request,response);
    }

}