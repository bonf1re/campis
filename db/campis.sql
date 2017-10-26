CREATE SCHEMA IF NOT EXISTS campis;

CREATE SEQUENCE campis.client_id_client_seq;

CREATE TABLE campis.client (
                id_client INTEGER NOT NULL DEFAULT nextval('campis.client_id_client_seq'),
                name VARCHAR NOT NULL,
                dni VARCHAR,
                ruc VARCHAR,
                active BOOLEAN DEFAULT true NOT NULL,
                address VARCHAR,
                phone VARCHAR,
                email VARCHAR,
                priority INTEGER NOT NULL,
                CONSTRAINT client_pk PRIMARY KEY (id_client)
);


ALTER SEQUENCE campis.client_id_client_seq OWNED BY campis.client.id_client;

CREATE SEQUENCE campis.request_order_id_request_order_seq;

CREATE TABLE campis.request_order (
                id_request_order INTEGER NOT NULL DEFAULT nextval('campis.request_order_id_request_order_seq'),
                creation_date TIMESTAMP DEFAULT now() NOT NULL,
                delivery_date TIMESTAMP,
                status VARCHAR,
                base_amount DOUBLE PRECISION,
                total_amount DOUBLE PRECISION,
                status_1 VARCHAR,
                id_client INTEGER NOT NULL,
                CONSTRAINT request_order_pk PRIMARY KEY (id_request_order)
);


ALTER SEQUENCE campis.request_order_id_request_order_seq OWNED BY campis.request_order.id_request_order;

CREATE SEQUENCE campis.document_id_document_seq;

CREATE TABLE campis.document (
                id_document INTEGER NOT NULL DEFAULT nextval('campis.document_id_document_seq'),
                id_request_order INTEGER NOT NULL,
                doc_type VARCHAR NOT NULL,
                total_amount VARCHAR NOT NULL,
                CONSTRAINT document_pk PRIMARY KEY (id_document)
);


ALTER SEQUENCE campis.document_id_document_seq OWNED BY campis.document.id_document;

CREATE SEQUENCE campis.dispatch_order_id_dispatch_order_seq;

CREATE TABLE campis.dispatch_order (
                id_dispatch_order INTEGER NOT NULL DEFAULT nextval('campis.dispatch_order_id_dispatch_order_seq'),
                id_request_order INTEGER NOT NULL,
                priority INTEGER DEFAULT 1 NOT NULL,
                status VARCHAR,
                CONSTRAINT dispatch_order_pk PRIMARY KEY (id_dispatch_order)
);


ALTER SEQUENCE campis.dispatch_order_id_dispatch_order_seq OWNED BY campis.dispatch_order.id_dispatch_order;

CREATE SEQUENCE campis.sale_condition_type_id_sale_condition_type_seq_1;

CREATE TABLE campis.sale_condition_type (
                id_sale_condition_type VARCHAR NOT NULL DEFAULT nextval('campis.sale_condition_type_id_sale_condition_type_seq_1'),
                description VARCHAR NOT NULL,
                CONSTRAINT sale_condition_type_pk PRIMARY KEY (id_sale_condition_type)
);


ALTER SEQUENCE campis.sale_condition_type_id_sale_condition_type_seq_1 OWNED BY campis.sale_condition_type.id_sale_condition_type;

CREATE SEQUENCE campis.sale_condition_id_sale_condition_seq;

CREATE TABLE campis.sale_condition (
                id_sale_condition VARCHAR NOT NULL DEFAULT nextval('campis.sale_condition_id_sale_condition_seq'),
                initial_date TIMESTAMP NOT NULL,
                final_date TIMESTAMP NOT NULL,
                amount DOUBLE PRECISION NOT NULL,
                id_sale_condition_type VARCHAR NOT NULL,
                limits INTEGER,
                id_to_take INTEGER NOT NULL,
                CONSTRAINT sale_condition_pk PRIMARY KEY (id_sale_condition)
);


ALTER SEQUENCE campis.sale_condition_id_sale_condition_seq OWNED BY campis.sale_condition.id_sale_condition;

