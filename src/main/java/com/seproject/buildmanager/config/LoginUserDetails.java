package com.seproject.buildmanager.config;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.seproject.buildmanager.entity.MstUser;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LoginUserDetails implements UserDetails {

  private final String email;
  private final String password;
  private final String name;
  private final Collection<? extends GrantedAuthority> authorities;

  public LoginUserDetails(MstUser user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.name = user.getLName() + user.getFName();
    this.authorities = Arrays.stream(user.getRole().split(","))
        .map(role -> new SimpleGrantedAuthority(role)).toList();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // ロールのコレクションを返す
    return authorities;
  }

  @Override
  public String getPassword() {
    // パスワードを返す
    return password;
  }

  @Override
  public String getUsername() {
    // ログイン名を返す
    return name;
  }

  public String getName() {
    // ユーザー名を返す
    return name;
  }

  @Override
  public boolean isAccountNonExpired() {
    // ユーザーが期限切れでなければtrueを返す
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // ユーザーがロックされていなければtrueを返す
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // パスワードが期限切れでなければtrueを返す
    return true;
  }

  @Override
  public boolean isEnabled() {
    // ユーザーが有効ならtrueを返す
    return true;
  }
}
