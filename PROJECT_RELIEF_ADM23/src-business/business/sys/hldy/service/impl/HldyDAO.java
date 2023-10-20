package business.sys.hldy.service.impl;

import java.util.List;

/**
 * [DAO클래스] - 공휴일관리를 관리하는 DAO 클래스
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
 * @class   : HdlyDAO
 * @author  : KSH
 * @since   : 2023.02.03
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */
import org.springframework.stereotype.Repository;

import business.sys.hldy.service.HldyVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

@Repository("HldyDAO")
@SuppressWarnings({"all"})
public class HldyDAO extends BaseDAO {
	
	/**
     * 공휴일관리 목록 조회
     */
	public List listHldy(HldyVO hldyVO) {
		return list("Hldy.listHldy", hldyVO);
	}
	/**
     * 공휴일관리 페이징목록 조회
     */
	public PaginatedArrayList listHldy(HldyVO hldyVO, int currPage, int pageSize) {
		return pageList("Hldy.listHldy", hldyVO, currPage, pageSize);
	}
	
	/**
     * 공휴일관리 상세조회
     */
	public HldyVO viewHldy(HldyVO hldyVO) {
        return (HldyVO)view("Hldy.viewHldy", hldyVO);
    }
	
	/**
     * 공휴일관리 수정
     */
	public int updtHldy(HldyVO hldyVo) {
		return update("Hldy.updtHldy", hldyVo);
	}
	/**
     * 공휴일관리 삭제
     */
	public int deltHldy(HldyVO hldyVo) {
		return delete("Hldy.deltHldy", hldyVo);
	}
	/**
     * 공휴일관리 등록
     */
	public int regiHldy(HldyVO hldyVo) {
		return insert("Hldy.regiHldy", hldyVo);
	}
	
	
}
