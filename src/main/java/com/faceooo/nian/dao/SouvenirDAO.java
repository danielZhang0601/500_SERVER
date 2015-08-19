package com.faceooo.nian.dao;

import com.faceooo.nian.dao.impl.ISouvenirDAO;
import com.faceooo.nian.model.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("souvenirDAO")
public class SouvenirDAO extends BaseDao implements ISouvenirDAO{



	public List<ImageDTO> querySouImagesCode(String souvenirid) throws SQLException {
//		return this.executeQueryForList("querySouImagesCode", souvenirid);
        return null;
	}

	public List<SouvenirtypeDTO> queryUserSouListForHome(UserinfoDTO userinfo) throws SQLException {
//		return this.executeQueryForList("queryUserSouListForHome", userinfo);
        return null;
	}

	public List<SouvenirDTO> querySouListForSearch(Map<String,String> paramMap) throws SQLException {
//		return this.executeQueryForList("querySouListForSearch", paramMap);
        return null;
	}

	public List<SouvenirDTO> querySouListForSearchName(Map<String,String> paramMap)throws SQLException  {
//		return this.executeQueryForList("querySouListForSearchName", paramMap);
        return null;
	}

	public void deleteSou(String souvenirid) throws SQLException {
//		this.executeDelete("deleteSou", souvenirid);
	}

	public void deleteSoutype(String soutypeid)throws SQLException {
//		this.executeDelete("deleteSoutype", soutypeid);

	}

	public void updateSoutype(SouvenirtypeDTO soutypedto)throws SQLException  {
//		this.executeQueryForUpdate("updateSoutype", soutypedto);
	}

	public void creatSouTypes(SouvenirtypeDTO soutypedto) throws SQLException  {
//		this.executeInsert("creatSouTypes", soutypedto);

	}

	public SouvenirDTO querySouInfoForID(String souvenirid)throws SQLException  {
//		return (SouvenirDTO) this.executeQueryForObject("querySouInfoForID", souvenirid);
        return null;
	}

	public void chargeSoutype(SouvenirDTO soudto)throws SQLException {
//		this.executeQueryForUpdate("chargeSoutype", soudto);
	}

	public void updateSouBaseInfo(SouvenirDTO soudto) throws SQLException {
//		this.executeQueryForUpdate("updateSouBaseInfo", soudto);
	}

	public void createSouRecord(RecordinfoDTO recorddto) throws SQLException {
//		this.executeInsert("createSouRecord", recorddto);

	}

	public void deleteSouRecord(RecordinfoDTO recorddto) throws SQLException {
//		this.executeDelete("deleteSouRecord", recorddto);

	}

	public void updateSouRecord(RecordinfoDTO recorddto) throws SQLException {
//		this.executeQueryForUpdate("updateSouRecord", recorddto);

	}

    public void createSouvenir(SouvenirDTO soudto) throws SQLException {
//        this.executeInsert("createSouvenir", soudto);
    }

    public void createSouImage(ImageDTO imagedto) throws SQLException {
//        this.executeInsert("createSouImage", imagedto);
    }

    public void deleteImage(String imageId)  throws SQLException {
//        this.executeDelete("deleteImage",imageId);
    }

    public void createClearQiniu(ClearQiniuDTO clearqiniudto)  throws SQLException{
//        this.executeInsert("createClearQiniu",clearqiniudto);
    }

    public List<SouvenirDTO> getSouvenirListForType(SouvenirDTO souvenirDTO)  throws SQLException {
//        return this.executeQueryForList("getSouvenirListForType",souvenirDTO);
        return null;
    }
}
