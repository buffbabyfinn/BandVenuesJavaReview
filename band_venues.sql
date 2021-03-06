PGDMP     %    /                t            band_venues    9.3.11    9.3.11     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    16667    band_venues    DATABASE     �   CREATE DATABASE band_venues WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE band_venues;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            �           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    11750    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16670    bands    TABLE     L   CREATE TABLE bands (
    id integer NOT NULL,
    name character varying
);
    DROP TABLE public.bands;
       public         postgres    false    6            �            1259    16668    bands_id_seq    SEQUENCE     n   CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.bands_id_seq;
       public       postgres    false    172    6            �           0    0    bands_id_seq    SEQUENCE OWNED BY     /   ALTER SEQUENCE bands_id_seq OWNED BY bands.id;
            public       postgres    false    171            �            1259    16693    bands_venues    TABLE     b   CREATE TABLE bands_venues (
    id integer NOT NULL,
    band_id integer,
    venue_id integer
);
     DROP TABLE public.bands_venues;
       public         postgres    false    6            �            1259    16691    bands_venues_id_seq    SEQUENCE     u   CREATE SEQUENCE bands_venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.bands_venues_id_seq;
       public       postgres    false    6    176            �           0    0    bands_venues_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE bands_venues_id_seq OWNED BY bands_venues.id;
            public       postgres    false    175            �            1259    16681    venues    TABLE     s   CREATE TABLE venues (
    id integer NOT NULL,
    venue_name character varying,
    location character varying
);
    DROP TABLE public.venues;
       public         postgres    false    6            �            1259    16679    venues_id_seq    SEQUENCE     o   CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.venues_id_seq;
       public       postgres    false    6    174            �           0    0    venues_id_seq    SEQUENCE OWNED BY     1   ALTER SEQUENCE venues_id_seq OWNED BY venues.id;
            public       postgres    false    173            -           2604    16673    id    DEFAULT     V   ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);
 7   ALTER TABLE public.bands ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    172    171    172            /           2604    16696    id    DEFAULT     d   ALTER TABLE ONLY bands_venues ALTER COLUMN id SET DEFAULT nextval('bands_venues_id_seq'::regclass);
 >   ALTER TABLE public.bands_venues ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    176    175    176            .           2604    16684    id    DEFAULT     X   ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);
 8   ALTER TABLE public.venues ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    173    174    174            �          0    16670    bands 
   TABLE DATA               "   COPY bands (id, name) FROM stdin;
    public       postgres    false    172   �       �           0    0    bands_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('bands_id_seq', 1, false);
            public       postgres    false    171            �          0    16693    bands_venues 
   TABLE DATA               6   COPY bands_venues (id, band_id, venue_id) FROM stdin;
    public       postgres    false    176   �       �           0    0    bands_venues_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('bands_venues_id_seq', 1, false);
            public       postgres    false    175            �          0    16681    venues 
   TABLE DATA               3   COPY venues (id, venue_name, location) FROM stdin;
    public       postgres    false    174   �       �           0    0    venues_id_seq    SEQUENCE SET     5   SELECT pg_catalog.setval('venues_id_seq', 1, false);
            public       postgres    false    173            1           2606    16678 
   bands_pkey 
   CONSTRAINT     G   ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.bands DROP CONSTRAINT bands_pkey;
       public         postgres    false    172    172            5           2606    16698    bands_venues_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY bands_venues
    ADD CONSTRAINT bands_venues_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.bands_venues DROP CONSTRAINT bands_venues_pkey;
       public         postgres    false    176    176            3           2606    16689    venues_pkey 
   CONSTRAINT     I   ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.venues DROP CONSTRAINT venues_pkey;
       public         postgres    false    174    174            �      x������ � �      �      x������ � �      �      x������ � �     