package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractDeviceRepository<T> {
    protected final JdbcTemplate jdbcTemplate;

    @Autowired
    public AbstractDeviceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void setDeviceQuantityById(int deviceId, int newQuantity) {
        final String sql = "UPDATE device SET quantity_in_stock = ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, deviceId);
        });
    }
    public void increaseDeviceQuantityById(int deviceId, int quantity) {
        final String sql = "UPDATE device SET quantity_in_stock = quantity_in_stock + ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, deviceId);
        });
    }
    public void takeFromDeviceQuantityById(int deviceId, int quantity) {
        final String sql = "UPDATE device SET quantity_in_stock = quantity_in_stock - ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, deviceId);
        });
    }
    public void setDevicePriceById(int deviceId, BigDecimal newPrice) {
        final String sql = "UPDATE device SET price = ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setBigDecimal(1, newPrice);
            preparedStatement.setInt(2, deviceId);
        });
    }
    public void setDeviceSerialNumberById(int deviceId, String newSerialNumber) {
        final String sql = "UPDATE device SET serial_number = ? where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, newSerialNumber);
            preparedStatement.setInt(2, deviceId);
        });
    }
    public void deleteDevice(int deviceId) {
        final String sql = "delete from device where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
           preparedStatement.setInt(1, deviceId);
        });
    }

    public void addMainCharacteristics(String serialNumber, BigDecimal price, int quantityInStock, Byte type) {
        final String mainCharacteristicsSql = "insert into device (serial_number, price, quantity_in_stock, device_type)" +
                "values (?,?,?,?);";
        jdbcTemplate.update(mainCharacteristicsSql,preparedStatement -> {
            preparedStatement.setString(1, serialNumber);
            preparedStatement.setBigDecimal(2, price);
            preparedStatement.setInt(3, quantityInStock);
            preparedStatement.setByte(4,type);

        });
    }
    public abstract List<T> getAll();
}
