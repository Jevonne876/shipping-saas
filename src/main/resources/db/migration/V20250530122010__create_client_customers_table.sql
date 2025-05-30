CREATE TABLE client_customers
(
  id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  client_id            UUID REFERENCES clients (id) ON DELETE CASCADE,
  customer_id          UUID REFERENCES customers (id) ON DELETE CASCADE,
  suite_code           VARCHAR(50) UNIQUE NOT NULL,
  pickup_location_id   UUID REFERENCES pickup_locations (id),
  warehouse_address_id UUID REFERENCES warehouse_addresses (id),
  user_type            VARCHAR(20)        NOT NULL,
  is_active            BOOLEAN          DEFAULT FALSE,
  created_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (client_id, customer_id)
);
