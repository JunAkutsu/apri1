package com.apri.login;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apri.common.exception.ApplicationException;


@Service
public class LoginService implements UserDetailsService {
    @Autowired
	@Qualifier("com.apri.login.LoginMapper")
    private LoginMapper loginMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    	UserValue userValue = loginMapper.findById(username);
        if(userValue == null) {
            throw new UsernameNotFoundException(username + " is not found");
        }
        return new UserValueDetails(userValue);
    }
    
}
