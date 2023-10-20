package business.com.exmn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.CommExcel;
import business.com.exmn.service.MnsvyService;
import business.com.exmn.service.MnsvyVO;
import business.com.exmn.service.RcperLvlhService;
import business.com.exmn.service.RcperLvlhVO;
import business.com.relief.service.ReliefService;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.util.CommUtils;

/**
 * [서비스클래스] - 요양생활수당관리 Service 구현 클래스
 * 
 * @class   : RcperLvlhServiceImpl
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 * 2022.12.27   LSH         2차개발 - TB_RCPER_LVLH_MNG 사용제거
 * 2022.12.27   LSH         2차개발 - TB_RCPER_LVLH_GRD 사용 (피해등급산정)
 */

@Service("RcperLvlhService")
@SuppressWarnings({"all"})
public class RcperLvlhServiceImpl extends BaseService implements RcperLvlhService {

    @Resource(name = "RcperLvlhGrdDAO")
    private RcperLvlhGrdDAO rcperLvlhGrdDAO;

    @Resource(name = "RcperLvlhDtlsDAO")
    private RcperLvlhDtlsDAO rcperLvlhDtlsDAO;

    @Resource(name="MnsvyService")
    private MnsvyService mnsvyService;
    
    @Resource(name="ReliefService")
    protected ReliefService reliefService;

