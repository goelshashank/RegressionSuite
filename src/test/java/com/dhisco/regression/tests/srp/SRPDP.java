package com.dhisco.regression.tests.srp;

import com.dhisco.regression.dataproviders.BaseDP;


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
