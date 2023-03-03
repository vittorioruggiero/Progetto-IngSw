--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2023-03-03 16:14:28

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
-- TOC entry 200 (class 1259 OID 17886)
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
-- TOC entry 201 (class 1259 OID 17890)
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
-- TOC entry 202 (class 1259 OID 17894)
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
-- TOC entry 203 (class 1259 OID 17897)
-- Name: sequence_avviso; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_avviso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 204 (class 1259 OID 17899)
-- Name: avviso; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.avviso (
    testo character varying(200) NOT NULL,
    id_avviso integer DEFAULT nextval('public.sequence_avviso'::regclass) NOT NULL,
    attivita character varying(50) NOT NULL,
    indirizzo_attivita character varying(100) NOT NULL
);


--
-- TOC entry 205 (class 1259 OID 17903)
-- Name: sequence_conto; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_conto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 206 (class 1259 OID 17905)
-- Name: conto; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.conto (
    id_conto integer DEFAULT nextval('public.sequence_conto'::regclass) NOT NULL,
    data date NOT NULL,
    importo double precision NOT NULL,
    stato boolean NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 17909)
-- Name: sequence_ordinazione; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_ordinazione
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 208 (class 1259 OID 17911)
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
-- TOC entry 210 (class 1259 OID 17920)
-- Name: prodottomenu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.prodottomenu (
    nome character varying(30) NOT NULL,
    descrizione character varying(100) NOT NULL,
    costo double precision NOT NULL,
    nome_seconda_lingua character varying(30),
    descrizione_seconda_lingua character varying(100),
    allergeni character varying(100)[] NOT NULL,
    sezione character varying(30) NOT NULL
);


--
-- TOC entry 209 (class 1259 OID 17918)
-- Name: sequence_prodotto; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_prodotto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 211 (class 1259 OID 17927)
-- Name: sequence_sezione; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_sezione
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 212 (class 1259 OID 17929)
-- Name: sequence_singolo_ordine; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sequence_singolo_ordine
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 213 (class 1259 OID 17931)
-- Name: sezionemenu; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sezionemenu (
    nome character varying(30) NOT NULL,
    attivita character varying(50) NOT NULL,
    indirizzo_attivita character varying(100) NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 17938)
