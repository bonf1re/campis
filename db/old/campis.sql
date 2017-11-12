--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: campis; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA campis;


ALTER SCHEMA campis OWNER TO postgres;

SET search_path = campis, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: area; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE area (
    id_area character varying NOT NULL,
    name character varying NOT NULL,
    length integer NOT NULL,
    width integer NOT NULL,
    pos_x integer NOT NULL,
    pos_y integer NOT NULL,
    id_warehouse integer NOT NULL
);


ALTER TABLE area OWNER TO postgres;

--
-- Name: batch; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE batch (
    id_batch integer NOT NULL,
    quantity integer NOT NULL,
    batch_cost double precision,
    arrival_date timestamp without time zone DEFAULT now() NOT NULL,
    expiration_date timestamp without time zone,
    id_product integer NOT NULL,
    type_batch integer DEFAULT 1,
    id_group_batch integer NOT NULL,
    location character varying,
    state boolean,
    heritage jsonb,
    id_unit integer
);


ALTER TABLE batch OWNER TO postgres;

--
-- Name: batch_id_batch_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE batch_id_batch_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE batch_id_batch_seq OWNER TO postgres;

--
-- Name: batch_id_batch_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE batch_id_batch_seq OWNED BY batch.id_batch;


--
-- Name: client; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE client (
    id_client integer NOT NULL,
    name character varying NOT NULL,
    dni character varying,
    ruc character varying,
    active boolean DEFAULT true NOT NULL,
    address character varying,
    phone character varying,
    email character varying,
    priority integer,
    id_district integer
);


ALTER TABLE client OWNER TO postgres;

--
-- Name: client_id_client_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE client_id_client_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE client_id_client_seq OWNER TO postgres;

--
-- Name: client_id_client_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE client_id_client_seq OWNED BY client.id_client;


--
-- Name: disclaim_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE disclaim_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE disclaim_seq OWNER TO postgres;

--
-- Name: complaint; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE complaint (
    id_complaint integer DEFAULT nextval('disclaim_seq'::regclass) NOT NULL,
    description character varying NOT NULL,
    status character varying,
    id_request_order integer
);


ALTER TABLE complaint OWNER TO postgres;

--
-- Name: dispatch_order; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE dispatch_order (
    id_dispatch_order integer NOT NULL,
    id_request_order integer NOT NULL,
    priority integer DEFAULT 1 NOT NULL,
    status character varying
);


ALTER TABLE dispatch_order OWNER TO postgres;

--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE dispatch_order_id_dispatch_order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dispatch_order_id_dispatch_order_seq OWNER TO postgres;

--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE dispatch_order_id_dispatch_order_seq OWNED BY dispatch_order.id_dispatch_order;


--
-- Name: dispatch_order_line; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE dispatch_order_line (
    id_dispatch_order_line integer NOT NULL,
    id_dispatch_order integer NOT NULL,
    id_product integer NOT NULL,
    quantity integer
);


ALTER TABLE dispatch_order_line OWNER TO postgres;

--
-- Name: dispatch_order_line_id_dispatch_order_line_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE dispatch_order_line_id_dispatch_order_line_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dispatch_order_line_id_dispatch_order_line_seq OWNER TO postgres;

--
-- Name: dispatch_order_line_id_dispatch_order_line_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE dispatch_order_line_id_dispatch_order_line_seq OWNED BY dispatch_order_line.id_dispatch_order_line;


--
-- Name: district; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE district (
    id_district integer NOT NULL,
    name character varying,
    freight double precision
);


ALTER TABLE district OWNER TO postgres;

--
-- Name: document; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE document (
    id_document integer NOT NULL,
    id_request_order integer NOT NULL,
    doc_type character varying NOT NULL,
    total_amount character varying NOT NULL
);


ALTER TABLE document OWNER TO postgres;

--
-- Name: document_id_document_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE document_id_document_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE document_id_document_seq OWNER TO postgres;

--
-- Name: document_id_document_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE document_id_document_seq OWNED BY document.id_document;


--
-- Name: group_batch_id_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE group_batch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE group_batch_id_seq OWNER TO postgres;

--
-- Name: group_batch; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE group_batch (
    id_group_batch integer DEFAULT nextval('group_batch_id_seq'::regclass) NOT NULL,
    type_owner integer,
    id_owner integer,
    arrival_date timestamp without time zone DEFAULT now() NOT NULL,
    reason integer
);


ALTER TABLE group_batch OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: movement; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE movement (
    id_movement integer NOT NULL,
    mov_date timestamp without time zone DEFAULT now() NOT NULL,
    id_user integer NOT NULL,
    quantity integer NOT NULL,
    id_vehicle integer NOT NULL,
    mov_type integer,
    id_warehouse integer,
    id_zone integer
);


ALTER TABLE movement OWNER TO postgres;

--
-- Name: movement_id_movement_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE movement_id_movement_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE movement_id_movement_seq OWNER TO postgres;

--
-- Name: movement_id_movement_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE movement_id_movement_seq OWNED BY movement.id_movement;


