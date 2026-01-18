-- ---------------------------------------------------------------------
-- database schema initializations
-- ---------------------------------------------------------------------


-- ---------------------------------------------------------------------
-- tabla voted_countries: almacena información basica de cada uno de los paises por los cuales se ha votadp al menos una vez
-- incluye un indice unico por código de país para evitar duplicidad
--
-- ---------------------------------------------------------------------

create table voted_countries(
    id bigserial not null,
    alpha3_code varchar(3) not null,
    common_name varchar(128) not null,
    official_name varchar(128) not null,
    capital_city varchar(128) not null,
    region varchar(64) not null,
    subregion varchar(64) not null,
    constraint voted_countries__pk primary key (id)
);

create unique index voted_countries__idx01 on voted_countries (alpha3_code);


-- ---------------------------------------------------------------------
-- tabla votes: almacena la información de los votos sufragados
-- incluye un indice unico por email de votante para restringir los votos a solo 1 por votante
-- incluye un indice por pais para efectos de optimización al momento de generar el top-10
-- ---------------------------------------------------------------------
create table votes(
    id bigserial not null,
    voter_email varchar(256) not null,
    voter_name varchar(256) not null,
    voted_country_id bigint not null,
    vote_datetime timestamp not null default current_timestamp,
    constraint votes__pk primary key (id),
    constraint votes__fk01 foreign key (voted_country_id)
            references voted_countries (id)
);

create unique index votes__idx01 on votes (voter_email);
create index votes__idx02 on votes (voted_country_id);
