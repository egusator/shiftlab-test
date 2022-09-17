package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class HardDiskRepository<HardDisk> extends AbstractDeviceRepository {

    private TransactionTemplate template;
    private final RowMapper<HardDisk> rowMapper;
    @Autowired
    public HardDiskRepository(JdbcTemplate jdbcTemplate, RowMapper<HardDisk> rowMapper,
                             TransactionTemplate template) {
        super(jdbcTemplate);
        this.template = template;
        this.rowMapper = rowMapper;
    }


    public void addDesktop(String serialNumber, BigDecimal price, int quantityInStock, int capacity) {
        template.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, Byte.valueOf((byte)4));
            final String addingFormFactorSql = "insert into HARD_DISK_PROPERTIES ( HARD_DISK_id, CAPACITY) values " +
                    "((select device_id from device where serial_number = ?), ?)";
            jdbcTemplate.update(addingFormFactorSql, preparedStatement -> {
                preparedStatement.setString(1,serialNumber);
                preparedStatement.setInt(2, capacity);
            });
            return null;
        });
    }
    @Override
    public List<HardDisk> getAll() {
        final String sql = "select * from device join HARD_DISK_PROPERTIES on " +
                "(device.device_id = HARD_DISK_PROPERTIES.HARD_DISK_id)";
        return jdbcTemplate.query(sql,rowMapper);
    }
}