--
-- Name: movementxbatch; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE movementxbatch (
    id_batch integer NOT NULL,
    id_movement integer NOT NULL
);


ALTER TABLE movementxbatch OWNER TO postgres;

--
-- Name: movementxdispatch; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE movementxdispatch (
    id_movement integer NOT NULL,
    id_dispatch_order_line integer NOT NULL
);


ALTER TABLE movementxdispatch OWNER TO postgres;

--
-- Name: movementxdocument; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE movementxdocument (
    id_document integer NOT NULL,
    id_movement integer NOT NULL
);


ALTER TABLE movementxdocument OWNER TO postgres;

--
-- Name: permission_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE permission_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE permission_seq OWNER TO postgres;

--
-- Name: permission; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE permission (
    id_view integer NOT NULL,
    id_role integer NOT NULL,
    visualize boolean DEFAULT true NOT NULL,
    modify boolean DEFAULT true NOT NULL,
    id_permission integer DEFAULT nextval('permission_seq'::regclass) NOT NULL
);


ALTER TABLE permission OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE product (
    id_product integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    p_stock integer DEFAULT 0 NOT NULL,
    c_stock integer DEFAULT 0 NOT NULL,
    weight double precision DEFAULT 0 NOT NULL,
    trademark character varying,
    base_price double precision NOT NULL,
    id_unit_of_measure integer NOT NULL,
    id_product_type integer NOT NULL
);


ALTER TABLE product OWNER TO postgres;

--
-- Name: product_id_product_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE product_id_product_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_id_product_seq OWNER TO postgres;

--
-- Name: product_id_product_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE product_id_product_seq OWNED BY product.id_product;


--
-- Name: product_type; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE product_type (
    id_product_type integer NOT NULL,
    description character varying NOT NULL,
    descripcion character varying(255)
);


ALTER TABLE product_type OWNER TO postgres;

--
-- Name: product_type_id_product_type_seq_1; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE product_type_id_product_type_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE product_type_id_product_type_seq_1 OWNER TO postgres;

--
-- Name: product_type_id_product_type_seq_1; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE product_type_id_product_type_seq_1 OWNED BY product_type.id_product_type;


--
-- Name: rack; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE rack (
    id_rack integer NOT NULL,
    id_warehouse integer NOT NULL,
    pos_x integer NOT NULL,
    pos_y integer NOT NULL,
    n_columns integer NOT NULL,
    n_floors integer NOT NULL,
    orientation integer
);


ALTER TABLE rack OWNER TO postgres;

--
-- Name: rack_id_rack_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE rack_id_rack_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE rack_id_rack_seq OWNER TO postgres;

--
-- Name: rack_id_rack_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE rack_id_rack_seq OWNED BY rack.id_rack;


--
-- Name: refund_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE refund_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE refund_seq OWNER TO postgres;

--
-- Name: refund; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE refund (
    id_refund integer DEFAULT nextval('refund_seq'::regclass) NOT NULL,
    id_complaint integer,
    status character varying,
    creation_date timestamp without time zone
);


ALTER TABLE refund OWNER TO postgres;

--
-- Name: refund_line_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE refund_line_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE refund_line_seq OWNER TO postgres;

--
-- Name: refund_line; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE refund_line (
    id_refund_line integer DEFAULT nextval('refund_line_seq'::regclass) NOT NULL,
    id_refund integer,
    id_product integer,
    quantity integer
);


ALTER TABLE refund_line OWNER TO postgres;

--
-- Name: request_order; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE request_order (
    id_request_order integer NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL,
    delivery_date timestamp without time zone,
    base_amount double precision,
    total_amount double precision,
    status character varying,
    id_client integer NOT NULL
);


ALTER TABLE request_order OWNER TO postgres;

--
-- Name: request_order_id_request_order_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE request_order_id_request_order_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE request_order_id_request_order_seq OWNER TO postgres;

--
-- Name: request_order_id_request_order_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE request_order_id_request_order_seq OWNED BY request_order.id_request_order;


--
-- Name: request_order_line; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE request_order_line (
    id_request_order_line integer NOT NULL,
    quantity integer,
    cost double precision,
    id_request_order integer NOT NULL,
    id_product integer NOT NULL
);


ALTER TABLE request_order_line OWNER TO postgres;

--
-- Name: request_order_line_id_request_order_line_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE request_order_line_id_request_order_line_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE request_order_line_id_request_order_line_seq OWNER TO postgres;

--
-- Name: request_order_line_id_request_order_line_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE request_order_line_id_request_order_line_seq OWNED BY request_order_line.id_request_order_line;


--
-- Name: request_status; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE request_status (
    id_request_status integer,
    description character varying,
    name character varying(255)
);


