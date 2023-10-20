 package business.sys.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.sys.user.service.InfoIntrlckVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 정보연동신청을 관리하는 DAO 클래스
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
 * @class   : InfoIntrlckDAO
 * @author  : 한금주
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("InfoIntrlckDAO")
@SuppressWarnings({"all"})
public class InfoIntrlckDAO extends BaseDAO {

	public ArrayList searchInfoIntrlck(InfoIntrlckVO infoIntrlckVO) {
		return (ArrayList) selectList("JoinInfo.searchInfoIntrlck",infoIntrlckVO);
	}

	public Integer regiInfoIntrlck(InfoIntrlckVO infoIntrlckVO) {
		return insert("JoinInfo.regiInfoIntrlck",infoIntrlckVO);
	}

	public PaginatedArrayList listInfoIntrlck(InfoIntrlckVO infoIntrlckVO, int currPage, int pageSize) {
		return pageList("InfoIntrlck.listInfoIntrlck",infoIntrlckVO,currPage,pageSize);
	}
	public List listInfoIntrlck(InfoIntrlckVO infoIntrlckVO) {
		return list("InfoIntrlck.listInfoIntrlck",infoIntrlckVO);
	}

	public InfoIntrlckVO viewIntrlckList(String aplyNo) {
		return (InfoIntrlckVO)view("InfoIntrlck.viewIntrlckList", aplyNo);
	}

	public Integer updateIntrlckList(InfoIntrlckVO infoIntrlckVO) {
		return update("InfoIntrlck.updateIntrlckList",infoIntrlckVO);
	}
}