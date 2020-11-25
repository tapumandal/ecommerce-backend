package com.tapumandal.ecommerce.domain.business_settings;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "business_settings")
@Component
public class BusinessSettings {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int id;

    @Column(name = "update_menu")
    protected boolean updateMenu = true;

    @Column(name = "delivery_charge")
    protected int deliveryCharge = 20;

    @Column(name = "default_discount_btn")
    protected String defaultDiscountBtn = "radioSpecialOffer"; // radioOnProduct/radioSpecialOffer

    @Column(name = "discount_name")
    protected String discountName = "Special Offer"; // Special Offer(Eid/Puja/NewYear)

    @Column(name = "discount_type")
    protected String discountType = "TotalPercentage"; // TotalPercentage/OverallAmount/ProductDiscount


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "business_settings_id_discount_type_condition", referencedColumnName = "id")
    protected List<DiscountTypeCondition> discountTypeCondition;


    @Column(name = "discount_banner")
    protected String discountBanner;

    @Column(name = "payment_discount_message")
    protected String paymentDiscountMessage = "If there is any payment discount";

    @Column(name = "payment_discount_banner")
    protected String paymentDiscountBanner;

    @Column(name = "cardPayment_discount_name")
    protected String cardPaymentDiscountName = "Debit/Credit Card";

    @Column(name = "card_payment_type")
    protected String cardPaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "business_settings_id_card_payment_condition", referencedColumnName = "id")
    protected List<DiscountTypeCondition> cardPaymentCondition;

    @Column(name = "mobile_payment_discount_name")
    protected String mobilePaymentDiscountName = "BKash/Rocket/Nagad";

    @Column(name = "mobile_payment_type")
    protected String mobilePaymentDiscountType = "TotalPercentage"; // TotalPercentage/OverallAmount

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "business_settings_id_mobile_payment_condition", referencedColumnName = "id")
    protected List<DiscountTypeCondition> mobilePaymentCondition;


    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public BusinessSettings(){}

    public BusinessSettings(BusinessSettingsDto businessSettingsDto) {
        this.id = businessSettingsDto.getId();
        this.updateMenu = businessSettingsDto.isUpdateMenu();
        this.deliveryCharge = businessSettingsDto.getDeliveryCharge();
        this.defaultDiscountBtn = businessSettingsDto.getDefaultDiscountBtn();
        this.discountName = businessSettingsDto.getDiscountName();
        this.discountType = businessSettingsDto.getDiscountType();

        this.discountTypeCondition = new ArrayList<>();
        for (DiscountTypeConditionDto dTypeConditionDto: businessSettingsDto.getDiscountTypeCondition()){
            DiscountTypeCondition dTypeCondition = new DiscountTypeCondition(dTypeConditionDto);
            this.discountTypeCondition.add(dTypeCondition);
        }

        this.discountBanner = businessSettingsDto.getDiscountBanner();
        this.paymentDiscountMessage = businessSettingsDto.getPaymentDiscountMessage();
        this.paymentDiscountBanner = businessSettingsDto.getPaymentDiscountBanner();
        this.cardPaymentDiscountName = businessSettingsDto.getCardPaymentDiscountName();
        this.cardPaymentDiscountType = businessSettingsDto.getCardPaymentDiscountType();

        this.cardPaymentCondition = new ArrayList<>();
        for (DiscountTypeConditionDto dTypeConditionDto: businessSettingsDto.getCardPaymentCondition()){
            DiscountTypeCondition dTypeCondition = new DiscountTypeCondition(dTypeConditionDto);
            this.cardPaymentCondition.add(dTypeCondition);
        }

        this.mobilePaymentDiscountName = businessSettingsDto.getMobilePaymentDiscountName();
        this.mobilePaymentDiscountType = businessSettingsDto.getMobilePaymentDiscountType();

        this.mobilePaymentCondition = new ArrayList<>();
        for (DiscountTypeConditionDto dTypeConditionDto: businessSettingsDto.getMobilePaymentCondition()){
            DiscountTypeCondition dTypeCondition = new DiscountTypeCondition(dTypeConditionDto);
            this.mobilePaymentCondition.add(dTypeCondition);
        }
    }


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

    public List<DiscountTypeCondition> getDiscountTypeCondition() {
        return discountTypeCondition;
    }

    public void setDiscountTypeCondition(List<DiscountTypeCondition> discountTypeCondition) {
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

    public List<DiscountTypeCondition> getCardPaymentCondition() {
        return cardPaymentCondition;
    }

    public void setCardPaymentCondition(List<DiscountTypeCondition> cardPaymentCondition) {
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

    public List<DiscountTypeCondition> getMobilePaymentCondition() {
        return mobilePaymentCondition;
    }

    public void setMobilePaymentCondition(List<DiscountTypeCondition> mobilePaymentCondition) {
        this.mobilePaymentCondition = mobilePaymentCondition;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
