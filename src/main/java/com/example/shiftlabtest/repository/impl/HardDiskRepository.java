package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class HardDiskRepository<HardDisk> extends AbstractDeviceRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HardDiskRepository(JdbcTemplate jdbcTemplate,
                             TransactionTemplate template) {
        super(jdbcTemplate, template);

        this.jdbcTemplate = jdbcTemplate;

    }


    public void addHardDisk(String serialNumber, BigDecimal price, int quantityInStock, String manufacturerName,
                           int capacity) {
        this.transactionTemplate.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, manufacturerName);
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
        final String sql = "select *, HARD_DISK_id is not null as isHardDisk from device join HARD_DISK_PROPERTIES on " +
                "(device.device_id = HARD_DISK_PROPERTIES.HARD_DISK_id)";
        return (List<HardDisk>) jdbcTemplate.query(sql, deviceRowMapper);
    }
}
