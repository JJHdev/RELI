package business.usr.bbs.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import business.com.CommConst;
import business.com.bbs.service.BbsFileVO;
import business.com.bbs.service.BbsService;
import business.com.bbs.service.BbsVO;
import business.com.file.service.PapeMngService;
import business.com.file.service.PapeMngVO;
import business.sys.user.service.UserInfoVO;
import commf.paging.PaginatedArrayList;
import common.base.BaseController;
import common.file.FileDirectory;
import common.file.FileInfo;
import common.file.FileManager;
import common.util.CommUtils;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * [컨트롤러클래스] - 게시판 Controller - [게시판] 공지사항 - [게시판] 질의응답 - [자료실] 법ㆍ규정관리 - [자료실]
 * 신청서류양식
 * 
 * @class : BbsController
 * @author : 김주호
 * @since : 2021.10.02
 * @version : 1.0
 *
 * 수정일  	   수정자    수정내용
 * ------- 	   --------  ---------------------------
 * 2021.12.03   LSH      자료실-신청서류양식을 UsrPapeController로 분리
 */
@Controller
@SuppressWarnings({ "all" })
public class UsrBbsController extends BaseController {

	@Resource(name = "BbsService")
	protected BbsService bbsService;

	@Resource(name = "fileManager")
	protected FileManager fileManager;

	/**
	 * [게시판] 공지사항 화면
	 */
	@RequestMapping("/usr/bbs/listNotice.do")
	public String listNotice(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);

		// -------------------- Default Setting End -----------------------//
		bbsVO.setPage(CommUtils.nvlTrim(bbsVO.getPage(), "1"));
		// 게시판구분 정의 (공지사항)
		bbsVO.setBbsSeCd(CommConst.BBS_NOTICE);

