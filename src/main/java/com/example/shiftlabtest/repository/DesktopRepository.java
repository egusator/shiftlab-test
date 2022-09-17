package com.example.shiftlabtest.repository;

import com.example.shiftlabtest.repository.model.Desktop;

import java.math.BigDecimal;
import java.util.List;

public interface DesktopRepository extends DeviceRepository{
    public void addDesktop(String serialNumber, BigDecimal price, int quantityInStock, Byte formFactor);
    public List<Desktop> getAllDesktops();

}
