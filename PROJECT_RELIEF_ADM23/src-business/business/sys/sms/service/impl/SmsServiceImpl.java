package business.sys.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.CommSms;
import business.com.CommSmsUtils;
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.util.CommUtils;

/**
 * [서비스클래스] - SMS이력을 관리하는 Service 구현 클래스
 * 
 * @class   : SmsServiceImpl
 * @author  : LSH
 * @since   : 2021.09.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("SmsService")
@SuppressWarnings({"all"})
public class SmsServiceImpl extends BaseService implements SmsService {

    @Resource(name = "SmsDAO")
    private SmsDAO smsDAO;
	
    /**
     * SMS이력 페이징목록조회
     */
    @Override
    public PaginatedArrayList listSms(SmsVO smsVO, int currPage, int pageSize) throws Exception {
    	return smsDAO.listSms(smsVO, currPage, pageSize);
    }

    /**
     * SMS이력 목록조회
     */
    @Override
    public List listSms(SmsVO smsVO) throws Exception {
    	return smsDAO.listSms(smsVO);
    }

    /**
     * SMS이력 상세조회
     */
	@Override
	public SmsVO viewSms(SmsVO smsVO) throws Exception {
		return smsDAO.viewSms(smsVO);
	}

    /**
     * SMS이력 등록
     */
    private int _regiSms(SmsVO smsVO) throws Exception {
        return smsDAO.regiSms(smsVO);
    }
    
    /**
     * SMS이력 삭제
     */
    private int _deltSms(SmsVO smsVO) throws Exception {
        return smsDAO.deltSms(smsVO);
    }

    /**
     * SMS이력 다중처리 (삭제,발송)
     */
    public String saveSms(SmsVO smsVO) throws Exception {
		
		if (smsVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		List<SmsVO> rows = smsVO.getSmsList();
		String mode = smsVO.getMode();
		
		if (rows == null || rows.size() == 0)
			throw processException("error.comm.notTarget");
		
		for (SmsVO row : rows) {
			
			if (CommConst.DELETE.equals(mode)) {
				// SMS이력 삭제
				ret += _deltSms(row);
			}
			else if (CommConst.INSERT.equals(mode)) {

				// SMS 타입구분 (장문,단문구분)
				row.setLngtSeCd  (CommConst.getSmsLengthCode(smsVO.getTrsmCn()));
				row.setTrsmCn    (smsVO.getTrsmCn        ()); // 발송내용
				row.setTrnsterNo (smsVO.getTrnsterNo     ()); // 발송번호
				row.setSmsSeCd   (CommConst.SMS_SYSTEM_CODE); // 전송구분 (SS99 : 관리자전송)
				row.setTrnsterNm (CommConst.SMS_TRANSFER_NM); // 발송자명 (관리자)
				row.setTrsmStusCd(CommConst.SMS_RESULT_WAIT); // 발송대기

				// SMS이력 발송
				ret += sendSms(row);
			}
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
    }

    /**
     * 2021.10.26 LSH
     * SMS 발송처리
     */
	@Override
	public int sendSms(SmsVO smsVO) throws Exception {
		
		if (smsVO == null)
			throw processException("error.comm.notTarget");

		// 발송실행여부
		boolean enable = false;
		// 관리자전송인 경우
		if (CommConst.SMS_SYSTEM_CODE.equals(smsVO.getSmsSeCd())) {
			enable = true;
		}
		// 비밀번호 전송인 경우
		else if (CommConst.SMS_USER_PSW.equals(smsVO.getSmsSeCd())) {
			enable = true;
		}
		// 업무용 SMS 발송 활성화이면
		else if (CommConst.enableBizSMS()) {
			enable = true;
		}
		
		// SMS 타입구분
		smsVO.setLngtSeCd(CommConst.getSmsLengthCode(smsVO.getTrsmCn()));
		// 전송메세지 객체 생성
		CommSms sms = CommSms.builder()
				.to       (smsVO.getRcvrNo     ()) // 수신번호
				.from     (smsVO.getTrnsterNo  ()) // 발신번호
				.msg      (smsVO.getTrsmCn     ()) // 전송메세지
				.msgType  (smsVO.getLngtSeCd   ()) // 단문/장문 구분
				.sendType (CommConst.SMS_SEND_NOW) // 전송타입(즉시발송)
				.build();
		
		// 2021.12.27 소개이미지 포함여부 설정
		if (CommConst.includeSMSImage()) {
			sms.setImage     (CommSmsUtils.getIntroImage());
			sms.setMsgType   (CommConst.SMS_LENGTH_MMS);
			smsVO.setLngtSeCd(CommConst.SMS_LENGTH_MMS); // MMS 타입
		}

		// SMS 발송가능시에만 발송처리
		if (enable) {
			// SMS 발송 처리
			CommSmsUtils.send(sms);
			// SMS 결과코드가 있으면
			if (sms.getRcode() != null) {
				// SMS 발송결과 처리
				smsVO.setTrsmStusCd(sms.getRcode());
			}
		}
		
    	// 비밀번호 전송 로그저장시 숨김.
    	if (CommUtils.nvlTrim(smsVO.getSmsSeCd()).equals("SS05") ) {
    		smsVO.setTrsmCn("[환경산업기술원] 임시비밀번호는 ****** 입니다.");
    	}
    	
		// SMS이력 등록
    	return _regiSms(smsVO);
	}

    /**
     * SMS 수신대상자 목록 조회
     */
	@Override
	public List listSmsTarget(SmsVO smsVO) throws Exception {
		return smsDAO.listSmsTarget(smsVO);
	}
	
    /**
     * 2022.01.19 LSH ADD
     * SMS 수신대상자 페이징목록조회
     */
    @Override
    public PaginatedArrayList listSmsTarget(SmsVO smsVO, int currPage, int pageSize) throws Exception {
    	return smsDAO.listSmsTarget(smsVO, currPage, pageSize);
    }
}