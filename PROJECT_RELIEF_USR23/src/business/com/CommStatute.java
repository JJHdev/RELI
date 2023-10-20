package business.com;

import java.util.Calendar;

import common.util.CommUtils;

/**
 * 법령 정보 관련 상수 클래스 (사용안함)
 * 
 * @class   : CommStatute
 * @author  : LSH
 * @since   : 2021.10.24
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
public class CommStatute {
	
	// 2021.10.24 구제급여 보완기한 (15일 이내)
	// 2022.01.20 사용안함 (CommConst 로 이동처리)
	public static final int RELIEF_SUPPLEMENT_LIMIT = 30;
	
	// 2021.10.24 보완기한 반환 (현재기준 15일 이내)
	// 2022.01.20 사용안함
	public static String getSupplementLimitDate() {
		Calendar cal = CommUtils.getCurCalendar();
		cal.add(Calendar.DATE, RELIEF_SUPPLEMENT_LIMIT);
		return CommUtils.formatDate("MM월dd일", cal);
	}
}
