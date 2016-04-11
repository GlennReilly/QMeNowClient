package demo.bluemongo.com.barcodescannertest1.view;

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

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.presenter.MainPresenter;

/**
 * Created by glenn on 26/09/15.
 */
public class MainMenuFragment extends GenericView implements MainView {
    private OnFragmentInteractionListener mListener;
    private MainPresenter presenter;
    private Button btnToEnterUserDetails;
    private Button btnToScanBarcode;
    private LinearLayout parentLayout;

    public CameraPreviewView.BarcodeType barcodeType;

    public interface OnFragmentInteractionListener {
        void showUserDetails(Bundle bundle);
        void openCameraPreview(CameraPreviewView.BarcodeType barcodeType);
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

        parentLayout = (LinearLayout) view.findViewById(R.id.ll_main);

        setUIElementsFromSavedDetails();

        return view;
    }

    @Override
    public void setUIElementsFromSavedDetails(){
        if(presenter.getButtonBackgroundColour() !=  "") {
            int colour = Color.parseColor(presenter.getButtonBackgroundColour());
            btnToEnterUserDetails.setBackgroundColor(colour);
            btnToScanBarcode.setBackgroundColor(colour);
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
