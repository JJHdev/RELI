package common.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Program Name     : FileInfo
 * Description      :
 * Programmer Name  : ntarget
 * Creation Date    : 2021-02-08
 * Used Table       :
 *
 * 2021.08.04 LSH 파일테이블 수정
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private String fileNo; // 파일번호
    private String fileName; // 파일명칭
    private String saveName; // 파일저장명칭
    private String filePath; // 파일경로
    private String gsUserNo; // 등록자번호

    private String fileExt;  // 파일확장자
    private long   fileSize; // 파일크기
    private int    fileIdx;  // 파일표시순서 (2021.07.21 LSH)

    private String fileYn;   // 파일첨부여부 (2021.07.21 LSH)
    private String needYn;   // 파일필수여부 (2021.07.21 LSH)
    private String fileType; // REFERENCE TYPE (2021.07.21 LSH)
    private String contType; // 파일컨텐츠타입

    private String docuCd;   // REFERENCE KEY 값 (2021.10.08 LSH)
    private String docuNo;   // REFERENCE KEY 값 (2021.07.21 LSH)
    private String docuSeq;  // REFERENCE KEY 값 (2021.10.08 LSH)

    private String fullPath;
    private String fullFile;

    private boolean decrypt; // 암호화대상여부
    private boolean deleted; // 삭제대상여부
}