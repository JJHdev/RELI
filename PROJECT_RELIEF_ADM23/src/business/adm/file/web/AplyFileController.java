package business.adm.file.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.file.service.AplyFileService;
import business.com.file.service.AplyFileVO;
import business.com.file.service.PapeCodeService;
import business.com.file.service.PapeCodeVO;
import business.com.file.service.PapeMngService;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.util.FileUtils;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 신청첨부파일 Controller
 *
 * @class   : AplyFileController
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class AplyFileController extends BaseController {

    @Resource(name="AplyFileService")
    protected AplyFileService aplyFileService;

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngService;

    @Resource(name="PapeCodeService")
    protected PapeCodeService papeCodeService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 신청첨부파일 화면 오픈
     */
    @RequestMapping("/adm/file/listAplyFile.do")
    public String listAplyFile(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute AplyFileVO aplyFileVO) throws Exception {

        setGlobalSession(aplyFileVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", aplyFileVO);

        return "adm/file/listAplyFile";
    }

    /**
     * 신청첨부파일 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/file/getListAplyFile.do")
    @ResponseBody
    public Map getListAplyFile(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute AplyFileVO aplyFileVO
            , ModelMap model) throws Exception {

        setGlobalSession(aplyFileVO);
        // -------------------- Default Setting End -----------------------//


        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = aplyFileService.listAplyFile(aplyFileVO, page, size);
        }
        else {
        	list = aplyFileService.listAplyFile(aplyFileVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 신청첨부파일 조회JSON 반환
     */
    @RequestMapping("/adm/file/getAplyFile.do")
    @ResponseBody
    public Map getAplyFile(HttpServletRequest request
            , @ModelAttribute AplyFileVO aplyFileVO
			, ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);

        String sn = aplyFileVO.getSn();
        // 다운로드할 파일 정보 조회
    	AplyFileVO fileVO = _getAplyFile(sn, paramMap);

        return getSuccess(fileVO);
    }

    /**
     * 신청첨부파일 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/file/saveAplyFile.do")
    @ResponseBody
    public Map saveAplyFile(HttpServletRequest request
			, @ModelAttribute AplyFileVO aplyFileVO) throws Exception {

        setGlobalSession(aplyFileVO);

        if (aplyFileVO.getUserInfo() != null)
        	aplyFileVO.setGsUserNo(aplyFileVO.getUserInfo().getUserNo());

        // 신청첨부파일를 저장한다.
    	String result = aplyFileService.saveAplyFile(aplyFileVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 2021.10.08 LSH
     * 서류기준 신청파일 조회
     * comm_component.js 의 appAplyFile 에서 사용됨
     */
    @RequestMapping("/adm/file/getListAplyFileByPape.do")
    @ResponseBody
    public List getListAplyFileByPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return aplyFileService.listAplyFileByPape(paramMap);
    }

    /**
     * 2021.10.06 LSH
     * 신청서류 목록 조회
     * 2021.12.03 PapeMngController에서 이전됨
     */
    @RequestMapping("/adm/file/getListPape.do")
    @ResponseBody
    public List getListPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return papeMngService.getListPape(paramMap);
    }

    /**
     * 2021.10.08 LSH
     * 신청서류그룹 리스트 조회
     * 2021.12.03 PapeMngController에서 이전됨
     */
    @RequestMapping("/adm/file/getListPapeGroup.do")
    @ResponseBody
    public List getListPapeGroup(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return papeMngService.getListPapeGroup(paramMap);
    }

    /**
     * 2021.10.10 LSH
     * 신청서류 다운로드
     * 2021.12.03 PapeCodeController에서 이전됨
     */
    @RequestMapping("/adm/file/downloadPapeFile.do")
    public void downloadPapeFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute PapeCodeVO papeCodeVO
    		) throws Exception {

        // 다운로드할 파일 정보 조회
    	papeCodeVO = papeCodeService.viewPapeCode(papeCodeVO);

        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;

    	// TODO. 양식파일의 저장명칭이 정의되면 변경해야함
    	// 현재는 papeCd+".hwp"로 처리함
        FileInfo fileInfo = FileInfo.builder()
							.saveName(papeCodeVO.getPapeCd()+".hwp")
							.fileName(papeCodeVO.getFileNm())
							.filePath(papeCodeVO.getFilePath())
							.fullPath(d.getRealPath(papeCodeVO.getFilePath()))
							.build();

        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 2021.10.08 LSH
     * 첨부파일 임시 업로드 (업로드 팝업에서 호출)
     */
    @RequestMapping("/adm/file/uploadAplyFile.do")
    @ResponseBody
    public Map uploadFile(HttpServletRequest request) throws Exception {

    	Map paramMap = getParameterMap(request, true);

	    // 파일을 임시 경로에 업로드 한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    AplyFileVO obj = null;

	    // 단일 파일로 처리
	    if (files != null &&
	    	files.size() == 1) {

	    	FileInfo fileInfo = files.get(0);
	    	fileInfo.setGsUserNo((String)paramMap.get("gsUserNo"));

		    // 업로드한 파일정보를 저장한다.
		    obj = aplyFileService.saveTempFile(files.get(0));
	    }
	    return getSuccess("File", obj);
    }

    /**
     * 2021.10.10 LSH
     * 첨부파일 다운로드
     */
    @RequestMapping(value="/adm/file/downloadAplyFile.do")
    public void downloadAplyFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn
    		) throws Exception {

    	Map paramMap = getParameterMap(request, true);

    	// 2021.12.28 LSH BASE64 DECODE 
    	String decSn = EgovFileScrty.decode(sn);

        // 다운로드할 파일 정보 조회
    	AplyFileVO fileVO = _getAplyFile(decSn, paramMap);

        FileInfo fileInfo = FileInfo.builder()
							.saveName(fileVO.getStrgNm())
							.fileName(fileVO.getFileNm())
        					// 2022.01.10 관리자가 아닌 경우 복호화
							.decrypt(!CommConst.isAdminRole((String)paramMap.get("gsRoleId")))
							.build();
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;

        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo())) {
        	fileInfo.setFullPath(d.getTempDir());
        }
        else {
        	fileInfo.setFullPath(d.getRealPath(fileVO.getFilePath()));
        }
        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 2021.12.10 CSLEE
     * [관리자용] 파일 다운로드
     *
     * sn에 여러 sn 값을 ','로 연결한 문자열로 받아서 처리
     *
     * @param request
     * @param response
     * @param sn
     * @throws Exception
     */
    @RequestMapping(value="/adm/file/downloadAdmAplyFileZip.do")
    public void downloadAdmAplyFile(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam String sn
            ) throws Exception {

        Map paramMap = getParameterMap(request, true);

        // 배열로 변경
        String[] sns = sn.split(",");

        // 다중 파일정보조회  ---------------
        AplyFileVO pVO    = new AplyFileVO();
        pVO.setSns(sns);
        List list = aplyFileService.viewAplyFiles(pVO);

        // 오류 체크 ------------------------
        String gsRoleId = (String)paramMap.get("gsRoleId");
        if (list == null || list.isEmpty())
            throw new EgovBizException(message.getMessage("error.file.notExist"));
        // 관리자인 경우 ACCESS OK
        if (!CommConst.isAdminRole(gsRoleId))
            throw new EgovBizException(message.getMessage("error.file.notAccess"));

        // 다운로드할 압축파일 구성 ---------
        List<FileInfo> files = new ArrayList<FileInfo>();

        // 첨부파일 경로 정보
        FileDirectory fd = FileDirectory.PAPER;
        for(AplyFileVO item : (List<AplyFileVO>)list) {

            // 파일 다운로드 공통함수 사용
            FileInfo fileInfo = FileInfo.builder()
                                .fullPath(fd.getRealPath(item.getFilePath()))
                                .filePath(item.getFilePath())
                                .saveName(item.getStrgNm())
                                .fileName(item.getFileNm())
            					// 2022.01.10 파일복호화를 위한 타입정의
            					.fileType(item.getDtySeCd())
            					// 2022.01.10 관리자가 아닌 경우 복호화
    							.decrypt(!CommConst.isAdminRole((String)paramMap.get("gsRoleId")))
                                .build();
            files.add(fileInfo);
        }
        // 실제 파일 압축 다운로드 처리
        fileManager.procFileZipDownload(request, response, files, fd.getType());
    }


    /**
     * 2021.11.02 LSH
     * 문서번호 기준 첨부파일 압축 다운로드
     */
    @RequestMapping(value="/adm/file/downloadAplyFileZip.do")
    public void downloadAplyFileZip(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute AplyFileVO aplyFileVO
    		) throws Exception {

    	Map paramMap = getParameterMap(request, true);

        // 첨부파일 경로 정보
        FileDirectory fd = FileDirectory.PAPER;

        // 다운로드할 파일 목록
        List<Map> list = aplyFileService.listAplySubmitFile(aplyFileVO);

        if (list == null ||
        	list.size() == 0)
        	throw new EgovBizException("다운로드할 첨부파일이 없습니다.");

        List<FileInfo> files = new ArrayList<FileInfo>();

        for (Map vo : list) {

            // 파일 다운로드 공통함수 사용
            FileInfo fileInfo = FileInfo.builder()
			            		.fullPath(fd.getRealPath((String)vo.get("filePath")))
    							.filePath((String)vo.get("filePath"))
    							.saveName((String)vo.get("strgNm"))
    							.fileName((String)vo.get("fileNm"))
								.fileType((String)vo.get("fileType")) // 2022.01.10 파일복호화를 위한 타입정의
    							.build();
        	files.add(fileInfo);
        }
        // 실제 파일 압축 다운로드 처리
        fileManager.procFileZipDownload(request, response, files, fd.getType());
    }

    /**
     * 2021.10.10 LSH
     * 문서/미디어 URL링크보기
     */
    @RequestMapping(value="/adm/file/linkAplyFile.do")
    @ResponseBody
    public HttpEntity<byte[]> linkFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn) throws Exception {

    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	AplyFileVO fileVO = _getAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;
        // 파일경로
        String filePath = null;
        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo()))
        	filePath = d.getTempDir();
        else
        	filePath = d.getRealPath(fileVO.getFilePath());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(filePath)
        		.saveName(fileVO.getStrgNm())
        		.decrypt (true)
        		.build();
        
        // 미디어 URL 링크용 HttpEntity 반환
    	return fileManager.linkFile(f , request, response);
    }

    /**
     * 2021.10.15 LSH hwpjs 미리보기용 문서 URL링크보기
     */
    @RequestMapping(value = "/adm/file/linkAplyHwp{sn}.do", method= RequestMethod.GET)
    public void linkAplyHwp(HttpServletRequest request,
    		HttpServletResponse response,
    		@PathVariable("sn") String sn
    	) throws Exception {
    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	AplyFileVO fileVO = _getAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;
        // 파일경로
        String filePath = null;
        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo()))
        	filePath = d.getTempDir();
        else
        	filePath = d.getRealPath(fileVO.getFilePath());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(filePath)
        		.saveName(fileVO.getStrgNm())
        		.decrypt (true)
        		.build();
        // 문서 URL 링크 스트리밍
    	fileManager.linkDoc(f, request, response);
    }

    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/file/getListAplySubmitFile.do")
    @ResponseBody
    public Map getListAplySubmitFile(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute AplyFileVO aplyFileVO
            , ModelMap model) throws Exception {

        setGlobalSession(aplyFileVO);
        // -------------------- Default Setting End -----------------------//


        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = aplyFileService.listAplySubmitFile(aplyFileVO, page, size);
        }
        else {
        	list = aplyFileService.listAplySubmitFile(aplyFileVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류파일 조회JSON 반환
     */
    @RequestMapping("/adm/file/getAplySubmitFile.do")
    @ResponseBody
    public Map getAplySubmitFile(HttpServletRequest request
            , @ModelAttribute AplyFileVO aplyFileVO
			, ModelMap model) throws Exception {

        Map paramMap = getParameterMap(request, true);

        String sn = aplyFileVO.getSn();
        // 다운로드할 파일 정보 조회
    	AplyFileVO fileVO = _getAplyFile(sn, paramMap);

        return getSuccess(fileVO);
    }

    /**
     * 파일 상세조회 및 권한 체크
     */
    private AplyFileVO _getAplyFile(String sn, Map paramMap) throws Exception {

    	// 파일정보조회
    	AplyFileVO fileVO = aplyFileService.viewAplySubmitFile(sn);
    	if (fileVO == null)
    		throw new EgovBizException(message.getMessage("error.file.notExist"));

    	String gsRoleId = (String)paramMap.get("gsRoleId");
    	String gsUserNo = (String)paramMap.get("gsUserNo");
    	String gsUserId = (String)paramMap.get("gsUserId");
    	// 관리자인 경우 ACCESS OK
    	if (CommConst.isAdminRole(gsRoleId)) {
    		return fileVO;
    	}
    	// 회원사용자인 경우 본인인지 체크
    	else if (CommConst.isUserRole(gsRoleId)) {
    		// 파일 생성자이면 ACCESS OK
    		if (CommUtils.isEqual(gsUserNo, fileVO.getRgtrNo()))
    			return fileVO;
    	}
    	// 식별아이디사용자인 경우
    	else if (CommConst.isIdntfcRole(gsRoleId)) {
        	// 구제급여인 경우 동일한 식별ID이면 ACCESS OK
        	if (CommConst.PAPE_DTY_RELIEF.equals(fileVO.getDtySeCd()) &&
       			CommUtils.isEqual(fileVO.getIdntfcId(), gsUserId))
    			return fileVO;
    	}
    	// 그외엔 NOT ACCESS
		throw new EgovBizException(message.getMessage("error.file.notAccess"));
    }

    /**
     * 파일의 물리적 경로를 포함한 전체 파일명을 반환한다.
     */
    private String _getAplyFilePhysicalName(String sn, Map paramMap) throws Exception {

    	// 파일정보조회
    	AplyFileVO fileVO = _getAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.PAPER;
        // 파일 풀경로
        String fullName = null;

        // 임시경로인 경우
        if (CommUtils.isEmpty(fileVO.getDcmtNo())) {
        	fullName = FileUtils.mergePath(d.getTempDir(), fileVO.getStrgNm());
        }
        else {
        	fullName = FileUtils.mergePath(d.getRealPath(fileVO.getFilePath()), fileVO.getStrgNm());
        }
        return fullName;
    }
}
