package edu.eduardo.android.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DELL on 12/06/2016.
 */
public class TipRecord {
    private double bill;
    private int tipPorcentage;
    private Date timestap;

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPorcentage() {
        return tipPorcentage;
    }

    public void setTipPorcentage(int tipPorcentage) {
        this.tipPorcentage = tipPorcentage;
    }

    public Date getTimestap() {
        return timestap;
    }

    public void setTimestap(Date timestap) {
        this.timestap = timestap;
    }

    public double getTip(){
        return bill*(tipPorcentage/100d);
    }
    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return simpleDateFormat.format(timestap);
    }
}
