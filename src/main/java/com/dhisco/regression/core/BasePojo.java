package com.dhisco.regression.core;

import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 28-03-2019
 */
public class BasePojo implements Serializable, Cloneable {

	@Override protected Object clone() {
		return SerializationUtils.clone(this);
	}
}
