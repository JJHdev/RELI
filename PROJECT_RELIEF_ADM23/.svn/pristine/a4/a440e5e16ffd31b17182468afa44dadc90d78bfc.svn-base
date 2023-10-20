package business.com.exmn.service;

import java.util.List;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - 본조사 Service Interface
 * 
 * @class   : MnsvyService
 * @author  : LSH
 * @since   : 2021.11.17
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface MnsvyService {

    /**
     * 본조사 페이징목록 조회
     */
    public PaginatedArrayList listMnsvy(MnsvyVO mnsvyVO, int currPage, int pageSize) throws Exception;

    /**
     * 본조사 목록조회
     */
    public List listMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 상세조회
     */
    public MnsvyVO viewMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 2021.12.03 LSH
     * 본조사 신청번호 기준 최종 조사차수 조회
     */
    public String getMnsvyOderLast(String aplyNo) throws Exception;

    /**
     * 본조사 수정
     * 2022.12.27 private 에서 public 으로 변경 (요양생활수당에서 사용됨)
     */
    public int updtMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 등록,수정,삭제
     */
    public String saveMnsvy(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 요양생활수당 저장
     */
    public String saveMnsvyRcper(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사 심의결과 저장
     */
    public String saveMnsvyRslt(MnsvyVO mnsvyVO) throws Exception;
    /**
     * 2021.12.02
     * 본조사 심의결과 SMS 발송
     */
	public void sendMnsvyRsltSms(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사계획 페이징목록 조회
     */
    public PaginatedArrayList listMnsvyPlan(MnsvyVO mnsvyVO, int currPage, int pageSize) throws Exception;

    /**
     * 본조사계획 목록조회
     */
    public List listMnsvyPlan(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사계획 상세조회
     */
    public MnsvyVO viewMnsvyPlan(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 본조사계획 등록,수정,삭제
     */
    public String saveMnsvyPlan(MnsvyVO mnsvyVO) throws Exception;
    
    /**
     * 본조사계획 NEXT 조사차수 가져오기
     */
	public String getMnsvyPlanNextOder(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 2021.12.09 LSH 마이페이지
     * 신청번호기준 본조사 목록 조회
     */
    public List listMnsvyMypage(String aplyNo) throws Exception;
    
    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 장의비 확정금액 조회
     * 1) 지급기준년도의 중위소득의 89.7%
     * 2) FN_FNRL_CST_AMT 함수 사용
     * 
     * @param fnrlCrtrYr 지급기준년도 (신청년도)
     */
    public long getFnrlCstAmt(String fnrlCrtrYr) throws Exception;

    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 장의비 지급등록처리
     */
    public String saveFnrlCst(MnsvyVO mnsvyVO) throws Exception;

    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 유족보상비,기지급의료비,요양생활수당 조회
     * 1) 유족보상비 확정 = (장의비 * 피해등급별 장의비 지급비율)
     * 2) 유족보상비 잔액 = (유족보상비) - (기지급 의료비 + 요양생활수당) 
     * 3) 장의비의 등급별 지급비율 
     *    - 1등급 1500%
     *    - 2등급 1080%
     *    - 3등급 750%
     *    - 4등급 500%
     *    - 5등급 250%
     * 4) 유족보상비 FN_BRVFM_RWRD_AMT 함수 사용
     * 
     * @param mnsvyVO.bizAreaCd       사업지역코드
     * @param mnsvyVO.bizOder         사업차수
     * @param mnsvyVO.exmnOder        조사차수
     * @param mnsvyVO.aplyNo          신청번호
     * @param mnsvyVO.lastDmgeGrdCd   피해등급
     * @return brvfmRwrdAmt           유족보상비
     * @return mcpDtlsSum             기지급 의료비 합계
     * @return rcperLvlhSum           기지급 요양생활수당 함계
     */
    public MnsvyVO viewBrvfmRwrdAmt(MnsvyVO mnsvyVO) throws Exception;
    
    /**
     * 2023.01.04 LSH
     * 구제급여지급 - 유족보상비 지급등록처리
     */
    public String saveBrvfmRwrd(MnsvyVO mnsvyVO) throws Exception;
}