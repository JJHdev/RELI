package business.bio.file.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.file.service.BioAplyFileService;
import business.bio.file.service.BioAplyFileVO;
import business.bio.relief.service.BioReliefVO;
import business.com.CommConst;
import commf.exception.BusinessException;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * [서비스클래스] - 살생물제품 신청첨부파일 Service 구현 클래스
 *
 * @class   : BioAplyFileServiceImpl
 * @author  : LSH
 * @since   : 2023.01.16
 * @version : 2.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("BioAplyFileService")
@SuppressWarnings({"all"})
public class BioAplyFileServiceImpl extends BaseService implements BioAplyFileService {

    @Resource(name = "BioAplyFileDAO")
    private BioAplyFileDAO aplyFileDAO;

    /**
     * 신청첨부파일 페이징목록조회
     */
    @Override
    public PaginatedArrayList listBioAplyFile(BioAplyFileVO aplyFileVO, int currPage, int pageSize) throws Exception {
    	return aplyFileDAO.listBioAplyFile(aplyFileVO, currPage, pageSize);
    }

    /**
     * 신청첨부파일 목록조회
     */
    @Override
    public List listBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception {
    	return aplyFileDAO.listBioAplyFile(aplyFileVO);
    }

    /**
     * 서류기준 신청파일 조회
     */
    public List listBioAplyFileByPape(Map paramMap) throws Exception{
        return aplyFileDAO.listBioAplyFileByPape(paramMap);
    }

