create sequence hibernate_sequence start with 1 increment by 1;

    create table t_artists (
       id bigint not null,
        bio varchar(3000),
        createDate timestamp,
        name varchar(100) not null,
        primary key (id)
    );

    create table t_customers (
       id bigint not null,
        created_date timestamp not null,
        e_mail varchar(50) not null,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        primary key (id)
    );

    create table t_items (
       DTYPE varchar(31) not null,
        id bigint not null,
        created_date timestamp not null,
        description varchar(3000),
        price decimal(19,2) not null,
        title varchar(100) not null,
        genre varchar(100),
        music_company varchar(255),
        isbn varchar(15),
        language varchar(20),
        number_of_pages integer,
        publication_date date,
        artist_fk bigint,
        publisher_fk bigint,
        primary key (id)
    );

    create table t_publishers (
       id bigint not null,
        created_date timestamp not null,
        name varchar(50) not null,
        primary key (id)
    );

    create table t_purchase_order_line (
       id bigint not null,
        created_date timestamp not null,
        quantity integer not null,
        item_fk bigint,
        purchase_order_fk bigint,
        primary key (id)
    );

    create table t_purchase_orders (
       id bigint not null,
        created_date timestamp not null,
        purchase_order_date date not null,
        customer_fk bigint not null,
        primary key (id)
    );

    create table t_tracks (
       id bigint not null,
        created_date timestamp not null,
        duration bigint not null,
        title varchar(255) not null,
        cd_fk bigint,
        primary key (id)
    );

    alter table t_items 
       add constraint FKr3152tukbog585dik5qwonldg 
       foreign key (artist_fk) 
       references t_artists;

    alter table t_items 
       add constraint FKi6lqpcqfnc4dtsp9w473p5kkj 
       foreign key (publisher_fk) 
       references t_publishers;

    alter table t_purchase_order_line 
       add constraint FKk5o0lynwj3vddwn397a24kwqj 
       foreign key (item_fk) 
       references t_items;

    alter table t_purchase_order_line 
       add constraint FKffbfk27356l55yt28wd7w8mwp 
       foreign key (purchase_order_fk) 
       references t_purchase_orders;

    alter table t_purchase_orders 
       add constraint FK93wd2w995ng3vyj51y4fur1hg 
       foreign key (customer_fk) 
       references t_customers;

    alter table t_tracks 
       add constraint FK23u6r10m0dkp0m8t5hr40ilux 
       foreign key (cd_fk) 
       references t_items;
