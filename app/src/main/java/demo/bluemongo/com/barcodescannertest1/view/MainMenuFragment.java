package demo.bluemongo.com.barcodescannertest1.view;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import demo.bluemongo.com.barcodescannertest1.R;
import demo.bluemongo.com.barcodescannertest1.presenter.MainPresenter;

/**
 * Created by glenn on 26/09/15.
 */
public class MainMenuFragment extends Fragment implements MainView {
    private OnFragmentInteractionListener mListener;
    private MainPresenter presenter;

    public interface OnFragmentInteractionListener {
        void enterUserDetails();
        void openCameraPreview();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnToEnterUserDetails = (Button) view.findViewById(R.id.btn_enter_user);
        btnToEnterUserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.enterUserDetails();
            }
        });

        Button btnToScanBarcode = (Button) view.findViewById(R.id.btn_scan_barcode);
        btnToScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.openCameraPreview();
            }
        });

        return view;
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
