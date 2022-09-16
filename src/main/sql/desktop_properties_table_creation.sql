create table desktop_properties (
                                    desktop_id integer auto_increment primary key,
                                    type tinyint check (type in (1,2,3)));
ALTER TABLE desktop_properties
    ADD FOREIGN KEY (desktop_id)
        REFERENCES device(DEVICE_ID);