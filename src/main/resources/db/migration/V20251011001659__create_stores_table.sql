CREATE TABLE stores
(
  id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id            UUID         NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  name                 VARCHAR(100) NOT NULL,
  code                 VARCHAR(20)  NOT NULL, -- e.g. KNG, MBJ
  is_active            BOOLEAN          DEFAULT TRUE,
  created_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by           VARCHAR(100) NOT NULL,
  updated_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by           VARCHAR(100) NOT NULL,
  CONSTRAINT uq_client_store_code UNIQUE (client_id, code)
);
