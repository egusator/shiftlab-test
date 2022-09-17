package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class LaptopRepository<Laptop> extends AbstractDeviceRepository {

    private TransactionTemplate template;
    private final RowMapper<Laptop> rowMapper;

    @Autowired
    public LaptopRepository(JdbcTemplate jdbcTemplate, RowMapper<Laptop> rowMapper,
                             TransactionTemplate template) {
        super(jdbcTemplate);
        this.template = template;
        this.rowMapper = rowMapper;
    }

    public void addLaptop(String serialNumber, BigDecimal price, int quantityInStock, Byte size) {
        template.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, Byte.valueOf((byte)2));
            final String addingDiagonalSql = "insert into LAPTOP_PROPERTIES (LAPTOP_ID, SIZE) values " +
                    "((select device_id from device where serial_number = ?), ?)";
            jdbcTemplate.update(addingDiagonalSql, preparedStatement -> {
                preparedStatement.setString(1,serialNumber);
                preparedStatement.setByte(2, size);
            });
            return null;
        });
    }
    @Override
    public List<Laptop> getAll() {
        final String sql = "select * from device join LAPTOP_PROPERTIES on " +
                "(device.device_id = LAPTOP_PROPERTIES.laptop_id)";
        return jdbcTemplate.query(sql,rowMapper);
    }
}
