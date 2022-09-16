package com.example.shiftlabtest.repository.mapper;

import com.example.shiftlabtest.repository.model.HardDisk;
import com.example.shiftlabtest.repository.model.Laptop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HardDiskRowMapper implements RowMapper<HardDisk> {
    public HardDisk mapRow(ResultSet rs, int rowNum) throws SQLException {
        HardDisk hardDisk = new HardDisk(rs.getInt("device_id"),
                rs.getString("serial_number"),
                rs.getBigDecimal("price"),
                rs.getInt("quantity_in_stock"),
                rs.getInt("capacity"));
        return hardDisk;
    }
}

