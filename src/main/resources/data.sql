INSERT INTO PRODUTO(nome, valor) VALUES('Violão', '400.0');
INSERT INTO PRODUTO(nome, valor) VALUES('Guitarra', '1200.0');
INSERT INTO PRODUTO(nome, valor) VALUES('Teclado', '1400.0');
INSERT INTO PRODUTO(nome, valor) VALUES('Bateria', '1700.0');

INSERT INTO ROLE(nome) VALUES('ROLE_ADMIN');
INSERT INTO ROLE(nome) VALUES('ROLE_USER');

INSERT INTO USUARIO(nome, email, senha) VALUES('felipe', 'felipe@gadelha.com', '$2a$10$3tIO/6tg54t/jOKq1JWwZu05AH6JgZ1fp8Vkn0y5a89lasnCUiDSa');
INSERT INTO USUARIO(nome, email, senha) VALUES('isabella', 'isabella@monteiro.com', '$2a$10$3tIO/6tg54t/jOKq1JWwZu05AH6JgZ1fp8Vkn0y5a89lasnCUiDSa');
INSERT INTO USUARIO(nome, email, senha) VALUES('luis', 'luis@feitosa.com', '$2a$10$3tIO/6tg54t/jOKq1JWwZu05AH6JgZ1fp8Vkn0y5a89lasnCUiDSa');
INSERT INTO USUARIO(nome, email, senha) VALUES('gian', 'gian@carlos.com', '$2a$10$3tIO/6tg54t/jOKq1JWwZu05AH6JgZ1fp8Vkn0y5a89lasnCUiDSa');

INSERT INTO USUARIOS_ROLES(usuario_id, role_id) VALUES('1', 'ROLE_ADMIN');
INSERT INTO USUARIOS_ROLES(usuario_id, role_id) VALUES('2', 'ROLE_USER');
INSERT INTO USUARIOS_ROLES(usuario_id, role_id) VALUES('3', 'ROLE_ADMIN');
INSERT INTO USUARIOS_ROLES(usuario_id, role_id) VALUES('4', 'ROLE_ADMIN');
INSERT INTO USUARIOS_ROLES(usuario_id, role_id) VALUES('1', 'ROLE_USER');