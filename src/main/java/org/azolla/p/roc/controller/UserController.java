/*
 * @(#)UserController.java		Created at 15/5/1
 *
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.azolla.p.roc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
@Controller
public class UserController
{
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model)
  {
    model.addAttribute("jsp_title", "Login");

    return "login";
  }
}
