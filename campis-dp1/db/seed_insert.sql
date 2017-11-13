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

SET search_path = campis, pg_catalog;

--
-- Data for Name: product_type; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO product_type VALUES (1, 'TipoProducto1');
INSERT INTO product_type VALUES (2, 'TipoProducto2');
INSERT INTO product_type VALUES (3, 'TipoProducto3');
INSERT INTO product_type VALUES (1566, 'TipoProducto4');


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO warehouse VALUES (1, 'Almacen1', 100, 100, true);


--
-- Data for Name: area; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO area VALUES (1, 'test_area1', 10, 10, 0, 0, 1, 1);
INSERT INTO area VALUES (778, 'area1', 23, 13, 891, 123, 1, 1);
INSERT INTO area VALUES (3, 'test_area3', 10, 10, 0, 0, 1, 2);
INSERT INTO area VALUES (1550, 'qwer2', 2, 2, 2, 2, 1, 2);
INSERT INTO area VALUES (2, 'test_area2', 10, 10, 0, 0, 1, 3);


--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 1, false);


--
-- Data for Name: unit_of_measure; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO unit_of_measure VALUES (1, 'Kilogramos', NULL);
INSERT INTO unit_of_measure VALUES (2, 'Litros', NULL);


--
-- Data for Name: product; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO product VALUES (23, 'Fierros', 'Construccion.', -7, -18, 210, 'SOL', 95.1999969482421875, 1, 1, 40);
INSERT INTO product VALUES (32, 'Salio', 'pls pls pls pls pls pls.', 165, 165, 666, 'Campis', 111, 1, 1, 40);
INSERT INTO product VALUES (739, 'ProductoSuenho', 'un producto bien xvr', 11, 11, 12, 'FLowynais', 123, 2, 2, 40);
INSERT INTO product VALUES (30, 'ProductoD', 'Producto de prueba para ver si funciona esto lol', 13, 12, 12, 'Pls', 10, 1, 1, 40);
INSERT INTO product VALUES (31, 'ProductoNIce', 'shits', 193, 188, 1234, 'Evian', 13, 2, 1, 40);
INSERT INTO product VALUES (752, 'Clavos', 'Caja de clavos simple.', 1, 1, 1, 'Pablito', 8, 1, 3, 100);
INSERT INTO product VALUES (1592, 'producto de prueba', '', 1, 1, 12, 'acme', 1, 1, 1, 50);
INSERT INTO product VALUES (3, 'Arena Fina', 'Arean fina para construccion.', 13, -1, 36, 'SOL', 165.199999999999989, 1, 1, 40);


