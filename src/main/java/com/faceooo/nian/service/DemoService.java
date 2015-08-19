package com.faceooo.nian.service;

import com.faceooo.nian.dao.IDemoDAO;
import com.faceooo.nian.model.UserinfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by qin on 15/8/19.
 */
@Service("demoService")
public class DemoService {

    @Autowired
    IDemoDAO demoDAO;

    public UserinfoDTO getDemo(String username){
        try {
            UserinfoDTO demoDTO = demoDAO.getDemo(username);
            return demoDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
