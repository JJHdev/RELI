package business.sys.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import business.sys.log.service.DownLogService;
import business.sys.log.service.LogVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseService;

/**
 * [서비스클래스] - 다운로드이력 Service 구현 클래스
 *
 * @class   : DownLogServiceImpl
 * @author  : LSH
 * @since   : 2021.11.04
 * @version : 1.0
 *
 *   수정일     수정자             수정내용
 *  --------   --------    ---------------------------
 *
 */

@Service("DownLogService")
@SuppressWarnings({"all"})
public class DownLogServiceImpl extends BaseService implements DownLogService {

    @Resource(name = "DownLogDAO")
    private DownLogDAO downLogDAO;

    /**
     * 다운로드이력 페이징목록조회
     */
    @Override
    public PaginatedArrayList listDownLog(LogVO logVO, int currPage, int pageSize) throws Exception {
    	return downLogDAO.listDownLog(logVO, currPage, pageSize);
    }

    /**
     * 다운로드이력 목록조회
     */
    @Override
    public List listDownLog(LogVO logVO) throws Exception {
    	return downLogDAO.listDownLog(logVO);
    }

    /**
     * 다운로드이력 등록
     */
    public String regiDownLog(LogVO logVO) throws Exception {
		if (logVO == null)
			throw processException("error.comm.notTarget");
        // 저장결과를 반환한다.
		if (downLogDAO.regiDownLog(logVO) > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
    }

    /**
     * 2021.12.10 CSLEE
     * 다중으로 다운로드이력 등록
     * : atchFileSn을 배열로 받아 다중 정보 insert
     */
    public String regiDownLogs(LogVO logVO) throws Exception{
        if (logVO == null)
            throw processException("error.comm.notTarget");

        List<String> atchFileSnList = logVO.getAtchFileSns();
        int cnt = 0;
        if(atchFileSnList != null && atchFileSnList.size() > 0) {
            for(String sn : atchFileSnList) {
                logVO.setAtchFileSn(sn);
                cnt += downLogDAO.regiDownLog(logVO);
            }
            if(atchFileSnList.size() == cnt) {
                return message.getMessage("prompt.success");
            }
            else
                throw processException("error.comm.notProcess");
        }
        else throw processException("error.comm.notProcess");
    }

    /**
     * 다운로드이력 다중삭제
     */
	@Override
	public String deltDownLog(LogVO logVO) throws Exception {

		if (logVO == null)
			throw processException("error.comm.notTarget");

		int ret = 0;
		List<LogVO> rows = logVO.getLogList();

		if (rows == null || rows.size() == 0)
			throw processException("error.comm.notTarget");

		for (LogVO row : rows) {
			// 다운로드이력 삭제
			ret += downLogDAO.deltDownLog(row);
		}
        // 저장결과를 반환한다.
		if (ret > 0)
	        return message.getMessage("prompt.success");
		else
			throw processException("error.comm.notProcess");
	}
}