package uk.co.tmmct.rigging;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class InputsFragment extends Fragment {

    private EditText catchAngle;
    private EditText finishAngle;
    private EditText sweepAngle;

    private TextWatcher updateSweepAngleWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            final String catchAngleText = catchAngle.getText().toString();
            final String finishAngleText = finishAngle.getText().toString();
            try {
                int sweepAngle = Integer.parseInt(catchAngleText) + Integer.parseInt(finishAngleText);
                sweepAngle %= 360;
                InputsFragment.this.sweepAngle.setText(Integer.toString(sweepAngle));
            } catch (NumberFormatException e) {
                //nowt
            }
        }
    };

    public static Fragment newInstance() {
        return new InputsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inputs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        catchAngle = (EditText) view.findViewById(R.id.catch_angle);
        finishAngle = (EditText) view.findViewById(R.id.finish_angle);
        sweepAngle = (EditText) view.findViewById(R.id.sweep_angle);

        setAngleListeners();
    }

    private void setAngleListeners() {
        catchAngle.addTextChangedListener(updateSweepAngleWatcher);
        finishAngle.addTextChangedListener(updateSweepAngleWatcher);
    }
}