-- Name: singolo_ordine; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.singolo_ordine (
    quantita_prodotto integer NOT NULL,
    id_singolo_ordine integer DEFAULT nextval('public.sequence_singolo_ordine'::regclass) NOT NULL,
    prodotto character varying(30) NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 17942)
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
-- TOC entry 3072 (class 0 OID 17886)
-- Dependencies: 200
-- Data for Name: addettosala; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.addettosala (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('addettosala1@boh.com', 'AddettoSala1', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.addettosala (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('addettosala2@boh.com', 'AddettoSala2', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3073 (class 0 OID 17890)
-- Dependencies: 201
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.amministratore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('admin1@boh.com', 'MarcoRossi', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.amministratore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('admin2@boh.com', 'LuigiVerdi', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3074 (class 0 OID 17894)
-- Dependencies: 202
-- Data for Name: attivita; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.attivita (nome, indirizzo, telefono, citta, capienza) VALUES ('Pizzeria da Luigi', 'Via Roma 63', '66666666', 'Napoli', 10);


--
-- TOC entry 3076 (class 0 OID 17899)
-- Dependencies: 204
-- Data for Name: avviso; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.avviso (testo, id_avviso, attivita, indirizzo_attivita) VALUES ('Ciao', 1, 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.avviso (testo, id_avviso, attivita, indirizzo_attivita) VALUES ('Ciao ciao', 2, 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3078 (class 0 OID 17905)
-- Dependencies: 206
-- Data for Name: conto; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3080 (class 0 OID 17911)
-- Dependencies: 208
-- Data for Name: ordinazione; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3082 (class 0 OID 17920)
-- Dependencies: 210
-- Data for Name: prodottomenu; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3085 (class 0 OID 17931)
-- Dependencies: 213
-- Data for Name: sezionemenu; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3086 (class 0 OID 17938)
-- Dependencies: 214
-- Data for Name: singolo_ordine; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3087 (class 0 OID 17942)
-- Dependencies: 215
-- Data for Name: supervisore; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.supervisore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('supervisore1@boh.com', 'Supervisore1', 'nonloso01', 'Pizzeria da Luigi', 'Via Roma 63');
INSERT INTO public.supervisore (email, nomeutente, password, attivita, indirizzo_attivita) VALUES ('supervisore2@boh.com', 'Supervisore2', 'nonloso02', 'Pizzeria da Luigi', 'Via Roma 63');


--
-- TOC entry 3093 (class 0 OID 0)
-- Dependencies: 203
-- Name: sequence_avviso; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_avviso', 2, true);


--
-- TOC entry 3094 (class 0 OID 0)
-- Dependencies: 205
-- Name: sequence_conto; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_conto', 1, false);


--
-- TOC entry 3095 (class 0 OID 0)
-- Dependencies: 207
-- Name: sequence_ordinazione; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_ordinazione', 1, false);


--
-- TOC entry 3096 (class 0 OID 0)
-- Dependencies: 209
-- Name: sequence_prodotto; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_prodotto', 1, false);


--
-- TOC entry 3097 (class 0 OID 0)
-- Dependencies: 211
-- Name: sequence_sezione; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_sezione', 1, false);


--
-- TOC entry 3098 (class 0 OID 0)
-- Dependencies: 212
-- Name: sequence_singolo_ordine; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.sequence_singolo_ordine', 1, false);


--
-- TOC entry 2907 (class 2606 OID 17947)
-- Name: addettosala addettosala_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT addettosala_pkey PRIMARY KEY (email);


--
-- TOC entry 2911 (class 2606 OID 17949)
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (email);


--
-- TOC entry 2915 (class 2606 OID 17951)
-- Name: attivita attivita_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.attivita
    ADD CONSTRAINT attivita_pkey PRIMARY KEY (nome, indirizzo);


--
-- TOC entry 2917 (class 2606 OID 17953)
-- Name: avviso avviso_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.avviso
    ADD CONSTRAINT avviso_pkey PRIMARY KEY (id_avviso);


--
-- TOC entry 2919 (class 2606 OID 17955)
-- Name: conto conto_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.conto
    ADD CONSTRAINT conto_pkey PRIMARY KEY (id_conto);


--
-- TOC entry 2921 (class 2606 OID 17957)
-- Name: ordinazione ordinazione_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordinazione
    ADD CONSTRAINT ordinazione_pkey PRIMARY KEY (id_ordinazione);


--
-- TOC entry 2923 (class 2606 OID 18006)
-- Name: prodottomenu prodottomenu_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT prodottomenu_pkey PRIMARY KEY (nome);


--
-- TOC entry 2927 (class 2606 OID 18004)
-- Name: sezionemenu sezionemenu_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sezionemenu
    ADD CONSTRAINT sezionemenu_pkey PRIMARY KEY (nome);


--
-- TOC entry 2929 (class 2606 OID 17963)
-- Name: singolo_ordine singoloordine_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.singolo_ordine
    ADD CONSTRAINT singoloordine_pkey PRIMARY KEY (id_singolo_ordine);


--
-- TOC entry 2931 (class 2606 OID 17965)
-- Name: supervisore supervisore_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT supervisore_pkey PRIMARY KEY (email);


--
-- TOC entry 2925 (class 2606 OID 17969)
-- Name: prodottomenu unique_nome_seconda_lingua; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT unique_nome_seconda_lingua UNIQUE (nome_seconda_lingua);


--
-- TOC entry 2909 (class 2606 OID 17973)
-- Name: addettosala unique_nomeutente_addettosala; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT unique_nomeutente_addettosala UNIQUE (nomeutente);


--
-- TOC entry 2913 (class 2606 OID 17975)
-- Name: amministratore unique_nomeutente_amministratore; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT unique_nomeutente_amministratore UNIQUE (nomeutente);


--
-- TOC entry 2933 (class 2606 OID 17977)
-- Name: supervisore unique_nomeutente_supervisore; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT unique_nomeutente_supervisore UNIQUE (nomeutente);


--
-- TOC entry 2935 (class 2606 OID 17978)
-- Name: amministratore attivita_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT attivita_fkey FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2941 (class 2606 OID 17983)
-- Name: supervisore attivita_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.supervisore
    ADD CONSTRAINT attivita_fkey FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2934 (class 2606 OID 17988)
-- Name: addettosala attivita_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.addettosala
    ADD CONSTRAINT attivita_fkey FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2939 (class 2606 OID 18012)
-- Name: sezionemenu attivita_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sezionemenu
    ADD CONSTRAINT attivita_fkey FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2936 (class 2606 OID 18027)
-- Name: avviso attivita_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.avviso
    ADD CONSTRAINT attivita_fkey FOREIGN KEY (attivita, indirizzo_attivita) REFERENCES public.attivita(nome, indirizzo);


--
-- TOC entry 2937 (class 2606 OID 17993)
-- Name: ordinazione conto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.ordinazione
    ADD CONSTRAINT conto_fkey FOREIGN KEY (id_conto) REFERENCES public.conto(id_conto) NOT VALID;


--
-- TOC entry 2940 (class 2606 OID 18017)
-- Name: singolo_ordine prodotto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.singolo_ordine
    ADD CONSTRAINT prodotto_fkey FOREIGN KEY (prodotto) REFERENCES public.prodottomenu(nome);


--
-- TOC entry 2938 (class 2606 OID 18007)
-- Name: prodottomenu sezionemenu_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.prodottomenu
    ADD CONSTRAINT sezionemenu_fkey FOREIGN KEY (sezione) REFERENCES public.sezionemenu(nome);


-- Completed on 2023-03-03 16:14:28

--
-- PostgreSQL database dump complete
--

