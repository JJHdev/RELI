package business.com.file.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.ExmnFileService;
import business.com.file.service.ExmnFileVO;
import common.base.BaseService;
import common.file.FileDirectory;
import common.util.FileUtils;

/**
 * [서비스클래스] - 피해조사첨부파일 Service 구현 클래스
 * 
 * @class   : ExmnFileServiceImpl
 * @author  : LSH
 * @since   : 2021.11.23
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("ExmnFileService")
@SuppressWarnings({"all"})
public class ExmnFileServiceImpl extends BaseService implements ExmnFileService {

    @Resource(name = "ExmnFileDAO")
    private ExmnFileDAO exmnFileDAO;

    /**
     * 피해조사첨부파일 목록조회
     */
    @Override
    public List listExmnFile(ExmnFileVO exmnFileVO) throws Exception {
    	return exmnFileDAO.listExmnFile(exmnFileVO);
    }

    /**
     * 피해조사첨부파일 상세조회
     */
	@Override
	public ExmnFileVO viewExmnFile(ExmnFileVO exmnFileVO) throws Exception {
		return exmnFileDAO.viewExmnFile(exmnFileVO);
	}

    /**
     * 피해조사첨부파일 등록
     */
	@Override
	public int regiExmnFile(ExmnFileVO exmnFileVO) throws Exception {
        return exmnFileDAO.regiExmnFile(exmnFileVO);
    }

    /**
     * 피해조사첨부파일 수정
     */
	@Override
	public int updtExmnFile(ExmnFileVO exmnFileVO) throws Exception {
        return exmnFileDAO.updtExmnFile(exmnFileVO);
    }

    /**
     * 2021.11.26
     * 피해조사첨부파일 삭제
     * 실제 삭제되진 않으며, DEL_YN만 'Y'로 변경된다.
     * 물리적 파일은 "removed" 로 이동처리된다.
     */
	@Override
	public int deltExmnFile(ExmnFileVO exmnFileVO) throws Exception {
		
		// 파일정보 조회
		ExmnFileVO viewVO = viewExmnFile(exmnFileVO);
		if (viewVO == null)
			throw processException("error.comm.notTarget");
		
		// 첨부파일 삭제(DEL_YN = 'Y'로 변경됨)
		ExmnFileVO saveVO = ExmnFileVO.builder()
				.sn       (exmnFileVO.getSn())
				.gsUserNo (exmnFileVO.getGsUserNo())
				.delYn    (CommConst.YES)
				.build    ();
		
		// 파일경로타입
		FileDirectory d = FileDirectory.PRPTEXMN;
		
		int ret = updtExmnFile(saveVO); 
		if (ret > 0) {
			// 실제 경로에 저장된 파일을 삭제된파일 저장경로로 이동처리한다.
			d.moveToRemoved(viewVO.getFilePath(), viewVO.getStrgNm());
		}
        return ret;
    }
	
    /**
     * 2021.11.25
     * 피해조사첨부파일 등록
     */
	@Override
	public int saveExmnFile(List<ExmnFileVO> files, FileDirectory d) throws Exception {
		
		if (files == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		
		for (ExmnFileVO f : files) {
			
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
		    ret += regiExmnFile(f);
		}
		return ret;
	}
	
}