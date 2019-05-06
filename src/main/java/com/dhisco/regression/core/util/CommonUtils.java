package com.dhisco.regression.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */

@Log4j2 public class CommonUtils {

	public static Boolean isEmpty(Object obj) {
		return obj == null;
	}

	public static Boolean isNotEmpty(Object obj) {
		return obj != null;
	}

	public static Boolean isEmpty(String obj) {
		return StringUtils.isEmpty(obj);
	}

	public static Boolean isNotEmpty(String obj) {
		return StringUtils.isNotEmpty(obj);
	}

	public static Boolean isEmpty(Collection obj) {
		return CollectionUtils.isEmpty(obj);
	}

	public static Boolean isNotEmpty(Collection obj) {
		return CollectionUtils.isNotEmpty(obj);
	}

	public static Boolean isEmpty(Map obj) {
		return MapUtils.isEmpty(obj);
	}

	public static Boolean isNotEmpty(Map obj) {
		return MapUtils.isNotEmpty(obj);
	}

	public static <T> T getObjFromResourceJsonAbsPath(String absPath, Class<T> tClass) throws IOException {
		String result = getResourceAsStrFromAbsPath(absPath);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonnode = mapper.readTree(result);
		return mapper.treeToValue(jsonnode, tClass);
	}

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

	public static int getSizeInBytes(Serializable someObject) throws Exception {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
		objectOut.writeObject(someObject);
		objectOut.flush();
		return byteOut.toByteArray().length;
	}

	public static int getSizeInKB(Serializable someObject) throws Exception {
		return getSizeInBytes(someObject) / 1000;
	}

	public static String getResourceAsStrFromAbsPath(String absPath) throws IOException {
		return IOUtils.toString(getResourceStreamFromAbsPath(absPath), "UTF-8");
	}

	public static InputStream getResourceStreamFromAbsPath(String absPath) throws IOException {
		return new FileInputStream(absPath);
	}

	public static String getRestCall(String uri) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, String.class);
	}

	public static String postRestCall(String uri, String requestJson) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity(requestJson, headers);
		String response = restTemplate.postForObject(uri, entity, String.class);
		return response;
	}

	public static List<File> getAllFiles(String pathName) {
		List<File> out = new ArrayList();
		File folder2 = new File(pathName);
		File[] files = folder2.listFiles();

		for (File file : files) {
			List<File> dirFiles = new ArrayList<>();
			if (file.isDirectory()) {
				dirFiles = getAllFiles(file.getAbsolutePath());
				if (isNotEmpty(dirFiles))
					out.addAll(dirFiles);
			} else
				out.add(file);
		}

		return out;
	}

}

