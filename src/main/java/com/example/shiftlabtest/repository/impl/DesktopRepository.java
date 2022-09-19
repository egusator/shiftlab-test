package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class DesktopRepository<Desktop> extends AbstractDeviceRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public DesktopRepository(JdbcTemplate jdbcTemplate,
                             TransactionTemplate template) {
        super(jdbcTemplate, template);
        this.jdbcTemplate = jdbcTemplate;

    }


    public void addDesktop(String serialNumber, BigDecimal price, int quantityInStock,
                           String manufacturerName, Byte formFactor) {
        this.transactionTemplate.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, manufacturerName, Byte.valueOf((byte)1));
            final String addingFormFactorSql = "insert into DESKTOP_PROPERTIES (desktop_id,  form_factor) values " +
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
        final String sql = "select *, desktop_id is not null as isDesktop" +
                " from device join DESKTOP_PROPERTIES on " +
                "(device.device_id = DESKTOP_PROPERTIES.desktop_id)";
        return (List<Desktop>) jdbcTemplate.query(sql, deviceRowMapper);
    }
}
