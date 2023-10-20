package common.file;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.fasoo.fcwpkg.packager.WorkPackager;

import common.util.CommUtils;
import common.util.properties.ApplicationProperty;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * Program Name     : FileDRMHelper
 * Description      : File DRM 암호화/복호화 처리
 * Programmer Name  : CSLEE
 * Creation Date    : 2022-01-07
 *
 * <사용 예>
 *
 *      // 사용 예
 *      FileDRMHelper fileDRMHelper = new FileDRMHelper();
 *      fileDRMHelper.doPackaging(
 *              "D:/APP_PROJECT/PROJECT/PROJECT_SUNDO21/UPLOAD_FILE/upload/",
 *              "D:/APP_PROJECT/PROJECT/PROJECT_SUNDO21/UPLOAD_FILE/upload/",
 *              "DRMTEST-12UIY3T13R.zip",
 *              true);
 *
 *      fileDRMHelper.doExtract(
 *              "D:/APP_PROJECT/PROJECT/PROJECT_SUNDO21/UPLOAD_FILE/upload/",
 *              "D:/APP_PROJECT/PROJECT/PROJECT_SUNDO21/UPLOAD_FILE/upload/",
 *              "DRMTEST-12UIY3T13R.zip");
 *
 * @author CSLEE
 *
 */
public class FileDRMHelper {

    private static final Logger logger = LoggerFactory.getLogger(FileDRMHelper.class);

    private static final String DEF_SYSTEMCODE   = "SYSTEM";
    private static final String DEF_WRITER_ID    = "admin";
    private static final String DEF_WRITER_NAME  = "관리자";

    private WorkPackager oWorkPackager;

    private int    errorNum;
    private String errorStr;

    // fsdinit 디렉토리 path
    private String drmFsdinitDirPath;
    // domain id
    private String drmDoaminId      ;

