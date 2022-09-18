package com.example.shiftlabtest.repository.mapper;

import com.example.shiftlabtest.repository.model.Monitor;
import com.example.shiftlabtest.repository.model.UnknownDevice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnknownDeviceRowMapper implements RowMapper<UnknownDevice> {
    @Override
    public UnknownDevice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UnknownDevice(rs.getInt("device_id"),
                rs.getString("serial_number"),
                rs.getBigDecimal("price"),
                rs.getInt("quantity_in_stock"),
                rs.getString("manufacturer_name"),
                rs.getByte("device_type"),
                rs.getByte("form_factor"),
                rs.getInt("capacity"),
                rs.getByte("size"),
                rs.getShort("diagonal"));
    }
}
