DROP TABLE crypto IF EXISTS;

CREATE TABLE crypto  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    asset VARCHAR(20),
    quantity NUMERIC(128,32),
    price NUMERIC(128,32),
    historicPrice NUMERIC(128,32),
    performance NUMERIC(128,32)
);