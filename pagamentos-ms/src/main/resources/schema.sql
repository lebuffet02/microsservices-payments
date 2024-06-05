INSERT INTO enderecos (id, logradouro, numero, cidade, estado, cep)
VALUES (1, 'Rua Exemplo', '123', 'Cidade Exemplo', 'Estado Exemplo', '12345-678');

INSERT INTO usuarios (id, email, nome_completo, cpf, data_nascimento, endereco_id)
VALUES (1, 'usuario@example.com', 'Nome Completo', '12345678900', '2000-01-01', 1);

INSERT INTO pagamentos (id, nome_produto, tipo, peso, valor, cartao, parcelas, quantidade, horario, status, is_pagamento_aceito, usuario_id)
VALUES (1, 'Produto A', 'Tipo A', 1.5, 100.0, 'Cartao A', 3, 10, '2024-05-26T12:00:00', 'APROVADO', FALSE, 1);


ALTER TABLE pagamentos
    ADD CONSTRAINT FK_PAGAMENTOS_ON_USUARIO FOREIGN KEY (usuario_id) REFERENCES usuarios (id);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_cpf UNIQUE (cpf);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_email UNIQUE (email);

ALTER TABLE usuarios
    ADD CONSTRAINT FK_USUARIOS_ON_ENDERECO FOREIGN KEY (endereco_id) REFERENCES enderecos (id);