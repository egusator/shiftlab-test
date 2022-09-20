create table DEVICE
(
    DEVICE_ID         INTEGER primary key auto_increment,
    SERIAL_NUMBER     CHARACTER VARYING(100),
    PRICE             NUMERIC,
    QUANTITY_IN_STOCK INTEGER,
    MANUFACTURER_NAME VARCHAR (100)
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



insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                    '82382hfsdy23743', 1999, 10, 'Apple Macbook'
);
insert into laptop_properties (laptop_id, size) values (1, 13);

insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                    '82rjjfj72347', 2999, 15, 'Acer PostMalone'
                                                                                       );

insert into laptop_properties (laptop_id, size) values (2, 17);





insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                 '3954jkefol', 4999, 20, 'Xiaomi Computer');
insert into desktop_properties (desktop_id, form_factor) values (3, 1);



insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                     'dkfkd382239', 2499, 15, 'Apple Macintosh');
insert into desktop_properties (desktop_id, form_factor) values (4, 3);



insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                            'asdsadgf434', 1499, 10, 'LG TOP');
insert into monitor_properties (monitor_id, diagonal) values (5, 65);

insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                      '23j43j483', 2499, 10, 'Samsung giga' );
insert into monitor_properties (monitor_id, diagonal) values (6, 88);





insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                        '4734hf23hsa', 999, 20, 'WD Blue' );
insert into hard_disk_properties (hard_disk_id, capacity) values (7, 1000);



insert into DEVICE (SERIAL_NUMBER, PRICE, QUANTITY_IN_STOCK, MANUFACTURER_NAME) values (
                       'o3efgrh341r', 899, 15, 'Samsung' );
insert into hard_disk_properties (hard_disk_id, capacity) values (8, 2000);