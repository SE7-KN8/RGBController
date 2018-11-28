package se7kn8.rgbcontroller;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by sebkn on 13.01.2018.
 * The new hsv fragment
 */

public class HSVFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private SeekBar hueSeekBar;
    private SeekBar saturationSeekBar;
    private SeekBar valueSeekBar;

    private TextView hueTextView;
    private TextView saturationTextView;
    private TextView valueTextView;

    private View colorView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("HSVFragment", "Created");
        View view = inflater.inflate(R.layout.fragment_hsv, container, false);

        colorView = view.findViewById(R.id.color_view);

        hueTextView = view.findViewById(R.id.textView_Hue);
        saturationTextView = view.findViewById(R.id.textView_Saturation);
        valueTextView = view.findViewById(R.id.textView_Value);

        hueSeekBar = view.findViewById(R.id.seekBar_Hue);
        hueSeekBar.setMax(360);
        hueSeekBar.setOnSeekBarChangeListener(this);
        saturationSeekBar = view.findViewById(R.id.seekBar_Saturation);
        saturationSeekBar.setMax(1000);

        saturationSeekBar.setOnSeekBarChangeListener(this);
        valueSeekBar = view.findViewById(R.id.seekBar_Value);
        valueSeekBar.setOnSeekBarChangeListener(this);
        valueSeekBar.setMax(1000);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float hue = hueSeekBar.getProgress();
        float saturationPercent = saturationSeekBar.getProgress() / 1000f;
        float valuePercent = valueSeekBar.getProgress() / 1000f;
        float[] hsv = new float[]{hue, saturationPercent, valuePercent};

        int color = Color.HSVToColor(hsv);

        colorView.setBackgroundColor(color);

        ColorTask.updateRed(Color.red(color));
        ColorTask.updateGreen(Color.green(color));
        ColorTask.updateBlue(Color.blue(color));

        if (seekBar == hueSeekBar) {
            hueTextView.setText(getString(R.string.seekBar_hue, seekBar.getProgress()));
        } else if (seekBar == saturationSeekBar) {
            saturationTextView.setText(getString(R.string.seekBar_saturation, seekBar.getProgress() / 10f));
        } else if (seekBar == valueSeekBar) {
            valueTextView.setText(getString(R.string.seekBar_value, seekBar.getProgress() / 10f));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
