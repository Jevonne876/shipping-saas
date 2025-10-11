CREATE TABLE customers
(
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  first_name    VARCHAR(100) NOT NULL,
  last_name     VARCHAR(100) NOT NULL,
  email         VARCHAR(255),
  password_hash TEXT         NOT NULL,
  created_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by    VARCHAR(100) NOT NULL,
  updated_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by    VARCHAR(100) NOT NULL
);
