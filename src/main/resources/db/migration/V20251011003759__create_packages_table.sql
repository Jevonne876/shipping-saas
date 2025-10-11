CREATE TABLE packages
(
  id                UUID PRIMARY KEY             DEFAULT uuid_generate_v4(),

  -- 🔗 Foreign Keys
  client_id         UUID                NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  customer_id       UUID                NOT NULL REFERENCES customers (id) ON DELETE CASCADE,
  warehouse_id      UUID                REFERENCES warehouse_addresses (id) ON DELETE SET NULL,
  store_id          UUID                REFERENCES stores (id) ON DELETE SET NULL,

  -- 📦 Package Details
  tracking_number   VARCHAR(100) UNIQUE NOT NULL,                   -- e.g. QCK-MIA-000234
  description       TEXT,
  declared_value    DECIMAL(12, 2),
  weight_kg         DECIMAL(10, 2),
  length_cm         DECIMAL(10, 2),
  width_cm          DECIMAL(10, 2),
  height_cm         DECIMAL(10, 2),
  total_volume_cm3  DECIMAL(15, 2) GENERATED ALWAYS AS (length_cm * width_cm * height_cm) STORED,

  -- 🚚 Current Shipment Info
  current_status    VARCHAR(50)         NOT NULL DEFAULT 'CREATED', -- CREATED, AT_WAREHOUSE, SHIPPED, IN_TRANSIT, DELIVERED, RETURNED
  last_location     VARCHAR(255),
  estimated_arrival TIMESTAMP,
  delivered_at      TIMESTAMP,

  -- 🧾 Audit
  created_at        TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
  created_by        VARCHAR(100)        NOT NULL,
  updated_at        TIMESTAMP                    DEFAULT CURRENT_TIMESTAMP,
  updated_by        VARCHAR(100)        NOT NULL,
  is_active         BOOLEAN                      DEFAULT TRUE
);

-- ⚙️ Helpful indexes
CREATE INDEX idx_packages_client_customer ON packages (client_id, customer_id);
CREATE INDEX idx_packages_tracking_number ON packages (tracking_number);
CREATE INDEX idx_packages_status ON packages (current_status);


