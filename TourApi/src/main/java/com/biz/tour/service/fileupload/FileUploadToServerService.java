package com.biz.tour.service.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadToServerService {
	// servlet-context.xml에 설정된 파일 저장 경로 정보를 가져와서 사용하기
		private final String filePath;
		
		/*
		 * 원래 파일이름을 UUID 부착된 파일이름으로 변경하고
		 * 변경된 이름으로 서버의 filePath에 저장하고
		 * 변경된 파일이름을 return
		 */
		public String filesUp(MultipartHttpServletRequest uploaded_files) {
			if(uploaded_files==null || uploaded_files.getFiles("uploaded_files").size()<=0) {
				return null;
			}
			//uploaded_files.getFiles("uploaded_files") 이부분은 jsp form input 에서 지정한 name과 동일해야함
			for(MultipartFile file:uploaded_files.getFiles("uploaded_files")) {
				fileUp(file);
			}
			return null;
		}
		
		public String fileUp(MultipartFile uploadedFile) {
			//파일이름을 추출(그림.jpg)
			String originalFileName=uploadedFile.getOriginalFilename();
			
			//UUID가 부착된 새로운 이름을 생성(UUID그림.jpg)
			String strUUID=UUID.randomUUID().toString();
			strUUID+=originalFileName;
			
			//filePath와 변경된 파일이름을 결합하여 empty 파일 객체를 생성
			File serverFile=new File(filePath,strUUID);
			
			//upload할 filePath가 있는지 확인을 하고
			//없으면 폴더를 생성
			File dir=new File(filePath);
			if(dir.exists()) {
				dir.mkdirs();
			}
			
			//upFile을 serverFile 이름으로 복사 수행 
			try {
				uploadedFile.transferTo(serverFile);
				return strUUID;
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
