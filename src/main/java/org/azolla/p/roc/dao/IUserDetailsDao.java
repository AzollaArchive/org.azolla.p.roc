package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.impl.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IUserDetailsDao
{
    public UserDetailsImpl lst(String username);
}
