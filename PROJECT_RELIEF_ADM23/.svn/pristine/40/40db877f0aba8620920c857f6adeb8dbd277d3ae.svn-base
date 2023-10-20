 package business.com.exmn.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import business.com.exmn.service.RcperLvlhVO;
import common.base.BaseDAO;

/**
 * [DAO클래스] - 요양생활수당지급상세을 관리하는 DAO 클래스
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
 * @class   : RcperLvlhDtlsDAO
 * @author  : LSH
 * @since   : 2021.12.06
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("RcperLvlhDtlsDAO")
@SuppressWarnings({"all"})
public class RcperLvlhDtlsDAO extends BaseDAO {

    /**
     * 요양생활수당지급상세 목록 조회
     */
    public List listRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return list("RcperLvlhDtls.listRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 상세 조회
     */
    public RcperLvlhVO viewRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return (RcperLvlhVO)view("RcperLvlhDtls.viewRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 등록
     */
    public int regiRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlhDtls.regiRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 이력등록
     */
    public int regiRcperLvlhDtlsHst(RcperLvlhVO rcperLvlhVO) {
        return insert("RcperLvlhDtls.regiRcperLvlhDtlsHst", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 수정
     */
    public int updtRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return update("RcperLvlhDtls.updtRcperLvlhDtls", rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 삭제
     */
    public int deltRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) {
        return delete("RcperLvlhDtls.deltRcperLvlhDtls", rcperLvlhVO);
    }

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
     * 기간내 지급된 금액은 제외
     */
    public long getRcperLvlhLumpSum(RcperLvlhVO rcperLvlhVO) {
        return (Long)view("RcperLvlhDtls.getRcperLvlhLumpSum", rcperLvlhVO);
    }

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
    public long getRtroactSum(RcperLvlhVO rcperLvlhVO) {
        return (Long)view("RcperLvlhDtls.getRtroactSum", rcperLvlhVO);
    }
    
    /**
     * 2023.01.02 LSH
     * 소급지급 여부 확인
     * @param rcperLvlhVO.bizAreaCd     사업지역코드
     * @param rcperLvlhVO.bizOder       사업차수
     * @param rcperLvlhVO.exmnOder      조사차수
     * @param rcperLvlhVO.aplyNo        신청번호
     * @param rcperLvlhVO.giveSeCd      지급구분 (소급)
     * @param rcperLvlhVO.giveYr        지급년도 (선택사항)
     * @param rcperLvlhVO.giveMm        지급월 (선택사항)
     */
    public boolean checkRcperLvlhRtroact(RcperLvlhVO rcperLvlhVO) {
        return (Boolean)view("RcperLvlhDtls.checkRcperLvlhRtroact", rcperLvlhVO);
    }
    
    /**
     * 2023.01.02 LSH
     * 식별ID,조사차수 기준 본조사정보 조회
     * @param rcperLvlhVO.idntfcId      식별ID
     * @param rcperLvlhVO.exmnOder      본조사차수
     * 
     * @return rcperLvlhVO.bizAreaCd    사업지역코드
     * @return rcperLvlhVO.bizOder      사업차수
     * @return rcperLvlhVO.exmnOder     조사차수
     * @return rcperLvlhVO.aplyNo       신청번호
     * @return rcperLvlhVO.giveAmt      월지급금액
     * @return rcperLvlhVO.giveUseYn    지급등록가능여부
     * @return rcperLvlhVO.giveBgngYm   본조사의 지급기간 시작년월
     * @return rcperLvlhVO.giveEndYm    본조사의 지급기간 종료년월
     */
    public RcperLvlhVO viewRcperLvlhMnsvy(RcperLvlhVO rcperLvlhVO) {
        return (RcperLvlhVO)view("RcperLvlhDtls.viewRcperLvlhMnsvy", rcperLvlhVO);
    }
}