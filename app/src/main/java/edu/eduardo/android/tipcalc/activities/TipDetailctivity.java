package edu.eduardo.android.tipcalc.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.eduardo.android.tipcalc.R;

public class TipDetailctivity extends AppCompatActivity {
    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimeStap)
    TextView txtTimestap;

    public final static String TIP_KEK = "tip";
    public final static String DATE_KEY = "timestamp";
    public final static String BILL_TOTAL_KEY = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detailctivity);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String strTotal = String.format(getString(R.string.tipdetail_message_bill),
                                        intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));
        String strTip = String.format(getString(R.string.global_message_tip),
                intent.getDoubleExtra(TIP_KEK, 0d));

        txtBillTotal.setText(strTotal);
        txtTip.setText(strTip);
        txtTimestap.setText(intent.getStringExtra(DATE_KEY));
    }
}
