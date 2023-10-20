package business.adm.exmn.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.CommFormFile;
import business.com.exmn.service.MnsvyService;
import business.com.exmn.service.MnsvyVO;
import business.com.exmn.service.RcperLvlhService;
import business.com.exmn.service.RcperLvlhVO;
import business.com.relief.service.ReliefService;
import business.com.relief.service.ReliefVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.view.ExcelTempView;

/**
 * [컨트롤러클래스] - 요양생활수당관리 Controller
 * 
 * @class   : RcperLvlhController
 * @author  : LSH
 * @since   : 2021.11.30
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 * 2022.12.27   LSH         2차개발 - TB_RCPER_LVLH_MNG 사용제거
 * 2022.12.27   LSH         2차개발 - TB_RCPER_LVLH_GRD 사용 (피해등급산정)
 */
@Controller
@SuppressWarnings({"all"})
public class RcperLvlhController extends BaseController {

    @Resource(name="RcperLvlhService")
    protected RcperLvlhService rcperLvlhService;

    @Resource(name="MnsvyService")
    protected MnsvyService mnsvyService;

    @Resource(name="ReliefService")
    protected ReliefService reliefService;

    // 파일업로드용 
    @Resource(name="fileManager")
    protected FileManager fileManager;

    /**
     * 2022.12.27 LSH
     * 구제급여지급 - 요양생활수당 화면 오픈
     */
    @RequestMapping("/adm/exmn/listRcperLvlh.do")
    public String listRcperLvlh(HttpServletRequest request
	        , @ModelAttribute RcperLvlhVO rcperLvlhVO
            , ModelMap model) throws Exception {
				
        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", rcperLvlhVO);
    	
        return "adm/exmn/listRcperLvlh";
    }

