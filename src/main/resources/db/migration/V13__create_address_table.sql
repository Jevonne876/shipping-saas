CREATE TABLE addresses
(
  id               UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

  -- Polymorphic owner reference
  addressable_id   UUID         NOT NULL,
  addressable_type VARCHAR(50)  NOT NULL CHECK (addressable_type IN ('CLIENT', 'CUSTOMER', 'STAFF', 'WAREHOUSE')),

  -- Address details
  street_address   VARCHAR(255) NOT NULL,
  state_or_parish  VARCHAR(100) NOT NULL,
  city             VARCHAR(255) NOT NULL,
  postal_code      VARCHAR(255),
  country          VARCHAR(255) NOT NULL,

  -- Audit fields
  created_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by       VARCHAR(100) NOT NULL,
  updated_at       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by       VARCHAR(100) NOT NULL,
  is_active        BOOLEAN          DEFAULT TRUE
);
-- 🔍 Optimized index for owner lookups
CREATE INDEX idx_addresses_owner ON addresses (addressable_id, addressable_type);

