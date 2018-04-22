package com.apri.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserValueDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
    private UserValue userValue;

    public UserValueDetails(UserValue userValue) {
        this.userValue = userValue;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO 自動生成されたメソッド・スタブ
//    	List<GrantedAuthority> authorities = new ArrayList<>();
//    	authorities.add(new SimpleGrantedAuthority(authority));
//        return authorities;
    	return null;
    }

    @Override
    public String getPassword() {
        // TODO 自動生成されたメソッド・スタブ
        return this.userValue.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO 自動生成されたメソッド・スタブ
        return this.userValue.getTantousya_id();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO 自動生成されたメソッド・スタブ
        return true;
    }
}
