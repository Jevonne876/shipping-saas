CREATE TABLE permissions
(
  id          UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name        VARCHAR(100) UNIQUE NOT NULL, -- e.g., MANAGE_USERS, VIEW_REPORTS
  description TEXT
);
