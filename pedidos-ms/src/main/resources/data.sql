CREATE TABLE IF NOT EXISTS pedidos (
    id           BIGINT       NOT NULL,
    email        VARCHAR(255) NOT NULL,
    nome_produto VARCHAR(255) NOT NULL,
    tipo         VARCHAR(255) NOT NULL,
    peso_kg      DOUBLE PRECISION,
    valor        DOUBLE PRECISION,
    status       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_pedidos PRIMARY KEY (id)
);

ALTER TABLE pedidos
    ADD CONSTRAINT uc_pedidos_email UNIQUE (email);