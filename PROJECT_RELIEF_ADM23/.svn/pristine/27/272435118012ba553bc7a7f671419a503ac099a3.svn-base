 package business.sys.sms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import business.sys.sms.service.SmsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseDAO;

/**
 * [DAO클래스] - SMS이력을 관리하는 DAO 클래스
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
 * @class   : SmsDAO
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자               수정내용
 *  -------    --------    ---------------------------
 *
 */

@Repository("SmsDAO")
@SuppressWarnings({"all"})
public class SmsDAO extends BaseDAO {

    /**
     * SMS이력 페이징목록 조회
     */
    public PaginatedArrayList listSms(SmsVO smsVO, int currPage, int pageSize) {
        return pageList("Sms.listSms", smsVO, currPage, pageSize);
    }

    /**
     * SMS이력 목록 조회
     */
    public List listSms(SmsVO smsVO) {
        return list("Sms.listSms", smsVO);
    }

    /**
     * SMS이력 상세 조회
     */
    public SmsVO viewSms(SmsVO smsVO) {
        return (SmsVO)view("Sms.viewSms", smsVO);
    }

    /**
     * SMS이력 등록
     */
    public int regiSms(SmsVO smsVO) {
        return insert("Sms.regiSms", smsVO);
    }

    /**
     * SMS이력 삭제
     */
    public int deltSms(SmsVO smsVO) {
        return delete("Sms.deltSms", smsVO);
    }

    /**
     * SMS 수신대상자 목록 조회
     */
	public List listSmsTarget(SmsVO smsVO) {
		return list("Sms.listSmsTarget", smsVO);
	}
	
    /**
     * 2022.01.19 LSH ADD
     * SMS 수신대상자 페이징목록조회
     */
    public PaginatedArrayList listSmsTarget(SmsVO smsVO, int currPage, int pageSize) {
        return pageList("Sms.listSmsTarget", smsVO, currPage, pageSize);
    }
	
}