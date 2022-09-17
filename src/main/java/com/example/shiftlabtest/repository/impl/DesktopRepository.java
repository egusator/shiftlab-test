package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class DesktopRepository<Desktop> extends AbstractDeviceRepository {

    private TransactionTemplate template;
    private final RowMapper<Desktop> rowMapper;
    @Autowired
    public DesktopRepository(JdbcTemplate jdbcTemplate, RowMapper<Desktop> rowMapper,
                             TransactionTemplate template) {
        super(jdbcTemplate);
        this.template = template;
        this.rowMapper = rowMapper;
    }


    public void addDesktop(String serialNumber, BigDecimal price, int quantityInStock, Byte formFactor) {
        template.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, Byte.valueOf((byte)1));
            final String addingFormFactorSql = "insert into DESKTOP_PROPERTIES (desktop_id, form_factor) values " +
                    "((select device_id from device where serial_number = ?), ?)";
            jdbcTemplate.update(addingFormFactorSql, preparedStatement -> {
                preparedStatement.setString(1,serialNumber);
                preparedStatement.setByte(2, formFactor);
            });
            return null;
        });
    }

    @Override
    public List<Desktop> getAll() {
        final String sql = "select * from device join DESKTOP_PROPERTIES on " +
                "(device.device_id = DESKTOP_PROPERTIES.desktop_id)";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
