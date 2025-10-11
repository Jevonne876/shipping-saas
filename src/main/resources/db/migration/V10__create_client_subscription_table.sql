CREATE TABLE client_subscriptions
(
  id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

  -- 🔗 Foreign keys
  client_id            UUID NOT NULL REFERENCES clients (id) ON DELETE CASCADE,
  subscription_plan_id UUID NOT NULL REFERENCES subscription_plans (id) ON DELETE RESTRICT,

  -- 🕒 Subscription details
  start_date           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  end_date             TIMESTAMP,
  is_active            BOOLEAN          DEFAULT TRUE,

  -- 💰 Optional billing/tracking
  price_at_signup      DECIMAL(10, 2),
  billing_cycle        VARCHAR(20)      DEFAULT 'monthly', -- monthly, yearly, etc.
  notes                TEXT,

  -- 🧾 Audit
  created_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by           VARCHAR(100),
  updated_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by           VARCHAR(100)
);

CREATE UNIQUE INDEX ux_active_subscription_per_client
  ON client_subscriptions (client_id) WHERE is_active = TRUE;
