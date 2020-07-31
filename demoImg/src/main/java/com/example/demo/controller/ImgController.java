package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ImgDao;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
public class ImgController {
	private static final Logger logger = LoggerFactory.getLogger(ImgController.class);
	@Autowired
	private ImgDao dao;

	/**
	 * 파일태그를 위한 폼태그
	 * 
	 * @return
	 */
	@RequestMapping(value = "/formFile")
	public String formFile() {
		logger.debug("formFile - 호출");
		return "formFile";
	}

	/**
	 * 파일처리 컨트롤러
	 * 
	 * @param vo
	 * @return
	 */
//	@RequestMapping(value = "/saveImage")
//	public String saveImage(Img img) {
//		try {
//			Map<String, Object> hmap = new HashMap<String, Object>();
//			hmap.put("img", img.getImgFile().getBytes());
//			dao.saveImage(hmap);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "redirect:/formFile";
//	}
	
	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public void singleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		try {
			Map<String, Object> hmap = new HashMap<String, Object>();
			hmap.put("img", file.getBytes());
			System.out.println(hmap.toString());
			dao.saveImage(hmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getByteImage")
	public ResponseEntity<String> getByteImage() {
		// vue용
		Map<String,Object> element = dao.getByteImage();
		byte[] encoded=org.apache.commons.codec.binary.Base64.encodeBase64((byte[])element.get("img"));
		String encodedString = new String(encoded);
		
		// jsp용
//		Map<String, Object> map = dao.getByteImage();
//		byte[] imageContent = (byte[]) map.get("img");
//		final HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_PNG);
//		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);

		return new ResponseEntity<String>(encodedString, HttpStatus.OK);
	}

}
