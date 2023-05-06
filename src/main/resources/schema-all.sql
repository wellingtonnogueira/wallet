DROP TABLE crypto IF EXISTS;

CREATE TABLE crypto  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    asset VARCHAR(20),
    quantity NUMERIC,
    price NUMERIC,
    historicPrice NUMERIC,
    performance NUMERIC
);