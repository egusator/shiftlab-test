create table monitor_properties (
                                    monitor_id integer auto_increment primary key,
                                    diagonal smallint check (diagonal>0));

ALTER TABLE monitor_properties
    ADD FOREIGN KEY (monitor_id)
        REFERENCES device(DEVICE_ID) on delete cascade;
