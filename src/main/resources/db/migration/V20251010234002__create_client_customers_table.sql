CREATE TABLE client_customers
(
  id           UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

  -- 🔗 Foreign Keys
  client_id    UUID         NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  customer_id  UUID         NOT NULL REFERENCES customers (id) ON DELETE CASCADE,
  warehouse_id UUID         REFERENCES warehouse_addresses (id) ON DELETE SET NULL,

  -- ⚙️ Relationship Details
  status       VARCHAR(50)      DEFAULT 'active',

  -- 🧾 Audit Fields
  created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by   VARCHAR(100) NOT NULL,
  updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by   VARCHAR(100) NOT NULL
);

CREATE INDEX idx_client_customers_client_id ON client_customers (client_id);
CREATE INDEX idx_client_customers_customer_id ON client_customers (customer_id);
