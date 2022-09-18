package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MonitorRepository<Monitor> extends AbstractDeviceRepository  {

    private final RowMapper<Monitor> monitorRowMapper;
    @Autowired
    public MonitorRepository(JdbcTemplate jdbcTemplate,
                             TransactionTemplate template, RowMapper<Monitor> monitorRowMapper) {
        super(jdbcTemplate, template);

        this.monitorRowMapper = monitorRowMapper;
    }


    public void addMonitor(String serialNumber, BigDecimal price, int quantityInStock, String manufacturerName, int diagonal) {
        this.transactionTemplate.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, manufacturerName, Byte.valueOf((byte)3));
            final String addingDiagonalSql = "insert into MONITOR_PROPERTIES (MONITOR_ID, DIAGONAL) values " +
                    "((select device_id from device where serial_number = ?), ?)";
            jdbcTemplate.update(addingDiagonalSql, preparedStatement -> {
                preparedStatement.setString(1,serialNumber);
                preparedStatement.setInt(2, diagonal);
            });
            return null;
        });
    }
    @Override
    public List<Monitor>getAll() {
        final String sql = "select * from device join MONITOR_PROPERTIES on " +
                "(device.device_id = MONITOR_PROPERTIES.monitor_id)";
        return jdbcTemplate.query(sql, monitorRowMapper);
    }
}
