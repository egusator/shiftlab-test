package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.MonitorRepository;
import com.example.shiftlabtest.repository.model.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class MonitorRepositoryImpl extends DeviceRepositoryImpl implements MonitorRepository {
    private TransactionTemplate template;

    private final RowMapper<Monitor> rowMapper;
    @Autowired
    public MonitorRepositoryImpl(JdbcTemplate jdbcTemplate,
                                 TransactionTemplate template, RowMapper<Monitor> rowMapper) {
        super(jdbcTemplate);
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public void addMonitor(String serialNumber, BigDecimal price, int quantityInStock, int diagonal) {
        template.execute(status -> {
            final String mainCharacteristicsSql = "insert into device (serial_number, price, quantity_in_stock, device_type)" +
                    "values (?,?,?,3);";
            jdbcTemplate.update(mainCharacteristicsSql,preparedStatement -> {
                preparedStatement.setString(1, serialNumber);
                preparedStatement.setBigDecimal(2, price);
                preparedStatement.setInt(3, quantityInStock);

            });
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
    public List<Monitor> getAllMonitors() {
        final String sql = "select * from device join MONITOR_PROPERTIES on " +
                "(device.device_id = MONITOR_PROPERTIES.monitor_id)";
        return jdbcTemplate.query(sql,rowMapper);
    }
}
