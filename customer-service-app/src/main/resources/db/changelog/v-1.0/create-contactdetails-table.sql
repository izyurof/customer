CREATE SEQUENCE IF NOT EXISTS contactdetails_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS ContactDetails (
    id BIGINT DEFAULT nextval('contactdetails_seq') PRIMARY KEY,
    email VARCHAR(50),
    telegram_id VARCHAR,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
    CONSTRAINT check_email CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$')
)
