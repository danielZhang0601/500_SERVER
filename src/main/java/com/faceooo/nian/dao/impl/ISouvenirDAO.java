package com.faceooo.nian.dao.impl;

import com.faceooo.nian.model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by qin on 15/8/18.
 */
public interface ISouvenirDAO {

    public List<ImageDTO> querySouImagesCode(String souvenirid) throws SQLException;

    public List<SouvenirtypeDTO> queryUserSouListForHome(UserinfoDTO userinfo) throws SQLException;

    public List<SouvenirDTO> querySouListForSearch(Map<String, String> paramMap) throws SQLException;

    public List<SouvenirDTO> querySouListForSearchName(Map<String, String> paramMap) throws SQLException;

    public void deleteSou(String souvenirid) throws SQLException;

    public void deleteSoutype(String soutypeid) throws SQLException;

    public void updateSoutype(SouvenirtypeDTO soutypedto) throws SQLException;

    public void creatSouTypes(SouvenirtypeDTO soutypedto) throws SQLException;

    public SouvenirDTO querySouInfoForID(String souvenirid) throws SQLException;

    public void chargeSoutype(SouvenirDTO soudto) throws SQLException;

    public void updateSouBaseInfo(SouvenirDTO soudto) throws SQLException;

    public void createSouRecord(RecordinfoDTO recorddto) throws SQLException;

    public void deleteSouRecord(RecordinfoDTO recorddto) throws SQLException;

    public void updateSouRecord(RecordinfoDTO recorddto) throws SQLException;

    public void createSouvenir(SouvenirDTO soudto) throws SQLException;

    public void createSouImage(ImageDTO imagedto) throws SQLException;

    public void deleteImage(String imageId) throws SQLException;

    public void createClearQiniu(ClearQiniuDTO clearqiniudto) throws SQLException;

    public List<SouvenirDTO> getSouvenirListForType(SouvenirDTO souvenirDTO) throws SQLException;

}
