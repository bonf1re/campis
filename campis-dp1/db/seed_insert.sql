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

INSERT INTO product_type VALUES (2, 'TipoProducto2');
INSERT INTO product_type VALUES (3, 'TipoProducto3');


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO warehouse VALUES (1894, 'Deposito', 100, 100, true);
INSERT INTO warehouse VALUES (1, 'Principal', 150, 100, true);


--
-- Data for Name: area; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO area VALUES (3, 'Area3', 10, 10, 0, 0, 1, 2);
INSERT INTO area VALUES (1550, 'Area4', 2, 2, 2, 2, 1, 2);
INSERT INTO area VALUES (2, 'Area5', 10, 10, 0, 0, 1, 3);
INSERT INTO area VALUES (1686, 'Area6', 10, 10, 999, 999, 1, 3);


--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 1, false);


--
-- Data for Name: unit_of_measure; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO unit_of_measure VALUES (1, 'kilogramos', NULL);
INSERT INTO unit_of_measure VALUES (2, 'litros', NULL);


--
-- Data for Name: product; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO product VALUES (739, 'Tierra', 'Tierra de construccion', 11, 11, 12, 'Humus', 123, 2, 2, 40, 0);
INSERT INTO product VALUES (752, 'Clavos', 'Caja de clavos simple.', 1, 1, 1, 'Ferrex', 8, 1, 3, 100, 0);
INSERT INTO product VALUES (144, 'Martillos', 'Martillos especiales para tumbar paredes.', 1, 1, 8, 'Comba Dura', 19.5060005187988281, 1, 2, 30, 0);


