package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class DeviceRepositoryImpl implements DeviceRepository {
    protected final JdbcTemplate jdbcTemplate;

    @Autowired
    public DeviceRepositoryImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void setDeviceQuantityById(int deviceId, int newQuantity) {
        final String sql = "UPDATE device SET quantity_in_stock = ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, deviceId);
        });
    }
    @Override
    public void addToDeviceQuantityById(int deviceId, int quantity) {
        final String sql = "UPDATE device SET quantity_in_stock = quantity_in_stock + ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, deviceId);
        });
    }
    @Override
    public void takeFromDeviceQuantityById(int deviceId, int quantity) {
        final String sql = "UPDATE device SET quantity_in_stock = quantity_in_stock - ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, deviceId);
        });
    }
    @Override
    public void setDevicePriceById(int deviceId, BigDecimal newPrice) {
        final String sql = "UPDATE device SET price = ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setBigDecimal(1, newPrice);
            preparedStatement.setInt(2, deviceId);
        });
    }
    @Override
    public void setDeviceSerialNumberById(int deviceId, String newSerialNumber) {
        final String sql = "UPDATE device SET serial_number = ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, newSerialNumber);
            preparedStatement.setInt(2, deviceId);
        });
    }
    @Override
    public void deleteDevice(int deviceId) {
        final String sql = "delete from device where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
           preparedStatement.setInt(1, deviceId);
        });
    }
}
