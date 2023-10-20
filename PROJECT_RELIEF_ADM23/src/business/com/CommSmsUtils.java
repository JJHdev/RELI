package business.com;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;

import common.file.FileDirectory;
import kr.co.uplus.api.java_sdk.exception.OpenApiSDKException;
import kr.co.uplus.api.java_sdk.method.Message;

/**
 * LG U+ OPEN API 기반 SMS 송신 유틸
 * 
 * @class   : CommSmsUtils
 * @author  : LSH
 * @since   : 2021.09.24
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 * 연동방법 : https://openapi.sms.uplus.co.kr/
 * API 키를 고객센터를 통해 발급닫아야 사용가능함.
 * 2021.11.23 14:10 SMS 발송테스트 완료
 */
public class CommSmsUtils {
	
	private static final Log logger = LogFactory.getLog(CommSmsUtils.class);
	
	// 2021.12.27 LSH 로고이미지 실제경로 반환
	public static String getIntroImage() {
		FileDirectory d = FileDirectory.SMSFILE;
		return d.getRealName(CommConst.SMS_INTRO_IMAGE);
	}
	
	// SMS 발송 처리
	public static void send(CommSms sms) throws OpenApiSDKException {
		
		// SMS 사용여부가 FALSE이면
		if (!CommConst.enableSMS())
			return;
		
		Message message = new Message(CommConst.SMS_API_KEY, CommConst.SMS_API_SECRET, CommConst.SMS_ALGORITHM);
		JSONObject json = (JSONObject) message.send(sms.getSendData());

		logger.info("[SMS SEND] RESULT " +json.toString());
		
		// 결과맵핑
		sms.fromJSON(json);
	}

	// SMS 예약 취소
	public static void cancel(CommSms sms) throws OpenApiSDKException {
		
		// SMS 사용여부가 FALSE이면
		if (!CommConst.enableSMS())
			return;
		
		Message message = new Message(CommConst.SMS_API_KEY, CommConst.SMS_API_SECRET, CommConst.SMS_ALGORITHM);
		JSONObject json = (JSONObject) message.cancel(sms.getCancelData());

		logger.info("[SMS CANCEL] RESULT " +json.toString());
		
		// 결과맵핑
		sms.fromJSON(json);
	}
		
	// SMS 발송건별 결과 조회
	public static void getSent(CommSms sms) throws OpenApiSDKException {
		
		// SMS 사용여부가 FALSE이면
		if (!CommConst.enableSMS())
			return;
		
		Message message = new Message(CommConst.SMS_API_KEY, CommConst.SMS_API_SECRET, CommConst.SMS_ALGORITHM);
		JSONObject json = (JSONObject) message.sent(sms.getSentData());
		
		logger.info("[SMS 발송건별결과목록] RESULT " +json.toString());

		// 결과맵핑
		sms.fromJSONSent(json);
	}
	
	// SMS 발송결과 조회
	public static void getPop(CommSms sms) throws OpenApiSDKException {
		
		// SMS 사용여부가 FALSE이면
		if (!CommConst.enableSMS())
			return;

		Message message = new Message(CommConst.SMS_API_KEY, CommConst.SMS_API_SECRET, CommConst.SMS_ALGORITHM);
		JSONObject json = (JSONObject) message.sent(sms.getPopData());
		
		logger.info("[SMS 발송결과] RESULT " +json.toString());

		sms.fromJSONPop(json);
	}
	
	// SMS 선불 잔액 조회
	public static CommSms getCash() throws OpenApiSDKException {
		
		// SMS 사용여부가 FALSE이면
		if (!CommConst.enableSMS())
			return null;

		Message message = new Message(CommConst.SMS_API_KEY, CommConst.SMS_API_SECRET, CommConst.SMS_ALGORITHM);
		JSONObject json = (JSONObject) message.cash(new HashMap<String, String>());

		logger.info("[SMS CASH] RESULT " +json.toString());
		
		// 결과맵핑
		CommSms sms = new CommSms();
		sms.fromJSON(json);
		return sms;
	}

	// SMS 발송 통계 조회
	public static void getStatus(CommSms sms) throws OpenApiSDKException {
		
		// SMS 사용여부가 FALSE이면
		if (!CommConst.enableSMS())
			return;

		Message message = new Message(CommConst.SMS_API_KEY, CommConst.SMS_API_SECRET, CommConst.SMS_ALGORITHM);
		JSONObject json = (JSONObject) message.sent(sms.getStatusData());
		
		logger.info("[SMS 통계] RESULT " +json.toString());

		sms.fromJSONStatus(json);
	}
}
