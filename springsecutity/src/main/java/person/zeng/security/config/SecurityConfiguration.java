package person.zeng.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  private CustomUserService userSrvice;
  /**
   * 配置认证处理器
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userSrvice);
  }

  /**
   * 配置http 请求拦截
   * 请求流程：请求被filter拦截，filter根据sessionid 查找上securitycontext中查找是否存在authencation对象
   *          没有的话就跳转到登录页面（默认的登录页面是/login,这是springsecurity自带的），登录后按确定就跳转到登录处理逻辑
   *          中去了，登录处理逻辑对应的url可以自行配置，这是一个逻辑URL。
   *          如果已经登陆过了。那么就直接放行了
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/admin1/**").hasAnyAuthority("admin1","admin2")//该方法好使
        .antMatchers("/admin2/**").hasRole("admin2")//该路径下面的用户角色必须是admin2，hasrole不好使用403
        .anyRequest()
        .authenticated()//剩下的请求必须是认证过的
        .and()
        .formLogin()//有一个默认的登录页面/login
        .loginProcessingUrl("/login")//登录处理，主要是验证处理账号密码
        .successForwardUrl("/successLogin")
        .permitAll()
        .and()
        .rememberMe()
        .and()
        .logout()
        .permitAll();
  }

  /**
   * 配置web 安全,配置过滤项
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    //web.ignoring().antMatchers(antPatterns)
  }
}
