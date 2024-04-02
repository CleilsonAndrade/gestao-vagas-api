    create table tb_apply_job (
        created_at datetime(6),
        id binary(16) not null,
        id_candidate binary(16) not null,
        id_job binary(16) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tb_candidate (
        created_at datetime(6),
        id binary(16) not null,
        password varchar(100),
        curriculum varchar(255),
        description varchar(255),
        email varchar(255),
        name varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_company (
        created_at datetime(6),
        id binary(16) not null,
        password varchar(100),
        description varchar(255),
        email varchar(255),
        name varchar(255),
        username varchar(255),
        website varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_job (
        created_at datetime(6),
        id binary(16) not null,
        id_company binary(16),
        benefits varchar(255),
        description varchar(255),
        level varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table tb_apply_job 
       add constraint FKd3fe4em4pk6dlkvm2aviuopr2 
       foreign key (id_candidate) 
       references tb_candidate (id);

    alter table tb_apply_job 
       add constraint FKe1j1concp5jxcwvyhbvivmsym 
       foreign key (id_job) 
       references tb_job (id);

    alter table tb_job 
       add constraint FK7iv87p9yyusvhqllune21kgwd 
       foreign key (id_company) 
       references tb_company (id);
