package com.dhisco.regression.dataproviders;

import com.dhisco.regression.core.BasePojo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 24-04-2019
 */
@Getter @Setter
public class BaseInput extends BasePojo {

	private Boolean loadMariaDB;
	private String testName;

}
