package business.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import common.file.ExcelReadHelper;
import common.file.ExcelReadInfo;
import common.util.CommUtils;

/**
 * 엑셀 로드 설정 클래스
 * 
 * @class   : CommExcel
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2021.11.30  LSH        세부의료비 산정결과 설정추가
 */
public enum CommExcel {

	MCP ("세부의료비 산정결과", 0, 1, 1, 0, true, 
			new String[] {
				"idntfcId"     , "bizAreaNm"   , "bizOder"     , "exmnOder"    , "aplyNo"      ,
				"rcperInstNm"  , "rcperSeNm"   , "sckwndCd"    , "sckwndNm"    , "selfAlotm"   , 
				"rcognStusNm"  , "resn"        , "rcperPstgYmd", "rcperEndYmd"
			},
			new String[] {
				"식별ID"       , "피해지역명"  , "사업차수"    , "조사차수"    , "신청번호"    ,
				"요양기관명"   , "요양구분명"  , "질병코드"    , "질병명"      , "본인부담금"  , 
				"인정상태명"   , "불인정사유"  , "요양게시일자", "요양종료일자"
			},
			new boolean[] {	
				true   , true  , true   , false   , false    ,
				true   , true  , false  , false   , true     , 
				true   , false , true   , false
			},
			// 길이체크가 필요한 값만 0보다 큰값으로 설정
			new int[] {	
				0   , 0  , 0   , 0   , 0    ,
				100 , 0  , 0   , 100 , 0    , 
				0   , 65 , 10  , 10
			}
	)
	;
	private int sheetIdx;   // SHEET INDEX
	private int headerCnt;  // 헤더 행갯수
	private int startRno;   // 시작 행INDEX
	private int startCno;   // 시작 열INDEX
	private boolean allow;  // 포맷오류 허용여부
	private String[]  keys;    // 데이터 칼럼 KEY 배열
	private String[]  names;   // 데이터 칼럼 명칭 배열
	private boolean[] needs;   // 데이터 칼럼 필수여부 배열
	private int[]     maxlens; // 데이터 칼럼 최대길이 배열
	private String[]  types;   // 데이터 칼럼 타입 배열
	private String remark;
	
	private CommExcel(String remark, int sheetIdx, int headerCnt,
			int startRno, int startCno, boolean allow,
			String[] keys, String[] names, 
			boolean[] needs, int[] maxlens) {

		this.remark    = remark;
		this.sheetIdx  = sheetIdx;
		this.headerCnt = headerCnt;
		this.startRno  = startRno;
		this.startCno  = startCno;
		this.allow     = allow;
		this.keys      = keys;
		this.names     = names;
		this.needs     = needs;
		this.maxlens   = maxlens;
	}
	
	private CommExcel(String remark, int sheetIdx, int headerCnt,
			int startRno, int startCno, boolean allow,
			String[] keys, String[] names, 
			boolean[] needs, int[] maxlens,
			String[] types) {

		this.remark    = remark;
		this.sheetIdx  = sheetIdx;
		this.headerCnt = headerCnt;
		this.startRno  = startRno;
		this.startCno  = startCno;
		this.allow     = allow;
		this.keys      = keys;
		this.names     = names;
		this.needs     = needs;
		this.maxlens   = maxlens;
		this.types     = types;
	}

	public List<String> getMappingKeys() {
		return Arrays.asList(keys);
	}

	@SuppressWarnings("rawtypes")
	public List<Map> parseData(String fileName) throws Exception {

		List<String> mappingKeys = getMappingKeys(); // 데이터 칼럼 KEY 목록
	    //-------------------------
	    // 엑셀 파싱
	    //-------------------------
	    return new ExcelReadHelper(
	        new ExcelReadInfo()
	        	.setSheetIndex           (sheetIdx)    // SHEET INDEX 설정 (default : -1)
	            .setHeaderRowCount       (headerCnt)   // header row 개수 설정 (default : 1)
	            .setStartRowIndex        (startRno)    // 데이터 시작 행 INDEX 설정 (default : 1)
	            .setStartColIndex        (startCno)    // 데이터 시작 열 INDEX 설정 (default : 0)
	            .setCellMappingKeys      (mappingKeys) // 엑셀 mapping 키 리스트 설정
	            .setFileFullPathName     (fileName)    // 엑셀 파일 경로 설정
	            .setAllowExcelFormatError(allow)       // Format 오류 허용 여부 (내용이 다 들어가지 않아도 처리될 수있게 허용)
	    ).excelToDataList();
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> validateErrors(Map data) {
		
		List<String> errors = new ArrayList<String> ();
		int i = 0;
		for (String key : keys) {
			String value = (String)data.get(key);
			boolean need = needs[i];
			String  name = names[i];
			// 필수여부 체크
			if (need && CommUtils.isEmpty(value)) {
				errors.add(name);
			}
			i++;
		}
		if (errors.size() == 0)
			return null;
		return errors;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> validateLengthErrors(Map data) {
		
		List<String> errors = new ArrayList<String> ();
		int i = 0;
		for (String key : keys) {
			String value = (String)data.get(key);
			String  name = names[i];
			int maxlen   = maxlens[i];
			// 최대길이 체크
			if (maxlen > 0 && value != null && value.length() > maxlen) {
				errors.add(name+"[최대길이: "+maxlen+"자]");
			}
			i++;
		}
		if (errors.size() == 0)
			return null;
		return errors;
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> validateTypeErrors(Map data) {
		
		if (types == null)
			return null;
		
		List<String> errors = new ArrayList<String> ();
		int i = 0;
		for (String key : keys) {
			String value = (String)data.get(key);
			String name  = names[i];
			String type  = types[i];
			i++;
			
			if (type == null)
				continue;
			
			if (CommUtils.isEmpty(value))
				continue;
			
			// 데이터 타입 체크 (INT, DOUBLE, DATE)
			if ("INT".equals(type)) {
	    		try {
	        		Integer.parseInt(value);
	    		} catch(NumberFormatException ex) {
	    			errors.add(name+"[유효타입: 정수형]");
	    		}
			}
			else if ("DOUBLE".equals(type)) {
	    		try {
	    			Double.parseDouble(value);
	    		} catch(NumberFormatException ex) {
	    			errors.add(name+"[유효타입: 실수형]");
	    		}
			}
			else if ("DATE".equals(type)) {
				if (value.length() != 10) {
					errors.add(name+"[유효타입: 날짜형(예:2022.01.01)]");
				}
				else {
	    			try {
		    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
						sdf.parse(value);
					} catch (ParseException ex) {
						errors.add(name+"[유효타입: 날짜형(예:2022.01.01)]");
					}
				}
			}
		}
		if (errors.size() == 0)
			return null;
		return errors;
	}
	
	public int getHeaderCnt() {
		return headerCnt;
	}

	public int getStartRno() {
		return startRno;
	}

	public int getStartCno() {
		return startCno;
	}

	public boolean isAllow() {
		return allow;
	}

	public String[] getKeys() {
		return keys;
	}

	public String getRemark() {
		return remark;
	}

	public String[] getNames() {
		return names;
	}

	public int getSheetIdx() {
		return sheetIdx;
	}
}
