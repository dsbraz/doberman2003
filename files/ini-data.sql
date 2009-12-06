-- SYS_SEQUENCES --
insert into sys_sequences
values (1,'seq_sequences', 8);
insert into sys_sequences
values (2,'seq_logs', 0);
insert into sys_sequences
values (3,'seq_users', 1);
insert into sys_sequences
values (4,'seq_permissions', 7);
insert into sys_sequences
values (5,'seq_domains', 3);
insert into sys_sequences
values (6,'seq_certificates', 1);
insert into sys_sequences
values (7,'seq_permissions_domains', 14);
insert into sys_sequences
values (8,'seq_permissions_certificates', 7);
select * from sys_sequences;

-- TB_USERS --
insert into tb_users
values (1, 'MASTER', '6woZF5diTdOkj6aB0wYSEg==');
select * from tb_users;

-- TB_PERMISSIONS --
insert into tb_permissions
values (1, 'Login', 'Login');
insert into tb_permissions
values (2, 'Cadastro', 'Cadastro');
insert into tb_permissions
values (3, 'Pedido', 'Pedido');
insert into tb_permissions
values (4, 'Estoque', 'Estoque');
insert into tb_permissions
values (5, 'Compras', 'Compras');
insert into tb_permissions
values (6, 'Relatorios', 'Relatorios');
insert into tb_permissions
values (7, 'Administracao', 'Administracao');
select * from tb_permissions;

-- TB_DOMAINS --
insert into tb_domains
values (1, 'Administrador');
insert into tb_domains
values (2, 'Caixa');
insert into tb_domains
values (3, 'Fornecedor');
select * from tb_domains;

-- TB_CERTIFICATE --
insert into tb_certificates
values(1,'aUqwleG79D3L1jVv34xp+A==', 1);
select * from tb_certificates;

-- TB_PERMISSIONS_CERTIFICATES --
insert into tb_permissions_certificates
values (1, 1, 1);
insert into tb_permissions_certificates
values (2, 1, 2);
insert into tb_permissions_certificates
values (3, 1, 3);
insert into tb_permissions_certificates
values (4, 1, 4);
insert into tb_permissions_certificates
values (5, 1, 5);
insert into tb_permissions_certificates
values (6, 1, 6);
insert into tb_permissions_certificates
values (7, 1, 7);
select * from tb_permissions_certificates;

-- TB_PERMISSIONS_DOMAINS --
insert into tb_permissions_domains
values (1, 1, 1);
insert into tb_permissions_domains
values (2, 1, 2);
insert into tb_permissions_domains
values (3, 1, 3);
insert into tb_permissions_domains
values (4, 1, 4);
insert into tb_permissions_domains
values (5, 1, 5);
insert into tb_permissions_domains
values (6, 1, 6);
insert into tb_permissions_domains
values (7, 1, 7);
insert into tb_permissions_domains
values (8, 2, 1);
insert into tb_permissions_domains
values (9, 2, 2);
insert into tb_permissions_domains
values (10, 2, 3);
insert into tb_permissions_domains
values (11, 2, 4);
insert into tb_permissions_domains
values (12, 2, 5);
insert into tb_permissions_domains
values (13, 3, 1);
insert into tb_permissions_domains
values (14, 3, 4);
select * from tb_permissions_domains;