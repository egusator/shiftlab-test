package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.model.UnknownDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractDeviceRepository<T> {
    protected final JdbcTemplate jdbcTemplate;
    @Autowired
    private RowMapper<UnknownDevice> rowMapper;
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

    public UnknownDevice getUnknownDeviceById(int deviceId) {
        final String sql = "SELECT *\n" +
                "FROM device\n" +
                "         LEFT JOIN HARD_DISK_PROPERTIES HDP on DEVICE.DEVICE_ID = HDP.HARD_DISK_ID\n" +
                "         LEFT JOIN DESKTOP_PROPERTIES DP on DEVICE.DEVICE_ID = DP.DESKTOP_ID\n" +
                "         LEFT JOIN LAPTOP_PROPERTIES LP on DEVICE.DEVICE_ID = LP.LAPTOP_ID\n" +
                "         LEFT JOIN MONITOR_PROPERTIES MP on DEVICE.DEVICE_ID = MP.MONITOR_ID\n" +
                "WHERE\n" +
                "    device.DEVICE_ID = ?\n";
        return jdbcTemplate.query(sql, rowMapper).get(0);
    }
}
