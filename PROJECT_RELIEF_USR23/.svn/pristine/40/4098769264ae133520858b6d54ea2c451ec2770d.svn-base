 package business.com.relief.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.bbs.service.BbsVO;
import business.com.relief.service.IdntfcVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 피해자정보을 관리하는 DAO 클래스
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
 * @class   : IdntfcDAO
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("IdntfcDAO")
@SuppressWarnings({"all"})
public class IdntfcDAO extends BaseDAO {

    /**
     * 피해자정보 페이징목록 조회
     */
    public PaginatedArrayList listIdntfc(IdntfcVO idntfcVO, int currPage, int pageSize) {
        return pageList("Idntfc.listIdntfc", idntfcVO, currPage, pageSize);
    }

    /**
     * 피해자정보 목록 조회
     */
    public List listIdntfc(IdntfcVO idntfcVO) {
        return list("Idntfc.listIdntfc", idntfcVO);
    }

    /**
     * 피해자정보 상세 조회
     */
    public IdntfcVO viewIdntfc(String idntfcId) {
        return (IdntfcVO)view("Idntfc.viewIdntfc", idntfcId);
    }

    /**
     * 피해자정보 등록
     */
    public int regiIdntfc(IdntfcVO idntfcVO) {
        return insert("Idntfc.regiIdntfc", idntfcVO);
    }

    /**
     * 2021.10.14 LSH
     * 피해자정보 이력저장
     */
    public int regiIdntfcHst(IdntfcVO idntfcVO) {
        return insert("Idntfc.regiIdntfcHst", idntfcVO);
    }

    /**
     * 피해자정보 수정
     */
    public int updtIdntfc(IdntfcVO idntfcVO) {
        return update("Idntfc.updtIdntfc", idntfcVO);
    }

    /**
     * 피해자정보 삭제
     */
    public int deltIdntfc(IdntfcVO idntfcVO) {
        return delete("Idntfc.deltIdntfc", idntfcVO);
    }

    /**
     * 2021.10.19 LSH
     * 성명/생년월일 기준 피해자정보 KEY 조회
     */
    public String getIdntfcExistNo(IdntfcVO idntfcVO) {
        return (String)view("Idntfc.getIdntfcExistNo", idntfcVO);
    }

    /**
     * 2021.11.01 LSH
     * 식별ID 신규생성
     */
    public String getCreateIdntfcId() {
        return (String)view("Idntfc.getCreateIdntfcId", null);
    }
    
	/**
	 * 2021.11.08 LSH
	 * 주민등록번호 기준 인증 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm   : 성명
	 * @param  (필수) idntfcVO.idntfcId   : 식별아이디
	 * @param  (필수) idntfcVO.sufrerRrno : 주민등록번호
	 * */
	public boolean checkIdntfcByRrno(IdntfcVO idntfcVO) {
		return (Boolean)view("Idntfc.checkIdntfcByRrno", idntfcVO);
	}

	/**
	 * 2021.11.08 LSH
	 * 휴대전화번호 기준 인증 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm      : 성명
	 * @param  (필수) idntfcVO.sufrerBrdt    : 생년월일
	 * @param  (필수) idntfcVO.sufrerSxdst   : 성별
	 * @param  (필수) idntfcVO.sufrerMbtelNo : 휴대전화번호
	 * */
	public boolean checkIdntfcByMbtelNo(IdntfcVO idntfcVO) {
		return (Boolean)view("Idntfc.checkIdntfcByMbtelNo", idntfcVO);
	}

	/**
	 * 2021.11.08 LSH
	 * 이름/식별ID기준 일치하는 사용자가 있는지 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm   : 성명
	 * @param  (필수) idntfcVO.idntfcId   : 식별아이디
	 * */
	public boolean existIdntfc(IdntfcVO idntfcVO) {
		return (Boolean)view("Idntfc.existIdntfc", idntfcVO);
	}
}