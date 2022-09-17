package com.example.shiftlabtest.repository;

import com.example.shiftlabtest.repository.model.Monitor;

import java.math.BigDecimal;
import java.util.List;

public interface MonitorRepository extends DeviceRepository {
    public void addMonitor(String serialNumber, BigDecimal price, int quantityInStock, int diagonal);
    public List<Monitor> getAllMonitors();
}
