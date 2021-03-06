package demo.bluemongo.com.QMeNowClient.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import demo.bluemongo.com.QMeNowClient.R;
import demo.bluemongo.com.QMeNowClient.presenter.MainPresenter;

/**
 * Created by glenn on 26/09/15.
 */
public class MainMenuFragment extends GenericViewImpl implements MainView {
    private OnFragmentInteractionListener mListener;
    private MainPresenter presenter;
    private Button btnToEnterUserDetails;
    private Button btnToScanBarcode;
    private Button btnViewAppointments;
    private LinearLayout parentLayout;

    public CameraPreviewView.BarcodeType barcodeType;

    public interface OnFragmentInteractionListener {
        void showUserDetails(Bundle bundle);
        void openCameraPreview(CameraPreviewView.BarcodeType barcodeType);
        void viewCachedAppointments();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        btnToEnterUserDetails = (Button) view.findViewById(R.id.btn_enter_user);
        btnToEnterUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showUserDetails(null);
            }
        });

        btnToScanBarcode = (Button) view.findViewById(R.id.btn_scan_barcode);
        btnToScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openCameraPreview(CameraPreviewView.BarcodeType.BUSINESS);
            }
        });

        btnViewAppointments = (Button) view.findViewById(R.id.btn_view_appointments);
        btnViewAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.viewCachedAppointments();
            }
        });

        parentLayout = (LinearLayout) view.findViewById(R.id.ll_main);

        if (presenter.isAppointmentsCacheNotEmpty()) {
            btnViewAppointments.setEnabled(true);
            btnViewAppointments.setCompoundDrawablesWithIntrinsicBounds(R.drawable.enabled_agenda_icon, 0, 0, 0);
        }
        setUIElementsFromSavedDetails();

        return view;
    }

    @Override
    public void setUIElementsFromSavedDetails(){
        if(presenter.getButtonBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getButtonBackgroundColour());
            btnToEnterUserDetails.setBackgroundColor(colour);
            btnToScanBarcode.setBackgroundColor(colour);
            btnViewAppointments.setBackgroundColor(colour);
        }

       if(presenter.getBackgroundBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getBackgroundBackgroundColour());
            parentLayout.setBackgroundColor(colour);
        }

        if(presenter.getHeaderBackgroundColour() !=  "") {
            int color = Color.parseColor(presenter.getHeaderBackgroundColour());
            presenter.setGenericUIStuff();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
