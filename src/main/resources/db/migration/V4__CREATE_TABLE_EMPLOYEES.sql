CREATE TABLE api.employees (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    storage_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_employees_users
        FOREIGN KEY (user_id) 
        REFERENCES api.users(id),

    CONSTRAINT fk_employees_storage
        FOREIGN KEY (storage_id) 
        REFERENCES inventory.storages(id)
)