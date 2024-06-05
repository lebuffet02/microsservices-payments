CREATE TABLE IF NOT EXISTS pagamentos (
                                          id BIGINT PRIMARY KEY,
                                          nome_produto VARCHAR(255),
                                          tipo VARCHAR(255),
                                          peso DOUBLE PRECISION,
                                          valor DOUBLE PRECISION,
                                          cartao VARCHAR(255),
                                          parcelas INTEGER,
                                          quantidade INTEGER,
                                          horario VARCHAR(255),
                                          status VARCHAR(255),
                                          is_pagamento_aceito BOOLEAN,
                                          usuario_id BIGINT,
                                          FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);


CREATE TABLE IF NOT EXISTS usuarios (
                                        id BIGINT PRIMARY KEY,
                                        nome_completo VARCHAR(255) NOT NULL,
                                        email VARCHAR(255) NOT NULL UNIQUE,
                                        cpf VARCHAR(255) UNIQUE,
                                        data_nascimento VARCHAR(255),
                                        endereco_id BIGINT,
                                        FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);

CREATE TABLE IF NOT EXISTS enderecos (
                                         id BIGINT PRIMARY KEY,
                                         rua VARCHAR(255),
                                         numero VARCHAR(50),
                                         cidade VARCHAR(100),
                                         estado VARCHAR(100),
                                         cep VARCHAR(20)
);