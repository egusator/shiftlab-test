-- auto-generated definition
create table DEVICE
(
    DEVICE_ID         INTEGER auto_increment
        primary key,
    SERIAL_NUMBER     CHARACTER VARYING(100),
    PRICE             NUMERIC,
    QUANTITY_IN_STOCK INTEGER,
    MANUFACTURER_NAME VARCHAR (100)
);

