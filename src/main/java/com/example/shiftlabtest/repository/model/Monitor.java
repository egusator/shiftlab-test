package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Monitor extends AbstractDevice {
    private Short diagonal;

    public Monitor() {
    }

    public Short getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Short diagonal) {
        this.diagonal = diagonal;
    }
}
