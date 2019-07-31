package com.hfad.databuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private EditText[] editTexts = new EditText[7];
    private int[] textViewIds = {R.id.bit_view, R.id.byte_view, R.id.kilo_view, R.id.mega_view,
            R.id.giga_view, R.id.tera_view, R.id.peta_view};
    private int[] editTextIds = {R.id.bit_text, R.id.byte_text, R.id.kilo_text, R.id.mega_text,
            R.id.giga_text, R.id.tera_text, R.id.peta_text};
    private GenericTextWatcher[] watchers = new GenericTextWatcher[7];
    private SharedPreferences savedTextValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        setDefaultVisibilities();
        displayViews();
        savedTextValues = getSharedPreferences("textValues", MODE_PRIVATE);
        for(int i = 0; i < editTexts.length; ++i) {
            editTexts[i] = (EditText) findViewById(editTextIds[i]);
            watchers[i] = new GenericTextWatcher(editTexts[i]);
            editTexts[i].addTextChangedListener(watchers[i]);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        for(int i = 0; i < editTexts.length; ++i) {
            String text = savedTextValues.getString(String.valueOf(editTextIds[i]), "");
            editTexts[i].setText(text);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        for(int i = 0; i < editTexts.length; ++i) {
            savedTextValues.edit().putString(String.valueOf(editTextIds[i]),
                    editTexts[i].getText().toString()).apply();
        }

    }

    private void displayViews() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> dataTypes = sharedPreferences.getStringSet("dataSelection", null);
        for(String item : dataTypes) {
            for(int id : textViewIds) {
                TextView textView = (TextView) findViewById(id);
                if(textView.getTag().toString().equals(item)) {
                    specifyVisibility(id, View.VISIBLE);
                }
            }
            for(int id : editTextIds) {
                EditText editText = (EditText) findViewById(id);
                if(editText.getTag().toString().equals(item)) {
                    specifyVisibility(id, View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDefaultVisibilities() {
        for(int id : textViewIds)
            specifyVisibility(id, View.GONE);
        for(int id : editTextIds)
            specifyVisibility(id, View.GONE);
    }

    private void specifyVisibility(int id, int visibility) {
        View view = findViewById(id);
        view.setVisibility(visibility);
    }

    private class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence text, int start, int i1, int i2) {
            String textString = text.toString();
            if((text.length() > 0) && !(textString.contains("-") || textString.contains("E"))) {
                double currentTextValue = Double.parseDouble(textString);
                setEditTexts(view.getId(), currentTextValue);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}

        private void setEditTexts(int currentViewId, double value) {
            for(int i = 0; i < editTexts.length; ++i) {
                if(editTexts[i].getId() != currentViewId) {
                    editTexts[i].removeTextChangedListener(watchers[i]);
                    editTexts[i].setText(String.valueOf(computeConversion(currentViewId, i,
                            value)));
                    editTexts[i].addTextChangedListener(watchers[i]);
                }
            }
        }

        private double computeConversion(int id, double i, double value) {
            double result = 0;
            switch(id) {
                case R.id.bit_text:
                    result = value * (1.0 / 8.0) / Math.pow(1000.0, (i - 1.0));
                    break;
                case R.id.byte_text:
                    result = computeResult(i, value, 0.0, 1);
                    break;
                case R.id.kilo_text:
                    result = computeResult(i, value, 3, 2);
                    break;
                case R.id.mega_text:
                    result = computeResult(i, value, 6, 3);
                    break;
                case R.id.giga_text:
                    result = computeResult(i, value, 9, 4);
                    break;
                case R.id.tera_text:
                    result = computeResult(i, value, 12, 5);
                    break;
                case R.id.peta_text:
                    result = computeResult(i, value, 15, 6);
                    break;
            }
            return result;
        }

        private double computeResult(double i, double value, double factor, double previousNum) {
            double result = 0;
            if(i < previousNum)
                result = value * (Math.pow(1000.0, i));
            if(i == 0)
                result = result * 8 * (Math.pow(10, factor));
            else
                result = value / Math.pow(1000.0, i - previousNum);
            return result;
        }
    }
}
