package com.example.shiftlabtest.repository.model;

import java.math.BigDecimal;

public class Desktop extends AbstractDevice {
    private Byte formFactor;

    public Desktop(int desktopId, String serialNumber, BigDecimal price,
                    int quantityInStock, Byte type, String manufacturerName, Byte formFactor) {
        super(desktopId, serialNumber, price, quantityInStock,manufacturerName ,type);
        this.formFactor = formFactor;
    }

    public Desktop(UnknownDevice unknownDevice) {
        super(unknownDevice.getDeviceId(),
                unknownDevice.getSerialNumber(),
                    unknownDevice.getPrice(),
                        unknownDevice.getQuantityInStock(),
                            unknownDevice.getManufacturerName(),
                            unknownDevice.getType());
        this.formFactor = unknownDevice.getFormFactor();
    }
    public Desktop() {}
    public Byte getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(Byte formFactor) {
        this.formFactor = formFactor;
    }
}
