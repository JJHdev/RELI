package business.com.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.PapeCodeService;
import business.com.file.service.PapeCodeVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * [서비스클래스] - 서류코드관리 Service 구현 클래스
 *
 * @class   : PapeCodeServiceImpl
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("PapeCodeService")
@SuppressWarnings({"all"})
public class PapeCodeServiceImpl extends BaseService implements PapeCodeService {

    @Resource(name = "PapeCodeDAO")
    private PapeCodeDAO papeCodeDAO;

    /**
     * 서류코드관리 페이징목록조회
     */
    @Override
    public PaginatedArrayList listPapeCode(PapeCodeVO papeCodeVO, int currPage, int pageSize) throws Exception {
    	return papeCodeDAO.listPapeCode(papeCodeVO, currPage, pageSize);
    }

    /**
     * 서류코드관리 목록조회
     */
    @Override
    public List listPapeCode(PapeCodeVO papeCodeVO) throws Exception {
    	return papeCodeDAO.listPapeCode(papeCodeVO);
    }

    /**
     * 서류코드관리 계층형 목록조회
     */
    @Override
    public List listPapeCodeTree(PapeCodeVO papeCodeVO) throws Exception {
    	return papeCodeDAO.listPapeCodeTree(papeCodeVO);
    }

    /**
     * 서류코드관리 상세조회
     */
	@Override
	public PapeCodeVO viewPapeCode(PapeCodeVO papeCodeVO) throws Exception {
	    PapeCodeVO vo = papeCodeDAO.viewPapeCode(papeCodeVO);
	    String fileNm = vo.getFileNm();

	    if(vo != null && !CommUtils.isEmpty(fileNm)) {

	        String fileExt    = fileNm.substring(fileNm.indexOf(".")+1);
	        String papeNm     = FileUtils.convertInvalidFileName(CommUtils.nvlTrim(vo.getPapeNm()));
            String downFileNm = "[양식]"+papeNm + "." + fileExt;

            vo.setFileExt(fileExt);
            vo.setDownFileNm(downFileNm);
	    }
		return vo;
	}

    /**
     * 서류코드관리 등록
     */
    private int regiPapeCode(PapeCodeVO papeCodeVO) throws Exception {

    	String papeCd   = papeCodeVO.getPapeCd();
    	String upPapeCd = papeCodeVO.getUpPapeCd();

        // 상위 코드와 하위 코드가 동일한 경우
    	if (CommUtils.isEqual(papeCd, upPapeCd)) {
            throw processException("exception.adm.sameUpperLowerCode", new String[] {papeCd});
    	}
        int count = 0;

        // 코드를 확인한다.
        count = (Integer) papeCodeDAO.confPapeCode(papeCodeVO);

        // 코드가 존재하는 경우
        if (count > 0) {
            // 상위 코드인 경우
            if (CommUtils.isEmpty(upPapeCd)) {
                throw processException("exception.adm.duplUpperCode", new String[] {papeCd});
            }
            // 하위 코드인 경우
            else {
                throw processException("exception.adm.duplLowerCode", new String[] {upPapeCd, papeCd});
            }
        }

        // 상위 코드를 확인한다.
        count = (Integer) papeCodeDAO.confUpperPapeCode(papeCd);

        // 상위 코드가 존재하는 경우
        if (count > 0) {
            throw processException("exception.adm.useUpperCode", new String[] {papeCd});
        }

        // 코드를 등록한다.
        return papeCodeDAO.regiPapeCode(papeCodeVO);
    }

    /**
     * 서류코드관리 수정
     */
    private int updtPapeCode(PapeCodeVO papeCodeVO) throws Exception {
        return papeCodeDAO.updtPapeCode(papeCodeVO);
    }

    /**
     * 서류코드관리 삭제
     */
    private int deltPapeCode(PapeCodeVO papeCodeVO) throws Exception {

    	String papeCd = papeCodeVO.getPapeCd();

        // 하위 코드를 확인한다.
        int count = (Integer) papeCodeDAO.confLowerPapeCode(papeCd);

        // 하위 코드가 존재하는 경우
        if (count > 0) {
            throw processException("exception.adm.hasLowerCode", new String[] {papeCd});
        }

        // 코드를 삭제한다.
        return papeCodeDAO.deltPapeCode(papeCodeVO);
    }

    /**
     * 서류코드관리 등록,수정,삭제
     */
	@Override
	public String savePapeCode(PapeCodeVO papeCodeVO) throws Exception {

		if (papeCodeVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = papeCodeVO.getMode();

		// 파일경로타입 (FORMFILE : 양식파일경로)
        FileDirectory d = FileDirectory.FORMFILE;

        // 기존 데이터
        PapeCodeVO prePapeCodeVO = null;

        // ================
        // 등록/수정
        if (CommConst.UPDATE.equals(mode) || CommConst.INSERT.equals(mode)) {

            boolean isNewFile = false;

            //------------------------
            // 첨부파일 내용 처리
            if(!CommUtils.isEmpty(papeCodeVO.getFileNm())) {
                String orgnFile = d.getTempName(papeCodeVO.getFileNm());
                String trgtFile = d.getRealName(papeCodeVO.getFileNm());

                // 물리적 파일을 임시 경로에서 실제 경로로 이동처리한다.
                FileUtils.moveFile(orgnFile, trgtFile);

                // 파일경로맵핑
                papeCodeVO.setFilePath(d.getPath());

                // 파일 신규 업로드 여부
                isNewFile = true;
            }
            //------------------------
            // DB 저장 (수정)
            if (CommConst.UPDATE.equals(mode)) {
                // 기존 데이터 조회
                prePapeCodeVO = viewPapeCode(papeCodeVO);

                // 서류코드관리 수정
                ret = updtPapeCode(papeCodeVO);
                // 기존 파일이 존재하고 새로 업로드가 수행됐을 때 기존 파일 삭제 수행
                if(isNewFile && !CommUtils.isEmpty(prePapeCodeVO.getFileNm()) && ret > 0) {
                    // 실제 경로에 저장된 파일을 삭제된파일 저장경로로 이동처리한다.
                    d.moveToRemoved(prePapeCodeVO.getFilePath(), prePapeCodeVO.getFileNm());
                }
            }
            // DB 저장 (등록)
            else if (CommConst.INSERT.equals(mode)) {
                // 서류코드관리 등록
                ret = regiPapeCode(papeCodeVO);
            }
        }
        // ================
        // 삭제
        else if (CommConst.DELETE.equals(mode)) {
            // 기존 데이터 조회
            prePapeCodeVO = viewPapeCode(papeCodeVO);
			// 서류코드관리 삭제
			ret = deltPapeCode(papeCodeVO);

			// 기존 파일이 존재할 때 기존 파일 삭제 수행
            if(!CommUtils.isEmpty(prePapeCodeVO.getFileNm()) && ret > 0) {
                // 실제 경로에 저장된 파일을 삭제된파일 저장경로로 이동처리한다.
                d.moveToRemoved(prePapeCodeVO.getFilePath(), prePapeCodeVO.getFileNm());
            }
		}

        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

	/**
     * 2022.01.11 CSLEE 추가
     * 다운로드 카운트 증가 저장
     */
    public int updtPapeCodeDownCount(PapeCodeVO papeCodeVO) throws Exception {
        return papeCodeDAO.updtPapeCodeDownCount(papeCodeVO);
    }
}