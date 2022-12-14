PGDMP     1                    z            test_market    14.5    14.5     
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            	
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            

           1262    16396    test_market    DATABASE     h   CREATE DATABASE test_market WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE test_market;
                postgres    false            ?            1259    16398    Consumer    TABLE     ?   CREATE TABLE public."Consumer" (
    "firstName" character varying(55),
    "lastName" character varying(55),
    id smallint NOT NULL
);
    DROP TABLE public."Consumer";
       public         heap    postgres    false            ?            1259    16436    Consumer_id_seq    SEQUENCE     z   CREATE SEQUENCE public."Consumer_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Consumer_id_seq";
       public          postgres    false    209            
           0    0    Consumer_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Consumer_id_seq" OWNED BY public."Consumer".id;
          public          postgres    false    214            ?            1259    16401    Product    TABLE     x   CREATE TABLE public."Product" (
    "productName" character varying(55),
    price integer,
    id smallint NOT NULL
);
    DROP TABLE public."Product";
       public         heap    postgres    false            ?            1259    16433    Product_id_seq    SEQUENCE     y   CREATE SEQUENCE public."Product_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public."Product_id_seq";
       public          postgres    false    210            
           0    0    Product_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."Product_id_seq" OWNED BY public."Product".id;
          public          postgres    false    213            ?            1259    16411 	   Purchases    TABLE     ?   CREATE TABLE public."Purchases" (
    id smallint NOT NULL,
    consumer_id smallint,
    product_id smallint,
    date date
);
    DROP TABLE public."Purchases";
       public         heap    postgres    false            ?            1259    16410    Purchases_purchase_id_seq    SEQUENCE     ?   CREATE SEQUENCE public."Purchases_purchase_id_seq"
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public."Purchases_purchase_id_seq";
       public          postgres    false    212            

           0    0    Purchases_purchase_id_seq    SEQUENCE OWNED BY     R   ALTER SEQUENCE public."Purchases_purchase_id_seq" OWNED BY public."Purchases".id;
          public          postgres    false    211            f           2604    16437    Consumer id    DEFAULT     n   ALTER TABLE ONLY public."Consumer" ALTER COLUMN id SET DEFAULT nextval('public."Consumer_id_seq"'::regclass);
 <   ALTER TABLE public."Consumer" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    209            g           2604    16434 
   Product id    DEFAULT     l   ALTER TABLE ONLY public."Product" ALTER COLUMN id SET DEFAULT nextval('public."Product_id_seq"'::regclass);
 ;   ALTER TABLE public."Product" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    210            h           2604    16414    Purchases id    DEFAULT     y   ALTER TABLE ONLY public."Purchases" ALTER COLUMN id SET DEFAULT nextval('public."Purchases_purchase_id_seq"'::regclass);
 =   ALTER TABLE public."Purchases" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            ?          0    16398    Consumer 
   TABLE DATA           A   COPY public."Consumer" ("firstName", "lastName", id) FROM stdin;
    public          postgres    false    209   ?        
          0    16401    Product 
   TABLE DATA           =   COPY public."Product" ("productName", price, id) FROM stdin;
    public          postgres    false    210   W        
          0    16411 	   Purchases 
   TABLE DATA           H   COPY public."Purchases" (id, consumer_id, product_id, date) FROM stdin;
    public          postgres    false    212   ?        
           0    0    Consumer_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Consumer_id_seq"', 1, false);
          public          postgres    false    214            
           0    0    Product_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Product_id_seq"', 1, false);
          public          postgres    false    213            
           0    0    Purchases_purchase_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public."Purchases_purchase_id_seq"', 1, false);
          public          postgres    false    211            k           2606    16406    Consumer consumer_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public."Consumer"
    ADD CONSTRAINT consumer_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Consumer" DROP CONSTRAINT consumer_pk;
       public            postgres    false    209            m           2606    16409    Product product_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT product_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Product" DROP CONSTRAINT product_pk;
       public            postgres    false    210            p           2606    16422    Purchases purchases_pk 
   CONSTRAINT     V   ALTER TABLE ONLY public."Purchases"
    ADD CONSTRAINT purchases_pk PRIMARY KEY (id);
 B   ALTER TABLE ONLY public."Purchases" DROP CONSTRAINT purchases_pk;
       public            postgres    false    212            i           1259    16404    consumer_consumer_id_uindex    INDEX     W   CREATE UNIQUE INDEX consumer_consumer_id_uindex ON public."Consumer" USING btree (id);
 /   DROP INDEX public.consumer_consumer_id_uindex;
       public            postgres    false    209            n           1259    16407    product_product_id_uindex    INDEX     T   CREATE UNIQUE INDEX product_product_id_uindex ON public."Product" USING btree (id);
 -   DROP INDEX public.product_product_id_uindex;
       public            postgres    false    210            q           1259    16420    purchases_purchase_id_uindex    INDEX     Y   CREATE UNIQUE INDEX purchases_purchase_id_uindex ON public."Purchases" USING btree (id);
 0   DROP INDEX public.purchases_purchase_id_uindex;
       public            postgres    false    212            r           2606    16423     Purchases purchases_consumers_fk 
   FK CONSTRAINT     ?   ALTER TABLE ONLY public."Purchases"
    ADD CONSTRAINT purchases_consumers_fk FOREIGN KEY (consumer_id) REFERENCES public."Consumer"(id);
 L   ALTER TABLE ONLY public."Purchases" DROP CONSTRAINT purchases_consumers_fk;
       public          postgres    false    209    3179    212            s           2606    16428    Purchases purchases_products_fk 
   FK CONSTRAINT     ?   ALTER TABLE ONLY public."Purchases"
    ADD CONSTRAINT purchases_products_fk FOREIGN KEY (product_id) REFERENCES public."Product"(id);
 K   ALTER TABLE ONLY public."Purchases" DROP CONSTRAINT purchases_products_fk;
       public          postgres    false    3181    212    210            ?   [   x??0?¦.??? e\?wa?!ׅ?v\?ua?ŦP匸???{.?s??\?w?,k?uaH??f???@q?????&\1z\\\ ??I       
   ?   x?%?;?@Dk?? 9???0!? ?G4?l+???1+?7~??3F?L
?+yD???fw|?????acR*????T?Jq??c?????,????u?l??f???IMh??GVi??y?S?^Ý???RA      
   \   x?eι
?@??ۋ-X???_???@ю???(
y˺E?3X?e??A????=È?~?0+8V??/??Z??"?#??B[?M??> >?")?     