package com.hoseasandstrom.controllers;

import com.hoseasandstrom.services.LiabilityRepository;
import com.hoseasandstrom.services.UserRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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


}
