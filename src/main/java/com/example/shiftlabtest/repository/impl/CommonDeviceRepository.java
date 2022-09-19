package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.mapper.DeviceRowMapper;
import com.example.shiftlabtest.repository.model.AbstractDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class CommonDeviceRepository extends AbstractDeviceRepository<AbstractDevice> {

    private final JdbcTemplate jdbcTemplate;
    protected final TransactionTemplate transactionTemplate;

    private static final DeviceRowMapper deviceRowMapper = new DeviceRowMapper();

    @Autowired
    public CommonDeviceRepository(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        super(jdbcTemplate, transactionTemplate);

        this.jdbcTemplate = jdbcTemplate;
        this.transactionTemplate = transactionTemplate;
    }

    //FIXME make read all records here
    @Override
    public List<AbstractDevice> getAll() {
        return null;
    }

    //FIXME add boolean values for device type identification
    public AbstractDevice getDeviceById(int deviceId) {
        final String sql = "SELECT *, monitor_id is not null as isMonitor, DESKTOP_ID is not null as isDesktop,  LAPTOP_id is not null as isLaptop, hard_disk_ID is not null as isHardDisk\n" +
                "FROM device\n" +
                "         LEFT JOIN HARD_DISK_PROPERTIES HDP on DEVICE.DEVICE_ID = HDP.HARD_DISK_ID\n" +
                "         LEFT JOIN DESKTOP_PROPERTIES DP on DEVICE.DEVICE_ID = DP.DESKTOP_ID\n" +
                "         LEFT JOIN LAPTOP_PROPERTIES LP on DEVICE.DEVICE_ID = LP.LAPTOP_ID\n" +
                "         LEFT JOIN MONITOR_PROPERTIES MP on DEVICE.DEVICE_ID = MP.MONITOR_ID\n" +
                "WHERE\n" +
                "    device.DEVICE_ID = ?\n";
        return jdbcTemplate.query(sql, preparedStatement -> {
            preparedStatement.setInt(1, deviceId);
        }, deviceRowMapper).get(0);
    }
}
