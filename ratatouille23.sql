--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-03-01 13:10:42

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

DROP DATABASE "Ratatouille23";
--
-- TOC entry 3423 (class 1262 OID 24590)
-- Name: Ratatouille23; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "Ratatouille23" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Italian_Italy.1252';


\connect "Ratatouille23"

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
-- TOC entry 214 (class 1259 OID 24591)
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
-- TOC entry 215 (class 1259 OID 24595)
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
-- TOC entry 216 (class 1259 OID 24599)
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
-- TOC entry 217 (class 1259 OID 24602)
-- Name: sequence_avviso; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_avviso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 218 (class 1259 OID 24603)
-- Name: avviso; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.avviso (
    testo character varying(200) NOT NULL,
    id_avviso integer DEFAULT nextval('public.sequence_avviso'::regclass) NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 24723)
-- Name: sequence_conto; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_conto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 228 (class 1259 OID 24713)
-- Name: conto; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.conto (
    id_conto integer DEFAULT nextval('public.sequence_conto'::regclass) NOT NULL
);


--
-- TOC entry 227 (class 1259 OID 24711)
-- Name: sequence_ordinazione; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_ordinazione
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 226 (class 1259 OID 24704)
-- Name: ordinazione; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.ordinazione (
    id_ordinazione integer DEFAULT nextval('public.sequence_ordinazione'::regclass) NOT NULL,
    lista_prodotti integer[] NOT NULL,
    numero_tavolo integer,
    numero_commensali integer,
    id_conto integer
);


--
-- TOC entry 222 (class 1259 OID 24670)
-- Name: sequence_prodotto; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_prodotto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 223 (class 1259 OID 24673)
-- Name: prodottomenu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.prodottomenu (
    id_prodotto integer DEFAULT nextval('public.sequence_prodotto'::regclass) NOT NULL,
    nome character varying(30) NOT NULL,
    descrizione character varying(100) NOT NULL,
    costo double precision NOT NULL,
    nome_seconda_lingua character varying(30),
    descrizione_seconda_lingua character varying(100),
    allergeni character varying(100)[] NOT NULL,
    id_sezione integer NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 24654)
-- Name: sequence_sezione; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_sezione
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 224 (class 1259 OID 24692)
-- Name: sequence_singolo_ordine; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_singolo_ordine
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 221 (class 1259 OID 24661)
-- Name: sezionemenu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sezionemenu (
    id_sezione integer DEFAULT nextval('public.sequence_sezione'::regclass) NOT NULL,
    nome character varying(30) NOT NULL,
    id_prodotti integer[]
);


