package com.dhisco.regression.core.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
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

	public static <T> T getObjFromResourceJson(String fileName, Class<T> tClass) throws IOException {
		String result = getStringFromFileResource(fileName);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonnode = mapper.readTree(result);
		return mapper.treeToValue(jsonnode, tClass);
	}

	public static String getStringFromFileResource(String fileName) throws IOException {
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

}

