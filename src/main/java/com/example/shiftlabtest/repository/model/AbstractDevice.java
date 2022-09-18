package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public abstract class AbstractDevice {
    private int deviceId;
    private String serialNumber;
    private BigDecimal price;
    private int quantityInStock;

    private String manufacturerName;

    public AbstractDevice(int deviceId, String serialNumber, BigDecimal price, int quantityInStock, String manufacturerName, Byte type) {
        this.deviceId = deviceId;
        this.serialNumber = serialNumber;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.manufacturerName = manufacturerName;
        this.type = type;
    }

    private Byte type;

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public AbstractDevice() {
    }
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
