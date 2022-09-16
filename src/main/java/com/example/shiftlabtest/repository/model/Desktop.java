package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Desktop extends abstractDevice {
    private Byte formFactor;

    public Desktop(int desktopId, String serialNumber, BigDecimal price,
                    int quantityInStock, Byte formFactor) {
        super(desktopId, serialNumber, price, quantityInStock);
        this.formFactor = formFactor;
    }
    public Desktop() {}
    public Byte getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(Byte formFactor) {
        this.formFactor = formFactor;
    }
}
