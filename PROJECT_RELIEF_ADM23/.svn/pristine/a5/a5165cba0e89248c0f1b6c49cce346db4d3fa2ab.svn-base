package business.com.file.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.file.service.AplyFileService;
import business.com.file.service.AplyFileVO;
import business.com.relief.service.ReliefVO;
import business.com.support.service.LwstVO;
import business.sys.user.service.InfoIntrlckVO;
import commf.exception.BusinessException;
import commf.paging.PaginatedArrayList;
import common.base.BaseModel;
import common.base.BaseService;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * [서비스클래스] - 신청첨부파일 Service 구현 클래스
 *
 * @class   : AplyFileServiceImpl
 * @author  : LSH
 * @since   : 2021.10.07
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("AplyFileService")
@SuppressWarnings({"all"})
public class AplyFileServiceImpl extends BaseService implements AplyFileService {

    @Resource(name = "AplyFileDAO")
    private AplyFileDAO aplyFileDAO;

    /**
     * 신청첨부파일 페이징목록조회
     */
    @Override
    public PaginatedArrayList listAplyFile(AplyFileVO aplyFileVO, int currPage, int pageSize) throws Exception {
    	return aplyFileDAO.listAplyFile(aplyFileVO, currPage, pageSize);
    }

    /**
     * 신청첨부파일 목록조회
     */
    @Override
    public List listAplyFile(AplyFileVO aplyFileVO) throws Exception {
    	return aplyFileDAO.listAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 상세조회
     */
	@Override
	public AplyFileVO viewAplyFile(AplyFileVO aplyFileVO) throws Exception {
		return aplyFileDAO.viewAplyFile(aplyFileVO);
	}

	/**
	 * 2021.12.10 CSLEE
     * 다중 신청첨부파일 상세 조회
     */
    public List viewAplyFiles(AplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.viewAplyFiles(aplyFileVO);
    }

    /**
     * 신청첨부파일 등록
     */
    private int regiAplyFile(AplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.regiAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 수정
     */
    public int updtAplyFile(AplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.updtAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 삭제
     */
    private int deltAplyFile(AplyFileVO aplyFileVO) throws Exception {
        return aplyFileDAO.deltAplyFile(aplyFileVO);
    }

    /**
     * 신청첨부파일 등록,수정,삭제
     */
	@Override
	public String saveAplyFile(AplyFileVO aplyFileVO) throws Exception {

		if (aplyFileVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		String mode = aplyFileVO.getMode();

		if (CommConst.UPDATE.equals(mode)) {
			// 신청첨부파일 수정
			ret = updtAplyFile(aplyFileVO);
		}
		else if (CommConst.INSERT.equals(mode)) {
			// 신청첨부파일 등록
			ret = regiAplyFile(aplyFileVO);
		}
		else if (CommConst.DELETE.equals(mode)) {
			// 신청첨부파일 삭제
			ret = deltAplyFile(aplyFileVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}

    /**
     * 2021.10.08 LSH
     * 서류기준 신청파일 조회
     */
    public List listAplyFileByPape(Map paramMap) throws Exception{
        return aplyFileDAO.listAplyFileByPape(paramMap);
    }

    /**
     * 2021.10.08 LSH
     * 신청파일 임시저장처리
     */
    public AplyFileVO saveTempFile(FileInfo fileInfo) throws Exception {

    	if (fileInfo == null)
    		return null;

        // 파일이 첨부된 경우
        if (!"Y".equals(fileInfo.getFileYn()))
        	return null;

        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.PAPER;

        AplyFileVO vo = AplyFileVO.builder()
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
        if (regiAplyFile(vo) > 0)
            return vo;

        return null;
    }

    /**
     * 2021.10.12 LSH
     * 구제급여 신청파일 실제저장처리
     * 2021.10.30 신청파일 보완제출도 함께 처리함
     * 2021.12.11 파일처리를 공통으로 분리
     */
	@Override
	public int saveReliefFile(ReliefVO reliefVO) throws Exception {
		// 저장대상 신청파일목록
		List<AplyFileVO> saveFiles = reliefVO.getSaveFiles();
		// 삭제대상 신청파일목록
		List<AplyFileVO> removeFiles = reliefVO.getRemoveFiles();
		// 파일정보 저장처리
		return _saveAplyFile(reliefVO, saveFiles, removeFiles);
	}

    /**
     * 2021.12.11 LSH
     * 정보연동 신청파일 실제저장처리 (현재 등록만 있음)
     */
	@Override
	public int saveIntrlckFile(InfoIntrlckVO infoIntrlckVO) throws Exception {
		// 저장대상 신청파일목록
		List<AplyFileVO> saveFiles = infoIntrlckVO.getSaveFiles();
		// 삭제대상 신청파일목록
		List<AplyFileVO> removeFiles = infoIntrlckVO.getRemoveFiles();
		// 파일정보 저장처리
		return _saveAplyFile(infoIntrlckVO, saveFiles, removeFiles);
	}

    /**
     * 2021.12.11 LSH
     * 취약계층소송지원 신청파일 실제저장처리
     */
	@Override
	public int saveLwstFile(LwstVO lwstVO) throws Exception {
		// 저장대상 신청파일목록
		List<AplyFileVO> saveFiles = lwstVO.getSaveFiles();
		// 삭제대상 신청파일목록
		List<AplyFileVO> removeFiles = lwstVO.getRemoveFiles();
		// 파일정보 저장처리
		return _saveAplyFile(lwstVO, saveFiles, removeFiles);
	}
	
	// 2021.12.11 파일경로 및 기본정보 정의 (공통함수)
	private AplyFileVO _buildAplyFileVO(BaseModel modelVO, AplyFileVO file, FileDirectory d) {
		
		AplyFileVO ret = AplyFileVO.builder()
        		.gsUserNo (modelVO.getUserInfo().getGsUserNo())
        		.sn       (file.getSn())
        		.dtlDcmtNo("0")
                .build();
		
		String filePath = null;
		
		// 구제급여 신청파일인 경우
		if (CommConst.PAPE_DTY_RELIEF.equals(file.getDtySeCd())) {
			
			ReliefVO reliefVO = (ReliefVO)modelVO;
			
			// 파일경로 : ROOT+"/paper"+(/지역코드/차수/상위서류코드/서류코드/) + 파일(난수)명
			// 2021.11.01 경로정보 변경
			// 파일경로 : ROOT+"/paper"+(/지역코드/차수/상위서류코드/서류코드/신청일자/) + 파일(난수)명
			// 2021.12.27 경로정보에서 지역코드/차수 제외 : 신청시 지역코드가 NULL 이므로
			filePath = d.getPath()
					//+ "/" + reliefVO.getBizAreaCd()
					//+ "/" + CommConst.BIZ_ODER_RELIEF
					+ "/" + file.getUpPapeCd()
					+ "/" + file.getPapeCd()
					+ "/" + reliefVO.getAplyYmd();
	        
			ret.setDcmtNo   (reliefVO.getAplyNo());
			ret.setDtlDcmtNo(reliefVO.getAplyOder());
			
	        // 구제급여신청 제출하기인 경우
	        // 처리상태를 제출로 변경한다.
	        if (CommConst.PRGRE_APPLY.equals(reliefVO.getPrgreStusCd())) {
	        	ret.setPrcsStusCd(CommConst.PRCS_SUBMIT);
	        }
		}
		// 정보연동 신청파일인 경우
		else if (CommConst.PAPE_DTY_INTRLCK.equals(file.getDtySeCd())) {
			
			InfoIntrlckVO infoIntrlckVO = (InfoIntrlckVO)modelVO;
			
			// 파일경로 : ROOT+"/paper"+(/상위서류코드/서류코드/신청일자) + 파일(난수)명
			// 2022.02.03 LSH 신청일자가 넘어오지 않으므로 현재날짜로 자동 맵핑함.
			filePath = d.getPath()
					+ "/" + file.getUpPapeCd()
					+ "/" + file.getPapeCd()
					+ "/" + CommUtils.getCurDateString();
					//+ "/" + infoIntrlckVO.getAplyYmd();

			ret.setDcmtNo    (infoIntrlckVO.getAplyNo());
			ret.setPrcsStusCd(CommConst.PRCS_SUBMIT);
		}
		// 취약계층소송지원 신청파일인 경우
		else if (CommConst.PAPE_DTY_LWST.equals(file.getDtySeCd())) {
			
			LwstVO lwstVO = (LwstVO)modelVO;
			
			// 파일경로 : ROOT+"/paper"+(/지역코드/상위서류코드/서류코드/신청일자) + 파일(난수)명
			// 2022.02.03 LSH 지역코드는 신청시 확인할 수 있는 정보가 아니므로 경로에서 제외함.
			// 2022.02.03 LSH 신청일자가 넘어오지 않으므로 현재날짜로 맵핑함.
			filePath = d.getPath()
					//+ "/" + lwstVO.getBizAreaCd()
					+ "/" + file.getUpPapeCd()
					+ "/" + file.getPapeCd()
					+ "/" + CommUtils.getCurDateString();
					//+ "/" + lwstVO.getAplyYmd();

			ret.setDcmtNo    (lwstVO.getAplyNo());
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

	// 2021.12.11 파일정보 저장처리 (공통함수)
	private int _saveAplyFile(BaseModel modelVO, List<AplyFileVO> saveFiles, List<AplyFileVO> removeFiles) throws Exception {
		
        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.PAPER;
		
		// 세션 사용자번호
		String gsUserNo = modelVO.getUserInfo().getUserNo();
		// 세션 사용자ROLE
		String gsRoleId = modelVO.getUserInfo().getRoleId();

		// 물리적삭제 대상목록
		List<String> removes = new ArrayList<String>();

		int ret = 0;

		// 삭제대상 파일이 있으면
		if (removeFiles != null &&
			removeFiles.size() > 0) {

			// 삭제대상파일의 권한체크 Validation
			for (AplyFileVO file : removeFiles) {
				// 파일정보 조회
				AplyFileVO org = viewAplyFile(file);
		    	// 파일처리 권한이 없는 경우
		    	// (관리자가 아니고 해당 파일생성자가 아닌 경우)
		    	if (org != null &&
		    		!CommConst.isAdminRole(gsRoleId) &&
		    		!CommUtils.isEqual(gsUserNo, org.getRgtrNo()))
		    		throw new BusinessException("error.file.notAccess");
			}

			for (AplyFileVO file : removeFiles) {
				// 임시경로에 업로드된 파일이면
				if ("Y".equals(file.getTempYn())) {
				    // 파일의 물리적 경로를 포함한 FULL NAME
					String phyFile = d.getTempName(file.getStrgNm());
					// 물리적 삭제 대상에 추가한다.
					removes.add(phyFile);
					// 파일 데이터를 삭제한다.
					deltAplyFile(file);
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

					AplyFileVO vo = AplyFileVO.builder()
							.gsUserNo(gsUserNo)
							.filePath(trgtPath)
							.delYn   ("Y")
							.sn      (file.getSn())
							.build();
					// 파일경로와 삭제여부를 업데이트한다.
					updtAplyFile(vo);
				}
			}
		}
		// 저장대상 파일이 있으면
		if (saveFiles != null &&
			saveFiles.size() > 0) {
			for (AplyFileVO file : saveFiles) {
				
				// 파일경로정보 정의
				AplyFileVO vo = _buildAplyFileVO(modelVO, file, d);

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
		        updtAplyFile(vo);
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

    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회 (페이징)
     */
    @Override
    public PaginatedArrayList listAplySubmitFile(AplyFileVO aplyFileVO, int currPage, int pageSize) throws Exception {
    	return aplyFileDAO.listAplySubmitFile(aplyFileVO, currPage, pageSize);
    }
    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류목록조회
     */
    @Override
    public List listAplySubmitFile(AplyFileVO aplyFileVO) throws Exception {
    	return aplyFileDAO.listAplySubmitFile(aplyFileVO);
    }
    /**
     * 2021.12.11 LSH
     * 공통사용 - 제출서류 상세 조회
     */
    @Override
    public AplyFileVO viewAplySubmitFile(String sn) throws Exception {
    	return aplyFileDAO.viewAplySubmitFile(sn);
    }
    /**
     * 2022.01.11 LSH
     * 일정시간이 지난 임시저장된 신청파일 삭제
     * 임시경로에 저장된 물리적 파일도 함께 삭제한다.
     */
	@Override
	public int deltAplyTempFiles(int diffDay) throws Exception {
		
		if (diffDay > -1)
			return 0;
		
		FileDirectory fd = FileDirectory.PAPER;
		
		Map params = new HashMap();
		params.put("diffDay", diffDay);
		
		List<Map> tempFiles = aplyFileDAO.listAplyTempFile(params);
		
		if (tempFiles == null)
			return 0;
		
		int ret = 0;
		
		for (Map f : tempFiles) {
			String tmpPath = fd.getTempDir();
			String tmpName = (String)f.get("strgNm");
			String tmpFile = FileUtils.mergePath(tmpPath, tmpName);
			String sn      = String.valueOf(f.get("sn"));
			// 신청파일정보 삭제
			if (deltAplyFile(AplyFileVO.builder().sn(sn).build()) > 0) {
				if (FileUtils.deleteFile(tmpFile)) {
					logger.debug("Aply temporary file delete : " + tmpFile);
				}
				ret++;
			}
		}
		return ret;
	}

    /**
     * 2022.12.06 LSH
     * 구제급여신청 관리자 제출서류 추가등록 처리 (단일파일)
     * @return 저장한 파일번호
     */
	@Override
    public String saveReliefAddfile(ReliefVO reliefVO, FileInfo fileInfo) throws Exception {

    	if (fileInfo == null)
    		return null;

        // 파일이 첨부되지 않은 경우
        if (!"Y".equals(fileInfo.getFileYn()))
        	return null;

        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.PAPER;
        
        // 파일경로
        String filePath = d.getPath()
				+ "/" + reliefVO.getUpPapeCd()
				+ "/" + reliefVO.getPapeCd()
				+ "/" + reliefVO.getAplyYmd();
        
    	// papeCd와 upPapeCd 는 화면에서 받아옴
        AplyFileVO aplyFileVO = AplyFileVO.builder()
			        .dtySeCd    (CommConst.PAPE_DTY_RELIEF)
			        .gsUserNo   (reliefVO.getGsUserNo())
			        .dcmtNo     (reliefVO.getAplyNo  ())
			        .papeCd     (reliefVO.getPapeCd  ())
			        .fileNm     (fileInfo.getFileName())
			        .strgNm     (fileInfo.getSaveName())
			        .fileSz     (fileInfo.getFileSize()+"")
			        .fileIdx    (fileInfo.getFileIdx ())
			        .mngrRegYn  (CommConst.YES) // 관리자등록여부
			        .dtlDcmtNo  (CommConst.APLY_ODER_RELIEF)
			        .prcsStusCd (CommConst.PRCS_SUBMIT)   // 제출
			        .tempYn     (CommConst.YES)
			        .delYn      (CommConst.NO)
			        .filePath   (filePath) // 파일경로
			        .build();

	    // 파일의 물리적 경로를 포함한 FULL NAME
		String orgnFile = d.getTempName(aplyFileVO.getStrgNm());
		String trgtFile = d.getRealName(aplyFileVO.getFilePath(), aplyFileVO.getStrgNm());
		// 물리적 파일을 실제 경로에 복사한다.
		FileUtils.copyFileWithDir(orgnFile, trgtFile, true);

        // 파일정보 등록
        if (regiAplyFile(aplyFileVO) > 0) {
	        return aplyFileVO.getSn();
        }
        return null;
	}

    /**
     * 2023.01.27 LSH
     * 취약계층소송지원신청 관리자 제출서류 추가등록 처리 (단일파일)
     * @return 저장한 파일번호
     */
	@Override
	public String saveLwstAddfile(LwstVO lwstVO, FileInfo fileInfo) throws Exception {

    	if (fileInfo == null)
    		return null;

        // 파일이 첨부되지 않은 경우
        if (!"Y".equals(fileInfo.getFileYn()))
        	return null;

        // 첨부파일 경로 정보
        FileDirectory d = FileDirectory.PAPER;
        
        // 파일경로
		// 신청일자가 넘어오지 않으므로 현재날짜로 맵핑함.
        String filePath = d.getPath()
				+ "/" + lwstVO.getUpPapeCd()
				+ "/" + lwstVO.getPapeCd()
				+ "/" + CommUtils.getCurDateString();

    	// papeCd와 upPapeCd 는 화면에서 받아옴
        AplyFileVO aplyFileVO = AplyFileVO.builder()
			        .dtySeCd    (CommConst.PAPE_DTY_LWST)
			        .gsUserNo   (lwstVO.getGsUserNo  ())
			        .dcmtNo     (lwstVO.getAplyNo    ())
			        .papeCd     (lwstVO.getPapeCd    ())
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
		String orgnFile = d.getTempName(aplyFileVO.getStrgNm());
		String trgtFile = d.getRealName(aplyFileVO.getFilePath(), aplyFileVO.getStrgNm());
		// 물리적 파일을 실제 경로에 복사한다.
		FileUtils.copyFileWithDir(orgnFile, trgtFile, true);

        // 파일정보 등록
        if (regiAplyFile(aplyFileVO) > 0) {
	        return aplyFileVO.getSn();
        }
        return null;
	}
}