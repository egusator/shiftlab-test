package com.example.shiftlabtest;

import com.example.shiftlabtest.repository.impl.*;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommonAndAbstractRepositoryUnitTesting {
@Autowired
  private JdbcTemplate jdbcTemplate;
    private DeviceRowMapper deviceRowMapper = new DeviceRowMapper();

    private final CommonDeviceRepository commonDeviceRepository;
@Autowired
    public CommonAndAbstractRepositoryUnitTesting(CommonDeviceRepository commonDeviceRepository) {
        this.commonDeviceRepository = commonDeviceRepository;
    }

    @BeforeAll
  public void initTables() {
    jdbcTemplate.update("create table DEVICE\n" +
            "(\n" +
            "    DEVICE_ID         INTEGER primary key auto_increment,\n" +
            "    SERIAL_NUMBER     CHARACTER VARYING(100),\n" +
            "    PRICE             NUMERIC,\n" +
            "    QUANTITY_IN_STOCK INTEGER,\n" +
            "    MANUFACTURER_NAME VARCHAR (100)\n" +
            ");\n");

    jdbcTemplate.update("create table hard_disk_properties (\n" +
            "                                      hard_disk_id integer auto_increment primary key,\n" +
            "                                      capacity integer check (capacity>0));\n");

    jdbcTemplate.update("\n" +
            "create table monitor_properties (\n" +
            "                                    monitor_id integer auto_increment primary key,\n" +
            "                                    diagonal smallint check (diagonal>0));\n");

    jdbcTemplate.update("create table laptop_properties (\n" +
            "                                   laptop_id integer auto_increment primary key,\n" +
            "                                   size tinyint check size in (13,14,15,17))");

    jdbcTemplate.update("create table desktop_properties (\n" +
            "                                    desktop_id integer auto_increment primary key,\n" +
            "                                    form_factor tinyint check (form_factor in (1,2,3)));\n");
  }
  @BeforeEach
  public void setup() {

      jdbcTemplate.update(
              "ALTER TABLE hard_disk_properties\n" +
              "    ADD  CONSTRAINT fk_hard_disk FOREIGN KEY  (hard_disk_id)\n" +
              "        REFERENCES device(DEVICE_ID) on delete cascade;");
      jdbcTemplate.update(
              "ALTER TABLE MONITOR_PROPERTIES\n" +
                      "    ADD  CONSTRAINT fk_monitor FOREIGN KEY  (MONITOR_ID)\n" +
                      "        REFERENCES device(DEVICE_ID) on delete cascade;");
      jdbcTemplate.update(
              "ALTER TABLE DESKTOP_PROPERTIES\n" +
                      "    ADD  CONSTRAINT fk_desktop FOREIGN KEY  (DESKTOP_ID)\n" +
                      "        REFERENCES device(DEVICE_ID) on delete cascade;");
      jdbcTemplate.update(
              "ALTER TABLE LAPTOP_PROPERTIES\n" +
                      "    ADD  CONSTRAINT fk_laptop FOREIGN KEY  (LAPTOP_ID)\n" +
                      "        REFERENCES device(DEVICE_ID) on delete cascade;");
        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                      "                    '82382hfsdy23743', 1999, 10, 'Apple Macbook'\n" +
        ");\n" +
                                "insert into laptop_properties (laptop_id, size) values (1, 13);\n");

        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "'82rjjfj72347', 2999, 15, 'Acer PostMalone'); "+
                "insert into laptop_properties (laptop_id, size) values (2, 17);");
        jdbcTemplate.update("\n" +
                "insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "                 '3954jkefol', 4999, 20, 'Xiaomi Computer');\n" +
                "insert into desktop_properties (desktop_id, form_factor) values (3, 1);");

        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "                     'dkfkd382239', 2499, 15, 'Apple Macintosh');\n" +
                "insert into desktop_properties (desktop_id, form_factor) values (4, 3);\n");

        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "                            'asdsadgf434', 1499, 10, 'LG TOP');\n" +
                "insert into monitor_properties (monitor_id, diagonal) values (5, 65);");

        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "                      '23j43j483', 2499, 10, 'Samsung giga' );\n" +
                "insert into monitor_properties (monitor_id, diagonal) values (6, 88);");

        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "                        '4734hf23hsa', 999, 20, 'WD Blue' );\n" +
                "insert into hard_disk_properties (hard_disk_id, capacity) values (7, 1000);");
        jdbcTemplate.update("insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (\n" +
                "                       'o3efgrh341r', 899, 15, 'Samsung' );\n" +
                "insert into hard_disk_properties (hard_disk_id, capacity) values (8, 2000);");
  }

  @Test
  public void setDeviceQuantityById_ShouldUpdateDeviceQuantity() {
        commonDeviceRepository.setDeviceQuantityById(1, 1338);
        Integer updatedQuantity = jdbcTemplate.queryForObject("select QUANTITY_IN_STOCK from device where device_id = 1",
                                    Integer.class);
        Assert.assertEquals(Integer.valueOf(1338), updatedQuantity);
  }
    @Test
    public void increaseDeviceQuantityById_ShouldIncreaseDeviceQuantity() {
        commonDeviceRepository.increaseDeviceQuantityById(1, 5);
        Integer updatedQuantity = jdbcTemplate.queryForObject("select QUANTITY_IN_STOCK from device where device_id = 1",
                Integer.class);
        Assert.assertEquals(Integer.valueOf(15), updatedQuantity);
    }
    @Test
    public void decreaseDeviceQuantityById_ShouldIncreaseDeviceQuantity() {
        commonDeviceRepository.decreaseDeviceQuantityById(1, 5);
        Integer updatedQuantity = jdbcTemplate.queryForObject("select QUANTITY_IN_STOCK from device where device_id = 1",
                Integer.class);
        Assert.assertEquals(Integer.valueOf(5), updatedQuantity);
    }

    @Test
    public void setDevicePriceById_ShouldSetNewPrice() {
        commonDeviceRepository.setDevicePriceById(1, BigDecimal.TEN);
        BigDecimal updatedPrice = jdbcTemplate.queryForObject("select price from device where device_id = 1",
                BigDecimal.class);
        Assert.assertEquals(BigDecimal.TEN, updatedPrice);
    }

    @Test
    public void setDeviceSerialNumberById_ShouldSetNewSerialNumber() {
        commonDeviceRepository.setDeviceSerialNumberById(1, "newnum123");
        String updatedSerialNumber = jdbcTemplate.queryForObject("select SERIAL_NUMBER from device where device_id = 1",
                String.class);
        Assert.assertEquals("newnum123", updatedSerialNumber);
    }

    @Test
    public void addMainCharacteristics_ShouldInsertMainPropertiesToDeviceTable() {
        commonDeviceRepository.addMainCharacteristics("newnum123", BigDecimal.ONE, 1234,
                        "Apple Macbook");
        String updatedSerialNumber = jdbcTemplate.queryForObject("select SERIAL_NUMBER from device where device_id = 9",
                String.class);
        String updatedManufacturer = jdbcTemplate.queryForObject("select MANUFACTURER_NAME from device where device_id = 9",
                String.class);
        BigDecimal updatedPrice = jdbcTemplate.queryForObject("select price from device where device_id = 9",
                BigDecimal.class);
        Integer updatedQuantity = jdbcTemplate.queryForObject("select QUANTITY_IN_STOCK from device where device_id = 9",
                Integer.class);
        Assert.assertEquals("newnum123", updatedSerialNumber);
        Assert.assertEquals("Apple Macbook", updatedManufacturer);
        Assert.assertEquals(BigDecimal.ONE, updatedPrice);
        Assert.assertEquals(Integer.valueOf(1234), updatedQuantity);
    }
    @Test
    public void deleteDevice_ShouldDeleteDevice () {

        Integer oldSize = jdbcTemplate.query("select *, HARD_DISK_id is not null as isHardDisk from device join HARD_DISK_PROPERTIES on " +
                "(device.device_id = HARD_DISK_PROPERTIES.HARD_DISK_id)", deviceRowMapper).size();
        Assert.assertEquals(Integer.valueOf(2), oldSize);
        commonDeviceRepository.deleteDevice(8);
        Integer size = jdbcTemplate.query("select *, HARD_DISK_id is not null as isHardDisk from device join HARD_DISK_PROPERTIES on " +
                        "(device.device_id = HARD_DISK_PROPERTIES.HARD_DISK_id)", deviceRowMapper).size();
        Assert.assertEquals(Integer.valueOf(1), size);
    }

    @Test
    public void getDeviceById_ShouldReturnDeviceByID() {
        Assert.assertEquals("82382hfsdy23743", commonDeviceRepository.getDeviceById(1).getSerialNumber());
    }

    @AfterEach
  public void teardown(){
    jdbcTemplate.update("alter table MONITOR_PROPERTIES drop constraint fk_monitor;");
      jdbcTemplate.update("alter table DESKTOP_PROPERTIES drop constraint fk_desktop;");
      jdbcTemplate.update("alter table HARD_DISK_PROPERTIES drop constraint fk_hard_disk;");
      jdbcTemplate.update("alter table LAPTOP_PROPERTIES drop constraint fk_laptop;");

    jdbcTemplate.update("truncate table MONITOR_PROPERTIES RESTART IDENTITY;");
      jdbcTemplate.update("truncate table DESKTOP_PROPERTIES RESTART IDENTITY;");
      jdbcTemplate.update("truncate table LAPTOP_PROPERTIES RESTART IDENTITY;");
      jdbcTemplate.update("truncate table HARD_DISK_PROPERTIES RESTART IDENTITY;");
    jdbcTemplate.update("truncate table DEVICE RESTART IDENTITY;");
  }

  @AfterAll
  public void dropTables() {
      jdbcTemplate.update("drop table MONITOR_PROPERTIES;");
      jdbcTemplate.update("drop table DESKTOP_PROPERTIES;");
      jdbcTemplate.update("drop table LAPTOP_PROPERTIES;");
      jdbcTemplate.update("drop table HARD_DISK_PROPERTIES;");
      jdbcTemplate.update("drop table DEVICE;");
  }
}