    /**
     * 2023.01.05 LSH
     * 구제급여지급 - 요양생활수당 - 대상목록 JSON 반환 (EasyUI GRID)
     * ReliefController의 getListReliefGive 응용
     */
    @RequestMapping("/adm/exmn/getListRcperLvlh.do")
    @ResponseBody
    public Map getListRcperLvlh(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (paramMap.containsKey("page")) {
            int page = CommUtils.getInt(paramMap, "page");
            int size = CommUtils.getInt(paramMap, "rows");
        	list = reliefService.listReliefGive(reliefVO, page, size);
        }
        else {
        	list = reliefService.listReliefGive(reliefVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2023.01.02 LSH
     * 구제급여지급 - 요양생활수당 - 엑셀 다운로드
     */
    @RequestMapping("/adm/exmn/downRcperLvlhExcel.do")
    public String downRcperLvlhExcel(HttpServletRequest request
            , @ModelAttribute ReliefVO reliefVO
            , ModelMap model) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//

        List list = reliefService.listReliefGive(reliefVO);
        // 생성일자
        paramMap.put("downDate", CommUtils.getCurrDateTime());
		
		model.addAttribute(ExcelTempView.DATA_KEY    , list);
		model.addAttribute(ExcelTempView.PARAM_KEY   , paramMap);
		model.addAttribute(ExcelTempView.TEMPLATE_KEY, "RcperLvlh");
		model.addAttribute(ExcelTempView.DOWNLOAD_KEY, "요양생활수당_대상목록");

		return "excelTempView";
    }
    
    /**
     * 2022.12.27 LSH 
     * 구제급여지급 - 요양생활수당 - 피해등급산정 목록JSON 반환
     */
    @RequestMapping("/adm/exmn/getListRcperLvlhGrd.do")
    @ResponseBody
    public Map getListRcperLvlhGrd(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//

        List list = rcperLvlhService.listRcperLvlhGrd(rcperLvlhVO);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2022.12.27 LSH 
     * 구제급여지급 - 요양생활수당 - 피해등급산정 등급산출 
     */
    @RequestMapping("/adm/exmn/getRcperLvlhGrd.do")
    @ResponseBody
    public Map getRcperLvlhGrd(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//
        // 평가점수 / 최종피해등급 산출
        RcperLvlhVO obj = rcperLvlhService.viewDmgeGrdScre(rcperLvlhVO);
        if (obj == null)
        	return getFailure();
    	// 성공결과 반환
        return getSuccess(obj);
    }

    /**
     * 2022.12.27 LSH 
     * 구제급여지급 - 요양생활수당 - 피해등급산정 결과저장 처리 
     */
    @RequestMapping("/adm/exmn/saveRcperLvlhGrd.do")
    @ResponseBody
    public Map saveRcperLvlhGrd(HttpServletRequest request
			, @RequestBody RcperLvlhVO rcperLvlhVO) throws Exception {
    	
        setGlobalSession(rcperLvlhVO);
        
        if (rcperLvlhVO.getUserInfo() != null)
        	rcperLvlhVO.setGsUserNo(rcperLvlhVO.getUserInfo().getUserNo());
    	
        // 요양생활수당 피해등급산정 결과 저장한다.
    	if (rcperLvlhService.saveRcperLvlhGrd(rcperLvlhVO) > 0) {
        	// 성공결과 반환
            return getSuccess();
    	}
    	else {
        	// 실패결과 반환
            return getFailure(message.getMessage("error.comm.notProcess"));
    	}
    }
    
    /**
     * 2022.12.27 LSH 
     * 구제급여지급 - 요양생활수당 - 요양생활수당 지급정보 상세조회
     * - 본조사정보를 가져온다.
     */
    @RequestMapping("/adm/exmn/getRcperLvlh.do")
    @ResponseBody
    public Map getRcperLvlh(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//
        // 본조사정보 상세조회
        MnsvyVO mnsvyVO = mnsvyService.viewMnsvy(
        		MnsvyVO.builder()
        		.bizAreaCd(rcperLvlhVO.getBizAreaCd())
        		.bizOder  (rcperLvlhVO.getBizOder  ())
        		.exmnOder (rcperLvlhVO.getExmnOder ())
        		.aplyNo   (rcperLvlhVO.getAplyNo   ())
        		.build()
        );
        
        if (mnsvyVO == null)
        	return getFailure();

        RcperLvlhVO obj = RcperLvlhVO.builder()
        		.bizAreaCd     (mnsvyVO.getBizAreaCd    ())
        		.bizOder       (mnsvyVO.getBizOder      ())
        		.exmnOder      (mnsvyVO.getExmnOder     ())
        		.aplyNo        (mnsvyVO.getAplyNo       ())
		        .idntfcId      (mnsvyVO.getIdntfcId     ()) // 식별ID
		        .lastDmgeGrdCd (mnsvyVO.getLastDmgeGrdCd()) // 최종피해등급
		        .lastDmgeScre  (mnsvyVO.getLastDmgeScre ()) // 최종피해점수
		        .dmgeGrdYr     (mnsvyVO.getDmgeGrdYr    ()) // 최종피해등급 기준년도
		        .rcperLvlhAmt  (mnsvyVO.getRcperLvlhAmt ()) // 월지급금액
		        .rtroactAmt    (mnsvyVO.getRtroactAmt   ()) // 소급금액
		        .rtroactBgngYm (mnsvyVO.getRtroactBgngYm()) // 소급기간(시작)
		        .rtroactEndYm  (mnsvyVO.getRtroactEndYm ()) // 소급기간 (종료0
		        .giveBgngYm    (mnsvyVO.getGiveBgngYm   ()) // 지급기간(시작)
		        .giveEndYm     (mnsvyVO.getGiveEndYm    ()) // 지급기간(종료)
		        .build();

        // 요양생활수당 지급해야할 일시금금액 (미지급금 포함)
        long lumpSum = rcperLvlhService.getRcperLvlhLumpSum(obj);
        obj.setLumpSumAmt(String.valueOf(lumpSum));
        
        // 최종피해등급과 기준년도가 있는 경우 지급등록 가능
        if (CommUtils.isNotEmpty(obj.getLastDmgeGrdCd()) &&
        	CommUtils.isNotEmpty(obj.getDmgeGrdYr())) {
        	// 지급등록 가능
        	obj.setGiveUseYn(CommConst.YES);
            // 최종피해등급이 4,5등급인 경우 일시지급 가능
            if (CommUtils.isEqual(obj.getLastDmgeGrdCd(), "4") ||
            	CommUtils.isEqual(obj.getLastDmgeGrdCd(), "5")) {
            	// 일시지급 가능
                obj.setLumpSumYn(CommConst.YES);
            }
        }
    	// 성공결과 반환
        return getSuccess(obj);
    }
    
    /**
     * 2022.12.28 LSH 
     * 구제급여지급 - 요양생활수당 - 지급해야할 일시지급액 조회
     */
    @RequestMapping("/adm/exmn/getRcperLvlhLumpSum.do")
    @ResponseBody
    public Map getRcperLvlhLumpSum(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//

        // 요양생활수당 지급해야할 일시금금액 (미지급금 포함)
        long lumpSum = rcperLvlhService.getRcperLvlhLumpSum(rcperLvlhVO);
    	// 성공결과 반환
        return getSuccess(String.valueOf(lumpSum));
    }

    /**
     * 2022.12.27 LSH
     * 구제급여지급 - 요양생활수당 - 요양급여 지급현황 목록JSON 반환
     * (이전URL 재사용함)
     */
    @RequestMapping("/adm/exmn/getListRcperLvlhDtls.do")
    @ResponseBody
    public Map getListRcperLvlhDtls(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//

        List list = rcperLvlhService.listRcperLvlhDtls(rcperLvlhVO);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2022.12.27 LSH 
     * 구제급여지급 - 요양생활수당 - 요양생활수당 지급 지급등록 처리
     * (이전URL 재사용함)
     */
    @RequestMapping("/adm/exmn/saveRcperLvlhDtls.do")
    @ResponseBody
    public Map saveRcperLvlhDtls(HttpServletRequest request
			, @RequestBody RcperLvlhVO rcperLvlhVO) throws Exception {
    	
        setGlobalSession(rcperLvlhVO);
        
        if (rcperLvlhVO.getUserInfo() != null)
        	rcperLvlhVO.setGsUserNo(rcperLvlhVO.getUserInfo().getUserNo());
    	
        // 본조사정보 상세조회
        MnsvyVO mnsvyVO = mnsvyService.viewMnsvy(
        		MnsvyVO.builder()
        		.bizAreaCd(rcperLvlhVO.getBizAreaCd())
        		.bizOder  (rcperLvlhVO.getBizOder  ())
        		.exmnOder (rcperLvlhVO.getExmnOder ())
        		.aplyNo   (rcperLvlhVO.getAplyNo   ())
        		.build()
        );
        // 본조사정보가 없는 경우
        if (mnsvyVO == null) {
            return getFailure(message.getMessage("error.comm.notProcess"));
        }
        // 피해등급산정 완료여부 검증
        // 최종피해등급과 기준년도가 없는 경우
        if (CommUtils.isEmpty(mnsvyVO.getLastDmgeGrdCd()) ||
        	CommUtils.isEmpty(mnsvyVO.getDmgeGrdYr())) {
        	//피해등급산정이 완료되지 않았습니다.<br>
        	//먼저 요양생활수당 피해등급산정을 완료하세요.
        	return getFailure(message.getMessage("error.adm.rcperLvlhDtls.notExistMng"));
        }
        // 지급기한이 등록되어있지 않은 경우
        if (CommUtils.isEmpty(mnsvyVO.getGiveBgngYm()) ||
        	CommUtils.isEmpty(mnsvyVO.getGiveEndYm())) {
        	//지급기한이 등록되지 않았습니다. 
        	//먼저 지급기한을 등록하세요.
        	return getFailure(message.getMessage("error.adm.rcperLvlhDtls.notExistDeadline"));
        }
        // -------------------- Default Setting End -----------------------//
        RcperLvlhVO obj = rcperLvlhService.viewRcperLvlhDtls(rcperLvlhVO);
        if (obj != null) {
        	// 동일한 지급년월에 지급된 내역이 있습니다.
        	return getFailure(message.getMessage("error.adm.rcperLvlhDtls.duplGiveYm"));
        }
        // 요양생활수당지급상세를 저장한다.
    	if (rcperLvlhService.saveRcperLvlhDtls(rcperLvlhVO) > 0) {
        	// 성공결과 반환
            return getSuccess();
    	}
    	else {
        	// 실패결과 반환
            return getFailure(message.getMessage("error.comm.notProcess"));
    	}
    }

    /**
     * 2023.01.02 LSH 
     * 구제급여지급 - 요양생활수당 - 요양생활수당 지급 지급기한등록 처리
     */
    @RequestMapping("/adm/exmn/saveRcperLvlhDeadline.do")
    @ResponseBody
    public Map saveRcperLvlhDeadline(HttpServletRequest request
			, @RequestBody RcperLvlhVO rcperLvlhVO) throws Exception {
    	
        setGlobalSession(rcperLvlhVO);
        
        if (rcperLvlhVO.getUserInfo() != null)
        	rcperLvlhVO.setGsUserNo(rcperLvlhVO.getUserInfo().getUserNo());
    	
        // 지급기한을 저장한다.
    	if (rcperLvlhService.saveRcperLvlhDeadline(rcperLvlhVO) > 0) {
        	// 성공결과 반환
            return getSuccess();
    	}
    	else {
        	// 실패결과 반환
            return getFailure(message.getMessage("error.comm.notProcess"));
    	}
    }
    
    /**
     * 2022.12.29 LSH 
     * 구제급여지급 - 요양생활수당 - 요양생활수당지급 - 지급년월 중복체크
     */
    @RequestMapping("/adm/exmn/checkRcperLvlhDtlsDupl.do")
    @ResponseBody
    public Map checkRcperLvlhDtlsDupl(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//
        if (rcperLvlhService.existRcperLvlhDtls(rcperLvlhVO))
        	return getSuccess(CommConst.YES);
        else
        	return getSuccess(CommConst.NO);
    }
    
    /**
     * 2023.01.02 LSH 
     * 구제급여지급 - 요양생활수당 - 요양생활수당지급 - 소급지급여부 확인
     */
    @RequestMapping("/adm/exmn/checkRcperLvlhRtroact.do")
    @ResponseBody
    public Map checkRcperLvlhRtroact(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//
        rcperLvlhVO.setGiveSeCd(CommConst.RCP_GIVE_RTROACT);
        boolean rtroact = rcperLvlhService.checkRcperLvlhRtroact(rcperLvlhVO);
        if (rtroact)
        	return getSuccess(CommConst.YES);
        else
        	return getSuccess(CommConst.NO);
    }
    
    /**
     * 2022.12.29 LSH
     * 구제급여지급 - 요양생활수당 - 요양생활수당지급 - 소급결정내용 모달팝업 오픈
     */
    @RequestMapping("/adm/exmn/modalRtroact.do")
    public String modalRtroact(HttpServletRequest request
    		, @ModelAttribute RcperLvlhVO rcperLvlhVO
    		, ModelMap model) throws Exception {
    	
    	// 세션사용자정보를 모델객체에 담는다
        setGlobalSession(rcperLvlhVO);
        
        model.addAttribute("form", rcperLvlhVO);
        
        return "adm/exmn/modalRtroact";
    }
    
    /**
     * 2022.12.29 LSH 
     * 구제급여지급 - 요양생활수당 - 요양생활수당지급 - 소급결정내용 소급급액 합산 조회
     */
    @RequestMapping("/adm/exmn/getRtroactSum.do")
    @ResponseBody
    public Map getRtroactSum(HttpServletRequest request
            , @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {

        setGlobalSession(rcperLvlhVO);
        // -------------------- Default Setting End -----------------------//

        // 요양생활수당 소급기간 내의 지급할 금액 합산
        // 월단위 금액의 합산
        long rtroactSum = rcperLvlhService.getRtroactSum(rcperLvlhVO);
    	// 성공결과 반환
        return getSuccess(String.valueOf(rtroactSum));
    }

    /**
     * 2023.01.02 LSH
     * 구제급여지급 - 요양생활수당 - 등록양식 다운로드
     */
    @RequestMapping(value="/adm/exmn/downRcperLvlhForm.do")
    public void downRcperLvlhForm(HttpServletRequest request
    		, HttpServletResponse response) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);
    	
        // 다운로드할 양식 파일 정보
    	CommFormFile cf = CommFormFile.RCPERLVLH;
        
        FileInfo fileInfo = FileInfo.builder()
							.saveName(cf.getSaveName())
							.fileName(cf.getFileName())
							.fullPath(cf.getFullPath())
							.build();
        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }
    
    /**
     * 2023.01.03 LSH
     * 구제급여지급 - 요양생활수당 - 지급일자 일괄등록 엑셀업로드
     */
    @RequestMapping("/adm/exmn/loadRcperLvlhDtls.do")
    @ResponseBody
    public Map loadRcperLvlhDtls(HttpServletRequest request
			, @ModelAttribute RcperLvlhVO rcperLvlhVO) throws Exception {
    	
        setGlobalSession(rcperLvlhVO);
        
        if (rcperLvlhVO.getUserInfo() != null)
        	rcperLvlhVO.setGsUserNo(rcperLvlhVO.getUserInfo().getUserNo());
    	
	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    
	    if (files == null || files.size() != 1) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
	    }
	    FileInfo fileInfo = files.get(0);
    	if (!"Y".equals(fileInfo.getFileYn())) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
    	}
    	// 요양생활수당 지급일자 일괄등록 엑셀을 읽어 저장한다.
    	rcperLvlhService.loadRcperLvlhDtls(rcperLvlhVO, fileInfo);
    	
    	// 성공결과 반환
        return getSuccess();
    }

}
