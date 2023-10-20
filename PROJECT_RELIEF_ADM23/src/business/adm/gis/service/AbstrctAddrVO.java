package business.adm.gis.service;

import java.util.List;

import common.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class AbstrctAddrVO extends BaseModel {
	// 일련번호
	private Integer sn;
	// 피해자 번호
    private int sufrerNo;
    // 초본주소
    private String abstrctAddr;
    // 신고연도
    private double dclrYr;
    // 신고사유
    private String dclrResn;
    // 이격거리
    private double gapDstnc;
    // 위도
    private double lat;
    // 경도
    private double lot;
    // 등록자 번호
    private String rgtrNo;
    // 등록일자
    private String regYmd;
    // 수정자 번호
    private String mdfrNo;
    // 수정일자
    private String mdfcnYmd;
    
    // 식별 ID
    private String idntfcId;
    
    // 선택된 목록
    private List<AbstrctAddrVO> abstrctAddrList;
    
    // 세션사용자번호
    private String gsUserNo;
}
