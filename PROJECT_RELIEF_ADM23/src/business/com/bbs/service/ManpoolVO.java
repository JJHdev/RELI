package business.com.bbs.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 인력풀 모델 클래스
 *
 * @class   : ManpoolVO
 * @author  : 김주호
 * @since   : 2021.10.02
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
public class ManpoolVO extends BaseModel {
    
	// 검색조건
    private String srchText;
    
    // 세션사용자번호
    private String gsUserNo;
}
