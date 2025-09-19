CREATE TABLE plans
(
    id          SERIAL PRIMARY KEY,
    version     SMALLINT       NOT NULL DEFAULT 0,
    name        VARCHAR(255)   NOT NULL,
    description VARCHAR(1000)  NOT NULL,
    active      BOOLEAN        NOT NULL DEFAULT FALSE,
    currency    CHAR(3)        NOT NULL,
    amount      NUMERIC(15, 2) NOT NULL,
    created_at  TIMESTAMP(6)   NOT NULL,
    updated_at  TIMESTAMP(6)   NOT NULL,
    deleted_at  TIMESTAMP(6)
);