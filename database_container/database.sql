CREATE DATABASE mtg;

GRANT USAGE ON SCHEMA public TO postgres;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO postgres;


create table users(
    id SERIAL PRIMARY KEY,
    email varchar(150) unique,
    password varchar(72) not null
);

create table list_of_cards(
    id SERIAL PRIMARY KEY,
    id_user integer
);

create type language_card_type as ENUM('inglês', 'português', 'japones');

create table cards(
    id SERIAL PRIMARY KEY,
    id_list integer not null,
    name varchar(150) not null,
    edition varchar(250) not null,
    language_card language_card_type not null,
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