--
-- TOC entry 225 (class 1259 OID 24693)
-- Name: singolo_ordine; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.singolo_ordine (
    id_prodotto integer NOT NULL,
    quantita_prodotto integer NOT NULL,
    id_singolo_ordine integer DEFAULT nextval('public.sequence_singolo_ordine'::regclass) NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 24607)
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
-- TOC entry 3402 (class 0 OID 24591)
-- Dependencies: 214
-- Data for Name: addettosala; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.addettosala (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('addettosala1@boh.com', 'AddettoSala1', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.addettosala (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('addettosala2@boh.com', 'AddettoSala2', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3403 (class 0 OID 24595)
-- Dependencies: 215
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.amministratore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('admin1@boh.com', 'MarcoRossi', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.amministratore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('admin2@boh.com', 'LuigiVerdi', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3404 (class 0 OID 24599)
-- Dependencies: 216
-- Data for Name: attivita; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.attivita (nome, indirizzo, telefono, citta, capienza) VALUES ('Pizzeria da Luigi', 'Via Roma 63', '66666666', 'Napoli', 10);


--
-- TOC entry 3406 (class 0 OID 24603)
-- Dependencies: 218
-- Data for Name: avviso; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.avviso (testo, id_avviso) VALUES ('Ciao', 1);
INSERT INTO public.avviso (testo, id_avviso) VALUES ('Ciao ciao', 2);


--
-- TOC entry 3416 (class 0 OID 24713)
-- Dependencies: 228
-- Data for Name: conto; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3414 (class 0 OID 24704)
-- Dependencies: 226
-- Data for Name: ordinazione; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3411 (class 0 OID 24673)
-- Dependencies: 223
-- Data for Name: prodottomenu; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3409 (class 0 OID 24661)
-- Dependencies: 221
-- Data for Name: sezionemenu; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3413 (class 0 OID 24693)
-- Dependencies: 225
-- Data for Name: singolo_ordine; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3407 (class 0 OID 24607)
-- Dependencies: 219
-- Data for Name: supervisore; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.supervisore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('supervisore1@boh.com', 'Supervisore1', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.supervisore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('supervisore2@boh.com', 'Supervisore2', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 217
-- Name: sequence_avviso; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_avviso', 2, true);


--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 229
-- Name: sequence_conto; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_conto', 1, false);


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 227
-- Name: sequence_ordinazione; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_ordinazione', 1, false);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 222
-- Name: sequence_prodotto; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_prodotto', 1, false);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 220
-- Name: sequence_sezione; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_sezione', 1, false);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 224
-- Name: sequence_singolo_ordine; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_singolo_ordine', 1, false);


--
-- TOC entry 3224 (class 2606 OID 24612)
-- Name: addettosala addettosala_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT addettosala_pkey PRIMARY KEY (email);


--
-- TOC entry 3228 (class 2606 OID 24614)
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (email);


--
-- TOC entry 3232 (class 2606 OID 24616)
-- Name: attivita attivita_primary_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.attivita
    ADD CONSTRAINT attivita_primary_key PRIMARY KEY (nome, indirizzo);


--
-- TOC entry 3234 (class 2606 OID 24618)
-- Name: avviso avviso_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.avviso
    ADD CONSTRAINT avviso_pkey PRIMARY KEY (id_avviso);


--
-- TOC entry 3254 (class 2606 OID 24717)
-- Name: conto conto_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.conto
    ADD CONSTRAINT conto_pkey PRIMARY KEY (id_conto);


--
-- TOC entry 3252 (class 2606 OID 24710)
-- Name: ordinazione ordinazione_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordinazione
    ADD CONSTRAINT ordinazione_pkey PRIMARY KEY (id_ordinazione);


--
-- TOC entry 3244 (class 2606 OID 24679)
-- Name: prodottomenu prodotto_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT prodotto_pkey PRIMARY KEY (id_prodotto);


--
-- TOC entry 3240 (class 2606 OID 24667)
-- Name: sezionemenu sezione_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sezionemenu
    ADD CONSTRAINT sezione_pkey PRIMARY KEY (id_sezione);


--
-- TOC entry 3250 (class 2606 OID 24697)
-- Name: singolo_ordine singoloordine_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.singolo_ordine
    ADD CONSTRAINT singoloordine_pkey PRIMARY KEY (id_singolo_ordine);


--
-- TOC entry 3236 (class 2606 OID 24620)
-- Name: supervisore supervisore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT supervisore_pkey PRIMARY KEY (email);


--
-- TOC entry 3246 (class 2606 OID 24681)
-- Name: prodottomenu unique_nome; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT unique_nome UNIQUE (nome);


--
-- TOC entry 3248 (class 2606 OID 24683)
-- Name: prodottomenu unique_nome_seconda_lingua; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT unique_nome_seconda_lingua UNIQUE (nome_seconda_lingua);


--
-- TOC entry 3242 (class 2606 OID 24686)
-- Name: sezionemenu unique_nome_sezione; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sezionemenu
    ADD CONSTRAINT unique_nome_sezione UNIQUE (nome);


--
-- TOC entry 3226 (class 2606 OID 24622)
-- Name: addettosala unique_nomeutente_addettosala; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT unique_nomeutente_addettosala UNIQUE (nomeutente);


--
-- TOC entry 3230 (class 2606 OID 24624)
-- Name: amministratore unique_nomeutente_amministratore; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT unique_nomeutente_amministratore UNIQUE (nomeutente);


--
-- TOC entry 3238 (class 2606 OID 24626)
-- Name: supervisore unique_nomeutente_supervisore; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT unique_nomeutente_supervisore UNIQUE (nomeutente);


--
-- TOC entry 3256 (class 2606 OID 24627)
-- Name: amministratore attivita_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT attivita_fk FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 3257 (class 2606 OID 24632)
-- Name: supervisore attivita_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT attivita_fk FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 3255 (class 2606 OID 24637)
-- Name: addettosala attivita_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT attivita_fk FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 3259 (class 2606 OID 24718)
-- Name: ordinazione conto_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordinazione
    ADD CONSTRAINT conto_fk FOREIGN KEY (id_conto) REFERENCES public.conto(id_conto) NOT VALID;


--
-- TOC entry 3258 (class 2606 OID 24687)
-- Name: prodottomenu sezione_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT sezione_fk FOREIGN KEY (id_sezione) REFERENCES public.sezionemenu(id_sezione) NOT VALID;


-- Completed on 2023-03-01 13:10:42

--
-- PostgreSQL database dump complete
--

