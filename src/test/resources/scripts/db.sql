-- Table: public.student

-- DROP TABLE public.student;

CREATE TABLE public.student
(
    id integer NOT NULL,
    nom character varying(255) COLLATE pg_catalog."default" NOT NULL,
    prenom character varying(255) COLLATE pg_catalog."default" NOT NULL,
    datenaissance date,
    mail character varying(255) COLLATE pg_catalog."default",
    gsm character varying(255) COLLATE pg_catalog."default",
    classe character varying(255) COLLATE pg_catalog."default",
    professeur character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;