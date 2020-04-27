package com.hengxin.sbd.modules.test.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hengxin.sbd.modules.test.vo.ConfigBean;

@RestController
@RequestMapping("/api/test")
public class TestController {
	private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Value("${server.port}")
	private int port;
	@Value("${com.hengxin.name}")
	private String name;
	@Value("${com.hengxin.age}")
	private int age;
	@Value("${com.hengxin.desc}")
	private String desc;
	@Value("${com.hengxin.random}")
	private String random;

	@Autowired
	private ConfigBean configBean;

	/**
	 * https://127.0.0.1:443/api/test/bootTest
	 */
	@RequestMapping("/bootTest")
	@ResponseBody
	public String testBoot() {
		return "SpringBoot Farst Day ！";
	}

	/**
	 * https://127.0.0.1:443/api/test/log
	 */
	@RequestMapping("/log")
	public String logTest() {
		// TRACE<DEBUG<INFO<WARN<ERROR
		LOGGER.trace("This is TRACE log.");
		LOGGER.debug("This is DEBUG log.");
		LOGGER.info("This is INFO log.");
		LOGGER.warn("This is WARN log.");
		LOGGER.error("This is ERROR log");
		return "日志测试";
	}

	/**
	 * https://127.0.0.1:443/api/test/config
	 */
	@RequestMapping("/config")
	public String configTest() {
		StringBuffer sb = new StringBuffer();
		sb.append("端口号：" + port + "<br/>");
		sb.append("用户名：" + name + "<br/>");
		sb.append("年龄：" + age + "<br/>");
		sb.append("描述：" + desc + "<br/>");
		sb.append("随机数：" + random + "<br/>");
		sb.append("</br>");
		sb.append("局部文件-用户名：" + configBean.getName() + "<br/>");
		sb.append("局部文件-年龄：" + configBean.getAge() + "<br/>");
		sb.append("局部文件-描述：" + configBean.getDesc() + "<br/>");
		sb.append("局部文件-随机数：" + configBean.getRandom());
		return sb.toString();
	}

	/**
	 * https://127.0.0.1:443/api/test/appDesc
	 */
	@RequestMapping("/appDesc")
	public String getAppDesc() {
		return "SpringBoot Second Day ！";
	}

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}

		String fileName = file.getOriginalFilename();

		File destFile = new File(String.format("D:\\file\\", fileName));
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

}
