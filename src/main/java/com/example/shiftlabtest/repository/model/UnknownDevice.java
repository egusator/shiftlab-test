package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class UnknownDevice extends AbstractDevice{
    private Byte formFactor = null;
    private Integer capacity = null;
    private Byte size = null;
    private Short diagonal = null;

    public UnknownDevice(int deviceId, String serialNumber, BigDecimal price, int quantityInStock, String manufacturerName, Byte type, Byte formFactor, int capacity, Byte size, Short diagonal) {
        super(deviceId, serialNumber, price, quantityInStock, manufacturerName, type);
        this.formFactor = formFactor;
        this.capacity = capacity;
        this.size = size;
        this.diagonal = diagonal;
    }

    public Byte getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(Byte formFactor) {
        this.formFactor = formFactor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Byte getSize() {
        return size;
    }

    public void setSize(Byte size) {
        this.size = size;
    }

    public Short getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Short diagonal) {
        this.diagonal = diagonal;
    }
}
