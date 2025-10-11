CREATE TABLE warehouse_addresses
(
  id           UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id    UUID UNIQUE        NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  label        VARCHAR(50), -- e.g., "Miami Hub", "NY Facility"
  suite_prefix VARCHAR(20) UNIQUE NOT NULL,
  city         VARCHAR(100),
  is_active    BOOLEAN          DEFAULT TRUE,
  created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by   VARCHAR(100)       NOT NULL,
  updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by   VARCHAR(100)       NOT NULL
);
