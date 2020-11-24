package com.tapumandal.ecommerce.domain.business_settings;


import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Component
public class BusinessSettingsDto implements Serializable {

    @SerializedName("id")
    protected int id;

    @SerializedName("updateMenu")
    protected boolean updateMenu = true;

    @SerializedName("deliveryCharge")
    protected int deliveryCharge = 20;

    @SerializedName("defaultDiscountBtn")
    protected String defaultDiscountBtn = "radioSpecialOffer"; // radioOnProduct/radioSpecialOffer

    @SerializedName("discountName")
    protected String discountName = "Special Offer"; // Special Offer(Eid/Puja/NewYear)

    @SerializedName("discountType")
    protected String discountType = "TotalPercentage"; // TotalPercentage/OverallAmount/ProductDiscount

    @SerializedName("discountTypeCondition")
    protected List<DiscountTypeConditionDto> discountTypeCondition;


    @SerializedName("discountBanner")
    protected String discountBanner;

    @SerializedName("paymentDiscountMessage")
    protected String paymentDiscountMessage = "If there is any payment discount";

    @SerializedName("paymentDiscountBanner")
    protected String paymentDiscountBanner;

    @SerializedName("cardPaymentDiscountName")
    protected String cardPaymentDiscountName = "Debit/Credit Card";

    @SerializedName("cardPaymentType")
    protected String cardPaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @SerializedName("cardPaymentCondition")
    protected List<DiscountTypeConditionDto> cardPaymentCondition;

    @SerializedName("mobilePaymentDiscountName")
    protected String mobilePaymentDiscountName = "BKash/Rocket/Nagad";

    @SerializedName("mobilePaymentType")
    protected String mobilePaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @SerializedName("mobilePaymentCondition")
    protected List<DiscountTypeConditionDto> mobilePaymentCondition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUpdateMenu() {
        return updateMenu;
    }

    public void setUpdateMenu(boolean updateMenu) {
        this.updateMenu = updateMenu;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDefaultDiscountBtn() {
        return defaultDiscountBtn == null ? "" : defaultDiscountBtn;
    }

    public void setDefaultDiscountBtn(String defaultDiscountBtn) {
        this.defaultDiscountBtn = defaultDiscountBtn;
    }

    public String getDiscountName() {
        return discountName == null ? "" : discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountType() {
        return discountType == null ? "" : discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public List<DiscountTypeConditionDto> getDiscountTypeCondition() {
        return discountTypeCondition;
    }

    public void setDiscountTypeCondition(List<DiscountTypeConditionDto> discountTypeCondition) {
        this.discountTypeCondition = discountTypeCondition;
    }

    public String getDiscountBanner() {
        return discountBanner == null ? "" : discountBanner;
    }

    public void setDiscountBanner(String discountBanner) {
        this.discountBanner = discountBanner;
    }

    public String getPaymentDiscountMessage() {
        return paymentDiscountMessage == null ? "" : paymentDiscountMessage;
    }

    public void setPaymentDiscountMessage(String paymentDiscountMessage) {
        this.paymentDiscountMessage = paymentDiscountMessage;
    }

    public String getPaymentDiscountBanner() {
        return paymentDiscountBanner == null ? "" : paymentDiscountBanner;
    }

    public void setPaymentDiscountBanner(String paymentDiscountBanner) {
        this.paymentDiscountBanner = paymentDiscountBanner;
    }

    public String getCardPaymentDiscountName() {
        return cardPaymentDiscountName == null ? "" : cardPaymentDiscountName;
    }

    public void setCardPaymentDiscountName(String cardPaymentDiscountName) {
        this.cardPaymentDiscountName = cardPaymentDiscountName;
    }

    public String getCardPaymentDiscountType() {
        return cardPaymentDiscountType == null ? "" : cardPaymentDiscountType;
    }

    public void setCardPaymentDiscountType(String cardPaymentDiscountType) {
        this.cardPaymentDiscountType = cardPaymentDiscountType;
    }

    public List<DiscountTypeConditionDto> getCardPaymentCondition() {
        return cardPaymentCondition;
    }

    public void setCardPaymentCondition(List<DiscountTypeConditionDto> cardPaymentCondition) {
        this.cardPaymentCondition = cardPaymentCondition;
    }

    public String getMobilePaymentDiscountName() {
        return mobilePaymentDiscountName == null ? "" : mobilePaymentDiscountName;
    }

    public void setMobilePaymentDiscountName(String mobilePaymentDiscountName) {
        this.mobilePaymentDiscountName = mobilePaymentDiscountName;
    }

    public String getMobilePaymentDiscountType() {
        return mobilePaymentDiscountType == null ? "" : mobilePaymentDiscountType;
    }

    public void setMobilePaymentDiscountType(String mobilePaymentDiscountType) {
        this.mobilePaymentDiscountType = mobilePaymentDiscountType;
    }

    public List<DiscountTypeConditionDto> getMobilePaymentCondition() {
        return mobilePaymentCondition;
    }

    public void setMobilePaymentCondition(List<DiscountTypeConditionDto> mobilePaymentCondition) {
        this.mobilePaymentCondition = mobilePaymentCondition;
    }
}
