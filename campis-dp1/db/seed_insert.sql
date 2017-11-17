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

INSERT INTO warehouse VALUES (1894, 'Deposito', 100, 100, true);
INSERT INTO warehouse VALUES (1, 'Principal', 150, 100, true);


--
-- Data for Name: area; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO area VALUES (1, 'test_area1', 10, 10, 0, 0, 1, 1);
INSERT INTO area VALUES (778, 'area1', 23, 13, 891, 123, 1, 1);
INSERT INTO area VALUES (3, 'test_area3', 10, 10, 0, 0, 1, 2);
INSERT INTO area VALUES (1550, 'qwer2', 2, 2, 2, 2, 1, 2);
INSERT INTO area VALUES (2, 'test_area2', 10, 10, 0, 0, 1, 3);
INSERT INTO area VALUES (1686, 'Zona Nice', 10, 10, 999, 999, 1, 3);


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
INSERT INTO product VALUES (752, 'Clavos', 'Caja de clavos simple.', 1, 1, 1, 'Pablito', 8, 1, 3, 100);
INSERT INTO product VALUES (3, 'Arena Fina', 'Arean fina para construccion.', 13, -1, 36, 'SOL', 165.199999999999989, 1, 1, 40);
INSERT INTO product VALUES (31, 'Cemento', '', 193, 188, 10, 'Sol', 130, 1, 1, 50);
INSERT INTO product VALUES (30, 'Tornillos', 'tornillos.....', 13, 12, 1, 'Pls', 100, 1, 1, 50);
INSERT INTO product VALUES (1592, 'Tubos', '', 1, 1, 12, 'acme', 10, 1, 1, 1000);


--
-- Data for Name: batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO batch VALUES (2681, 150, 200, '2017-11-15 00:00:00', '2017-11-30 00:00:00', 3, 2, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2647, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 2, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2651, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 2, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2649, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 5, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2653, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 5, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2602, 10, 100, '2017-11-15 00:00:00', '2017-11-29 00:00:00', 752, 4, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2607, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2609, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2611, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2613, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2615, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2617, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2619, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2621, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2623, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2625, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2627, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2629, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2631, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2633, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2635, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2637, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2639, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2641, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2643, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2645, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 3, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2663, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 1, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2665, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 1, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2655, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 5, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2657, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 5, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2659, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 5, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2705, 10000, 1000, '2017-11-01 00:00:00', '2017-11-30 00:00:00', 752, 2, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2709, 15, 45, '2017-11-14 00:00:00', '2017-12-20 00:00:00', 3, 1, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2661, 100, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, 5, true, NULL, '[2599]', '--');
INSERT INTO batch VALUES (2678, 1000, 100, '2017-11-15 00:00:00', '2017-11-29 00:00:00', 752, 1, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2688, 60, 165.5, '2017-11-14 00:00:00', '2017-11-29 00:00:00', 23, 5, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2708, 3, 12, '2017-11-14 00:00:00', '2017-12-20 00:00:00', 23, 1, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2603, 10, 150, '2017-11-16 00:00:00', '2017-11-22 00:00:00', 752, 5, true, NULL, '--', '-1');
INSERT INTO batch VALUES (2599, 3000, 100, '2017-11-02 00:00:00', '2017-11-30 00:00:00', 752, -1, true, NULL, '--', '-1');


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

INSERT INTO request_order VALUES (2718, '2017-11-22 00:00:00', '2017-11-30 00:00:00', 816, 538.55999755859375, 'EN PROGRESO', 1564, 1, 7, 'aV. HEY');
INSERT INTO request_order VALUES (2713, '2017-11-15 00:00:00', '2017-11-23 00:00:00', 816, 481.44000244140625, 'ENTREGADO', 57, 2, 28, 'Pabellon V');
INSERT INTO request_order VALUES (2724, '2017-11-15 00:00:00', '2017-11-30 00:00:00', 112, 68.3199996948242188, 'EN PROGRESO', 1, 1, 3, '1-9yy09qfwh[0qfj');


--
-- Data for Name: complaint; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Name: disclaim_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('disclaim_seq', 1, false);


