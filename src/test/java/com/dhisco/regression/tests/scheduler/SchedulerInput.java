package com.dhisco.regression.tests.scheduler;

import java.io.InputStream;
import java.util.List;
import com.dhisco.regression.dataproviders.BaseInput;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Abhishek.Chauhan
 * @version 1.0
 * @since 19-07-2019
 */

@Getter @Setter
public class SchedulerInput extends BaseInput {

	private List<InputStream> dataFiles;
	private String scriptFile;
	private List<String> inputTopic;
	private List<String> outputTopic;

}
