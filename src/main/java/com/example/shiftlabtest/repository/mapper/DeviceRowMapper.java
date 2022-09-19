package com.example.shiftlabtest.repository.mapper;

import com.example.shiftlabtest.repository.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceRowMapper implements RowMapper<AbstractDevice> {
    @Override
    public AbstractDevice mapRow(ResultSet rs, int rowNum) throws SQLException {

        try {
            if (rs.getBoolean("isMonitor")) {
                Monitor returnValue = new Monitor();
                returnValue.setDeviceId(rs.getInt("device_id"));
                returnValue.setSerialNumber(rs.getString("serial_number"));
                returnValue.setQuantityInStock(rs.getInt("quantity_in_stock"));
                returnValue.setManufacturerName(rs.getString("manufacturer_name"));

                returnValue.setPrice(rs.getBigDecimal("price"));
                returnValue.setDiagonal(rs.getShort("diagonal"));

                return returnValue;
            }
        } catch (SQLException e) {
            //do nothing
        }

        try {
            if (rs.getBoolean("isDesktop")) {
                Desktop returnValue = new Desktop();
                returnValue.setDeviceId(rs.getInt("device_id"));
                returnValue.setSerialNumber(rs.getString("serial_number"));
                returnValue.setQuantityInStock(rs.getInt("quantity_in_stock"));
                returnValue.setManufacturerName(rs.getString("manufacturer_name"));

                returnValue.setPrice(rs.getBigDecimal("price"));
                returnValue.setFormFactor(rs.getByte("form_factor"));

                return returnValue;
            }
        } catch (SQLException e) {
            //do nothing
        }

        try {
            if (rs.getBoolean("isHardDisk")) {
                HardDisk returnValue = new HardDisk();
                returnValue.setDeviceId(rs.getInt("device_id"));
                returnValue.setSerialNumber(rs.getString("serial_number"));
                returnValue.setQuantityInStock(rs.getInt("quantity_in_stock"));
                returnValue.setManufacturerName(rs.getString("manufacturer_name"));

                returnValue.setPrice(rs.getBigDecimal("price"));
                returnValue.setCapacity(rs.getInt("capacity"));
                return returnValue;
            }
        } catch (SQLException e) {
            //do nothing
        }

        try {
            if (rs.getBoolean("isLaptop")) {
                Laptop returnValue = new Laptop();
                returnValue.setDeviceId(rs.getInt("device_id"));
                returnValue.setSerialNumber(rs.getString("serial_number"));
                returnValue.setQuantityInStock(rs.getInt("quantity_in_stock"));
                returnValue.setManufacturerName(rs.getString("manufacturer_name"));

                returnValue.setPrice(rs.getBigDecimal("price"));
                returnValue.setSize(rs.getByte("size"));
                return returnValue;
            }
        } catch (SQLException e) {
            //do nothing
        }

        //FIXME make own exception
        throw new IllegalArgumentException("Can't parse device.");



    }
}
