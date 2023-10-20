package business.adm.file.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.file.service.ExmnFileService;
import business.com.file.service.ExmnFileVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * [컨트롤러클래스] - 피해조사첨부파일 Controller
 * 
 * @class   : ExmnFileController
 * @author  : LSH
 * @since   : 2021.11.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class ExmnFileController extends BaseController {

    @Resource(name="ExmnFileService")
    protected ExmnFileService exmnFileService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 피해조사첨부파일 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/file/getListExmnFile.do")
    @ResponseBody
    public Map getListExmnFile(HttpServletRequest request
            , @ModelAttribute ExmnFileVO exmnFileVO) throws Exception {

        setGlobalSession(exmnFileVO);
        // -------------------- Default Setting End -----------------------//

        List list = exmnFileService.listExmnFile(exmnFileVO);
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 피해조사첨부파일 조회JSON 반환
     */
    @RequestMapping("/adm/file/getExmnFile.do")
    @ResponseBody
    public Map getExmnFile(HttpServletRequest request
            , @ModelAttribute ExmnFileVO exmnFileVO) throws Exception {

        ExmnFileVO obj = exmnFileService.viewExmnFile(exmnFileVO);
        return getSuccess(obj);
    }

    /**
     * 2021.11.26 LSH
     * 피해조사첨부파일 삭제처리
     */
    @RequestMapping("/adm/file/deltExmnFile.do")
    @ResponseBody
    public Map deltExmnFile(HttpServletRequest request
			, @RequestBody ExmnFileVO exmnFileVO) throws Exception {
    	
        setGlobalSession(exmnFileVO);
        
        if (exmnFileVO.getUserInfo() != null)
        	exmnFileVO.setGsUserNo(exmnFileVO.getUserInfo().getUserNo());
    	
        // 피해조사첨부파일을 삭제한다.
    	exmnFileService.deltExmnFile(exmnFileVO);
    	// 성공결과 반환
        return getSuccess();
    }

    /**
     * 2021.11.25 LSH
     * 피해조사첨부파일 다운로드
     */
    @RequestMapping(value="/adm/file/downloadExmnFile.do")
    public void downloadExmnFile(HttpServletRequest request
    		, HttpServletResponse response
    		, @RequestParam String sn) throws Exception {
    	
    	Map paramMap = getParameterMap(request, true);

    	// 2021.12.28 LSH BASE64 DECODE 
    	String decSn = EgovFileScrty.decode(sn);
    	
        // 다운로드할 파일 정보 조회
    	ExmnFileVO fileVO = _getExmnFile(decSn);
        // 파일경로타입
        FileDirectory d = FileDirectory.getByCode(CommConst.EXMN+fileVO.getDtySeCd());
        
        FileInfo fileInfo = FileInfo.builder()
							.saveName(fileVO.getStrgNm())
							.fileName(fileVO.getFileNm())
							.fullPath(d.getRealPath(fileVO.getFilePath()))
        					// 2022.01.10 관리자가 아닌 경우 복호화
							.decrypt(!CommConst.isAdminRole((String)paramMap.get("gsRoleId")))
							.build();
        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 2021.11.25 LSH
     * 피해조사첨부파일 URL링크보기
     */
    @RequestMapping(value="/adm/file/linkExmnFile.do")
    @ResponseBody
    public HttpEntity<byte[]> linkExmnFile(HttpServletRequest request
    		, HttpServletResponse response
    		, @RequestParam String sn) throws Exception {
    	// 파일정보조회
    	ExmnFileVO fileVO = _getExmnFile(sn);
    	// 파일경로타입
        FileDirectory d = FileDirectory.getByCode(CommConst.EXMN+fileVO.getDtySeCd());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(d.getRealPath(fileVO.getFilePath()))
        		.saveName(fileVO.getStrgNm())
        		.decrypt (true)
        		.build();
        // 미디어 URL 링크용 HttpEntity 반환
    	return fileManager.linkFile(f, request, response);
    }

    /**
     * 2021.11.25 LSH
     * 피해조사첨부파일 hwpjs 미리보기용 한글문서 URL링크보기
     */
    @RequestMapping(value = "/adm/file/linkExmnHwp{sn}.do", method= RequestMethod.GET)
    public void linkExmnHwp(HttpServletRequest request
    		, HttpServletResponse response
    		, @PathVariable("sn") String sn) throws Exception {
    	// 파일정보조회
    	ExmnFileVO fileVO = _getExmnFile(sn);
    	// 파일경로타입
        FileDirectory d = FileDirectory.getByCode(CommConst.EXMN+fileVO.getDtySeCd());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(d.getRealPath(fileVO.getFilePath()))
        		.saveName(fileVO.getStrgNm())
        		.decrypt (true)
        		.build();
        // 문서 URL 링크 스트리밍
    	fileManager.linkDoc(f, request, response);
    }
    
    /**
     * 2021.11.25 LSH
     * 파일 상세조회
     */
    private ExmnFileVO _getExmnFile(String sn) throws Exception {
    	// 파일정보조회
    	return exmnFileService.viewExmnFile(ExmnFileVO.builder().sn(sn).build());
    }
}
