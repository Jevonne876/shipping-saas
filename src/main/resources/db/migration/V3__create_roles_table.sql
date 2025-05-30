CREATE TABLE roles
(
  id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name        VARCHAR(50) UNIQUE NOT NULL, -- e.g., SUPER_ADMIN, CLIENT_ADMIN, CUSTOMER
  description TEXT
);
