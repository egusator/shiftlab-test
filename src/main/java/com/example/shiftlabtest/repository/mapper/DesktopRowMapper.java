package com.example.shiftlabtest.repository.mapper;

import com.example.shiftlabtest.repository.model.Desktop;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DesktopRowMapper implements RowMapper<Desktop> {
    public Desktop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Desktop desktop = new Desktop(rs.getInt("device_id"),
                                        rs.getString("serial_number"),
                                            rs.getBigDecimal("price"),
                                                rs.getInt("quantity_in_stock"),
                                                    rs.getByte("device_type"),
                                                    rs.getByte("form_factor"));
        return desktop;
    }
}
