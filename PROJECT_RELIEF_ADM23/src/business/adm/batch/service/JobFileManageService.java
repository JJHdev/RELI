package business.adm.batch.service;

import java.io.File;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import business.com.file.service.AplyFileService;
import common.base.BaseService;
import common.file.FileDirectory;
import common.util.CommUtils;
import common.util.FileUtils;

/**
 * Program Name     : JobFileManageService
 * Description      : 파일을 처리하는 Cronjob 용 Service
 *                    context-scheduling.xml에 등록하여 사용
 * Programmer Name  : CSLEE
 * Creation Date    : 2022-01-10
 *
 * <사용 예>
 *
 * @author CSLEE
 *
 */
@Service
public class JobFileManageService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(JobFileManageService.class);

    // 신청파일정보
    @Resource(name = "AplyFileService")
    private AplyFileService aplyFileService;

    /**
     * 기간이 지난 복호화 디랙토리를 제거
     * 오늘날짜 이전 날짜(8자리문자열,yyyymmdd) 디렉토리 제거 Job
     *
     */
    public void clearDecryptFiles() {

        logger.debug("### [JOB Scheduler] Start clearDecryptFiles");

        // 2022.01.11 DRM 복호화 경로
        FileDirectory fd = FileDirectory.DECRYPT;
        // 복호화파일 저장 경로
        //String baseDirPath = fd.getRealPath();
        // 2022.01.11 LSH 임시경로로 변경
        String baseDirPath = fd.getTempDir();
        logger.debug("### [JOB Scheduler] #################### Real path : " + baseDirPath);

        // 현재 날짜의 8자리 문자열 (yyyyMMdd)
        String currDate = CommUtils.getCurDateString();

        File baseDirObj = new File(baseDirPath);
        if(baseDirObj.exists() && baseDirObj.isDirectory()) {

            File[] fileList = baseDirObj.listFiles();
            if(fileList != null && fileList.length > 0) {
                for(File file : fileList) {
                    // 이름이 8자리인 디렉토리중 현재 디렉토리명과 비교하여 예전 폴더는 제거
                    if( file.isDirectory()
                            && file.getName().length() == 8
                            && currDate.compareTo(file.getName()) > 0  ) {

                        String curFileNm = file.getName();
                        if(FileUtils.deleteDirectory(file.getPath())) {
                            logger.debug("### [JOB Scheduler] Success delete directory : " + curFileNm);
                        }
                    }
                }   // end of for - stat
            }
        }

        logger.debug("### [JOB Scheduler] End clearDecryptFiles");
    }

    /**
     * 2022.01.11 LSH
     * 등록후 24시간이 지난 임시저장 신청파일을 삭제한다.
     */
    public void clearTemporaryAplyFiles() {

        logger.debug("### [JOB Scheduler] Start clearTemporaryAplyFiles");
        
        // 등록후 24시간이 지난 임시저장파일 삭제
        try {
            // 등록후 하루(24시간)가 지난 파일 기준
            int diffDay = -1;
            // 임시저장 신청파일 삭제 (물리적파일 & DB 데이터)
			int ret = aplyFileService.deltAplyTempFiles(diffDay);

			logger.debug("### [JOB Scheduler] delete temporary aply files count : " + ret);
			
		} catch (Exception e) {
			logger.error("### [JOB Scheduler] clearTemporaryAplyFiles ERROR : ", e);
		}
        logger.debug("### [JOB Scheduler] End clearTemporaryAplyFiles");
    }
}
