DROP TABLE IF EXISTS lendings;

CREATE TABLE lendings (
    id long primary key AUTO_INCREMENT,
    uuid uuid not null default random_uuid(),
    user_id text not null,
    days integer not null,
    date_delivery datetime,
    date_devolution datetime
);