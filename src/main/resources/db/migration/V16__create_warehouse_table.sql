CREATE TABLE warehouse
(
  id           UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id    UUID UNIQUE        NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  name         VARCHAR(255)       NOT NULL,
  label        VARCHAR(50), -- e.g., "Miami Hub", "NY Facility"
  suite_prefix VARCHAR(20) UNIQUE NOT NULL,
  is_active    BOOLEAN          DEFAULT TRUE,
  created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by   VARCHAR(100)       NOT NULL,
  updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by   VARCHAR(100)       NOT NULL
);