CREATE SEQUENCE campis.supplier_id_supplier_seq;

CREATE TABLE campis.supplier (
                id_supplier VARCHAR NOT NULL DEFAULT nextval('campis.supplier_id_supplier_seq'),
                name VARCHAR NOT NULL,
                address VARCHAR,
                phone VARCHAR,
                email VARCHAR,
                ruc VARCHAR,
                created_at TIMESTAMP DEFAULT now() NOT NULL,
                updated_at TIMESTAMP NOT NULL,
                active BOOLEAN DEFAULT true NOT NULL,
                priority INTEGER NOT NULL,
                CONSTRAINT supplier_pk PRIMARY KEY (id_supplier)
);


ALTER SEQUENCE campis.supplier_id_supplier_seq OWNED BY campis.supplier.id_supplier;

CREATE SEQUENCE campis.batch_id_batch_seq;

CREATE TABLE campis.batch (
                id_batch INTEGER NOT NULL DEFAULT nextval('campis.batch_id_batch_seq'),
                quantity INTEGER NOT NULL,
                batch_cost DOUBLE PRECISION NOT NULL,
                arrival_date TIMESTAMP DEFAULT now() NOT NULL,
                expiration_date TIMESTAMP,
                id_supplier VARCHAR NOT NULL,
                CONSTRAINT batch_pk PRIMARY KEY (id_batch)
);


ALTER SEQUENCE campis.batch_id_batch_seq OWNED BY campis.batch.id_batch;

CREATE SEQUENCE campis.product_type_id_product_type_seq_1;

CREATE TABLE campis.product_type (
                id_product_type VARCHAR NOT NULL DEFAULT nextval('campis.product_type_id_product_type_seq_1'),
                description VARCHAR NOT NULL,
                CONSTRAINT product_type_pk PRIMARY KEY (id_product_type)
);


ALTER SEQUENCE campis.product_type_id_product_type_seq_1 OWNED BY campis.product_type.id_product_type;

CREATE SEQUENCE campis.unit_of_measure_id_unit_of_measure_seq_1;

CREATE TABLE campis.unit_of_measure (
                id_unit_of_measure INTEGER NOT NULL DEFAULT nextval('campis.unit_of_measure_id_unit_of_measure_seq_1'),
                description VARCHAR NOT NULL,
                CONSTRAINT unit_of_measure_pk PRIMARY KEY (id_unit_of_measure)
);


ALTER SEQUENCE campis.unit_of_measure_id_unit_of_measure_seq_1 OWNED BY campis.unit_of_measure.id_unit_of_measure;

CREATE SEQUENCE campis.product_id_product_seq;

CREATE TABLE campis.product (
                id_product INTEGER NOT NULL DEFAULT nextval('campis.product_id_product_seq'),
                name VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                p_stock INTEGER DEFAULT 0 NOT NULL,
                c_stock INTEGER DEFAULT 0 NOT NULL,
                weight DOUBLE PRECISION DEFAULT 0 NOT NULL,
                trademark VARCHAR,
                base_price DOUBLE PRECISION NOT NULL,
                id_unit_of_measure INTEGER NOT NULL,
                id_product_type VARCHAR NOT NULL,
                CONSTRAINT product_pk PRIMARY KEY (id_product)
);


ALTER SEQUENCE campis.product_id_product_seq OWNED BY campis.product.id_product;

CREATE SEQUENCE campis.dispatch_order_line_id_dispatch_order_line_seq;

CREATE TABLE campis.dispatch_order_line (
                id_dispatch_order_line INTEGER NOT NULL DEFAULT nextval('campis.dispatch_order_line_id_dispatch_order_line_seq'),
                id_dispatch_order INTEGER NOT NULL,
                id_product INTEGER NOT NULL,
                quantity INTEGER,
                CONSTRAINT dispatch_order_line_pk PRIMARY KEY (id_dispatch_order_line)
);


ALTER SEQUENCE campis.dispatch_order_line_id_dispatch_order_line_seq OWNED BY campis.dispatch_order_line.id_dispatch_order_line;

