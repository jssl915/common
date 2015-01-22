prompt PL/SQL Developer import file
prompt Created on 2015��1��14�� by Administrator
set feedback off
set define off
prompt Creating T_S_DETAIL...
create table T_S_DETAIL
(
  detail_id     NUMBER(16) not null,
  dict_id       NUMBER(16),
  detail_name   NVARCHAR2(32),
  detail_value  NUMBER(16),
  detail_desc   NVARCHAR2(256),
  detail_status NUMBER(16),
  create_time   NVARCHAR2(19),
  update_time   NVARCHAR2(19)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column T_S_DETAIL.detail_id
  is '�ֵ���ϸID';
comment on column T_S_DETAIL.dict_id
  is '�ֵ�ID';
comment on column T_S_DETAIL.detail_name
  is '�ֵ��ֶ�����';
comment on column T_S_DETAIL.detail_value
  is '�ֵ��ֶ�ֵ';
comment on column T_S_DETAIL.detail_desc
  is '����';
comment on column T_S_DETAIL.detail_status
  is '״̬';
comment on column T_S_DETAIL.create_time
  is '����ʱ��';
comment on column T_S_DETAIL.update_time
  is '����ʱ��';
alter table T_S_DETAIL
  add constraint T_S_DETAIL primary key (DETAIL_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_DICT...
create table T_S_DICT
(
  dict_id     NUMBER(16) not null,
  dict_name   NVARCHAR2(32),
  dict_desc   NVARCHAR2(256),
  dict_status NUMBER(8),
  create_time NVARCHAR2(19),
  update_time NVARCHAR2(19)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column T_S_DICT.dict_id
  is '�ֵ�ID';
comment on column T_S_DICT.dict_name
  is '�ֵ�����';
comment on column T_S_DICT.dict_desc
  is '����';
comment on column T_S_DICT.dict_status
  is '״̬';
comment on column T_S_DICT.create_time
  is '����ʱ��';
comment on column T_S_DICT.update_time
  is '����ʱ��';
alter table T_S_DICT
  add constraint T_S_DICT primary key (DICT_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_LOG...
create table T_S_LOG
(
  log_id     NUMBER(16) not null,
  action_url NVARCHAR2(128),
  log_time   NVARCHAR2(19),
  user_ip    NVARCHAR2(15),
  user_id    NUMBER(16),
  log_desc   NVARCHAR2(128),
  user_name  NVARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_S_LOG
  is 'ϵͳ������־��';
comment on column T_S_LOG.action_url
  is '����URL';
comment on column T_S_LOG.log_time
  is '����ʱ��';
comment on column T_S_LOG.user_ip
  is '�û�IP';
comment on column T_S_LOG.user_id
  is '�û�ID';
comment on column T_S_LOG.log_desc
  is '��־����';
comment on column T_S_LOG.user_name
  is '�û�����';
alter table T_S_LOG
  add constraint T_S_LOG primary key (LOG_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_MENU...
create table T_S_MENU
(
  menu_id     NUMBER(16) not null,
  menu_name   NVARCHAR2(32),
  menu_desc   NVARCHAR2(256),
  menu_url    NVARCHAR2(128),
  menu_pid    NUMBER(8),
  menu_type   NUMBER(8),
  menu_status NUMBER(8),
  menu_level  NUMBER(8),
  menu_icon   NVARCHAR2(128),
  create_time NVARCHAR2(19),
  update_time NVARCHAR2(19),
  menu_order  NUMBER(8)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_S_MENU
  is '�˵���';
comment on column T_S_MENU.menu_id
  is '��ԴID';
comment on column T_S_MENU.menu_name
  is '��Դ����';
comment on column T_S_MENU.menu_desc
  is '��Դ����';
comment on column T_S_MENU.menu_url
  is '��ԴURL';
comment on column T_S_MENU.menu_pid
  is '��ԴPID';
comment on column T_S_MENU.menu_type
  is '��Դ����';
comment on column T_S_MENU.menu_status
  is '��Դ״̬';
comment on column T_S_MENU.menu_level
  is '��Դ����';
comment on column T_S_MENU.menu_icon
  is '��Դͼ��';
comment on column T_S_MENU.create_time
  is '����ʱ��';
comment on column T_S_MENU.update_time
  is '����ʱ��';
comment on column T_S_MENU.menu_order
  is '�˵�����';
alter table T_S_MENU
  add constraint T_S_MENU primary key (MENU_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_ROLE...
create table T_S_ROLE
(
  role_id     NUMBER(16) not null,
  role_name   NVARCHAR2(32),
  role_desc   NVARCHAR2(256),
  role_order  NUMBER(8),
  role_type   NUMBER(8),
  role_status NUMBER(8),
  create_time NVARCHAR2(19),
  update_time NVARCHAR2(19)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column T_S_ROLE.role_id
  is '��ɫID';
comment on column T_S_ROLE.role_name
  is '��ɫ����';
comment on column T_S_ROLE.role_desc
  is '����';
comment on column T_S_ROLE.role_type
  is '��ɫ����';
comment on column T_S_ROLE.role_status
  is '��ɫ״̬';
comment on column T_S_ROLE.create_time
  is '����ʱ��';
comment on column T_S_ROLE.update_time
  is '����ʱ��';
alter table T_S_ROLE
  add constraint T_S_ROLE primary key (ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_ROLE_MENU...
create table T_S_ROLE_MENU
(
  role_id NUMBER(16),
  menu_id NUMBER(16)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_S_ROLE_MENU
  is '��ɫ�󶨵Ĳ˵�';
comment on column T_S_ROLE_MENU.role_id
  is '��ɫID';
comment on column T_S_ROLE_MENU.menu_id
  is '��ԴID';

prompt Creating T_S_USER...
create table T_S_USER
(
  user_id     NUMBER(16) not null,
  user_name   NVARCHAR2(32),
  user_order  NUMBER(8),
  user_type   NUMBER(8),
  user_status NUMBER(8),
  user_pwd    NVARCHAR2(256),
  create_time NVARCHAR2(19),
  update_time NVARCHAR2(19),
  real_name   NVARCHAR2(32)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table T_S_USER
  is '�û���';
comment on column T_S_USER.user_id
  is '�û�ID';
comment on column T_S_USER.user_name
  is '��¼����';
comment on column T_S_USER.user_order
  is '����';
comment on column T_S_USER.user_type
  is '�û�����';
comment on column T_S_USER.user_status
  is '�û�״̬';
comment on column T_S_USER.user_pwd
  is '��������';
comment on column T_S_USER.create_time
  is '����ʱ��';
comment on column T_S_USER.update_time
  is '����ʱ��';
comment on column T_S_USER.real_name
  is '��ʵ����';
alter table T_S_USER
  add constraint T_S_USER primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating T_S_USER_ROLE...
create table T_S_USER_ROLE
(
  user_id NUMBER(16),
  role_id NUMBER(16)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table T_S_USER_ROLE
  is '�û���Ӧ��ɫ';
comment on column T_S_USER_ROLE.user_id
  is '�û�ID';
comment on column T_S_USER_ROLE.role_id
  is '��ɫID';

prompt Disabling triggers for T_S_DETAIL...
alter table T_S_DETAIL disable all triggers;
prompt Disabling triggers for T_S_DICT...
alter table T_S_DICT disable all triggers;
prompt Disabling triggers for T_S_LOG...
alter table T_S_LOG disable all triggers;
prompt Disabling triggers for T_S_MENU...
alter table T_S_MENU disable all triggers;
prompt Disabling triggers for T_S_ROLE...
alter table T_S_ROLE disable all triggers;
prompt Disabling triggers for T_S_ROLE_MENU...
alter table T_S_ROLE_MENU disable all triggers;
prompt Disabling triggers for T_S_USER...
alter table T_S_USER disable all triggers;
prompt Disabling triggers for T_S_USER_ROLE...
alter table T_S_USER_ROLE disable all triggers;
prompt Deleting T_S_USER_ROLE...
delete from T_S_USER_ROLE;
commit;
prompt Deleting T_S_USER...
delete from T_S_USER;
commit;
prompt Deleting T_S_ROLE_MENU...
delete from T_S_ROLE_MENU;
commit;
prompt Deleting T_S_ROLE...
delete from T_S_ROLE;
commit;
prompt Deleting T_S_MENU...
delete from T_S_MENU;
commit;
prompt Deleting T_S_LOG...
delete from T_S_LOG;
commit;
prompt Deleting T_S_DICT...
delete from T_S_DICT;
commit;
prompt Deleting T_S_DETAIL...
delete from T_S_DETAIL;
commit;
prompt Loading T_S_DETAIL...
insert into T_S_DETAIL (detail_id, dict_id, detail_name, detail_value, detail_desc, detail_status, create_time, update_time)
values (8, 1, '����', 1, '����״̬', 1, '2015-01-04 14:04:28', '2015-01-06 11:02:33');
insert into T_S_DETAIL (detail_id, dict_id, detail_name, detail_value, detail_desc, detail_status, create_time, update_time)
values (9, 1, '��ֹ', 2, '��ֹ״̬', 1, '2015-01-04 14:04:39', '2015-01-04 14:04:39');
commit;
prompt 2 records loaded
prompt Loading T_S_DICT...
insert into T_S_DICT (dict_id, dict_name, dict_desc, dict_status, create_time, update_time)
values (1, '״̬', '1:���� 2:��ֹ', 1, '2015-01-04 11:07:56', '2015-01-13 14:07:08');
commit;
prompt 1 records loaded
prompt Loading T_S_LOG...
prompt Table is empty
prompt Loading T_S_MENU...
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (60, 'ϵͳ��־����', null, '/system/prg/log/init', 1, null, 1, 2, null, '2014-12-30 13:37:07', '2015-01-04 10:11:37', 5);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (6, 'ϵͳ�˵�����', '�˵�����', '/system/prg/menu/init', 1, 1, 1, 2, null, null, '2015-01-04 10:11:16', 2);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (130, 'ͼƬ���', null, null, 0, null, 1, 1, null, '2015-01-08 09:43:05', '2015-01-14 09:03:46', 2);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (131, 'ͼƬ�ϴ�����', null, 'cy/img/upload', 130, null, 1, 2, null, '2015-01-08 09:44:16', '2015-01-14 09:06:21', 1);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (132, 'ͼ�����', null, null, 0, null, 1, 1, null, '2015-01-08 09:44:47', '2015-01-14 15:11:31', 3);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (190, '��������ͼ', null, 'cy/chart/baseline', 132, null, 1, 2, null, '2015-01-14 15:26:28', '2015-01-14 15:26:47', 1);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (191, '������ͼ', null, 'cy/chart/basepie', 132, null, 1, 2, null, '2015-01-14 15:29:03', '2015-01-14 15:29:25', 2);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (170, 'ͼƬ��קʾ��', null, 'cy/img/photowall', 130, null, 1, 2, null, '2015-01-14 09:05:58', '2015-01-14 09:18:42', 1);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (192, '������״ͼ', null, 'cy/chart/basecolumn', 132, null, 1, 2, null, '2015-01-14 15:33:34', '2015-01-14 15:33:34', 3);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (0, '��Դ��', '��Դ��', null, -1, 1, 1, 0, null, '2014-12-30 12:38:14', '2014-12-30 12:38:14', 1);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (1, 'ϵͳ����', 'ϵͳ����', null, 0, 1, 1, 1, null, null, '2014-12-30 13:07:08', 1);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (90, 'ϵͳ��������', null, '/system/prg/dict/init', 1, null, 1, 2, null, '2015-01-04 10:11:48', '2015-01-06 11:10:46', 4);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (3, 'ϵͳ����Ա����', '�û�����', '/system/prg/user/init', 1, 1, 1, 2, null, null, '2014-12-30 13:36:00', 1);
insert into T_S_MENU (menu_id, menu_name, menu_desc, menu_url, menu_pid, menu_type, menu_status, menu_level, menu_icon, create_time, update_time, menu_order)
values (4, 'ϵͳ��ɫ����', '��ɫ����', '/system/prg/role/init', 1, 1, 1, 2, null, null, '2014-12-30 13:36:42', 3);
commit;
prompt 14 records loaded
prompt Loading T_S_ROLE...
insert into T_S_ROLE (role_id, role_name, role_desc, role_order, role_type, role_status, create_time, update_time)
values (3, '�û�����Ա', '�û�����Ա', 2, null, null, '2014-12-30 13:31:30', '2014-12-30 13:31:30');
insert into T_S_ROLE (role_id, role_name, role_desc, role_order, role_type, role_status, create_time, update_time)
values (1, '��������Ա', '���Ȩ�޹���Ա', 1, null, 1, '2014-12-30 13:22:52', '2014-12-30 13:22:52');
commit;
prompt 2 records loaded
prompt Loading T_S_ROLE_MENU...
insert into T_S_ROLE_MENU (role_id, menu_id)
values (3, 3);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (3, 90);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (3, 4);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 90);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 130);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 131);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 190);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 192);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 1);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 6);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 4);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 60);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 170);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 132);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 191);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 0);
insert into T_S_ROLE_MENU (role_id, menu_id)
values (1, 3);
commit;
prompt 17 records loaded
prompt Loading T_S_USER...
insert into T_S_USER (user_id, user_name, user_order, user_type, user_status, user_pwd, create_time, update_time, real_name)
values (42, 'lj', 1, 1, 1, 'FBADE9E36A3F36D3D676C1B808451DD7', '2014-12-29 10:45:46', '2015-01-04 17:14:12', '�');
insert into T_S_USER (user_id, user_name, user_order, user_type, user_status, user_pwd, create_time, update_time, real_name)
values (121, 'abc', 2, 1, 1, '900150983CD24FB0D6963F7D28E17F72', '2015-01-06 13:07:05', '2015-01-13 16:10:03', 'abc');
insert into T_S_USER (user_id, user_name, user_order, user_type, user_status, user_pwd, create_time, update_time, real_name)
values (161, 'ff', 1, 1, 1, '21218CCA77804D2BA1922C33E0151105', '2015-01-12 15:25:30', '2015-01-13 15:39:59', 'ff');
commit;
prompt 3 records loaded
prompt Loading T_S_USER_ROLE...
insert into T_S_USER_ROLE (user_id, role_id)
values (42, 1);
insert into T_S_USER_ROLE (user_id, role_id)
values (121, 3);
insert into T_S_USER_ROLE (user_id, role_id)
values (121, 1);
commit;
prompt 3 records loaded
prompt Enabling triggers for T_S_DETAIL...
alter table T_S_DETAIL enable all triggers;
prompt Enabling triggers for T_S_DICT...
alter table T_S_DICT enable all triggers;
prompt Enabling triggers for T_S_LOG...
alter table T_S_LOG enable all triggers;
prompt Enabling triggers for T_S_MENU...
alter table T_S_MENU enable all triggers;
prompt Enabling triggers for T_S_ROLE...
alter table T_S_ROLE enable all triggers;
prompt Enabling triggers for T_S_ROLE_MENU...
alter table T_S_ROLE_MENU enable all triggers;
prompt Enabling triggers for T_S_USER...
alter table T_S_USER enable all triggers;
prompt Enabling triggers for T_S_USER_ROLE...
alter table T_S_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