    /**
     * 2022.12.27 LSH
     * 요양생활수당평가등급 목록조회
     */
    @Override
    public List listRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhGrdDAO.listRcperLvlhGrd(rcperLvlhVO);
    }

    /**
     * 2022.12.27 LSH
     * 요양생활수당평가등급 상세조회
     */
	@Override
	public RcperLvlhVO viewRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception {
		return rcperLvlhGrdDAO.viewRcperLvlhGrd(rcperLvlhVO);
	}

    /**
     * 2022.12.27 LSH
     * 요양생활수당평가등급 등록
     */
    private int regiRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhGrdDAO.regiRcperLvlhGrd(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.INSERT);
			rcperLvlhGrdDAO.regiRcperLvlhGrdHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 2022.12.27 LSH
     * 요양생활수당평가등급 수정
     */
    private int updtRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhGrdDAO.updtRcperLvlhGrd(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.UPDATE);
			rcperLvlhGrdDAO.regiRcperLvlhGrdHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 2022.12.27 LSH
     * 요양생활수당평가등급 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = 0;
		// 이력저장
		rcperLvlhVO.setLogTy(CommConst.DELETE);
		if (rcperLvlhGrdDAO.regiRcperLvlhGrdHst(rcperLvlhVO) > 0) {
	        ret = rcperLvlhGrdDAO.deltRcperLvlhGrd(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 평가점수 / 최종피해등급 산출
     * @param rcperLvlhVO.svrtyScreStr 점수를 파이프라인으로 묶은 문자열
     *                                 ex) 31.25|12.5|56.25|62.5|37.5
     * @return lastDmgeScre  최종평가점수
     * @return lastDmgeGrdCd 최종피해등급
     */
    public RcperLvlhVO viewDmgeGrdScre(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhGrdDAO.viewDmgeGrdScre(rcperLvlhVO);
    }

    /**
     * 2022.12.27 LSH
     * 구제급여지급 - 요양생활수당 - 피해등급 산정결과 저장 처리
     * 요양생활수당평가등급 저장처리 (다중처리)
     */
	@Override
	public int saveRcperLvlhGrd(RcperLvlhVO rcperLvlhVO) throws Exception {
		
		if (rcperLvlhVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		// 저장할 평가점수 목록
		List<RcperLvlhVO> saveList = rcperLvlhVO.getScreList();
		// 삭제할 평가점수 목록
		List<RcperLvlhVO> deltList = rcperLvlhVO.getDeltList();

		// 삭제대상이 있는 경우
		if (!CommUtils.isEmptyList(deltList)) {
			for (RcperLvlhVO data : deltList) {
				data.setGsUserNo (rcperLvlhVO.getGsUserNo ());
				// 요양생활수당평가등급 삭제
				ret += deltRcperLvlhGrd(data);
			}
		}
		
		for (RcperLvlhVO data : saveList) {
			data.setBizAreaCd(rcperLvlhVO.getBizAreaCd());
			data.setBizOder  (rcperLvlhVO.getBizOder  ());
			data.setExmnOder (rcperLvlhVO.getExmnOder ());
			data.setAplyNo   (rcperLvlhVO.getAplyNo   ());
			data.setGsUserNo (rcperLvlhVO.getGsUserNo ());
			
			// 등록인 경우 (다중행 등록)
			if (CommConst.INSERT.equals(data.getMode())) {
				// 요양생활수당평가등급 수정
				ret += regiRcperLvlhGrd(data);
			}
			else if (CommConst.UPDATE.equals(data.getMode())) {
				// 요양생활수당평가등급 수정
				ret += updtRcperLvlhGrd(data);
			}
		}
		// 본조사의 최종피해등급/최종피해점수 업데이트
		mnsvyService.updtMnsvy(
				MnsvyVO.builder()
				.bizAreaCd    (rcperLvlhVO.getBizAreaCd    ())
				.bizOder      (rcperLvlhVO.getBizOder      ())
				.exmnOder     (rcperLvlhVO.getExmnOder     ())
				.aplyNo       (rcperLvlhVO.getAplyNo       ())
				.lastDmgeGrdCd(rcperLvlhVO.getLastDmgeGrdCd()) // 최종피해등급
				.lastDmgeScre (rcperLvlhVO.getLastDmgeScre ()) // 최종피해점수
				.dmgeGrdYr    (rcperLvlhVO.getDmgeGrdYr    ()) // 피해등급기준년도
				.lastDmgeGrdYn(rcperLvlhVO.getLastDmgeGrdYn()) // 최종피해등급 필수등록여부
				.gsUserNo     (rcperLvlhVO.getGsUserNo     ())
				.build()
		);
        return ret;
	}

    /**
     * 요양생활수당지급상세 목록조회
     */
    @Override
    public List listRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhDtlsDAO.listRcperLvlhDtls(rcperLvlhVO);
    }

    /**
     * 요양생활수당지급상세 상세조회
     */
	@Override
	public RcperLvlhVO viewRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		return rcperLvlhDtlsDAO.viewRcperLvlhDtls(rcperLvlhVO);
	}

    /**
     * 요양생활수당지급상세 등록
     */
    private int regiRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhDtlsDAO.regiRcperLvlhDtls(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.INSERT);
			rcperLvlhDtlsDAO.regiRcperLvlhDtlsHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 요양생활수당지급상세 수정
     */
    private int updtRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = rcperLvlhDtlsDAO.updtRcperLvlhDtls(rcperLvlhVO);
		if (ret > 0) {
			// 이력저장
			rcperLvlhVO.setLogTy(CommConst.UPDATE);
			rcperLvlhDtlsDAO.regiRcperLvlhDtlsHst(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 요양생활수당지급상세 삭제 (이력을 먼저 저장후 삭제한다)
     */
    private int deltRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
		int ret = 0;
		// 이력저장
		rcperLvlhVO.setLogTy(CommConst.DELETE);
		if (rcperLvlhDtlsDAO.regiRcperLvlhDtlsHst(rcperLvlhVO) > 0) {
	        ret = rcperLvlhDtlsDAO.deltRcperLvlhDtls(rcperLvlhVO);
		}
		return ret;
    }

    /**
     * 2023.01.02 LSH
     * 구제급여지급 - 요양생활수당 - 요양생활수당 지급기한등록 처리
     */
	@Override
	public int saveRcperLvlhDeadline(RcperLvlhVO rcperLvlhVO) throws Exception {
	 	if (rcperLvlhVO == null)
			throw processException("error.comm.notTarget");
		
		// 본조사 지급기간 업데이트
		return mnsvyService.updtMnsvy(
			MnsvyVO.builder()
        		.bizAreaCd    (rcperLvlhVO.getBizAreaCd ())
        		.bizOder      (rcperLvlhVO.getBizOder   ())
        		.exmnOder     (rcperLvlhVO.getExmnOder  ())
        		.aplyNo       (rcperLvlhVO.getAplyNo    ())
        		.gsUserNo     (rcperLvlhVO.getGsUserNo  ())
        		.giveBgngYm   (rcperLvlhVO.getGiveBgngYm())
        		.giveEndYm    (rcperLvlhVO.getGiveEndYm ())
        		.build()
		);
	}
	
    /**
     * 2022.12.27 LSH
     * 구제급여지급 - 요양생활수당 - 요양생활수당 지급등록 처리
     * 1) 월지급
     * 2) 일시금지급
     * 3) 소급
     */
	@Override
	public int saveRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {

	 	if (rcperLvlhVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String giveSeCd = rcperLvlhVO.getGiveSeCd();
		
		// 지급여부 'Y'로 설정
		rcperLvlhVO.setGiveYn(CommConst.YES);
		// 지급금액 설정
		if (CommConst.RCP_GIVE_MONTHLY.equals(giveSeCd)) {
			// 월지급인 경우
			rcperLvlhVO.setGiveAmt(rcperLvlhVO.getRcperLvlhAmt());
		}
		else if (CommConst.RCP_GIVE_LUMPSUM.equals(giveSeCd)) {
			// 일시지급인 경우
			rcperLvlhVO.setGiveAmt(rcperLvlhVO.getLumpSumAmt());
		}
		else if (CommConst.RCP_GIVE_RTROACT.equals(giveSeCd)) {
			// 소급인 경우
			rcperLvlhVO.setGiveAmt(rcperLvlhVO.getRtroactAmt());
		}
		// 요양생활수당 지급등록 처리
		ret = regiRcperLvlhDtls(rcperLvlhVO);

		if (ret > 0) {
	        // 소급인 경우 본조사의 소급기간 업데이트
			if (CommConst.RCP_GIVE_RTROACT.equals(giveSeCd)) {
				// 본조사 소급기간 업데이트
				mnsvyService.updtMnsvy(
					MnsvyVO.builder()
		        		.bizAreaCd    (rcperLvlhVO.getBizAreaCd    ())
		        		.bizOder      (rcperLvlhVO.getBizOder      ())
		        		.exmnOder     (rcperLvlhVO.getExmnOder     ())
		        		.aplyNo       (rcperLvlhVO.getAplyNo       ())
		        		.gsUserNo     (rcperLvlhVO.getGsUserNo     ())
		        		.rtroactBgngYm(rcperLvlhVO.getRtroactBgngYm())
		        		.rtroactEndYm (rcperLvlhVO.getRtroactEndYm ())
		        		.build()
				);
			}
			// 구제급여신청 상태 업데이트
			reliefService.updtReliefStatus(
				rcperLvlhVO.getAplyNo(),   // 신청번호 
				CommConst.PRGRE_GIVE,      // 상태코드 (지급)
				rcperLvlhVO.getGsUserNo()  // 등록자번호
			);
		}
        return ret;
	}
    
    /**
     * 2022.12.28 LSH
     * 요양생활수당 지급해야할 일시금금액 조회
     * @param rcperLvlhVO.bizAreaCd  사업지역코드
     * @param rcperLvlhVO.bizOder    사업차수
     * @param rcperLvlhVO.exmnOder   조사차수
     * @param rcperLvlhVO.aplyNo     신청번호
     * @param rcperLvlhVO.giveBgngYm 지급기간 (시작)
     * @param rcperLvlhVO.giveEndYm  지급기간 (종료)
     */
    public long getRcperLvlhLumpSum(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhDtlsDAO.getRcperLvlhLumpSum(rcperLvlhVO);
    }

    /**
     * 2022.12.29 LSH
     * 요양생활수당 소급기간 내의 지급할 금액 합산 조회
     * @param rcperLvlhVO.bizAreaCd     사업지역코드
     * @param rcperLvlhVO.bizOder       사업차수
     * @param rcperLvlhVO.exmnOder      조사차수
     * @param rcperLvlhVO.aplyNo        신청번호
     * @param rcperLvlhVO.rtroactBgngYm 소급기간 (시작)
     * @param rcperLvlhVO.rtroactEndYm  소급기간 (종료)
     * 
     */
	@Override
    public long getRtroactSum(RcperLvlhVO rcperLvlhVO) throws Exception {
    	return rcperLvlhDtlsDAO.getRtroactSum(rcperLvlhVO);
    }

    /**
     * 2023.01.02 LSH
     * 요양생활수당 소급지급 여부 확인
     */
	public boolean checkRcperLvlhRtroact(RcperLvlhVO rcperLvlhVO) throws Exception {
		return rcperLvlhDtlsDAO.checkRcperLvlhRtroact(rcperLvlhVO);
	}

    /**
     * 2023.01.02 LSH
     * 요양생활수당 지급년월에 기지급 여부
     */
	@Override
	public boolean existRcperLvlhDtls(RcperLvlhVO rcperLvlhVO) throws Exception {
        RcperLvlhVO obj = viewRcperLvlhDtls(rcperLvlhVO);
        return (obj != null);
	}

    /**
     * 2023.01.03 LSH
     * 요양생활수당 지급일자 일괄등록 엑셀로드
     */
	@Override
	public int loadRcperLvlhDtls(RcperLvlhVO rcperLvlhVO, FileInfo fileInfo) throws Exception {
		
		if (fileInfo == null)
			throw processException("error.comm.notTarget");

    	// 파일 물리적 경로 포함 명칭
    	String fullName = FileDirectory.ROOT.getTempName(fileInfo.getSaveName());
    	
    	// 엑셀로드타입
    	CommExcel ce = CommExcel.RCPERLVLH;
    	
    	// 엑셀로드된 List<Map> 데이터
    	List<Map> dataList = ce.parseData(fullName);
    	
    	if (dataList == null)
    		throw processException("error.comm.notTarget");
    	    
    	// 세션사용자번호
    	String gsUserNo = rcperLvlhVO.getGsUserNo();
    	
    	int sn  = 1;
    	int ret = 0;
    	
        // 검증 및 데이터 등록
    	for (Map data : dataList) {

    		// 필수값 입력 체크
    		List<String> requireErrors = ce.validateErrors(data);
    		if (requireErrors != null) {
    			// 데이터가 없습니다.
    			throw processException("error.adm.mcpDtl.notExistExcel", new String[] { String.valueOf(sn), requireErrors.toString()});
    		}
    		// 최대길이 검증
    		List<String> lengthErrors = ce.validateLengthErrors(data);
    		if (lengthErrors != null) {
    			// 데이터가 최대길이를 초과했습니다.
    			throw processException("error.adm.mcpDtl.overMaxLength", new String[] { String.valueOf(sn), lengthErrors.toString()});
    		}
    		RcperLvlhVO model = RcperLvlhVO.builder()
    				.gsUserNo (rcperLvlhVO.getGsUserNo   ())
    				.giveSeCd (CommConst.RCP_GIVE_MONTHLY  ) // 지급구분 '월지급' 
    				.giveYn   (CommConst.YES               ) // 지급여부 'Y'
    				.idntfcId ((String)data.get("idntfcId")) // 식별ID
    				.exmnOder ((String)data.get("exmnOder")) // 조사차수
    				.giveYm   ((String)data.get("giveYm"  )) // 지급년월(yyyy.MM)
    				.giveYmd  ((String)data.get("giveYmd" )) // 지급일자(yyyy.MM.dd)
    				.build    ();
    		
    		// 지급년월 문자길이 확인
    		int ymlen = ce.getMaxLength("giveYm");
    		if (CommUtils.isEmpty(model.getGiveYm()) ||
    			model.getGiveYm().length() != ymlen) {
    			// 데이터 길이[{2}자]가 맞지 않습니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.notEqualLength", new String[] { String.valueOf(sn), "지급년월", String.valueOf(ymlen)});
    			
    		}
    		// 지급일자 문자길이 확인
    		int dtlen = ce.getMaxLength("giveYmd");
    		if (CommUtils.isEmpty(model.getGiveYm()) ||
    			model.getGiveYmd().length() != dtlen) {
    			// 데이터 길이[{2}자]가 맞지 않습니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.notEqualLength", new String[] { String.valueOf(sn), "지급일자", String.valueOf(dtlen)});
    		}
    		// 지급년월 포맷제거
    		model.setGiveYm (CommUtils.replace(model.getGiveYm (),".",""));
    		// 지급일자 포맷제거
    		model.setGiveYmd(CommUtils.replace(model.getGiveYmd(),".",""));
    		// 지급년월 기준으로 지급년도 정의
    		model.setGiveYr(model.getGiveYm().substring(0,4));
    		// 지급년월 기준으로 지급월 정의
    		model.setGiveMm(model.getGiveYm().substring(4,6));
    		// 조사차수 : double 형의 문자열 데이터를 int 형의 문자열 데이터로 변환 (2.0 -> 2)
    		model.setExmnOder(CommUtils.toIntString(model.getExmnOder()));
    		
    		// 식별ID, 조사차수 조건 본조사정보 조회
    		// : 피해지역,사업차수,신청번호,월지급금액,지급등록가능여부,지급기간
    		RcperLvlhVO obj = null;
    		try {
	    		obj = rcperLvlhDtlsDAO.viewRcperLvlhMnsvy(model);
    		} catch(DataAccessException ex) {
        		// 중복된 데이터가 존재합니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.tooManyResult", new String[] { 
    					String.valueOf(sn),
    					model.getIdntfcId(),
    					model.getExmnOder()
    			});
    		}
    		if (obj == null) {
        		// 본조사 정보가 존재하지 않습니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.notExistMnsvy", new String[] { String.valueOf(sn) });
    		}
    		// 지급등록가능여부가 'Y'가 아닌 경우
    		if (!CommConst.YES.equals(obj.getGiveUseYn())) {
        		// 피해등급산정이 완료되지 않았습니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.notExistDmgeGrd", new String[] { String.valueOf(sn) });
    		}
    		// 지급기간이 정의되지 않은 경우
    		if (CommUtils.isEmpty(obj.getGiveBgngYm()) ||
    			CommUtils.isEmpty(obj.getGiveEndYm ())) {
        		// 지급기한이 등록되지 않았습니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.notExistDeadline", new String[] { String.valueOf(sn) });
    		}
    		
    		// 지급년월이 지급기간에 포함되지 않는 경우
    		if (CommUtils.strToInt(model.getGiveYm()) < CommUtils.strToInt(obj.getGiveBgngYm()) ||
    			CommUtils.strToInt(model.getGiveYm()) > CommUtils.strToInt(obj.getGiveEndYm ())) {
        		// 지급년월[{1}]이 지급기간[{2}~{3}]내에 포함되지 않습니다.
    			throw processException("error.adm.rcperLvlhDtls.excel.notIncludePeriod", new String[] { String.valueOf(sn), 
    					String.valueOf(model.getGiveYm  ()), 
    					String.valueOf(obj.getGiveBgngYm()), 
    					String.valueOf(obj.getGiveEndYm ()) 
    			});
    		}
    		model.setBizAreaCd(obj.getBizAreaCd()); // 피해지역
    		model.setBizOder  (obj.getBizOder  ()); // 사업차수
    		model.setExmnOder (obj.getExmnOder ()); // 조사차수
    		model.setAplyNo   (obj.getAplyNo   ()); // 신청번호
    		model.setGiveAmt  (obj.getGiveAmt  ()); // 월지급금액
    		
    		// 기지급된 항목인 경우
    		if (existRcperLvlhDtls(model)) {
    			// 요양생활수당 지급 수정 처리
    			ret += updtRcperLvlhDtls(model);
    		}
    		// 신규인 경우
    		else {
    			// 요양생활수당 지급 등록 처리
	    		if (regiRcperLvlhDtls(model) > 0) {
	    			// 구제급여신청 상태 업데이트
	    			reliefService.updtReliefStatus(
	    				model.getAplyNo(),    // 신청번호 
	    				CommConst.PRGRE_GIVE, // 상태코드 (지급)
	    				model.getGsUserNo()   // 등록자번호
	    			);
	    			ret++;
	    		}
    		}
        	sn++;
    	}
    	return ret;
	}
}