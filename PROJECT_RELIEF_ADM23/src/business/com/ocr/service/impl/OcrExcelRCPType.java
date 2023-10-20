package business.com.ocr.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commf.ocr.OcrExcelField;
import commf.ocr.OcrExcelForm;
import commf.ocr.OcrExcelType;

// 양식 타입별 항목 설정 
public enum OcrExcelRCPType implements OcrExcelType {

	FORM_A ("FORM_A", 
			new String[] {"진료개시일","입내원구분","입내원일","진료(투약)일수"},
			new String[] {"진료건수:"},
			OcrExcelForm.builder().code("H").match("EQ").wordIdx(1)
			.texts (new String[] {"일반외래","치과외래","보건기관외래","한방기관외래","일반입원"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입원일수"      ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("의료급여기관명").build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("sckwndCd"      ).type("text").required( true).label("상병코드"      ).build(),  
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required( true).label("상병명"        ).build(),      
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build(),
					OcrExcelField.builder().field("undrInstAlotm" ).type("long").required( true).label("100/100미만기관부담금").build(),
					OcrExcelField.builder().field("undrSelfAlotm" ).type("long").required( true).label("100/100미만본인부담금").build()
			 }))
			.build(),
			OcrExcelForm.builder().code("P").match("EQ").wordIdx(1)
			.texts (new String[] {"처방조제"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입원일수"      ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("의료급여기관명").build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("sckwndCd"      ).type("text").required(false).label("상병코드"      ).build(),  
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required(false).label("상병명"        ).build(),      
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build(),
					OcrExcelField.builder().field("undrInstAlotm" ).type("long").required( true).label("100/100미만기관부담금").build(),
					OcrExcelField.builder().field("undrSelfAlotm" ).type("long").required( true).label("100/100미만본인부담금").build()
			 }))
			.build()
	),
	FORM_B ("FORM_B", 
			new String[] {"진료개시일","입내원구분","입내원일","요양(투약)일수"},
			null,
			OcrExcelForm.builder().code("H").match("EQ").wordIdx(2)
			.texts (new String[] {"외래","입원"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입원일수"      ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required(false).label("투약일수"      ).build(),
					OcrExcelField.builder().field("sckwndCd"      ).type("text").required( true).label("상병코드"      ).build(),  
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required( true).label("상병명"        ).build(),      
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("요양기관명"    ).build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build(),
					OcrExcelField.builder().field("undrInstAlotm" ).type("long").required( true).label("100/100미만기관부담금").build(),
					OcrExcelField.builder().field("undrSelfAlotm" ).type("long").required( true).label("100/100미만본인부담금").build()
			 }))
			.build(),
			OcrExcelForm.builder().code("P").match("EQ").wordIdx(2)
			.texts (new String[] {"약국"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입원일수"      ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("요양기관명"    ).build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build(),
					OcrExcelField.builder().field("undrInstAlotm" ).type("long").required( true).label("100/100미만기관부담금").build(),
					OcrExcelField.builder().field("undrSelfAlotm" ).type("long").required( true).label("100/100미만본인부담금").build()
			 }))
			.build()
	),
	// FORM_C는 FORM_B 보다 반드시 뒤에 위치해야함 (키워드 구분 순서를 위한 부분)
	FORM_C ("FORM_C", 
			new String[] {"진료개시일","입내원구분","입내원일","투약일수"},
			new String[] {"ㅇ이 자료는 요양기관(병원 등)의"},
			OcrExcelForm.builder().code("H").match("EQ").wordIdx(2)
			.texts (new String[] {"외래","입원"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입원일수"      ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("sckwndCd"      ).type("text").required(false).label("상병코드"      ).build(),  
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required(false).label("상병명"        ).build(),      
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("요양기관명"    ).build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build()
			 }))
			.build(),
			OcrExcelForm.builder().code("P").match("EQ").wordIdx(2)
			.texts (new String[] {"약국"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입원일수"      ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("요양기관명"    ).build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build()
			 }))
			.build()
	),
	FORM_D ("FORM_D", 
			new String[] {"입내원일수(일)","상병명"},
			null,
			OcrExcelForm.builder().code("H").match("EN").wordIdx(1)
			.texts (new String[] {"병원","의원","의료원"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("의료급여기관명").build(),     
					OcrExcelField.builder().field("rcperDays"     ).type("text").required( true).label("입내원일수(일)").build(),         
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required( true).label("상병명"        ).build(),      
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금(원)").build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금(원)").build()
			 }))
			.build(),
			OcrExcelForm.builder().code("P").match("EN").wordIdx(1)
			.texts (new String[] {"약국"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("의료급여기관명").build(),     
					OcrExcelField.builder().field("rcperDays"     ).type("text").required( true).label("입내원일수(일)").build(),         
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required(false).label("상병명"        ).build(),      
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("기관부담금(원)").build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금(원)").build()
			 }))
			.build()
	),
	FORM_E ("FORM_E", 
			new String[] {"상병명","입내원구분","입내원일","투약일수"},
			null,
			OcrExcelForm.builder().code("H").match("EQ").wordIdx(6)
			.texts (new String[] {"외래","입원"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("요양기관명"    ).build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("sckwndCd"      ).type("text").required(false).label("상병코드"      ).build(),  
					OcrExcelField.builder().field("sckwndNm"      ).type("text").required(false).label("상병명"        ).build(),      
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입내원일수"    ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("공단부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build()
			 }))
			.build(),
			OcrExcelForm.builder().code("P").match("EQ").wordIdx(4)
			.texts (new String[] {"약국"})
			.fields(Arrays.asList(new OcrExcelField[] {
					OcrExcelField.builder().field("rcperSn"       ).type("int" ).required( true).label("순번"          ).build(),
					OcrExcelField.builder().field("rcperYmd"      ).type("date").required( true).label("진료게시일"    ).build(),
					OcrExcelField.builder().field("instNm"        ).type("text").required( true).label("요양기관명"    ).build(),     
					OcrExcelField.builder().field("instTel"       ).type("text").required( true).label("연락처"        ).build(),     
					OcrExcelField.builder().field("rcperSeNm"     ).type("text").required( true).label("입내원구분"    ).build(),         
					OcrExcelField.builder().field("rcperDays"     ).type("int" ).required( true).label("입내원일수"    ).build(),         
					OcrExcelField.builder().field("dosageDays"    ).type("int" ).required( true).label("투약일수"      ).build(),
					OcrExcelField.builder().field("instAlotm"     ).type("long").required( true).label("공단부담금"    ).build(), 
					OcrExcelField.builder().field("selfAlotm"     ).type("long").required( true).label("본인부담금"    ).build()
			 }))
			.build()
	);

	private static final String LINE_FEED = "\n";
	
	private List<OcrExcelForm> forms = new ArrayList<OcrExcelForm> (); // 양식 폼 목록
	private String[] pageKeywords; // 페이지별 양식 타입을 확인할 수 있는 단어 목록
	private String[] stopKeywords; // 페이지별 종료를 확인할 수 있는 문장 목록
	private String code;    // 양식 타입코드
	private String keyword; // 페이지 키워드 목록이 Line Feed로 병합된 문자역
	
	private OcrExcelRCPType(
			String code, 
			String[] pageKeywords, 
			String[] stopKeywords, 
			OcrExcelForm form1, 
			OcrExcelForm form2) {
		
		this.code  = code;
		this.pageKeywords = pageKeywords;
		this.stopKeywords = stopKeywords;
		// 키워드병합
		this.keyword = String.join(LINE_FEED, this.pageKeywords);
		this.forms.add(form1);
		this.forms.add(form2);
	}
	
	/**
	 * 라인단위로 해당하는 FORM을 반환한다.
	 */
	@Override
	public OcrExcelForm getForm(String line) {
		if (line == null)
			return null;
		for (OcrExcelForm f : forms) {
			if (f.matches(line))
				return f;
		}
		return null;
	}

	/**
	 * 라인단위로 종료 단어가 포함되었는지 체크한다.
	 */
	@Override
	public boolean isStop(String line) {
		if (stopKeywords == null)
			return false;
		
		for (String word : stopKeywords) {
			if(line.indexOf(word) >= 0)
				return true;
		}
		return false;
	}
	
	/**
	 * 텍스트 컨텐츠에 포함된 단어 기준 현재의 폼 타입 반환
	 */
	public static OcrExcelType get(String content) {
		if (content == null)
			return null;
		for (OcrExcelRCPType o : values()) {
			// 순서대로 병합된 키워드가 포함되어 있을 경우
			if (content.indexOf(o.keyword) > 0)
				return o;
		}
		/* -----------------------------------------------------------------
		// 정의된 word가 모두 순서대로 포함되어 있는 경우
		// 해당 매치되는 ENUM을 반환한다.
		// ※ FORM_B와 FORM_C는 동일한 단어를 포함하지만 
		//   FORM_B의 경우 단어 하나가 더 있어야 하므로
		//   FORM_C보다 FORM_B를 먼저 비교해야 정확한 폼타입이 확인된다.
		//   따라서, FORM_C는 반드시 FORM_B 뒤의 순서로 위치되어야 한다.
		for (OcrExcelTypeRCP o : values()) {
			boolean exist = false;
			for (String word : o.pageKeywords) {
				if (content.indexOf(word) < 0) {
					exist = false;
					break;
				}
				exist = true;
			}
			if (exist)
				return o;
		}
		----------------------------------------------------------------- */
		return null;
	}

	public String getTypeCode() {
		return code;
	}
}