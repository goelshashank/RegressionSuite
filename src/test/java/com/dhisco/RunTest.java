package com.dhisco;

import com.dhisco.tests.Tess;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 08-04-2019
 */
public class RunTest {

	public static void main(String[] args){
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		testng.setTestClasses(new Class[] { Tess.class });
		testng.addListener(tla);
		testng.run();
	}
}
