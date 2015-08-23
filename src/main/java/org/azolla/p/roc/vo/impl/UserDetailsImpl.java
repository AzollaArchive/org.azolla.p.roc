/*
 * @(#)UserVo.java		Created at 15/4/18
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.roc.vo.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Table(name = "roc_t_user")
@Component
public class UserDetailsImpl implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Integer id;
    private String  username;
    private String  email;
    private String  password;
    @OrderBy
    private Date    addDate;
    private Date    modDate;
    private Date    rmvDate;
    private Integer visible = 1;
    private Integer operable;
    private Integer deleted = 0;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    public UserDetailsImpl setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getAddDate()
    {
        return addDate;
    }

    public void setAddDate(Date addDate)
    {
        this.addDate = addDate;
    }

    public Date getModDate()
    {
        return modDate;
    }

    public void setModDate(Date modDate)
    {
        this.modDate = modDate;
    }

    public Date getRmvDate()
    {
        return rmvDate;
    }

    public void setRmvDate(Date rmvDate)
    {
        this.rmvDate = rmvDate;
    }

    public Integer getVisible()
    {
        return visible;
    }

    public void setVisible(Integer visible)
    {
        this.visible = visible;
    }

    public Integer getOperable()
    {
        return operable;
    }

    public void setOperable(Integer operable)
    {
        this.operable = operable;
    }

    public Integer getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Integer deleted)
    {
        this.deleted = deleted;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority sim = new SimpleGrantedAuthority("ROLE_ADMIN");
        simpleGrantedAuthorityList.add(sim);
        return simpleGrantedAuthorityList;
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
