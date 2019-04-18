package com.dhisco.regression.services.config.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 01-04-2019
 */
@Log4j2
@RestController @RequestMapping("/pctest") public class PushCoreTestController {

	@PostMapping(value = "/capture-rez-gain") public void captureRezGain(@RequestBody String rezGainInput) {

		log.info("inside captureRezGain");
		String fileName="out4.json";
		File filePath = new File("/apps/test/regression/out/"+fileName);
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(rezGainInput);
			log.info("File saved from controler");
			writer.flush();

			writer.close();
		}catch (Exception e){
			log.error(e.getMessage(),e);
		}

	}

	@GetMapping(value="/getOut") public String captureRezGain(){
			return "heyheyhey";
	}
}
