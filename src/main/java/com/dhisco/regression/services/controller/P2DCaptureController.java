package com.dhisco.regression.services.controller;

import com.dhisco.regression.core.util.CommonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import static com.dhisco.regression.core.BaseConstants.SLASH_FW;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 01-04-2019
 */
@Log4j2 @RestController @RequestMapping("/pctest") public class P2DCaptureController {

	@Autowired Environment env;

	@PostMapping(value = "/capture-rez-gain") public void captureRezGain(@RequestBody String rezGainInput) {

		log.info("Inside captureRezGain");
		String fileName = "out1.json";
		File filePath = new File(env.getProperty("out.path") + SLASH_FW + fileName);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(rezGainInput);
			log.info("File saved from controller");
			writer.flush();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (CommonUtils.isNotEmpty(writer)) {
					writer.close();
				}
			} catch (Exception e) {
				log.error("Error: writer could not be closed");
				log.error(e.getMessage(), e);
			}
		}

	}

	@GetMapping(value = "/testURL") public String captureRezGain() {
		return "Test URL output";
	}
}
