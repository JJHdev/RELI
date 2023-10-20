package business.com.support.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.com.support.service.LwstVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 취약계층소송지원을 관리하는 DAO 클래스
 * 
 * 사용 가능한 DAO Statement Method 1. list : 리스트 조회시 사용함. 2. pageListOra : 페이징처리용
 * 리스트조회시 사용함. for Oracle, Tibero 3. view : 단건조회, 상세조회시 사용함. 4. save : INSERT,
 * UPDATE, DELETE 모두 사용가능. (Return Type : Integer) 5. insert : INSERT (Return
 * String : Key 채번 사용함.) 6. update : UPDATE (Return Type : Integer) 7. delete :
 * DELETE (Return Type : Integer)
 * 
 *
 * @class : LwstDAO
 * @author : 한금주
 * @since : 2021.10.02
 * @version : 1.0
 *
 *          수정일 수정자 수정내용 ------- -------- ---------------------------
 *
 */

@Repository("SprtAplyDAO")
@SuppressWarnings({ "all" })
public class SprtAplyDAO extends BaseDAO {

	public Integer delSprtList(LwstVO lwstVO) {
		return delete("SprtAply.delSprtList", lwstVO);
	}

	public Integer delSprtListAplyNo(LwstVO lwstVO) {
		return delete("SprtAply.delSprtListAplyNo", lwstVO);
	}

	public int regiSprtList(LwstVO lwstVO) {
		return insert("SprtAply.regiSprtList", lwstVO);
	}

	public int udtSprtList(LwstVO lwstVO) {
		return update("SprtAply.udtSprtList", lwstVO);
	}

	public List listSprtList(LwstVO lwstVO) {
		return list("SprtAply.listSprtList", lwstVO);
	}

	public LwstVO viewSprtList(LwstVO lwstVO) {
		return (LwstVO)view("SprtAply.viewSprtList", lwstVO);
	}

	public List listLwstSprtAply(LwstVO lwstVO) {
		return list("SprtAply.listLwstSprtAply",lwstVO);
	}
}