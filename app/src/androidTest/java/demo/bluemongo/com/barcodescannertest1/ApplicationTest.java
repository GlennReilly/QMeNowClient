package demo.bluemongo.com.barcodescannertest1;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }



    public void testFirst() {
        Boolean expected = true, actual = true;

        assertEquals(expected, actual);
    }

    public void testCameraPreview(){
        Application app = getApplication();

        //CameraPreviewView view = new CameraPreviewView();
        //CameraPreviewPresenter presenter = new CameraPreviewPresenter(view);
        //presenter.showCameraPreview();
    }
}