    /**
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    @Override
    public PaginatedArrayList listBioAplySubmitFile(BioAplyFileVO aplyFileVO, int currPage, int pageSize) throws Exception {
    	return aplyFileDAO.listBioAplySubmitFile(aplyFileVO, currPage, pageSize);
    }

    /**
     * 공통사용 - 제출서류목록조회
     */
    @Override
    public List listBioAplySubmitFile(BioAplyFileVO aplyFileVO) throws Exception {
    	return aplyFileDAO.listBioAplySubmitFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 상세조회
     */
	@Override
	public BioAplyFileVO viewBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception {
		return aplyFileDAO.viewBioAplyFile(aplyFileVO);
	}

    /**
     * 공통사용 - 제출서류 상세 조회
     */
    @Override
    public BioAplyFileVO viewBioAplySubmitFile(String sn) throws Exception {
    	return aplyFileDAO.viewBioAplySubmitFile(sn);
    }

    /**
     * 신청첨부파일 등록
     */
    @Override
	public int regiBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.regiBioAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 수정
     */
    public int updtBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.updtBioAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 삭제
     */
    private int deltBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.deltBioAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 등록,수정,삭제
     */
	@Override
	public String saveBioAplyFile(BioAplyFileVO aplyFileVO) throws Exception {

		if (aplyFileVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = aplyFileVO.getMode();

		if (CommConst.UPDATE.equals(mode)) {
			// 신청첨부파일 수정
			ret = updtBioAplyFile(aplyFileVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 신청첨부파일 등록
			ret = regiBioAplyFile(aplyFileVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 신청첨부파일 삭제
			ret = deltBioAplyFile(aplyFileVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 신청파일 임시저장처리
     */
	@Override
	public BioAplyFileVO saveBioTempFile(FileInfo fileInfo) throws Exception {
    	if (fileInfo == null)
    		return null;

        // 파일이 첨부된 경우
        if (!"Y".equals(fileInfo.getFileYn()))
        	return null;

        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.BIOPAPER;

        BioAplyFileVO vo = BioAplyFileVO.builder()
                .dtySeCd    (fileInfo.getFileType())
        		.papeCd     (fileInfo.getDocuCd  ())
        		.dcmtNo     (fileInfo.getDocuNo  ())
        		.dtlDcmtNo  (fileInfo.getDocuSeq ())
                .fileNm     (fileInfo.getFileName())
                .strgNm     (fileInfo.getSaveName())
                .fileSz     (fileInfo.getFileSize()+"")
                .fileIdx    (fileInfo.getFileIdx ())
                .gsUserNo   (fileInfo.getGsUserNo())
                .delYn      (CommConst.NO)
                .tempYn     (CommConst.YES)
                .prcsStusCd (CommConst.PRCS_UNSUBMIT) // 미제출
                .orgSn      (fileInfo.getFileNo  ())  // 이전번호
                .build();

        // 파일정보 등록
        if (regiBioAplyFile(vo) > 0)
            return vo;

        return null;
	}

    /**
     * 구제급여 신청파일 실제저장처리
     */
	@Override
	public int saveBioReliefFile(BioReliefVO reliefVO) throws Exception {
		// 저장대상 신청파일목록
		List<BioAplyFileVO> saveFiles = reliefVO.getSaveFiles();
		// 삭제대상 신청파일목록
		List<BioAplyFileVO> removeFiles = reliefVO.getRemoveFiles();
		
        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.BIOPAPER;
		
		// 세션 사용자번호
		String gsUserNo = reliefVO.getUserInfo().getUserNo();
		// 세션 사용자ROLE
		String gsRoleId = reliefVO.getUserInfo().getRoleId();

		// 물리적삭제 대상목록
		List<String> removes = new ArrayList<String>();

		int ret = 0;

		// 삭제대상 파일이 있으면
		if (removeFiles != null &&
			removeFiles.size() > 0) {

			// 삭제대상파일의 권한체크 Validation
			for (BioAplyFileVO file : removeFiles) {
				// 파일정보 조회
				BioAplyFileVO org = viewBioAplyFile(file);
		    	// 파일처리 권한이 없는 경우
		    	// (관리자가 아니고 해당 파일생성자가 아닌 경우)
		    	if (org != null &&
		    		!CommConst.isAdminRole(gsRoleId) &&
		    		!CommUtils.isEqual(gsUserNo, org.getRgtrNo()))
		    		throw new BusinessException("error.file.notAccess");
			}

			for (BioAplyFileVO file : removeFiles) {
				// 임시경로에 업로드된 파일이면
				if ("Y".equals(file.getTempYn())) {
				    // 파일의 물리적 경로를 포함한 FULL NAME
					String phyFile = d.getTempName(file.getStrgNm());
					// 물리적 삭제 대상에 추가한다.
					removes.add(phyFile);
					// 파일 데이터를 삭제한다.
					deltBioAplyFile(file);
				}
				// 기저장된 파일이면
				else {
					// 실제 파일
					String orgnName = d.getRealName(file.getFilePath(), file.getStrgNm());
					// 삭제된 파일 저장 경로
					String trgtPath = FileUtils.mergePath(CommConst.REMOVE_PATH, file.getFilePath());
					// 전체 경로를 포함한 실제 파일
					String trgtName = d.getRealName(trgtPath, file.getStrgNm());
					// 물리적 파일을 removed 경로에 복사한다.
					FileUtils.copyFileWithDir(orgnName, trgtName, true);
					// 물리적 삭제 대상에 추가한다.
					removes.add(orgnName);

					BioAplyFileVO vo = BioAplyFileVO.builder()
							.gsUserNo(gsUserNo)
							.filePath(trgtPath)
							.delYn   ("Y")
							.sn      (file.getSn())
							.build();
					// 파일경로와 삭제여부를 업데이트한다.
					updtBioAplyFile(vo);
				}
			}
		}
		// 저장대상 파일이 있으면
		if (saveFiles != null &&
			saveFiles.size() > 0) {
			for (BioAplyFileVO file : saveFiles) {
				
				// 파일경로정보 정의
				BioAplyFileVO vo = BioAplyFileVO.builder()
		        		.gsUserNo (reliefVO.getUserInfo().getGsUserNo())
		        		.sn       (file.getSn())
		        		.dtlDcmtNo("0")
		                .build();
				
				// 파일경로 : ROOT+"/paper"+(/상위서류코드/서류코드/신청일자/) + 파일(난수)명
				String filePath = d.getPath()
						+ "/" + file.getUpPapeCd()
						+ "/" + file.getPapeCd()
						+ "/" + reliefVO.getAplyYmd();
		        
				vo.setDcmtNo   (reliefVO.getAplyNo());
				vo.setDtlDcmtNo(reliefVO.getAplyOder());
				
		        // 구제급여신청 제출하기인 경우 처리상태를 제출로 변경한다.
		        if (CommConst.PRGRE_APPLY.equals(reliefVO.getPrgreStusCd())) {
		        	vo.setPrcsStusCd(CommConst.PRCS_SUBMIT);
		        }
		        // 임시경로에 업로드된 파일이면
				if ("Y".equals(file.getTempYn())) {
					// 파일경로정의
					vo.setFilePath(filePath);
				}
				// 기업로드된 파일이면
				else {
					// 등록자번호 맵핑
					vo.setRgtrNo(vo.getGsUserNo());
				}
				// 임시경로에 업로드된 파일이면
				if ("Y".equals(file.getTempYn())) {
					// 저장파일명
					String strgNm   = file.getStrgNm();
				    // 파일의 물리적 경로를 포함한 FULL NAME
					String orgnFile = d.getTempName(strgNm);
					String trgtFile = d.getRealName(vo.getFilePath(), strgNm);
					// 물리적 파일을 실제 경로에 복사한다.
					FileUtils.copyFileWithDir(orgnFile, trgtFile, true);
					// 물리적 삭제 대상에 추가한다.
					removes.add(orgnFile);
				}
		        // 파일의 KEY정보를 업데이트한다.
		        updtBioAplyFile(vo);
		        ret++;
			}
		}

		// 물리적 삭제 대상이 있으면 일괄 삭제한다.
		if (removes.size() > 0) {
			for (String fileName : removes) {
				// 대상 파일을 삭제한다.
				FileUtils.deleteFile(fileName);
			}
		}
		return ret;
	}
}