package business.com;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import antlr.collections.List;
import common.util.CommUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SMS 전송 모델 클래스
 *
 * @class   : CommSms
 * @author  : LSH
 * @since   : 2021.09.27
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommSms {
	
    private final Log logger = LogFactory.getLog(getClass());

	private String to;       // ("to"       , "01000000000") 수신번호
	private String from;     // ("from"     , "01000000000") 발신번호
	private String msgType;  // ("msg_type" , "m") 메시지종류(S,M,L)
	private String subject;  // ("subject"  , "subject") 제목
	private String msg;      // ("msg"      , "메시지 내용")
	private String sendType; // ("send_type", "S") 발송형태(예약R,즉시S)
	private String cmpgId;   // ("cmpg_id"  , "ABCD1234") 예약ID (발송형태 R일 경우)
	private String cmpgTime; // ("datetime" , "20170928151930") 예약시간
	private String image;    // ("image"    , "D:/work/LGU+/image/ddd/11.jpg") 이미지(MMS인 경우 사용)
	
	// 결과조회시 사용
	private String grpId;    // ("grp_id"   , "00001") 메세지 발송 그룹 ID
	private String msgId;    // ("msg_id"   , "OtFFPUYJDJdpCpV") 메시지 발송 ID
	private String limit;    // ("limit"    , "100") 요청개수
	private String page;     // ("page"     , "2")   페이지
	private String totCnt;   // ("tot_cnt"  , "7")   총 카운트
	
	// 통계조회시에만 사용
	private String type;     // ("type"     , "m") 통계타입 ( "m":월 / "d":일)
	private String sendDate; // ("send_date", "201707") 발송년월 또는 발송일자
	
	// 결과데이터
	private String  rcode;   // 결과코드
	private String  rdesc;   // 결제메시지
	private CommSms result;  // 결과데이터
	private List    results; // 결과목록데이터

	private String rptCode;      // [발송결과] 발송 결과코드
	private String rptDesc;      // [발송결과] 발송 결과메시지
	private String rptTime;      // [발송결과] 리포트 일시 (20170929140721)
	private String sentTime;     // [발송결과] 발송 일시 (20170929140718)
	private String acceptTime;   // [발송결과] 접수 일시 (20170929140718)
	private String reservTime;   // [발송결과] 예약 일시 (예약발송일 경우)
	private String cliRptTime;   // [발송결과] API 결과수신일시
	
	private String cash      ; // [잔액조회] 잔액
	private String cliId     ; // [통계API] SM 아이디
	private String send_date ; // [통계API] 발송일시
	private String smsTotal  ; // [통계API] sms 발송 총 건수
	private String smsSuccess; // [통계API] sms 성공건수
	private String smsFail   ; // [통계API] sms 실패건수
	private String lmsTotal  ; // [통계API] lms 발송 총 건수
	private String lmsSuccess; // [통계API] lms 성공건수
	private String lmsFail   ; // [통계API] lms 실패건수
	private String mmsTotal  ; // [통계API] mms 발송 총 건수
	private String mmsSuccess; // [통계API] mms 성공건수
	private String mmsFail   ; // [통계API] mms 실패건수
	
	// 메세지발송 결과맵핑
	public void fromJSON(JSONObject json) {
		setRcode((String)json.get("rcode"));
		setRdesc((String)json.get("rdesc"));
		
		JSONObject d = (JSONObject)json.get("data");
		
		if (d != null) {
			CommSms ret = new CommSms();
			ret.setMsgId  ((String)d.get("msg_id" )); // 메시지 발송ID
			ret.setGrpId  ((String)d.get("grp_id" )); // 메시지발송에 대한 그룹 ID
			ret.setCmpgId ((String)d.get("cmpg_id")); // 예약 ID
			setResult(ret);
		}
	}

	// 잔액조회 결과맵핑
	public void fromJSONCash(JSONObject json) {
		setRcode((String)json.get("rcode"));
		setRdesc((String)json.get("rdesc"));
		
		JSONObject d = (JSONObject)json.get("data");
		
		if (d != null) {
			CommSms ret = new CommSms();
			ret.setCash((String)d.get("cash"));
			setResult(ret);
		}
	}
	
	// 발송결과조회 결과맵핑
	public void fromJSONPop(JSONObject json) {
		setRcode((String)json.get("rcode"));
		setRdesc((String)json.get("rdesc"));
		
		JSONObject d = (JSONObject)json.get("data");
		
		if (d != null) {
			CommSms ret = _getSentResult(d);
			setResult(ret);
		}
	}
	
	private CommSms _getSentResult(JSONObject d) {
		CommSms ret = new CommSms();
		ret.setMsgId        ((String)d.get("msg_id"       ));
		ret.setGrpId        ((String)d.get("grp_id"       ));
		ret.setCmpgId       ((String)d.get("cmpg_id"      ));
		ret.setRptCode      ((String)d.get("rpt_code"     ));
		ret.setRptDesc      ((String)d.get("rpt_desc"     ));
		ret.setRptTime      ((String)d.get("rpt_time"     ));
		ret.setAcceptTime   ((String)d.get("accepted_time"));
		ret.setSentTime     ((String)d.get("sent_time"    ));
		ret.setSendType     ((String)d.get("send_type"    ));

		ret.setReservTime   ((String)d.get("reservation_time"));
		ret.setCliRptTime   ((String)d.get("cli_rpt_time"    ));
		return ret;
	}
	
	// 발송다중결과조회 결과맵핑
	public void fromJSONSent(JSONObject json) { 
		setRcode((String)json.get("rcode"));
		setRdesc((String)json.get("rdesc"));
		
		JSONObject d = (JSONObject)json.get("data");
		
		if (d != null) {
			CommSms ret = new CommSms();
			ret.setTotCnt((String)d.get("tot_cnt"));
			ret.setLimit ((String)d.get("limit"  ));
			ret.setPage  ((String)d.get("page"   ));
			setResult(ret);
			
			JSONArray arr = (JSONArray)d.get("list");
			if (arr != null && arr.size() > 0) {
				ArrayList<CommSms> list = new ArrayList<CommSms>();
				for (Object o : arr) {
					list.add( _getSentResult((JSONObject)o) );
				}
				ret.setResults((List) list);
			}
		}
	}
	
	// 통계API 결과맵핑
	public void fromJSONStatus(JSONObject json) {
		setRcode((String)json.get("rcode"));
		setRdesc((String)json.get("rdesc"));
		
		JSONObject d = (JSONObject)json.get("data");
		
		if (d != null) {
			CommSms ret = new CommSms();
			ret.setCliId      ((String)d.get("cli_id"      )); 
			ret.setSendDate   ((String)d.get("send_date"   ));
			ret.setSmsTotal   ((String)d.get("sms_total"   ));
			ret.setSmsSuccess ((String)d.get("sms_success" ));
			ret.setSmsFail    ((String)d.get("sms_fail"    ));
			ret.setLmsTotal   ((String)d.get("lms_total"   ));
			ret.setLmsSuccess ((String)d.get("lms_success" ));
			ret.setLmsFail    ((String)d.get("lms_fail"    ));
			ret.setMmsTotal   ((String)d.get("mms_total"   ));
			ret.setMmsSuccess ((String)d.get("mms_success" ));
			ret.setMmsFail    ((String)d.get("mms_fail"    ));
			setResult(ret);
		}
	}
	
	// SMS 발송 데이터
	public HashMap<String, String> getSendData() {

		HashMap<String, String> params = new HashMap<String, String>();

		params.put("to"       , CommUtils.replace(to  , "-", "")); // 수신번호
		params.put("from"     , CommUtils.replace(from, "-", "")); // 발신번호
		params.put("msg_type" , msgType);	//메시지종류(S,M,L)
		params.put("msg"      , msg);
		params.put("send_type", sendType); //발송형태(예약R,즉시S)

		logger.info("[SMS SEND] to : " +params.get("to"));
		logger.info("[SMS SEND] from : " +params.get("from"));
		logger.info("[SMS SEND] msg : " +params.get("msg"));
		logger.info("[SMS SEND] msg_type : " +params.get("msg_type"));
		logger.info("[SMS SEND] send_type : " +params.get("send_type"));

		if (subject != null) {
			params.put("subject"  , subject);	//제목
			logger.info("[SMS SEND] subject : " +params.get("subject"));
		}
		if (cmpgId != null) {
			params.put("cmpg_id"  , cmpgId); //발송형태 R일 경우 예약ID
			params.put("datetime" , cmpgTime);	//예약시간
			logger.info("[SMS SEND] cmpg_id : " +params.get("cmpg_id"));
			logger.info("[SMS SEND] datetime : " +params.get("datetime"));
		}
		if (image != null) {
			params.put("image"    , image); //이미지, MMS인 경우 사용
			logger.info("[SMS SEND] image : " +params.get("image"));
		}
		return params;
	}
		
	// SMS 발송건별 결과 조회 데이터
	public HashMap<String, String>  getSentData() {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("grp_id" , grpId);
		params.put("limit"  , limit);
		params.put("page"   , page);
		params.put("msg_id" , msgId); //메시지 발송 ID
		if (cmpgId != null) {
			params.put("cmpg_id", cmpgId);
		}
		return params;
	}
	
	// SMS 발송결과 조회
	public HashMap<String, String> getPopData() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("limit", limit); //"100"
		return params;
	}
	
	// SMS 예약 취소 데이터
	public HashMap<String, String> getCancelData() {

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("grp_id" , grpId);
		params.put("msg_id" , msgId); //메시지 발송 ID
		if (cmpgId != null) {
			params.put("cmpg_id", cmpgId);
		}
		return params;
	}

	// SMS 발송 통계 조회 데이터
	public HashMap<String, String> getStatusData() {
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("type"     , type    ); // "m"
		params.put("send_date", sendDate); // "201707"
		return params;
	}

}