CREATE SEQUENCE campis.request_order_line_id_request_order_line_seq;

CREATE TABLE campis.request_order_line (
                id_request_order_line INTEGER NOT NULL DEFAULT nextval('campis.request_order_line_id_request_order_line_seq'),
                quantity INTEGER,
                cost DOUBLE PRECISION,
                id_request_order INTEGER NOT NULL,
                id_product INTEGER NOT NULL,
                CONSTRAINT request_order_line_pk PRIMARY KEY (id_request_order_line)
);


ALTER SEQUENCE campis.request_order_line_id_request_order_line_seq OWNED BY campis.request_order_line.id_request_order_line;

CREATE SEQUENCE campis.warehouse_id_warehouse_seq;

CREATE TABLE campis.warehouse (
                id_warehouse INTEGER NOT NULL DEFAULT nextval('campis.warehouse_id_warehouse_seq'),
                name VARCHAR NOT NULL,
                length INTEGER NOT NULL,
                width INTEGER NOT NULL,
                status BOOLEAN DEFAULT true NOT NULL,
                CONSTRAINT warehouse_pk PRIMARY KEY (id_warehouse)
);


ALTER SEQUENCE campis.warehouse_id_warehouse_seq OWNED BY campis.warehouse.id_warehouse;

CREATE SEQUENCE campis.vehicle_id_vehicle_seq_1;

CREATE TABLE campis.vehicle (
                id_vehicle INTEGER NOT NULL DEFAULT nextval('campis.vehicle_id_vehicle_seq_1'),
                max_weight DOUBLE PRECISION NOT NULL,
                speed INTEGER,
                active BOOLEAN DEFAULT true NOT NULL,
                id_warehouse INTEGER NOT NULL,
                CONSTRAINT vehicle_pk PRIMARY KEY (id_vehicle)
);


ALTER SEQUENCE campis.vehicle_id_vehicle_seq_1 OWNED BY campis.vehicle.id_vehicle;

CREATE TABLE campis.area (
                id_area VARCHAR NOT NULL,
                name VARCHAR NOT NULL,
                length INTEGER NOT NULL,
                width INTEGER NOT NULL,
                pos_x INTEGER NOT NULL,
                pos_y INTEGER NOT NULL,
                id_warehouse INTEGER NOT NULL,
                CONSTRAINT area_pk PRIMARY KEY (id_area)
);


CREATE SEQUENCE campis.rack_id_rack_seq;

CREATE TABLE campis.rack (
                id_rack INTEGER NOT NULL DEFAULT nextval('campis.rack_id_rack_seq'),
                id_warehouse INTEGER NOT NULL,
                pos_x INTEGER NOT NULL,
                pos_y VARCHAR NOT NULL,
                n_columns INTEGER NOT NULL,
                n_floors INTEGER NOT NULL,
                id_area VARCHAR NOT NULL,
                CONSTRAINT rack_pk PRIMARY KEY (id_rack)
);


ALTER SEQUENCE campis.rack_id_rack_seq OWNED BY campis.rack.id_rack;

CREATE SEQUENCE campis.zone_id_zone_seq_1;

CREATE TABLE campis.zone (
                id_zone INTEGER NOT NULL DEFAULT nextval('campis.zone_id_zone_seq_1'),
                id_warehouse INTEGER NOT NULL,
                id_rack INTEGER NOT NULL,
                pos_x INTEGER NOT NULL,
                pos_y VARCHAR NOT NULL,
                pos_z VARCHAR NOT NULL,
                free BOOLEAN DEFAULT true NOT NULL,
                max_weight DOUBLE PRECISION NOT NULL,
                quantity INTEGER,
                id_area VARCHAR NOT NULL,
                last_activity TIMESTAMP DEFAULT now() NOT NULL,
                CONSTRAINT zone_pk PRIMARY KEY (id_zone)
);


ALTER SEQUENCE campis.zone_id_zone_seq_1 OWNED BY campis.zone.id_zone;

