package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Desktop extends AbstractDevice {
    private Byte formFactor;

    public Desktop(int desktopId, String serialNumber, BigDecimal price,
                    int quantityInStock, Byte type, String manufacturerName, Byte formFactor) {
        super(desktopId, serialNumber, price, quantityInStock,manufacturerName);
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
