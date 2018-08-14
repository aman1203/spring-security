package person.zeng.security.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import junit.framework.Assert;
import person.zeng.security.entity.User;
import person.zeng.security.repository.UserRepository;

/**
 * 自定义用户认证的服务，使用jpa 返回用户对象（包括用户名，密码，权限等等）
 * 
 * @author Administrator
 *
 */
@Configuration
public class CustomUserService implements UserDetailsService {
  @Autowired
  private UserRepository Repository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = Repository.findByUsername(userName);
    if(user==null) {
      throw new UsernameNotFoundException("用户名不存在");
    }
    return user;
  }
}