--
-- Data for Name: batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO batch VALUES (14, 3, 15, '2017-11-04 04:00:04.394618', '2017-11-04 00:00:00', 23, 5, true, -1, NULL, NULL);
INSERT INTO batch VALUES (606, 50, 599.989990234375, '2017-12-07 00:00:00', '2017-12-30 00:00:00', 30, 1, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (633, 60, 515, '2017-12-07 00:00:00', '2017-12-07 00:00:00', 23, 4, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (634, 60, 515, '2017-12-07 00:00:00', '2017-12-07 00:00:00', 23, 4, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (635, 60, 515, '2017-12-07 00:00:00', '2017-12-07 00:00:00', 23, 4, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (636, 850, 616, '2017-12-07 00:00:00', '2017-12-07 00:00:00', 3, 4, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (637, 500, 544, '2017-12-07 00:00:00', '2017-12-22 00:00:00', 23, 1, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (647, 56, 85, '2017-12-28 00:00:00', '2017-12-29 00:00:00', 32, 4, true, NULL, '--', NULL);
INSERT INTO batch VALUES (648, 512, 235, '2017-12-28 00:00:00', '2017-12-30 00:00:00', 31, 4, true, NULL, '--', NULL);
INSERT INTO batch VALUES (649, 231, 515, '2017-12-29 00:00:00', '2017-12-30 00:00:00', 31, 4, true, NULL, '--', NULL);
INSERT INTO batch VALUES (640, 654, 986, '2017-12-21 00:00:00', '2017-12-29 00:00:00', 23, 5, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (639, 156, 365, '2017-12-07 00:00:00', '2017-12-07 00:00:00', 3, 5, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (638, 65, 986, '2017-12-07 00:00:00', '2017-12-14 00:00:00', 3, 5, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (644, 513, 325, '2017-12-27 00:00:00', '2017-12-27 00:00:00', 3, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (643, 52, 352, '2017-12-28 00:00:00', '2017-12-28 00:00:00', 30, -1, true, NULL, '--', NULL);
INSERT INTO batch VALUES (1, 1, 1, '2017-11-04 00:03:33.952473', '2017-11-04 00:03:33.952473', 3, 2, true, -1, NULL, NULL);
INSERT INTO batch VALUES (2, 1, 1, '2017-11-04 00:03:43.849983', '2017-11-04 00:03:43.849983', 3, 2, true, -1, NULL, NULL);
INSERT INTO batch VALUES (3, 1, 1, '2017-11-04 00:03:51.268802', '2017-11-04 00:03:51.268802', 23, 2, true, -1, NULL, NULL);
INSERT INTO batch VALUES (607, 6, 10, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 30, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (608, 6, 10, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 30, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (609, 6, 10, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 30, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (610, 6, 10, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 30, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (611, 6, 10, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 30, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (612, 6, 10, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 30, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (613, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (614, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (1557, 300, 150, '2017-12-12 00:00:00', '2017-12-30 00:00:00', 752, 4, true, NULL, '--', '-1');
INSERT INTO batch VALUES (1559, 850, 1560, '2017-12-12 00:00:00', '2017-12-30 00:00:00', 23, 2, true, NULL, '--', '-1');
INSERT INTO batch VALUES (1558, 500, 150, '2017-12-12 00:00:00', '2017-12-28 00:00:00', 752, -1, true, NULL, '--', '-1');
INSERT INTO batch VALUES (1577, 500, 1500, '2017-12-12 00:00:00', '2017-12-29 00:00:00', 752, -1, true, NULL, '--', '-1');
INSERT INTO batch VALUES (615, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (616, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (617, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (618, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (575, 5, 165, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 3, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (576, 5, 165, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 3, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (577, 5, 165, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 3, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (578, 5, 165, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 3, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (579, 5, 165, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 3, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (580, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (581, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (582, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (583, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (584, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (585, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (586, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (587, 8, 95, '2017-11-07 04:43:58.388', '2017-11-07 04:43:58.388', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (619, 7, 13, '2017-11-07 15:33:14.062', '2017-11-07 15:33:14.062', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (654, 12, 13, '2017-11-07 19:17:19.363', '2017-11-07 19:17:19.363', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (664, 17, 13, '2017-11-07 19:29:03.596', '2017-11-07 19:29:03.596', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (676, 10, 95, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (677, 10, 95, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (678, 10, 95, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (679, 10, 95, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (680, 10, 95, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (681, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (682, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (683, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (684, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (685, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (686, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (687, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (688, 5, 13, '2017-11-07 20:12:09.752', '2017-11-07 20:12:09.752', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (714, 123, 13, '2017-11-07 20:47:08.669', '2017-11-07 20:47:08.669', 31, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (716, -70, 95, '2017-11-07 20:49:51.042', '2017-11-07 20:49:51.042', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (718, 152, 62, '2017-12-08 00:00:00', '2017-12-29 00:00:00', 32, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (779, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (740, 10, 123, '2017-11-10 02:14:36.211', '2017-11-10 02:14:36.211', 739, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (780, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (773, 20, 8, '2017-11-12 02:13:19.453', '2017-11-12 02:13:19.453', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (781, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (782, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (783, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (784, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (785, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (791, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (792, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (793, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (794, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (795, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (796, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (786, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (787, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (788, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (789, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (790, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (605, 20, 550.989990234375, '2017-12-07 00:00:00', '2018-03-03 00:00:00', 3, 2, true, NULL, NULL, NULL);
INSERT INTO batch VALUES (726, 10, 10, '2017-12-09 00:00:00', '2018-01-30 00:00:00', 3, 2, true, NULL, '--', '-1');
INSERT INTO batch VALUES (673, 10, 36, '2017-12-07 00:00:00', '2017-12-24 00:00:00', 23, 2, true, NULL, '--', NULL);
INSERT INTO batch VALUES (797, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (798, 1, 8, '2017-11-12 16:09:35.426', '2017-11-12 16:09:35.426', 752, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (819, 13, 111, '2017-11-12 16:20:32.342', '2017-11-12 16:20:32.342', 32, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (821, 55, 123, '2017-11-12 16:31:31.334', '2017-11-12 16:31:31.334', 739, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (823, 90, 10, '2017-11-12 17:14:39.644', '2017-11-12 17:14:39.644', 30, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (824, 33, 13, '2017-11-12 17:14:39.644', '2017-11-12 17:14:39.644', 31, 2, true, NULL, '--', '1');
INSERT INTO batch VALUES (1552, 12, 352, '2017-12-28 00:00:00', '2017-12-28 00:00:00', 30, 2, true, NULL, '[643]', NULL);
INSERT INTO batch VALUES (1554, 40, 352, '2017-12-28 00:00:00', '2017-12-28 00:00:00', 30, 2, true, NULL, '[643]', NULL);
INSERT INTO batch VALUES (1567, 100, 150, '2017-12-12 00:00:00', '2017-12-28 00:00:00', 752, 2, true, NULL, '[1558]', '-1');
INSERT INTO batch VALUES (1569, 100, 150, '2017-12-12 00:00:00', '2017-12-28 00:00:00', 752, 2, true, NULL, '[1558]', '-1');
INSERT INTO batch VALUES (1571, 100, 150, '2017-12-12 00:00:00', '2017-12-28 00:00:00', 752, 2, true, NULL, '[1558]', '-1');
INSERT INTO batch VALUES (1573, 100, 150, '2017-12-12 00:00:00', '2017-12-28 00:00:00', 752, 2, true, NULL, '[1558]', '-1');
INSERT INTO batch VALUES (1575, 100, 150, '2017-12-12 00:00:00', '2017-12-28 00:00:00', 752, 2, true, NULL, '[1558]', '-1');
INSERT INTO batch VALUES (1580, 100, 1500, '2017-12-12 00:00:00', '2017-12-29 00:00:00', 752, 2, true, NULL, '[1577]', '-1');
INSERT INTO batch VALUES (1582, 100, 1500, '2017-12-12 00:00:00', '2017-12-29 00:00:00', 752, 2, true, NULL, '[1577]', '-1');
INSERT INTO batch VALUES (1584, 100, 1500, '2017-12-12 00:00:00', '2017-12-29 00:00:00', 752, 2, true, NULL, '[1577]', '-1');
INSERT INTO batch VALUES (1586, 100, 1500, '2017-12-12 00:00:00', '2017-12-29 00:00:00', 752, 2, true, NULL, '[1577]', '-1');
INSERT INTO batch VALUES (1588, 100, 1500, '2017-12-12 00:00:00', '2017-12-29 00:00:00', 752, 2, true, NULL, '[1577]', '-1');


--
-- Name: batch_id_batch_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('batch_id_batch_seq', 14, true);


--
-- Data for Name: campaign; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO campaign VALUES (0, 'Ninguna', 'No existe una campaña asociada para los descuentos', '1969-12-31 19:00:00', '1969-12-31 19:00:00');
INSERT INTO campaign VALUES (1618, 'C1', 'Primera campanha para testear', '2017-12-15 00:00:00', '2017-12-17 00:00:00');


--
-- Name: campaign_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('campaign_id_seq', 1, false);


--
-- Data for Name: client; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO client VALUES (1, 'Benito Juarez', '48296679', '12354687952', true, 'Los naranjos 123', '555-3462', 'benito07@gmail.com', NULL);
INSERT INTO client VALUES (57, 'teest', '12321', '1232122', true, '2121123', '333', 'test@test.com', NULL);
INSERT INTO client VALUES (712, '656+6+', 'zdgfsd', 'adfsd', true, 'sdfsdf', 'fasdfsd', 'sdf', NULL);
INSERT INTO client VALUES (2, 'Julio Roman', '32548647', '16317746542', true, '12312312312321312', '665-6485', 'julior@gmail.com', NULL);
INSERT INTO client VALUES (745, 'odebrecht', '', '123123123', true, '', '', 'ode@ode.com', NULL);
INSERT INTO client VALUES (746, 'cliente 123', '', '123', true, '', 'test@test.com', '', NULL);
INSERT INTO client VALUES (748, 'gloria', '', '123', true, '', '', 'glo@ria.com', NULL);
INSERT INTO client VALUES (747, 'cliente cliente', '7070707', '', true, '', '', 'test@test.com', NULL);
INSERT INTO client VALUES (1564, 'Keiko Fujimori', '66666666', 'Korrupcion SAC', true, 'Penal de Piedras Gordas', '666-6666', 'keiko@gmail.com', NULL);


--
-- Name: client_id_client_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('client_id_client_seq', 2, true);


--
-- Data for Name: district; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO district VALUES (2, 'Ate', 0.149999999999999994);
INSERT INTO district VALUES (3, 'Barranco', 0.110000000000000001);
INSERT INTO district VALUES (4, 'Breña', 0.0599999999999999978);
INSERT INTO district VALUES (6, 'Chorrillos', 0.179999999999999993);
INSERT INTO district VALUES (7, 'El Agustino', 0.160000000000000003);
INSERT INTO district VALUES (8, 'Jesús María', 0.0599999999999999978);
INSERT INTO district VALUES (9, 'La Molina', 0.119999999999999996);
INSERT INTO district VALUES (10, 'La Victoria', 0.100000000000000006);
INSERT INTO district VALUES (11, 'Lince', 0.0700000000000000067);
INSERT INTO district VALUES (12, 'Magdalena del Mar', 0.0500000000000000028);
INSERT INTO district VALUES (13, 'Miraflores', 0.0800000000000000017);
INSERT INTO district VALUES (15, 'Puente Piedra', 0.119999999999999996);
INSERT INTO district VALUES (16, 'Rimac', 0.100000000000000006);
INSERT INTO district VALUES (17, 'San Isidro', 0.0800000000000000017);
INSERT INTO district VALUES (18, 'Independencia', 0.110000000000000001);
INSERT INTO district VALUES (19, 'San Juan de Miraflores', 0.149999999999999994);
INSERT INTO district VALUES (20, 'San Luis', 0.100000000000000006);
INSERT INTO district VALUES (23, 'Santiago de Surco', 0.100000000000000006);
INSERT INTO district VALUES (24, 'Surquillo', 0.0899999999999999967);
INSERT INTO district VALUES (25, 'Villa María del Triunfo', 0.119999999999999996);
INSERT INTO district VALUES (26, 'San Juan de Lurigancho', 0.119999999999999996);
INSERT INTO district VALUES (27, 'Santa Rosa', 0.0800000000000000017);
INSERT INTO district VALUES (28, 'Los Olivos', 0.0899999999999999967);
INSERT INTO district VALUES (29, 'San Borja', 0.0899999999999999967);
INSERT INTO district VALUES (30, 'Villa El Savador', 0.110000000000000001);
INSERT INTO district VALUES (31, 'Santa Anita', 0.119999999999999996);
INSERT INTO district VALUES (21, 'San Martin de Porres', 0.0700000000000000067);
INSERT INTO district VALUES (14, 'Pueblo Libre', 0.0500000000000000028);
INSERT INTO district VALUES (5, 'Comas', 0.0500000000000000028);
INSERT INTO district VALUES (1, 'Cercado de Lima', 0.0900000035762786865);


--
-- Data for Name: request_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO request_order VALUES (40, '2017-12-01 00:00:00', '2017-12-28 00:00:00', 555, 555, 'EN PROGRESO', 2, 2, NULL, NULL);
INSERT INTO request_order VALUES (37, '2017-12-01 00:00:00', '2017-12-04 00:00:00', 826, 826, 'EN PROGRESO', 1, 1, NULL, NULL);
INSERT INTO request_order VALUES (42, '2017-12-01 00:00:00', '2017-12-15 00:00:00', 476, 476, 'CANCELADO', 2, 1, NULL, NULL);
INSERT INTO request_order VALUES (41, '2017-12-01 00:00:00', '2017-12-22 00:00:00', 660.79998779296875, 660.79998779296875, 'EN PROGRESO', 1, 3, NULL, NULL);
INSERT INTO request_order VALUES (50, '2018-04-01 00:00:00', '2018-04-15 00:00:00', 941.39996337890625, 941.39996337890625, 'EN PROGRESO', 1, 3, NULL, NULL);
INSERT INTO request_order VALUES (49, '2017-12-01 00:00:00', '2017-12-22 00:00:00', 1195.4000244140625, 1195.4000244140625, 'CANCELADO', 1, 2, NULL, NULL);
INSERT INTO request_order VALUES (721, '2017-12-09 00:00:00', '2017-12-13 00:00:00', 39, 39, 'EN PROGRESO', 2, 2, NULL, NULL);
INSERT INTO request_order VALUES (722, '2017-12-10 00:00:00', '2017-12-14 00:00:00', 261, 261, 'EN PROGRESO', 57, 1, NULL, NULL);
INSERT INTO request_order VALUES (724, '2017-12-10 00:00:00', '2017-12-13 00:00:00', 13, 13, 'EN PROGRESO', 57, 1, NULL, NULL);
INSERT INTO request_order VALUES (725, '2017-12-10 00:00:00', '2017-12-15 00:00:00', 20, 20, 'ENTREGADO', 1, 1, NULL, NULL);
INSERT INTO request_order VALUES (728, '2017-12-10 00:00:00', '2017-12-12 00:00:00', 36, 36, 'ENTREGADO', 1, 1, NULL, NULL);
INSERT INTO request_order VALUES (753, '2017-12-11 00:00:00', '2017-12-13 00:00:00', 660.79998779296875, 8.260009765625, 'EN PROGRESO', 748, 1, NULL, NULL);
INSERT INTO request_order VALUES (827, '2017-12-13 00:00:00', '2017-12-30 00:00:00', 1720, 247, 'EN PROGRESO', 746, 1, NULL, NULL);
INSERT INTO request_order VALUES (832, '2017-12-13 00:00:00', '2017-12-30 00:00:00', 1720, 247, 'EN PROGRESO', 746, 1, NULL, NULL);


--
-- Data for Name: complaint; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO complaint VALUES (61, 'ttest', 'Aceptado', 42);
INSERT INTO complaint VALUES (66, 'hols', 'Rechazado', 50);
INSERT INTO complaint VALUES (67, 'otra prueba', 'En trámite', 37);
INSERT INTO complaint VALUES (85, 'real', 'Aceptado', 50);
INSERT INTO complaint VALUES (93, 'prueba final', 'Aceptado', 50);
INSERT INTO complaint VALUES (98, 'reclamo valido', 'Aceptado', 50);
INSERT INTO complaint VALUES (707, 'se rompio 1tornillo', 'Aceptado', 50);


--
-- Name: disclaim_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('disclaim_seq', 1, false);


--
-- Data for Name: dispatch_move; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_move VALUES (65, 2, 57, '2017-11-04 02:15:21.101', '1', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (60, 2, 1, '2017-11-02 04:46:48.638', '2', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (74, 1, 3, '2017-11-05 20:23:10.176', '2', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (69, 0, 0, '2017-11-04 04:59:24.382011', '1', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (75, 1, 4, '2017-11-05 20:31:09.558', '2', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (92, 2, 1, '2017-11-06 02:53:34.06', '1', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (76, 1, 25, '2017-11-05 20:40:34.34', '0', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (91, 2, 1, '2017-11-06 02:49:59.286', '1', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (84, 2, 1, '2017-11-06 02:34:04.764', '1', 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (641, 3, 0, '2017-11-07 16:28:55.052', '3', 640, '2017-12-07 00:00:00');
INSERT INTO dispatch_move VALUES (642, 3, 0, '2017-11-07 16:28:55.052', '3', 640, '2017-12-21 00:00:00');
INSERT INTO dispatch_move VALUES (645, 3, 0, '2017-11-07 16:45:03.337', '3', 644, '2017-12-28 00:00:00');
INSERT INTO dispatch_move VALUES (646, 3, 0, '2017-11-07 16:45:03.337', '3', 644, '2017-12-27 00:00:00');
INSERT INTO dispatch_move VALUES (674, 3, 0, '2017-11-07 20:07:52.57', '3', 673, '2017-12-07 00:00:00');
INSERT INTO dispatch_move VALUES (675, 1, 1, '2017-11-07 20:09:08.627', '2', 673, '2017-11-07 20:09:08.627');
INSERT INTO dispatch_move VALUES (719, 4, 1, '2017-11-08 19:34:54.962', '4', 718, '2017-12-08 00:00:00');
INSERT INTO dispatch_move VALUES (727, 3, 0, '2017-11-09 18:38:30.429', '3', 726, '2017-12-09 00:00:00');
INSERT INTO dispatch_move VALUES (731, 1, 1, '2017-11-09 19:21:57.461', '0', 644, '2017-11-09 19:21:57.461');
INSERT INTO dispatch_move VALUES (732, 1, 1, '2017-11-09 19:41:30.3', '2', 726, '2017-11-09 19:41:30.3');
INSERT INTO dispatch_move VALUES (733, 1, 1, '2017-11-09 19:55:59.583', '2', 726, '2017-11-09 19:55:59.583');
INSERT INTO dispatch_move VALUES (734, 1, 1, '2017-11-09 20:08:52.573', '2', 726, '2017-11-09 20:08:52.573');
INSERT INTO dispatch_move VALUES (735, 1, 1, '2017-11-09 20:22:05.935', '2', 726, '2017-11-09 20:22:05.935');
INSERT INTO dispatch_move VALUES (736, 2, 1, '2017-11-09 23:39:03.605', '1', 640, '2017-11-09 23:39:03.605');
INSERT INTO dispatch_move VALUES (737, 2, 1, '2017-11-09 23:39:11.232', '1', 639, '2017-11-09 23:39:11.232');
INSERT INTO dispatch_move VALUES (738, 2, 1, '2017-11-09 23:39:18.482', '1', 638, '2017-11-09 23:39:18.482');
INSERT INTO dispatch_move VALUES (769, 1, 1, '2017-11-11 16:01:00.692', '2', 673, '2017-11-11 16:01:00.692');
INSERT INTO dispatch_move VALUES (775, 1, 1, '2017-11-12 04:27:54.996', '2', 644, '2017-11-12 04:27:54.996');
INSERT INTO dispatch_move VALUES (776, 1, 1, '2017-11-12 04:28:11.979', '2', 643, '2017-11-12 04:28:11.979');
INSERT INTO dispatch_move VALUES (777, 1, 1, '2017-11-12 04:28:30.179', '2', 605, '2017-11-12 04:28:30.179');
INSERT INTO dispatch_move VALUES (1560, 3, 0, '2017-11-12 19:47:50.577', '3', 1559, '2017-12-12 00:00:00');
INSERT INTO dispatch_move VALUES (1561, 3, 0, '2017-11-12 19:47:50.577', '3', 1559, '2017-12-12 00:00:00');
INSERT INTO dispatch_move VALUES (1562, 1, 1, '2017-11-12 19:50:01.149', '2', 1558, '2017-11-12 19:50:01.149');
INSERT INTO dispatch_move VALUES (1563, 1, 1, '2017-11-12 19:50:07.472', '2', 1559, '2017-11-12 19:50:07.472');
INSERT INTO dispatch_move VALUES (1578, 3, 0, '2017-11-12 21:30:19.632', '3', 1577, '2017-12-12 00:00:00');
INSERT INTO dispatch_move VALUES (1579, 1, 1, '2017-11-12 21:31:01.435', '2', 1577, '2017-11-12 21:31:01.435');


--
-- Data for Name: dispatch_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_order VALUES (1, 50, 1, 'EN PROGRESO', NULL, NULL);


--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_id_dispatch_order_seq', 1, false);


--
-- Data for Name: dispatch_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_order_line VALUES (2, 1, 23, 4);
INSERT INTO dispatch_order_line VALUES (1, 1, 3, 3);


--
-- Name: dispatch_order_line_id_dispatch_order_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_line_id_dispatch_order_line_seq', 1, false);


--
-- Name: district_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('district_id_seq', 1, false);


--
-- Data for Name: document; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Name: document_id_document_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('document_id_document_seq', 1, false);


--
-- Data for Name: group_batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Name: group_batch_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('group_batch_id_seq', 3, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1647, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO role VALUES (1593, 'Admin');


--
-- Data for Name: users; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO users VALUES (21, 'Miguel', 'Guanira', '123456', 'guani@ra.com', '2017-10-27 23:53:16.454', true, 1593, '2017-10-27 23:53:16.454', 'mguani');


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO vehicle VALUES (33, 1230, 100, true, 1, 'AAA-123');
INSERT INTO vehicle VALUES (2, 2500, 54, true, 1, '123-ASD');
INSERT INTO vehicle VALUES (838, 3200, 150, true, 1, 'ACM-1PT');


--
-- Data for Name: movement; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO movement VALUES (1568, '2017-11-12 21:16:23.305', 21, 100, 33, 1, 1, 476, 1567);
INSERT INTO movement VALUES (1570, '2017-11-12 21:16:23.305', 21, 100, 33, 1, 1, 474, 1569);
INSERT INTO movement VALUES (1572, '2017-11-12 21:16:23.305', 21, 100, 33, 1, 1, 472, 1571);
INSERT INTO movement VALUES (1574, '2017-11-12 21:16:23.305', 21, 100, 33, 1, 1, 470, 1573);
INSERT INTO movement VALUES (1576, '2017-11-12 21:16:23.305', 21, 100, 33, 1, 1, 488, 1575);
INSERT INTO movement VALUES (1581, '2017-11-12 21:44:25.462', 21, 100, 33, 1, 1, 937, 1580);
INSERT INTO movement VALUES (1583, '2017-11-12 21:44:25.462', 21, 100, 33, 1, 1, 935, 1582);
INSERT INTO movement VALUES (1585, '2017-11-12 21:44:25.462', 21, 100, 33, 1, 1, 933, 1584);
INSERT INTO movement VALUES (1587, '2017-11-12 21:44:25.462', 21, 100, 33, 1, 1, 931, 1586);
INSERT INTO movement VALUES (1589, '2017-11-12 21:44:25.462', 21, 100, 33, 1, 1, 478, 1588);
INSERT INTO movement VALUES (1590, '2017-11-12 21:44:25.462', 21, 10, 2, 1, 1, 486, 673);


--
-- Name: movement_id_movement_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('movement_id_movement_seq', 3, true);


--
-- Data for Name: movementxbatch; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Data for Name: movementxdispatch; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Data for Name: movementxdocument; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Data for Name: view; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO view VALUES (1, 'products');
INSERT INTO view VALUES (2, 'warehouse');
INSERT INTO view VALUES (3, 'product_types');
INSERT INTO view VALUES (4, 'racks');
INSERT INTO view VALUES (5, 'vehicles');
INSERT INTO view VALUES (6, 'areas');
INSERT INTO view VALUES (7, 'entries_warehouse');
INSERT INTO view VALUES (8, 'departures_warehouse');
INSERT INTO view VALUES (9, 'request_orders');
INSERT INTO view VALUES (10, 'sale_conditions');
INSERT INTO view VALUES (11, 'campaigns');
INSERT INTO view VALUES (12, 'request_statuses');
INSERT INTO view VALUES (13, 'clients');
INSERT INTO view VALUES (14, 'districts');
INSERT INTO view VALUES (15, 'refunds');
INSERT INTO view VALUES (16, 'complaints');
INSERT INTO view VALUES (17, 'entries_dispatch');
INSERT INTO view VALUES (18, 'departures_dispatch');
INSERT INTO view VALUES (19, 'kardex');
INSERT INTO view VALUES (20, 'expiration');
INSERT INTO view VALUES (21, 'stocks');
INSERT INTO view VALUES (22, 'users');
INSERT INTO view VALUES (23, 'roles');
INSERT INTO view VALUES (24, 'log');


--
-- Data for Name: permission; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO permission VALUES (1, 1593, true, false, 1594);
INSERT INTO permission VALUES (2, 1593, true, false, 1595);
INSERT INTO permission VALUES (3, 1593, true, false, 1596);
INSERT INTO permission VALUES (4, 1593, true, false, 1597);
INSERT INTO permission VALUES (5, 1593, true, false, 1598);
INSERT INTO permission VALUES (6, 1593, true, false, 1599);
INSERT INTO permission VALUES (7, 1593, true, false, 1600);
INSERT INTO permission VALUES (8, 1593, true, false, 1601);
INSERT INTO permission VALUES (9, 1593, true, false, 1602);
INSERT INTO permission VALUES (10, 1593, true, false, 1603);
INSERT INTO permission VALUES (11, 1593, true, false, 1604);
INSERT INTO permission VALUES (12, 1593, true, false, 1605);
INSERT INTO permission VALUES (13, 1593, true, false, 1606);
INSERT INTO permission VALUES (14, 1593, true, false, 1607);
INSERT INTO permission VALUES (15, 1593, true, false, 1608);
INSERT INTO permission VALUES (16, 1593, true, false, 1609);
INSERT INTO permission VALUES (17, 1593, true, false, 1610);
INSERT INTO permission VALUES (18, 1593, true, false, 1611);
INSERT INTO permission VALUES (19, 1593, true, false, 1612);
INSERT INTO permission VALUES (20, 1593, true, false, 1613);
INSERT INTO permission VALUES (21, 1593, true, false, 1614);
INSERT INTO permission VALUES (22, 1593, true, false, 1615);
INSERT INTO permission VALUES (23, 1593, true, false, 1616);
INSERT INTO permission VALUES (24, 1593, true, false, 1617);


--
-- Name: permission_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('permission_seq', 5, true);


--
-- Name: product_id_product_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('product_id_product_seq', 8, true);


--
-- Name: product_type_id_product_type_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('product_type_id_product_type_seq_1', 2, true);


--
-- Data for Name: productxwarehouse; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO productxwarehouse VALUES (752, 1, 1020, 1020);
INSERT INTO productxwarehouse VALUES (23, 1, 65, 65);
INSERT INTO productxwarehouse VALUES (1592, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (32, 1, 13, 13);
INSERT INTO productxwarehouse VALUES (31, 1, 33, 24);
INSERT INTO productxwarehouse VALUES (739, 1, 55, 44);
INSERT INTO productxwarehouse VALUES (30, 1, 142, 117);
INSERT INTO productxwarehouse VALUES (3, 1, 85, 85);


--
-- Data for Name: rack; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO rack VALUES (5, 1, 14, 5, 10, 5, 0);
INSERT INTO rack VALUES (6, 1, 26, 5, 10, 5, 0);
INSERT INTO rack VALUES (7, 1, 2, 8, 10, 5, 0);
INSERT INTO rack VALUES (8, 1, 14, 8, 10, 5, 0);
INSERT INTO rack VALUES (408, 1, 2, 2, 10, 5, 0);
INSERT INTO rack VALUES (839, 1, 25, 15, 10, 5, 0);


--
-- Name: rack_id_rack_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('rack_id_rack_seq', 9, true);


--
-- Data for Name: refund; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO refund VALUES (64, 61, 'En proceso', NULL, 'Nota de crédito');
INSERT INTO refund VALUES (87, 85, 'En proceso', '2017-11-06 02:48:52.032011', 'Físico');
INSERT INTO refund VALUES (94, 93, 'En proceso', '2017-11-06 02:54:01.262446', 'Físico');
INSERT INTO refund VALUES (99, 98, 'Entregado', '2017-11-06 02:59:05.326527', 'Nota de crédito');
INSERT INTO refund VALUES (708, 707, NULL, '2017-11-07 20:30:18.530348', 'Nota de crédito');


--
-- Data for Name: refund_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO refund_line VALUES (100, 99, 1, 51);
INSERT INTO refund_line VALUES (101, 99, 1, 52);
INSERT INTO refund_line VALUES (102, 99, 1, 56);
INSERT INTO refund_line VALUES (709, 708, 1, 51);
INSERT INTO refund_line VALUES (710, 708, 1, 52);
INSERT INTO refund_line VALUES (711, 708, 1, 56);


--
-- Name: refund_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('refund_line_seq', 1, false);


--
-- Name: refund_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('refund_seq', 1, false);


--
-- Name: request_order_id_request_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('request_order_id_request_order_seq', 1, false);


--
-- Data for Name: request_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO request_order_line VALUES (51, 3, 165.199996948242188, 50, 3);
INSERT INTO request_order_line VALUES (52, 5, 13, 50, 31);
INSERT INTO request_order_line VALUES (56, 4, 95.1999969482421875, 50, 23);
INSERT INTO request_order_line VALUES (723, 3, 13, 722, 31);
INSERT INTO request_order_line VALUES (729, 1, 10, 728, 30);
INSERT INTO request_order_line VALUES (730, 2, 13, 728, 31);
INSERT INTO request_order_line VALUES (754, 3, 165.199996948242188, 753, 3);
INSERT INTO request_order_line VALUES (755, 1, 165.199996948242188, 753, 3);
INSERT INTO request_order_line VALUES (833, 12, 10, 832, 30);
INSERT INTO request_order_line VALUES (834, 9, 13, 832, 31);
INSERT INTO request_order_line VALUES (835, 11, 123, 832, 739);
INSERT INTO request_order_line VALUES (836, 13, 10, 832, 30);


--
-- Name: request_order_line_id_request_order_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('request_order_line_id_request_order_line_seq', 1, false);


--
-- Data for Name: request_status; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO request_status VALUES (1, 'Cotización', NULL);
INSERT INTO request_status VALUES (2, 'En proceso', NULL);
INSERT INTO request_status VALUES (3, 'Entregado', NULL);
INSERT INTO request_status VALUES (4, 'Anulado', NULL);


--
-- Name: role_id_role_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('role_id_role_seq', 7, true);


--
-- Data for Name: sale_condition; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO sale_condition VALUES (1591, '2017-12-14 00:00:00', '2017-12-21 00:00:00', 16.3999996185302734, '1', 0, 752, 0);


--
-- Name: sale_condition_id_sale_condition_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('sale_condition_id_sale_condition_seq', 1, false);


--
-- Data for Name: sale_condition_type; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO sale_condition_type VALUES (1, 'Por producto');
INSERT INTO sale_condition_type VALUES (2, 'Por tipo de producto');


--
-- Name: sale_condition_type_id_sale_condition_type_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('sale_condition_type_id_sale_condition_type_seq_1', 2, true);


--
-- Name: unit_of_measure_id_unit_of_measure_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('unit_of_measure_id_unit_of_measure_seq_1', 2, true);


--
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('users_id_user_seq', 1, true);


--
-- Name: vehicle_id_vehicle_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('vehicle_id_vehicle_seq_1', 7, true);


--
-- Name: view_id_view_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('view_id_view_seq_1', 24, true);


--
-- Name: warehouse_id_warehouse_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('warehouse_id_warehouse_seq', 4, true);


--
-- Data for Name: zone; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO zone VALUES (869, 1, 839, 27, '16', '4', true);
INSERT INTO zone VALUES (870, 1, 839, 28, '15', '0', true);
INSERT INTO zone VALUES (871, 1, 839, 28, '16', '0', true);
INSERT INTO zone VALUES (872, 1, 839, 28, '15', '1', true);
INSERT INTO zone VALUES (873, 1, 839, 28, '16', '1', true);
INSERT INTO zone VALUES (874, 1, 839, 28, '15', '2', true);
INSERT INTO zone VALUES (875, 1, 839, 28, '16', '2', true);
INSERT INTO zone VALUES (876, 1, 839, 28, '15', '3', true);
INSERT INTO zone VALUES (877, 1, 839, 28, '16', '3', true);
INSERT INTO zone VALUES (878, 1, 839, 28, '15', '4', true);
INSERT INTO zone VALUES (879, 1, 839, 28, '16', '4', true);
INSERT INTO zone VALUES (880, 1, 839, 29, '15', '0', true);
INSERT INTO zone VALUES (881, 1, 839, 29, '16', '0', true);
INSERT INTO zone VALUES (882, 1, 839, 29, '15', '1', true);
INSERT INTO zone VALUES (883, 1, 839, 29, '16', '1', true);
INSERT INTO zone VALUES (884, 1, 839, 29, '15', '2', true);
INSERT INTO zone VALUES (885, 1, 839, 29, '16', '2', true);
INSERT INTO zone VALUES (886, 1, 839, 29, '15', '3', true);
INSERT INTO zone VALUES (887, 1, 839, 29, '16', '3', true);
INSERT INTO zone VALUES (888, 1, 839, 29, '15', '4', true);
INSERT INTO zone VALUES (889, 1, 839, 29, '16', '4', true);
INSERT INTO zone VALUES (890, 1, 839, 30, '15', '0', true);
INSERT INTO zone VALUES (891, 1, 839, 30, '16', '0', true);
INSERT INTO zone VALUES (892, 1, 839, 30, '15', '1', true);
INSERT INTO zone VALUES (893, 1, 839, 30, '16', '1', true);
INSERT INTO zone VALUES (894, 1, 839, 30, '15', '2', true);
INSERT INTO zone VALUES (895, 1, 839, 30, '16', '2', true);
INSERT INTO zone VALUES (896, 1, 839, 30, '15', '3', true);
INSERT INTO zone VALUES (897, 1, 839, 30, '16', '3', true);
INSERT INTO zone VALUES (898, 1, 839, 30, '15', '4', true);
INSERT INTO zone VALUES (899, 1, 839, 30, '16', '4', true);
INSERT INTO zone VALUES (900, 1, 839, 31, '15', '0', true);
INSERT INTO zone VALUES (901, 1, 839, 31, '16', '0', true);
INSERT INTO zone VALUES (902, 1, 839, 31, '15', '1', true);
INSERT INTO zone VALUES (903, 1, 839, 31, '16', '1', true);
INSERT INTO zone VALUES (904, 1, 839, 31, '15', '2', true);
INSERT INTO zone VALUES (905, 1, 839, 31, '16', '2', true);
INSERT INTO zone VALUES (906, 1, 839, 31, '15', '3', true);
INSERT INTO zone VALUES (907, 1, 839, 31, '16', '3', true);
INSERT INTO zone VALUES (908, 1, 839, 31, '15', '4', true);
INSERT INTO zone VALUES (909, 1, 839, 31, '16', '4', true);
INSERT INTO zone VALUES (910, 1, 839, 32, '15', '0', true);
INSERT INTO zone VALUES (911, 1, 839, 32, '16', '0', true);
INSERT INTO zone VALUES (912, 1, 839, 32, '15', '1', true);
INSERT INTO zone VALUES (913, 1, 839, 32, '16', '1', true);
INSERT INTO zone VALUES (914, 1, 839, 32, '15', '2', true);
INSERT INTO zone VALUES (915, 1, 839, 32, '16', '2', true);
INSERT INTO zone VALUES (916, 1, 839, 32, '15', '3', true);
INSERT INTO zone VALUES (917, 1, 839, 32, '16', '3', true);
INSERT INTO zone VALUES (918, 1, 839, 32, '15', '4', true);
INSERT INTO zone VALUES (919, 1, 839, 32, '16', '4', true);
INSERT INTO zone VALUES (920, 1, 839, 33, '15', '0', true);
INSERT INTO zone VALUES (921, 1, 839, 33, '16', '0', true);
INSERT INTO zone VALUES (922, 1, 839, 33, '15', '1', true);
INSERT INTO zone VALUES (923, 1, 839, 33, '16', '1', true);
INSERT INTO zone VALUES (924, 1, 839, 33, '15', '2', true);
INSERT INTO zone VALUES (925, 1, 839, 33, '16', '2', true);
INSERT INTO zone VALUES (926, 1, 839, 33, '15', '3', true);
INSERT INTO zone VALUES (927, 1, 839, 33, '16', '3', true);
INSERT INTO zone VALUES (489, 1, 408, 10, '2', '0', true);
INSERT INTO zone VALUES (490, 1, 408, 10, '3', '0', true);
INSERT INTO zone VALUES (491, 1, 408, 10, '2', '1', true);
INSERT INTO zone VALUES (492, 1, 408, 10, '3', '1', true);
INSERT INTO zone VALUES (493, 1, 408, 10, '2', '2', true);
INSERT INTO zone VALUES (494, 1, 408, 10, '3', '2', true);
INSERT INTO zone VALUES (495, 1, 408, 10, '2', '3', true);
INSERT INTO zone VALUES (496, 1, 408, 10, '3', '3', true);
INSERT INTO zone VALUES (497, 1, 408, 10, '2', '4', true);
INSERT INTO zone VALUES (498, 1, 408, 10, '3', '4', true);
INSERT INTO zone VALUES (499, 1, 408, 11, '2', '0', true);
INSERT INTO zone VALUES (500, 1, 408, 11, '3', '0', true);
INSERT INTO zone VALUES (501, 1, 408, 11, '2', '1', true);
INSERT INTO zone VALUES (502, 1, 408, 11, '3', '1', true);
INSERT INTO zone VALUES (503, 1, 408, 11, '2', '2', true);
INSERT INTO zone VALUES (504, 1, 408, 11, '3', '2', true);
INSERT INTO zone VALUES (505, 1, 408, 11, '2', '3', true);
INSERT INTO zone VALUES (506, 1, 408, 11, '3', '3', true);
INSERT INTO zone VALUES (507, 1, 408, 11, '2', '4', true);
INSERT INTO zone VALUES (508, 1, 408, 11, '3', '4', true);
INSERT INTO zone VALUES (417, 1, 408, 2, '2', '4', true);
INSERT INTO zone VALUES (415, 1, 408, 2, '2', '3', true);
INSERT INTO zone VALUES (413, 1, 408, 2, '2', '2', true);
INSERT INTO zone VALUES (411, 1, 408, 2, '2', '1', true);
INSERT INTO zone VALUES (409, 1, 408, 2, '2', '0', true);
INSERT INTO zone VALUES (427, 1, 408, 3, '2', '4', true);
INSERT INTO zone VALUES (425, 1, 408, 3, '2', '3', true);
INSERT INTO zone VALUES (423, 1, 408, 3, '2', '2', true);
INSERT INTO zone VALUES (421, 1, 408, 3, '2', '1', true);
INSERT INTO zone VALUES (419, 1, 408, 3, '2', '0', true);
INSERT INTO zone VALUES (418, 1, 408, 2, '3', '4', true);
INSERT INTO zone VALUES (416, 1, 408, 2, '3', '3', true);
INSERT INTO zone VALUES (414, 1, 408, 2, '3', '2', true);
INSERT INTO zone VALUES (412, 1, 408, 2, '3', '1', true);
INSERT INTO zone VALUES (410, 1, 408, 2, '3', '0', true);
INSERT INTO zone VALUES (437, 1, 408, 4, '2', '4', true);
INSERT INTO zone VALUES (435, 1, 408, 4, '2', '3', true);
INSERT INTO zone VALUES (433, 1, 408, 4, '2', '2', true);
INSERT INTO zone VALUES (431, 1, 408, 4, '2', '1', true);
INSERT INTO zone VALUES (429, 1, 408, 4, '2', '0', true);
INSERT INTO zone VALUES (428, 1, 408, 3, '3', '4', true);
INSERT INTO zone VALUES (426, 1, 408, 3, '3', '3', true);
INSERT INTO zone VALUES (424, 1, 408, 3, '3', '2', true);
INSERT INTO zone VALUES (422, 1, 408, 3, '3', '1', true);
INSERT INTO zone VALUES (420, 1, 408, 3, '3', '0', true);
INSERT INTO zone VALUES (447, 1, 408, 5, '2', '4', true);
INSERT INTO zone VALUES (445, 1, 408, 5, '2', '3', true);
INSERT INTO zone VALUES (436, 1, 408, 4, '3', '3', true);
INSERT INTO zone VALUES (434, 1, 408, 4, '3', '2', true);
INSERT INTO zone VALUES (432, 1, 408, 4, '3', '1', true);
INSERT INTO zone VALUES (430, 1, 408, 4, '3', '0', true);
INSERT INTO zone VALUES (443, 1, 408, 5, '2', '2', true);
INSERT INTO zone VALUES (441, 1, 408, 5, '2', '1', true);
INSERT INTO zone VALUES (439, 1, 408, 5, '2', '0', true);
INSERT INTO zone VALUES (438, 1, 408, 4, '3', '4', true);
INSERT INTO zone VALUES (457, 1, 408, 6, '2', '4', true);
INSERT INTO zone VALUES (455, 1, 408, 6, '2', '3', true);
INSERT INTO zone VALUES (453, 1, 408, 6, '2', '2', true);
INSERT INTO zone VALUES (451, 1, 408, 6, '2', '1', true);
INSERT INTO zone VALUES (449, 1, 408, 6, '2', '0', true);
INSERT INTO zone VALUES (448, 1, 408, 5, '3', '4', true);
INSERT INTO zone VALUES (446, 1, 408, 5, '3', '3', true);
INSERT INTO zone VALUES (444, 1, 408, 5, '3', '2', true);
INSERT INTO zone VALUES (442, 1, 408, 5, '3', '1', true);
INSERT INTO zone VALUES (440, 1, 408, 5, '3', '0', true);
INSERT INTO zone VALUES (465, 1, 408, 7, '2', '3', true);
INSERT INTO zone VALUES (467, 1, 408, 7, '2', '4', true);
INSERT INTO zone VALUES (463, 1, 408, 7, '2', '2', true);
INSERT INTO zone VALUES (461, 1, 408, 7, '2', '1', true);
INSERT INTO zone VALUES (459, 1, 408, 7, '2', '0', true);
INSERT INTO zone VALUES (458, 1, 408, 6, '3', '4', true);
INSERT INTO zone VALUES (456, 1, 408, 6, '3', '3', true);
INSERT INTO zone VALUES (454, 1, 408, 6, '3', '2', true);
INSERT INTO zone VALUES (452, 1, 408, 6, '3', '1', true);
INSERT INTO zone VALUES (450, 1, 408, 6, '3', '0', true);
INSERT INTO zone VALUES (477, 1, 408, 8, '2', '4', true);
INSERT INTO zone VALUES (475, 1, 408, 8, '2', '3', true);
INSERT INTO zone VALUES (473, 1, 408, 8, '2', '2', true);
INSERT INTO zone VALUES (471, 1, 408, 8, '2', '1', true);
INSERT INTO zone VALUES (469, 1, 408, 8, '2', '0', true);
INSERT INTO zone VALUES (468, 1, 408, 7, '3', '4', true);
INSERT INTO zone VALUES (466, 1, 408, 7, '3', '3', true);
INSERT INTO zone VALUES (464, 1, 408, 7, '3', '2', true);
INSERT INTO zone VALUES (462, 1, 408, 7, '3', '1', true);
INSERT INTO zone VALUES (460, 1, 408, 7, '3', '0', true);
INSERT INTO zone VALUES (487, 1, 408, 9, '2', '4', true);
INSERT INTO zone VALUES (485, 1, 408, 9, '2', '3', true);
INSERT INTO zone VALUES (483, 1, 408, 9, '2', '2', true);
INSERT INTO zone VALUES (481, 1, 408, 9, '2', '1', true);
INSERT INTO zone VALUES (479, 1, 408, 9, '2', '0', true);
INSERT INTO zone VALUES (840, 1, 839, 25, '15', '0', true);
INSERT INTO zone VALUES (841, 1, 839, 25, '16', '0', true);
INSERT INTO zone VALUES (842, 1, 839, 25, '15', '1', true);
INSERT INTO zone VALUES (843, 1, 839, 25, '16', '1', true);
INSERT INTO zone VALUES (844, 1, 839, 25, '15', '2', true);
INSERT INTO zone VALUES (845, 1, 839, 25, '16', '2', true);
INSERT INTO zone VALUES (846, 1, 839, 25, '15', '3', true);
INSERT INTO zone VALUES (847, 1, 839, 25, '16', '3', true);
INSERT INTO zone VALUES (848, 1, 839, 25, '15', '4', true);
INSERT INTO zone VALUES (849, 1, 839, 25, '16', '4', true);
INSERT INTO zone VALUES (850, 1, 839, 26, '15', '0', true);
INSERT INTO zone VALUES (851, 1, 839, 26, '16', '0', true);
INSERT INTO zone VALUES (852, 1, 839, 26, '15', '1', true);
INSERT INTO zone VALUES (853, 1, 839, 26, '16', '1', true);
INSERT INTO zone VALUES (854, 1, 839, 26, '15', '2', true);
INSERT INTO zone VALUES (855, 1, 839, 26, '16', '2', true);
INSERT INTO zone VALUES (856, 1, 839, 26, '15', '3', true);
INSERT INTO zone VALUES (857, 1, 839, 26, '16', '3', true);
INSERT INTO zone VALUES (858, 1, 839, 26, '15', '4', true);
INSERT INTO zone VALUES (859, 1, 839, 26, '16', '4', true);
INSERT INTO zone VALUES (860, 1, 839, 27, '15', '0', true);
INSERT INTO zone VALUES (861, 1, 839, 27, '16', '0', true);
INSERT INTO zone VALUES (862, 1, 839, 27, '15', '1', true);
INSERT INTO zone VALUES (863, 1, 839, 27, '16', '1', true);
INSERT INTO zone VALUES (864, 1, 839, 27, '15', '2', true);
INSERT INTO zone VALUES (865, 1, 839, 27, '16', '2', true);
INSERT INTO zone VALUES (866, 1, 839, 27, '15', '3', true);
INSERT INTO zone VALUES (867, 1, 839, 27, '16', '3', true);
INSERT INTO zone VALUES (868, 1, 839, 27, '15', '4', true);
INSERT INTO zone VALUES (476, 1, 408, 8, '3', '3', true);
INSERT INTO zone VALUES (474, 1, 408, 8, '3', '2', true);
INSERT INTO zone VALUES (472, 1, 408, 8, '3', '1', true);
INSERT INTO zone VALUES (470, 1, 408, 8, '3', '0', true);
INSERT INTO zone VALUES (488, 1, 408, 9, '3', '4', true);
INSERT INTO zone VALUES (928, 1, 839, 33, '15', '4', true);
INSERT INTO zone VALUES (929, 1, 839, 33, '16', '4', true);
INSERT INTO zone VALUES (930, 1, 839, 34, '15', '0', true);
INSERT INTO zone VALUES (932, 1, 839, 34, '15', '1', true);
INSERT INTO zone VALUES (934, 1, 839, 34, '15', '2', true);
INSERT INTO zone VALUES (936, 1, 839, 34, '15', '3', true);
INSERT INTO zone VALUES (938, 1, 839, 34, '15', '4', true);
INSERT INTO zone VALUES (939, 1, 839, 34, '16', '4', true);
INSERT INTO zone VALUES (937, 1, 839, 34, '16', '3', true);
INSERT INTO zone VALUES (935, 1, 839, 34, '16', '2', true);
INSERT INTO zone VALUES (933, 1, 839, 34, '16', '1', true);
INSERT INTO zone VALUES (931, 1, 839, 34, '16', '0', true);
INSERT INTO zone VALUES (478, 1, 408, 8, '3', '4', true);
INSERT INTO zone VALUES (486, 1, 408, 9, '3', '3', true);
INSERT INTO zone VALUES (480, 1, 408, 9, '3', '0', true);
INSERT INTO zone VALUES (482, 1, 408, 9, '3', '1', true);
INSERT INTO zone VALUES (484, 1, 408, 9, '3', '2', true);


--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('zone_id_zone_seq_1', 1, false);


--
-- PostgreSQL database dump complete
--

