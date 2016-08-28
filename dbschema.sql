--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: glucosemeasurement; Type: TABLE; Schema: public; Owner: mba
--

CREATE TABLE glucosemeasurement (
    id bigint NOT NULL,
    glucosevalue numeric,
    measuredate timestamp without time zone DEFAULT now(),
    userid integer NOT NULL
);


ALTER TABLE glucosemeasurement OWNER TO mba;

--
-- Name: glucoseuser; Type: TABLE; Schema: public; Owner: mba
--

CREATE TABLE glucoseuser (
    id bigint NOT NULL,
    firstname character varying(64),
    lastname character varying(64),
    username character varying(64),
    email character varying(64),
    password character varying(64),
    usertypeid integer NOT NULL
);


ALTER TABLE glucoseuser OWNER TO mba;

--
-- Name: seq_glucosemeasurement; Type: SEQUENCE; Schema: public; Owner: mba
--

CREATE SEQUENCE seq_glucosemeasurement
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_glucosemeasurement OWNER TO mba;

--
-- Name: seq_glucosemeasurement; Type: SEQUENCE OWNED BY; Schema: public; Owner: mba
--

ALTER SEQUENCE seq_glucosemeasurement OWNED BY glucosemeasurement.id;


--
-- Name: seq_glucoseuser; Type: SEQUENCE; Schema: public; Owner: mba
--

CREATE SEQUENCE seq_glucoseuser
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_glucoseuser OWNER TO mba;

--
-- Name: seq_glucoseuser; Type: SEQUENCE OWNED BY; Schema: public; Owner: mba
--

ALTER SEQUENCE seq_glucoseuser OWNED BY glucoseuser.id;


--
-- Name: usertype; Type: TABLE; Schema: public; Owner: mba
--

CREATE TABLE usertype (
    id bigint NOT NULL,
    name character varying(64),
    description character varying(64)
);


ALTER TABLE usertype OWNER TO mba;

--
-- Name: seq_usertype; Type: SEQUENCE; Schema: public; Owner: mba
--

CREATE SEQUENCE seq_usertype
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_usertype OWNER TO mba;

--
-- Name: seq_usertype; Type: SEQUENCE OWNED BY; Schema: public; Owner: mba
--

ALTER SEQUENCE seq_usertype OWNED BY usertype.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: mba
--

ALTER TABLE ONLY glucosemeasurement ALTER COLUMN id SET DEFAULT nextval('seq_glucosemeasurement'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: mba
--

ALTER TABLE ONLY glucoseuser ALTER COLUMN id SET DEFAULT nextval('seq_glucoseuser'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: mba
--

ALTER TABLE ONLY usertype ALTER COLUMN id SET DEFAULT nextval('seq_usertype'::regclass);


--
-- Data for Name: glucosemeasurement; Type: TABLE DATA; Schema: public; Owner: mba
--

COPY glucosemeasurement (id, glucosevalue, measuredate, userid) FROM stdin;
50	7.8	2016-08-23 19:25:26.031	1
\.


--
-- Data for Name: glucoseuser; Type: TABLE DATA; Schema: public; Owner: mba
--

COPY glucoseuser (id, firstname, lastname, username, email, password, usertypeid) FROM stdin;
1	Martin	Bäumer	mba	martin.baeumer@gmail.com	mbpass	1
2	Jonas	Jaconelli	jja	jja@softhouse.se	jjpass	1
3	Lena	Green	leg	leg@softhouse.se	lgpass	1
\.


--
-- Name: seq_glucosemeasurement; Type: SEQUENCE SET; Schema: public; Owner: mba
--

SELECT pg_catalog.setval('seq_glucosemeasurement', 1, true);


--
-- Name: seq_glucoseuser; Type: SEQUENCE SET; Schema: public; Owner: mba
--

SELECT pg_catalog.setval('seq_glucoseuser', 1, false);


--
-- Name: seq_usertype; Type: SEQUENCE SET; Schema: public; Owner: mba
--

SELECT pg_catalog.setval('seq_usertype', 1, false);


--
-- Data for Name: usertype; Type: TABLE DATA; Schema: public; Owner: mba
--

COPY usertype (id, name, description) FROM stdin;
1	User	a normal user
2	Admin	an admin user
\.


--
-- Name: glucosemeasurement_pkey; Type: CONSTRAINT; Schema: public; Owner: mba
--

ALTER TABLE ONLY glucosemeasurement
    ADD CONSTRAINT glucosemeasurement_pkey PRIMARY KEY (id);


--
-- Name: glucoseuser_pkey; Type: CONSTRAINT; Schema: public; Owner: mba
--

ALTER TABLE ONLY glucoseuser
    ADD CONSTRAINT glucoseuser_pkey PRIMARY KEY (id);


--
-- Name: usertype_pkey; Type: CONSTRAINT; Schema: public; Owner: mba
--

ALTER TABLE ONLY usertype
    ADD CONSTRAINT usertype_pkey PRIMARY KEY (id);


--
-- Name: glucosemeasurement_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mba
--

ALTER TABLE ONLY glucosemeasurement
    ADD CONSTRAINT glucosemeasurement_userid_fkey FOREIGN KEY (userid) REFERENCES glucoseuser(id);


--
-- Name: glucoseuser_usertypeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: mba
--

ALTER TABLE ONLY glucoseuser
    ADD CONSTRAINT glucoseuser_usertypeid_fkey FOREIGN KEY (usertypeid) REFERENCES usertype(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: martinbaumer
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM martinbaumer;
GRANT ALL ON SCHEMA public TO martinbaumer;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

