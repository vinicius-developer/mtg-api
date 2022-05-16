GRANT USAGE ON SCHEMA public TO postgres;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO postgres;

create table users(
    id SERIAL PRIMARY KEY,
    email varchar(150) unique,
    password varchar(72) not null,
    username varchar(150) not null unique
);

create table list_of_cards(
    id SERIAL PRIMARY KEY,
    id_user integer,
    name varchar(150)
);

create table cards(
    id SERIAL PRIMARY KEY,
    id_list integer not null,
    name varchar(150) not null,
    edition varchar(250) not null,
    language_card varchar(20) not null,
    is_foil boolean not null,
    price real not null,
    amount integer default(1) not null
);

alter table cards
   add constraint fk_cards_id_list
   foreign key (id_list)
   references list_of_cards(id);

alter table list_of_cards
   add constraint fk_list_of_cards_id_user
   foreign key (id_user)
   references users(id);