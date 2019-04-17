package com.dhisco.tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 17-04-2019
 */
@Log4j2
public class Tess2 extends BaseTest{

	@Test
	public void test1() throws Exception{
		Assert.assertEquals(1,1);
	}

	@Test
	public void test2() throws Exception{
		Assert.assertEquals(1,2);
	}

}
