package demo.bluemongo.com.barcodescannertest1.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.model.AppointmentWrapper;
import demo.bluemongo.com.barcodescannertest1.model.CustomerQRCodePayload;
import demo.bluemongo.com.barcodescannertest1.model.QMeNowModel;

//public class MainActivity extends AppCompatActivity implements
public class MainActivity extends Activity implements
        MainMenuFragment.OnFragmentInteractionListener,
        UserDetailsFragment.OnFragmentInteractionListener,
        CameraFragment.OnFragmentInteractionListener,
        MessageFragment.OnFragmentInteractionListener,
        GetAppointmentsFragment.OnFragmentInteractionListener,
        AppointmentDetailsFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener
{
    private static final String TAG = "MainActivity";
    private static final String TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT";
    private static final String TAG_CAMERA_FRAGMENT = "TAG_CAMERA_FRAGMENT";
    private static final String TAG_MESSAGE_FRAGMENT = "TAG_MESSAGE_FRAGMENT";
    private static final String TAG_USER_DETAILS_FRAGMENT = "TAG_USER_DETAILS_FRAGMENT";
    private static final String TAG_GET_APPOINTMENTS_FRAGMENT = "TAG_GET_APPOINTMENTS_FRAGMENT";
    private static final String TAG_GET_APPOINTMENTS_DETAILS_FRAGMENT = "TAG_GET_APPOINTMENTS_DETAILS_FRAGMENT";
    private static final String TAG_GET_SETTINGS_FRAGMENT = "TAG_GET_SETTINGS_FRAGMENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showMainMenu();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showMainMenu() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        fragmentTransaction.replace(R.id.fragment_container, mainMenuFragment, TAG_MAIN_FRAGMENT);
        fragmentTransaction.addToBackStack(TAG_MAIN_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    public void showUserDetails(Bundle bundle) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        if (bundle != null){
            userDetailsFragment.setArguments(bundle);
        }
        fragmentTransaction.replace(R.id.fragment_container, userDetailsFragment, TAG_USER_DETAILS_FRAGMENT);
        fragmentTransaction.addToBackStack(TAG_MAIN_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    public void populateCustomerDetailsFromBarcode(CustomerQRCodePayload customerQRCodePayload) {
        Bundle bundle = new Bundle();
        bundle.putString(QMeNowModel.FIRSTNAME, customerQRCodePayload.getCustomerFirstName());
        bundle.putString(QMeNowModel.LASTNAME, customerQRCodePayload.getCustomerLastName());
        bundle.putInt(QMeNowModel.CUSTOMERID, customerQRCodePayload.getCustomerId());
        showUserDetails(bundle);
    }


    @Override
    public void openCameraPreview(CameraPreviewView.BarcodeType barcodeType) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CameraFragment cameraFragment = new CameraFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CameraPreviewView.ScanningForBarcodeType, barcodeType.toString());
        cameraFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, cameraFragment, TAG_CAMERA_FRAGMENT);
        fragmentTransaction.addToBackStack(TAG_CAMERA_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    public void showInvalidBarcodeMessage() {
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        MessageFragment messageFragment = new MessageFragment();
//        Bundle args = new Bundle();
//        args.putString(messageFragment.PARAM_MESSAGE, getString(R.string.invalid_barcode_message));
//        messageFragment.setArguments(args);
//        fragmentTransaction.replace(R.id.fragment_container, messageFragment);
//        fragmentTransaction.addToBackStack(TAG_MESSAGE_FRAGMENT);
//        fragmentTransaction.commit();
        Toast.makeText(this, getString(R.string.invalid_barcode_message), Toast.LENGTH_LONG).show();
    }


    @Override
    public void showUsersAppointments() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GetAppointmentsFragment getAppointmentsFragment = new GetAppointmentsFragment();

        fragmentTransaction.replace(R.id.fragment_container, getAppointmentsFragment);
        fragmentTransaction.addToBackStack(TAG_GET_APPOINTMENTS_FRAGMENT);
        fragmentTransaction.commit();
    }



    @Override
    public void showAppointmentDetails(AppointmentWrapper appointmentWrapper) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = AppointmentDetailsFragment.newInstance(appointmentWrapper);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(TAG_GET_APPOINTMENTS_DETAILS_FRAGMENT);
        fragmentTransaction.commit();
    }

    private void showModifySettingsScreen() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new SettingsFragment();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(TAG_GET_SETTINGS_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showModifySettingsScreen();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