CREATE SEQUENCE campis.view_id_view_seq_1;

CREATE TABLE campis.view (
                id_view INTEGER NOT NULL DEFAULT nextval('campis.view_id_view_seq_1'),
                description VARCHAR NOT NULL,
                CONSTRAINT view_pk PRIMARY KEY (id_view)
);


ALTER SEQUENCE campis.view_id_view_seq_1 OWNED BY campis.view.id_view;

CREATE SEQUENCE campis.role_id_role_seq;

CREATE TABLE campis.role (
                id_role INTEGER NOT NULL DEFAULT nextval('campis.role_id_role_seq'),
                description VARCHAR NOT NULL,
                CONSTRAINT role_pk PRIMARY KEY (id_role)
);


ALTER SEQUENCE campis.role_id_role_seq OWNED BY campis.role.id_role;

CREATE TABLE campis.permission (
                id_view INTEGER NOT NULL,
                id_role INTEGER NOT NULL,
                visualize BOOLEAN DEFAULT true NOT NULL,
                modify BOOLEAN DEFAULT true NOT NULL,
                CONSTRAINT permission_pk PRIMARY KEY (id_view, id_role)
);


CREATE SEQUENCE campis.users_id_user_seq;

CREATE TABLE campis.users (
                id_user INTEGER NOT NULL DEFAULT nextval('campis.users_id_user_seq'),
                firstname VARCHAR NOT NULL,
                lastname VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                created_at TIMESTAMP DEFAULT now() NOT NULL,
                updated_at VARCHAR DEFAULT now() NOT NULL,
                active BOOLEAN DEFAULT true NOT NULL,
                id_role INTEGER NOT NULL,
                CONSTRAINT users_pk PRIMARY KEY (id_user)
);


ALTER SEQUENCE campis.users_id_user_seq OWNED BY campis.users.id_user;

CREATE SEQUENCE campis.movement_id_movement_seq;

CREATE TABLE campis.movement (
                id_movement INTEGER NOT NULL DEFAULT nextval('campis.movement_id_movement_seq'),
                date TIMESTAMP DEFAULT now() NOT NULL,
                id_user INTEGER NOT NULL,
                quantity INTEGER NOT NULL,
                id_zone INTEGER NOT NULL,
                id_vehicle INTEGER NOT NULL,
                mov_type VARCHAR,
                CONSTRAINT movement_pk PRIMARY KEY (id_movement)
);


ALTER SEQUENCE campis.movement_id_movement_seq OWNED BY campis.movement.id_movement;

CREATE TABLE campis.movementxdocument (
                id_document INTEGER NOT NULL,
                id_movement INTEGER NOT NULL,
                CONSTRAINT movementxdocument_pk PRIMARY KEY (id_document, id_movement)
);


CREATE TABLE campis.movementxdispatch (
                id_movement INTEGER NOT NULL,
                id_dispatch_order_line INTEGER NOT NULL,
                CONSTRAINT movementxdispatch_pk PRIMARY KEY (id_movement, id_dispatch_order_line)
);


CREATE TABLE campis.movementxbatch (
                id_batch INTEGER NOT NULL,
                id_movement INTEGER NOT NULL,
                CONSTRAINT movementxbatch_pk PRIMARY KEY (id_batch, id_movement)
);


