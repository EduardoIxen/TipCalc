package edu.eduardo.android.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.eduardo.android.tipcalc.R;
import edu.eduardo.android.tipcalc.TipCalcApp;
import edu.eduardo.android.tipcalc.adapters.TipAdapter;
import edu.eduardo.android.tipcalc.fragments.TipHistoryListFragment;
import edu.eduardo.android.tipcalc.fragments.TipHistoryListFragmentListener;
import edu.eduardo.android.tipcalc.models.TipRecord;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmin;
    @Bind(R.id.inputPercentage)
    EditText inputPorcentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE =1;
    private final static int DEFAULT_TIP_PORCENTAGE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about){
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
        hideKeyBoard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){
            Double total = Double.parseDouble(strInputTotal);
            int tipPorcentage  = getTipPorcentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPorcentage(tipPorcentage);
            tipRecord.setTimestap(new Date());


            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickInCrease(){
        handleTipChange(TIP_STEP_CHANGE);
        hideKeyBoard();
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        handleTipChange(-TIP_STEP_CHANGE);
        hideKeyBoard();
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        fragmentListener.clearList();
    }

        private void handleTipChange(int change) {
        int tipPorcentage  = getTipPorcentage();
        tipPorcentage += change;
        if (tipPorcentage > 0){
            inputPorcentage.setText(String.valueOf(tipPorcentage));
        }
    }


    private int getTipPorcentage() {
        int tipPorcentage = DEFAULT_TIP_PORCENTAGE;
        String strInputTipPorcentage = inputPorcentage.getText().toString().trim();
        if (!strInputTipPorcentage.isEmpty()){
            tipPorcentage = Integer.parseInt(strInputTipPorcentage);
        }else {
            inputPorcentage.setText(String.valueOf(tipPorcentage));
        }
        return tipPorcentage;
    }

    private void hideKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe){
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcApp app=(TipCalcApp)getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
