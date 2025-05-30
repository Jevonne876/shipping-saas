CREATE TABLE client_users
(
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id     UUID REFERENCES clients (id) ON DELETE CASCADE,
  first_name    VARCHAR(100)        NOT NULL,
  last_name     VARCHAR(100)        NOT NULL,
  email         VARCHAR(255) UNIQUE NOT NULL,
  phone_number  VARCHAR(30)         NOT NULL,
  password_hash TEXT                NOT NULL,
  user_type     VARCHAR(20)         NOT NULL,
  role_id       UUID                NOT NULL,
  is_active     BOOLEAN          DEFAULT TRUE,
  created_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);
