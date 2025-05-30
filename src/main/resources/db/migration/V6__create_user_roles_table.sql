CREATE TABLE user_roles
(
  id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id   UUID        NOT NULL,
  user_type VARCHAR(50) NOT NULL,
  role_id   UUID REFERENCES roles (id),
  UNIQUE (user_id, user_type)
);
