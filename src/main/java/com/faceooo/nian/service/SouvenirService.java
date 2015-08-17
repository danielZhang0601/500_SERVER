package com.faceooo.nian.service;

import com.faceooo.nian.dao.SouvenirDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by yuanlin on 15/8/18.
 */
@Service("souvenirService")
public class SouvenirService {

    @Autowired
    SouvenirDAO souvenirDAO;

    public void deleteSou(String souvenirid) {
        try {
            souvenirDAO.deleteSou(souvenirid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSoutype(String soutypeid) {
        try {
            souvenirDAO.deleteSoutype(soutypeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
