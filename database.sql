--create database
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

--population
insert into eventtypes(type) values('Nightclub');
insert into eventtypes(type) values('Theatre');
insert into eventtypes(type) values('Live Music');
insert into eventtypes(type) values('Restaurant');
insert into eventtypes(type) values('Art');


insert into publicevents(type, evdate, poster, description, evlocation) values(1, TIMESTAMP '2011-05-16 15:36:38', 'http://www.creativefreedom.co.uk/wp-content/uploads/2013/03/00-android-4-0_icons.png', 'This is the event description. O Kurt einai malakas. AMEN', ST_GeomFromText('Point(-4.295654 55.864594)', 4326));

insert into publicevents(type, evdate, poster, description, evlocation) values(2, TIMESTAMP '2011-05-16 15:36:38', 'http://www.creativefreedom.co.uk/wp-content/uploads/2013/03/00-android-4-0_icons.png', 'This is the event description. O Kurt einai malakas. AMEN', ST_GeomFromText('Point(-4.293573 55.864811)', 4326));

insert into publicevents(type, evdate, poster, description, evlocation) values(3, TIMESTAMP '2011-05-16 15:36:38', 'http://www.creativefreedom.co.uk/wp-content/uploads/2013/03/00-android-4-0_icons.png', 'This is the event description. O Kurt einai malakas. AMEN', ST_GeomFromText('Point(-4.289914 55.866027)', 4326));

insert into publicevents(type, evdate, poster, description, evlocation) values(4, TIMESTAMP '2011-05-16 15:36:38', 'http://www.creativefreedom.co.uk/wp-content/uploads/2013/03/00-android-4-0_icons.png', 'This is the event description. O Kurt einai malakas. AMEN', ST_GeomFromText('Point(-4.293573 55.866027)', 4326)); 



	
