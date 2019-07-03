package com.dhisco.regression.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */

@Log4j2 public class RegressionUtils extends com.dhisco.ptd.dj.util.CommonUtils{

	public static <T> T getObjFromResourceJsonRelPath(String fileName, Class<T> tClass) throws IOException {
		String result = getStringFromFileResourceRelPath(fileName);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonnode = mapper.readTree(result);
		return mapper.treeToValue(jsonnode, tClass);
	}

	public static String getStringFromFileResourceRelPath(String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource(fileName);
		InputStream inputStream = resource.getInputStream();
		Scanner s = (new Scanner(inputStream)).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}


	public List<File> getAllFilesFromRelativePath (String relPath) throws IOException {

		ClassPathResource resource = new ClassPathResource(relPath);
		File folder2 = resource.getFile();

		List<File> out = new ArrayList();
		File[] files = folder2.listFiles();

		for (File file : files) {
			List<File> dirFiles = new ArrayList<>();
			if (file.isDirectory()) {
				dirFiles = getAllFilesFromRelativePath(file.getAbsolutePath());
				if (RegressionUtils.isNotEmpty(dirFiles))
					out.addAll(dirFiles);
			} else
				out.add(file);
		}

		return out;
	}

}

