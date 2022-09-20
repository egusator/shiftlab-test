package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Laptop extends AbstractDevice {
    private Byte size;


    public Laptop() {}

    public Byte getSize() {
        return size;
    }

    public void setSize(Byte size) {
        this.size = size;
    }
}
