CREATE TABLE clients
(
  id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name                 VARCHAR(255) UNIQUE                        NOT NULL,
  email                VARCHAR(255) UNIQUE                        NOT NULL,
  company_code         VARCHAR(50) UNIQUE                         NOT NULL, -- like FASTSHIP
  logo_url             TEXT UNIQUE                                NOT NULL,
  is_active            BOOLEAN          DEFAULT TRUE,
  created_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  created_by           VARCHAR(50)                                NOT NULL,
  updated_at           TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
  updated_by           VARCHAR                                    NOT NULL,
  time_zone            VARCHAR(100)     DEFAULT 'America/Jamaica' NOT NULL
);
