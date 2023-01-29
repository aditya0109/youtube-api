drop database youtubeapidb;
drop user youtubeapi;
create user youtubeapi with password 'password';
create database youtubeapidb with template=template0 owner=youtubeapi;
\connect youtubeapidb;
alter default privileges grant all on tables to youtubeapi;
alter default privileges grant all on sequences to youtubeapi;

create table youtube(
content_id integer primary key not null,
title text not null,
description text not null,
datetime timestamp not null,
url text
);
