package com.hoseasandstrom.controllers;

import com.hoseasandstrom.entities.User;
import com.hoseasandstrom.services.LiabilityRepository;
import com.hoseasandstrom.services.UserRepository;
import com.hoseasandstrom.utils.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hoseasandstrom on 10/19/16.
 */
@RestController
public class IMSSpringController {
    @Autowired
    UserRepository users;

    @Autowired
    LiabilityRepository liabilities;

    public void init() throws Exception {
        Server.createWebServer().start();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login (HttpSession session, @RequestBody User user) throws Exception {
        User dummy = users.findByUsername(user.getUsername());
        if (dummy == null ) {
            throw new Exception("Username does not exist! Please register");
        } else if (!PasswordStorage.verifyPassword(user.getPassword(), dummy.getPassword())) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("username", user.getUsername());
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register (HttpSession session, @RequestBody User user) throws Exception {
        User regi = users.findByUsername(user.getUsername());
        if (regi == null) {
            regi = new User(user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), PasswordStorage.createHash(user.getPassword()));
            users.save(regi);
            session.setAttribute("username", user.getUsername());
        }
    }


}
