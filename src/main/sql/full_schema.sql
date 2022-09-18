-- auto-generated definition
create table DEVICE
(
    DEVICE_ID         INTEGER auto_increment
        primary key,
    SERIAL_NUMBER     CHARACTER VARYING(100),
    PRICE             NUMERIC,
    QUANTITY_IN_STOCK INTEGER,
    MANUFACTURER_NAME VARCHAR (100),
    Device_type tinyint
);

create table hard_disk_properties (
                                      hard_disk_id integer auto_increment primary key,
                                      capacity integer check (capacity>0));

ALTER TABLE hard_disk_properties
    ADD FOREIGN KEY (hard_disk_id)
        REFERENCES device(DEVICE_ID) on delete cascade;

create table monitor_properties (
                                    monitor_id integer auto_increment primary key,
                                    diagonal smallint check (diagonal>0));

ALTER TABLE monitor_properties
    ADD FOREIGN KEY (monitor_id)
        REFERENCES device(DEVICE_ID) on delete cascade;

create table laptop_properties (
                                   laptop_id integer auto_increment primary key,
                                   size tinyint check size in (13,14,15,17)
);
ALTER TABLE laptop_properties
    ADD FOREIGN KEY (laptop_id)
        REFERENCES device(DEVICE_ID) on delete cascade;

create table desktop_properties (
                                    desktop_id integer auto_increment primary key,
                                    form_factor tinyint check (form_factor in (1,2,3)));
ALTER TABLE desktop_properties
    ADD FOREIGN KEY (desktop_id)
        REFERENCES device(DEVICE_ID) on delete cascade;