    public FileDRMHelper() {

        try {
            File dir = ResourceUtils.getFile(ApplicationProperty.get("DRM.FSDINIT.PATH"));
            // fsdinit 디렉토리 path
            drmFsdinitDirPath     = dir.getAbsolutePath();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        // domain id
        drmDoaminId = ApplicationProperty.get("DRM.DOMAIN.ID");

        oWorkPackager = new WorkPackager();
    }

    //=========================================================
    // 암호화
    //=========================================================

    /**
     * 파일 암호화 처리
     * - 작성자 정보는 default로 처리
     *
     * @param srcFileFullPath   암호화하려는 대상 파일의 fullPath (파일명 제외)
     * @param desFileFullPath   암호화결과 파일의 fullPath (파일명 제외)
     * @param fileName          파일명
     * @param isOverWirte       파일덮어쓰기 여부
     * @throws Exception
     */
    public void doPackaging(String srcFileFullPath
                          , String desFileFullPath
                          , String fileName
                          , boolean isOverWirte) throws Exception {

        doPackaging(srcFileFullPath, desFileFullPath, fileName, isOverWirte, DEF_WRITER_ID, DEF_WRITER_NAME);
    }

    /**
     * 파일 암호화 처리
     *
     * @param srcFileFullPath   암호화하려는 대상 파일의 fullPath (파일명 제외)
     * @param desFileFullPath   암호화결과 파일의 fullPath (파일명 제외)
     * @param fileName          파일명
     * @param isOverWirte       파일덮어쓰기 여부
     * @param writerId          작성자 ID
     * @param writeName         작성자 명
     * @throws Exception
     */
    public void doPackaging(String srcFileFullPath
                          , String desFileFullPath
                          , String fileName
                          , boolean isOverWirte
                          , String writerId, String writeName) throws Exception {

        // 파일 타입
        int fileType = 0;
        // 지원확장자 여부
        boolean isSupportExt = true;
        // 암호화 결과
        boolean result       = true;

        try {
            String convSrcFilePath = srcFileFullPath+fileName;
            String convDesFilePath = desFileFullPath+fileName;

            // 파일 확장자 체크 (암호화 파일인지 여부)
            fileType     = oWorkPackager.GetFileType(  convSrcFilePath );

            // 암호화 되지 않은 일반 파일 경우
            if (fileType == 29) {

                // 지원 확장자 체크
                isSupportExt = oWorkPackager.IsSupportFile( this.drmFsdinitDirPath,
                                                            this.drmDoaminId,
                                                            convSrcFilePath  );  //지원 확장자 구분
                // 지원하는 확장자일 경우
                if(isSupportExt) {
                    // 결과 덮어쓰기 여부
                    oWorkPackager.setOverWriteFlag(isOverWirte);

                    result = oWorkPackager.DoPackaging(
                                this.drmFsdinitDirPath,   //fsdinit 폴더 FullPath 설정
                                this.drmDoaminId,         //고객사 Key(default)
                                convSrcFilePath,          //암호화 대상 문서 FullPath + FileName
                                convDesFilePath,          //암호화 된 문서 FullPath + FileName
                                fileName,                 //파일 명
                                writerId,                 //작성자 ID
                                writeName,                //작성자 명
                                DEF_SYSTEMCODE,           //시스템 ID
                                DEF_SYSTEMCODE            //ACL ID (권한 확인을 위한 ID로 SystemID와 동일 적용)
                    );
                	errorNum = oWorkPackager.getLastErrorNum();
                	errorStr = oWorkPackager.getLastErrorStr();

                    if(result) {
                        logger.debug("DRM 암호화 성공 (File Name :" + fileName + ")");
                    }
                    else {
                        throw new EgovBizException("DRM 암호화 실패하였습니다.");
                    }
                }
                else {
                    logger.error("# DRM Packaging - Error Message : DRM 암호화에서 지원하지 않는 확장자 파일입니다.");
                    logger.error("# DRM Extract - Error NUM : " + oWorkPackager.getLastErrorNum());
                    logger.error("# DRM Extract - Error Message : " + oWorkPackager.getLastErrorStr());
                    throw new EgovBizException("DRM 암호화에서 지원하지 않는 확장자 파일입니다.");
                }
            }
            else {
                logger.error("# DRM Packaging - Error Message : DRM 암호화할 수 없는 파일입니다.");
                logger.error("# DRM Extract - Error NUM : " + oWorkPackager.getLastErrorNum());
                logger.error("# DRM Extract - Error Message : " + oWorkPackager.getLastErrorStr());
                throw new EgovBizException("DRM 암호화할 수 없는 파일입니다.");
            }

        }catch( Exception e ){
            throw new EgovBizException("DRM 암호화중 오류가 발생했습니다.", e);
        }
    }

    //=========================================================
    // 복호화
    //=========================================================

    /**
     * 파일 복호화 처리 (파일명은 동일하게 하고 path 변경 가능)
     *
     * @param srcFileFullPath   복호화하려는 대상 파일의 fullPath (파일명 제외)
     * @param desFileFullPath   복호화결과 파일의 fullPath (파일명 제외)
     * @param fileName          파일명
     *
     * @throws Exception
     */
    public void doExtract(String drmFileFullPath
                        , String desFileFullPath
                        , String fileName) throws Exception {

        String convDrmFilePath = drmFileFullPath+fileName;
        String convDesFilePath = desFileFullPath+fileName;

        doExtract(convDrmFilePath, convDesFilePath);
    }

    /**
     * 파일 복호화 처리
     * @param drmFileFullPathName 복호화하려는 대상 파일의 fullPath + fileName
     * @param desFileFullPathName 복호화하려는 대상 파일의 fullPath + fileName
     * @throws Exception
     */
    public void doExtract(String drmFileFullPathName
                        , String desFileFullPathName
            ) throws Exception {

        // 파일 타입
        int fileType = 0;
        // 암호화 결과
        boolean result       = true;

        if(CommUtils.isEmpty(drmFileFullPathName) || CommUtils.isEmpty(desFileFullPathName)) {
            throw new EgovBizException("요청정보 오류로 인해 DRM 복호화 작업이 실패하였습니다.");
        }

        try {
            // 파일 확장자 체크 (암호화 파일인지 여부)
            fileType     = oWorkPackager.GetFileType(  drmFileFullPathName );

            // DRM(FSD) 문서일 경우 복호화 수행
            if (fileType == 26) {

                result = oWorkPackager.DoExtract(
                                this.drmFsdinitDirPath,     //key 폴더 경로
                                this.drmDoaminId,           //dsdcode
                                drmFileFullPathName,        //복호화 대상 파일 경로
                                desFileFullPathName         //복호화 파일 저장 경로
                         );
                errorNum = oWorkPackager.getLastErrorNum();
                errorStr = oWorkPackager.getLastErrorStr();

                if(errorNum == 0 ) {
                    logger.debug("DRM 복호화 성공 (File Name :" + drmFileFullPathName + ")");
                }else {
                    logger.error("# DRM Extract - Error NUM : " + errorNum);
                    logger.error("# DRM Extract - Error Message : " + errorStr);
                    throw new EgovBizException("DRM 복호화 실패하였습니다.");
                }
            }
            else {
                logger.error("# DRM Extract - Error Message : DRM 복호화를 위한 암호화된 파일이 아닙니다.");
                logger.error("# DRM Extract - Error NUM : " + oWorkPackager.getLastErrorNum());
                logger.error("# DRM Extract - Error Message : " + oWorkPackager.getLastErrorStr());
                throw new EgovBizException("DRM 복호화를 위한 암호화된 파일이 아닙니다.");
            }

        }catch( Exception e ){
            throw new EgovBizException("DRM 복호화중 오류가 발생했습니다.", e);
        }
    }

	public int getErrorNum() {
		return errorNum;
	}

	public String getErrorStr() {
		return errorStr;
	}
}
