--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2023-02-28 19:15:17

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
-- TOC entry 200 (class 1259 OID 17826)
-- Name: addettosala; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.addettosala (
    email character varying(50) NOT NULL,
    nomeutente character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    attivita character varying(50) NOT NULL,
    indirizzo_attivita character varying(100) NOT NULL,
    CONSTRAINT email_valida CHECK (((email)::text ~ '^.+@[^\.].*\.[a-z]{2,}$'::text))
);


--
-- TOC entry 201 (class 1259 OID 17829)
-- Name: amministratore; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.amministratore (
    email character varying(50) NOT NULL,
    nomeutente character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    attivita character varying(50) NOT NULL,
    indirizzo_attivita character varying(100) NOT NULL,
    CONSTRAINT email_valida CHECK (((email)::text ~ '^.+@[^\.].*\.[a-z]{2,}$'::text))
);


--
-- TOC entry 205 (class 1259 OID 17865)
-- Name: attivita; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.attivita (
    nome character varying(50) NOT NULL,
    indirizzo character varying(100) NOT NULL,
    telefono character varying(9) NOT NULL,
    citta character varying(20) NOT NULL,
    capienza integer NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 17851)
-- Name: sequence_avviso; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_avviso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 204 (class 1259 OID 17853)
-- Name: avviso; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.avviso (
    testo character varying(200) NOT NULL,
    id_avviso integer DEFAULT nextval('public.sequence_avviso'::regclass) NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 17832)
-- Name: supervisore; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.supervisore (
    email character varying(50) NOT NULL,
    nomeutente character varying(20) NOT NULL,
    password character varying(20) NOT NULL,
    attivita character varying(50) NOT NULL,
    indirizzo_attivita character varying(100) NOT NULL,
    CONSTRAINT email_valida CHECK (((email)::text ~ '^.+@[^\.].*\.[a-z]{2,}$'::text))
);


--
-- TOC entry 3019 (class 0 OID 17826)
-- Dependencies: 200
-- Data for Name: addettosala; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.addettosala (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('addettosala1@boh.com', 'AddettoSala1', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.addettosala (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('addettosala2@boh.com', 'AddettoSala2', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3020 (class 0 OID 17829)
-- Dependencies: 201
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.amministratore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('admin1@boh.com', 'MarcoRossi', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.amministratore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('admin2@boh.com', 'LuigiVerdi', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3024 (class 0 OID 17865)
-- Dependencies: 205
-- Data for Name: attivita; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.attivita (nome, indirizzo, telefono, citta, capienza) VALUES ('Pizzeria da Luigi', 'Via Roma 63', '66666666', 'Napoli', 10);


--
-- TOC entry 3023 (class 0 OID 17853)
-- Dependencies: 204
-- Data for Name: avviso; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.avviso (testo, id_avviso) VALUES ('Ciao', 1);
INSERT INTO public.avviso (testo, id_avviso) VALUES ('Ciao ciao', 2);


--
-- TOC entry 3021 (class 0 OID 17832)
-- Dependencies: 202
-- Data for Name: supervisore; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.supervisore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('supervisore1@boh.com', 'Supervisore1', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.supervisore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('supervisore2@boh.com', 'Supervisore2', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 203
-- Name: sequence_avviso; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_avviso', 2, true);


--
-- TOC entry 2871 (class 2606 OID 17836)
-- Name: addettosala addettosala_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT addettosala_pkey PRIMARY KEY (email);


--
-- TOC entry 2875 (class 2606 OID 17838)
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (email);


--
-- TOC entry 2885 (class 2606 OID 17869)
-- Name: attivita attivita_primary_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.attivita
    ADD CONSTRAINT attivita_primary_key PRIMARY KEY (nome, indirizzo);


--
-- TOC entry 2883 (class 2606 OID 17860)
-- Name: avviso avviso_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.avviso
    ADD CONSTRAINT avviso_pkey PRIMARY KEY (id_avviso);


--
-- TOC entry 2879 (class 2606 OID 17840)
-- Name: supervisore supervisore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT supervisore_pkey PRIMARY KEY (email);


--
-- TOC entry 2873 (class 2606 OID 17846)
-- Name: addettosala unique_nomeutente_addettosala; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT unique_nomeutente_addettosala UNIQUE (nomeutente);


--
-- TOC entry 2877 (class 2606 OID 17842)
-- Name: amministratore unique_nomeutente_amministratore; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT unique_nomeutente_amministratore UNIQUE (nomeutente);


--
-- TOC entry 2881 (class 2606 OID 17844)
-- Name: supervisore unique_nomeutente_supervisore; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT unique_nomeutente_supervisore UNIQUE (nomeutente);


--
-- TOC entry 2887 (class 2606 OID 17870)
-- Name: amministratore attivita_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT attivita_fk FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2888 (class 2606 OID 17875)
-- Name: supervisore attivita_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT attivita_fk FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2886 (class 2606 OID 17880)
-- Name: addettosala attivita_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT attivita_fk FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


-- Completed on 2023-02-28 19:15:18

--
-- PostgreSQL database dump complete
--

