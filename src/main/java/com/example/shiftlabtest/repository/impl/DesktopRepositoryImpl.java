package com.example.shiftlabtest.repository.impl;

import com.example.shiftlabtest.repository.DesktopRepository;
import com.example.shiftlabtest.repository.model.Desktop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class DesktopRepositoryImpl extends DeviceRepositoryImpl implements DesktopRepository {

    private TransactionTemplate template;
    private final RowMapper<Desktop> rowMapper;
    @Autowired
    public DesktopRepositoryImpl(JdbcTemplate jdbcTemplate, RowMapper<Desktop> rowMapper,
                                 TransactionTemplate template) {
        super(jdbcTemplate);
        this.template = template;
        this.rowMapper = rowMapper;
    }

    @Override
    public void addDesktop(String serialNumber, BigDecimal price, int quantityInStock, Byte formFactor) {
        template.execute(status -> {
            final String mainCharacteristicsSql = "insert into device (serial_number, price, quantity_in_stock, device_type)" +
                    "values (?,?,?,1);";
            jdbcTemplate.update(mainCharacteristicsSql,preparedStatement -> {
                preparedStatement.setString(1, serialNumber);
                preparedStatement.setBigDecimal(2, price);
                preparedStatement.setInt(3, quantityInStock);

            });
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
    public List<Desktop> getAllDesktops () {
        final String sql = "select * from device join DESKTOP_PROPERTIES on " +
                "(device.device_id = DESKTOP_PROPERTIES.desktop_id)";
        return jdbcTemplate.query(sql, rowMapper);
    }


}
