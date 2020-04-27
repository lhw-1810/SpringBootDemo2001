package com.hengxin.sbd.modules.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/testFileUploadController")
public class TestFileUploadController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	/**
	 * 单文件上传
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}

		// 获取原文件名
		String fileName = file.getOriginalFilename();
		String destFileName = "D:\\file\\" + fileName;

		File destFile = new File(destFileName);
		try {
			file.transferTo(destFile);
			redirectAttributes.addFlashAttribute("message", "upload success.");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			LOGGER.debug(e.getMessage());
			redirectAttributes.addFlashAttribute("message", "upload failed.");
		}

		return "redirect:/test/index";
	}

	/**
	 * 多文件上传
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping(value = "/uploadBatchFile", consumes = "multipart/form-data")
	public String uploadBatchFile(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes) {
		if (files != null) {
			for (MultipartFile multipartFile : files) {
				// 获取原文件名
				String fileName = multipartFile.getOriginalFilename();
				String destFileName = "D:\\file\\" + fileName;
				File destFile = new File(destFileName);
				try {
					multipartFile.transferTo(destFile);
					redirectAttributes.addFlashAttribute("message", "file upload success.");
					return "redirect:/test/index";
				} catch (IllegalStateException | IOException e) {
					LOGGER.debug(e.getMessage());
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("message", "upload failed.");
					return "redirect:/test/index";

				}
			}

		} else {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}
		return "redirect:/test/index";
	}

	@RequestMapping("/download")
	public ResponseEntity<Resource> downLoadFile(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(Paths.get("D:\\file\\" + fileName).toUri());

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, 
							String.format("attachment; filename=\"%s\"", resource.getFilename()))
					.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:\\file\\" + File.separator + fileName;
		File downloadFile = new File(filePath);

		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int) downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:\\file\\" + File.separator + fileName;
		File downloadFile = new File(filePath);

		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
						String.format("attachment; filename=\"%s\"", fileName));

				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}

}
