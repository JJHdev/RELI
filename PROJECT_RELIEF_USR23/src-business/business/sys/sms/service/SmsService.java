package business.sys.sms.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import commf.paging.PaginatedArrayList;

/**
 * [서비스인터페이스] - SMS이력을 관리하는 Service Interface
 * 
 * @class   : SmsService
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@SuppressWarnings("all")
public interface SmsService {

    /**
     * SMS이력 페이징목록 조회
     */
    public PaginatedArrayList listSms(SmsVO smsVO, int currPage, int pageSize) throws Exception;

    /**
     * SMS이력 목록조회
     */
    public List listSms(SmsVO smsVO) throws Exception;

    /**
     * SMS이력 상세조회
     */
    public SmsVO viewSms(SmsVO smsVO) throws Exception;

    /**
     * SMS 저장처리 (다중삭제,다중발송)
     */
    public String saveSms(SmsVO smsVO) throws Exception;
    
    /**
     * 2021.10.26 LSH
     * SMS 발송처리 (공통)
     */
    public int sendSms(SmsVO smsVO) throws Exception;

    /**
     * SMS수신대상자 목록조회
     */
    public List listSmsTarget(SmsVO smsVO) throws Exception;

    /**
     * 2022.01.19 LSH ADD
     * SMS 수신대상자 페이징목록조회
     */
    public PaginatedArrayList listSmsTarget(SmsVO smsVO, int currPage, int pageSize) throws Exception;
}