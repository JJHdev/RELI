package business.com.exmn.service;

import java.util.List;

import common.file.FileInfo;

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
 * 2022.12.27   LSH         2차개발 - TB_RCPER_LVLH_MNG 사용제거
 * 2022.12.27   LSH         2차개발 - TB_RCPER_LVLH_GRD 사용 (피해등급산정)
 */
@SuppressWarnings("all")
public interface RcperLvlhService {

    /**
     * 요양생활수당평가등급 목록조회
     */
    public List listRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 평가점수 / 최종피해등급 산출
     * @param rcperLvlhVO.svrtyScreStr 점수를 파이프라인으로 묶은 문자열
     *                                 ex) 31.25|12.5|56.25|62.5|37.5
     * @return lastDmgeScre  최종평가점수
     * @return lastDmgeGrdCd 최종피해등급
     */
    public RcperLvlhVO viewDmgeGrdScre(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당평가등급 상세조회
     */
    public RcperLvlhVO viewRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 요양생활수당평가등급 등록,수정,삭제
     */
    public int saveRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception;

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

    /**
     * 요양생활수당 지급기한 등록,수정
     */
    public int saveRcperLvlhDeadline(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 2022.12.28 LSH
     * 요양생활수당 지급해야할 일시금금액 조회
     * @param rcperLvlhVO.bizAreaCd  사업지역코드
     * @param rcperLvlhVO.bizOder    사업차수
     * @param rcperLvlhVO.exmnOder   조사차수
     * @param rcperLvlhVO.aplyNo     신청번호
     * @param rcperLvlhVO.giveBgngYm 지급기간 (시작)
     * @param rcperLvlhVO.giveEndYm  지급기간 (종료)
     * 
     */
    public long getRcperLvlhLumpSum(RcperLvlhVO rcperLvlhVO) throws Exception;

    /**
     * 2022.12.29 LSH
     * 요양생활수당 소급기간 내의 지급할 금액 합산 조회
     * @param rcperLvlhVO.bizAreaCd     사업지역코드
     * @param rcperLvlhVO.bizOder       사업차수
     * @param rcperLvlhVO.exmnOder      조사차수
     * @param rcperLvlhVO.aplyNo        신청번호
     * @param rcperLvlhVO.rtroactBgngYm 소급기간 (시작)
     * @param rcperLvlhVO.rtroactEndYm  소급기간 (종료)
     * 
     */
    public long getRtroactSum(RcperLvlhVO rcperLvlhVO) throws Exception;
    
    /**
     * 2023.01.03 LSH
     * 요양생활수당 지급일자 일괄등록 엑셀로드
     */
    public int loadRcperLvlhDtls(RcperLvlhVO rcperLvlhVO, FileInfo fileInfo) throws Exception;
    
    /**
     * 2023.01.02 LSH
     * 요양생활수당 지급년월에 기지급 여부
     */
    public boolean existRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception;
    
    /**
     * 2023.01.02 LSH
     * 요양생활수당 소급지급 여부 확인
     */
    public boolean checkRcperLvlhRtroact(RcperLvlhVO rcperLvlhVO) throws Exception;
}