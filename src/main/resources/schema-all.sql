DROP TABLE countryCurrencies IF EXISTS;

CREATE TABLE countryCurrencies  (
    country_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    country_name VARCHAR(200),
    country_coin_name VARCHAR(200),
    country_coin_sigla VARCHAR(200),
    country_coin_value FLOAT(1)
);
