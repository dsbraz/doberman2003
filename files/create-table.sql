-- SYS_SEQUENCES --
create table sys_sequences (
  id int2 not null,
  name varchar(255) not null,
  value int2 not null
);

-- SYS_LOGS --
create table sys_logs (
  id int2 not null,
  message varchar(255) not null,
  time timestamp not null
);

-- TB_PERMISSIONS --
create table tb_permissions (
  id int2 not null,
  name varchar(255) not null,
  module varchar(255) not null
);

-- TB_CERTIFICATES --
create table tb_certificates (
  id int2 not null,
  key varchar(32) not null,
  owner int2 not null
);

-- TB_DOMAINS --
create table tb_domains (
  id int2 not null,
  name varchar(25) not null
);

-- TB_USERS --
create table tb_users (
  id int2 not null,
  name varchar(60) not null,
  password varchar(100)
);

-- TB_PERMISSIONS_CERTIFICATES --
create table tb_permissions_certificates (
  id int2 not null,
  certificate int2 not null,
  permission int2 not null
);

-- TB_PERMISSIONS_DOMAINS --
create table tb_permissions_domains (
  id int2 not null,
  domain int2 not null,
  permission int2 not null
);

-- SYS_SEQUENCES --
alter table sys_sequences add constraint pk_id_sequences primary key (id);
alter table sys_sequences add constraint uk_name_sequences unique (name);

-- SYS_LOGS --
alter table sys_logs add constraint pk_id_log primary key (id);

-- TB_PERMISSIONS --
alter table tb_permissions add constraint pk_id_permissions primary key (id);
alter table tb_permissions add constraint uk_name_module_permission unique (name, module);
-- TB_USERS --
alter table tb_users add constraint pk_id_user primary key (id);
alter table tb_users add constraint uk_login_user unique (name);

-- TB_CERTIFICATES --
alter table tb_certificates add constraint pk_id_certificate primary key (id);
alter table tb_certificates add constraint fk_certificate_owner_user_id foreign key (owner) references public.tb_users (id) on update restrict on delete restrict;
alter table tb_certificates add constraint uk_key_certificate unique (key);
alter table tb_certificates add constraint uk_owner_certificate unique (owner);

-- TB_DOMAINS --
alter table tb_domains add constraint pk_id_domain primary key (id);
alter table tb_domains add constraint uk_name_domain unique (name);

-- TB_PERMISSIONS_CERTIFICATES --
alter table tb_permissions_certificates add constraint pk_id_certificate_permission primary key (id);
alter table tb_permissions_certificates add constraint fk_certificate_permission_permission_id foreign key (permission) references public.tb_permissions (id) on update restrict on delete restrict;
alter table tb_permissions_certificates add constraint fk_certificate_permission_certificate_id foreign key (certificate) references public.tb_certificates (id) on update restrict on delete restrict;
alter table tb_permissions_certificates add constraint uk_certificate_permission_certificate_permission unique (certificate, permission);

-- TB_PERMISSIONS_DOMAINS --
alter table tb_permissions_domains add constraint pk_id_domain_permission primary key (id);
alter table tb_permissions_domains add constraint fk_domain_permission_permission_permission_id foreign key (permission) references public.tb_permissions (id) on update restrict on delete restrict;
alter table tb_permissions_domains add constraint fk_domain_permission_domain_domain_id foreign key (domain) references public.tb_domains (id) on update restrict on delete restrict;
alter table tb_permissions_domains add constraint uk_domain_permission_domain_permission unique (domain, permission);
