package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.mapper.DeviceRowMapper;
import com.example.shiftlabtest.repository.model.AbstractDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;
@Repository
public abstract class AbstractDeviceRepository<T> {

    private JdbcTemplate jdbcTemplate;
    protected TransactionTemplate transactionTemplate;

    protected static final DeviceRowMapper deviceRowMapper = new DeviceRowMapper();

    public AbstractDeviceRepository(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
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
    public void decreaseDeviceQuantityById(int deviceId, int quantity) {
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

    public void addMainCharacteristics(String serialNumber, BigDecimal price, int quantityInStock, String manufacturerName, Byte type) {
        final String mainCharacteristicsSql = "insert into device (serial_number, price, quantity_in_stock, MANUFACTURER_NAME)" +
                "values (?,?,?,?);";
        jdbcTemplate.update(mainCharacteristicsSql,preparedStatement -> {
            preparedStatement.setString(1, serialNumber);
            preparedStatement.setBigDecimal(2, price);
            preparedStatement.setInt(3, quantityInStock);
            preparedStatement.setString(4, manufacturerName);

        });
    }
    public abstract List<T> getAll();

    public void deleteDevice(int deviceId) {
        final String sql = "delete from device where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, deviceId);
        });
    }
}
