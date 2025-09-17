-- Aqui podemos criar instruções SQL DML (Insert, Update, Delete)
-- Podem haver mais uma instrução, separadas por ponto e vírgula (;)

INSERT INTO regras (minimo_pets, maximo_pets)
VALUES
(1, 4);

INSERT INTO raca (codigo, nome)
VALUES
('VIRA_LATA', 'Vira-lata'),
('SIAMES', 'Siamês'),
('PERSA', 'Persa'),
('BULDOGUE', 'Bulldogue'),
('PASTOR_ALEMAO', 'Pastor Alemão');


INSERT INTO pet (nome_pet, nome_dono, especie, raca_codigo, email_dono, peso, altura, nascimento, validade_chip, cpf_dono, telefone_dono, ativo) VALUES
('Fido', 'João Silva', 'Cachorro', 'VIRA_LATA', 'joao.silva@example.com', 15.5, 0.5, '2020-01-15', '2030-01-15', '12345678901', '(11) 98765-4321', true),
('Miau', 'Maria Souza', 'Gato', 'SIAMES', 'maria.souza@example.com', 4.2, 0.25, '2021-05-20', '2031-05-20', '10987654321', '(21) 91234-5678', true),
('Piu', 'Carlos Pereira', 'Pássaro', 'PERSA', 'carlos.pereira@example.com', 0.1, 0.1, '2022-02-10', '2032-02-10', '56789012345', '(31) 99999-8888', false),
('Rex', 'Ana Costa', 'Cachorro', 'PASTOR_ALEMAO', 'ana.costa@example', 30.0, 0.7, '2019-11-30', '2029-11-30', '98765432109', '(41) 98888-7777', true);