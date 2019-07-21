drop table parking_lot;
create table parking_lot
(
    id       int auto_increment primary key not null,
    capacity integer check (capacity >= 0),
    name     varchar(30) unique,
    position varchar(255)
);
create table parking_order
(
    id             bigint primary key not null,
    carNumber         varchar(255),
    closed_time    date,
    created_time   date,
    orderNumber       varchar(255),
    status         int default 1,
    parkingLotId bigint, primary key (id)
);

alter table parking_order
    add  constraint parking_lot_id_fk
        foreign key(parkingLotId)
              references parking_lot;

