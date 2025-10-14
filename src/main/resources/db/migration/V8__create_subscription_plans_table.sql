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

-- Liquibase formatted SQL

-- changeset jevonne.laing:20251011164000-seed-subscription-plans
INSERT INTO subscription_plans (id,
                                code,
                                name,
                                description,
                                monthly_price,
                                annual_price,
                                created_by,
                                updated_by)
VALUES ('ad468a5e-ff85-45c2-b705-c14bfd77295d', 'STARTER', 'Starter Plan',
        'Basic plan with essential shipping features for small businesses.',
        100.00, 960.00, 'system', 'system'),

       ('bd568a5e-aa45-45c2-b705-c14bfd77295d', 'PRO', 'Professional Plan',
        'Advanced plan with more automation and integrations for growing companies.',
        500.00, 4800.00, 'system', 'system'),

       ('cd668a5e-bb85-45c2-b705-c14bfd77295d', 'ENTERPRISE', 'Enterprise Plan',
        'Full-featured plan with premium support, scalability, and enterprise-level tools.',
        1000.00, 9600.00, 'system', 'system');

