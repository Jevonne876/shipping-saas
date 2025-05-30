CREATE TABLE warehouse_addresses
(
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id     UUID REFERENCES clients (id) ON DELETE CASCADE,
  label         VARCHAR(50), -- e.g., "Miami Hub", "NY Facility"
  address_line1 TEXT               NOT NULL,
  address_line2 TEXT,
  suite_prefix  VARCHAR(20) UNIQUE NOT NULL,
  city          VARCHAR(100),
  state         VARCHAR(100),
  postal_code   VARCHAR(20),
  country       VARCHAR(100)     DEFAULT 'USA',
  is_active     BOOLEAN          DEFAULT TRUE,
  created_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);


