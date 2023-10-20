package business.adm.file.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommBizUtils;
import business.com.file.service.PapeCodeService;
import business.com.file.service.PapeCodeVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.com.utl.sim.service.EgovFileScrty;

/**
 * [컨트롤러클래스] - 서류코드관리 Controller
 *
 * @class   : PapeCodeController
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
public class PapeCodeController extends BaseController {

    @Resource(name="PapeCodeService")
    protected PapeCodeService papeCodeService;

    @Resource(name="fileManager")
    FileManager fileManager;

    /**
     * 서류코드관리 화면 오픈
     */
    @RequestMapping("/adm/file/listPapeCode.do")
    public String listPapeCode(HttpServletRequest request
	        , ModelMap model
            , @ModelAttribute PapeCodeVO papeCodeVO) throws Exception {

        setGlobalSession(papeCodeVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", papeCodeVO);

        return "adm/file/listPapeCode";
    }

    /**
     * 서류코드관리 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/file/getListPapeCode.do")
    @ResponseBody
    public Map getListPapeCode(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute PapeCodeVO papeCodeVO
            , ModelMap model) throws Exception {

        setGlobalSession(papeCodeVO);
        // -------------------- Default Setting End -----------------------//

        List list = null;
        if (reqMap.containsKey("page")) {
            int page = CommUtils.getInt(reqMap, "page");
            int size = CommUtils.getInt(reqMap, "rows");
        	list = papeCodeService.listPapeCode(papeCodeVO, page, size);
        }
        else {
        	list = papeCodeService.listPapeCode(papeCodeVO);
        }
        // Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 서류코드관리 계층형 목록JSON 반환 (EasyUI GRID)
     */
    @RequestMapping("/adm/file/getListPapeCodeTree.do")
    @ResponseBody
    public Map getListPapeCodeTree(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute PapeCodeVO papeCodeVO
            , ModelMap model) throws Exception {

        setGlobalSession(papeCodeVO);
        // -------------------- Default Setting End -----------------------//

        List data = papeCodeService.listPapeCodeTree(papeCodeVO);

        // 계층구조로 변경
		Map keys = new HashMap();
		keys.put("level" , "level");
		keys.put("itemId", "papeCd");
		keys.put("itemNm", "papeNm");
		keys.put("parentId", "upPapeCd");

		List<Map> list = CommBizUtils.buildTree(data, keys);

		// Easy UI GRID용 결과값 반환
        return getEasyUIResult(list);
    }

    /**
     * 서류코드관리 조회JSON 반환
     */
    @RequestMapping("/adm/file/getPapeCode.do")
    @ResponseBody
    public Map getPapeCode(HttpServletRequest request
            , @ModelAttribute PapeCodeVO papeCodeVO
			, ModelMap model) throws Exception {

        PapeCodeVO obj = papeCodeService.viewPapeCode(papeCodeVO);
        return getSuccess(obj);
    }

    /**
     * 서류코드관리 저장처리 (등록,수정,삭제)
     * mode 값에 따라 분기
     */
    @RequestMapping("/adm/file/savePapeCode.do")
    @ResponseBody
    public Map savePapeCode(HttpServletRequest request
			, @ModelAttribute PapeCodeVO papeCodeVO) throws Exception {

        setGlobalSession(papeCodeVO);

        if (papeCodeVO.getUserInfo() != null)
        	papeCodeVO.setGsUserNo(papeCodeVO.getUserInfo().getUserNo());

        // 파일을 임시경로에 업로드한다.
        List<FileInfo> files = fileManager.multiFileUploadByName(request, null);
        if(files.size() > 0) {
            // 임시경로에 저장된 파일 객체
            FileInfo fileInfo = files.get(0);

            papeCodeVO.setFileNm(fileInfo.getSaveName());
        }

        // 서류코드관리를 저장한다.
    	String result = papeCodeService.savePapeCode(papeCodeVO);
    	// 성공결과 반환
        return getSuccess("Message", result);
    }

    /**
     * 첨부파일 다운로드
     * @param request
     * @param response
     * @param papeCodeVO
     * @throws Exception
     */
    @RequestMapping(value="/adm/file/downloadPapeCode.do")
    public void downloadPapeCode(HttpServletRequest request
            , HttpServletResponse response
            , @ModelAttribute PapeCodeVO papeCodeVO) throws Exception {

        // 파일경로타입
        FileDirectory d = FileDirectory.FORMFILE;

        // 2021.12.29 CSLEE BASE64 DECODE
        papeCodeVO.setPapeCd(EgovFileScrty.decode(papeCodeVO.getPapeCd()));
        papeCodeVO.setUpPapeCd(EgovFileScrty.decode(papeCodeVO.getUpPapeCd()));

        // 상세정보 조회
        PapeCodeVO prePapeCodeVO = papeCodeService.viewPapeCode(papeCodeVO);

        String fileNm     = prePapeCodeVO.getFileNm();
        String downFileNm = fileNm;
        if(!CommUtils.isEmpty(fileNm)) {
            // 서류명 항목으로 다운로드 파일명을 적용함.
            downFileNm    = prePapeCodeVO.getDownFileNm();
        }
        // 다운로드할 파일의 정보를 공통파일모델객체로 맵핑
        FileInfo fileInfo = FileInfo.builder()
                            .saveName(fileNm)
                            .fileName(downFileNm)
                            .filePath(prePapeCodeVO.getFilePath())
                            .build();

        // 파일의 실제 전체경로 정의
        fileInfo.setFullPath(d.getRealPath(prePapeCodeVO.getFilePath()));

        // 2022.01.11 CSLEE 수정,
        boolean isSuccess = true;
        try {
            // 실제 파일 다운로드 처리
            fileManager.procFileDownload(request, response, fileInfo);
        } catch (Exception ex) {
            isSuccess = false;
            throw ex;
        } finally {
            // 2022.01.11 CSLEE 추가,
            // 다운로드 COUNT 저장
            if(isSuccess) {
                try {
                    papeCodeService.updtPapeCodeDownCount(prePapeCodeVO);
                }catch(Exception ex) {
                    logger.error("[Error] update download count!", ex);
                }
            }
        }
    }
}
