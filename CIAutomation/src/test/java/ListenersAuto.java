import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public  class ListenersAuto implements ITestListener {

    public void onStart(ITestContext execution){
        System.out.println("------------------Starting Execution------------------");
        System.out.println();
    }

    public void onFinish(ITestContext execution){
        System.out.println("------------------Execution Ended------------------");
        System.out.println();
    }

    public void onTestStart(ITestResult test){
        System.out.println("------------------Starting Test: "+test.getName()+"------------------");
        System.out.println();
    }

    public void onTestSuccess(ITestResult test){
        System.out.println("------------------Test: "+test.getName()+" Passed------------------");
        System.out.println();
    }

    public void onTestFailure(ITestResult test){
//        saveScreenshot();
        System.out.println("------------------Test: "+test.getName()+" Failed------------------");
        System.out.println();

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult test) {
        System.out.println("???");
        System.out.println();
    }

    public void onTestSkipped(ITestResult test){
        System.out.println("------------------Skipping Test: "+test.getName()+"------------------");
        System.out.println();
    }

//    @Attachment(value = "Page Screen-Shot", type = "image/png")
//    public byte[] saveScreenshot() {
//        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//    }
}
