package org.azolla.p.roc.dao;

import org.azolla.p.roc.vo.impl.UserDetailsImpl;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public interface IUserDetailsDao
{
    public UserDetailsImpl getByUsername(String username);
}
