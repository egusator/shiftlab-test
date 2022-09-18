package com.example.shiftlabtest.repository.mapper;

import com.example.shiftlabtest.repository.model.Desktop;
import com.example.shiftlabtest.repository.model.Laptop;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("laptopRowMapper")
public class LaptopRowMapper implements RowMapper<Laptop> {
    public Laptop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Laptop laptop = new Laptop(rs.getInt("device_id"),
                rs.getString("serial_number"),
                rs.getBigDecimal("price"),
                rs.getInt("quantity_in_stock"),
                rs.getString("manufacturer_name"),
                rs.getByte("device_type"),
                rs.getByte("size"));
        return laptop;
    }
}
