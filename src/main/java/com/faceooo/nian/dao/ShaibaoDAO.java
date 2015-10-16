package com.faceooo.nian.dao;

import com.faceooo.nian.model.ShaibaoLabelDTO;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/10/15.
 */
@Repository("shaibaoDAO")
public interface ShaibaoDAO {
    void createshaibaolabel(ShaibaoLabelDTO shaibaoLabelDTO);
}
