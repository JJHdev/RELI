package business.usr.main.service;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * [VO클래스] - 사용자메인 모델 클래스
 *
 * @class   : UsrMainVO
 * @author  : ntarget
 * @since   : 2021.02.03
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
public class UsrMainVO extends BaseModel {
	// 화면 구분값
	private String mode;
}