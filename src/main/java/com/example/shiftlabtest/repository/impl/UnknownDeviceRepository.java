package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.model.UnknownDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository

public class UnknownDeviceRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<UnknownDevice> unknownDeviceRowMapper;

    @Autowired
    public UnknownDeviceRepository(JdbcTemplate jdbcTemplate, RowMapper<UnknownDevice> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.unknownDeviceRowMapper = rowMapper;
    }
    public void deleteDevice(int deviceId) {
        final String sql = "delete from device where device_id = ?";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setInt(1, deviceId);
        });
    }
    public UnknownDevice getUnknownDeviceById(int deviceId) {
        final String sql = "SELECT *\n" +
                "FROM device\n" +
                "         LEFT JOIN HARD_DISK_PROPERTIES HDP on DEVICE.DEVICE_ID = HDP.HARD_DISK_ID\n" +
                "         LEFT JOIN DESKTOP_PROPERTIES DP on DEVICE.DEVICE_ID = DP.DESKTOP_ID\n" +
                "         LEFT JOIN LAPTOP_PROPERTIES LP on DEVICE.DEVICE_ID = LP.LAPTOP_ID\n" +
                "         LEFT JOIN MONITOR_PROPERTIES MP on DEVICE.DEVICE_ID = MP.MONITOR_ID\n" +
                "WHERE\n" +
                "    device.DEVICE_ID = ?\n";
        return jdbcTemplate.query(sql, unknownDeviceRowMapper).get(0);
    }
}
