drop table if exists criminal_case
drop table if exists detective
drop table if exists evidence
drop table if exists storage
drop table if exists track_entry
drop table if exists working_detective_case
create table criminal_case (
       criminal_case_id bigint not null auto_increment,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        version integer,
        detailed_description longtext,
        notes longtext,
        criminal_case_number varchar(255) default '',
        short_description varchar(255) default '',
        case_status varchar(30) default 'SUBMITTED',
        type varchar(30) default 'UNCATEGORIZED',
        lead_investigator_id bigint,
        primary key (criminal_case_id)
    ) engine=InnoDB

create table detective (
       detective_id bigint not null auto_increment,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        version integer,
        armed smallint  default 0,
        badge_number varchar(255) default '',
        first_name varchar(255) default '',
        hiring_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        last_name varchar(255) default '',
        passwords varchar(255) default '',
        detective_rank varchar(30) default 'TRAINEE',
        detective_status varchar(30) default 'ACTIVE',
        username varchar(255) default '',
        primary key (detective_id)
    ) engine=InnoDB

create table evidence (
       evidence_id bigint not null auto_increment,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        version integer,
        archived TINYINT,
        item_name varchar(255) default '',
        notes longtext,
        evidence_number varchar(255) default '',
        criminal_case_id bigint,
        storage_id bigint,
        primary key (evidence_id)
    ) engine=InnoDB

create table storage (
       storage_id bigint not null auto_increment,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        version integer,
        location varchar(255) default '',
        storage_number varchar(255) default '',
        primary key (storage_id)
    ) engine=InnoDB

create table track_entry (
       track_entry_id bigint not null auto_increment,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        version integer,
        action varchar(30) default 'RETRIEVED',
        date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        reason varchar(255) default '',
        detective_id bigint,
        evidence_id bigint,
        primary key (track_entry_id)
        ) engine=InnoDB

create table working_detective_case (
       criminal_case_id bigint not null,
        detective_id bigint not null,
        primary key (criminal_case_id, detective_id)
    ) engine=InnoDB

alter table criminal_case
       add constraint UK_a6sg9ufa0hidohgb259swj9cp unique (criminal_case_number)

alter table detective
       add constraint UK_4y29i3x2ffrsqka03tevdsice unique (badge_number)

alter table detective
       add constraint UK_mfig77g1hjl85t3og3lsqaski unique (username)

alter table evidence
       add constraint UK_ia9t7wcrxp4m65w9m0tfpw2qk unique (evidence_number)

alter table storage
       add constraint UK_gsn7vodcc1ns7nkhostta3t7x unique (storage_number)

alter table criminal_case
       add constraint FKarnw696m7ifupuxw7ufva5j69
       foreign key (lead_investigator_id)
       references detective (detective_id)

alter table evidence
       add constraint FKf7h6m60otwrldlr236modh6pu
       foreign key (criminal_case_id)
       references criminal_case (criminal_case_id)
       on delete cascade

alter table evidence
       add constraint FKktid71cjsa48q61pw4m3nrdbx
       foreign key (storage_id)
       references storage (storage_id)
       on delete cascade

 alter table track_entry
       add constraint FKfjlymcrq4i3wv0x1e3vlua2wv
       foreign key (detective_id)
       references detective (detective_id)
       on delete cascade

alter table track_entry
       add constraint FKdb3h19d1wdajl0bqw3jo7hkj6
       foreign key (evidence_id)
       references evidence (evidence_id)

alter table working_detective_case
       add constraint FKa1s54tvdsgnww8w1nk0d4grw7
       foreign key (detective_id)
       references detective (detective_id)

alter table working_detective_case
       add constraint FKdil1783758qven9tcvyrroyhi
       foreign key (criminal_case_id)
       references criminal_case (criminal_case_id)