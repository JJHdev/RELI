 package business.com.support.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.support.service.LwstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 취약계층소송지원을 관리하는 DAO 클래스
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
 * @class   : LwstDAO
 * @author  : 한금주
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("LwstDAO")
@SuppressWarnings({"all"})
public class LwstDAO extends BaseDAO {

	public LwstVO openLwst(String aplcntNo) {
		return selectOne("Lwst.openLwst",aplcntNo);
	}

	public Integer saveUserLwst(LwstVO lwstVO) {
		return insert("Lwst.saveUserLwst", lwstVO);
	}

	public PaginatedArrayList listLwst(LwstVO lwstVO, int currPage, int pageSize) {
		return pageList("Lwst.listLwst",lwstVO,currPage,pageSize);
	}

	public List listLwst(LwstVO lwstVO) {
		return list("Lwst.listLwst",lwstVO);
	}

	public LwstVO viewListLwst(String aplyNo) {
		return (LwstVO)view("Lwst.viewListLwst",aplyNo);
	}

	public Integer updateLwstIncdnt(LwstVO lwstVO) {
		return update("Lwst.updateLwstIncdnt",lwstVO);
	}

	public Integer cancelLwstIncdnt(LwstVO lwstVO) {
		return update("Lwst.cancelLwstIncdnt",lwstVO);
	}

	public Integer saveLwstIncdnt(LwstVO lwstVO) {
		return insert("Lwst.saveLwstIncdnt", lwstVO);
	}

	public List listLwstIncdnt(LwstVO lwstVO) {
		return list("Lwst.listLwstIncdnt",lwstVO);
	}

	public LwstVO viewListLwstIncdnt(int incdntMngNo) {
		return (LwstVO)view("Lwst.viewListLwstIncdnt",incdntMngNo);
	}

	public int udtLwstIncdnt(LwstVO lwstVO) {
		return update("Lwst.udtLwstIncdnt",lwstVO);
	}

	public int deleteLwstIncdnt(LwstVO lwstVO) {
		return delete("Lwst.deleteLwstIncdnt",lwstVO);
	}

	public PaginatedArrayList listLwstRtrcn(LwstVO lwstVO, int currPage, int pageSize) {
		return pageList("Lwst.listLwstRtrcn",lwstVO,currPage,pageSize);
	}

	public List listLwstRtrcn(LwstVO lwstVO) {
		return list("Lwst.listLwstRtrcn",lwstVO);
	}

	public LwstVO viewlistLwstRtrcn(String aplyNo) {
		return (LwstVO)view("Lwst.viewlistLwstRtrcn",aplyNo);
	}

	public List listLwstIncdnt(LwstVO lwstVO, int currPage, int pageSize) {
		return pageList("Lwst.listLwstIncdnt",lwstVO,currPage,pageSize);
	}

	public PaginatedArrayList listLwstPrgre(LwstVO lwstVO, int currPage, int pageSize) {
	    return pageList("Lwst.listLwstPrgre",lwstVO,currPage,pageSize);
	}

	public List listLwstPrgre(LwstVO lwstVO) {
		return list("Lwst.listLwstPrgre",lwstVO);
	}

	public LwstVO viewlistLwstPrgre(String aplyNo) {
		return (LwstVO)view("Lwst.viewlistLwstPrgre",aplyNo);
	}

	public ArrayList searchLwstNo(HashMap paramAMp) {
		return (ArrayList)selectList("Lwst.searchLwstNo",paramAMp);
	}

	public ArrayList listAllLwst(HashMap paramMap) {
		return (ArrayList)selectList("Lwst.listAllLwst",paramMap);
	}

	public int deleteAplyList(LwstVO lwstVO) {
		return delete("Lwst.deleteAplyList",lwstVO);
	}

	public int saveAplyLwstList(LwstVO lwstVO) {
		return insert("Lwst.saveAplyLwstList", lwstVO);
	}

	public int udtAplyLwstList(LwstVO lwstVO) {
		return update("Lwst.udtAplyLwstList",lwstVO);
	}

	/**
	 * 2021.12.16 CSLEE
     *  신청정보와 소송사건 정보 목록 조회
	 */
	public List listLwstAplyIncdnt(LwstVO lwstVO) {
        return list("Lwst.listLwstAplyIncdnt",lwstVO);
    }

	/**
     * 2021.12.16 CSLEE
     *  신청정보와 소송사건 정보 조회 (향후기일 정보는 최신 정보 1건 기준
     *  [USR>마이페이지>취약계층 소송지원 현황] 화면을 위해 추가
     */
    public LwstVO viewLwstAplyIncdnt(LwstVO lwstVO) {
        return (LwstVO)view("Lwst.viewLwstAplyIncdnt",lwstVO);
    }

    /**
     * 2021.12.17 CSLEE
     *  특정 신청정보의  향후일자 목록 정보를 향후기일 최신 일자 순으로 조회
     */
    public List listLwstAplyIncdntDetail(LwstVO lwstVO) {
        return list("Lwst.listLwstAplyIncdntDetail",lwstVO);
    }
}