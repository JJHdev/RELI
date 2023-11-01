package business.usr.file.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.file.service.CmitFileService;
import business.com.file.service.CmitFileVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * [컨트롤러클래스] - 위원회첨부파일 Controller
 *
 * @class   : UsrCmitFileController
 * @author  : LSH
 * @since   : 2023.10.26
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */
@Controller
@SuppressWarnings({"all"})
public class UsrCmitFileController extends BaseController {

    @Resource(name="CmitFileService")
    protected CmitFileService cmitFileService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 2023.10.26 LSH
     * 위원회 제출서류목록조회 목록JSON 반환 (EasyUI GRID)
     * comm_component.js 의 appCmitFile 에서 사용됨
     */
    @RequestMapping("/usr/file/getListCmitFile.do")
    @ResponseBody
    public Map getListCmitFile(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute CmitFileVO cmitFileVO
            , ModelMap model) throws Exception {

        setGlobalSession(cmitFileVO);
        // -------------------- Default Setting End -----------------------//

        List list = cmitFileService.listCmitFile(cmitFileVO);

        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 2023.10.26 LSH
     * 위원회 제출서류 다운로드
     * comm_component.js 의 appCmitFile 에서 사용됨
     */
    @RequestMapping(value="/usr/file/downloadCmitFile.do")
    public void downloadCmitFile(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam String sn
    		) throws Exception {

    	Map paramMap = getParameterMap(request, true);

    	// 2021.12.28 LSH BASE64 DECODE 
    	String decSn = EgovFileScrty.decode(sn);

        // 다운로드할 파일 정보 조회
    	CmitFileVO fileVO = cmitFileService.viewCmitFile(decSn);

        FileInfo fileInfo = FileInfo.builder()
							.saveName(fileVO.getStrgNm())
							.fileName(fileVO.getFileNm())
							.fullPath(FileDirectory.CMIT.getRealPath(fileVO.getFilePath()))
							.decrypt(false)
							.build();

        // 실제 파일 다운로드 처리
        fileManager.procFileDownload(request, response, fileInfo);
    }

    /**
     * 2023.10.26 LSH 
     * hwpjs 미리보기용 한글문서 URL링크보기
     * comm_component.js 의 appCmitFile 에서 사용됨
     */
    @RequestMapping(value = "/usr/file/linkCmitHwp{sn}.do", method= RequestMethod.GET)
    public void linkCmitHwp(HttpServletRequest request,
    		HttpServletResponse response,
    		@PathVariable("sn") String sn
    	) throws Exception {

    	Map paramMap = getParameterMap(request, true);
    	// 파일정보조회
    	CmitFileVO fileVO = cmitFileService.viewCmitFile(sn);
        // 파일문서타입
        FileDirectory d = FileDirectory.CMIT;
        // 파일경로
        String filePath = d.getRealPath(fileVO.getFilePath());
        // 파일정보
        FileInfo f = FileInfo.builder()
        		.fullPath(filePath)
        		.saveName(fileVO.getStrgNm())
        		.decrypt (true)
        		.build();
        // 문서 URL 링크 스트리밍
    	fileManager.linkDoc(f, request, response);
    }
}
