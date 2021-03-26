package com.blog.api.api.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"User\"")
public class User implements UserDetails {
    @Id
    @Column(name="username")
    @Expose
    private String username;

    @Column(name="password")
    private String password;

    @Column
    @Expose
    private String vorname;

    @Column
    @Expose
    private String nachname;

    @Column
    @Lob
    @Expose
    private byte[] profilBild;

    @Column(name="auth")
    @Expose
    private String authorities;

    @Transient
    private Collection<? extends GrantedAuthority> authList;

    @Column
    @Expose
    private boolean isAccountNonExpired;

    @Column
    @Expose
    private boolean isAccountNonLocked;

    @Column
    @Expose
    private boolean isCredentialsNonExpired;

    @Column
    @Expose
    private boolean isEnabled;

    @OneToMany(mappedBy = "username")
    private Set<Beitrag> beitraege;

    @OneToMany(mappedBy = "user")
    private Set<BeitragView> views;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAuthList(Collection<? extends GrantedAuthority> authorities){
        this.authList = authorities;
        this.authorities = "";
        for(GrantedAuthority a : authorities){
            this.authorities += a.getAuthority().toString();
        }
    }
}
