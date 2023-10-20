package business.com.support.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public interface LwstService {

	//취약계층 소송지원 신청
	public LwstVO openLwst(String aplcntNo) throws Exception;

	//취약계층 소송지원 신청서 저장
	public Integer saveUserLwst(LwstVO lwstVO) throws Exception;

	//취약계층 소송지원 신청 리스트
	public PaginatedArrayList listLwst(LwstVO lwstVO, int currPage, int pageSize) throws Exception;

	//취약계층 소송지원 신청 리스트
	public List listLwst(LwstVO lwstVO) throws Exception;

	//취약계층 소송지원 신청 상세보기
	public LwstVO viewListLwst(String aplyNo) throws Exception;

	//취약계층 소송지원 신청 접수
	public Integer updateLwstIncdnt(LwstVO lwstVO) throws Exception;

	//취약계층 소송지원 신청 취소
	public Integer cancelLwstIncdnt(LwstVO lwstVO) throws Exception;

	//소송 등록
	public String regiLwstIncdnt(LwstVO lwstVO) throws Exception;

	//취약계층 소송 개요
	public List listLwstIncdnt(LwstVO lwstVO, int currPage, int Size) throws Exception;

	public List listLwstIncdnt(LwstVO lwstVO) throws Exception;

	public LwstVO viewListLwstIncdnt(int incdntMngNo) throws Exception;

	//소송 취소 현황
	public PaginatedArrayList listLwstRtrcn(LwstVO lwstVO, int currPage, int pageSize) throws Exception;

	public List listLwstRtrcn(LwstVO lwstVO) throws Exception;

	//취약계층 소송지원 취소 상세보기
	public LwstVO viewlistLwstRtrcn(String aplyNo) throws Exception;

	//취약계층 소송 진행현황 상세보기
	public List listLwstPrgre(LwstVO lwstVO, int currPage, int Size) throws Exception;

	public List listLwstPrgre(LwstVO lwstVO) throws Exception;

	public LwstVO viewlistLwstPrgre(String aplyNo) throws Exception;

	public ArrayList searchLwstNo(HashMap paramMap)throws Exception;

	public ArrayList listAllLwst(HashMap paramMap) throws Exception;

	public String regiLwstAply(LwstVO lwstVO) throws Exception;


	/**
     * 2021.12.16 CSLEE
     *  신청정보와 소송사건 정보 목록 조회
     */
    public List listLwstAplyIncdnt(LwstVO lwstVO) throws Exception ;

	/**
     * 2021.12.16 CSLEE
     *  신청정보와 소송사건 정보 조회 (향후기일 정보는 최신 정보 1건 기준
     *  [USR>마이페이지>취약계층 소송지원 현황] 화면을 위해 추가
     */
    public LwstVO viewLwstAplyIncdnt(LwstVO lwstVO) throws Exception ;

    /**
     * 2021.12.17 CSLEE
     *  특정 신청정보의  향후일자 목록 정보를 향후기일 최신 일자 순으로 조회
     */
    public List listLwstAplyIncdntDetail(LwstVO lwstVO) throws Exception;
}