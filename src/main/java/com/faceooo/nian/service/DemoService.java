package com.faceooo.nian.service;

import com.faceooo.nian.dao.IDemoDAO;
import com.faceooo.nian.model.DemoDTO;
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

    public DemoDTO getDemo(String username){
        try {
            DemoDTO demoDTO = demoDAO.getDemo(username);
            return demoDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
