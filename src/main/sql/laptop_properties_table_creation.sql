create table laptop_properties (
                                   laptop_id integer auto_increment primary key,
                                   size tinyint check size in (13,14,15,17)
);
ALTER TABLE laptop_properties
    ADD FOREIGN KEY (laptop_id)
        REFERENCES device(DEVICE_ID) on delete cascade;
