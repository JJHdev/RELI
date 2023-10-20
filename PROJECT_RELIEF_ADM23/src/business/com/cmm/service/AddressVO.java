package business.com.cmm.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.base.BaseModel;
import common.util.CommUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * [VO클래스] - 행정안전부 개발자센터 주소검색용 모델클랙스
 *
 * @class   : AddressVO
 * @author  : LSH
 * @since   : 2021.07.28
 * @version : 1.0
 *
 *   수정일     수정자              수정내용
 *  -------    --------    ---------------------------
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class AddressVO extends BaseModel {
	
	//request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
	//request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
	
	private String roadFullAddr;    // (필수)전체 도로명주소
	private String roadAddr;        // "서울특별시 서초구 반포대로 3(서초동)"
	private String roadAddrPart1;   // (필수)도로명주소(참고항목 제외)
	private String roadAddrPart2;   // 도로명주소 참고항목
	private String engAddr;         // (필수)도로명주소(영문)
	private String jibunAddr;       // (필수)지번 정보
			
	private String zipNo;           // (필수)우편번호
	private String admCd;           // (필수)행정구역코드
	private String rnMgtSn;         // (필수)도로명코드
	private String bdMgtSn;         // (필수)건물관리번호
	private String addrDetail;      // 고객 입력 상세 주소
	private String detBdNmList;	    // 상세 건물명
	private String bdNm;            // (2017년 2월 추가) 건물명
	private String bdKdcd;          // (2017년 2월 추가) (필수)공동주택여부 (1:공동주택, 0: 비공동주택)
	private String siNm;            // (2017년 2월 추가) (필수)시도명
	private String sggNm;           // (2017년 2월 추가) 시군구명
	private String emdNm;           // (2017년 2월 추가) (필수)읍면동명
	private String liNm;            // (2017년 2월 추가) 법정리명
	private String rn;              // (2017년 2월 추가) (필수)도로명
	private String udrtYn;          // (2017년 2월 추가) (필수)지하여부 (0:지상, 1:지하)
	private String buldMnnm;        // (2017년 2월 추가) (필수)건물본번
	private String buldSlno;        // (2017년 2월 추가) (필수)건물부번 (부번이 없는 경우 0)
	private String mtYn;            // (2017년 2월 추가) (필수)산여부 (0:대지, 1:산)
	private String lnbrMnnm;        // (2017년 2월 추가) (필수)지번본번(번지)
	private String lnbrSlno;        // (2017년 2월 추가) (필수)지번부번(호) (부번이 없는 경우 0)
	private String emdNo;           // (2017년 3월 추가) (필수)읍면동일련번호
	
	
	private String inputYn;         // 입력여부
	private String confmKey;        // 신청시 부여받은 승인키
	// 1 : 도로명
	// 2 : 도로명+지번+상세보기(관련지번, 관할주민센터)
	// 3 : 도로명+상세보기(상세건물명)
	// 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
	private String resultType;      // 도로명주소 검색결과 화면 출력내용
	private String returnUrl;       // 선택결과 페이지URL
	
	private int countPerPage;  // "5"
	private int currentPage;   // "1"
	private int totalCount;    // "677"
	private int totalPages;
	private String errorCode;     // "0"
	private String errorMessage;  // "정상"
	
	// 검색API 사용시 결과객체
	// 에러코드,출력수,검색건수 등의 맵핑됨
	private AddressVO common; 
	
	// 검색API 사용시 검색데이터목록
	private List<AddressVO> juso;
	
	// 검색API 결과JSON을 받아 객체로 변환
	@SuppressWarnings("unchecked")
	public void buildFromJson(String jsonString) throws ParseException, JsonParseException, JsonMappingException, IOException {

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
		
		if (jsonObject == null)
			return;
		
		JSONObject results = (JSONObject)jsonObject.get("results");
		if (results == null)
			return;

		JSONObject obj = (JSONObject)results.get("common");
		if (obj != null) {
			this.errorCode    = (String)obj.get("errorCode");
			this.errorMessage = (String)obj.get("errorMessage");
			this.countPerPage = CommUtils.strToInt((String)obj.get("countPerPage"));
			this.currentPage  = CommUtils.strToInt((String)obj.get("currentPage"));
			this.totalCount   = CommUtils.strToInt((String)obj.get("totalCount"));
			this.totalPages   = (int)Math.ceil( (double)totalCount / countPerPage );
		}
		
		JSONArray arr  = (JSONArray)results.get("juso");
		if (arr != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			this.juso = (List<AddressVO>)objectMapper.readValue(arr.toJSONString(), new TypeReference<List<AddressVO>>(){}); 
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getPaginatedResult() {
        Map data = new HashMap();
		data.put("total", getTotalCount());
		data.put("limit", getCountPerPage());
		data.put("page",  getCurrentPage());
		data.put("pages", getTotalPages());
		data.put("rows",  getJuso());
		return data;
	}
}