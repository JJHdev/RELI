package business.sys.hldy.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 공휴일관리 모델 클래스
 *
 * @class   : HldyVO
 * @author  : KSH
 * @since   : 2023.02.02
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
@EqualsAndHashCode(callSuper=true)
public class HldyVO extends BaseModel{
	//공휴일 날짜
	private String hldyYmd;
	//공휴일 명
	private String hldyNm;
	//등록일
	private String regDate;
	//작성자
	private String rgtrNo;
	// 사용여부
    private String useYn;

    
    //검색조건
    private String srchText;
    private String srchYear;
    
    
    // 세션사용자번호
    private String gsUserNo;
    // 세션사용자권한
    private String gsRoleId;
    
 
	//공휴일 날짜(오리지널)
	private String oldHldyYmd;
	
}
