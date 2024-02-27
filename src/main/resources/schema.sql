CREATE TABLE public."family" (
	id serial4 NOT NULL,
	"comment" varchar(255) NULL,
	CONSTRAINT family_pkey PRIMARY KEY (id)
);

CREATE TABLE public.person (
	id serial4 NOT NULL,
	has_accepted bool NULL,
	"name" varchar(255) NULL,
	family_id int4 NULL,
	CONSTRAINT person_pkey PRIMARY KEY (id)
);

CREATE TABLE public.family_change (
	family_id int4 NULL,
	id serial4 NOT NULL,
	change_time timestamptz(6) NULL,
	new_comment varchar(255) NULL,
	CONSTRAINT family_change_pkey PRIMARY KEY (id)
);

CREATE TABLE public.person_change (
	family_change_id int4 NULL,
	family_id int4 NULL,
	id serial4 NOT NULL,
	new_has_accepted bool NULL,
	person_id int4 NULL,
	was_created bool NULL,
	change_date timestamptz(6) NULL,
	CONSTRAINT person_change_pkey PRIMARY KEY (id)
);


ALTER TABLE public.person ADD CONSTRAINT fkcie85gmou5vftruh12obynpu8 FOREIGN KEY (family_id) REFERENCES public."family"(id);

ALTER TABLE public.family_change ADD CONSTRAINT fktkdlc9yx527v8siasxtamddut FOREIGN KEY (family_id) REFERENCES public."family"(id);

ALTER TABLE public.person_change ADD CONSTRAINT fk691m3imlqbx6669jlatmy47x7 FOREIGN KEY (person_id) REFERENCES public.person(id);
ALTER TABLE public.person_change ADD CONSTRAINT fk8i16333win9f1cxlcjqwdowtr FOREIGN KEY (family_id) REFERENCES public."family"(id);
ALTER TABLE public.person_change ADD CONSTRAINT fktoogi6ksjai0s2fwkm8u1dfb8 FOREIGN KEY (family_change_id) REFERENCES public.family_change(id);