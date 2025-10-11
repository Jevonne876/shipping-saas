-- ===========================================
-- Migration: Add store_id and shipment_id columns
-- Author: Jevonne Laing
-- Date: 2025-10-10
-- Description:
--   1️⃣ Add store_id column to client_customers table
--   2️⃣ Add shipment_id column to packages table
-- ===========================================

-- 🔹 1. Add store reference to client_customers
ALTER TABLE client_customers
  ADD COLUMN IF NOT EXISTS store_id UUID REFERENCES stores (id) ON DELETE SET NULL;

CREATE INDEX IF NOT EXISTS idx_client_customers_store_id
  ON client_customers (store_id);

-- 🔹 2. Add shipment reference to packages
ALTER TABLE packages
  ADD COLUMN IF NOT EXISTS shipment_id UUID REFERENCES shipments (id) ON DELETE SET NULL;

CREATE INDEX IF NOT EXISTS idx_packages_shipment_id
  ON packages (shipment_id);
