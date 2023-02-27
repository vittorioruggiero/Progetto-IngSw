--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2023-02-27 21:36:51

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 17820)
-- Name: addettosala; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.addettosala (
    email character varying(50) NOT NULL,
    nomeutente character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 17810)
-- Name: amministratore; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.amministratore (
    email character varying(50) NOT NULL,
    nomeutente character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


--
-- TOC entry 201 (class 1259 OID 17815)
-- Name: supervisore; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.supervisore (
    email character varying(50) NOT NULL,
    nomeutente character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


--
-- TOC entry 2994 (class 0 OID 17820)
-- Dependencies: 202
-- Data for Name: addettosala; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2992 (class 0 OID 17810)
-- Dependencies: 200
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2993 (class 0 OID 17815)
-- Dependencies: 201
-- Data for Name: supervisore; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2861 (class 2606 OID 17824)
-- Name: addettosala addettosala_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT addettosala_pkey PRIMARY KEY (email);


--
-- TOC entry 2857 (class 2606 OID 17814)
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (email);


--
-- TOC entry 2859 (class 2606 OID 17819)
-- Name: supervisore supervisore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT supervisore_pkey PRIMARY KEY (email);


-- Completed on 2023-02-27 21:36:51

--
-- PostgreSQL database dump complete
--

