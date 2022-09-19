package com.example.shiftlabtest.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;
@Repository
public class LaptopRepository<Laptop> extends AbstractDeviceRepository {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public LaptopRepository(JdbcTemplate jdbcTemplate,
                             TransactionTemplate template) {
        super(jdbcTemplate, template);

        this.jdbcTemplate = jdbcTemplate;

    }

    public void addLaptop(String serialNumber, BigDecimal price, int quantityInStock, String manufacturerName,
                          Byte size) {
        this.transactionTemplate.execute(status -> {
            addMainCharacteristics(serialNumber, price, quantityInStock, manufacturerName, Byte.valueOf((byte)2));
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
        final String sql = "select *, Laptop_ID is not null as isLaptop from device join LAPTOP_PROPERTIES on " +
                "(device.device_id = LAPTOP_PROPERTIES.laptop_id)";
        return (List<Laptop>) jdbcTemplate.query(sql, deviceRowMapper);
    }
}
