package business.adm.gis.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.adm.gis.service.AbstrctAddrVO;
import business.com.relief.service.IdntfcVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 초본주소 및 GIS서비스를 관리하는 DAO 클래스
 * 
 * 사용 가능한  DAO Statement Method
 * 1. list          : 리스트 조회시 사용함.
 * 2. pageListOra   : 페이징처리용 리스트조회시 사용함. for Oracle, Tibero
 * 3. view          : 단건조회, 상세조회시 사용함.
 * 4. save          : INSERT, UPDATE, DELETE 모두 사용가능. (Return Type : Integer)
 * 5. insert        : INSERT (Return String : Key 채번 사용함.)
 * 6. update        : UPDATE (Return Type : Integer)
 * 7. delete        : DELETE (Return Type : Integer)
 * 
 *
 * @class   : GisDAO
 * @author  : JWH
 * @since   : 2022.11.14
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("GisDAO")
@SuppressWarnings({"all"})
public class GisDAO extends BaseDAO {
	
	/**
     * 초본주소 페이징목록조회
     */
	public PaginatedArrayList listAbstrctAddr(AbstrctAddrVO abstrctAddrVO, int currPage, int pageSize) {
		return pageList("AbstrctAddr.listAbstrctAddr", abstrctAddrVO, currPage, pageSize);
	}
	
	/**
     * 초본주소 목록조회
     */
	public List listAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return list("AbstrctAddr.listAbstrctAddr", abstrctAddrVO);
	}
	/**
	 * 피해자 초본주소 상세조회
	 */
	public AbstrctAddrVO viewAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return (AbstrctAddrVO)view("AbstrctAddr.viewAbstrctAddr", abstrctAddrVO);
	}
	
	/**
     * 초본주소 등록
     */
	public int regiAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return insert("AbstrctAddr.regiAbstrctAddr", abstrctAddrVO);
	}
	
	/**
     * 피해자 초본주소 수정
     */
	public int updtAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return update("AbstrctAddr.updtAbstrctAddr", abstrctAddrVO);
	}
	
	/**
     * 피해자 초본주소 삭제
     */
	public int deltAbstrctAddr(AbstrctAddrVO abstrctAddrVO) {
		return delete("AbstrctAddr.deltAbstrctAddr", abstrctAddrVO);
	}

	/**
     * 피해지역 목록 조회
     */
	public List<Map> getlistBizArea() {
		return selectList("EnvpAffc.getlistBizArea");
	}
	
	/**
     * 피해지역 목록 조회
     */
	public List<Map> getOneBizArea(Map paramMap) {
		return selectList("EnvpAffc.getOneBizArea", paramMap);
	}
	
    /**
     * 피해자정보 페이징목록 조회
     */
    public PaginatedArrayList listIdntBizArea(Map paramMap, int currPage, int pageSize) {
        return pageList("EnvpAffc.listIdntBizArea", paramMap, currPage, pageSize);
    }

    /**
     * 피해자정보 목록 조회
     */
    public List listIdntBizArea(Map paramMap) {
        return list("EnvpAffc.listIdntBizArea", paramMap);
    }

    
    public List listEnvpAffcLoc(Map paramMap){
    	return list("EnvpAffc.listEnvpAffcLoc", paramMap);
    }
    
	/**
	 * GIS 환경 오염 영향분석 - 클릭시 가장가까운 point 가져오기
	 */
    public List<Map> getCoordinateByOnclick(Map paramMap){
    	return selectList("EnvpAffc.getCoordinateByOnclick", paramMap);
    }
    
	
	
}
