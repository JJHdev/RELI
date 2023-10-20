package business.com.support.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import business.com.exmn.service.ResiHstVO;
import business.sys.user.service.InfoIntrlckVO;
import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 취약계층소송지원을 관리하는 Service Interface
 * 
 * @class   : LwstService
 * @author  : 한금주
 * @since   : 2021.10.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface DtlService {

	//향후기일 등록 수정
	public Integer saveDtlsList(List<LwstVO> dtlsList, LwstVO lwstVO) throws Exception;

	//향후기일 관리번호 기준삭제
	public Integer deltDtlsListByMngNo(LwstVO lwstVO) throws Exception;

	//향후기일 목록 조회
	public List listDtlsList(LwstVO lwstVO) throws Exception;
	
	//향후기일 상세조회
    public LwstVO viewDtlsList(LwstVO lwstVO) throws Exception;

}