ALTER TABLE request_status OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE role (
    id_role integer NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE role OWNER TO postgres;

--
-- Name: role_id_role_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE role_id_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_role_seq OWNER TO postgres;

--
-- Name: role_id_role_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE role_id_role_seq OWNED BY role.id_role;


--
-- Name: sale_condition; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE sale_condition (
    id_sale_condition character varying NOT NULL,
    initial_date timestamp without time zone,
    final_date timestamp without time zone,
    amount double precision,
    id_sale_condition_type character varying,
    limits integer,
    id_to_take integer
);


ALTER TABLE sale_condition OWNER TO postgres;

--
-- Name: sale_condition_id_sale_condition_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE sale_condition_id_sale_condition_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sale_condition_id_sale_condition_seq OWNER TO postgres;

--
-- Name: sale_condition_id_sale_condition_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE sale_condition_id_sale_condition_seq OWNED BY sale_condition.id_sale_condition;


--
-- Name: sale_condition_type; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE sale_condition_type (
    id_sale_condition_type character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE sale_condition_type OWNER TO postgres;

--
-- Name: sale_condition_type_id_sale_condition_type_seq_1; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE sale_condition_type_id_sale_condition_type_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sale_condition_type_id_sale_condition_type_seq_1 OWNER TO postgres;

--
-- Name: sale_condition_type_id_sale_condition_type_seq_1; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE sale_condition_type_id_sale_condition_type_seq_1 OWNED BY sale_condition_type.id_sale_condition_type;


--
-- Name: unit_of_measure; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE unit_of_measure (
    id_unit_of_measure integer NOT NULL,
    description character varying NOT NULL,
    descrip character varying(255)
);


ALTER TABLE unit_of_measure OWNER TO postgres;

--
-- Name: unit_of_measure_id_unit_of_measure_seq_1; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE unit_of_measure_id_unit_of_measure_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE unit_of_measure_id_unit_of_measure_seq_1 OWNER TO postgres;

--
-- Name: unit_of_measure_id_unit_of_measure_seq_1; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE unit_of_measure_id_unit_of_measure_seq_1 OWNED BY unit_of_measure.id_unit_of_measure;


--
-- Name: users; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE users (
    id_user integer NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying NOT NULL,
    password character varying NOT NULL,
    email character varying NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL,
    id_role integer NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    username character varying DEFAULT 'johnny1'::character varying NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE users_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_user_seq OWNER TO postgres;

--
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE users_id_user_seq OWNED BY users.id_user;


--
-- Name: vehicle; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE vehicle (
    id_vehicle integer NOT NULL,
    max_weight double precision NOT NULL,
    speed integer,
    active boolean DEFAULT true NOT NULL,
    id_warehouse integer NOT NULL,
    plate character varying
);


ALTER TABLE vehicle OWNER TO postgres;

--
-- Name: vehicle_id_vehicle_seq_1; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE vehicle_id_vehicle_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE vehicle_id_vehicle_seq_1 OWNER TO postgres;

--
-- Name: vehicle_id_vehicle_seq_1; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE vehicle_id_vehicle_seq_1 OWNED BY vehicle.id_vehicle;


--
-- Name: view; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE view (
    id_view integer NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE view OWNER TO postgres;

--
-- Name: view_id_view_seq_1; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE view_id_view_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE view_id_view_seq_1 OWNER TO postgres;

--
-- Name: view_id_view_seq_1; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE view_id_view_seq_1 OWNED BY view.id_view;


--
-- Name: warehouse; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE warehouse (
    id_warehouse integer NOT NULL,
    name character varying NOT NULL,
    length integer NOT NULL,
    width integer NOT NULL,
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE warehouse OWNER TO postgres;

--
-- Name: warehouse_id_warehouse_seq; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE warehouse_id_warehouse_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE warehouse_id_warehouse_seq OWNER TO postgres;

--
-- Name: warehouse_id_warehouse_seq; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE warehouse_id_warehouse_seq OWNED BY warehouse.id_warehouse;


--
-- Name: zone; Type: TABLE; Schema: campis; Owner: postgres
--

CREATE TABLE zone (
    id_zone integer NOT NULL,
    id_warehouse integer NOT NULL,
    id_rack integer NOT NULL,
    pos_x integer NOT NULL,
    pos_y character varying NOT NULL,
    pos_z character varying NOT NULL,
    free boolean DEFAULT true NOT NULL,
    max_weight double precision NOT NULL,
    quantity integer,
    last_activity timestamp without time zone DEFAULT now() NOT NULL,
    id_product integer
);


ALTER TABLE zone OWNER TO postgres;

--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE; Schema: campis; Owner: postgres
--

CREATE SEQUENCE zone_id_zone_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE zone_id_zone_seq_1 OWNER TO postgres;

--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE OWNED BY; Schema: campis; Owner: postgres
--

ALTER SEQUENCE zone_id_zone_seq_1 OWNED BY zone.id_zone;


--
-- Name: batch id_batch; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY batch ALTER COLUMN id_batch SET DEFAULT nextval('batch_id_batch_seq'::regclass);


--
-- Name: client id_client; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN id_client SET DEFAULT nextval('client_id_client_seq'::regclass);


--
-- Name: dispatch_order id_dispatch_order; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order ALTER COLUMN id_dispatch_order SET DEFAULT nextval('dispatch_order_id_dispatch_order_seq'::regclass);


--
-- Name: dispatch_order_line id_dispatch_order_line; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order_line ALTER COLUMN id_dispatch_order_line SET DEFAULT nextval('dispatch_order_line_id_dispatch_order_line_seq'::regclass);


--
-- Name: document id_document; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY document ALTER COLUMN id_document SET DEFAULT nextval('document_id_document_seq'::regclass);


--
-- Name: movement id_movement; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movement ALTER COLUMN id_movement SET DEFAULT nextval('movement_id_movement_seq'::regclass);


--
-- Name: product id_product; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY product ALTER COLUMN id_product SET DEFAULT nextval('product_id_product_seq'::regclass);


--
-- Name: product_type id_product_type; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY product_type ALTER COLUMN id_product_type SET DEFAULT nextval('product_type_id_product_type_seq_1'::regclass);


--
-- Name: rack id_rack; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY rack ALTER COLUMN id_rack SET DEFAULT nextval('rack_id_rack_seq'::regclass);


--
-- Name: request_order id_request_order; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order ALTER COLUMN id_request_order SET DEFAULT nextval('request_order_id_request_order_seq'::regclass);


--
-- Name: request_order_line id_request_order_line; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order_line ALTER COLUMN id_request_order_line SET DEFAULT nextval('request_order_line_id_request_order_line_seq'::regclass);


--
-- Name: role id_role; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY role ALTER COLUMN id_role SET DEFAULT nextval('role_id_role_seq'::regclass);


--
-- Name: sale_condition id_sale_condition; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY sale_condition ALTER COLUMN id_sale_condition SET DEFAULT nextval('sale_condition_id_sale_condition_seq'::regclass);


--
-- Name: sale_condition_type id_sale_condition_type; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY sale_condition_type ALTER COLUMN id_sale_condition_type SET DEFAULT nextval('sale_condition_type_id_sale_condition_type_seq_1'::regclass);


--
-- Name: unit_of_measure id_unit_of_measure; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY unit_of_measure ALTER COLUMN id_unit_of_measure SET DEFAULT nextval('unit_of_measure_id_unit_of_measure_seq_1'::regclass);


--
-- Name: users id_user; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id_user SET DEFAULT nextval('users_id_user_seq'::regclass);


--
-- Name: vehicle id_vehicle; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY vehicle ALTER COLUMN id_vehicle SET DEFAULT nextval('vehicle_id_vehicle_seq_1'::regclass);


--
-- Name: view id_view; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY view ALTER COLUMN id_view SET DEFAULT nextval('view_id_view_seq_1'::regclass);


--
-- Name: warehouse id_warehouse; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY warehouse ALTER COLUMN id_warehouse SET DEFAULT nextval('warehouse_id_warehouse_seq'::regclass);


--
-- Name: zone id_zone; Type: DEFAULT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY zone ALTER COLUMN id_zone SET DEFAULT nextval('zone_id_zone_seq_1'::regclass);


--
-- Data for Name: area; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY area (id_area, name, length, width, pos_x, pos_y, id_warehouse) FROM stdin;
\.


--
-- Data for Name: batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY batch (id_batch, quantity, batch_cost, arrival_date, expiration_date, id_product, type_batch, id_group_batch, location, state, heritage, id_unit) FROM stdin;
6	20	99.9000000000000057	2017-10-30 03:16:45.30369	2017-10-30 03:16:45.30369	30	2	1	asd	t	{}	1
7	7	99.9000000000000057	2017-10-30 03:16:53.008705	2017-10-30 03:16:45.30369	31	2	1	asd	t	{}	1
8	23	99.9000000000000057	2017-10-30 03:17:02.030601	2017-10-30 03:16:45.30369	31	2	1	asd	t	{}	1
12	48	99.9000000000000057	2017-10-30 03:17:36.99511	2017-10-30 03:16:45.30369	23	2	1	asd	t	{}	1
10	18	99.9000000000000057	2017-10-30 03:17:24.455406	2017-10-30 03:16:45.30369	23	2	1	asd	f	{}	1
9	42	99.9000000000000057	2017-10-30 03:17:12.259997	2017-10-30 03:16:45.30369	31	2	1	asd	t	{}	1
11	7	99.9000000000000057	2017-10-30 03:17:31.492939	2017-10-30 03:16:45.30369	23	2	1	asd	t	{}	1
13	10	99.9000000000000057	2017-10-30 03:17:44.986617	2017-10-30 03:16:45.30369	30	2	1	asd	t	{}	1
3	20	99.9000000000000057	2017-10-30 01:18:53.049626	2017-10-30 03:16:45.30369	3	1	1	asd	t	{}	1
\.


--
-- Name: batch_id_batch_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('batch_id_batch_seq', 13, true);


--
-- Data for Name: client; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY client (id_client, name, dni, ruc, active, address, phone, email, priority, id_district) FROM stdin;
1	Benito Juarez	48296679	12354687952	t	Los naranjos 123	555-3462	benito07@gmail.com	\N	\N
2	Julio Roman	32548647	16317746542	t	Los manzanos 555	665-6485	julior@gmail.com	\N	\N
57	teest	12321	1232122	t	2121123	333	test@test.com	\N	\N
\.


--
-- Name: client_id_client_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('client_id_client_seq', 2, true);


--
-- Data for Name: complaint; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY complaint (id_complaint, description, status, id_request_order) FROM stdin;
\.


--
-- Name: disclaim_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('disclaim_seq', 1, false);


--
-- Data for Name: dispatch_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY dispatch_order (id_dispatch_order, id_request_order, priority, status) FROM stdin;
\.


--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_id_dispatch_order_seq', 1, false);


--
-- Data for Name: dispatch_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY dispatch_order_line (id_dispatch_order_line, id_dispatch_order, id_product, quantity) FROM stdin;
\.


--
-- Name: dispatch_order_line_id_dispatch_order_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_line_id_dispatch_order_line_seq', 1, false);


--
-- Data for Name: district; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY district (id_district, name, freight) FROM stdin;
1	Cercado de Lima	0.0500000000000000028
2	Ate	0.149999999999999994
3	Barranco	0.110000000000000001
4	Breña	0.0599999999999999978
6	Chorrillos	0.179999999999999993
7	El Agustino	0.160000000000000003
8	Jesús María	0.0599999999999999978
9	La Molina	0.119999999999999996
10	La Victoria	0.100000000000000006
11	Lince	0.0700000000000000067
12	Magdalena del Mar	0.0500000000000000028
13	Miraflores	0.0800000000000000017
15	Puente Piedra	0.119999999999999996
16	Rimac	0.100000000000000006
17	San Isidro	0.0800000000000000017
18	Independencia	0.110000000000000001
19	San Juan de Miraflores	0.149999999999999994
20	San Luis	0.100000000000000006
23	Santiago de Surco	0.100000000000000006
24	Surquillo	0.0899999999999999967
25	Villa María del Triunfo	0.119999999999999996
26	San Juan de Lurigancho	0.119999999999999996
27	Santa Rosa	0.0800000000000000017
28	Los Olivos	0.0899999999999999967
29	San Borja	0.0899999999999999967
30	Villa El Savador	0.110000000000000001
31	Santa Anita	0.119999999999999996
21	San Martin de Porres	0.0700000000000000067
14	Pueblo Libre	0.0500000000000000028
5	Comas	0.0500000000000000028
\.


--
-- Data for Name: document; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY document (id_document, id_request_order, doc_type, total_amount) FROM stdin;
\.


--
-- Name: document_id_document_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('document_id_document_seq', 1, false);


--
-- Data for Name: group_batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY group_batch (id_group_batch, type_owner, id_owner, arrival_date, reason) FROM stdin;
1	1	1	2017-10-30 01:14:47.351344	1
2	1	1	2017-10-30 01:14:52.968268	1
3	1	1	2017-10-30 01:14:53.560423	1
43	1	1	2017-10-31 19:14:12.504	1
59	2	1	2017-11-02 04:29:59.751	1
60	2	1	2017-11-02 04:46:48.638	1
\.


--
-- Name: group_batch_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('group_batch_id_seq', 3, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 60, true);


--
-- Data for Name: movement; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY movement (id_movement, mov_date, id_user, quantity, id_vehicle, mov_type, id_warehouse, id_zone) FROM stdin;
2	2017-10-30 05:36:09.272301	21	10	1	1	1	1
3	2017-10-30 05:36:14.573463	21	20	1	1	1	1
\.


--
-- Name: movement_id_movement_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('movement_id_movement_seq', 3, true);


--
-- Data for Name: movementxbatch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY movementxbatch (id_batch, id_movement) FROM stdin;
\.


--
-- Data for Name: movementxdispatch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY movementxdispatch (id_movement, id_dispatch_order_line) FROM stdin;
\.


--
-- Data for Name: movementxdocument; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY movementxdocument (id_document, id_movement) FROM stdin;
\.


--
-- Data for Name: permission; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY permission (id_view, id_role, visualize, modify, id_permission) FROM stdin;
2	14	t	f	3
1	14	f	t	2
\.


--
-- Name: permission_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('permission_seq', 3, true);


--
-- Data for Name: product; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY product (id_product, name, description, p_stock, c_stock, weight, trademark, base_price, id_unit_of_measure, id_product_type) FROM stdin;
3	Arena Fina	Arean fina para construccion.	1	1	36	SOL	165.199999999999989	1	1
23	Fierros	Construccion.	1	1	210	SOL	95.1999969482421875	1	1
30	ProductoD	Producto de prueba para ver si funciona esto lol	1	1	12	Pls	10	1	1
31	ProductoNIce	shits	1	1	1234	Evian	13	2	1
32	Salio	pls pls pls pls pls pls.	1	1	666	Campis	111	1	1
\.


--
-- Name: product_id_product_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('product_id_product_seq', 8, true);


--
-- Data for Name: product_type; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY product_type (id_product_type, description, descripcion) FROM stdin;
1	aaa	\N
\.


--
-- Name: product_type_id_product_type_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('product_type_id_product_type_seq_1', 1, true);


--
-- Data for Name: rack; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY rack (id_rack, id_warehouse, pos_x, pos_y, n_columns, n_floors, orientation) FROM stdin;
1	1	2	2	10	5	0
2	1	14	2	10	5	0
3	1	26	2	10	5	0
5	1	14	5	10	5	0
6	1	26	5	10	5	0
7	1	2	8	10	5	0
8	1	14	8	10	5	0
4	2	2	5	10	5	0
\.


--
-- Name: rack_id_rack_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('rack_id_rack_seq', 9, true);


--
-- Data for Name: refund; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY refund (id_refund, id_complaint, status, creation_date) FROM stdin;
\.


--
-- Data for Name: refund_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY refund_line (id_refund_line, id_refund, id_product, quantity) FROM stdin;
\.


--
-- Name: refund_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('refund_line_seq', 1, false);


--
-- Name: refund_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('refund_seq', 1, false);


--
-- Data for Name: request_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY request_order (id_request_order, creation_date, delivery_date, base_amount, total_amount, status, id_client) FROM stdin;
37	2017-12-01 00:00:00	2017-12-04 00:00:00	826	826	EN PROGRESO	1
42	2017-12-01 00:00:00	2017-12-15 00:00:00	476	476	CANCELADO	2
40	2017-12-01 00:00:00	2017-12-28 00:00:00	555	555	EN PROGRESO	2
41	2017-12-01 00:00:00	2017-12-22 00:00:00	660.79998779296875	660.79998779296875	EN PROGRESO	1
49	2017-12-01 00:00:00	2017-12-22 00:00:00	1195.4000244140625	1195.4000244140625		1
50	2018-04-01 00:00:00	2018-04-15 00:00:00	941.39996337890625	941.39996337890625	EN PROGRESO	1
\.


--
-- Name: request_order_id_request_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('request_order_id_request_order_seq', 1, false);


--
-- Data for Name: request_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY request_order_line (id_request_order_line, quantity, cost, id_request_order, id_product) FROM stdin;
51	3	165.199996948242188	50	3
52	5	13	50	31
56	4	95.1999969482421875	50	23
\.


--
-- Name: request_order_line_id_request_order_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('request_order_line_id_request_order_line_seq', 1, false);


--
-- Data for Name: request_status; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY request_status (id_request_status, description, name) FROM stdin;
1	Cotización	\N
2	En proceso	\N
3	Entregado	\N
4	Anulado	\N
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY role (id_role, description) FROM stdin;
1	admin
2	test
3	Rol 1
5	Funcionario
6	ROL TEST
7	ROL JP
14	rolex
15	Almacenero1
46	rol de prueba
\.


--
-- Name: role_id_role_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('role_id_role_seq', 7, true);


--
-- Data for Name: sale_condition; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY sale_condition (id_sale_condition, initial_date, final_date, amount, id_sale_condition_type, limits, id_to_take) FROM stdin;
\.


--
-- Name: sale_condition_id_sale_condition_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('sale_condition_id_sale_condition_seq', 1, false);


--
-- Data for Name: sale_condition_type; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY sale_condition_type (id_sale_condition_type, description) FROM stdin;
1	Por producto
2	Por tipo de producto
\.


--
-- Name: sale_condition_type_id_sale_condition_type_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('sale_condition_type_id_sale_condition_type_seq_1', 2, true);


--
-- Data for Name: unit_of_measure; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY unit_of_measure (id_unit_of_measure, description, descrip) FROM stdin;
1	Kilogramos	\N
2	Litros	\N
\.


--
-- Name: unit_of_measure_id_unit_of_measure_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('unit_of_measure_id_unit_of_measure_seq_1', 2, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY users (id_user, firstname, lastname, password, email, created_at, active, id_role, updated_at, username) FROM stdin;
21	Miguel	Guanira	123456	guani@ra.com	2017-10-27 23:53:16.454	t	1	2017-10-27 23:53:16.454	mguani
29	galway	girl	doritos	galwaygirl@pucp.pe	2017-10-28 21:24:49.616	t	1	2017-10-28 21:24:49.616	galwaygirl
12	Test2	test	qwer	qwer	2017-10-26 02:55:34.076	t	14	2017-10-26 02:55:34.076	qwer
45	prueba 10	test	123456	test@test.com	2017-10-31 20:16:46.986	t	1	2017-10-31 20:16:46.986	test
\.


--
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('users_id_user_seq', 1, true);


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY vehicle (id_vehicle, max_weight, speed, active, id_warehouse, plate) FROM stdin;
2	800.799999999999955	54	t	1	\N
33	123	60	t	2	AAA-123
5	750.740000000000009	59	t	3	XXX-123
34	111	222	t	4	AASGA
7	123	144	t	26	HUEHUE
35	123	55	t	4	AIEGFA
1	111.099999999999994	123	t	24	124124
\.


--
-- Name: vehicle_id_vehicle_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('vehicle_id_vehicle_seq_1', 7, true);


--
-- Data for Name: view; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY view (id_view, description) FROM stdin;
1	products
2	warehouse
\.


--
-- Name: view_id_view_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('view_id_view_seq_1', 2, true);


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY warehouse (id_warehouse, name, length, width, status) FROM stdin;
2	Almacen2	40	40	t
3	Almacen3	40	40	t
4	Almacen4	40	40	t
5	Almacen5	40	40	t
24	Almacenin	40	40	t
25	Almacetrash	40	40	t
26	weareded	40	40	t
0	Ninguno	0	0	t
1	Almacen1	40	30	t
\.


--
-- Name: warehouse_id_warehouse_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('warehouse_id_warehouse_seq', 4, true);


--
-- Data for Name: zone; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY zone (id_zone, id_warehouse, id_rack, pos_x, pos_y, pos_z, free, max_weight, quantity, last_activity, id_product) FROM stdin;
\.


--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('zone_id_zone_seq_1', 1, false);


--
-- Name: area area_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY area
    ADD CONSTRAINT area_pk PRIMARY KEY (id_area);


--
-- Name: batch batch_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY batch
    ADD CONSTRAINT batch_pk PRIMARY KEY (id_batch);


--
-- Name: client client_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pk PRIMARY KEY (id_client);


--
-- Name: complaint complaint_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY complaint
    ADD CONSTRAINT complaint_pk PRIMARY KEY (id_complaint);


--
-- Name: dispatch_order_line dispatch_order_line_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order_line
    ADD CONSTRAINT dispatch_order_line_pk PRIMARY KEY (id_dispatch_order_line);


--
-- Name: dispatch_order dispatch_order_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order
    ADD CONSTRAINT dispatch_order_pk PRIMARY KEY (id_dispatch_order);


--
-- Name: document document_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY document
    ADD CONSTRAINT document_pk PRIMARY KEY (id_document);


--
-- Name: group_batch idpk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY group_batch
    ADD CONSTRAINT idpk PRIMARY KEY (id_group_batch);


--
-- Name: movement movement_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movement
    ADD CONSTRAINT movement_pk PRIMARY KEY (id_movement);


--
-- Name: movementxbatch movementxbatch_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxbatch
    ADD CONSTRAINT movementxbatch_pk PRIMARY KEY (id_batch, id_movement);


--
-- Name: movementxdispatch movementxdispatch_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxdispatch
    ADD CONSTRAINT movementxdispatch_pk PRIMARY KEY (id_movement, id_dispatch_order_line);


--
-- Name: movementxdocument movementxdocument_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxdocument
    ADD CONSTRAINT movementxdocument_pk PRIMARY KEY (id_document, id_movement);


--
-- Name: permission permission_id_permission_key; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_id_permission_key UNIQUE (id_permission);


--
-- Name: permission permission_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_pk PRIMARY KEY (id_view, id_role);


--
-- Name: product product_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pk PRIMARY KEY (id_product);


--
-- Name: product_type product_type_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY product_type
    ADD CONSTRAINT product_type_pk PRIMARY KEY (id_product_type);


--
-- Name: rack rack_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY rack
    ADD CONSTRAINT rack_pk PRIMARY KEY (id_rack);


--
-- Name: refund_line refund_line_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY refund_line
    ADD CONSTRAINT refund_line_pk PRIMARY KEY (id_refund_line);


--
-- Name: refund refund_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY refund
    ADD CONSTRAINT refund_pk PRIMARY KEY (id_refund);


--
-- Name: request_order_line request_order_line_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order_line
    ADD CONSTRAINT request_order_line_pk PRIMARY KEY (id_request_order_line);


--
-- Name: request_order request_order_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order
    ADD CONSTRAINT request_order_pk PRIMARY KEY (id_request_order);


--
-- Name: role role_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pk PRIMARY KEY (id_role);


--
-- Name: sale_condition sale_condition_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY sale_condition
    ADD CONSTRAINT sale_condition_pk PRIMARY KEY (id_sale_condition);


--
-- Name: sale_condition_type sale_condition_type_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY sale_condition_type
    ADD CONSTRAINT sale_condition_type_pk PRIMARY KEY (id_sale_condition_type);


--
-- Name: unit_of_measure unit_of_measure_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY unit_of_measure
    ADD CONSTRAINT unit_of_measure_pk PRIMARY KEY (id_unit_of_measure);


--
-- Name: users users_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pk PRIMARY KEY (id_user);


--
-- Name: vehicle vehicle_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY vehicle
    ADD CONSTRAINT vehicle_pk PRIMARY KEY (id_vehicle);


--
-- Name: view view_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY view
    ADD CONSTRAINT view_pk PRIMARY KEY (id_view);


--
-- Name: warehouse warehouse_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY warehouse
    ADD CONSTRAINT warehouse_pk PRIMARY KEY (id_warehouse);


--
-- Name: zone zone_pk; Type: CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY zone
    ADD CONSTRAINT zone_pk PRIMARY KEY (id_zone);


--
-- Name: movementxbatch batch_movementxbatch_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxbatch
    ADD CONSTRAINT batch_movementxbatch_fk FOREIGN KEY (id_batch) REFERENCES batch(id_batch) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: batch batch_product_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY batch
    ADD CONSTRAINT batch_product_fk FOREIGN KEY (id_product) REFERENCES product(id_product) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: request_order client_request_order_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order
    ADD CONSTRAINT client_request_order_fk FOREIGN KEY (id_client) REFERENCES client(id_client) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: dispatch_order_line dispatch_order_dispatch_order_line_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order_line
    ADD CONSTRAINT dispatch_order_dispatch_order_line_fk FOREIGN KEY (id_dispatch_order) REFERENCES dispatch_order(id_dispatch_order) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movementxdispatch dispatch_order_line_movementxdispatch_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxdispatch
    ADD CONSTRAINT dispatch_order_line_movementxdispatch_fk FOREIGN KEY (id_dispatch_order_line) REFERENCES dispatch_order_line(id_dispatch_order_line) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movementxdocument document_movementxdocument_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxdocument
    ADD CONSTRAINT document_movementxdocument_fk FOREIGN KEY (id_document) REFERENCES document(id_document) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: batch group_batch_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY batch
    ADD CONSTRAINT group_batch_fk FOREIGN KEY (id_group_batch) REFERENCES group_batch(id_group_batch) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movementxbatch movement_movementxbatch_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxbatch
    ADD CONSTRAINT movement_movementxbatch_fk FOREIGN KEY (id_movement) REFERENCES movement(id_movement) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movementxdispatch movement_movementxdispatch_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxdispatch
    ADD CONSTRAINT movement_movementxdispatch_fk FOREIGN KEY (id_movement) REFERENCES movement(id_movement) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movementxdocument movement_movementxdocument_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movementxdocument
    ADD CONSTRAINT movement_movementxdocument_fk FOREIGN KEY (id_movement) REFERENCES movement(id_movement) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: dispatch_order_line product_dispatch_order_line_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order_line
    ADD CONSTRAINT product_dispatch_order_line_fk FOREIGN KEY (id_product) REFERENCES product(id_product) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: request_order_line product_request_order_line_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order_line
    ADD CONSTRAINT product_request_order_line_fk FOREIGN KEY (id_product) REFERENCES product(id_product) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product product_type_product_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT product_type_product_fk FOREIGN KEY (id_product_type) REFERENCES product_type(id_product_type);


--
-- Name: zone rack_zone_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY zone
    ADD CONSTRAINT rack_zone_fk FOREIGN KEY (id_rack) REFERENCES rack(id_rack) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: dispatch_order request_order_dispatch_order_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY dispatch_order
    ADD CONSTRAINT request_order_dispatch_order_fk FOREIGN KEY (id_request_order) REFERENCES request_order(id_request_order) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: document request_order_document_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY document
    ADD CONSTRAINT request_order_document_fk FOREIGN KEY (id_request_order) REFERENCES request_order(id_request_order) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: request_order_line request_order_request_order_line_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY request_order_line
    ADD CONSTRAINT request_order_request_order_line_fk FOREIGN KEY (id_request_order) REFERENCES request_order(id_request_order) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: permission role_permission_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT role_permission_fk FOREIGN KEY (id_role) REFERENCES role(id_role) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: users role_user_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT role_user_fk FOREIGN KEY (id_role) REFERENCES role(id_role) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: sale_condition sale_condition_type_sale_condition_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY sale_condition
    ADD CONSTRAINT sale_condition_type_sale_condition_fk FOREIGN KEY (id_sale_condition_type) REFERENCES sale_condition_type(id_sale_condition_type) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: product unit_of_measure_product_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY product
    ADD CONSTRAINT unit_of_measure_product_fk FOREIGN KEY (id_unit_of_measure) REFERENCES unit_of_measure(id_unit_of_measure) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movement user_movement_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movement
    ADD CONSTRAINT user_movement_fk FOREIGN KEY (id_user) REFERENCES users(id_user) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: movement vehicle_movement_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY movement
    ADD CONSTRAINT vehicle_movement_fk FOREIGN KEY (id_vehicle) REFERENCES vehicle(id_vehicle) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: permission view_permission_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT view_permission_fk FOREIGN KEY (id_view) REFERENCES view(id_view) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: area warehouse_area_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY area
    ADD CONSTRAINT warehouse_area_fk FOREIGN KEY (id_warehouse) REFERENCES warehouse(id_warehouse) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: rack warehouse_rack_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY rack
    ADD CONSTRAINT warehouse_rack_fk FOREIGN KEY (id_warehouse) REFERENCES warehouse(id_warehouse) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: vehicle warehouse_vehicle_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY vehicle
    ADD CONSTRAINT warehouse_vehicle_fk FOREIGN KEY (id_warehouse) REFERENCES warehouse(id_warehouse) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: zone warehouse_zone_fk; Type: FK CONSTRAINT; Schema: campis; Owner: postgres
--

ALTER TABLE ONLY zone
    ADD CONSTRAINT warehouse_zone_fk FOREIGN KEY (id_warehouse) REFERENCES warehouse(id_warehouse) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

