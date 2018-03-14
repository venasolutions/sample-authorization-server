CREATE TABLE account
(
  id         BIGINT       NOT NULL
    CONSTRAINT pk_account
    PRIMARY KEY,
  username   VARCHAR(255) NOT NULL
    CONSTRAINT uk_account_username
    UNIQUE,
  email      VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL
);
