package business.com.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.CmitFileService;
import business.com.file.service.CmitFileVO;
import common.base.BaseService;
import common.file.FileDirectory;
import common.util.FileUtils;

/**
 * [서비스클래스] - 위원회첨부파일 Service 구현 클래스
 * 
 * @class   : CmitFileServiceImpl
 * @author  : LSH
 * @since   : 2023.10.26
 * @version : 3.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("CmitFileService")
@SuppressWarnings({"all"})
public class CmitFileServiceImpl extends BaseService implements CmitFileService {

    @Resource(name = "CmitFileDAO")
    private CmitFileDAO cmitFileDAO;

    /**
     * 2023.10.26 LSH
     * 위원회첨부파일 목록조회
     */
    @Override
    public List listCmitFile(CmitFileVO cmitFileVO) throws Exception {
    	return cmitFileDAO.listCmitFile(cmitFileVO);
    }

    /**
     * 2023.10.26 LSH
     * 위원회첨부파일 상세조회
     */
	@Override
	public CmitFileVO viewCmitFile(String sn) throws Exception {
		return cmitFileDAO.viewCmitFile(CmitFileVO.builder().sn(sn).build());
	}

    /**
     * 위원회첨부파일 등록
     */
	@Override
	public int regiCmitFile(CmitFileVO cmitFileVO) throws Exception {
        return cmitFileDAO.regiCmitFile(cmitFileVO);
    }

    /**
     * 위원회첨부파일 수정
     */
	@Override
	public int updtCmitFile(CmitFileVO cmitFileVO) throws Exception {
        return cmitFileDAO.updtCmitFile(cmitFileVO);
    }

    /**
     * 위원회첨부파일 삭제
     * 실제 삭제되진 않으며, DEL_YN만 'Y'로 변경된다.
     * 물리적 파일은 "removed" 로 이동처리된다.
     */
	@Override
	public int deltCmitFile(CmitFileVO cmitFileVO) throws Exception {
		
		// 파일정보 조회
		CmitFileVO viewVO = viewCmitFile(cmitFileVO.getSn());
		if (viewVO == null)
			throw processException("error.comm.notTarget");
		
		// 첨부파일 삭제(DEL_YN = 'Y'로 변경됨)
		CmitFileVO saveVO = CmitFileVO.builder()
				.sn       (cmitFileVO.getSn())
				.gsUserNo (cmitFileVO.getGsUserNo())
				.delYn    (CommConst.YES)
				.build    ();
		
		// 파일경로타입
		FileDirectory d = FileDirectory.CMIT;
		
		int ret = updtCmitFile(saveVO); 
		if (ret > 0) {
			// 실제 경로에 저장된 파일을 삭제된파일 저장경로로 이동처리한다.
			d.moveToRemoved(viewVO.getFilePath(), viewVO.getStrgNm());
		}
        return ret;
    }
	
    /**
     * 위원회첨부파일 등록
     */
	@Override
	public int saveCmitFile(List<CmitFileVO> files, FileDirectory d) throws Exception {
		
		if (files == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		for (CmitFileVO f : files) {
			
			// 파일의 물리적 경로를 포함한 FULL NAME
			String orgnFile = d.getTempName(f.getStrgNm());
			String trgtFile = d.getRealName(f.getStrgNm());
			// 물리적 파일을 임시 경로에서 실제 경로로 이동처리한다.
			FileUtils.moveFile(orgnFile, trgtFile);
			
			// 파일경로맵핑
		    f.setFilePath(d.getPath());
		    // 파일삭제여부(N)
		    f.setDelYn(CommConst.NO);
		    
		    // 첨부파일등록
		    ret += regiCmitFile(f);
		}
		return ret;
	}
}