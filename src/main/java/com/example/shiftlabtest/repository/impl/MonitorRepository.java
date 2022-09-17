package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class MonitorRepository<Monitor> extends AbstractDeviceRepository  {
    private TransactionTemplate template;

    private final RowMapper<Monitor> rowMapper;
    @Autowired
    public MonitorRepository(JdbcTemplate jdbcTemplate,
                             TransactionTemplate template, RowMapper<Monitor> rowMapper) {
        super(jdbcTemplate);
        this.template = template;
        this.rowMapper = rowMapper;
    }


    public void addMonitor(String serialNumber, BigDecimal price, int quantityInStock, int diagonal) {
        template.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, Byte.valueOf((byte)3));
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
        return jdbcTemplate.query(sql,rowMapper);
    }
}
