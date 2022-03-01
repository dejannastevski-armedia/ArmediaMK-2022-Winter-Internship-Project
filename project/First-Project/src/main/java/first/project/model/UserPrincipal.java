package first.project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrincipal implements UserDetails
{
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String email, String password, Collection<? extends GrantedAuthority> authorities)
    {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public UserPrincipal()
    {
    }

    public static UserPrincipal create(User user)
    {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.email = user.getEmail();
        userPrincipal.password = user.getPassword();
        userPrincipal.authorities = new ArrayList<>();
        return userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return new ArrayList<>();
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
