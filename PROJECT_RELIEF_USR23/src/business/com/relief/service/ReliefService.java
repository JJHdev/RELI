package business.com.relief.service;

import java.util.List;
import java.util.Map;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 구제급여신청 Service Interface
 * 
 * @class   : ReliefService
 * @author  : LSH
 * @since   : 2021.10.05
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface ReliefService {

    /**
     * 구제급여신청 페이징목록 조회
     */
    public PaginatedArrayList listRelief(ReliefVO reliefVO, int currPage, int pageSize) throws Exception;

    /**
     * 구제급여신청 목록조회
     */
    public List listRelief(ReliefVO reliefVO) throws Exception;
    
    /**
     * 2022.01.05 LSH
     * 예비조사/본조사 구제급여 대상자목록조회
     */
    public List listReliefTarget(ReliefVO reliefVO) throws Exception;

    /**
     * 구제급여신청 상세조회
     */
    public ReliefVO viewRelief(ReliefVO reliefVO) throws Exception;

    /**
     * 구제급여신청 임시저장/제출하기
     */
    public String saveRelief(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.10.14 LSH
     * 구제급여신청 본인이 작성한 임시저장 KEY 조회 
     */
    public ReliefVO viewReliefAplyLast(ReliefVO reliefVO) throws Exception;

    /**
     * 2022.12.01 LSH
     * 관리자대행으로 구제급여신청 임시저장한 최종 신청정보 KEY 조회
     */
    public ReliefVO viewReliefAplyLastAdmn(ReliefVO reliefVO) throws Exception;
    
    /**
     * 2021.11.08 LSH
     * 마이페이지 - 식별아이디기준 최종 KEY 조회
     */
    public ReliefVO viewReliefByIdntfcId(String idntfcId) throws Exception;

    /**
     * 구제급여신청 상세조회 (피해자정보 포함)
     */
    public ReliefVO viewReliefWidhIdntfc(ReliefVO reliefVO) throws Exception;
    
    /**
     * 2021.10.26
     * 구제급여 접수처리
     */
	public String saveReceipt(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 페이징목록 조회
     */
    public PaginatedArrayList listReliefGive(ReliefVO reliefVO, int currPage, int pageSize) throws Exception;

    /**
     * 2021.10.28 LSH
     * 구제급여 지급현황 목록조회
     */
    public List listReliefGive(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.10.30 LSH
     * 구제급여신청 보완제출하기
     */
    public String saveReliefSplemnt(ReliefVO reliefVO) throws Exception;
    
    /**
     * 2021.11.09 LSH
     * 구제급여 의료비 추가신청처리
     */
	public String saveReliefAdd(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.11.29 LSH
     * 구제급여신청 상태변경처리
     * (예비조사완료 / 본조사완료 / 지급완료 / 반려완료)
     */
    public int updtReliefStatus(String aplyNo, String prgreStusCd, String gsUserNo) throws Exception;

    /**
     * 2021.12.08 LSH
     * 마이페이지 구제급여신청 목록조회
     */
    public List listReliefMypage(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.12.07 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 의료비 총합 조회
     */
    public Map viewReliefGiveMCP(ReliefVO reliefVO) throws Exception;
    /**
     * 2021.12.07 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 요양생활수당 총합 조회
     */
    public Map viewReliefGiveRCP(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.12.09 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 의료비 세부내역 목록조회
     */
    public PaginatedArrayList listReliefGiveMCPDtls(ReliefVO reliefVO, int currPage, int pageSize) throws Exception;
    /**
     * 2021.12.09 LSH
     * 마이페이지 - 구제급여현황 - 구제급여지급현황 요양생활수당 세부내역 목록조회
     */
    public PaginatedArrayList listReliefGiveRCPDtls(ReliefVO reliefVO, int currPage, int pageSize) throws Exception;

    /**
     * 2021.12.13 LSH
     * 의료비 추가신청 최종차수 가져오기
     * 본조사인 경우 접수(04)/본조사(06)건의 최종차수
     * 지급인 경우 본조사(06)/지급(07)건의 최종차수
     */
    public String getReliefLastOder(String aplyNo, String prgreStusCd) throws Exception;

    /**
     * 2021.12.13 LSH
     * 의료비 추가신청 상태변경처리
     * (본조사완료 / 지급완료)
     * @param reliefVO.aplyNo      신청번호
     * @param reliefVO.aplyOder    신청차수
     * @param reliefVO.prgreStusCd 변경상태
     * @param reliefVO.gsUserNo    세션사용자번호
     */
    public int updtReliefOderStatus(ReliefVO reliefVO) throws Exception;
    
    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 페이징목록 조회
     */
    public PaginatedArrayList listReliefOder(ReliefVO reliefVO, int currPage, int pageSize) throws Exception;

    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 목록조회
     */
    public List listReliefOder(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 상세조회
     */
    public ReliefVO viewReliefOder(ReliefVO reliefVO) throws Exception;

    /**
     * 2021.12.14 LSH
     * 구제급여 - 추가의료비신청 접수처리
     */
    public String saveReliefOder(ReliefVO reliefVO) throws Exception;
    
    /**
     * 2021.12.15 LSH
     * 예비조사 기인정자의 진행상태 일괄 업데이트
     * @param reliefVO.bizAreaCd   지역코드
     * @param reliefVO.bizOder     사업차수
     * @param reliefVO.exmnOder    예비조사차수
     * @param reliefVO.prgreStusCd 진행상태
     * @param reliefVO.gsUserNo    세션사용자번호
     */
    public int updtReliefStatusLgcyAll(ReliefVO reliefVO) throws Exception;
}
