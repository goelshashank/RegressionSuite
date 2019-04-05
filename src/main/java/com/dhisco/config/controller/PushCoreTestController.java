package com.dhisco.config.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 01-04-2019
 */
@Log4j2
@RestController @RequestMapping("/pctest") public class PushCoreTestController {

	@PostMapping(value = "/capture-rez-gain") public void republishData(@RequestBody String rezGainInput) {

		String fileName="out1";
		File filePath = new File("C:\\Users\\shashank.goel\\IdeaProjects\\P2DRegressionSuite\\src\\test"
				+ "\\resources\\out\\"+fileName);
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(rezGainInput);

			writer.close();
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}

	}
}
