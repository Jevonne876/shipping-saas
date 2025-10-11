CREATE TABLE phone_numbers
(

  id           UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

  owner_id     UUID         NOT NULL,
  owner_type   VARCHAR(50)  NOT NULL CHECK ( owner_type IN ('CLIENT', 'CUSTOMER', 'STAFF', 'WAREHOUSE')),
  phone_number VARCHAR(30)  NOT NULL,
  phone_type   VARCHAR(50)  NOT NULL,
  is_primary   BOOLEAN          DEFAULT TRUE,

  created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by   VARCHAR(100) NOT NULL,
  updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by   VARCHAR(100) NOT NULL,
  is_active    BOOLEAN          DEFAULT TRUE
);

CREATE INDEX idx_phone_owner ON phone_numbers (owner_id, owner_type);
