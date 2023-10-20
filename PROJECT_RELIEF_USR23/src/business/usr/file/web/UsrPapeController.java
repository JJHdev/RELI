package business.usr.file.web;

import java.util.ArrayList;
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

import business.com.file.service.PapeMngService;
import business.com.file.service.PapeMngVO;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import common.util.FileUtils;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 자료실 신청서류양식 Controller
 *
 * @class   : UsrPapeController
 * @author  : LSH
 * @since   : 2021.12.03
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *  2021.12.03  LSH        UsrBbsController에서 분리
 */
@Controller
@SuppressWarnings({ "all" })
public class UsrPapeController extends BaseController {

	@Resource(name = "PapeMngService")
	protected PapeMngService papeMngService;

	@Resource(name = "fileManager")
	protected FileManager fileManager;

    /**
     * [자료실] 신청서류양식 화면 오픈
     */
    @RequestMapping("/usr/file/listPapeMng.do")
    public String listPapeMng(HttpServletRequest request
            , @RequestParam Map<String,String> reqMap
	        , ModelMap model
            , @ModelAttribute PapeMngVO papeMngVO) throws Exception {

        // -------------------- Default Setting Start ---------------------//
        setGlobalSession(papeMngVO);
        // -------------------- Default Setting End -----------------------//

        model.addAttribute("model", papeMngVO);

        return "usr/file/listPapeMng";
    }

    /**
     * 신청구분 목록 조회(구제급여일 때 사용)
     * @param request
     * @param papeMngVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/usr/file/getListPapeMngAplySe.do")
    @ResponseBody
    public List getListPapeMngAplySe(HttpServletRequest request
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {
        // -------------------- Default Setting Start ---------------------//
        setGlobalSession(papeMngVO);
        // -------------------- Default Setting End -----------------------//


        List list = papeMngService.listPapeMngAplySe(papeMngVO);
        return list;
    }

    /**
     * 급여 종류 목록 조회(구제급여일 때 사용)
     * @param request
     * @param papeMngVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/usr/file/getListPapeMngUpPape.do")
    @ResponseBody
    public List getListPapeMngUpPape(HttpServletRequest request
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {
        // -------------------- Default Setting Start ---------------------//
        setGlobalSession(papeMngVO);
        // -------------------- Default Setting End -----------------------//

        List list = papeMngService.listPapeMngUpPape(papeMngVO);
        return list;
    }

    /**
     * 신청구분과 급여 종류에 따른 제출서류 목록 조회
     * (구제급여일 때, 기타 일 때는 신청구분과 급여종류에 관계 없음 )
     * @param request
     * @param papeMngVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/usr/file/getListPapeMngPape.do")
    @ResponseBody
    public List getListPapeMngPape(HttpServletRequest request
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {

        List list = papeMngService.listPapeMngPape(papeMngVO);
        return list;
    }

    /**
     * 선택된 양식파일 압축파일 형태로 다운로드
     * @param request
     * @param response
     * @param reqMap
     * @param papeMngVO
     * @param model
     * @throws Exception
     */
    @RequestMapping("/usr/file/downPageMngFileZip.do")
    public void downPageMngFileZip(HttpServletRequest request
            , HttpServletResponse response
            , @RequestParam Map<String,String> reqMap
            , @ModelAttribute PapeMngVO papeMngVO
            , ModelMap model) throws Exception {

        // -------------------- Default Setting Start ---------------------//
        setGlobalSession(papeMngVO);
        // -------------------- Default Setting End -----------------------//

        // 파일타입
        FileDirectory fd = FileDirectory.FORMFILE;
        // zip파일 명 header
        String zipPrefixNm = "신청서류양식_";

        // 2021.12.29 CSLEE BASE64 DECODE
        List<String> reliefPapeCdList = papeMngVO.getReliefPapeCdList();
        if(reliefPapeCdList != null && !reliefPapeCdList.isEmpty()) {
            List<String> reliefCdList = new ArrayList<String>();
            for(String cd : reliefPapeCdList) {
                reliefCdList.add(EgovFileScrty.decode(cd));
            }
            papeMngVO.setReliefPapeCdList(reliefCdList);

            zipPrefixNm += "급여구제_";
        }
        List<String> biocidePapeCdList = papeMngVO.getBiocidePapeCdList();
        if(biocidePapeCdList != null && !biocidePapeCdList.isEmpty()) {
            List<String> biocideCdList = new ArrayList<String>();
            for(String cd : biocidePapeCdList) {
            	biocideCdList.add(EgovFileScrty.decode(cd));
            }
            papeMngVO.setBiocidePapeCdList(biocideCdList);

            zipPrefixNm += "급여구제_";
        }
        List<String> lwstPapeCdList   = papeMngVO.getLwstPapeCdList();
        if(lwstPapeCdList != null && !lwstPapeCdList.isEmpty()) {
            List<String> lwstCdList = new ArrayList<String>();
            for(String cd : lwstPapeCdList) {
                lwstCdList.add(EgovFileScrty.decode(cd));
            }
            papeMngVO.setLwstPapeCdList(lwstCdList);

            zipPrefixNm += "취약계층소송지원_";
        }

        // 대상 양식 파일 정보 목록 조회
        List<PapeMngVO> list = papeMngService.listPapeMngDownFile(papeMngVO);

        if (list == null ||
                list.size() == 0)
                throw new EgovBizException("다운로드할 첨부파일이 없습니다.");

        List<FileInfo> files = new ArrayList<FileInfo>();

        for (PapeMngVO vo : list) {

            String fileNm     = vo.getFileNm();
            String downFileNm = vo.getFileNm();

            if(!CommUtils.isEmpty(fileNm)) {
                String fileExt = fileNm.substring(fileNm.indexOf(".")+1);
                // 서류명 항목으로 다운로드 파일명을 적용함.
                String papeNm  = FileUtils.convertInvalidFileName(CommUtils.nvlTrim(vo.getPapeNm()));
                downFileNm     = "[양식]"+papeNm + "." + fileExt;
            }

            // 파일 다운로드 공통함수 사용
            FileInfo fileInfo = FileInfo.builder()
                    .saveName(vo.getFileNm())       // 서버에 저장된 파일명
                    .fileName(downFileNm)
                    .filePath(vo.getFilePath())
                    .fullPath(fd.getRealPath(vo.getFilePath()))
                    .fileType(fd.getType())
                    .build();
            files.add(fileInfo);
        }

        // 2022.01.11 CSLEE 수정,
        boolean isSuccess = true;
        try {
            // 실제 파일 압축 다운로드 처리
            fileManager.procFileZipDownload(request, response, files, zipPrefixNm);
            //fileManager.procFileZipDownload(request, response, files, );
        } catch (Exception ex) {
            isSuccess = false;
            throw ex;
        } finally {
            // 2022.01.11 CSLEE 추가,
            // 다운로드 COUNT 저장
            if(isSuccess) {
                try {
                    papeMngService.updtPapeMngDownCount(papeMngVO);
                }catch(Exception ex) {
                    logger.error("[Error] update download count!", ex);
                }
            }
        }
    }
}
