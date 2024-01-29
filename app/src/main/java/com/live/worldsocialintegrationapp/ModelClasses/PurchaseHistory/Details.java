package com.live.worldsocialintegrationapp.ModelClasses.PurchaseHistory;

import java.util.ArrayList;

public class Details {

    public ArrayList<PurchaseLuckyIdHistory> purchase_luckyId_history;
    public ArrayList<PurchaseFrameHistory> purchase_frame_history;
    public ArrayList<PurchaseSilverCoinHistory> purchaseSilverCoin_history;


    public ArrayList<PurchaseLuckyIdHistory> getPurchase_luckyId_history() {
        return purchase_luckyId_history;
    }

    public void setPurchase_luckyId_history(ArrayList<PurchaseLuckyIdHistory> purchase_luckyId_history) {
        this.purchase_luckyId_history = purchase_luckyId_history;
    }

    public ArrayList<PurchaseFrameHistory> getPurchase_frame_history() {
        return purchase_frame_history;
    }

    public void setPurchase_frame_history(ArrayList<PurchaseFrameHistory> purchase_frame_history) {
        this.purchase_frame_history = purchase_frame_history;
    }

    public ArrayList<PurchaseSilverCoinHistory> getPurchaseSilverCoin_history() {
        return purchaseSilverCoin_history;
    }

    public void setPurchaseSilverCoin_history(ArrayList<PurchaseSilverCoinHistory> purchaseSilverCoin_history) {
        this.purchaseSilverCoin_history = purchaseSilverCoin_history;
    }
}
