CREATE TABLE client_users
(
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id     UUID REFERENCES clients (id) ON DELETE CASCADE,
  first_name    VARCHAR(100)        NOT NULL,
  last_name     VARCHAR(100)        NOT NULL,
  email         VARCHAR(255) UNIQUE NOT NULL,
  password_hash TEXT                NOT NULL,
  role_id UUID REFERENCES roles(id) ON DELETE SET NULL,
  is_active     BOOLEAN          DEFAULT TRUE,
  created_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by    VARCHAR(100)        NOT NULL,
  updated_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by    VARCHAR(100)        NOT NULL

);

CREATE INDEX idx_client_users_client_id ON client_users (client_id);
CREATE INDEX idx_client_users_role_id   ON client_users (role_id);
