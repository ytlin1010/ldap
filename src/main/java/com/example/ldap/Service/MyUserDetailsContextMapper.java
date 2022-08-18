package com.example.ldap.Service;

import com.example.ldap.Dao.UserRepository;
import com.example.ldap.Data.User;
import com.example.ldap.LdapApplication;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

@Service
public class MyUserDetailsContextMapper implements UserDetailsContextMapper {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) throws UsernameNotFoundException{
        final Logger LOGGER=LoggerFactory.getLogger(LdapApplication.class);
        User user = userRepository.getUserByUsername(username);

        final MyUserDetails userDetails = new MyUserDetails(user);

        LOGGER.info("Deatils:  "+userDetails);
        LOGGER.info("UserName: " + userDetails.getUsername());
        LOGGER.info("Password: " + userDetails.getPassword());
        LOGGER.info("User Role size: " + userDetails.getAuthorities().size());
        LOGGER.info("User Role : " + userDetails.getAuthorities());

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
//        int code = 134569;
//        boolean isCodeValid = gAuth.authorize(userDetails.getPassword(), code);
        Calendar date = Calendar.getInstance();
        System.out.println("Current Date and TIme : " + date.getTime());
        long timeInSecs = date.getTimeInMillis();
        int code = gAuth.getTotpPassword(userDetails.getPassword(), timeInSecs);
        boolean isCodeValid = gAuth.authorize(userDetails.getPassword(), code);
 /*       for(int i=0; i<10; i++) {
            long timeInSecs = date.getTimeInMillis() + i * 60 * 1000;
            System.out.println("Code:" + gAuth.getTotpPassword(userDetails.getPassword(), timeInSecs));
        }*/
        if (isCodeValid) {
            System.out.println(timeInSecs+ " Match! " + code + " Now code:"  + gAuth.toString());
        }else{
            System.out.println(timeInSecs+ " Not Match! " + code + "Now code"  + gAuth.toString());
        }
        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

    }
}
