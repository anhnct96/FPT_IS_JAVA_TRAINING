create table tbl_customer (
    customer_id bigint not null auto_increment,
    address varchar(100) not null,
    mobile varchar(20) not null,
    customer_name varchar(99),
    primary key (customer_id)
) engine=InnoDB

create table tbl_order (
    order_id bigint not null auto_increment,
    order_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
    status VARCHAR(20) DEFAULT 'CREATED' not null,
    total_amount double precision,
    customer_id bigint,
    primary key (order_id)
) engine=InnoDB


create table tbl_order_item (
    id bigint not null auto_increment,
    amount double precision,
    quantity bigint,
    order_id bigint not null,
    product_id bigint not null,
    primary key (id)
    ) engine=InnoDB

create table tbl_product (
    p_id bigint not null auto_increment,
    available bigint not null,
    p_name varchar(99) not null,
    price double precision not null,
    primary key (p_id)
) engine=InnoDB


alter table tbl_customer
    add constraint mobile_uk unique (mobile)


alter table tbl_order
    add constraint customer_id_fk
    foreign key (customer_id)
    references tbl_customer (customer_id)
    on delete cascade


alter table tbl_order_item
    add constraint order_id_fk
    foreign key (order_id)
    references tbl_order (order_id)
    on delete cascade


alter table tbl_order_item
    add constraint product_id_fk
    foreign key (product_id)
    references tbl_product (p_id)
    on delete cascade