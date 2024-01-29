package com.live.worldsocialintegrationapp.ModelClasses.GetCoin;

import java.io.Serializable;
import java.util.ArrayList;

public class GetSilverCoinRoot implements Serializable {

    public String status;
    public String message;
    public ArrayList<Detail> details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }

    public class Detail{

        public String id;
        public String coinValue;
        public String moneyValue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoinValue() {
            return coinValue;
        }

        public void setCoinValue(String coinValue) {
            this.coinValue = coinValue;
        }

        public String getMoneyValue() {
            return moneyValue;
        }

        public void setMoneyValue(String moneyValue) {
            this.moneyValue = moneyValue;
        }
    }
}
