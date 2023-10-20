package business.adm.gis.web;

import java.util.HashMap;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import business.adm.gis.service.AbstrctAddrVO;
import business.adm.gis.service.GisService;
import business.com.CommConst;
import business.com.CommFormFile;
import business.com.biz.service.BizMngService;
import business.com.biz.service.BizMngVO;
import business.com.exmn.service.MnsvyVO;
import business.com.relief.service.IdntfcService;
import business.com.relief.service.IdntfcVO;
import business.com.relief.service.ReliefVO;
import common.base.BaseController;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;

/**
 * [컨트롤러클래스] - GIS분석 Controller
 *
 * @class   : GisController
 * @author  : whjeong
 * @since   : 2022.11.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Controller
@SuppressWarnings({"all"})
public class GisController extends BaseController {
	
	@Resource(name = "IdntfcService")
	protected IdntfcService idntfcService;
	
	@Resource(name = "GisService")
	protected GisService gisService;
	
	@Resource(name = "BizMngService")
	protected BizMngService bizMngService;
	
	// 파일업로드용 
    @Resource(name="fileManager")
    protected FileManager fileManager;
    
	/**
     * 2022.11.03
     * 초본 주소 등록 화면
     */
    @RequestMapping("/adm/gis/listAbstrctAddr.do")
    public String listAbstrctAddr(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute IdntfcVO idntfcVO) throws Exception {
				
        setGlobalSession(idntfcVO);
        // -------------------- Default Setting End -----------------------//
        
        // 서류업무구분 정의
        //reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);
        // 세션사용자번호 정의
        idntfcVO.setGsUserNo(idntfcVO.getUserInfo().getUserNo());

        model.addAttribute("model", idntfcVO);
    	
        return "adm/gis/listAbstrctAddr";
    }
    
    /**
	 * 초본 주소 등록 - 피해자정보 목록 (EasyUI Grid)
	 */
	@RequestMapping("/adm/gis/getlistIdntfc.do")
	@ResponseBody
	public Map getlistIdntfc(HttpServletRequest request, ModelMap model, 
			@ModelAttribute IdntfcVO idntfcVO) throws Exception {

		setGlobalSession(idntfcVO);
		Map paramMap = getParameterMap(request, true);

		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = idntfcService.listIdntfc(idntfcVO, page, size);

		} else {
			list = idntfcService.listIdntfc(idntfcVO);

		}
		
		return getEasyUIResult(list);
	}
	
	/**
     * 2022.11.11 JWH
     * 피해자 상세정보 가져오기
     */
    @RequestMapping("/adm/gis/getIdntfc.do")
    @ResponseBody
    public Map getIdntfc(
    		HttpServletRequest request, 
            @ModelAttribute IdntfcVO idntfcVO
    	) throws Exception {
    	
        setGlobalSession(idntfcVO);
        // -------------------- Default Setting End -----------------------//
        // 피해자정보 상세조회
        IdntfcVO result = idntfcService.viewIdntfc(idntfcVO.getSufrerNo());
        if (result != null) {
	        // 세션사용자번호 정의
		    result.setGsUserNo(idntfcVO.getUserInfo().getUserNo());
        }

        //피해지역 조회
        BizMngVO bizMngVO = bizMngService.viewBizMngBySufrerNo(result.getSufrerNo());
        result.setBizArea(bizMngVO.getBizArea());
        bizMngVO.setSufrerNo(result.getSufrerNo());
        //초본주소 등록에서 피해자 피해지역, 최초/최종오염 발생년도 조회
        Map pollutnLiveResult = bizMngService.findPollutnLiveByBizMngEntity(bizMngVO);
        //최초 오염 발생년도
        result.setFrstPollutnOcrnYr(String.valueOf(bizMngVO.getFrstPollutnOcrnYr()));
        //최종 오염 종료년도
        result.setLastPollutnOcrnYr(String.valueOf(bizMngVO.getLastPollutnOcrnYr()));
        
        // 성공결과 반환
        return getSuccess(result);
    }
    
    /**
	 * 초본 주소 등록 - 초본주소 목록 (EasyUI Grid)
	 */
	@RequestMapping("/adm/gis/getlistAbstrctAddr.do")
	@ResponseBody
	public Map getlistAbstrctAddr(HttpServletRequest request, ModelMap model, 
			@ModelAttribute AbstrctAddrVO abstrctAddrVO) throws Exception {

		setGlobalSession(abstrctAddrVO);
		Map paramMap = getParameterMap(request, true);

		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = gisService.listAbstrctAddr(abstrctAddrVO, page, size);
		} else {
			list = gisService.listAbstrctAddr(abstrctAddrVO);
		}
		return getEasyUIResult(list);
	}
	
	/**
     * 2022.11.16 JWH
     * 피해자 초본주소 상세정보 가져오기
     */
    @RequestMapping("/adm/gis/getAbstrctAddr.do")
    @ResponseBody
    public Map getAbstrctAddr(
    		HttpServletRequest request, 
            @ModelAttribute AbstrctAddrVO abstrctAddrVO
    	) throws Exception {
    	
        setGlobalSession(abstrctAddrVO);
        // -------------------- Default Setting End -----------------------//
        // 피해자 초본주소 상세조회
        AbstrctAddrVO result = gisService.viewAbstrctAddr(abstrctAddrVO);
        if (result != null) {
	        // 세션사용자번호 정의
		    result.setGsUserNo(abstrctAddrVO.getUserInfo().getUserNo());
        }
        
    	// 성공결과 반환
        return getSuccess(result);
    }
    
    /**
     * 본조사계획 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/gis/saveAbstrctAddr.do")
    @ResponseBody
    public Map saveAbstrctAddr(HttpServletRequest request
			, @RequestBody AbstrctAddrVO abstrctAddrVO) throws Exception {
    	
        setGlobalSession(abstrctAddrVO);
        
        if (abstrctAddrVO.getUserInfo() != null)
        	abstrctAddrVO.setGsUserNo(abstrctAddrVO.getUserInfo().getUserNo());
    	
        // 피해자 초본주소를 저장한다.
    	String result = gisService.saveAbstrctAddr(abstrctAddrVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }
    
    /**
     * 2022.11.09 JWH
     * 초본주소  엑셀양식 다운로드
     */
    @RequestMapping(value="/adm/gis/downAbstrctForm.do")
    public void downAbstrctForm(HttpServletRequest request
    		, HttpServletResponse response) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);
    	
        // 다운로드할 양식 파일 정보
    	CommFormFile cf = CommFormFile.ABSTRCT;
        
        FileInfo fileInfo = FileInfo.builder()
							.saveName(cf.getSaveName())
							.fileName(cf.getFileName())
							.fullPath(cf.getFullPath())
							.build();
        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }
    
    /**
     * 2022.11.09
     * 초본주소 엑셀 업로드
     */
    @RequestMapping("/adm/gis/saveAbstrct.do")
    @ResponseBody
    public Map saveAbstrct(HttpServletRequest request
			, @ModelAttribute AbstrctAddrVO abstrctAddrVO) throws Exception {
    	
        setGlobalSession(abstrctAddrVO);
        
        if (abstrctAddrVO.getUserInfo() != null)
        	abstrctAddrVO.setGsUserNo(abstrctAddrVO.getUserInfo().getUserNo());
    	
	    // 파일을 임시경로에 업로드한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    
	    if (files == null || files.size() != 1) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
	    }
	    FileInfo fileInfo = files.get(0);
    	if (!"Y".equals(fileInfo.getFileYn())) {
	    	return getFailure(message.getMessage("error.file.notUpload"));
    	}
    	// 의료비 세부내역 엑셀을 읽어 저장한다.
    	gisService.loadAbstrctAddr(abstrctAddrVO, fileInfo);
    	
    	// 성공결과 반환
        return getSuccess();
    }
    
    /**
     * 2022.11.07
     * GIS 환경오염 영향분석  화면
     */
    @RequestMapping("/adm/gis/viewEvnpAffc.do")
    public String viewEvnpAffc(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute ReliefVO reliefVO) throws Exception {
				
        setGlobalSession(reliefVO);
        // -------------------- Default Setting End -----------------------//
        
        // 서류업무구분 정의
        //reliefVO.setPapeDtySeCd(CommConst.PAPE_DTY_RELIEF);
        // 세션사용자번호 정의
        reliefVO.setGsUserNo(reliefVO.getUserInfo().getUserNo());
        //피해지역 조회
		List<Map> area = gisService.getlistBizArea();
		
        model.addAttribute("model", reliefVO);
    	model.addAttribute("area", area);
        return "adm/gis/viewEvnpAffc";
    }
    /**
	 * GIS 환경 오염 영향분석 - 조회결과 (EasyUI Grid)
	 */
	@RequestMapping("/adm/gis/getlistIdntBizArea.do")
	@ResponseBody
	public Map getlistIdntBizArea(HttpServletRequest request, ModelMap model, 
			@ModelAttribute IdntfcVO idntfcVO) throws Exception {

		setGlobalSession(idntfcVO);
		Map paramMap = getParameterMap(request, true);
		
		List list = null;

		if (paramMap.containsKey("page")) {
			int page = CommUtils.getInt(paramMap, "page");
			int size = CommUtils.getInt(paramMap, "rows");
			list = gisService.listIdntBizArea(paramMap, page, size);

		} else {
			list = gisService.listIdntBizArea(paramMap);

		}
		
		return getEasyUIResult(list);
	}
	
	/**
	 * GIS 환경 오염 영향분석 - 피해자 좌표 SELECT
	 */
	@RequestMapping("/adm/gis/getListEnvpAffcLoc.do")
	@ResponseBody
	public Map getEnvpAffcLoc(HttpServletRequest request, ModelMap model) throws Exception {
		Map paramMap = getParameterMap(request, true);
		
		List data = gisService.listEnvpAffcLoc(paramMap);

		return getSuccess(data);
	}
	
	/**
	 * GIS 환경 오염 영향분석 - 피해지역 좌표 SELECT
	 */
	@RequestMapping("/adm/gis/getEnvpAffcPollLoc.do")
	@ResponseBody
	public Map getEnvpAffcPollLoc(HttpServletRequest request, ModelMap model) throws Exception {
		Map paramMap = getParameterMap(request, true);
		
		//전체 피해지역 조회
		List<Map> data = gisService.getlistBizArea();
				
		return getSuccess(data);
	}
	
	/**
	 * GIS 환경 오염 영향분석 - 피해지역 좌표 SELECT
	 */
	@RequestMapping("/adm/gis/getEnvpAffcOnePollLoc.do")
	@ResponseBody
	public Map getEnvpAffcOnePollLoc(HttpServletRequest request, ModelMap model) throws Exception {
		Map paramMap = getParameterMap(request, true);
		
		//피해지역  한개 조회
		List<Map> data = gisService.getOneBizArea(paramMap);
				
		return getSuccess(data);
	}
	

	/**
	 * GIS 환경 오염 영향분석 - 클릭시 가장가까운 point 가져오기
	 */
	@RequestMapping("/adm/gis/getCoordinateByOnclick.do")
	@ResponseBody
	public Map getCoordinateByOnclick(HttpServletRequest request, ModelMap model) throws Exception {
		Map paramMap = getParameterMap(request, true);
		
		//피해지역  한개 조회
		List<Map> data = gisService.getCoordinateByOnclick(paramMap);
				
		return getSuccess(data);
	}
}
