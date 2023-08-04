package vtiger.GenericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class provide the implementation to ITestListener interface of TestNG
 * @author Shreya
 *
 */
public class ListenerImplementationClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		System.out.println("---Execution started---"+methodName);
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println("---PASS---"+methodName);
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println("---FAIL---"+methodName);
		
		System.out.println(result.getThrowable());
		
		WebDriverUtility wU = new WebDriverUtility();
		JavaUtility jU = new JavaUtility();
		
		String screenshotName = methodName+jU.getSystemDateInFormat();
		
		/*Take screen shot for failed test script - to attach with bug raising*/
		try {
			wU.takeScreenShot(BaseClass.sdriver, screenshotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println("---SKIPPED---"+methodName);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("---Suite execution started---");
		
		//Configure the extent report
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("---Suite execution ended---");
	}
	
	

}
