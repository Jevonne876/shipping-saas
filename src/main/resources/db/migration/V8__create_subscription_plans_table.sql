CREATE TABLE subscription_plans
(
  id            UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

  -- 🧩 Identifiers
  code          VARCHAR(50) UNIQUE NOT NULL
    CHECK (code IN ('STARTER', 'PRO', 'ENTERPRISE')), -- e.g. 'STARTER', 'PRO', 'ENTERPRISE'
  name          VARCHAR(100)       NOT NULL,          -- Display name (Starter Plan)

  -- 📝 Description & details
  description   TEXT,

  -- 💰 Pricing structure
  monthly_price DECIMAL(10, 2)     NOT NULL,
  annual_price  DECIMAL(10, 2),

  -- ⚙️ Status
  is_active     BOOLEAN          DEFAULT TRUE,

  -- 🕒 Audit
  created_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by    VARCHAR(255)       NOT NULL,
  updated_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by    VARCHAR(255)       NOT NULL
);
