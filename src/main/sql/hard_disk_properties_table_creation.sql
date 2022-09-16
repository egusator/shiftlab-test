create table hard_disk_properties (
                                      hard_disk_id integer auto_increment primary key,
                                      capacity integer check (capacity>0));

ALTER TABLE hard_disk_properties
    ADD FOREIGN KEY (hard_disk_id)
        REFERENCES device(DEVICE_ID);
