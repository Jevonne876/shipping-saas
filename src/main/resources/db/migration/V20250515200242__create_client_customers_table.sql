CREATE TABLE customers
(
  id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  first_name           VARCHAR(100)       NOT NULL,
  last_name            VARCHAR(100)       NOT NULL,
  email                VARCHAR(255),
  password_hash        TEXT               NOT NULL,
  phone_number         VARCHAR(30),
  address              TEXT,
  city                 VARCHAR(100),
  state                VARCHAR(100),
  postal_code          VARCHAR(20),
  country              VARCHAR(100),
  created_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);
