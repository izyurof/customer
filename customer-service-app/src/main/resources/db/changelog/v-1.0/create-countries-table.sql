CREATE SEQUENCE IF NOT EXISTS countries_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS countries (
    id BIGINT DEFAULT nextval('countries_seq') PRIMARY KEY,
    country_code VARCHAR(3) NOT NULL,
    name VARCHAR(60) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
    CONSTRAINT check_country_code CHECK ( country_code ~ '^[A-Z]{3}$' )
)
