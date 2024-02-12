drop table if exists "monster";

--this is a very special comment

CREATE TABLE "monster" (
    "id" integer primary key AUTOINCREMENT,
    "name" varchar(20) not null,
    "attack" bigint not null,
    "defense" bigint not null,
    "hp" bigint not null,
    "speed" bigint not null,
    "image_url" varchar(255) not null     
);