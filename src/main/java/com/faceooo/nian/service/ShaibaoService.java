package com.faceooo.nian.service;

import com.faceooo.nian.dao.ShaibaoDAO;
import com.faceooo.nian.model.ShaibaoLabelDTO;
import com.faceooo.nian.utils.SysUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2015/10/15.
 */

@Service("shaibaoService")
public class ShaibaoService {
    @Autowired
    ShaibaoDAO shaibaoDAO;

    public void createshaibaolabel(Map<String, String> paramMap) {
        String labelID= SysUtils.getShaibaoLabelID();
        ShaibaoLabelDTO shaibaoLabelDTO = new ShaibaoLabelDTO();
        shaibaoLabelDTO.setId(labelID);
        shaibaoLabelDTO.setLabelname(paramMap.get("labelname"));
        shaibaoLabelDTO.setLabelen(paramMap.get("labelen"));
        shaibaoLabelDTO.setTimerecord(SysUtils.getNowTimeStr());
        shaibaoLabelDTO.setSort("0");
        shaibaoDAO.createshaibaolabel(shaibaoLabelDTO);
    }
}
