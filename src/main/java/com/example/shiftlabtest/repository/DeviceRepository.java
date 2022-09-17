package com.example.shiftlabtest.repository;

import com.example.shiftlabtest.repository.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface DeviceRepository {


    public void setDeviceQuantityById(int deviceId, int newQuantity);

    public void addToDeviceQuantityById(int deviceId, int quantity);

    public void takeFromDeviceQuantityById(int deviceId, int quantity);

    public void setDevicePriceById(int deviceId, BigDecimal newPrice);

    public void setDeviceSerialNumberById(int deviceId, String newSerialNumber);

    public void deleteDevice(int deviceId);
}
