package com.project.data;

public class OrderDataTest {
    private final String browser;
    private final boolean useHeaderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String color;

    public OrderDataTest(String browser, boolean useHeaderButton, String name, String surname, String address,
                         String metroStation, String phone, String deliveryDate,
                         String rentalPeriod, String color) {
        this.browser = browser;
        this.useHeaderButton = useHeaderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
    }

    // Геттеры
    public String getBrowser() {
        return browser;
    }

    public boolean isUseHeaderButton() { return useHeaderButton; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public String getMetroStation() { return metroStation; }
    public String getPhone() { return phone; }
    public String getDeliveryDate() { return deliveryDate; }
    public String getRentalPeriod() { return rentalPeriod; }
    public String getColor() { return color; }
}
