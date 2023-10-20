package business.bio.relief.web;

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

import business.bio.relief.service.BioAplyFileService;
import business.bio.relief.service.BioAplyFileVO;
import business.com.CommConst;
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
 * [컨트롤러클래스] - 살생물제품 신청첨부파일 Controller
 *
 * @class   : BioAplyFileController
 * @author  : LSH
 * @since   : 2023.01.25
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class BioAplyFileController extends BaseController {

    @Resource(name="BioAplyFileService")
    protected BioAplyFileService aplyFileService;

    @Resource(name="PapeMngService")
    protected PapeMngService papeMngService;

    @Resource(name="PapeCodeService")
    protected PapeCodeService papeCodeService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 신청서류그룹 리스트 조회
     * openBioReliefForm.js 에서 사용됨
     */
    @RequestMapping("/adm/bio/getListBioPapeGroup.do")
    @ResponseBody
    public List getListBioPapeGroup(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return papeMngService.getListPapeGroup(paramMap);
    }

    /**
     * 서류기준 신청파일 조회
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping("/adm/bio/getListBioAplyFileByPape.do")
    @ResponseBody
    public List getListBioAplyFileByPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return aplyFileService.listBioAplyFileByPape(paramMap);
    }

    /**
     * 신청서류 목록 조회
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping("/adm/bio/getListBioPape.do")
    @ResponseBody
    public List getListBioPape(HttpServletRequest request) throws Exception {
        Map paramMap = getParameterMap(request, true);
        return papeMngService.getListPape(paramMap);
    }

    /**
     * 공통사용 - 제출서류목록조회 목록JSON 반환 (EasyUI GRID)
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping("/adm/bio/getListBioAplySubmitFile.do")
    @ResponseBody
    public Map getListBioAplySubmitFile(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute BioAplyFileVO bioAplyFileVO
            , ModelMap model) throws Exception {

        setGlobalSession(bioAplyFileVO);
        // -------------------- Default Setting End -----------------------//


        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = aplyFileService.listBioAplySubmitFile(bioAplyFileVO, page, size);
        }
        else {
        	list = aplyFileService.listBioAplySubmitFile(bioAplyFileVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 첨부파일 임시 업로드 (업로드 팝업에서 호출)
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping("/adm/bio/uploadBioAplyFile.do")
    @ResponseBody
    public Map uploadFile(HttpServletRequest request) throws Exception {

    	Map paramMap = getParameterMap(request, true);

	    // 파일을 임시 경로에 업로드 한다.
	    List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
	    BioAplyFileVO obj = null;

	    // 단일 파일로 처리
	    if (files != null &&
	    	files.size() == 1) {

	    	FileInfo fileInfo = files.get(0);
	    	fileInfo.setGsUserNo((String)paramMap.get("gsUserNo"));

		    // 업로드한 파일정보를 저장한다.
		    obj = aplyFileService.saveBioTempFile(files.get(0));
	    }
	    return getSuccess("File", obj);
    }

    /**
     * 신청서류 양식파일 다운로드
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping("/adm/bio/downloadBioPapeFile.do")
    public void downloadBioPapeFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute PapeCodeVO papeCodeVO
    		) throws Exception {

        // 다운로드할 파일 정보 조회
    	papeCodeVO = papeCodeService.viewPapeCode(papeCodeVO);

        // 파일문서타입
        FileDirectory d = FileDirectory.BIOPAPER;

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
     * 첨부파일 다운로드
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping(value="/adm/bio/downloadBioAplyFile.do")
    public void downloadBioAplyFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn
    		) throws Exception {

    	Map paramMap = getParameterMap(request, true);

    	// 2021.12.28 LSH BASE64 DECODE 
    	String decSn = EgovFileScrty.decode(sn);

        // 다운로드할 파일 정보 조회
    	BioAplyFileVO fileVO = _getBioAplyFile(decSn, paramMap);

        FileInfo fileInfo = FileInfo.builder()
							.saveName(fileVO.getStrgNm())
							.fileName(fileVO.getFileNm())
        					// 2022.01.10 관리자가 아닌 경우 복호화
							.decrypt(!CommConst.isAdminRole((String)paramMap.get("gsRoleId")))
							.build();
        // 파일문서타입
        FileDirectory d = FileDirectory.BIOPAPER;

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
     * 문서번호 기준 첨부파일 압축 다운로드
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping(value="/adm/bio/downloadBioAplyFileZip.do")
    public void downloadBioAplyFileZip(HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute BioAplyFileVO bioAplyFileVO
    		) throws Exception {

    	Map paramMap = getParameterMap(request, true);

        // 첨부파일 경로 정보
        FileDirectory fd = FileDirectory.BIOPAPER;

        // 다운로드할 파일 목록
        List<Map> list = aplyFileService.listBioAplySubmitFile(bioAplyFileVO);

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
     * 문서/미디어 URL링크보기
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping(value="/adm/bio/linkBioAplyFile.do")
    @ResponseBody
    public HttpEntity<byte[]> linkBioFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn) throws Exception {

    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	BioAplyFileVO fileVO = _getBioAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.BIOPAPER;
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
     * hwpjs 미리보기용 문서 URL링크보기
     * comm_component.js 의 appBioAplyFile 에서 사용됨
     */
    @RequestMapping(value = "/adm/bio/linkBioAplyHwp{sn}.do", method= RequestMethod.GET)
    public void linkBioAplyHwp(HttpServletRequest request,
    		HttpServletResponse response,
    		@PathVariable("sn") String sn
    	) throws Exception {
    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	BioAplyFileVO fileVO = _getBioAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.BIOPAPER;
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
     * 파일 상세조회 및 권한 체크
     */
    private BioAplyFileVO _getBioAplyFile(String sn, Map paramMap) throws Exception {

    	// 파일정보조회
    	BioAplyFileVO fileVO = aplyFileService.viewBioAplySubmitFile(sn);
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
    private String _getBioAplyFilePhysicalName(String sn, Map paramMap) throws Exception {

    	// 파일정보조회
    	BioAplyFileVO fileVO = _getBioAplyFile(sn, paramMap);
        // 파일문서타입
        FileDirectory d = FileDirectory.BIOPAPER;
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
