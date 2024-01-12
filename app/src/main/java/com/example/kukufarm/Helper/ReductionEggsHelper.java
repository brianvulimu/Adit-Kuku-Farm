package com.example.kukufarm.Helper;

public class ReductionEggsHelper {
    String flocks,reduction_reason,customer_name,reduction_date;
    int money_earned, quantity_of_reduction;

    public ReductionEggsHelper() {
    }

    public ReductionEggsHelper(String flocks, String reduction_reason, String customer_name, String reduction_date, int money_earned, int quantity_of_reduction) {
        this.flocks = flocks;
        this.reduction_reason = reduction_reason;
        this.customer_name = customer_name;
        this.reduction_date = reduction_date;
        this.money_earned = money_earned;
        this.quantity_of_reduction = quantity_of_reduction;
    }

    public String getFlocks() {
        return flocks;
    }

    public void setFlocks(String flocks) {
        this.flocks = flocks;
    }

    public String getReduction_reason() {
        return reduction_reason;
    }

    public void setReduction_reason(String reduction_reason) {
        this.reduction_reason = reduction_reason;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getReduction_date() {
        return reduction_date;
    }

    public void setReduction_date(String reduction_date) {
        this.reduction_date = reduction_date;
    }

    public int getMoney_earned() {
        return money_earned;
    }

    public void setMoney_earned(int money_earned) {
        this.money_earned = money_earned;
    }

    public int getQuantity_of_reduction() {
        return quantity_of_reduction;
    }

    public void setQuantity_of_reduction(int quantity_of_reduction) {
        this.quantity_of_reduction = quantity_of_reduction;
    }
}
