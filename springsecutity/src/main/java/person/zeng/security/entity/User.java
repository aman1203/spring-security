package person.zeng.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

/**
 * 用户实体类
 * 
 * @author Administrator
 *
 */
@Entity
public class User implements UserDetails {

  
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @Column(name="user_name")
  private String username;
  private String password;
  @ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
  private List<Role> roles;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public void setUserName(String userName) {
    this.username = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  /**
   * 获取权限,将role当做权限
   */
  @Override
  public Collection<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
    if (roles != null) {
      for (Role auth : roles) {
        GrantedAuthority gauth = new SimpleGrantedAuthority(auth.getName());
        authList.add(gauth);
      }
    }
    return authList;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }
}
