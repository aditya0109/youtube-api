drop database youtubeapidb;
drop user youtubeapi;
create user youtubeapi with password 'password';
create database youtubeapidb with template=template0 owner=youtubeapi;
\connect youtubeapidb;
alter default privileges grant all on tables to youtubeapi;
alter default privileges grant all on sequences to youtubeapi;

create table youtube(
id serial primary key,
title text not null,
description text not null,
url text,
video_id text unique not null
);