		int page = CommUtils.strToInt(bbsVO.getPage(), 1);
		logger.info("bbsVONotice = {}", bbsVO);
		// 2021.10.29 게시판 공통 목록 검색으로 통일
		List list = bbsService.listBbs(bbsVO, page, basePageSize);

		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo()); // 순서
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		// 전체건수
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());

		return "usr/bbs/listNotice";
	}

	/**
	 * [게시판] 공지사항 상세보기
	 */
	@RequestMapping("/usr/bbs/viewNotice.do")
	public String viewNotice(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		Map paramMap = getParameterMap(request, true);

		// 2021.10.29 게시판 공통 상세 조회로 통일

		BbsVO data = bbsService.viewBbs(bbsVO);
		chkinqCnt(data.getNttNo(), request, response);

		// 리턴 (ModelMap)
		model.addAttribute("model", data);

		return "usr/bbs/viewNotice";
	}

	/**
	 * [게시판] 질의응답 화면
	 */
	@RequestMapping("/usr/bbs/listBbs.do")
	public String listBbs(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 게시판구분 정의 (질의응답)
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);

		return "usr/bbs/listBbs";
	}

	/**
	 * [게시판] 질의응답 작성자 권한 체크
	 * 
	 */
	@RequestMapping("/usr/bbs/checkQnaWriter.do")
	@ResponseBody
	public Map checkQnawriter(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {
		setGlobalSession(bbsVO);
		String userNo = bbsVO.getUserInfo().getUserNo();
		BbsVO chkWrite = bbsService.checkQnaWriter(bbsVO);

		if (!CommUtils.isEmpty(userNo) && !CommUtils.isEmpty(chkWrite.getRgtrNo())) {
			if (userNo.equals(chkWrite.getRgtrNo()) || CommConst.isAdminRole(bbsVO.getUserInfo().getRoleId())) {

				return getSuccess();

			} else {

			}

		}

		return getFailure();

	}

	/**
	 * [게시판] 질의응답 상세보기
	 * 
	 */
	@RequestMapping("/usr/bbs/viewQna.do")
	public String viewBbs(HttpServletRequest request, ModelMap model, @ModelAttribute UserInfoVO userInfoVO,
			@ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		String userNo = bbsVO.getUserInfo().getUserNo();

		// 사용자 정보와 작성자 정보 확인
		String getUserNo = userInfoVO.getUserInfo().getUserNo();

		Map paramMap = getParameterMap(request, true);
		// 게시판구분 정의 (질의응답)
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);

		// TODO. 세션사용자와 글의 작성자가 일치하는지 확인할것
		// 세션사용자번호는 다음과같이 가져온다.
		// 해당 nttNO에 해당하는 객체 가져오기
		BbsVO bbsQ = bbsService.viewBbs(bbsVO);
		BbsVO bbsA = new BbsVO();

		String msg = "";

		if (bbsQ != null) {
			if (userNo.equals(bbsQ.getRgtrNo()) || CommConst.isAdminRole(bbsVO.getUserInfo().getRoleId())) {
				// 작성자 이름 마스킹
				bbsQ.setRgtrNm(maskName(CommUtils.nvlTrim(bbsQ.getRgtrNm())));
				// 말머리 처리
				bbsQ.setNttSj("[" + bbsQ.getNttClCd() + "] " + bbsQ.getNttSj());

				model.addAttribute("model", bbsQ);
				model.addAttribute("backToList", "/usr/bbs/viewQna.do");

				model.addAttribute("model", bbsQ);

				if (bbsQ.getStatus() != null) {
					bbsA.setUpNttNo(bbsQ.getNttNo());
					bbsA = bbsService.viewBbsN(bbsA);
					model.addAttribute("answer", bbsA);
					return "usr/bbs/viewQna";

				}
			} else {
				msg = "로그인된 사용자와 작성자가 일치하지 않습니다.";
			}
		} else {
			msg = "등록된 정보가 없습니다.";
		}

		if (!msg.equals(""))
			throw new EgovBizException(message.getMessage(msg));

		return "usr/bbs/viewQna";
	}

	/**
	 * [게시판] 질의응답 등록페이지
	 */
	@RequestMapping("/usr/bbs/writeQna.do")
	public String writeQna(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 게시판구분 정의 (질의응답)
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);
		String userNo = bbsVO.getUserInfo().getUserNo();

		model.addAttribute("form", bbsVO);
		return "usr/bbs/writeQna";

	}

	/**
	 * [게시판] 질의응답 문의등록
	 * 
	 * @param request
	 * @param bbsVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/usr/bbs/saveQna.do")
	@ResponseBody
	public Map saveQna(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		bbsVO.setBbsSeCd(CommConst.BBS_QNA);
		bbsVO.setRgtrNo(bbsVO.getUserInfo().getUserNo());
		int result = bbsService.qnaSave(bbsVO);

		if (result > 0) {
			return getSuccess();
		}
		return getFailure();

	}

	/**
	 * [게시판] 질의응답 ajax 리스트 조회
	 */
	@RequestMapping("/usr/bbs/listBbsJson.do")
	@ResponseBody
	public Map listBbsJson(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {

		// 페이징정보를 가져오기 위한 조건맵
		Map paramMap = getParameterMap(request, true);
		// 세션사용자정보를 모델객체에 담는다
		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//

		// 화면에서 넘어온 페이지번호 (없으면 디폴트 1)
		int page = CommUtils.getInt(paramMap.get("page"), 1);
		// 화면에서 넘어온 화면출력수 (없으면 디폴트 10)
		int size = CommUtils.getInt(paramMap.get("rows"), basePageSize);
		// 페이징으로 검색한다.
		List<BbsVO> list = bbsService.listBbs(bbsVO, page, size);

		list = bbsListComfirm(list);

		// 페이징정보가 담긴 데이터맵을 반환한다.
		// (BaseController에 정의되어 있음)
		return getPaginatedResult(list);
	}

	/**
	 * [게시판] 2021.12.09 질의응답 말머리
	 */
	public List getprolog(List<BbsVO> list) {

		for (int i = 0; i < list.size(); i++) {
			// 말머리 처리
			if (list.get(i).getNttClCd() != null && !"".equals(list.get(i).getNttClCd())) {
				String totNttSj = "[" + list.get(i).getNttClCd() + "] " + list.get(i).getNttSj();
				list.get(i).setNttSj(totNttSj);
			}

		}
		return list;
	}

	public List bbsListComfirm(List<BbsVO> list) {

		for (int i = 0; i < list.size(); i++) {
			// 마스킹처리
			String maskName = maskName(list.get(i).getRgtrNm());
			list.get(i).setRgtrNm(maskName);

			// 말머리 처리
			if (list.get(i).getNttClCd() != null && !"".equals(list.get(i).getNttClCd())) {
				String totNttSj = "[" + list.get(i).getNttClCd() + "] " + list.get(i).getNttSj();
				list.get(i).setNttSj(totNttSj);
			}

		}
		return list;
	}

	/**
	 * [게시판] 2021.12.08 질의응답 사용자 이름 마스킹 처리
	 */
	public String maskName(String name) {

		if (name != null && !"".equals(name)) {

			String middleMask = "";

			if (name.length() > 2) {
				middleMask = name.substring(1, name.length() - 1);
			} else {
				middleMask = name.substring(1, name.length());
			}

			String masking = "";

			for (int i = 0; i < middleMask.length(); i++) {
				masking += "*";
			}

			if (name.length() > 2) {

				name = name.replace(middleMask, masking);
			} else {
				name = name.replace(middleMask, masking);
			}

		}

		return name;
	}

	/**
	 * 2021.10.29 LSH [게시판] 게시글 비밀번호 확인
	 */
	@RequestMapping("/usr/bbs/checkPassword.do")
	@ResponseBody
	public Map checkPassword(HttpServletRequest request, @ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 비밀번호 확인

		boolean result = bbsService.checkBbsPassword(bbsVO);
		// 결과 반환
		return getSuccess(result);
	}

	/**
	 * [자료실] 법규정목록 화면
	 */
	@RequestMapping("/usr/bbs/listStatute.do")
	public String listStatute(HttpServletRequest request, ModelMap model, @ModelAttribute BbsVO bbsVO)
			throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		// 게시판구분 정의 (법규정)
		bbsVO.setBbsSeCd(CommConst.BBS_STATUTE);

		bbsVO.setPage(CommUtils.nvlTrim(bbsVO.getPage(), "1"));
		bbsVO.setUserId(CommUtils.nvlTrim(bbsVO.getUserId(), "test"));
		int page = CommUtils.strToInt(bbsVO.getPage(), 1);

		// 2021.10.29 게시판 공통 목록 검색으로 통일
		List list = bbsService.listBbs(bbsVO, page, basePageSize);

		model.addAttribute("model", bbsVO);
		model.addAttribute("rows", list);
		model.addAttribute("startNo", ((PaginatedArrayList) list).getStartNo());
		model.addAttribute("startRevNo", ((PaginatedArrayList) list).getStartRevNo());
		model.addAttribute("totalSize", ((PaginatedArrayList) list).getTotalSize());

		return "usr/bbs/listStatute";
	}

	/**
	 * [자료실] 법규정목록 상세화면
	 */
	@RequestMapping("/usr/bbs/viewStatute.do")
	public String viewStatute(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@ModelAttribute BbsVO bbsVO) throws Exception {

		setGlobalSession(bbsVO);
		// -------------------- Default Setting End -----------------------//
		Map paramMap = getParameterMap(request, true);

		// 2021.10.29 게시판 공통 상세 조회로 통일
		BbsVO data = bbsService.viewBbs(bbsVO);
		// 조회수
		chkinqCnt(data.getNttNo(), request, response);
		// 리턴 (ModelMap)
		model.addAttribute("model", data);

		return "usr/bbs/viewStatute";
	}

	/**
	 * 게시글 첨부파일 목록조회 JSON 반환
	 */
	@RequestMapping("/usr/bbs/listBbsFile.do")
	@ResponseBody
	public Map listBbsFile(HttpServletRequest request, @RequestParam Map<String, String> reqMap,
			@ModelAttribute BbsFileVO bbsFileVO, ModelMap model) throws Exception {

		// -------------------- Default Setting End -----------------------//
		Map paramMap = getParameterMap(request, true);
		// 게시글의 첨부파일 목록조회
		List list = bbsService.listBbsFile(bbsFileVO);

		return getSuccess("rows", list);
	}

	/**
	 * 2021.07.22 첨부파일 다운로드 임시생성함 추후엔 공통영역으로 변경되어야할 항목임
	 */
	@RequestMapping("/usr/bbs/downBbsFile.do")
	public void downBbsFile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute BbsFileVO bbsFileVO) throws Exception {
		// -------------------- Default Setting Start ---------------------//
		setGlobalSession(bbsFileVO);
		// -------------------- Default Setting End -----------------------//

		// 다운로드할 파일 정보 조회
		BbsFileVO data = bbsService.viewBbsFile(bbsFileVO);

		// 파일문서타입
		FileDirectory fdir = FileDirectory.BOARD;

		// 파일 다운로드 공통함수 사용
		FileInfo fileInfo = FileInfo.builder().fullPath(fdir.getRealPath(data.getFilePath()))
				.filePath(data.getFilePath()).saveName(data.getSaveName()).fileName(data.getFileName()).build();

		// 실제 파일 다운로드 처리
		fileManager.procFileDownload(request, response, fileInfo);
	}

	/**
	 * 2021.11.13 게시판 조회수 중복 방지
	 * 
	 * @throws Exception
	 */
	public void chkinqCnt(int nttNo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("postView")) {
					oldCookie = cookie;
				}
			}
		}

		if (oldCookie != null) {
			if (!oldCookie.getValue().contains("[" + nttNo + "]")) {
				bbsService.inqCntUp(nttNo);
				oldCookie.setValue(oldCookie.getValue() + "_[" + nttNo + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(oldCookie);
			}
		} else {
			bbsService.inqCntUp(nttNo);
			Cookie newCookie = new Cookie("postView", "[" + nttNo + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(newCookie);
		}
	}
	  
}
