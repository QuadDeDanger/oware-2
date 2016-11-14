package tests;
/**
 * This class is used to run test tests.
 * @author Aqib Rashid
 */

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import model.*;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(BasicComputerPlayerTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		
		
		result = JUnitCore.runClasses(BoardTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		
		result = JUnitCore.runClasses(HouseTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		
		result = JUnitCore.runClasses(PlayerTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		
		result = JUnitCore.runClasses(ScoreHouseTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		
		result = JUnitCore.runClasses(SeedTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
		

	}
}
