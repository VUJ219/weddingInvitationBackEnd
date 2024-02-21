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

ALTER TABLE public.person ADD CONSTRAINT fkcie85gmou5vftruh12obynpu8 FOREIGN KEY (family_id) REFERENCES public."family"(id);