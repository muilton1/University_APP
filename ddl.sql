-- Database: university

CREATE DATABASE university

-- SCHEMA: univer

CREATE SCHEMA IF NOT EXISTS univer
    AUTHORIZATION postgres;
	
-- Table: univer.students

CREATE TABLE IF NOT EXISTS univer.students
(
    id bigint NOT NULL DEFAULT nextval('univer.students_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    age smallint NOT NULL,
    score numeric NOT NULL,
    "olympicGamer" boolean NOT NULL,
    CONSTRAINT students_pkey PRIMARY KEY (id)
)

-- Table: univer.groups

CREATE TABLE IF NOT EXISTS univer.groups
(
    id bigint NOT NULL DEFAULT nextval('univer.groups_id_seq'::regclass),
    "groupName" character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT groups_pkey PRIMARY KEY (id)
)

-- Table: univer.cross

CREATE TABLE IF NOT EXISTS univer."cross"
(
    "groupId" bigint NOT NULL,
    "studentId" bigint NOT NULL,
    CONSTRAINT fk_groups FOREIGN KEY ("groupId")
        REFERENCES univer.groups (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_students FOREIGN KEY ("studentId")
        REFERENCES univer.students (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)