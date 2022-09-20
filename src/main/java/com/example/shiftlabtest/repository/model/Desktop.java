package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Desktop extends AbstractDevice {
    private Byte formFactor;



    public Desktop() {}
    public Byte getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(Byte formFactor) {
        this.formFactor = formFactor;
    }
}
