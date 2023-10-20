package business.com.relief.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 피해자정보 Service Interface
 * 
 * @class   : IdntfcService
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface IdntfcService {

    /**
     * 피해자정보 페이징목록 조회
     */
    public PaginatedArrayList listIdntfc(IdntfcVO idntfcVO, int currPage, int pageSize) throws Exception;

    /**
     * 피해자정보 목록조회
     */
    public List listIdntfc(IdntfcVO idntfcVO) throws Exception;

    /**
     * 피해자정보 상세조회
     */
    public IdntfcVO viewIdntfc(String idntfcId) throws Exception;

    /**
     * 피해자정보 등록,수정,삭제
     */
    public String saveIdntfc(IdntfcVO idntfcVO) throws Exception;

    /**
     * 피해자정보 등록
     */
	public int regiIdntfc(IdntfcVO idntfcVO) throws Exception;

    /**
     * 피해자정보 수정
     */
	public int updtIdntfc(IdntfcVO idntfcVO) throws Exception;

    /**
     * 피해자정보 삭제
     */
	public int deltIdntfc(IdntfcVO idntfcVO) throws Exception;

    /**
     * 2021.10.19 LSH
     * 성명/생년월일 기준 피해자정보 ID 조회
     */
    public String getIdntfcExistNo(IdntfcVO idntfcVO) throws Exception;

    /**
     * 2021.11.01 LSH
     * 구제급여 신청접수 처리 (식별ID 생성 반환)
     */
    public String saveIdntfcReceipt(IdntfcVO idntfcVO) throws Exception;
    
	/**
	 * 2021.11.08 LSH
	 * 주민등록번호 기준 인증 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm   : 성명
	 * @param  (필수) idntfcVO.idntfcId   : 식별아이디
	 * @param  (필수) idntfcVO.sufrerRrno : 주민등록번호
	 * */
	public boolean checkIdntfcByRrno(IdntfcVO idntfcVO) throws Exception;

	/**
	 * 2021.11.08 LSH
	 * 휴대전화번호 기준 인증 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm      : 성명
	 * @param  (필수) idntfcVO.sufrerBrdt    : 생년월일
	 * @param  (필수) idntfcVO.sufrerSxdst   : 성별
	 * @param  (필수) idntfcVO.sufrerMbtelNo : 휴대전화번호
	 * */
	public boolean checkIdntfcByMbtelNo(IdntfcVO idntfcVO) throws Exception;

	/**
	 * 2021.11.08 LSH
	 * 이름/식별ID기준 일치하는 사용자가 있는지 확인 (true / false)
	 * @param  (필수) idntfcVO.sufrerNm   : 성명
	 * @param  (필수) idntfcVO.idntfcId   : 식별아이디
	 * */
	public boolean existIdntfc(IdntfcVO idntfcVO) throws Exception;
}