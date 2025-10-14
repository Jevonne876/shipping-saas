CREATE TABLE shipments
(
  id               UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

  -- 🔗 Foreign keys
  client_id        UUID               NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  warehouse_id     UUID               REFERENCES warehouse (id) ON DELETE SET NULL,

  -- 📦 Shipment details
  shipment_number  VARCHAR(50) UNIQUE NOT NULL,        -- e.g., SHP-20251011-001
  flight_number    VARCHAR(50),
  vessel_name      VARCHAR(100),
  origin_city      VARCHAR(100),
  destination_city VARCHAR(100),
  departure_date   TIMESTAMP,
  arrival_date     TIMESTAMP,

  current_status   VARCHAR(50)      DEFAULT 'CREATED', -- CREATED, IN_TRANSIT, ARRIVED, DELIVERED
  total_weight_kg  DECIMAL(10, 2),
  total_packages   INT              DEFAULT 0,

  remarks          TEXT,

  -- 🧾 Audit
  created_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by       VARCHAR(100)       NOT NULL,
  updated_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by       VARCHAR(100)       NOT NULL,
  is_active        BOOLEAN          DEFAULT TRUE
);

CREATE INDEX idx_shipments_client ON shipments (client_id);
CREATE INDEX idx_shipments_status ON shipments (current_status);
