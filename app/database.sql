drop table users_privateevents;
drop table privateevents;
drop table publicevents;
drop table eventtypes;
drop table users;

create table users (
	email varchar(60),
	name varchar(60),
	gcmid varchar(4000),
	primary key (email)
);

create table eventtypes (
	id serial,
	type varchar(60),
	primary key (id)
);

create table publicevents (
	id serial,
	type integer references eventtypes(id),
	creator varchar(60) references users(email),
	description varchar(600),
	evdate timestamp,
	poster varchar(4000),
	url varchar(4000),
	telephone varchar(20),
	evlocation geometry(Point, 4326), 
	evlocationdescription varchar(60),
	primary key (id)
);

create index publicevents_type_idx on publicevents(type);
create index publicevents_evdate_idx on publicevents(evdate);
create index publicevents_evlocation_idx on publicevents(evlocation);
	
create table privateevents (
	id serial,
	type varchar(60),
	creator varchar(60) references users(email),
	description varchar(600),
	evdate timestamp,
	evlocation geometry(Point, 4326),
	evlocationdescription varchar(60),
	active boolean default true,
	primary key (id)
);

create index privateevents_evdate_idx on privateevents(evdate);
create index privateevents_evlocation_idx on privateevents(evlocation);

create table users_privateevents (
	email varchar(60) references users(email),
	event integer references privateevents(id),
	accepted boolean,
	primary key (email, event)
);
	