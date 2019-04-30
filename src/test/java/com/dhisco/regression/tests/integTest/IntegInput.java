package com.dhisco.regression.tests.integTest;

import com.dhisco.regression.dataproviders.BaseInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 30-04-2019
 */
@Getter @Setter
public class IntegInput extends BaseInput {

	private List<String> dataFiles;
	private String scriptFile;

}
