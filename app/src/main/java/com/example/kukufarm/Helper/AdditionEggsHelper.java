package com.example.kukufarm.Helper;

public class AdditionEggsHelper {
    String goodEggs,notTrayGoodEggs,badEggs,datePicker,flocks,remarks;

    public AdditionEggsHelper() {
    }

    public AdditionEggsHelper(String goodEggs, String notTrayGoodEggs, String badEggs, String datePicker, String flocks, String remarks) {
        this.goodEggs = goodEggs;
        this.notTrayGoodEggs = notTrayGoodEggs;
        this.badEggs = badEggs;
        this.datePicker = datePicker;
        this.flocks = flocks;
        this.remarks = remarks;
    }

    public String getGoodEggs() {
        return goodEggs;
    }

    public void setGoodEggs(String goodEggs) {
        this.goodEggs = goodEggs;
    }

    public String getNotTrayGoodEggs() {
        return notTrayGoodEggs;
    }

    public void setNotTrayGoodEggs(String notTrayGoodEggs) {
        this.notTrayGoodEggs = notTrayGoodEggs;
    }

    public String getBadEggs() {
        return badEggs;
    }

    public void setBadEggs(String badEggs) {
        this.badEggs = badEggs;
    }

    public String getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(String datePicker) {
        this.datePicker = datePicker;
    }

    public String getFlocks() {
        return flocks;
    }

    public void setFlocks(String flocks) {
        this.flocks = flocks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
