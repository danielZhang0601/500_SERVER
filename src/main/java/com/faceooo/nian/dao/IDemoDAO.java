package com.faceooo.nian.dao;

import com.faceooo.nian.model.UserinfoDTO;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by qin on 15/8/19.
 */
@Repository("demoDAO")
public interface IDemoDAO {

    public UserinfoDTO getDemo(String username) throws SQLException;
}
