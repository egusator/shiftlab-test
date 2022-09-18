package com.example.shiftlabtest.repository.mapper;

import com.example.shiftlabtest.repository.model.Laptop;
import com.example.shiftlabtest.repository.model.Monitor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("monitorRowMapper")
public class MonitorRowMapper implements RowMapper<Monitor> {
    public Monitor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Monitor monitor = new Monitor(rs.getInt("device_id"),
                rs.getString("serial_number"),
                rs.getBigDecimal("price"),
                rs.getInt("quantity_in_stock"),
                rs.getString("manufacturer_name"),
                rs.getByte("device_type"),
                rs.getShort("diagonal"));
        return monitor;
    }
}

