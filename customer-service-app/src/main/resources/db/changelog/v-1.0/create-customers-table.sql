CREATE SEQUENCE IF NOT EXISTS customers_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS customers (
    id BIGINT DEFAULT nextval('customers_seq') PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    country_code_id BIGINT NOT NULL ,
    contact_details_id BIGINT NOT NULL,
    createdAt TIMESTAMP WITH TIME ZONE NOT NULL,
    updatedAt TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (country_code_id) REFERENCES countries(id),
    FOREIGN KEY (contact_details_id) REFERENCES contactdetails(id)
)
