package business.com.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.com.CommConst;
import business.com.biz.service.SplemntService;
import business.com.biz.service.SplemntVO;
import business.com.file.service.AplyFileService;
import business.com.file.service.AplyFileVO;
import business.sys.sms.service.SmsService;
import business.sys.sms.service.SmsVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 보완요청 Service 구현 클래스
 * 
 * @class   : SplemntServiceImpl
 * @author  : LSH
 * @since   : 2021.10.24
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("SplemntService")
@SuppressWarnings({"all"})
public class SplemntServiceImpl extends BaseService implements SplemntService {

    @Resource(name = "SplemntDAO")
    private SplemntDAO splemntDAO;

    @Resource(name = "SmsService")
    private SmsService smsService;

    @Resource(name = "AplyFileService")
    private AplyFileService aplyFileService;
	
    /**
     * 보완요청 페이징목록조회
     */
    @Override
    public PaginatedArrayList listSplemnt(SplemntVO splemntVO, int currPage, int pageSize) throws Exception {
    	return splemntDAO.listSplemnt(splemntVO, currPage, pageSize);
    }

    /**
     * 보완요청 목록조회
     */
    @Override
    public List listSplemnt(SplemntVO splemntVO) throws Exception {
    	return splemntDAO.listSplemnt(splemntVO);
    }

    /**
     * 보완요청 상세조회
     */
	@Override
	public SplemntVO viewSplemnt(SplemntVO splemntVO) throws Exception {
		return splemntDAO.viewSplemnt(splemntVO);
	}

    /**
     * 보완요청 등록
     */
    private int regiSplemnt(SplemntVO splemntVO) throws Exception {
        return splemntDAO.regiSplemnt(splemntVO);
    }

    /**
     * 보완요청 수정
     */
    private int updtSplemnt(SplemntVO splemntVO) throws Exception {
        return splemntDAO.updtSplemnt(splemntVO);
    }

    /**
     * 보완요청 삭제
     */
    private int deltSplemnt(SplemntVO splemntVO) throws Exception {
        return splemntDAO.deltSplemnt(splemntVO);
    }
    /**
     * 2021.10.30
     * 보완요청 중인 최종건 상세조회
     */
    public SplemntVO viewSplemntLast(SplemntVO splemntVO) throws Exception {
        return splemntDAO.viewSplemntLast(splemntVO);
    }

    /**
     * 보완요청 등록,수정,삭제
     * 1) mode = 'I' : 보완요청처리 (관리자)
     * 2) mode = 'U' : 보완제출처리 (사용자)
     */
	@Override
	public String saveSplemnt(SplemntVO splemntVO) throws Exception {
		
		if (splemntVO == null)
			throw processException("error.comm.notTarget");
		
		int ret = 0;
		String mode = splemntVO.getMode();
		
		// 보완요청 저장처리
		if (CommConst.INSERT.equals(mode)) {

	        // 신청서류목록
	        List<AplyFileVO> fileList = splemntVO.getFileList();

			// SMS 업무메세지 전송
			SmsVO smsVO = SmsVO.builder()
					.rcvrNo    (splemntVO.getRcvrNo())
					.rcvrNm    (splemntVO.getRcvrNm())
					.trnsterNo (splemntVO.getTrnsterNo())
					.trnsterNm (CommConst.SMS_TRANSFER_NM   )
					.smsSeCd   (CommConst.SMS_RELIEF_SPLEMNT) // 보완요청
					.trsmStusCd(CommConst.SMS_RESULT_WAIT   ) // 발송대기
					.trsmCn    (splemntVO.getSplemntDmndCn())
					.build();
			
			// SMS 발송
			smsService.sendSms(smsVO);

			// 보완요청 등록 (보완요청처리)
			ret = regiSplemnt(splemntVO);
			
			if (ret > 0) {
				// 보완요청 파일 업데이트
				for (AplyFileVO aplyFileVO : fileList) {
					AplyFileVO file = AplyFileVO.builder()
							.sn         (aplyFileVO.getSn())
							.gsUserNo   (splemntVO.getGsUserNo())
							.prcsStusCd (CommConst.PRCS_SUPPLEMENT)
							.build();
					// 신청첨부파일 수정
					aplyFileService.updtAplyFile(file);
				}
			}
		}
		// 보완제출 저장처리
		else if (CommConst.UPDATE.equals(mode)) {
			
			// 보완요청중인 최종건 조회
			SplemntVO obj = SplemntVO.builder()
					.aplyNo    (splemntVO.getAplyNo())
					.aplyOder  (splemntVO.getAplyOder())
					.prcsStusCd(CommConst.SPLEMNT_APPLY)   // 보완요청
					.build     ();
			obj = viewSplemntLast(obj);
			if (obj == null)
				throw processException("error.comm.notTarget");
			
			// 대상건 KEY 정의
			splemntVO.setSn(obj.getSn());
			
			// 보완요청 수정 (보완완료처리)
			ret = updtSplemnt(splemntVO);
		}
		// 현재사용안함
		else if (CommConst.DELETE.equals(mode)) {
			// 보완요청 삭제
			ret = deltSplemnt(splemntVO);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}