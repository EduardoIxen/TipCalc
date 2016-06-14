package edu.eduardo.android.tipcalc.fragments;

import edu.eduardo.android.tipcalc.models.TipRecord;

/**
 * Created by DELL on 11/06/2016.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
