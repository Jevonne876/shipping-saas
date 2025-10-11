CREATE TABLE package_status_history
(
  id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  package_id UUID         NOT NULL REFERENCES packages (id) ON DELETE CASCADE,

  -- 📦 Status details
  status     VARCHAR(50)  NOT NULL, -- e.g., CREATED, AT_WAREHOUSE, SHIPPED, DELIVERED
  location   VARCHAR(255),
  remarks    TEXT,

  -- 🧾 Audit
  created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(100) NOT NULL
);

CREATE INDEX idx_package_status_history_package_id ON package_status_history (package_id);
CREATE INDEX idx_package_status_history_status ON package_status_history (status);