--
-- Data for Name: batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO batch VALUES (679, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (664, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 7, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (666, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 7, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (668, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 7, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (590, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (592, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (594, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (596, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (598, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (600, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (602, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (604, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (606, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (608, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (610, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (612, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (614, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (616, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (618, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (620, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (622, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (624, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (626, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (628, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (630, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (632, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (634, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (636, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (638, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (640, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (642, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (644, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (646, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (648, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (650, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (652, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (654, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (656, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (658, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (660, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (662, 40, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 2, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (681, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (683, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (685, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (687, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (689, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (691, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (693, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (695, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (697, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (699, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (701, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (703, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (705, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (707, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (709, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (711, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (713, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (715, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (717, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (719, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (721, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (723, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (725, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (727, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (729, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (731, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (733, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (735, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (737, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (739, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (741, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (743, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (745, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (747, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (749, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (751, 40, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (757, 80, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (779, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (759, 60, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[751]', '--');
INSERT INTO batch VALUES (781, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (783, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (755, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (753, 100, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[321]', '--');
INSERT INTO batch VALUES (785, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (787, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (789, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (772, 20, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 3, true, NULL, '[757]', '--');
INSERT INTO batch VALUES (791, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (793, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (795, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (797, 50, 0, '2017-11-18 09:47:18.915', '2017-11-30 00:00:00', 752, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (800, 25, 0, '2017-11-18 14:50:35.851', '2017-11-30 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (802, 25, 0, '2017-11-18 14:50:35.851', '2017-11-30 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (804, 25, 0, '2017-11-18 14:50:35.851', '2017-11-30 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (806, 25, 0, '2017-11-18 14:50:35.851', '2017-11-30 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (808, 25, 0, '2017-11-18 14:50:35.851', '2017-11-30 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (810, 30, 0, '2017-11-18 15:00:24.871', '2017-11-27 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (812, 30, 0, '2017-11-18 15:00:24.871', '2017-11-27 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (814, 30, 0, '2017-11-18 15:00:24.871', '2017-11-27 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (816, 30, 0, '2017-11-18 15:00:24.871', '2017-11-27 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (818, 30, 0, '2017-11-18 15:00:24.871', '2017-11-27 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (820, 5, 0, '2017-11-18 15:23:53.442', '2017-11-25 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (822, 5, 0, '2017-11-18 15:23:53.442', '2017-11-25 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (824, 5, 0, '2017-11-18 15:23:53.442', '2017-11-25 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (826, 40, 0, '2017-11-18 15:23:53.442', '2017-11-25 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (828, 40, 0, '2017-11-18 15:23:53.442', '2017-11-25 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (830, 40, 0, '2017-11-18 15:23:53.442', '2017-11-25 00:00:00', 739, 3, false, NULL, '--', NULL);
INSERT INTO batch VALUES (670, 60, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, 7, true, NULL, '[662]', '--');
INSERT INTO batch VALUES (321, 4000, 400, '2017-11-18 00:00:00', '2019-10-31 00:00:00', 752, -1, true, NULL, '--', '-1');


--
-- Name: batch_id_batch_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('batch_id_batch_seq', 1, false);


--
-- Data for Name: campaign; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO campaign VALUES (0, 'Ninguna', 'No existe una campaña asociada para los descuentos', '1969-12-31 19:00:00', '1969-12-31 19:00:00');
INSERT INTO campaign VALUES (102, 'dfghjk', 'fghjk', '2017-11-14 00:00:00', '2017-11-22 00:00:00');


--
-- Name: campaign_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('campaign_id_seq', 1, false);


--
-- Data for Name: client; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO client VALUES (1, 'Benito Juarez', '48296679', '12354687952', true, 'Los naranjos 123', '555-3462', 'benito07@gmail.com', NULL);
INSERT INTO client VALUES (57, 'Pablo Castro', '12321', '1232122', true, '2121123', '333', 'test@test.com', NULL);


--
-- Name: client_id_client_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('client_id_client_seq', 1, false);


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

INSERT INTO request_order VALUES (104, '2017-11-15 00:00:00', '2017-11-18 00:00:00', 861, 697.40997314453125, 'EN PROGRESO', 57, 1, 4, 'frgtyhujik');
INSERT INTO request_order VALUES (97, '2017-11-15 00:00:00', '2017-11-17 00:00:00', 1230, 1451.4000244140625, 'ENTREGADO', 1, 1, 6, 'rftgyhuj');
INSERT INTO request_order VALUES (460, '2017-11-18 01:22:03', '2017-11-23 00:00:00', 1353, 1424.03271484375, 'EN PROGRESO', 1, 1, 7, 'yea');
INSERT INTO request_order VALUES (464, '2017-11-18 01:25:16', '2017-11-30 00:00:00', 777.01202392578125, 380.27301025390625, 'ENTREGADO', 57, 2, 10, 'matute');


--
-- Data for Name: complaint; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO complaint VALUES (114, 'wedrftgyhuji', 'Aceptado', 97);


--
-- Name: disclaim_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('disclaim_seq', 1, false);


--
-- Data for Name: dispatch_move; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_move VALUES (589, 1, 1, '2017-11-18 03:09:33.651', 2, 321, '2017-11-18 03:09:33.651');


--
-- Data for Name: dispatch_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_order VALUES (106, 104, 1, 'INCOMPLETO', NULL, NULL);
INSERT INTO dispatch_order VALUES (263, 104, 1, 'EN PROGRESO', NULL, NULL);
INSERT INTO dispatch_order VALUES (99, 97, 1, 'ENTREGADO', NULL, NULL);
INSERT INTO dispatch_order VALUES (462, 460, 1, 'EN PROGRESO', NULL, NULL);
INSERT INTO dispatch_order VALUES (467, 464, 2, 'ENTREGADO', NULL, NULL);
INSERT INTO dispatch_order VALUES (498, 464, 2, 'ENTREGADO', NULL, NULL);


--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_id_dispatch_order_seq', 1, false);


--
-- Data for Name: dispatch_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO dispatch_order_line VALUES (100, 99, 739, 10, false);
INSERT INTO dispatch_order_line VALUES (107, 106, 739, 7, false);
INSERT INTO dispatch_order_line VALUES (463, 462, 739, 11, false);
INSERT INTO dispatch_order_line VALUES (469, 467, 739, 6, false);
INSERT INTO dispatch_order_line VALUES (468, 467, 144, 2, true);
INSERT INTO dispatch_order_line VALUES (499, 498, 739, 6, true);


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

SELECT pg_catalog.setval('group_batch_id_seq', 1, false);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 832, true);


--
-- Data for Name: role; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO role VALUES (1593, 'Admin');
INSERT INTO role VALUES (118, 'Vigilante');


--
-- Data for Name: users; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO users VALUES (143, 'perualmundial', 'rusia2018', 'ilusion', 'rusia2018@somosqatar.com', '2017-11-14 21:11:28.313', false, 118, '2017-11-14 21:11:28.313', 'perucampeon');
INSERT INTO users VALUES (21, 'Miguel', 'Guanira', '123456', 'guani@ra.com', '2017-10-27 23:53:16.454', true, 1593, '2017-10-27 23:53:16.454', 'mguani');
INSERT INTO users VALUES (256, 'Testing', 'Bugs', '123451', 't@bug.com', '2017-11-17 13:28:55.374', true, 1593, '2017-11-17 13:28:55.374', 'tbug');


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO vehicle VALUES (14, 8976, 100, true, 1, 'uoiuoiu');
INSERT INTO vehicle VALUES (501, 1500, 40, true, 1894, 'ABG-SSS');
INSERT INTO vehicle VALUES (319, 123, 70, true, 1, 'ABC-123');
INSERT INTO vehicle VALUES (799, 150, 50, true, 1, 'AMY-123');


--
-- Data for Name: movement; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO movement VALUES (680, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 449, 679);
INSERT INTO movement VALUES (682, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 453, 681);
INSERT INTO movement VALUES (684, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 455, 683);
INSERT INTO movement VALUES (686, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 457, 685);
INSERT INTO movement VALUES (688, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 440, 687);
INSERT INTO movement VALUES (690, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 463, 689);
INSERT INTO movement VALUES (692, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 459, 691);
INSERT INTO movement VALUES (694, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 461, 693);
INSERT INTO movement VALUES (696, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 458, 695);
INSERT INTO movement VALUES (698, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 456, 697);
INSERT INTO movement VALUES (700, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 465, 699);
INSERT INTO movement VALUES (702, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 450, 701);
INSERT INTO movement VALUES (704, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 452, 703);
INSERT INTO movement VALUES (706, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 454, 705);
INSERT INTO movement VALUES (708, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 467, 707);
INSERT INTO movement VALUES (710, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 462, 709);
INSERT INTO movement VALUES (712, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 460, 711);
INSERT INTO movement VALUES (714, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 477, 713);
INSERT INTO movement VALUES (716, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 473, 715);
INSERT INTO movement VALUES (718, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 471, 717);
INSERT INTO movement VALUES (720, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 469, 719);
INSERT INTO movement VALUES (722, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 468, 721);
INSERT INTO movement VALUES (724, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 475, 723);
INSERT INTO movement VALUES (726, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 466, 725);
INSERT INTO movement VALUES (728, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 464, 727);
INSERT INTO movement VALUES (730, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 478, 729);
INSERT INTO movement VALUES (732, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 474, 731);
INSERT INTO movement VALUES (734, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 472, 733);
INSERT INTO movement VALUES (736, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 470, 735);
INSERT INTO movement VALUES (738, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 487, 737);
INSERT INTO movement VALUES (740, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 476, 739);
INSERT INTO movement VALUES (742, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 485, 741);
INSERT INTO movement VALUES (744, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 483, 743);
INSERT INTO movement VALUES (746, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 481, 745);
INSERT INTO movement VALUES (748, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 479, 747);
INSERT INTO movement VALUES (750, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 484, 749);
INSERT INTO movement VALUES (752, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 482, 751);
INSERT INTO movement VALUES (754, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 486, 753);
INSERT INTO movement VALUES (756, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 488, 755);
INSERT INTO movement VALUES (758, '2017-11-18 03:47:37.698', 21, 100, 14, 1, 1, 480, 757);
INSERT INTO movement VALUES (760, '2017-11-18 03:51:46.509', 21, 60, 14, 2, 1894, 482, 759);
INSERT INTO movement VALUES (761, '2017-11-18 03:51:46.509', 21, 60, 14, 4, 1, 482, 759);
INSERT INTO movement VALUES (762, '2017-11-18 03:51:46.509', 21, 100, 14, 2, 1894, 486, 753);
INSERT INTO movement VALUES (763, '2017-11-18 03:51:46.509', 21, 100, 14, 4, 1, 486, 753);
INSERT INTO movement VALUES (764, '2017-11-18 03:51:46.509', 21, 100, 14, 2, 1894, 488, 755);
INSERT INTO movement VALUES (765, '2017-11-18 03:51:46.509', 21, 100, 14, 4, 1, 488, 755);
INSERT INTO movement VALUES (766, '2017-11-18 03:51:46.509', 21, 100, 14, 2, 1894, 480, 757);
INSERT INTO movement VALUES (767, '2017-11-18 03:51:46.509', 21, 100, 14, 4, 1, 480, 757);
INSERT INTO movement VALUES (768, '2017-11-18 03:52:47.983', 21, 60, 501, 1, 1894, 579, 759);
INSERT INTO movement VALUES (769, '2017-11-18 03:52:47.983', 21, 100, 501, 1, 1894, 577, 757);
INSERT INTO movement VALUES (770, '2017-11-18 03:52:47.983', 21, 100, 501, 1, 1894, 581, 755);
INSERT INTO movement VALUES (771, '2017-11-18 03:52:47.983', 21, 100, 501, 1, 1894, 583, 753);
INSERT INTO movement VALUES (773, '2017-11-18 03:53:50.766', 21, 20, 501, 2, 1, 480, 772);
INSERT INTO movement VALUES (774, '2017-11-18 03:53:50.766', 21, 20, 501, 4, 1894, 480, 772);
INSERT INTO movement VALUES (775, '2017-11-18 03:53:50.766', 21, 60, 501, 2, 1, 482, 759);
INSERT INTO movement VALUES (776, '2017-11-18 03:53:50.766', 21, 60, 501, 4, 1894, 482, 759);
INSERT INTO movement VALUES (777, '2017-11-18 03:54:54.787', 21, 20, 14, 1, 1, 486, 772);
INSERT INTO movement VALUES (778, '2017-11-18 03:54:54.787', 21, 60, 14, 1, 1, 480, 759);
INSERT INTO movement VALUES (780, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 567, 779);
INSERT INTO movement VALUES (782, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 565, 781);
INSERT INTO movement VALUES (784, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 574, 783);
INSERT INTO movement VALUES (786, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 572, 785);
INSERT INTO movement VALUES (788, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 571, 787);
INSERT INTO movement VALUES (790, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 569, 789);
INSERT INTO movement VALUES (792, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 575, 791);
INSERT INTO movement VALUES (794, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 573, 793);
INSERT INTO movement VALUES (796, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 482, 795);
INSERT INTO movement VALUES (798, '2017-11-18 09:47:36.346', 21, 50, 14, 0, 1, 488, 797);
INSERT INTO movement VALUES (801, '2017-11-18 14:50:49.588', 21, 25, 14, 0, 1, 564, 800);
INSERT INTO movement VALUES (803, '2017-11-18 14:50:49.588', 21, 25, 14, 0, 1, 563, 802);
INSERT INTO movement VALUES (805, '2017-11-18 14:50:49.588', 21, 25, 14, 0, 1, 561, 804);
INSERT INTO movement VALUES (807, '2017-11-18 14:50:49.588', 21, 25, 14, 0, 1, 570, 806);
INSERT INTO movement VALUES (809, '2017-11-18 14:50:49.588', 21, 25, 14, 0, 1, 568, 808);
INSERT INTO movement VALUES (811, '2017-11-18 15:01:28.665', 21, 30, 14, 0, 1, 562, 810);
INSERT INTO movement VALUES (813, '2017-11-18 15:01:28.665', 21, 30, 14, 0, 1, 560, 812);
INSERT INTO movement VALUES (815, '2017-11-18 15:01:28.665', 21, 30, 14, 0, 1, 559, 814);
INSERT INTO movement VALUES (817, '2017-11-18 15:01:28.665', 21, 30, 14, 0, 1, 557, 816);
INSERT INTO movement VALUES (819, '2017-11-18 15:01:28.665', 21, 30, 14, 0, 1, 566, 818);
INSERT INTO movement VALUES (821, '2017-11-18 15:24:01.677', 21, 5, 14, 0, 1, 447, 820);
INSERT INTO movement VALUES (823, '2017-11-18 15:24:01.677', 21, 5, 14, 0, 1, 446, 822);
INSERT INTO movement VALUES (825, '2017-11-18 15:24:01.677', 21, 5, 14, 0, 1, 442, 824);
INSERT INTO movement VALUES (827, '2017-11-18 15:24:01.677', 21, 40, 14, 0, 1, 448, 826);
INSERT INTO movement VALUES (829, '2017-11-18 15:24:01.677', 21, 40, 14, 0, 1, 444, 828);
INSERT INTO movement VALUES (831, '2017-11-18 15:24:01.677', 21, 40, 14, 0, 1, 451, 830);


--
-- Name: movement_id_movement_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('movement_id_movement_seq', 1, false);


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
-- Data for Name: parameters; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO parameters VALUES (0.189999997615814209, 3.25099992752075195, 4.28099999999999969, 3.83299994468688965);


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
INSERT INTO permission VALUES (1, 118, false, false, 119);
INSERT INTO permission VALUES (2, 118, false, false, 120);
INSERT INTO permission VALUES (3, 118, false, false, 121);
INSERT INTO permission VALUES (4, 118, true, true, 122);
INSERT INTO permission VALUES (5, 118, false, false, 123);
INSERT INTO permission VALUES (6, 118, false, false, 124);
INSERT INTO permission VALUES (7, 118, false, false, 125);
INSERT INTO permission VALUES (8, 118, false, false, 126);
INSERT INTO permission VALUES (9, 118, true, true, 127);
INSERT INTO permission VALUES (10, 118, true, false, 128);
INSERT INTO permission VALUES (11, 118, false, false, 129);
INSERT INTO permission VALUES (12, 118, false, false, 130);
INSERT INTO permission VALUES (13, 118, false, false, 131);
INSERT INTO permission VALUES (14, 118, false, false, 132);
INSERT INTO permission VALUES (15, 118, false, false, 133);
INSERT INTO permission VALUES (16, 118, false, false, 134);
INSERT INTO permission VALUES (17, 118, false, false, 135);
INSERT INTO permission VALUES (18, 118, false, false, 136);
INSERT INTO permission VALUES (19, 118, false, false, 137);
INSERT INTO permission VALUES (20, 118, false, false, 138);
INSERT INTO permission VALUES (21, 118, false, false, 139);
INSERT INTO permission VALUES (22, 118, false, false, 140);
INSERT INTO permission VALUES (23, 118, false, false, 141);
INSERT INTO permission VALUES (24, 118, false, false, 142);


--
-- Name: permission_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('permission_seq', 1, false);


--
-- Name: product_id_product_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('product_id_product_seq', 1, false);


--
-- Name: product_type_id_product_type_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('product_type_id_product_type_seq_1', 1, false);


--
-- Data for Name: productxwarehouse; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO productxwarehouse VALUES (739, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (144, 1894, 0, 0);
INSERT INTO productxwarehouse VALUES (144, 1, 0, 0);
INSERT INTO productxwarehouse VALUES (752, 1894, 280, 280);
INSERT INTO productxwarehouse VALUES (752, 1, 4220, 4220);
INSERT INTO productxwarehouse VALUES (739, 1, 410, 410);


--
-- Data for Name: rack; Type: TABLE DATA; Schema: campis; Owner: postgres
--

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
INSERT INTO rack VALUES (2153, 1, 60, 85, 10, 1, 0);
INSERT INTO rack VALUES (543, 1894, 2, 2, 10, 2, 0);


--
-- Name: rack_id_rack_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('rack_id_rack_seq', 1, false);


--
-- Data for Name: refund; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO refund VALUES (115, 114, 'Atendido', '2017-11-14 20:56:11.707974', 'Nota de crédito');


--
-- Data for Name: refund_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO refund_line VALUES (116, 115, 3, 98);


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

INSERT INTO request_order_line VALUES (98, 10, 123, 97, 739);
INSERT INTO request_order_line VALUES (105, 7, 123, 104, 739);
INSERT INTO request_order_line VALUES (461, 11, 123, 460, 739);
INSERT INTO request_order_line VALUES (465, 2, 19.5060005187988281, 464, 144);
INSERT INTO request_order_line VALUES (466, 6, 123, 464, 739);


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

SELECT pg_catalog.setval('role_id_role_seq', 1, false);


--
-- Data for Name: sale_condition; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO sale_condition VALUES (103, '2017-11-14 00:00:00', '2017-11-22 00:00:00', 25, '1', 5, 739, 102, 1, 1);
INSERT INTO sale_condition VALUES (108, '2017-11-14 00:00:00', '2017-11-22 00:00:00', 0, '1', 4, 752, 0, 2, 1);


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

SELECT pg_catalog.setval('sale_condition_type_id_sale_condition_type_seq_1', 1, false);


--
-- Name: unit_of_measure_id_unit_of_measure_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('unit_of_measure_id_unit_of_measure_seq_1', 1, false);


--
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('users_id_user_seq', 1, false);


--
-- Name: vehicle_id_vehicle_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('vehicle_id_vehicle_seq_1', 1, false);


--
-- Name: view_id_view_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('view_id_view_seq_1', 1, false);


--
-- Name: warehouse_id_warehouse_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('warehouse_id_warehouse_seq', 1, false);


--
-- Data for Name: zone; Type: TABLE DATA; Schema: campis; Owner: postgres
--

INSERT INTO zone VALUES (455, 1, 408, 6, 2, 3, false);
INSERT INTO zone VALUES (457, 1, 408, 6, 2, 4, false);
INSERT INTO zone VALUES (440, 1, 408, 5, 3, 0, false);
INSERT INTO zone VALUES (458, 1, 408, 6, 3, 4, false);
INSERT INTO zone VALUES (456, 1, 408, 6, 3, 3, false);
INSERT INTO zone VALUES (465, 1, 408, 7, 2, 3, false);
INSERT INTO zone VALUES (450, 1, 408, 6, 3, 0, false);
INSERT INTO zone VALUES (452, 1, 408, 6, 3, 1, false);
INSERT INTO zone VALUES (454, 1, 408, 6, 3, 2, false);
INSERT INTO zone VALUES (467, 1, 408, 7, 2, 4, false);
INSERT INTO zone VALUES (473, 1, 408, 8, 2, 2, false);
INSERT INTO zone VALUES (471, 1, 408, 8, 2, 1, false);
INSERT INTO zone VALUES (469, 1, 408, 8, 2, 0, false);
INSERT INTO zone VALUES (468, 1, 408, 7, 3, 4, false);
INSERT INTO zone VALUES (475, 1, 408, 8, 2, 3, false);
INSERT INTO zone VALUES (466, 1, 408, 7, 3, 3, false);
INSERT INTO zone VALUES (464, 1, 408, 7, 3, 2, false);
INSERT INTO zone VALUES (562, 1894, 543, 6, 2, 1, false);
INSERT INTO zone VALUES (560, 1894, 543, 6, 2, 0, false);
INSERT INTO zone VALUES (559, 1894, 543, 5, 3, 1, false);
INSERT INTO zone VALUES (557, 1894, 543, 5, 3, 0, false);
INSERT INTO zone VALUES (566, 1894, 543, 7, 2, 1, false);
INSERT INTO zone VALUES (447, 1, 408, 5, 2, 4, false);
INSERT INTO zone VALUES (409, 1, 408, 2, 2, 0, true);
INSERT INTO zone VALUES (500, 1, 408, 11, 3, 0, true);
INSERT INTO zone VALUES (501, 1, 408, 11, 2, 1, true);
INSERT INTO zone VALUES (502, 1, 408, 11, 3, 1, true);
INSERT INTO zone VALUES (503, 1, 408, 11, 2, 2, true);
INSERT INTO zone VALUES (504, 1, 408, 11, 3, 2, true);
INSERT INTO zone VALUES (505, 1, 408, 11, 2, 3, true);
INSERT INTO zone VALUES (506, 1, 408, 11, 3, 3, true);
INSERT INTO zone VALUES (507, 1, 408, 11, 2, 4, true);
INSERT INTO zone VALUES (508, 1, 408, 11, 3, 4, true);
INSERT INTO zone VALUES (411, 1, 408, 2, 2, 1, true);
INSERT INTO zone VALUES (413, 1, 408, 2, 2, 2, true);
INSERT INTO zone VALUES (415, 1, 408, 2, 2, 3, true);
INSERT INTO zone VALUES (417, 1, 408, 2, 2, 4, true);
INSERT INTO zone VALUES (438, 1, 408, 4, 3, 4, true);
INSERT INTO zone VALUES (2162, 1, 2153, 74, 85, 0, true);
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
INSERT INTO zone VALUES (544, 1894, 543, 2, 2, 0, true);
INSERT INTO zone VALUES (545, 1894, 543, 2, 3, 0, true);
INSERT INTO zone VALUES (546, 1894, 543, 2, 2, 1, true);
INSERT INTO zone VALUES (547, 1894, 543, 2, 3, 1, true);
INSERT INTO zone VALUES (548, 1894, 543, 3, 2, 0, true);
INSERT INTO zone VALUES (549, 1894, 543, 3, 3, 0, true);
INSERT INTO zone VALUES (550, 1894, 543, 3, 2, 1, true);
INSERT INTO zone VALUES (551, 1894, 543, 3, 3, 1, true);
INSERT INTO zone VALUES (552, 1894, 543, 4, 2, 0, true);
INSERT INTO zone VALUES (553, 1894, 543, 4, 3, 0, true);
INSERT INTO zone VALUES (554, 1894, 543, 4, 2, 1, true);
INSERT INTO zone VALUES (555, 1894, 543, 4, 3, 1, true);
INSERT INTO zone VALUES (556, 1894, 543, 5, 2, 0, true);
INSERT INTO zone VALUES (558, 1894, 543, 5, 2, 1, true);
INSERT INTO zone VALUES (1854, 1, 1793, 46, 40, 0, true);
INSERT INTO zone VALUES (1855, 1, 1793, 46, 41, 0, true);
INSERT INTO zone VALUES (1856, 1, 1793, 46, 40, 1, true);
INSERT INTO zone VALUES (1857, 1, 1793, 46, 41, 1, true);
INSERT INTO zone VALUES (1858, 1, 1793, 46, 40, 2, true);
INSERT INTO zone VALUES (1859, 1, 1793, 46, 41, 2, true);
INSERT INTO zone VALUES (2161, 1, 2153, 73, 86, 0, true);
INSERT INTO zone VALUES (2164, 1, 2153, 75, 85, 0, true);
INSERT INTO zone VALUES (2163, 1, 2153, 74, 86, 0, true);
INSERT INTO zone VALUES (2166, 1, 2153, 76, 85, 0, true);
INSERT INTO zone VALUES (2165, 1, 2153, 75, 86, 0, true);
INSERT INTO zone VALUES (2189, 1, 2184, 72, 70, 0, true);
INSERT INTO zone VALUES (567, 1894, 543, 7, 3, 1, false);
INSERT INTO zone VALUES (565, 1894, 543, 7, 3, 0, false);
INSERT INTO zone VALUES (564, 1894, 543, 7, 2, 0, false);
INSERT INTO zone VALUES (2188, 1, 2184, 71, 71, 0, true);
INSERT INTO zone VALUES (2191, 1, 2184, 73, 70, 0, true);
INSERT INTO zone VALUES (2190, 1, 2184, 72, 71, 0, true);
INSERT INTO zone VALUES (2193, 1, 2184, 74, 70, 0, true);
INSERT INTO zone VALUES (2192, 1, 2184, 73, 71, 0, true);
INSERT INTO zone VALUES (2195, 1, 2184, 75, 70, 0, true);
INSERT INTO zone VALUES (2194, 1, 2184, 74, 71, 0, true);
INSERT INTO zone VALUES (2197, 1, 2184, 76, 70, 0, true);
INSERT INTO zone VALUES (2196, 1, 2184, 75, 71, 0, true);
INSERT INTO zone VALUES (2199, 1, 2184, 77, 70, 0, true);
INSERT INTO zone VALUES (563, 1894, 543, 6, 3, 1, false);
INSERT INTO zone VALUES (561, 1894, 543, 6, 3, 0, false);
INSERT INTO zone VALUES (2198, 1, 2184, 76, 71, 0, true);
INSERT INTO zone VALUES (2201, 1, 2184, 78, 70, 0, true);
INSERT INTO zone VALUES (2200, 1, 2184, 77, 71, 0, true);
INSERT INTO zone VALUES (2203, 1, 2184, 79, 70, 0, true);
INSERT INTO zone VALUES (2202, 1, 2184, 78, 71, 0, true);
INSERT INTO zone VALUES (2204, 1, 2184, 79, 71, 0, true);
INSERT INTO zone VALUES (449, 1, 408, 6, 2, 0, false);
INSERT INTO zone VALUES (453, 1, 408, 6, 2, 2, false);
INSERT INTO zone VALUES (451, 1, 408, 6, 2, 1, false);
INSERT INTO zone VALUES (480, 1, 408, 9, 3, 0, false);
INSERT INTO zone VALUES (2186, 1, 2184, 70, 71, 0, true);
INSERT INTO zone VALUES (2185, 1, 2184, 70, 70, 0, true);
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
INSERT INTO zone VALUES (2168, 1, 2153, 77, 85, 0, true);
INSERT INTO zone VALUES (2154, 1, 2153, 70, 85, 0, true);
INSERT INTO zone VALUES (2156, 1, 2153, 71, 85, 0, true);
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
INSERT INTO zone VALUES (2229, 1, 2226, 71, 15, 0, true);
INSERT INTO zone VALUES (2230, 1, 2226, 71, 16, 0, true);
INSERT INTO zone VALUES (2231, 1, 2226, 72, 15, 0, true);
INSERT INTO zone VALUES (2232, 1, 2226, 72, 16, 0, true);
INSERT INTO zone VALUES (2233, 1, 2226, 73, 15, 0, true);
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
INSERT INTO zone VALUES (2167, 1, 2153, 76, 86, 0, true);
INSERT INTO zone VALUES (2170, 1, 2153, 78, 85, 0, true);
INSERT INTO zone VALUES (2155, 1, 2153, 70, 86, 0, true);
INSERT INTO zone VALUES (2158, 1, 2153, 72, 85, 0, true);
INSERT INTO zone VALUES (2157, 1, 2153, 71, 86, 0, true);
INSERT INTO zone VALUES (2160, 1, 2153, 73, 85, 0, true);
INSERT INTO zone VALUES (2159, 1, 2153, 72, 86, 0, true);
INSERT INTO zone VALUES (2275, 1, 2268, 73, 45, 0, true);
INSERT INTO zone VALUES (2187, 1, 2184, 71, 70, 0, true);
INSERT INTO zone VALUES (2227, 1, 2226, 70, 15, 0, true);
INSERT INTO zone VALUES (2228, 1, 2226, 70, 16, 0, true);
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
INSERT INTO zone VALUES (423, 1, 408, 3, 2, 2, true);
INSERT INTO zone VALUES (425, 1, 408, 3, 2, 3, true);
INSERT INTO zone VALUES (427, 1, 408, 3, 2, 4, true);
INSERT INTO zone VALUES (420, 1, 408, 3, 3, 0, true);
INSERT INTO zone VALUES (422, 1, 408, 3, 3, 1, true);
INSERT INTO zone VALUES (424, 1, 408, 3, 3, 2, true);
INSERT INTO zone VALUES (426, 1, 408, 3, 3, 3, true);
INSERT INTO zone VALUES (428, 1, 408, 3, 3, 4, true);
INSERT INTO zone VALUES (429, 1, 408, 4, 2, 0, true);
INSERT INTO zone VALUES (431, 1, 408, 4, 2, 1, true);
INSERT INTO zone VALUES (433, 1, 408, 4, 2, 2, true);
INSERT INTO zone VALUES (435, 1, 408, 4, 2, 3, true);
INSERT INTO zone VALUES (437, 1, 408, 4, 2, 4, true);
INSERT INTO zone VALUES (439, 1, 408, 5, 2, 0, true);
INSERT INTO zone VALUES (441, 1, 408, 5, 2, 1, true);
INSERT INTO zone VALUES (443, 1, 408, 5, 2, 2, true);
INSERT INTO zone VALUES (430, 1, 408, 4, 3, 0, true);
INSERT INTO zone VALUES (432, 1, 408, 4, 3, 1, true);
INSERT INTO zone VALUES (434, 1, 408, 4, 3, 2, true);
INSERT INTO zone VALUES (436, 1, 408, 4, 3, 3, true);
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
INSERT INTO zone VALUES (571, 1894, 543, 8, 3, 1, false);
INSERT INTO zone VALUES (569, 1894, 543, 8, 3, 0, false);
INSERT INTO zone VALUES (570, 1894, 543, 8, 2, 1, false);
INSERT INTO zone VALUES (568, 1894, 543, 8, 2, 0, false);
INSERT INTO zone VALUES (463, 1, 408, 7, 2, 2, false);
INSERT INTO zone VALUES (459, 1, 408, 7, 2, 0, false);
INSERT INTO zone VALUES (461, 1, 408, 7, 2, 1, false);
INSERT INTO zone VALUES (445, 1, 408, 5, 2, 3, true);
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
INSERT INTO zone VALUES (446, 1, 408, 5, 3, 3, false);
INSERT INTO zone VALUES (442, 1, 408, 5, 3, 1, false);
INSERT INTO zone VALUES (448, 1, 408, 5, 3, 4, false);
INSERT INTO zone VALUES (444, 1, 408, 5, 3, 2, false);
INSERT INTO zone VALUES (462, 1, 408, 7, 3, 1, false);
INSERT INTO zone VALUES (460, 1, 408, 7, 3, 0, false);
INSERT INTO zone VALUES (477, 1, 408, 8, 2, 4, false);
INSERT INTO zone VALUES (478, 1, 408, 8, 3, 4, false);
INSERT INTO zone VALUES (474, 1, 408, 8, 3, 2, false);
INSERT INTO zone VALUES (472, 1, 408, 8, 3, 1, false);
INSERT INTO zone VALUES (470, 1, 408, 8, 3, 0, false);
INSERT INTO zone VALUES (487, 1, 408, 9, 2, 4, false);
INSERT INTO zone VALUES (476, 1, 408, 8, 3, 3, false);
INSERT INTO zone VALUES (485, 1, 408, 9, 2, 3, false);
INSERT INTO zone VALUES (483, 1, 408, 9, 2, 2, false);
INSERT INTO zone VALUES (481, 1, 408, 9, 2, 1, false);
INSERT INTO zone VALUES (479, 1, 408, 9, 2, 0, false);
INSERT INTO zone VALUES (486, 1, 408, 9, 3, 3, false);
INSERT INTO zone VALUES (488, 1, 408, 9, 3, 4, false);
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
INSERT INTO zone VALUES (484, 1, 408, 9, 3, 2, false);
INSERT INTO zone VALUES (579, 1894, 543, 10, 3, 1, false);
INSERT INTO zone VALUES (577, 1894, 543, 10, 3, 0, false);
INSERT INTO zone VALUES (581, 1894, 543, 11, 3, 0, false);
INSERT INTO zone VALUES (583, 1894, 543, 11, 3, 1, false);
INSERT INTO zone VALUES (574, 1894, 543, 9, 2, 1, false);
INSERT INTO zone VALUES (572, 1894, 543, 9, 2, 0, false);
INSERT INTO zone VALUES (575, 1894, 543, 9, 3, 1, false);
INSERT INTO zone VALUES (573, 1894, 543, 9, 3, 0, false);
INSERT INTO zone VALUES (482, 1, 408, 9, 3, 1, false);
INSERT INTO zone VALUES (576, 1894, 543, 10, 2, 0, true);
INSERT INTO zone VALUES (578, 1894, 543, 10, 2, 1, true);
INSERT INTO zone VALUES (580, 1894, 543, 11, 2, 0, true);
INSERT INTO zone VALUES (582, 1894, 543, 11, 2, 1, true);
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
INSERT INTO zone VALUES (2408, 1, 2394, 61, 2, 0, true);
INSERT INTO zone VALUES (2409, 1, 2394, 62, 1, 0, true);
INSERT INTO zone VALUES (2410, 1, 2394, 62, 2, 0, true);
INSERT INTO zone VALUES (2411, 1, 2394, 63, 1, 0, true);
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
INSERT INTO zone VALUES (2169, 1, 2153, 77, 86, 0, true);
INSERT INTO zone VALUES (2172, 1, 2153, 79, 85, 0, true);
INSERT INTO zone VALUES (2171, 1, 2153, 78, 86, 0, true);
INSERT INTO zone VALUES (2173, 1, 2153, 79, 86, 0, true);
INSERT INTO zone VALUES (410, 1, 408, 2, 3, 0, true);
INSERT INTO zone VALUES (412, 1, 408, 2, 3, 1, true);
INSERT INTO zone VALUES (414, 1, 408, 2, 3, 2, true);
INSERT INTO zone VALUES (416, 1, 408, 2, 3, 3, true);
INSERT INTO zone VALUES (418, 1, 408, 2, 3, 4, true);
INSERT INTO zone VALUES (419, 1, 408, 3, 2, 0, true);
INSERT INTO zone VALUES (421, 1, 408, 3, 2, 1, true);


--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('zone_id_zone_seq_1', 1, false);


--
-- PostgreSQL database dump complete
--