--
-- Data for Name: dispatch_move; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_move VALUES (65, 2, 57, '2017-11-04 02:15:21.101', 1, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (60, 2, 1, '2017-11-02 04:46:48.638', 2, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (74, 1, 3, '2017-11-05 20:23:10.176', 2, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (69, 0, 0, '2017-11-04 04:59:24.382011', 1, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (75, 1, 4, '2017-11-05 20:31:09.558', 2, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (92, 2, 1, '2017-11-06 02:53:34.06', 1, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (76, 1, 25, '2017-11-05 20:40:34.34', 0, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (91, 2, 1, '2017-11-06 02:49:59.286', 1, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (84, 2, 1, '2017-11-06 02:34:04.764', 1, 14, '2017-11-04 04:59:23.021');
INSERT INTO dispatch_move VALUES (641, 3, 0, '2017-11-07 16:28:55.052', 3, 640, '2017-12-07 00:00:00');
INSERT INTO dispatch_move VALUES (642, 3, 0, '2017-11-07 16:28:55.052', 3, 640, '2017-12-21 00:00:00');
INSERT INTO dispatch_move VALUES (645, 3, 0, '2017-11-07 16:45:03.337', 3, 644, '2017-12-28 00:00:00');
INSERT INTO dispatch_move VALUES (646, 3, 0, '2017-11-07 16:45:03.337', 3, 644, '2017-12-27 00:00:00');
INSERT INTO dispatch_move VALUES (674, 3, 0, '2017-11-07 20:07:52.57', 3, 673, '2017-12-07 00:00:00');
INSERT INTO dispatch_move VALUES (675, 1, 1, '2017-11-07 20:09:08.627', 2, 673, '2017-11-07 20:09:08.627');
INSERT INTO dispatch_move VALUES (719, 4, 1, '2017-11-08 19:34:54.962', 4, 718, '2017-12-08 00:00:00');
INSERT INTO dispatch_move VALUES (727, 3, 0, '2017-11-09 18:38:30.429', 3, 726, '2017-12-09 00:00:00');
INSERT INTO dispatch_move VALUES (731, 1, 1, '2017-11-09 19:21:57.461', 0, 644, '2017-11-09 19:21:57.461');
INSERT INTO dispatch_move VALUES (732, 1, 1, '2017-11-09 19:41:30.3', 2, 726, '2017-11-09 19:41:30.3');
INSERT INTO dispatch_move VALUES (733, 1, 1, '2017-11-09 19:55:59.583', 2, 726, '2017-11-09 19:55:59.583');
INSERT INTO dispatch_move VALUES (734, 1, 1, '2017-11-09 20:08:52.573', 2, 726, '2017-11-09 20:08:52.573');
INSERT INTO dispatch_move VALUES (735, 1, 1, '2017-11-09 20:22:05.935', 2, 726, '2017-11-09 20:22:05.935');
INSERT INTO dispatch_move VALUES (736, 2, 1, '2017-11-09 23:39:03.605', 1, 640, '2017-11-09 23:39:03.605');
INSERT INTO dispatch_move VALUES (737, 2, 1, '2017-11-09 23:39:11.232', 1, 639, '2017-11-09 23:39:11.232');
INSERT INTO dispatch_move VALUES (738, 2, 1, '2017-11-09 23:39:18.482', 1, 638, '2017-11-09 23:39:18.482');
INSERT INTO dispatch_move VALUES (769, 1, 1, '2017-11-11 16:01:00.692', 2, 673, '2017-11-11 16:01:00.692');
INSERT INTO dispatch_move VALUES (775, 1, 1, '2017-11-12 04:27:54.996', 2, 644, '2017-11-12 04:27:54.996');
INSERT INTO dispatch_move VALUES (776, 1, 1, '2017-11-12 04:28:11.979', 2, 643, '2017-11-12 04:28:11.979');
INSERT INTO dispatch_move VALUES (777, 1, 1, '2017-11-12 04:28:30.179', 2, 605, '2017-11-12 04:28:30.179');
INSERT INTO dispatch_move VALUES (1560, 3, 0, '2017-11-12 19:47:50.577', 3, 1559, '2017-12-12 00:00:00');
INSERT INTO dispatch_move VALUES (1561, 3, 0, '2017-11-12 19:47:50.577', 3, 1559, '2017-12-12 00:00:00');
INSERT INTO dispatch_move VALUES (1562, 1, 1, '2017-11-12 19:50:01.149', 2, 1558, '2017-11-12 19:50:01.149');
INSERT INTO dispatch_move VALUES (1563, 1, 1, '2017-11-12 19:50:07.472', 2, 1559, '2017-11-12 19:50:07.472');
INSERT INTO dispatch_move VALUES (1578, 3, 0, '2017-11-12 21:30:19.632', 3, 1577, '2017-12-12 00:00:00');
INSERT INTO dispatch_move VALUES (1579, 1, 1, '2017-11-12 21:31:01.435', 2, 1577, '2017-11-12 21:31:01.435');
INSERT INTO dispatch_move VALUES (1665, 4, 1, '2017-11-13 15:50:19.844', 4, 1664, '2017-11-13 00:00:00');
INSERT INTO dispatch_move VALUES (1666, 4, 1, '2017-11-13 15:50:19.844', 4, 1664, '2017-11-13 00:00:00');
INSERT INTO dispatch_move VALUES (1667, 1, 1, '2017-11-13 15:51:37.747', 2, 1663, '2017-11-13 15:51:37.747');
INSERT INTO dispatch_move VALUES (1668, 1, 1, '2017-11-13 15:51:49.589', 2, 1664, '2017-11-13 15:51:49.589');
INSERT INTO dispatch_move VALUES (1675, 4, 1, '2017-11-13 16:08:09.679', 4, 1674, '2017-11-01 00:00:00');
INSERT INTO dispatch_move VALUES (1676, 4, 1, '2017-11-13 16:08:09.679', 4, 1674, '2017-11-06 00:00:00');
INSERT INTO dispatch_move VALUES (1677, 4, 1, '2017-11-13 16:08:09.679', 4, 1674, '2017-11-01 00:00:00');
INSERT INTO dispatch_move VALUES (1678, 4, 1, '2017-11-13 16:08:09.679', 4, 1674, '2017-11-08 00:00:00');
INSERT INTO dispatch_move VALUES (1679, 1, 1, '2017-11-13 16:10:28.765', 2, 1671, '2017-11-13 16:10:28.765');
INSERT INTO dispatch_move VALUES (1680, 1, 1, '2017-11-13 16:10:50.299', 2, 1672, '2017-11-13 16:10:50.299');
INSERT INTO dispatch_move VALUES (1681, 1, 1, '2017-11-13 16:11:04.698', 2, 1673, '2017-11-13 16:11:04.698');
INSERT INTO dispatch_move VALUES (1682, 1, 1, '2017-11-13 16:11:24.488', 2, 1674, '2017-11-13 16:11:24.488');
INSERT INTO dispatch_move VALUES (1903, 3, 0, '2017-11-13 22:53:41.863', 3, 1902, '2017-11-01 00:00:00');
INSERT INTO dispatch_move VALUES (1904, 1, 1, '2017-11-13 22:56:47.08', 2, 1902, '2017-11-13 22:56:47.08');
INSERT INTO dispatch_move VALUES (1905, 1, 1, '2017-11-13 22:56:58.606', 2, 1895, '2017-11-13 22:56:58.606');
INSERT INTO dispatch_move VALUES (1906, 1, 1, '2017-11-13 22:57:09.079', 2, 1671, '2017-11-13 22:57:09.079');
INSERT INTO dispatch_move VALUES (1966, 2, 1, '2017-11-13 23:10:41.994', 1, 1663, '2017-11-13 23:10:41.994');
INSERT INTO dispatch_move VALUES (1968, 3, 0, '2017-11-14 09:20:36.485', 3, 1967, '2017-11-14 00:00:00');
INSERT INTO dispatch_move VALUES (1971, 1, 1, '2017-11-14 09:34:45.092', 2, 1967, '2017-11-14 09:34:45.092');
INSERT INTO dispatch_move VALUES (2465, 3, 0, '2017-11-14 11:07:52.886', 3, 2458, '2017-11-01 00:00:00');
INSERT INTO dispatch_move VALUES (2466, 3, 0, '2017-11-14 11:07:52.886', 3, 2458, '2017-11-08 00:00:00');
INSERT INTO dispatch_move VALUES (2467, 1, 1, '2017-11-14 11:09:31.278', 2, 2457, '2017-11-14 11:09:31.278');
INSERT INTO dispatch_move VALUES (2591, 3, 0, '2017-11-14 12:32:47.822', 3, 2590, '2017-11-14 00:00:00');
INSERT INTO dispatch_move VALUES (2594, 3, 0, '2017-11-14 12:39:30.071', 3, 2593, '2017-11-14 00:00:00');
INSERT INTO dispatch_move VALUES (2595, 2, 2, '2017-11-14 12:42:41.226', 1, 2593, '2017-11-14 12:42:41.226');
INSERT INTO dispatch_move VALUES (2600, 3, 0, '2017-11-14 14:40:35.877', 3, 2599, '2017-11-02 00:00:00');
INSERT INTO dispatch_move VALUES (2604, 3, 0, '2017-11-14 14:52:38.283', 3, 2603, '2017-11-16 00:00:00');
INSERT INTO dispatch_move VALUES (2605, 1, 1, '2017-11-14 14:53:25.981', 2, 2599, '2017-11-14 14:53:25.981');
INSERT INTO dispatch_move VALUES (2606, 1, 1, '2017-11-14 14:53:28.241', 2, 2603, '2017-11-14 14:53:28.241');
INSERT INTO dispatch_move VALUES (2679, 3, 0, '2017-11-14 15:01:00.311', 3, 2678, '2017-11-16 00:00:00');
INSERT INTO dispatch_move VALUES (2680, 3, 0, '2017-11-14 15:01:00.311', 3, 2678, '2017-11-15 00:00:00');
INSERT INTO dispatch_move VALUES (2682, 4, 1, '2017-11-14 15:01:40.981', 4, 2681, '2017-11-16 00:00:00');
INSERT INTO dispatch_move VALUES (2683, 4, 1, '2017-11-14 15:01:40.981', 4, 2681, '2017-11-15 00:00:00');
INSERT INTO dispatch_move VALUES (2684, 4, 1, '2017-11-14 15:01:40.981', 4, 2681, '2017-11-15 00:00:00');
INSERT INTO dispatch_move VALUES (2685, 1, 1, '2017-11-14 15:02:46.819', 2, 2681, '2017-11-14 15:02:46.819');
INSERT INTO dispatch_move VALUES (2686, 1, 1, '2017-11-14 15:02:48.396', 2, 2647, '2017-11-14 15:02:48.396');
INSERT INTO dispatch_move VALUES (2687, 1, 1, '2017-11-14 15:02:50.58', 2, 2651, '2017-11-14 15:02:50.58');
INSERT INTO dispatch_move VALUES (2689, 3, 0, '2017-11-14 15:05:17.368', 3, 2688, '2017-11-14 00:00:00');
INSERT INTO dispatch_move VALUES (2690, 2, 2, '2017-11-14 15:25:38.56', 1, 2649, '2017-11-14 15:25:38.56');
INSERT INTO dispatch_move VALUES (2691, 2, 2, '2017-11-14 15:32:26.785', 1, 2653, '2017-11-14 15:32:26.785');
INSERT INTO dispatch_move VALUES (2692, 2, 2, '2017-11-14 15:34:56.308', 1, 2655, '2017-11-14 15:34:56.308');
INSERT INTO dispatch_move VALUES (2697, 2, 2, '2017-11-14 15:38:10.948', 1, 2657, '2017-11-14 15:38:10.948');
INSERT INTO dispatch_move VALUES (2700, 2, 2, '2017-11-14 15:45:28.174', 1, 2659, '2017-11-14 15:45:28.174');
INSERT INTO dispatch_move VALUES (2704, 2, 2, '2017-11-14 16:03:50.384', 1, 2688, '2017-11-14 16:03:50.384');
INSERT INTO dispatch_move VALUES (2706, 3, 0, '2017-11-14 16:41:56.614', 3, 2705, '2017-11-01 00:00:00');
INSERT INTO dispatch_move VALUES (2707, 1, 1, '2017-11-14 16:42:16.14', 2, 2705, '2017-11-14 16:42:16.14');
INSERT INTO dispatch_move VALUES (2710, 3, 0, '2017-11-14 16:50:48.337', 3, 2709, '2017-11-14 00:00:00');
INSERT INTO dispatch_move VALUES (2711, 3, 0, '2017-11-14 16:50:48.337', 3, 2709, '2017-11-14 00:00:00');
INSERT INTO dispatch_move VALUES (2712, 2, 748, '2017-11-14 16:51:41.076', 1, 2603, '2017-11-14 16:51:41.076');
INSERT INTO dispatch_move VALUES (2717, 2, 57, '2017-11-14 17:04:37.951', 1, 2661, '2017-11-14 17:04:37.951');
INSERT INTO dispatch_move VALUES (2722, 2, 1564, '2017-11-14 17:12:18.429', 1, 2663, '2017-11-14 17:12:18.429');


--
-- Data for Name: dispatch_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_order VALUES (2715, 2713, 2, 'ENTREGADO', NULL, NULL);
INSERT INTO dispatch_order VALUES (2720, 2718, 1, 'EN PROGRESO', NULL, NULL);
INSERT INTO dispatch_order VALUES (2726, 2724, 1, 'EN PROGRESO', NULL, NULL);


--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_id_dispatch_order_seq', 1, false);


--
-- Data for Name: dispatch_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_order_line VALUES (2716, 2715, 752, 102, false);
INSERT INTO dispatch_order_line VALUES (2721, 2720, 752, 2, false);
INSERT INTO dispatch_order_line VALUES (2727, 2726, 752, 14, false);


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

SELECT pg_catalog.setval('hibernate_sequence', 2727, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO role VALUES (1593, 'Admin');
INSERT INTO role VALUES (2560, 'Jefe de Almacén');
INSERT INTO role VALUES (666, 'Bulki');


--
-- Data for Name: users; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO users VALUES (21, 'Miguel', 'Guanira', '123456', 'guani@ra.com', '2017-10-27 23:53:16.454', true, 1593, '2017-10-27 23:53:16.454', 'mguani');
INSERT INTO users VALUES (2586, 'Marco Antonio', 'Lopez Vardales', '147852', 'mlopez@gmail.com', '2017-11-14 12:21:45.139', true, 2560, '2017-11-14 12:21:45.139', 'mlopez');
INSERT INTO users VALUES (666, 'Carga', 'Masiva', 'bulki', 'bul@lol.com', '2017-10-27 23:53:16.454', true, 1593, '2017-10-27 23:53:16.454', 'bulki');


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO vehicle VALUES (33, 1230, 100, true, 1, 'AAA-123');
INSERT INTO vehicle VALUES (2, 2500, 54, true, 1, '123-ASD');
INSERT INTO vehicle VALUES (838, 3200, 150, true, 1, 'CQK-147');
INSERT INTO vehicle VALUES (1651, 4500, 50, true, 1, 'GUD-BOY');


--
-- Data for Name: movement; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO movement VALUES (2608, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2189, 2607);
INSERT INTO movement VALUES (2610, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2188, 2609);
INSERT INTO movement VALUES (2612, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2191, 2611);
INSERT INTO movement VALUES (2614, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2190, 2613);
INSERT INTO movement VALUES (2616, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2193, 2615);
INSERT INTO movement VALUES (2618, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2192, 2617);
INSERT INTO movement VALUES (2620, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2195, 2619);
INSERT INTO movement VALUES (2622, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2194, 2621);
INSERT INTO movement VALUES (2624, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2197, 2623);
INSERT INTO movement VALUES (2626, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2196, 2625);
INSERT INTO movement VALUES (2628, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2199, 2627);
INSERT INTO movement VALUES (2630, '2017-11-14 14:57:38.743', 21, 100, 33, 1, 1, 2198, 2629);
INSERT INTO movement VALUES (2632, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2201, 2631);
INSERT INTO movement VALUES (2634, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2200, 2633);
INSERT INTO movement VALUES (2636, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2203, 2635);
INSERT INTO movement VALUES (2638, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2202, 2637);
INSERT INTO movement VALUES (2640, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2204, 2639);
INSERT INTO movement VALUES (2642, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2154, 2641);
INSERT INTO movement VALUES (2644, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2156, 2643);
INSERT INTO movement VALUES (2646, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2155, 2645);
INSERT INTO movement VALUES (2648, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2158, 2647);
INSERT INTO movement VALUES (2650, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2157, 2649);
INSERT INTO movement VALUES (2652, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2160, 2651);
INSERT INTO movement VALUES (2654, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2159, 2653);
INSERT INTO movement VALUES (2656, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2162, 2655);
INSERT INTO movement VALUES (2658, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2161, 2657);
INSERT INTO movement VALUES (2660, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2164, 2659);
INSERT INTO movement VALUES (2662, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2163, 2661);
INSERT INTO movement VALUES (2664, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2166, 2663);
INSERT INTO movement VALUES (2666, '2017-11-14 14:57:38.743', 21, 100, 2, 1, 1, 2165, 2665);
INSERT INTO movement VALUES (2667, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2158, 2647);
INSERT INTO movement VALUES (2668, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2157, 2649);
INSERT INTO movement VALUES (2669, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2160, 2651);
INSERT INTO movement VALUES (2670, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2159, 2653);
INSERT INTO movement VALUES (2671, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2162, 2655);
INSERT INTO movement VALUES (2672, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2161, 2657);
INSERT INTO movement VALUES (2673, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2164, 2659);
INSERT INTO movement VALUES (2674, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2163, 2661);
INSERT INTO movement VALUES (2675, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2166, 2663);
INSERT INTO movement VALUES (2676, '2017-11-14 14:58:31.06', 21, 100, 33, 3, 1, 2165, 2665);
INSERT INTO movement VALUES (2677, '2017-11-14 15:00:05.239', 21, 10, 1651, 1, 1, 2186, 2603);


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

INSERT INTO permission VALUES (1, 1593, true, true, 1594);
INSERT INTO permission VALUES (2, 1593, true, true, 1595);
INSERT INTO permission VALUES (3, 1593, true, true, 1596);
INSERT INTO permission VALUES (1, 2560, false, false, 2561);
INSERT INTO permission VALUES (2, 2560, false, false, 2562);
INSERT INTO permission VALUES (3, 2560, false, false, 2563);
INSERT INTO permission VALUES (4, 2560, false, false, 2564);
INSERT INTO permission VALUES (5, 2560, false, false, 2565);
INSERT INTO permission VALUES (6, 2560, false, false, 2566);
INSERT INTO permission VALUES (7, 2560, false, false, 2567);
INSERT INTO permission VALUES (8, 2560, false, false, 2568);
INSERT INTO permission VALUES (9, 2560, false, false, 2569);
INSERT INTO permission VALUES (10, 2560, false, false, 2570);
INSERT INTO permission VALUES (11, 2560, false, false, 2571);
INSERT INTO permission VALUES (12, 2560, false, false, 2572);
INSERT INTO permission VALUES (13, 2560, false, false, 2573);
INSERT INTO permission VALUES (14, 2560, false, false, 2574);
INSERT INTO permission VALUES (15, 2560, false, false, 2575);
INSERT INTO permission VALUES (16, 2560, false, false, 2576);
INSERT INTO permission VALUES (17, 2560, true, true, 2577);
INSERT INTO permission VALUES (18, 2560, true, true, 2578);
INSERT INTO permission VALUES (19, 2560, false, false, 2579);
INSERT INTO permission VALUES (20, 2560, false, false, 2580);
INSERT INTO permission VALUES (21, 2560, false, false, 2581);
INSERT INTO permission VALUES (22, 2560, false, false, 2582);
INSERT INTO permission VALUES (23, 2560, false, false, 2583);
INSERT INTO permission VALUES (24, 2560, false, false, 2584);
INSERT INTO permission VALUES (4, 1593, true, true, 1597);
INSERT INTO permission VALUES (5, 1593, true, true, 1598);
INSERT INTO permission VALUES (6, 1593, true, true, 1599);
INSERT INTO permission VALUES (7, 1593, true, true, 1600);
INSERT INTO permission VALUES (8, 1593, true, true, 1601);
INSERT INTO permission VALUES (9, 1593, true, true, 1602);
INSERT INTO permission VALUES (10, 1593, true, true, 1603);
INSERT INTO permission VALUES (11, 1593, true, true, 1604);
INSERT INTO permission VALUES (12, 1593, true, true, 1605);
INSERT INTO permission VALUES (13, 1593, true, true, 1606);
INSERT INTO permission VALUES (14, 1593, true, true, 1607);
INSERT INTO permission VALUES (15, 1593, true, true, 1608);
INSERT INTO permission VALUES (16, 1593, true, true, 1609);
INSERT INTO permission VALUES (17, 1593, true, true, 1610);
INSERT INTO permission VALUES (18, 1593, true, true, 1611);
INSERT INTO permission VALUES (19, 1593, true, true, 1612);
INSERT INTO permission VALUES (20, 1593, true, true, 1613);
INSERT INTO permission VALUES (21, 1593, true, true, 1614);
INSERT INTO permission VALUES (22, 1593, true, true, 1615);
INSERT INTO permission VALUES (23, 1593, true, true, 1616);
INSERT INTO permission VALUES (24, 1593, true, true, 1617);


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

INSERT INTO productxwarehouse VALUES (23, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (30, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (32, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (739, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (30, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (31, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (1592, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (3, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (1592, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (32, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (31, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (739, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (3, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (23, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (752, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (752, 1, 2010, 2780);


--
-- Data for Name: rack; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO rack VALUES (2153, 1, 70, 85, 10, 1, 0);
INSERT INTO rack VALUES (2184, 1, 70, 70, 10, 1, 0);
INSERT INTO rack VALUES (2205, 1, 70, 10, 10, 1, 0);
INSERT INTO rack VALUES (2226, 1, 70, 15, 10, 1, 0);
INSERT INTO rack VALUES (2247, 1, 70, 30, 10, 1, 0);
INSERT INTO rack VALUES (2268, 1, 70, 45, 10, 1, 0);
INSERT INTO rack VALUES (2289, 1, 70, 60, 10, 1, 0);
INSERT INTO rack VALUES (2373, 1, 55, 10, 10, 1, 0);
INSERT INTO rack VALUES (2394, 1, 55, 1, 10, 1, 0);
INSERT INTO rack VALUES (2415, 1, 55, 15, 10, 1, 0);
INSERT INTO rack VALUES (2436, 1, 55, 20, 10, 1, 0);
INSERT INTO rack VALUES (5, 1, 14, 5, 10, 5, 0);
INSERT INTO rack VALUES (6, 1, 26, 5, 10, 5, 0);
INSERT INTO rack VALUES (7, 1, 2, 8, 10, 5, 0);
INSERT INTO rack VALUES (8, 1, 14, 8, 10, 5, 0);
INSERT INTO rack VALUES (408, 1, 2, 2, 10, 5, 0);
INSERT INTO rack VALUES (1793, 1, 40, 40, 10, 5, 0);
INSERT INTO rack VALUES (839, 1, 25, 5, 10, 5, 0);


--
-- Name: rack_id_rack_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('rack_id_rack_seq', 9, true);


--
-- Data for Name: refund; Type: TABLE DATA; Schema: campis; Owner: postgres
--



--
-- Data for Name: refund_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--



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

INSERT INTO request_order_line VALUES (2714, 102, 8, 2713, 752);
INSERT INTO request_order_line VALUES (2719, 102, 8, 2718, 752);
INSERT INTO request_order_line VALUES (2725, 14, 8, 2724, 752);


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

INSERT INTO sale_condition VALUES (2598, '2017-11-01 00:00:00', '2017-11-30 00:00:00', 0, '1', 0, 752, 0, 2, 1);
INSERT INTO sale_condition VALUES (2703, '2017-12-05 00:00:00', '2017-12-14 00:00:00', 0, '1', 0, 30, 0, 3, 2);


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

INSERT INTO zone VALUES (409, 1, 408, 2, 2, 0, false);
INSERT INTO zone VALUES (869, 1, 839, 27, 16, 4, true);
INSERT INTO zone VALUES (870, 1, 839, 28, 15, 0, true);
INSERT INTO zone VALUES (871, 1, 839, 28, 16, 0, true);
INSERT INTO zone VALUES (872, 1, 839, 28, 15, 1, true);
INSERT INTO zone VALUES (873, 1, 839, 28, 16, 1, true);
INSERT INTO zone VALUES (874, 1, 839, 28, 15, 2, true);
INSERT INTO zone VALUES (875, 1, 839, 28, 16, 2, true);
INSERT INTO zone VALUES (876, 1, 839, 28, 15, 3, true);
INSERT INTO zone VALUES (877, 1, 839, 28, 16, 3, true);
INSERT INTO zone VALUES (878, 1, 839, 28, 15, 4, true);
INSERT INTO zone VALUES (879, 1, 839, 28, 16, 4, true);
INSERT INTO zone VALUES (880, 1, 839, 29, 15, 0, true);
INSERT INTO zone VALUES (881, 1, 839, 29, 16, 0, true);
INSERT INTO zone VALUES (882, 1, 839, 29, 15, 1, true);
INSERT INTO zone VALUES (883, 1, 839, 29, 16, 1, true);
INSERT INTO zone VALUES (884, 1, 839, 29, 15, 2, true);
INSERT INTO zone VALUES (885, 1, 839, 29, 16, 2, true);
INSERT INTO zone VALUES (886, 1, 839, 29, 15, 3, true);
INSERT INTO zone VALUES (887, 1, 839, 29, 16, 3, true);
INSERT INTO zone VALUES (888, 1, 839, 29, 15, 4, true);
INSERT INTO zone VALUES (889, 1, 839, 29, 16, 4, true);
INSERT INTO zone VALUES (890, 1, 839, 30, 15, 0, true);
INSERT INTO zone VALUES (891, 1, 839, 30, 16, 0, true);
INSERT INTO zone VALUES (892, 1, 839, 30, 15, 1, true);
INSERT INTO zone VALUES (893, 1, 839, 30, 16, 1, true);
INSERT INTO zone VALUES (894, 1, 839, 30, 15, 2, true);
INSERT INTO zone VALUES (895, 1, 839, 30, 16, 2, true);
INSERT INTO zone VALUES (896, 1, 839, 30, 15, 3, true);
INSERT INTO zone VALUES (897, 1, 839, 30, 16, 3, true);
INSERT INTO zone VALUES (898, 1, 839, 30, 15, 4, true);
INSERT INTO zone VALUES (899, 1, 839, 30, 16, 4, true);
INSERT INTO zone VALUES (900, 1, 839, 31, 15, 0, true);
INSERT INTO zone VALUES (901, 1, 839, 31, 16, 0, true);
INSERT INTO zone VALUES (902, 1, 839, 31, 15, 1, true);
INSERT INTO zone VALUES (903, 1, 839, 31, 16, 1, true);
INSERT INTO zone VALUES (904, 1, 839, 31, 15, 2, true);
INSERT INTO zone VALUES (905, 1, 839, 31, 16, 2, true);
INSERT INTO zone VALUES (906, 1, 839, 31, 15, 3, true);
INSERT INTO zone VALUES (907, 1, 839, 31, 16, 3, true);
INSERT INTO zone VALUES (908, 1, 839, 31, 15, 4, true);
INSERT INTO zone VALUES (909, 1, 839, 31, 16, 4, true);
INSERT INTO zone VALUES (910, 1, 839, 32, 15, 0, true);
INSERT INTO zone VALUES (911, 1, 839, 32, 16, 0, true);
INSERT INTO zone VALUES (912, 1, 839, 32, 15, 1, true);
INSERT INTO zone VALUES (913, 1, 839, 32, 16, 1, true);
INSERT INTO zone VALUES (914, 1, 839, 32, 15, 2, true);
INSERT INTO zone VALUES (915, 1, 839, 32, 16, 2, true);
INSERT INTO zone VALUES (916, 1, 839, 32, 15, 3, true);
INSERT INTO zone VALUES (917, 1, 839, 32, 16, 3, true);
INSERT INTO zone VALUES (918, 1, 839, 32, 15, 4, true);
INSERT INTO zone VALUES (919, 1, 839, 32, 16, 4, true);
INSERT INTO zone VALUES (920, 1, 839, 33, 15, 0, true);
INSERT INTO zone VALUES (921, 1, 839, 33, 16, 0, true);
INSERT INTO zone VALUES (922, 1, 839, 33, 15, 1, true);
INSERT INTO zone VALUES (923, 1, 839, 33, 16, 1, true);
INSERT INTO zone VALUES (924, 1, 839, 33, 15, 2, true);
INSERT INTO zone VALUES (925, 1, 839, 33, 16, 2, true);
INSERT INTO zone VALUES (926, 1, 839, 33, 15, 3, true);
INSERT INTO zone VALUES (927, 1, 839, 33, 16, 3, true);
INSERT INTO zone VALUES (489, 1, 408, 10, 2, 0, true);
INSERT INTO zone VALUES (490, 1, 408, 10, 3, 0, true);
INSERT INTO zone VALUES (491, 1, 408, 10, 2, 1, true);
INSERT INTO zone VALUES (492, 1, 408, 10, 3, 1, true);
INSERT INTO zone VALUES (493, 1, 408, 10, 2, 2, true);
INSERT INTO zone VALUES (494, 1, 408, 10, 3, 2, true);
INSERT INTO zone VALUES (495, 1, 408, 10, 2, 3, true);
INSERT INTO zone VALUES (496, 1, 408, 10, 3, 3, true);
INSERT INTO zone VALUES (497, 1, 408, 10, 2, 4, true);
INSERT INTO zone VALUES (498, 1, 408, 10, 3, 4, true);
INSERT INTO zone VALUES (499, 1, 408, 11, 2, 0, true);
INSERT INTO zone VALUES (500, 1, 408, 11, 3, 0, true);
INSERT INTO zone VALUES (501, 1, 408, 11, 2, 1, true);
INSERT INTO zone VALUES (502, 1, 408, 11, 3, 1, true);
INSERT INTO zone VALUES (503, 1, 408, 11, 2, 2, true);
INSERT INTO zone VALUES (504, 1, 408, 11, 3, 2, true);
INSERT INTO zone VALUES (505, 1, 408, 11, 2, 3, true);
INSERT INTO zone VALUES (506, 1, 408, 11, 3, 3, true);
INSERT INTO zone VALUES (507, 1, 408, 11, 2, 4, true);
INSERT INTO zone VALUES (508, 1, 408, 11, 3, 4, true);
INSERT INTO zone VALUES (487, 1, 408, 9, 2, 4, false);
INSERT INTO zone VALUES (411, 1, 408, 2, 2, 1, false);
INSERT INTO zone VALUES (445, 1, 408, 5, 2, 3, false);
INSERT INTO zone VALUES (447, 1, 408, 5, 2, 4, false);
INSERT INTO zone VALUES (413, 1, 408, 2, 2, 2, false);
INSERT INTO zone VALUES (415, 1, 408, 2, 2, 3, false);
INSERT INTO zone VALUES (417, 1, 408, 2, 2, 4, false);
INSERT INTO zone VALUES (840, 1, 839, 25, 15, 0, true);
INSERT INTO zone VALUES (841, 1, 839, 25, 16, 0, true);
INSERT INTO zone VALUES (842, 1, 839, 25, 15, 1, true);
INSERT INTO zone VALUES (843, 1, 839, 25, 16, 1, true);
INSERT INTO zone VALUES (844, 1, 839, 25, 15, 2, true);
INSERT INTO zone VALUES (845, 1, 839, 25, 16, 2, true);
INSERT INTO zone VALUES (846, 1, 839, 25, 15, 3, true);
INSERT INTO zone VALUES (847, 1, 839, 25, 16, 3, true);
INSERT INTO zone VALUES (848, 1, 839, 25, 15, 4, true);
INSERT INTO zone VALUES (849, 1, 839, 25, 16, 4, true);
INSERT INTO zone VALUES (850, 1, 839, 26, 15, 0, true);
INSERT INTO zone VALUES (851, 1, 839, 26, 16, 0, true);
INSERT INTO zone VALUES (852, 1, 839, 26, 15, 1, true);
INSERT INTO zone VALUES (853, 1, 839, 26, 16, 1, true);
INSERT INTO zone VALUES (854, 1, 839, 26, 15, 2, true);
INSERT INTO zone VALUES (855, 1, 839, 26, 16, 2, true);
INSERT INTO zone VALUES (856, 1, 839, 26, 15, 3, true);
INSERT INTO zone VALUES (857, 1, 839, 26, 16, 3, true);
INSERT INTO zone VALUES (858, 1, 839, 26, 15, 4, true);
INSERT INTO zone VALUES (859, 1, 839, 26, 16, 4, true);
INSERT INTO zone VALUES (860, 1, 839, 27, 15, 0, true);
INSERT INTO zone VALUES (861, 1, 839, 27, 16, 0, true);
INSERT INTO zone VALUES (862, 1, 839, 27, 15, 1, true);
INSERT INTO zone VALUES (863, 1, 839, 27, 16, 1, true);
INSERT INTO zone VALUES (864, 1, 839, 27, 15, 2, true);
INSERT INTO zone VALUES (865, 1, 839, 27, 16, 2, true);
INSERT INTO zone VALUES (866, 1, 839, 27, 15, 3, true);
INSERT INTO zone VALUES (867, 1, 839, 27, 16, 3, true);
INSERT INTO zone VALUES (868, 1, 839, 27, 15, 4, true);
INSERT INTO zone VALUES (928, 1, 839, 33, 15, 4, true);
INSERT INTO zone VALUES (929, 1, 839, 33, 16, 4, true);
INSERT INTO zone VALUES (930, 1, 839, 34, 15, 0, true);
INSERT INTO zone VALUES (932, 1, 839, 34, 15, 1, true);
INSERT INTO zone VALUES (934, 1, 839, 34, 15, 2, true);
INSERT INTO zone VALUES (936, 1, 839, 34, 15, 3, true);
INSERT INTO zone VALUES (938, 1, 839, 34, 15, 4, true);
INSERT INTO zone VALUES (939, 1, 839, 34, 16, 4, true);
INSERT INTO zone VALUES (937, 1, 839, 34, 16, 3, true);
INSERT INTO zone VALUES (935, 1, 839, 34, 16, 2, true);
INSERT INTO zone VALUES (933, 1, 839, 34, 16, 1, true);
INSERT INTO zone VALUES (931, 1, 839, 34, 16, 0, true);
INSERT INTO zone VALUES (482, 1, 408, 9, 3, 1, false);
INSERT INTO zone VALUES (477, 1, 408, 8, 2, 4, false);
INSERT INTO zone VALUES (470, 1, 408, 8, 3, 0, false);
INSERT INTO zone VALUES (472, 1, 408, 8, 3, 1, false);
INSERT INTO zone VALUES (474, 1, 408, 8, 3, 2, false);
INSERT INTO zone VALUES (480, 1, 408, 9, 3, 0, false);
INSERT INTO zone VALUES (438, 1, 408, 4, 3, 4, false);
INSERT INTO zone VALUES (2162, 1, 2153, 74, 85, 0, false);
INSERT INTO zone VALUES (2161, 1, 2153, 73, 86, 0, false);
INSERT INTO zone VALUES (2164, 1, 2153, 75, 85, 0, false);
INSERT INTO zone VALUES (2163, 1, 2153, 74, 86, 0, false);
INSERT INTO zone VALUES (2166, 1, 2153, 76, 85, 0, false);
INSERT INTO zone VALUES (2165, 1, 2153, 75, 86, 0, false);
INSERT INTO zone VALUES (455, 1, 408, 6, 2, 3, false);
INSERT INTO zone VALUES (457, 1, 408, 6, 2, 4, false);
INSERT INTO zone VALUES (450, 1, 408, 6, 3, 0, false);
INSERT INTO zone VALUES (452, 1, 408, 6, 3, 1, false);
INSERT INTO zone VALUES (454, 1, 408, 6, 3, 2, false);
INSERT INTO zone VALUES (440, 1, 408, 5, 3, 0, false);
INSERT INTO zone VALUES (442, 1, 408, 5, 3, 1, false);
INSERT INTO zone VALUES (444, 1, 408, 5, 3, 2, false);
INSERT INTO zone VALUES (446, 1, 408, 5, 3, 3, false);
INSERT INTO zone VALUES (448, 1, 408, 5, 3, 4, false);
INSERT INTO zone VALUES (467, 1, 408, 7, 2, 4, false);
INSERT INTO zone VALUES (465, 1, 408, 7, 2, 3, false);
INSERT INTO zone VALUES (460, 1, 408, 7, 3, 0, false);
INSERT INTO zone VALUES (462, 1, 408, 7, 3, 1, false);
INSERT INTO zone VALUES (464, 1, 408, 7, 3, 2, false);
INSERT INTO zone VALUES (466, 1, 408, 7, 3, 3, false);
INSERT INTO zone VALUES (475, 1, 408, 8, 2, 3, false);
INSERT INTO zone VALUES (478, 1, 408, 8, 3, 4, false);
INSERT INTO zone VALUES (468, 1, 408, 7, 3, 4, false);
INSERT INTO zone VALUES (469, 1, 408, 8, 2, 0, false);
INSERT INTO zone VALUES (471, 1, 408, 8, 2, 1, false);
INSERT INTO zone VALUES (473, 1, 408, 8, 2, 2, false);
INSERT INTO zone VALUES (456, 1, 408, 6, 3, 3, false);
INSERT INTO zone VALUES (458, 1, 408, 6, 3, 4, false);
INSERT INTO zone VALUES (459, 1, 408, 7, 2, 0, false);
INSERT INTO zone VALUES (461, 1, 408, 7, 2, 1, false);
INSERT INTO zone VALUES (463, 1, 408, 7, 2, 2, false);
INSERT INTO zone VALUES (449, 1, 408, 6, 2, 0, false);
INSERT INTO zone VALUES (451, 1, 408, 6, 2, 1, false);
INSERT INTO zone VALUES (453, 1, 408, 6, 2, 2, false);
INSERT INTO zone VALUES (2189, 1, 2184, 72, 70, 0, false);
INSERT INTO zone VALUES (2188, 1, 2184, 71, 71, 0, false);
INSERT INTO zone VALUES (2191, 1, 2184, 73, 70, 0, false);
INSERT INTO zone VALUES (2190, 1, 2184, 72, 71, 0, false);
INSERT INTO zone VALUES (2193, 1, 2184, 74, 70, 0, false);
INSERT INTO zone VALUES (2192, 1, 2184, 73, 71, 0, false);
INSERT INTO zone VALUES (2195, 1, 2184, 75, 70, 0, false);
INSERT INTO zone VALUES (2194, 1, 2184, 74, 71, 0, false);
INSERT INTO zone VALUES (2197, 1, 2184, 76, 70, 0, false);
INSERT INTO zone VALUES (2196, 1, 2184, 75, 71, 0, false);
INSERT INTO zone VALUES (2199, 1, 2184, 77, 70, 0, false);
INSERT INTO zone VALUES (2198, 1, 2184, 76, 71, 0, false);
INSERT INTO zone VALUES (2201, 1, 2184, 78, 70, 0, false);
INSERT INTO zone VALUES (2200, 1, 2184, 77, 71, 0, false);
INSERT INTO zone VALUES (2203, 1, 2184, 79, 70, 0, false);
INSERT INTO zone VALUES (2202, 1, 2184, 78, 71, 0, false);
INSERT INTO zone VALUES (2204, 1, 2184, 79, 71, 0, false);
INSERT INTO zone VALUES (2186, 1, 2184, 70, 71, 0, false);
INSERT INTO zone VALUES (2185, 1, 2184, 70, 70, 0, true);
INSERT INTO zone VALUES (2187, 1, 2184, 71, 70, 0, true);
INSERT INTO zone VALUES (2227, 1, 2226, 70, 15, 0, true);
INSERT INTO zone VALUES (2228, 1, 2226, 70, 16, 0, true);
INSERT INTO zone VALUES (2229, 1, 2226, 71, 15, 0, true);
INSERT INTO zone VALUES (2230, 1, 2226, 71, 16, 0, true);
INSERT INTO zone VALUES (2231, 1, 2226, 72, 15, 0, true);
INSERT INTO zone VALUES (2232, 1, 2226, 72, 16, 0, true);
INSERT INTO zone VALUES (2233, 1, 2226, 73, 15, 0, true);
INSERT INTO zone VALUES (2234, 1, 2226, 73, 16, 0, true);
INSERT INTO zone VALUES (2235, 1, 2226, 74, 15, 0, true);
INSERT INTO zone VALUES (2236, 1, 2226, 74, 16, 0, true);
INSERT INTO zone VALUES (2237, 1, 2226, 75, 15, 0, true);
INSERT INTO zone VALUES (2238, 1, 2226, 75, 16, 0, true);
INSERT INTO zone VALUES (2239, 1, 2226, 76, 15, 0, true);
INSERT INTO zone VALUES (2240, 1, 2226, 76, 16, 0, true);
INSERT INTO zone VALUES (2241, 1, 2226, 77, 15, 0, true);
INSERT INTO zone VALUES (2242, 1, 2226, 77, 16, 0, true);
INSERT INTO zone VALUES (2243, 1, 2226, 78, 15, 0, true);
INSERT INTO zone VALUES (2244, 1, 2226, 78, 16, 0, true);
INSERT INTO zone VALUES (2245, 1, 2226, 79, 15, 0, true);
INSERT INTO zone VALUES (1794, 1, 1793, 40, 40, 0, true);
INSERT INTO zone VALUES (1795, 1, 1793, 40, 41, 0, true);
INSERT INTO zone VALUES (1796, 1, 1793, 40, 40, 1, true);
INSERT INTO zone VALUES (1797, 1, 1793, 40, 41, 1, true);
INSERT INTO zone VALUES (1798, 1, 1793, 40, 40, 2, true);
INSERT INTO zone VALUES (1799, 1, 1793, 40, 41, 2, true);
INSERT INTO zone VALUES (1800, 1, 1793, 40, 40, 3, true);
INSERT INTO zone VALUES (1801, 1, 1793, 40, 41, 3, true);
INSERT INTO zone VALUES (1802, 1, 1793, 40, 40, 4, true);
INSERT INTO zone VALUES (1803, 1, 1793, 40, 41, 4, true);
INSERT INTO zone VALUES (1804, 1, 1793, 41, 40, 0, true);
INSERT INTO zone VALUES (1805, 1, 1793, 41, 41, 0, true);
INSERT INTO zone VALUES (1806, 1, 1793, 41, 40, 1, true);
INSERT INTO zone VALUES (1807, 1, 1793, 41, 41, 1, true);
INSERT INTO zone VALUES (1808, 1, 1793, 41, 40, 2, true);
INSERT INTO zone VALUES (1809, 1, 1793, 41, 41, 2, true);
INSERT INTO zone VALUES (1810, 1, 1793, 41, 40, 3, true);
INSERT INTO zone VALUES (1811, 1, 1793, 41, 41, 3, true);
INSERT INTO zone VALUES (1812, 1, 1793, 41, 40, 4, true);
INSERT INTO zone VALUES (1813, 1, 1793, 41, 41, 4, true);
INSERT INTO zone VALUES (1814, 1, 1793, 42, 40, 0, true);
INSERT INTO zone VALUES (1815, 1, 1793, 42, 41, 0, true);
INSERT INTO zone VALUES (1816, 1, 1793, 42, 40, 1, true);
INSERT INTO zone VALUES (1817, 1, 1793, 42, 41, 1, true);
INSERT INTO zone VALUES (1818, 1, 1793, 42, 40, 2, true);
INSERT INTO zone VALUES (1819, 1, 1793, 42, 41, 2, true);
INSERT INTO zone VALUES (1820, 1, 1793, 42, 40, 3, true);
INSERT INTO zone VALUES (1821, 1, 1793, 42, 41, 3, true);
INSERT INTO zone VALUES (1822, 1, 1793, 42, 40, 4, true);
INSERT INTO zone VALUES (1823, 1, 1793, 42, 41, 4, true);
INSERT INTO zone VALUES (1824, 1, 1793, 43, 40, 0, true);
INSERT INTO zone VALUES (1825, 1, 1793, 43, 41, 0, true);
INSERT INTO zone VALUES (1826, 1, 1793, 43, 40, 1, true);
INSERT INTO zone VALUES (1827, 1, 1793, 43, 41, 1, true);
INSERT INTO zone VALUES (1828, 1, 1793, 43, 40, 2, true);
INSERT INTO zone VALUES (1829, 1, 1793, 43, 41, 2, true);
INSERT INTO zone VALUES (1830, 1, 1793, 43, 40, 3, true);
INSERT INTO zone VALUES (1831, 1, 1793, 43, 41, 3, true);
INSERT INTO zone VALUES (1832, 1, 1793, 43, 40, 4, true);
INSERT INTO zone VALUES (1833, 1, 1793, 43, 41, 4, true);
INSERT INTO zone VALUES (1834, 1, 1793, 44, 40, 0, true);
INSERT INTO zone VALUES (1835, 1, 1793, 44, 41, 0, true);
INSERT INTO zone VALUES (1836, 1, 1793, 44, 40, 1, true);
INSERT INTO zone VALUES (1837, 1, 1793, 44, 41, 1, true);
INSERT INTO zone VALUES (1838, 1, 1793, 44, 40, 2, true);
INSERT INTO zone VALUES (1839, 1, 1793, 44, 41, 2, true);
INSERT INTO zone VALUES (1840, 1, 1793, 44, 40, 3, true);
INSERT INTO zone VALUES (1841, 1, 1793, 44, 41, 3, true);
INSERT INTO zone VALUES (1842, 1, 1793, 44, 40, 4, true);
INSERT INTO zone VALUES (1843, 1, 1793, 44, 41, 4, true);
INSERT INTO zone VALUES (1844, 1, 1793, 45, 40, 0, true);
INSERT INTO zone VALUES (1845, 1, 1793, 45, 41, 0, true);
INSERT INTO zone VALUES (1846, 1, 1793, 45, 40, 1, true);
INSERT INTO zone VALUES (1847, 1, 1793, 45, 41, 1, true);
INSERT INTO zone VALUES (1848, 1, 1793, 45, 40, 2, true);
INSERT INTO zone VALUES (1849, 1, 1793, 45, 41, 2, true);
INSERT INTO zone VALUES (1850, 1, 1793, 45, 40, 3, true);
INSERT INTO zone VALUES (1851, 1, 1793, 45, 41, 3, true);
INSERT INTO zone VALUES (1852, 1, 1793, 45, 40, 4, true);
INSERT INTO zone VALUES (1853, 1, 1793, 45, 41, 4, true);
INSERT INTO zone VALUES (1854, 1, 1793, 46, 40, 0, true);
INSERT INTO zone VALUES (1855, 1, 1793, 46, 41, 0, true);
INSERT INTO zone VALUES (1856, 1, 1793, 46, 40, 1, true);
INSERT INTO zone VALUES (1857, 1, 1793, 46, 41, 1, true);
INSERT INTO zone VALUES (1858, 1, 1793, 46, 40, 2, true);
INSERT INTO zone VALUES (1859, 1, 1793, 46, 41, 2, true);
INSERT INTO zone VALUES (1860, 1, 1793, 46, 40, 3, true);
INSERT INTO zone VALUES (1861, 1, 1793, 46, 41, 3, true);
INSERT INTO zone VALUES (1862, 1, 1793, 46, 40, 4, true);
INSERT INTO zone VALUES (1863, 1, 1793, 46, 41, 4, true);
INSERT INTO zone VALUES (1864, 1, 1793, 47, 40, 0, true);
INSERT INTO zone VALUES (1865, 1, 1793, 47, 41, 0, true);
INSERT INTO zone VALUES (1866, 1, 1793, 47, 40, 1, true);
INSERT INTO zone VALUES (1867, 1, 1793, 47, 41, 1, true);
INSERT INTO zone VALUES (1868, 1, 1793, 47, 40, 2, true);
INSERT INTO zone VALUES (1869, 1, 1793, 47, 41, 2, true);
INSERT INTO zone VALUES (1870, 1, 1793, 47, 40, 3, true);
INSERT INTO zone VALUES (1871, 1, 1793, 47, 41, 3, true);
INSERT INTO zone VALUES (1872, 1, 1793, 47, 40, 4, true);
INSERT INTO zone VALUES (1873, 1, 1793, 47, 41, 4, true);
INSERT INTO zone VALUES (1874, 1, 1793, 48, 40, 0, true);
INSERT INTO zone VALUES (1875, 1, 1793, 48, 41, 0, true);
INSERT INTO zone VALUES (1876, 1, 1793, 48, 40, 1, true);
INSERT INTO zone VALUES (1877, 1, 1793, 48, 41, 1, true);
INSERT INTO zone VALUES (1878, 1, 1793, 48, 40, 2, true);
INSERT INTO zone VALUES (1879, 1, 1793, 48, 41, 2, true);
INSERT INTO zone VALUES (1880, 1, 1793, 48, 40, 3, true);
INSERT INTO zone VALUES (1881, 1, 1793, 48, 41, 3, true);
INSERT INTO zone VALUES (1882, 1, 1793, 48, 40, 4, true);
INSERT INTO zone VALUES (1883, 1, 1793, 48, 41, 4, true);
INSERT INTO zone VALUES (1884, 1, 1793, 49, 40, 0, true);
INSERT INTO zone VALUES (1885, 1, 1793, 49, 41, 0, true);
INSERT INTO zone VALUES (1886, 1, 1793, 49, 40, 1, true);
INSERT INTO zone VALUES (1887, 1, 1793, 49, 41, 1, true);
INSERT INTO zone VALUES (1888, 1, 1793, 49, 40, 2, true);
INSERT INTO zone VALUES (1889, 1, 1793, 49, 41, 2, true);
INSERT INTO zone VALUES (1890, 1, 1793, 49, 40, 3, true);
INSERT INTO zone VALUES (1891, 1, 1793, 49, 41, 3, true);
INSERT INTO zone VALUES (1892, 1, 1793, 49, 40, 4, true);
INSERT INTO zone VALUES (1893, 1, 1793, 49, 41, 4, true);
INSERT INTO zone VALUES (486, 1, 408, 9, 3, 3, false);
INSERT INTO zone VALUES (488, 1, 408, 9, 3, 4, false);
INSERT INTO zone VALUES (485, 1, 408, 9, 2, 3, false);
INSERT INTO zone VALUES (483, 1, 408, 9, 2, 2, false);
INSERT INTO zone VALUES (481, 1, 408, 9, 2, 1, false);
INSERT INTO zone VALUES (479, 1, 408, 9, 2, 0, false);
INSERT INTO zone VALUES (476, 1, 408, 8, 3, 3, false);
INSERT INTO zone VALUES (2168, 1, 2153, 77, 85, 0, false);
INSERT INTO zone VALUES (484, 1, 408, 9, 3, 2, false);
INSERT INTO zone VALUES (2154, 1, 2153, 70, 85, 0, false);
INSERT INTO zone VALUES (2156, 1, 2153, 71, 85, 0, false);
INSERT INTO zone VALUES (2206, 1, 2205, 70, 10, 0, true);
INSERT INTO zone VALUES (2207, 1, 2205, 70, 11, 0, true);
INSERT INTO zone VALUES (2208, 1, 2205, 71, 10, 0, true);
INSERT INTO zone VALUES (2209, 1, 2205, 71, 11, 0, true);
INSERT INTO zone VALUES (2210, 1, 2205, 72, 10, 0, true);
INSERT INTO zone VALUES (2211, 1, 2205, 72, 11, 0, true);
INSERT INTO zone VALUES (2212, 1, 2205, 73, 10, 0, true);
INSERT INTO zone VALUES (2213, 1, 2205, 73, 11, 0, true);
INSERT INTO zone VALUES (2214, 1, 2205, 74, 10, 0, true);
INSERT INTO zone VALUES (2215, 1, 2205, 74, 11, 0, true);
INSERT INTO zone VALUES (2216, 1, 2205, 75, 10, 0, true);
INSERT INTO zone VALUES (2217, 1, 2205, 75, 11, 0, true);
INSERT INTO zone VALUES (2218, 1, 2205, 76, 10, 0, true);
INSERT INTO zone VALUES (2219, 1, 2205, 76, 11, 0, true);
INSERT INTO zone VALUES (2220, 1, 2205, 77, 10, 0, true);
INSERT INTO zone VALUES (2221, 1, 2205, 77, 11, 0, true);
INSERT INTO zone VALUES (2222, 1, 2205, 78, 10, 0, true);
INSERT INTO zone VALUES (2223, 1, 2205, 78, 11, 0, true);
INSERT INTO zone VALUES (2224, 1, 2205, 79, 10, 0, true);
INSERT INTO zone VALUES (2225, 1, 2205, 79, 11, 0, true);
INSERT INTO zone VALUES (2246, 1, 2226, 79, 16, 0, true);
INSERT INTO zone VALUES (2248, 1, 2247, 70, 30, 0, true);
INSERT INTO zone VALUES (2249, 1, 2247, 70, 31, 0, true);
INSERT INTO zone VALUES (2250, 1, 2247, 71, 30, 0, true);
INSERT INTO zone VALUES (2251, 1, 2247, 71, 31, 0, true);
INSERT INTO zone VALUES (2252, 1, 2247, 72, 30, 0, true);
INSERT INTO zone VALUES (2253, 1, 2247, 72, 31, 0, true);
INSERT INTO zone VALUES (2254, 1, 2247, 73, 30, 0, true);
INSERT INTO zone VALUES (2255, 1, 2247, 73, 31, 0, true);
INSERT INTO zone VALUES (2256, 1, 2247, 74, 30, 0, true);
INSERT INTO zone VALUES (2257, 1, 2247, 74, 31, 0, true);
INSERT INTO zone VALUES (2258, 1, 2247, 75, 30, 0, true);
INSERT INTO zone VALUES (2259, 1, 2247, 75, 31, 0, true);
INSERT INTO zone VALUES (2260, 1, 2247, 76, 30, 0, true);
INSERT INTO zone VALUES (2261, 1, 2247, 76, 31, 0, true);
INSERT INTO zone VALUES (2262, 1, 2247, 77, 30, 0, true);
INSERT INTO zone VALUES (2263, 1, 2247, 77, 31, 0, true);
INSERT INTO zone VALUES (2264, 1, 2247, 78, 30, 0, true);
INSERT INTO zone VALUES (2265, 1, 2247, 78, 31, 0, true);
INSERT INTO zone VALUES (2266, 1, 2247, 79, 30, 0, true);
INSERT INTO zone VALUES (2267, 1, 2247, 79, 31, 0, true);
INSERT INTO zone VALUES (2269, 1, 2268, 70, 45, 0, true);
INSERT INTO zone VALUES (2270, 1, 2268, 70, 46, 0, true);
INSERT INTO zone VALUES (2271, 1, 2268, 71, 45, 0, true);
INSERT INTO zone VALUES (2272, 1, 2268, 71, 46, 0, true);
INSERT INTO zone VALUES (2273, 1, 2268, 72, 45, 0, true);
INSERT INTO zone VALUES (2274, 1, 2268, 72, 46, 0, true);
INSERT INTO zone VALUES (2167, 1, 2153, 76, 86, 0, false);
INSERT INTO zone VALUES (2170, 1, 2153, 78, 85, 0, false);
INSERT INTO zone VALUES (2155, 1, 2153, 70, 86, 0, false);
INSERT INTO zone VALUES (2158, 1, 2153, 72, 85, 0, false);
INSERT INTO zone VALUES (2157, 1, 2153, 71, 86, 0, false);
INSERT INTO zone VALUES (2160, 1, 2153, 73, 85, 0, false);
INSERT INTO zone VALUES (2159, 1, 2153, 72, 86, 0, false);
INSERT INTO zone VALUES (2275, 1, 2268, 73, 45, 0, true);
INSERT INTO zone VALUES (2276, 1, 2268, 73, 46, 0, true);
INSERT INTO zone VALUES (2277, 1, 2268, 74, 45, 0, true);
INSERT INTO zone VALUES (2278, 1, 2268, 74, 46, 0, true);
INSERT INTO zone VALUES (2279, 1, 2268, 75, 45, 0, true);
INSERT INTO zone VALUES (2280, 1, 2268, 75, 46, 0, true);
INSERT INTO zone VALUES (2281, 1, 2268, 76, 45, 0, true);
INSERT INTO zone VALUES (2282, 1, 2268, 76, 46, 0, true);
INSERT INTO zone VALUES (2283, 1, 2268, 77, 45, 0, true);
INSERT INTO zone VALUES (2284, 1, 2268, 77, 46, 0, true);
INSERT INTO zone VALUES (2285, 1, 2268, 78, 45, 0, true);
INSERT INTO zone VALUES (2286, 1, 2268, 78, 46, 0, true);
INSERT INTO zone VALUES (2287, 1, 2268, 79, 45, 0, true);
INSERT INTO zone VALUES (2288, 1, 2268, 79, 46, 0, true);
INSERT INTO zone VALUES (2290, 1, 2289, 70, 60, 0, true);
INSERT INTO zone VALUES (2291, 1, 2289, 70, 61, 0, true);
INSERT INTO zone VALUES (2292, 1, 2289, 71, 60, 0, true);
INSERT INTO zone VALUES (2293, 1, 2289, 71, 61, 0, true);
INSERT INTO zone VALUES (2294, 1, 2289, 72, 60, 0, true);
INSERT INTO zone VALUES (2295, 1, 2289, 72, 61, 0, true);
INSERT INTO zone VALUES (2296, 1, 2289, 73, 60, 0, true);
INSERT INTO zone VALUES (2297, 1, 2289, 73, 61, 0, true);
INSERT INTO zone VALUES (2298, 1, 2289, 74, 60, 0, true);
INSERT INTO zone VALUES (2299, 1, 2289, 74, 61, 0, true);
INSERT INTO zone VALUES (2300, 1, 2289, 75, 60, 0, true);
INSERT INTO zone VALUES (2301, 1, 2289, 75, 61, 0, true);
INSERT INTO zone VALUES (2302, 1, 2289, 76, 60, 0, true);
INSERT INTO zone VALUES (2303, 1, 2289, 76, 61, 0, true);
INSERT INTO zone VALUES (2304, 1, 2289, 77, 60, 0, true);
INSERT INTO zone VALUES (2305, 1, 2289, 77, 61, 0, true);
INSERT INTO zone VALUES (2306, 1, 2289, 78, 60, 0, true);
INSERT INTO zone VALUES (2307, 1, 2289, 78, 61, 0, true);
INSERT INTO zone VALUES (2308, 1, 2289, 79, 60, 0, true);
INSERT INTO zone VALUES (2309, 1, 2289, 79, 61, 0, true);
INSERT INTO zone VALUES (2374, 1, 2373, 55, 10, 0, true);
INSERT INTO zone VALUES (2375, 1, 2373, 55, 11, 0, true);
INSERT INTO zone VALUES (2376, 1, 2373, 56, 10, 0, true);
INSERT INTO zone VALUES (2377, 1, 2373, 56, 11, 0, true);
INSERT INTO zone VALUES (2378, 1, 2373, 57, 10, 0, true);
INSERT INTO zone VALUES (2379, 1, 2373, 57, 11, 0, true);
INSERT INTO zone VALUES (2380, 1, 2373, 58, 10, 0, true);
INSERT INTO zone VALUES (2381, 1, 2373, 58, 11, 0, true);
INSERT INTO zone VALUES (2382, 1, 2373, 59, 10, 0, true);
INSERT INTO zone VALUES (2383, 1, 2373, 59, 11, 0, true);
INSERT INTO zone VALUES (2384, 1, 2373, 60, 10, 0, true);
INSERT INTO zone VALUES (2385, 1, 2373, 60, 11, 0, true);
INSERT INTO zone VALUES (2386, 1, 2373, 61, 10, 0, true);
INSERT INTO zone VALUES (2387, 1, 2373, 61, 11, 0, true);
INSERT INTO zone VALUES (2388, 1, 2373, 62, 10, 0, true);
INSERT INTO zone VALUES (2389, 1, 2373, 62, 11, 0, true);
INSERT INTO zone VALUES (2390, 1, 2373, 63, 10, 0, true);
INSERT INTO zone VALUES (2391, 1, 2373, 63, 11, 0, true);
INSERT INTO zone VALUES (2392, 1, 2373, 64, 10, 0, true);
INSERT INTO zone VALUES (2393, 1, 2373, 64, 11, 0, true);
INSERT INTO zone VALUES (2395, 1, 2394, 55, 1, 0, true);
INSERT INTO zone VALUES (2396, 1, 2394, 55, 2, 0, true);
INSERT INTO zone VALUES (2397, 1, 2394, 56, 1, 0, true);
INSERT INTO zone VALUES (2398, 1, 2394, 56, 2, 0, true);
INSERT INTO zone VALUES (2399, 1, 2394, 57, 1, 0, true);
INSERT INTO zone VALUES (2400, 1, 2394, 57, 2, 0, true);
INSERT INTO zone VALUES (2401, 1, 2394, 58, 1, 0, true);
INSERT INTO zone VALUES (2402, 1, 2394, 58, 2, 0, true);
INSERT INTO zone VALUES (2403, 1, 2394, 59, 1, 0, true);
INSERT INTO zone VALUES (2404, 1, 2394, 59, 2, 0, true);
INSERT INTO zone VALUES (2405, 1, 2394, 60, 1, 0, true);
INSERT INTO zone VALUES (2406, 1, 2394, 60, 2, 0, true);
INSERT INTO zone VALUES (2407, 1, 2394, 61, 1, 0, true);
INSERT INTO zone VALUES (2408, 1, 2394, 61, 2, 0, true);
INSERT INTO zone VALUES (2409, 1, 2394, 62, 1, 0, true);
INSERT INTO zone VALUES (2410, 1, 2394, 62, 2, 0, true);
INSERT INTO zone VALUES (2411, 1, 2394, 63, 1, 0, true);
INSERT INTO zone VALUES (2412, 1, 2394, 63, 2, 0, true);
INSERT INTO zone VALUES (2413, 1, 2394, 64, 1, 0, true);
INSERT INTO zone VALUES (2414, 1, 2394, 64, 2, 0, true);
INSERT INTO zone VALUES (2416, 1, 2415, 55, 15, 0, true);
INSERT INTO zone VALUES (2417, 1, 2415, 55, 16, 0, true);
INSERT INTO zone VALUES (2418, 1, 2415, 56, 15, 0, true);
INSERT INTO zone VALUES (2419, 1, 2415, 56, 16, 0, true);
INSERT INTO zone VALUES (2420, 1, 2415, 57, 15, 0, true);
INSERT INTO zone VALUES (2421, 1, 2415, 57, 16, 0, true);
INSERT INTO zone VALUES (2422, 1, 2415, 58, 15, 0, true);
INSERT INTO zone VALUES (2423, 1, 2415, 58, 16, 0, true);
INSERT INTO zone VALUES (2424, 1, 2415, 59, 15, 0, true);
INSERT INTO zone VALUES (2425, 1, 2415, 59, 16, 0, true);
INSERT INTO zone VALUES (2426, 1, 2415, 60, 15, 0, true);
INSERT INTO zone VALUES (2427, 1, 2415, 60, 16, 0, true);
INSERT INTO zone VALUES (2428, 1, 2415, 61, 15, 0, true);
INSERT INTO zone VALUES (2429, 1, 2415, 61, 16, 0, true);
INSERT INTO zone VALUES (2430, 1, 2415, 62, 15, 0, true);
INSERT INTO zone VALUES (2431, 1, 2415, 62, 16, 0, true);
INSERT INTO zone VALUES (2432, 1, 2415, 63, 15, 0, true);
INSERT INTO zone VALUES (2433, 1, 2415, 63, 16, 0, true);
INSERT INTO zone VALUES (2434, 1, 2415, 64, 15, 0, true);
INSERT INTO zone VALUES (2435, 1, 2415, 64, 16, 0, true);
INSERT INTO zone VALUES (2437, 1, 2436, 55, 20, 0, true);
INSERT INTO zone VALUES (2438, 1, 2436, 55, 21, 0, true);
INSERT INTO zone VALUES (2439, 1, 2436, 56, 20, 0, true);
INSERT INTO zone VALUES (2440, 1, 2436, 56, 21, 0, true);
INSERT INTO zone VALUES (2441, 1, 2436, 57, 20, 0, true);
INSERT INTO zone VALUES (2442, 1, 2436, 57, 21, 0, true);
INSERT INTO zone VALUES (2443, 1, 2436, 58, 20, 0, true);
INSERT INTO zone VALUES (2444, 1, 2436, 58, 21, 0, true);
INSERT INTO zone VALUES (2445, 1, 2436, 59, 20, 0, true);
INSERT INTO zone VALUES (2446, 1, 2436, 59, 21, 0, true);
INSERT INTO zone VALUES (2447, 1, 2436, 60, 20, 0, true);
INSERT INTO zone VALUES (2448, 1, 2436, 60, 21, 0, true);
INSERT INTO zone VALUES (2449, 1, 2436, 61, 20, 0, true);
INSERT INTO zone VALUES (2450, 1, 2436, 61, 21, 0, true);
INSERT INTO zone VALUES (2451, 1, 2436, 62, 20, 0, true);
INSERT INTO zone VALUES (2452, 1, 2436, 62, 21, 0, true);
INSERT INTO zone VALUES (2453, 1, 2436, 63, 20, 0, true);
INSERT INTO zone VALUES (2454, 1, 2436, 63, 21, 0, true);
INSERT INTO zone VALUES (2455, 1, 2436, 64, 20, 0, true);
INSERT INTO zone VALUES (2456, 1, 2436, 64, 21, 0, true);
INSERT INTO zone VALUES (2169, 1, 2153, 77, 86, 0, false);
INSERT INTO zone VALUES (2172, 1, 2153, 79, 85, 0, false);
INSERT INTO zone VALUES (2171, 1, 2153, 78, 86, 0, false);
INSERT INTO zone VALUES (2173, 1, 2153, 79, 86, 0, false);
INSERT INTO zone VALUES (410, 1, 408, 2, 3, 0, false);
INSERT INTO zone VALUES (412, 1, 408, 2, 3, 1, false);
INSERT INTO zone VALUES (414, 1, 408, 2, 3, 2, false);
INSERT INTO zone VALUES (416, 1, 408, 2, 3, 3, false);
INSERT INTO zone VALUES (418, 1, 408, 2, 3, 4, false);
INSERT INTO zone VALUES (419, 1, 408, 3, 2, 0, false);
INSERT INTO zone VALUES (421, 1, 408, 3, 2, 1, false);
INSERT INTO zone VALUES (423, 1, 408, 3, 2, 2, false);
INSERT INTO zone VALUES (425, 1, 408, 3, 2, 3, false);
INSERT INTO zone VALUES (427, 1, 408, 3, 2, 4, false);
INSERT INTO zone VALUES (420, 1, 408, 3, 3, 0, false);
INSERT INTO zone VALUES (422, 1, 408, 3, 3, 1, false);
INSERT INTO zone VALUES (424, 1, 408, 3, 3, 2, false);
INSERT INTO zone VALUES (426, 1, 408, 3, 3, 3, false);
INSERT INTO zone VALUES (428, 1, 408, 3, 3, 4, false);
INSERT INTO zone VALUES (429, 1, 408, 4, 2, 0, false);
INSERT INTO zone VALUES (431, 1, 408, 4, 2, 1, false);
INSERT INTO zone VALUES (433, 1, 408, 4, 2, 2, false);
INSERT INTO zone VALUES (435, 1, 408, 4, 2, 3, false);
INSERT INTO zone VALUES (437, 1, 408, 4, 2, 4, false);
INSERT INTO zone VALUES (439, 1, 408, 5, 2, 0, false);
INSERT INTO zone VALUES (441, 1, 408, 5, 2, 1, false);
INSERT INTO zone VALUES (443, 1, 408, 5, 2, 2, false);
INSERT INTO zone VALUES (430, 1, 408, 4, 3, 0, false);
INSERT INTO zone VALUES (432, 1, 408, 4, 3, 1, false);
INSERT INTO zone VALUES (434, 1, 408, 4, 3, 2, false);
INSERT INTO zone VALUES (436, 1, 408, 4, 3, 3, false);


--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('zone_id_zone_seq_1', 1, false);


--
-- PostgreSQL database dump complete
--

