package business.bio.relief.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.bio.relief.service.BioAplyFileService;
import business.bio.relief.service.BioAplyFileVO;
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
 * @since   : 2023.01.25
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
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    @Override
    public PaginatedArrayList listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO, int currPage, int pageSize) throws Exception {
    	return aplyFileDAO.listBioAplySubmitFile(bioAplyFileVO, currPage, pageSize);
    }
    /**
     * 공통사용 - 제출서류목록조회
     */
    @Override
    public List listBioAplySubmitFile(BioAplyFileVO bioAplyFileVO) throws Exception {
    	return aplyFileDAO.listBioAplySubmitFile(bioAplyFileVO);
    }
    /**
     * 공통사용 - 제출서류 상세 조회
     */
    @Override
    public BioAplyFileVO viewBioAplySubmitFile(String sn) throws Exception {
    	return aplyFileDAO.viewBioAplySubmitFile(sn);
    }
    /**
     * 서류기준 신청파일 조회
     */
    @Override
    public List listBioAplyFileByPape(Map paramMap) throws Exception{
        return aplyFileDAO.listBioAplyFileByPape(paramMap);
    }

    /**
     * 신청첨부파일 등록
     */
    private int regiBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception {
        return aplyFileDAO.regiBioAplyFile(bioAplyFileVO);
    }

    /**
     * 신청첨부파일 수정
     */
    private int updtBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception {
        return aplyFileDAO.updtBioAplyFile(bioAplyFileVO);
    }

    /**
     * 신청첨부파일 삭제
     */
    private int deltBioAplyFile(BioAplyFileVO bioAplyFileVO) throws Exception {
        return aplyFileDAO.deltBioAplyFile(bioAplyFileVO);
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
	public int saveBioReliefFile(BioReliefVO bioReliefVO) throws Exception {
		// 저장대상 신청파일목록
		List<BioAplyFileVO> saveFiles = bioReliefVO.getSaveFiles();
		// 삭제대상 신청파일목록
		List<BioAplyFileVO> removeFiles = bioReliefVO.getRemoveFiles();
        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.BIOPAPER;
		
		// 세션 사용자번호
		String gsUserNo = bioReliefVO.getUserInfo().getUserNo();
		// 세션 사용자ROLE
		String gsRoleId = bioReliefVO.getUserInfo().getRoleId();

		// 물리적삭제 대상목록
		List<String> removes = new ArrayList<String>();

		int ret = 0;

		// 삭제대상 파일이 있으면
		if (removeFiles != null &&
			removeFiles.size() > 0) {

			// 삭제대상파일의 권한체크 Validation
			for (BioAplyFileVO file : removeFiles) {
				// 파일정보 조회
				BioAplyFileVO org = aplyFileDAO.viewBioAplyFile(file);
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
				BioAplyFileVO vo = _buildBioAplyFileVO(bioReliefVO, file, d);

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
	
	// 파일경로 및 기본정보 정의 (공통함수)
	private BioAplyFileVO _buildBioAplyFileVO(BioReliefVO bioReliefVO, BioAplyFileVO file, FileDirectory d) {
		
		BioAplyFileVO ret = BioAplyFileVO.builder()
        		.gsUserNo (bioReliefVO.getUserInfo().getGsUserNo())
        		.sn       (file.getSn())
        		.dtlDcmtNo("0")
                .build();
		
		
		// 파일경로 : ROOT+"/bio/paper"+(/상위서류코드/서류코드/) + 파일(난수)명
		String filePath = d.getPath()
				        + "/" + file.getUpPapeCd()
				        + "/" + file.getPapeCd()
				        + "/" + bioReliefVO.getAplyYmd();
		ret.setDcmtNo   (bioReliefVO.getAplyNo());
		ret.setDtlDcmtNo("0");
		
        // 구제급여신청 제출하기인 경우
        // 처리상태를 제출로 변경한다.
        if (CommConst.PRGRE_APPLY.equals(bioReliefVO.getPrgreStusCd())) {
        	ret.setPrcsStusCd(CommConst.PRCS_SUBMIT);
        }
        // 임시경로에 업로드된 파일이면
		if ("Y".equals(file.getTempYn())) {
			// 파일경로정의
			ret.setFilePath(filePath);
		}
		// 기업로드된 파일이면
		else {
			// 등록자번호 맵핑
			ret.setRgtrNo(ret.getGsUserNo());
		}
		return ret;
	}

    /**
     * 구제급여신청 관리자 제출서류 추가등록 처리 (단일파일)
     * @return 저장한 파일번호
     */
	@Override
    public String saveBioReliefAddfile(BioReliefVO bioReliefVO, FileInfo fileInfo) throws Exception {

    	if (fileInfo == null)
    		return null;

        // 파일이 첨부되지 않은 경우
        if (!"Y".equals(fileInfo.getFileYn()))
        	return null;

        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.BIOPAPER;
        
        // 파일경로
        String filePath = d.getPath()
				+ "/" + bioReliefVO.getUpPapeCd()
				+ "/" + bioReliefVO.getPapeCd()
				+ "/" + bioReliefVO.getAplyYmd();
        
    	// papeCd와 upPapeCd 는 화면에서 받아옴
        BioAplyFileVO bioAplyFileVO = BioAplyFileVO.builder()
			        .dtySeCd    (CommConst.PAPE_DTY_BIO)
			        .gsUserNo   (bioReliefVO.getGsUserNo())
			        .dcmtNo     (bioReliefVO.getAplyNo  ())
			        .papeCd     (bioReliefVO.getPapeCd  ())
			        .fileNm     (fileInfo.getFileName())
			        .strgNm     (fileInfo.getSaveName())
			        .fileSz     (fileInfo.getFileSize()+"")
			        .fileIdx    (fileInfo.getFileIdx ())
			        .mngrRegYn  (CommConst.YES) // 관리자등록여부
			        .dtlDcmtNo  ("0")
			        .prcsStusCd (CommConst.PRCS_SUBMIT)   // 제출
			        .tempYn     (CommConst.YES)
			        .delYn      (CommConst.NO)
			        .filePath   (filePath) // 파일경로
			        .build();

	    // 파일의 물리적 경로를 포함한 FULL NAME
		String orgnFile = d.getTempName(bioAplyFileVO.getStrgNm());
		String trgtFile = d.getRealName(bioAplyFileVO.getFilePath(), bioAplyFileVO.getStrgNm());
		// 물리적 파일을 실제 경로에 복사한다.
		FileUtils.copyFileWithDir(orgnFile, trgtFile, true);

        // 파일정보 등록
        if (regiBioAplyFile(bioAplyFileVO) > 0) {
	        return bioAplyFileVO.getSn();
        }
        return null;
	}
}