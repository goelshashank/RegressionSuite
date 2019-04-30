package com.dhisco.regression.tests.srp;

import com.dhisco.regression.dataproviders.BaseDP;
import com.dhisco.regression.dataproviders.BaseInput;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.DataProvider;

import java.util.Map;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 30-04-2019
 */
public class SRPDP extends BaseDP {

	public Object[][] rowNumber() {
		SRPInput srpInput=new SRPInput();
		srpInput.setData(2);

		SRPInput srpInput2=new SRPInput();
		srpInput2.setData(3);

		return new Object[][] { new Object[]{srpInput}, {srpInput2} };
	}
}
