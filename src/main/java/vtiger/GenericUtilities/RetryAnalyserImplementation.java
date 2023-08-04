package vtiger.GenericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class provides implementation for IRetryAnalyser interface
 * @author Shreya
 *
 */
public class RetryAnalyserImplementation implements IRetryAnalyzer {

	int count=1;
	int retryCount=3;
	
	public boolean retry(ITestResult result) {
			//1<=3 2<=3 3<=3 4<=3
		while(count<=retryCount) {
			count++; //2 3 4
			return true; //retry retry retry
		}
		
		return false; //don't retry
	}

}
