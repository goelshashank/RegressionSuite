package com.dhisco.regression.services.controller;

import com.dhisco.regression.core.util.RegressionUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
@Log4j2 @RestController @RequestMapping("/p2d-test") public class P2DCaptureController {

	@Autowired Environment env;

	@PostMapping(value = "/capture-rez-gain-input") public void captureRezGainInput(@RequestBody String rezGainInput) {

		log.info(" .......   Capturing Rez Gain Input  .........");

		JsonElement root = new JsonParser().parse(rezGainInput);
		String fileName = root.getAsJsonObject().get("SessionID").getAsString()+".json";
		File filePath = new File(env.getProperty("test.out.path") + SLASH_FW + fileName);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(rezGainInput);
			log.info(".......   Captured Rez Gain Input......... rezGainInput: {}", rezGainInput);
			writer.flush();
		} catch (Exception e) {
			log.error(".......  Failed, to capture Rez Gain Input.........  rezGainInput: {}", rezGainInput);
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (RegressionUtils.isNotEmpty(writer)) {
					writer.close();
				}
			} catch (Exception e) {
				log.error("Error: writer could not be closed");
				log.error(e.getMessage(), e);
			}
		}

	}

	@GetMapping(value = "/testURL") public String testURL() {
		return "Test URL is working";
	}
}
