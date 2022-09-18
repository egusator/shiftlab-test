package com.example.shiftlabtest.service;

import com.example.shiftlabtest.repository.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShopService {
    private final DesktopRepository desktopRepository;
    private final LaptopRepository laptopRepository;
    private final MonitorRepository monitorRepository;
    private final HardDiskRepository hardDiskRepository;
    private final UnknownDeviceRepository unknownDeviceRepository;

    @Autowired
    public ShopService(DesktopRepository desktopRepository, LaptopRepository laptopRepository, MonitorRepository monitorRepository, HardDiskRepository hardDiskRepository, UnknownDeviceRepository unknownDeviceRepository) {
        this.desktopRepository = desktopRepository;
        this.laptopRepository = laptopRepository;
        this.monitorRepository = monitorRepository;
        this.hardDiskRepository = hardDiskRepository;
        this.unknownDeviceRepository = unknownDeviceRepository;
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
        unknownDeviceRepository.deleteDevice(id);
    }
}