ALTER TABLE campis.request_order ADD CONSTRAINT client_request_order_fk
FOREIGN KEY (id_client)
REFERENCES campis.client (id_client)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.request_order_line ADD CONSTRAINT request_order_request_order_line_fk
FOREIGN KEY (id_request_order)
REFERENCES campis.request_order (id_request_order)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.dispatch_order ADD CONSTRAINT request_order_dispatch_order_fk
FOREIGN KEY (id_request_order)
REFERENCES campis.request_order (id_request_order)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.document ADD CONSTRAINT request_order_document_fk
FOREIGN KEY (id_request_order)
REFERENCES campis.request_order (id_request_order)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movementxdocument ADD CONSTRAINT document_movementxdocument_fk
FOREIGN KEY (id_document)
REFERENCES campis.document (id_document)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.dispatch_order_line ADD CONSTRAINT dispatch_order_dispatch_order_line_fk
FOREIGN KEY (id_dispatch_order)
REFERENCES campis.dispatch_order (id_dispatch_order)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.sale_condition ADD CONSTRAINT sale_condition_type_sale_condition_fk
FOREIGN KEY (id_sale_condition_type)
REFERENCES campis.sale_condition_type (id_sale_condition_type)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.batch ADD CONSTRAINT supplier_batch_fk
FOREIGN KEY (id_supplier)
REFERENCES campis.supplier (id_supplier)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movementxbatch ADD CONSTRAINT batch_movementxbatch_fk
FOREIGN KEY (id_batch)
REFERENCES campis.batch (id_batch)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.product ADD CONSTRAINT product_type_product_fk
FOREIGN KEY (id_product_type)
REFERENCES campis.product_type (id_product_type)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.product ADD CONSTRAINT unit_of_measure_product_fk
FOREIGN KEY (id_unit_of_measure)
REFERENCES campis.unit_of_measure (id_unit_of_measure)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.request_order_line ADD CONSTRAINT product_request_order_line_fk
FOREIGN KEY (id_product)
REFERENCES campis.product (id_product)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.dispatch_order_line ADD CONSTRAINT product_dispatch_order_line_fk
FOREIGN KEY (id_product)
REFERENCES campis.product (id_product)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movementxdispatch ADD CONSTRAINT dispatch_order_line_movementxdispatch_fk
FOREIGN KEY (id_dispatch_order_line)
REFERENCES campis.dispatch_order_line (id_dispatch_order_line)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.rack ADD CONSTRAINT warehouse_rack_fk
FOREIGN KEY (id_warehouse)
REFERENCES campis.warehouse (id_warehouse)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.zone ADD CONSTRAINT warehouse_zone_fk
FOREIGN KEY (id_warehouse)
REFERENCES campis.warehouse (id_warehouse)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.area ADD CONSTRAINT warehouse_area_fk
FOREIGN KEY (id_warehouse)
REFERENCES campis.warehouse (id_warehouse)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.vehicle ADD CONSTRAINT warehouse_vehicle_fk
FOREIGN KEY (id_warehouse)
REFERENCES campis.warehouse (id_warehouse)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movement ADD CONSTRAINT vehicle_movement_fk
FOREIGN KEY (id_vehicle)
REFERENCES campis.vehicle (id_vehicle)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.rack ADD CONSTRAINT area_rack_fk
FOREIGN KEY (id_area)
REFERENCES campis.area (id_area)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.zone ADD CONSTRAINT area_zone_fk
FOREIGN KEY (id_area)
REFERENCES campis.area (id_area)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.zone ADD CONSTRAINT rack_zone_fk
FOREIGN KEY (id_rack)
REFERENCES campis.rack (id_rack)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movement ADD CONSTRAINT zone_movement_fk
FOREIGN KEY (id_zone)
REFERENCES campis.zone (id_zone)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.permission ADD CONSTRAINT view_permission_fk
FOREIGN KEY (id_view)
REFERENCES campis.view (id_view)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.permission ADD CONSTRAINT role_permission_fk
FOREIGN KEY (id_role)
REFERENCES campis.role (id_role)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.users ADD CONSTRAINT role_user_fk
FOREIGN KEY (id_role)
REFERENCES campis.role (id_role)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movement ADD CONSTRAINT user_movement_fk
FOREIGN KEY (id_user)
REFERENCES campis.users (id_user)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movementxbatch ADD CONSTRAINT movement_movementxbatch_fk
FOREIGN KEY (id_movement)
REFERENCES campis.movement (id_movement)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movementxdispatch ADD CONSTRAINT movement_movementxdispatch_fk
FOREIGN KEY (id_movement)
REFERENCES campis.movement (id_movement)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE campis.movementxdocument ADD CONSTRAINT movement_movementxdocument_fk
FOREIGN KEY (id_movement)
REFERENCES campis.movement (id_movement)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;
