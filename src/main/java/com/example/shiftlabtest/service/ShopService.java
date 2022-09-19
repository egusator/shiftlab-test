package com.example.shiftlabtest.service;

import com.example.shiftlabtest.repository.impl.*;
import com.example.shiftlabtest.repository.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShopService {
    private final DesktopRepository desktopRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;
    private final HardDiskRepository hardDiskRepository;

    private final CommonDeviceRepository commonDeviceRepository;

    @Autowired
    public ShopService(DesktopRepository desktopRepository, LaptopRepository laptopRepository, MonitorRepository monitorRepository, HardDiskRepository hardDiskRepository, CommonDeviceRepository commonDeviceRepository) {
        this.desktopRepository = desktopRepository;
        this.laptopRepository = laptopRepository;
        this.monitorRepository = monitorRepository;
        this.hardDiskRepository = hardDiskRepository;
        this.commonDeviceRepository = commonDeviceRepository;
    }

    public void addMonitor(String serialNumber, BigDecimal price, int quantityInStock,
                           String manufacturerName, int diagonal) {
        monitorRepository.addMonitor(serialNumber, price, quantityInStock, manufacturerName, diagonal);
    }


    public void addLaptop(String serialNumber, BigDecimal price, int quantityInStock,
                           String manufacturerName, Byte size ) {
        laptopRepository.addLaptop(serialNumber, price, quantityInStock, manufacturerName, size);
    }

    public void addDesktop(String serialNumber, BigDecimal price, int quantityInStock,
                          String manufacturerName, Byte form_factor ) {
        desktopRepository.addDesktop(serialNumber, price, quantityInStock, manufacturerName, form_factor);
    }

    public void addHardDisk(String serialNumber, BigDecimal price, int quantityInStock,
                           String manufacturerName, int capacity) {
        hardDiskRepository.addHardDisk(serialNumber, price, quantityInStock, manufacturerName, capacity);
    }

    public void deleteDevice(int id) {
        commonDeviceRepository.deleteDevice(id);
    }

    public List<Desktop> getAllDesktops() {
       return desktopRepository.getAll();
    }

    public List<Monitor> getAllMonitors() {
        return monitorRepository.getAll();
    }


    public List<HardDisk> getAllHardDisks() {
        return hardDiskRepository.getAll();
    }

    public List<Laptop> getAllLaptops() {
        return laptopRepository.getAll();
    }

    public void setDeviceQuantity (int deviceId, int quantity) {
        commonDeviceRepository.setDeviceQuantityById(deviceId, quantity);
    }
    public void increaseDeviceQuantity(int deviceId, int amount) {
        commonDeviceRepository.increaseDeviceQuantityById(deviceId, amount);
    }
    public void decreaseDeviceQuantity(int deviceId, int amount) {
        commonDeviceRepository.decreaseDeviceQuantityById(deviceId, amount);
    }

    public void setDevicePrice (int deviceId, BigDecimal price) {
        commonDeviceRepository.setDevicePriceById(deviceId, price);
    }
    public void setDeviceSerialNumber (int deviceId, String serialNumber) {
        commonDeviceRepository.setDeviceSerialNumberById(deviceId, serialNumber);
    }

    public AbstractDevice getDevice (int deviceId) {
        return commonDeviceRepository.getDeviceById(deviceId);
    }
}
