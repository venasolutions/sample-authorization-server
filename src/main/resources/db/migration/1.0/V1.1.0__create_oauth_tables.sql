CREATE TABLE oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  resource_ids            VARCHAR(255),
  client_secret           VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(255)
);

CREATE TABLE oauth_client_token (
  token_id          VARCHAR(255),
  token             BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255)
);

CREATE TABLE oauth_access_token (
  token_id          VARCHAR(255),
  token             BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  authentication    BYTEA,
  refresh_token     VARCHAR(255)
);

CREATE TABLE oauth_refresh_token (
  token_id       VARCHAR(255),
  token          BYTEA,
  authentication BYTEA
);

CREATE TABLE oauth_code (
  code           VARCHAR(255),
  authentication BYTEA
);

CREATE TABLE oauth_approvals (
  user_id          VARCHAR(255),
  client_id        VARCHAR(255),
  scope            VARCHAR(255),
  status           VARCHAR(10),
  expires_at       TIMESTAMP,
  last_modified_at TIMESTAMP
);