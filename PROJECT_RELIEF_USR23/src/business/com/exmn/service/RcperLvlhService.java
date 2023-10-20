package business.com.exmn.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 요양생활수당관리 Service Interface
 * 
 * @class   : RcperLvlhService
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface RcperLvlhService {

    /**
     * 요양생활수당관리 목록조회
     */
    public List listRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당관리 상세조회
     */
    public RcperLvlhVO viewRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당관리 등록,수정,삭제
     */
    public int saveRcperLvlh(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당지급상세 목록조회
     */
    public List listRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당지급상세 상세조회
     */
    public RcperLvlhVO viewRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당지급상세 등록,수정,삭제
     */
    public int saveRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception;
}