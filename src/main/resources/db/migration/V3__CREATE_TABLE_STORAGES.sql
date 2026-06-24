CREATE SCHEMA IF NOT EXISTS inventory;

CREATE TABLE inventory.storages (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(10) NOT NULL,
    image_id BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_storages_users 
        FOREIGN KEY (user_id) 
        REFERENCES api.users(id),
    
    CONSTRAINT fk_storages_images
        FOREIGN KEY (image_id)
        REFERENCES api.images(id)
)