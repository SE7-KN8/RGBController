package se7kn8.rgbcontroller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by sebkn on 13.01.2018.
 * The new rgb fragment
 */

public class RGBFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;

    private TextView redTextView;
    private TextView greenTextView;
    private TextView blueTextView;

    private View colorView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d("RGBFragment", "Created");
        View view = inflater.inflate(R.layout.fragment_rgb, container, false);

        colorView = view.findViewById(R.id.color_view);

        redTextView = view.findViewById(R.id.textView_red);
        greenTextView = view.findViewById(R.id.textView_green);
        blueTextView = view.findViewById(R.id.textView_blue);

        redSeekBar = view.findViewById(R.id.seekBar_red);
        redSeekBar.setMax(255);
        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar = view.findViewById(R.id.seekBar_green);
        greenSeekBar.setMax(255);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar = view.findViewById(R.id.seekBar_blue);
        blueSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setMax(255);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        colorView.setBackgroundColor(Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress()));

        if (seekBar == redSeekBar) {
            redTextView.setText(getString(R.string.seekBar_r, seekBar.getProgress()));
            //ColorTask.updateRed(seekBar.getProgress());
        } else if (seekBar == greenSeekBar) {
            greenTextView.setText(getString(R.string.seekBar_g, seekBar.getProgress()));
            //ColorTask.updateGreen(seekBar.getProgress());
        } else if (seekBar == blueSeekBar) {
            blueTextView.setText(getString(R.string.seekBar_b, seekBar.getProgress()));
            //ColorTask.updateBlue(seekBar.getProgress());
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.requireActivity());
        ColorTask.updateColor(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress(), preferences.getString("ip", "192.168.4.1"), Integer.valueOf(preferences.getString("port", "91")));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
