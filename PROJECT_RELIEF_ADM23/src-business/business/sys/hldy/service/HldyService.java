package business.sys.hldy.service;

import java.util.List;

/**
 * [서비스인터페이스] - 공휴일관리를 관리하는 Service Interface
 * 
 * @class   : HldyService
 * @author  : KSH
 * @since   : 2023.02.02
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface HldyService {
	
	/**
     * 공휴일관리 목록조회
     */
	public List listHldy(HldyVO hldyVO) throws Exception;
	
	/**
     * 공휴일관리 페이징 목록조회
     */
	public List listHldy(HldyVO hldyVo, int currPage, int pageSize) throws Exception;
	
	/**
     * 공휴일관리 상세조회
     */
	public HldyVO viewHldy(HldyVO hldyVo) throws Exception;
	
	/**
     * 공휴일관리 등록,수정,삭제
     */
	public String saveHldy(HldyVO hldyVo) throws Exception;
	
	
}
