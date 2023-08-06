package com.simple.basic.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.simple.basic.command.UploadVO;

@Controller
@RequestMapping("/upload")
public class UploadController {

	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	//폴더생성함수
	public String makeFolder() {
		
		String path = LocalDate.now().format( DateTimeFormatter.ofPattern("yyyyMMdd")  );
		
		File file = new File(uploadPath + "/" + path);
		
		if(file.exists() == false ) { //존재하면 true, 존재하지 않으면 false
			file.mkdirs();
		}
		
		return path; //날짜폴더명 반환
	}
	
	
	
	
	@GetMapping("/upload")
	public String upload() {
		return "upload/upload";
	}
	
	//파일데이터는 MultipartFile객체로 받습니다.
	@PostMapping("/upload_ok")
	public String upload_ok( @RequestParam("file") MultipartFile file ) {
		
		//파일이름을 받습니다.
		String originName = file.getOriginalFilename();
		//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
		String filename = originName.substring(  originName.lastIndexOf("\\") + 1   );
		//파일사이즈
		long size = file.getSize();
		//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올림
		String uuid = UUID.randomUUID().toString();
		//날짜별로 폴더생성
		String filepath = makeFolder();
		//세이브할 경로
		String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
		
		//System.out.println(originName);
		//System.out.println(size);
		//데이터베이스 추후에 저장
		System.out.println("실제파일명:" + filename);
		System.out.println("난수값:" + uuid);
		System.out.println("날짜폴더경로:" + filepath);
		System.out.println("세이브할 경로:" + savepath);
		
		try {
			File saveFile = new File(savepath);
			file.transferTo(saveFile); //파일업로드를 진행
			
		} catch (Exception e) {
			System.out.println("파일업로드중 error발생");
			e.printStackTrace();
		}
		
		
		
		return "upload/upload_ok"; 
	}
	
	//복수태그를 사용한 다중파일 업로드 - List(MultipartFile)
	@PostMapping("/upload_ok2")
	public String upload_ok2(@RequestParam("file") List<MultipartFile> list) {
		
		//빈 file객체는 제외, 새로운리스트 생성
		list = list.stream().filter( f -> f.isEmpty() == false ).collect( Collectors.toList() );
		
		for(MultipartFile file : list ) {
			
			//파일이름을 받습니다.
			String originName = file.getOriginalFilename();
			//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
			String filename = originName.substring(  originName.lastIndexOf("\\") + 1   );
			//파일사이즈
			long size = file.getSize();
			//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더생성
			String filepath = makeFolder();
			//세이브할 경로
			String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			
			//System.out.println(originName);
			//System.out.println(size);
			//데이터베이스 추후에 저장
			System.out.println("실제파일명:" + filename);
			System.out.println("난수값:" + uuid);
			System.out.println("날짜폴더경로:" + filepath);
			System.out.println("세이브할 경로:" + savepath);
			System.out.println("=======================================");
			
			try {
				File saveFile = new File(savepath);
				file.transferTo(saveFile); //파일업로드를 진행
			} catch (Exception e) {
				System.out.println("파일업로드중 error발생");
				e.printStackTrace();
			}
		
		} //end for
		
		
		return "upload/upload_ok";
	}
	
	
	//multiple 옵션을 사용한 다중파일 업로드
	@PostMapping("/upload_ok3")
	public String upload_ok3(MultipartHttpServletRequest files ) {
		
		List<MultipartFile> list = files.getFiles("file"); //클라이언트 input name값
		
		for(MultipartFile file : list ) {
			
			//파일이름을 받습니다.
			String originName = file.getOriginalFilename();
			//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
			String filename = originName.substring(  originName.lastIndexOf("\\") + 1   );
			//파일사이즈
			long size = file.getSize();
			//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올림
			String uuid = UUID.randomUUID().toString();
			//날짜별로 폴더생성
			String filepath = makeFolder();
			//세이브할 경로
			String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			
			//System.out.println(originName);
			//System.out.println(size);
			//데이터베이스 추후에 저장
			System.out.println("실제파일명:" + filename);
			System.out.println("난수값:" + uuid);
			System.out.println("날짜폴더경로:" + filepath);
			System.out.println("세이브할 경로:" + savepath);
			System.out.println("=======================================");
			
			try {
				File saveFile = new File(savepath);
				file.transferTo(saveFile); //파일업로드를 진행
			} catch (Exception e) {
				System.out.println("파일업로드중 error발생");
				e.printStackTrace();
			}
		
		} //end for
		
		
		return "upload/upload_ok";
	}
	
	
	//비동기방식으로 받기 -> 폼데이터(@requestBody는 필요x)
	@PostMapping("/upload_ok4")
	public @ResponseBody ResponseEntity<String> upload_ok4( UploadVO vo  ) {
		
		MultipartFile file = vo.getFile(); //getter
		
		//파일이름을 받습니다.
		String originName = file.getOriginalFilename();
		//브라우저 별로 파일의 경로가 다를 수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
		String filename = originName.substring(  originName.lastIndexOf("\\") + 1   );
		//파일사이즈
		long size = file.getSize();
		//동일한 파일을 재업로드시 기존파일 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올림
		String uuid = UUID.randomUUID().toString();
		//날짜별로 폴더생성
		String filepath = makeFolder();
		//세이브할 경로
		String savepath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
		
		//System.out.println(originName);
		//System.out.println(size);
		//데이터베이스 추후에 저장
		System.out.println("실제파일명:" + filename);
		System.out.println("난수값:" + uuid);
		System.out.println("날짜폴더경로:" + filepath);
		System.out.println("세이브할 경로:" + savepath);
		
		try {
			File saveFile = new File(savepath);
			file.transferTo(saveFile); //파일업로드를 진행
			
		} catch (Exception e) {
			System.out.println("파일업로드중 error발생");
			e.printStackTrace();
		}
				
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//이미지 띄워주기
	@GetMapping("/display")
	public @ResponseBody byte[] display(@RequestParam("filename") String filename,
										@RequestParam("filepath") String filepath,
										@RequestParam("uuid") String uuid) {
		
		//읽어올 경로
		String path = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
		System.out.println(path);
		byte[] data = null;
		
		try {
			data = FileCopyUtils.copyToByteArray(new File(path)); //이미지 경로를 바이트배열로 구함
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	
	
	
	
	
	
	
	
}
