CREATE SEQUENCE IF NOT EXISTS customers_seq
    START WITH 1
    INCREMENT BY 15;

CREATE TABLE IF NOT EXISTS customers (
    id BIGINT DEFAULT nextval('customers_seq') PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    country_code_id BIGINT NOT NULL ,
    contact_details_id BIGINT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    FOREIGN KEY (country_code_id) REFERENCES countries(id),
    FOREIGN KEY (contact_details_id) REFERENCES contactdetails(id)
)
