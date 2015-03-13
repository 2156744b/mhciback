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


insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(2, TIMESTAMP '2011-05-16 15:36:38', 'https://files.list.co.uk/images/s/citz-prods-1504-big-1414149352-lst154156.jpg', 'Seminal piece of Scottish theatre by John Byrne depicting one Friday in the life of the boys who work in the slab room of a Paisley carpet factory. 
One of the best-loved works in twentieth-century Scottish theatre, revived in a major new production directed by David Hayman.', ST_GeomFromText('Point(-4.293058 55.870513)', 4326), 'https://www.list.co.uk/event/89754-the-slab-boys/', '0131 529 6000', 'Kings Theatre Edinburgh');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(3, TIMESTAMP '2011-05-16 15:36:38', 'https://files.list.co.uk/images/r/john-williams-poster-lst120235.jpg', 'Whether we are battling Darth Vader, soaring with Superman or gasping at the wonders of Hogwarts, one thing is always for sure: the music of John Williams makes the magic happen. Tonight, Hollywood maestro Richard Kaufman joins the RSNO for a blockbuster tribute to the supreme film composer of our time. Enjoy classic themes from Harry Potter, Jaws, Star Wars, Raiders of the Lost Ark and many more. And if you think this music sounds amazing in the cinema, just wait till you hear it live!', ST_GeomFromText('Point(-4.300139 55.873137)', 4326), 'https://www.list.co.uk/event/348241-rsno-the-music-of-john-williams/', '0141 353 8000', 'Glasgow Royal Concert Hall');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(3, TIMESTAMP '2011-05-16 15:36:38', 'https://files.list.co.uk/images/b/balkanarama-lst155926.jpg', 'A blend of klezmer, Balkan, gypsy punk and electronic beats featuring live music, bellydancing, Balkan rakija brandy tasting and a live jam session.', ST_GeomFromText('Point(-4.291041 55.874678)', 4326), 'https://www.list.co.uk/event/212366-balkanarama/', '0131 558 3758', 'The Art School, Glasgow');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(1, TIMESTAMP '2011-05-16 15:36:38', 'https://files.list.co.uk/images/g/the-great-scottish-beer-celebration-lst158897.jpg', 'A beer festival held in honour of the independent Scottish brewing industry, featuring 18 of the very best small breweries in the country pouring a wide range of exciting beer styles. Each brewery runs their own bar, meaning the beers are poured by the very people who make them. Organised by Glasgow independent beer retailer Hippo Beers, this is the perfect event for everyone who wants to try new beers, from hardened beer geeks to craft beer virgins.', ST_GeomFromText('Point(-4.301040 55.869959)', 4326), 'https://www.list.co.uk/event/450286-the-great-scottish-beer-celebration/', '0141 946 3020', 'Barras Art & Design Centre');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(5, TIMESTAMP '2011-05-16 15:36:38', 'https://files.list.co.uk/images/o/bb-promo-hi-res-lst142356.jpg', 'Sixty Episodes in 60 Minutes. One Man Breaking Bad. See your iconic favourites come to life: Walt, Jesse, Saul, Skyler, Hank, Walt Junior, Mike and Gus Fring! LA actor Miles Allen has had over a million hits on YouTube, displaying both his acting and his incredible mimicking abilities. See him in Edinburgh after the sell out Melbourne Comedy Festival season of the world premiere of this incredible tour de force', ST_GeomFromText('Point(-4.285033 55.871981)', 4326), 'https://www.list.co.uk/event/415981-one-man-breaking-bad/', '0844 576 2210', 'Cheltenham Town Hall');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(4, TIMESTAMP '2011-05-16 15:36:38', 'http://cdn.evbuc.com/images/12466551/50586363040/1/logo.jpg', 'James began his kitchen life at Zinc & Etain in Princes Square before working for over two years at the Michelin-starred The Peat Inn in Fife, as well as Michael Caines at ABode, Lochgreen House Hotel and stages at Restaurant Andrew Fairlie. He then went on to be one half of the duo to open Burger Meats Bun in Glasgow almost two years ago. It is fair to say he has more than enough expertise and skill to create a menu perfect to be paired with Edinburgh Gin, WEST Brewery Beers and Tamdhu Speyside Single Malt Whisky.', ST_GeomFromText('Point(-4.295633 55.868538)', 4326), 'https://www.list.co.uk/event/89754-the-slab-boys/', '0131 529 6000', 'Burger Meats Bun Glasgow ');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(4, TIMESTAMP '2011-05-16 15:36:38', 'The Scottish Macaroni Appreciation Club serves up an evening of macaroni with four delicious dishes & sides and presents mac addicts with their very own Founder Member Card as they recite their allegiance to all things macaroni, before casting their vote for their favourite mac of the night.', ST_GeomFromText('Point(-4.293573 55.864811)', 4326), 'https://www.list.co.uk/event/89754-the-slab-boys/', '0131 529 6000', 'Sloans');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(1, TIMESTAMP '2011-05-16 15:36:38', 'http://static.designmynight.com/uploads/2014/05/yates-glasgow-book-online.jpg', 'Head over in the morning and sample their breakfast offering - stick around until the evening to get those thirsty paws on a bottle of vino for as little as £5.95, and a hearty pub grub selection, all priced with the bank balance in mind. By night, the space transforms into one of the citys most vibrant clubs, welcoming two resident DJs every Friday and Saturday night, and boasting a much envied sound system and lighting rig. ', ST_GeomFromText('Point(-4.294732 55.872752)', 4326), 'http://www.designmynight.com/glasgow/bars/sauchiehall-street/yatess', '0131 529 6000', 'Yatess');

insert into publicevents(type, evdate, poster, description, evlocation, url, telephone, evlocationdescription) values(5, TIMESTAMP '2011-05-16 15:36:38', 'http://www.mallgalleries.org.uk/sites/default/files/styles/content_image_620w/public/events/Ambrus-Victor-Girl-from-South-Sea-Islands.jpg', 'An exciting opportunity to work alongside Members of the Pastel Society Glass of wine or a soft drink served on your arrival Prize for best Non-Member work completed during the evening Sealed Bid Paintings will be finalised during the evening Art materials, courtesy of Caran dAche will be on sale', ST_GeomFromText('Point(-4.287307 55.876989)', 4326), 'http://www.mallgalleries.org.uk/whats-on/events/pastel-art-event-evening#sthash.OO5vZ2d6.dpuf', '020 7930 6844', 'Mall Galleries');
