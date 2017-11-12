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

COPY product_type (id_product_type, description) FROM stdin;
1	TipoProd1
81	UltimaaPrueba
83	Pruebaid
\.


--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY warehouse (id_warehouse, name, length, width, status) FROM stdin;
1	Almacen1	100	100	t
\.


--
-- Data for Name: area; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY area (id_area, name, length, width, pos_x, pos_y, id_warehouse, product_type) FROM stdin;
1	test_area1	10	10	0	0	1	1
2	test_area2	10	10	0	0	1	83
3	test_area3	10	10	0	0	1	81
778	area1	23	13	891	123	1	1
\.


--
-- Name: area_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('area_id_seq', 1, false);


--
-- Data for Name: unit_of_measure; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY unit_of_measure (id_unit_of_measure, description, descrip) FROM stdin;
1	Kilogramos	\N
2	Litros	\N
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY product (id_product, name, description, p_stock, c_stock, weight, trademark, base_price, id_unit_of_measure, id_product_type, max_qt) FROM stdin;
23	Fierros	Construccion.	-7	-18	210	SOL	95.1999969482421875	1	1	40
32	Salio	pls pls pls pls pls pls.	165	165	666	Campis	111	1	1	40
30	ProductoD	Producto de prueba para ver si funciona esto lol	13	12	12	Pls	10	1	1	40
31	ProductoNIce	shits	193	188	1234	Evian	13	2	1	40
739	ProductoSuenho	un producto bien xvr	11	11	12	FLowynais	123	2	81	40
752	Clavos	Caja de clavos simple.	1	1	1	Pablito	8	1	83	100
3	Arena Fina	Arean fina para construccion.	13	-1	36	SOL	165.199999999999989	1	1	40
\.


--
-- Data for Name: batch; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY batch (id_batch, quantity, batch_cost, arrival_date, expiration_date, id_product, type_batch, state, id_unit, heritage, location) FROM stdin;
14	3	15	2017-11-04 04:00:04.394618	2017-11-04 00:00:00	23	5	t	-1	\N	\N
1	1	1	2017-11-04 00:03:33.952473	2017-11-04 00:03:33.952473	3	3	t	-1	\N	\N
2	1	1	2017-11-04 00:03:43.849983	2017-11-04 00:03:43.849983	3	3	t	-1	\N	\N
3	1	1	2017-11-04 00:03:51.268802	2017-11-04 00:03:51.268802	23	3	t	-1	\N	\N
606	50	599.989990234375	2017-12-07 00:00:00	2017-12-30 00:00:00	30	1	t	\N	\N	\N
607	6	10	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	30	3	t	\N	--	\N
608	6	10	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	30	3	t	\N	--	\N
609	6	10	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	30	3	t	\N	--	\N
610	6	10	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	30	3	t	\N	--	\N
611	6	10	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	30	3	t	\N	--	\N
612	6	10	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	30	3	t	\N	--	\N
613	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
614	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
615	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
616	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
617	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
618	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
575	5	165	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	3	3	t	\N	--	\N
576	5	165	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	3	3	t	\N	--	\N
577	5	165	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	3	3	t	\N	--	\N
578	5	165	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	3	3	t	\N	--	\N
579	5	165	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	3	3	t	\N	--	\N
580	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
581	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
582	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
583	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
584	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
585	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
586	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
587	8	95	2017-11-07 04:43:58.388	2017-11-07 04:43:58.388	23	3	t	\N	--	\N
619	7	13	2017-11-07 15:33:14.062	2017-11-07 15:33:14.062	31	3	t	\N	--	\N
633	60	515	2017-12-07 00:00:00	2017-12-07 00:00:00	23	4	t	\N	\N	\N
634	60	515	2017-12-07 00:00:00	2017-12-07 00:00:00	23	4	t	\N	\N	\N
635	60	515	2017-12-07 00:00:00	2017-12-07 00:00:00	23	4	t	\N	\N	\N
636	850	616	2017-12-07 00:00:00	2017-12-07 00:00:00	3	4	t	\N	\N	\N
637	500	544	2017-12-07 00:00:00	2017-12-22 00:00:00	23	1	t	\N	\N	\N
647	56	85	2017-12-28 00:00:00	2017-12-29 00:00:00	32	4	t	\N	--	\N
648	512	235	2017-12-28 00:00:00	2017-12-30 00:00:00	31	4	t	\N	--	\N
649	231	515	2017-12-29 00:00:00	2017-12-30 00:00:00	31	4	t	\N	--	\N
654	12	13	2017-11-07 19:17:19.363	2017-11-07 19:17:19.363	31	3	t	\N	--	\N
664	17	13	2017-11-07 19:29:03.596	2017-11-07 19:29:03.596	31	3	t	\N	--	\N
676	10	95	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	23	3	t	\N	--	\N
677	10	95	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	23	3	t	\N	--	\N
678	10	95	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	23	3	t	\N	--	\N
679	10	95	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	23	3	t	\N	--	\N
680	10	95	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	23	3	t	\N	--	\N
681	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
682	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
683	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
684	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
685	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
686	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
687	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
688	5	13	2017-11-07 20:12:09.752	2017-11-07 20:12:09.752	31	3	t	\N	--	\N
714	123	13	2017-11-07 20:47:08.669	2017-11-07 20:47:08.669	31	3	t	\N	--	\N
716	-70	95	2017-11-07 20:49:51.042	2017-11-07 20:49:51.042	23	3	t	\N	--	\N
718	152	62	2017-12-08 00:00:00	2017-12-29 00:00:00	32	3	t	\N	--	\N
779	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
726	10	10	2017-12-09 00:00:00	2018-01-30 00:00:00	3	2	t	\N	--	-1
640	654	986	2017-12-21 00:00:00	2017-12-29 00:00:00	23	5	t	\N	\N	\N
639	156	365	2017-12-07 00:00:00	2017-12-07 00:00:00	3	5	t	\N	\N	\N
638	65	986	2017-12-07 00:00:00	2017-12-14 00:00:00	3	5	t	\N	\N	\N
740	10	123	2017-11-10 02:14:36.211	2017-11-10 02:14:36.211	739	3	t	\N	--	1
673	10	36	2017-12-07 00:00:00	2017-12-24 00:00:00	23	2	t	\N	--	\N
780	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
773	20	8	2017-11-12 02:13:19.453	2017-11-12 02:13:19.453	752	3	t	\N	--	1
644	513	325	2017-12-27 00:00:00	2017-12-27 00:00:00	3	2	t	\N	--	\N
643	52	352	2017-12-28 00:00:00	2017-12-28 00:00:00	30	2	t	\N	--	\N
605	20	550.989990234375	2017-12-07 00:00:00	2018-03-03 00:00:00	3	2	t	\N	\N	\N
781	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
782	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
783	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
784	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
785	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
786	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
787	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
788	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
789	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
790	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
791	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
792	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
793	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
794	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
795	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
796	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
797	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
798	1	8	2017-11-12 16:09:35.426	2017-11-12 16:09:35.426	752	3	t	\N	--	1
819	13	111	2017-11-12 16:20:32.342	2017-11-12 16:20:32.342	32	3	t	\N	--	1
821	55	123	2017-11-12 16:31:31.334	2017-11-12 16:31:31.334	739	3	t	\N	--	1
823	90	10	2017-11-12 17:14:39.644	2017-11-12 17:14:39.644	30	3	t	\N	--	1
824	33	13	2017-11-12 17:14:39.644	2017-11-12 17:14:39.644	31	3	t	\N	--	1
\.


--
-- Name: batch_id_batch_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('batch_id_batch_seq', 14, true);


--
-- Data for Name: campaign; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY campaign (id_campaign, name, description, initial_date, final_date) FROM stdin;
0	Ninguna	No existe una campaña asociada para los descuentos	1969-12-31 19:00:00	1969-12-31 19:00:00
749	NewCampein	Nice nice nice nice nice	2018-01-15 00:00:00	2018-01-31 00:00:00
1	Camp100	Primera. creada en este proyecto. Huehuehuehuehuehueh nice. xddd	2009-05-30 00:00:00	2013-06-30 00:00:00
750	Verano2018	Descuentos y promociones de verano 2018	2018-02-01 00:00:00	2018-04-03 00:00:00
\.


--
-- Name: campaign_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('campaign_id_seq', 1, false);


--
-- Data for Name: client; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY client (id_client, name, dni, ruc, active, address, phone, email, id_district) FROM stdin;
1	Benito Juarez	48296679	12354687952	t	Los naranjos 123	555-3462	benito07@gmail.com	\N
57	teest	12321	1232122	t	2121123	333	test@test.com	\N
712	656+6+	zdgfsd	adfsd	t	sdfsdf	fasdfsd	sdf	\N
2	Julio Roman	32548647	16317746542	t	12312312312321312	665-6485	julior@gmail.com	\N
745	odebrecht		123123123	t			ode@ode.com	\N
746	cliente 123		123	t		test@test.com		\N
748	gloria		123	t			glo@ria.com	\N
747	cliente cliente	7070707		t			test@test.com	\N
\.


--
-- Name: client_id_client_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('client_id_client_seq', 2, true);


--
-- Data for Name: request_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY request_order (id_request_order, creation_date, delivery_date, base_amount, total_amount, status, id_client, priority) FROM stdin;
40	2017-12-01 00:00:00	2017-12-28 00:00:00	555	555	EN PROGRESO	2	2
37	2017-12-01 00:00:00	2017-12-04 00:00:00	826	826	EN PROGRESO	1	1
42	2017-12-01 00:00:00	2017-12-15 00:00:00	476	476	CANCELADO	2	1
41	2017-12-01 00:00:00	2017-12-22 00:00:00	660.79998779296875	660.79998779296875	EN PROGRESO	1	3
50	2018-04-01 00:00:00	2018-04-15 00:00:00	941.39996337890625	941.39996337890625	EN PROGRESO	1	3
49	2017-12-01 00:00:00	2017-12-22 00:00:00	1195.4000244140625	1195.4000244140625	CANCELADO	1	2
721	2017-12-09 00:00:00	2017-12-13 00:00:00	39	39	EN PROGRESO	2	2
722	2017-12-10 00:00:00	2017-12-14 00:00:00	261	261	EN PROGRESO	57	1
724	2017-12-10 00:00:00	2017-12-13 00:00:00	13	13	EN PROGRESO	57	1
725	2017-12-10 00:00:00	2017-12-15 00:00:00	20	20	ENTREGADO	1	1
728	2017-12-10 00:00:00	2017-12-12 00:00:00	36	36	ENTREGADO	1	1
753	2017-12-11 00:00:00	2017-12-13 00:00:00	660.79998779296875	8.260009765625	EN PROGRESO	748	1
827	2017-12-13 00:00:00	2017-12-30 00:00:00	1720	247	EN PROGRESO	746	1
832	2017-12-13 00:00:00	2017-12-30 00:00:00	1720	247	EN PROGRESO	746	1
\.


--
-- Data for Name: complaint; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY complaint (id_complaint, description, status, id_request_order) FROM stdin;
61	ttest	Aceptado	42
66	hols	Rechazado	50
67	otra prueba	En trámite	37
85	real	Aceptado	50
93	prueba final	Aceptado	50
98	reclamo valido	Aceptado	50
707	se rompio 1tornillo	Aceptado	50
\.


--
-- Name: disclaim_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('disclaim_seq', 1, false);


--
-- Data for Name: dispatch_move; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY dispatch_move (id_dispatch_move, type_owner, id_owner, mov_date, reason, id_batch, arrival_date) FROM stdin;
65	2	57	2017-11-04 02:15:21.101	1	14	2017-11-04 04:59:23.021
60	2	1	2017-11-02 04:46:48.638	2	14	2017-11-04 04:59:23.021
74	1	3	2017-11-05 20:23:10.176	2	14	2017-11-04 04:59:23.021
69	0	0	2017-11-04 04:59:24.382011	1	14	2017-11-04 04:59:23.021
75	1	4	2017-11-05 20:31:09.558	2	14	2017-11-04 04:59:23.021
92	2	1	2017-11-06 02:53:34.06	1	14	2017-11-04 04:59:23.021
76	1	25	2017-11-05 20:40:34.34	0	14	2017-11-04 04:59:23.021
91	2	1	2017-11-06 02:49:59.286	1	14	2017-11-04 04:59:23.021
84	2	1	2017-11-06 02:34:04.764	1	14	2017-11-04 04:59:23.021
641	3	0	2017-11-07 16:28:55.052	3	640	2017-12-07 00:00:00
642	3	0	2017-11-07 16:28:55.052	3	640	2017-12-21 00:00:00
645	3	0	2017-11-07 16:45:03.337	3	644	2017-12-28 00:00:00
646	3	0	2017-11-07 16:45:03.337	3	644	2017-12-27 00:00:00
674	3	0	2017-11-07 20:07:52.57	3	673	2017-12-07 00:00:00
675	1	1	2017-11-07 20:09:08.627	2	673	2017-11-07 20:09:08.627
719	4	1	2017-11-08 19:34:54.962	4	718	2017-12-08 00:00:00
727	3	0	2017-11-09 18:38:30.429	3	726	2017-12-09 00:00:00
731	1	1	2017-11-09 19:21:57.461	0	644	2017-11-09 19:21:57.461
732	1	1	2017-11-09 19:41:30.3	2	726	2017-11-09 19:41:30.3
733	1	1	2017-11-09 19:55:59.583	2	726	2017-11-09 19:55:59.583
734	1	1	2017-11-09 20:08:52.573	2	726	2017-11-09 20:08:52.573
735	1	1	2017-11-09 20:22:05.935	2	726	2017-11-09 20:22:05.935
736	2	1	2017-11-09 23:39:03.605	1	640	2017-11-09 23:39:03.605
737	2	1	2017-11-09 23:39:11.232	1	639	2017-11-09 23:39:11.232
738	2	1	2017-11-09 23:39:18.482	1	638	2017-11-09 23:39:18.482
769	1	1	2017-11-11 16:01:00.692	2	673	2017-11-11 16:01:00.692
775	1	1	2017-11-12 04:27:54.996	2	644	2017-11-12 04:27:54.996
776	1	1	2017-11-12 04:28:11.979	2	643	2017-11-12 04:28:11.979
777	1	1	2017-11-12 04:28:30.179	2	605	2017-11-12 04:28:30.179
\.


--
-- Data for Name: dispatch_order; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY dispatch_order (id_dispatch_order, id_request_order, priority, status, id_prod, quantity) FROM stdin;
1	50	1	EN PROGRESO	\N	\N
\.


--
-- Name: dispatch_order_id_dispatch_order_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_id_dispatch_order_seq', 1, false);


--
-- Data for Name: dispatch_order_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY dispatch_order_line (id_dispatch_order_line, id_dispatch_order, id_product, quantity) FROM stdin;
2	1	23	4
1	1	3	3
\.


--
-- Name: dispatch_order_line_id_dispatch_order_line_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('dispatch_order_line_id_dispatch_order_line_seq', 1, false);


--
-- Data for Name: district; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY district (id_district, name, freight) FROM stdin;
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
1	Cercado de Lima	0.0900000035762786865
\.


--
-- Name: district_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('district_id_seq', 1, false);


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

COPY group_batch (id_group_batch, arrival_date, id_owner, reason, type_owner) FROM stdin;
\.


--
-- Name: group_batch_id_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('group_batch_id_seq', 3, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 836, true);


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
762	vendedor1
764	vendedor3
765	vendedor2
766	final
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY users (id_user, firstname, lastname, password, email, created_at, active, id_role, updated_at, username) FROM stdin;
21	Miguel	Guanira	123456	guani@ra.com	2017-10-27 23:53:16.454	t	1	2017-10-27 23:53:16.454	mguani
29	galway	girl	doritos	galwaygirl@pucp.pe	2017-10-28 21:24:49.616	t	1	2017-10-28 21:24:49.616	galwaygirl
12	Test2	test	qwer	qwer	2017-10-26 02:55:34.076	t	14	2017-10-26 02:55:34.076	qwer
744	Raul	Romero	123456	test@test.com	2017-11-10 11:30:47.67	t	1	2017-11-10 11:30:47.67	rromero
743	Mario		123456	mario@mario.com	2017-11-10 11:14:09.168	f	1	2017-11-10 11:14:09.168	mariom
\.


--
-- Data for Name: vehicle; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY vehicle (id_vehicle, max_weight, speed, active, id_warehouse, plate) FROM stdin;
2	800.799999999999955	54	t	1	123-ASD
33	1230	100	t	1	AAA-123
\.


--
-- Data for Name: movement; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY movement (id_movement, mov_date, id_user, quantity, id_vehicle, mov_type, id_warehouse, id_zone, id_batch) FROM stdin;
588	2017-11-07 04:44:12.536	21	5	2	1	1	417	575
589	2017-11-07 04:44:13.217	21	5	2	1	1	415	576
590	2017-11-07 04:44:13.314	21	5	2	1	1	413	577
591	2017-11-07 04:44:13.417	21	5	2	1	1	411	578
592	2017-11-07 04:44:13.526	21	5	2	1	1	409	579
593	2017-11-07 04:44:13.634	21	8	2	1	1	427	580
594	2017-11-07 04:44:13.724	21	8	2	1	1	425	581
595	2017-11-07 04:44:13.836	21	8	2	1	1	423	582
596	2017-11-07 04:44:13.955	21	8	2	1	1	421	583
597	2017-11-07 04:44:14.055	21	8	2	1	1	419	584
598	2017-11-07 04:44:14.174	21	8	2	1	1	418	585
599	2017-11-07 04:44:14.274	21	8	2	1	1	416	586
600	2017-11-07 04:44:14.377	21	8	2	1	1	414	587
620	2017-11-07 15:33:45.593	21	6	2	1	1	412	607
621	2017-11-07 15:33:45.736	21	6	2	1	1	410	608
622	2017-11-07 15:33:45.753	21	6	2	1	1	437	609
623	2017-11-07 15:33:45.763	21	6	2	1	1	435	610
624	2017-11-07 15:33:45.786	21	6	2	1	1	433	611
625	2017-11-07 15:33:45.794	21	6	2	1	1	431	612
626	2017-11-07 15:33:45.824	21	7	2	1	1	429	613
627	2017-11-07 15:33:45.837	21	7	2	1	1	428	614
628	2017-11-07 15:33:45.845	21	7	2	1	1	426	615
629	2017-11-07 15:33:45.866	21	7	2	1	1	424	616
630	2017-11-07 15:33:45.873	21	7	2	1	1	422	617
631	2017-11-07 15:33:45.888	21	7	2	1	1	420	618
632	2017-11-07 15:33:45.895	21	7	2	1	1	447	619
655	2017-11-07 19:17:41.303	21	12	2	1	1	445	654
665	2017-11-07 19:29:12.417	21	17	2	1	1	443	664
689	2017-11-07 20:13:37.12	21	10	2	1	1	441	676
690	2017-11-07 20:13:37.164	21	10	2	1	1	439	677
691	2017-11-07 20:13:37.172	21	10	2	1	1	438	678
692	2017-11-07 20:13:37.178	21	10	2	1	1	436	679
693	2017-11-07 20:13:37.185	21	10	2	1	1	434	680
694	2017-11-07 20:13:37.192	21	5	2	1	1	432	681
695	2017-11-07 20:13:37.198	21	5	2	1	1	430	682
696	2017-11-07 20:13:37.206	21	5	2	1	1	457	683
697	2017-11-07 20:13:37.212	21	5	2	1	1	455	684
698	2017-11-07 20:13:37.219	21	5	2	1	1	453	685
699	2017-11-07 20:13:37.228	21	5	2	1	1	451	686
700	2017-11-07 20:13:37.236	21	5	2	1	1	449	687
701	2017-11-07 20:13:37.242	21	5	2	1	1	448	688
720	2017-11-08 19:35:50.012	21	152	2	1	1	442	718
741	2017-11-10 02:14:50.193	21	10	2	1	1	440	740
774	2017-11-12 02:16:44.536	21	20	2	1	1	467	771
799	2017-11-12 16:11:30.399	21	1	2	1	1	465	779
800	2017-11-12 16:11:31.54	21	1	2	1	1	463	780
801	2017-11-12 16:11:31.687	21	1	2	1	1	461	781
802	2017-11-12 16:11:31.781	21	1	2	1	1	459	782
803	2017-11-12 16:11:31.857	21	1	2	1	1	458	783
804	2017-11-12 16:11:31.985	21	1	2	1	1	456	784
805	2017-11-12 16:11:32.041	21	1	2	1	1	454	785
806	2017-11-12 16:11:32.087	21	1	2	1	1	452	786
807	2017-11-12 16:11:32.131	21	1	2	1	1	450	787
808	2017-11-12 16:11:32.183	21	1	2	1	1	477	788
809	2017-11-12 16:11:32.223	21	1	2	1	1	475	789
810	2017-11-12 16:11:32.266	21	1	2	1	1	473	790
811	2017-11-12 16:11:32.306	21	1	2	1	1	471	791
812	2017-11-12 16:11:32.347	21	1	2	1	1	469	792
813	2017-11-12 16:11:32.384	21	1	2	1	1	468	793
814	2017-11-12 16:11:32.431	21	1	2	1	1	466	794
815	2017-11-12 16:11:32.477	21	1	2	1	1	464	795
816	2017-11-12 16:11:32.531	21	1	2	1	1	462	796
817	2017-11-12 16:11:32.581	21	1	2	1	1	460	797
818	2017-11-12 16:11:32.621	21	1	2	1	1	487	798
820	2017-11-12 16:21:05.198	21	13	2	1	1	485	819
822	2017-11-12 16:31:37.338	21	55	2	1	1	483	821
825	2017-11-12 17:15:52.116	21	90	2	1	1	481	823
826	2017-11-12 17:15:52.337	21	33	2	1	1	479	824
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
-- Data for Name: view; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY view (id_view, description) FROM stdin;
1	products
2	warehouse
\.


--
-- Data for Name: permission; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY permission (id_view, id_role, visualize, modify, id_permission) FROM stdin;
2	14	t	f	3
1	14	f	t	2
1	766	f	f	767
2	766	f	f	768
1	1	t	f	4
2	1	t	f	5
\.


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

COPY productxwarehouse (id_product, id_warehouse, p_stock, c_stock) FROM stdin;
752	1	20	20
32	1	13	13
23	1	55	55
3	1	55	55
31	1	33	24
739	1	55	44
30	1	90	65
\.


--
-- Data for Name: rack; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY rack (id_rack, id_warehouse, pos_x, pos_y, n_columns, n_floors, orientation) FROM stdin;
5	1	14	5	10	5	0
6	1	26	5	10	5	0
7	1	2	8	10	5	0
8	1	14	8	10	5	0
408	1	2	2	10	5	0
\.


--
-- Name: rack_id_rack_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('rack_id_rack_seq', 9, true);


--
-- Data for Name: refund; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY refund (id_refund, id_complaint, status, creation_date, type_refund) FROM stdin;
64	61	En proceso	\N	Nota de crédito
87	85	En proceso	2017-11-06 02:48:52.032011	Físico
94	93	En proceso	2017-11-06 02:54:01.262446	Físico
99	98	Entregado	2017-11-06 02:59:05.326527	Nota de crédito
708	707	\N	2017-11-07 20:30:18.530348	Nota de crédito
\.


--
-- Data for Name: refund_line; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY refund_line (id_refund_line, id_refund, quantity, id_request_order_line) FROM stdin;
100	99	1	51
101	99	1	52
102	99	1	56
709	708	1	51
710	708	1	52
711	708	1	56
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
723	3	13	722	31
729	1	10	728	30
730	2	13	728	31
754	3	165.199996948242188	753	3
755	1	165.199996948242188	753	3
833	12	10	832	30
834	9	13	832	31
835	11	123	832	739
836	13	10	832	30
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
-- Name: role_id_role_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('role_id_role_seq', 7, true);


--
-- Data for Name: sale_condition; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY sale_condition (id_sale_condition, initial_date, final_date, amount, id_sale_condition_type, limits, id_to_take, id_campaign) FROM stdin;
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

SELECT pg_catalog.setval('view_id_view_seq_1', 2, true);


--
-- Name: warehouse_id_warehouse_seq; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('warehouse_id_warehouse_seq', 4, true);


--
-- Data for Name: zone; Type: TABLE DATA; Schema: campis; Owner: postgres
--

COPY zone (id_zone, id_warehouse, id_rack, pos_x, pos_y, pos_z, free) FROM stdin;
470	1	408	8	3	0	t
472	1	408	8	3	1	t
474	1	408	8	3	2	t
476	1	408	8	3	3	t
478	1	408	8	3	4	t
480	1	408	9	3	0	t
482	1	408	9	3	1	t
484	1	408	9	3	2	t
486	1	408	9	3	3	t
488	1	408	9	3	4	t
489	1	408	10	2	0	t
490	1	408	10	3	0	t
491	1	408	10	2	1	t
492	1	408	10	3	1	t
493	1	408	10	2	2	t
494	1	408	10	3	2	t
495	1	408	10	2	3	t
496	1	408	10	3	3	t
497	1	408	10	2	4	t
498	1	408	10	3	4	t
499	1	408	11	2	0	t
500	1	408	11	3	0	t
501	1	408	11	2	1	t
502	1	408	11	3	1	t
503	1	408	11	2	2	t
504	1	408	11	3	2	t
505	1	408	11	2	3	t
506	1	408	11	3	3	t
507	1	408	11	2	4	t
508	1	408	11	3	4	t
417	1	408	2	2	4	f
415	1	408	2	2	3	f
413	1	408	2	2	2	f
411	1	408	2	2	1	f
409	1	408	2	2	0	f
427	1	408	3	2	4	f
425	1	408	3	2	3	f
423	1	408	3	2	2	f
421	1	408	3	2	1	f
419	1	408	3	2	0	f
418	1	408	2	3	4	f
416	1	408	2	3	3	f
414	1	408	2	3	2	f
412	1	408	2	3	1	f
410	1	408	2	3	0	f
437	1	408	4	2	4	f
435	1	408	4	2	3	f
433	1	408	4	2	2	f
431	1	408	4	2	1	f
429	1	408	4	2	0	f
428	1	408	3	3	4	f
426	1	408	3	3	3	f
424	1	408	3	3	2	f
422	1	408	3	3	1	f
420	1	408	3	3	0	f
447	1	408	5	2	4	f
445	1	408	5	2	3	f
436	1	408	4	3	3	f
434	1	408	4	3	2	f
432	1	408	4	3	1	f
430	1	408	4	3	0	f
443	1	408	5	2	2	f
441	1	408	5	2	1	f
439	1	408	5	2	0	f
438	1	408	4	3	4	f
457	1	408	6	2	4	f
455	1	408	6	2	3	f
453	1	408	6	2	2	f
451	1	408	6	2	1	f
449	1	408	6	2	0	f
448	1	408	5	3	4	f
446	1	408	5	3	3	f
444	1	408	5	3	2	f
442	1	408	5	3	1	f
440	1	408	5	3	0	f
465	1	408	7	2	3	f
467	1	408	7	2	4	f
463	1	408	7	2	2	f
461	1	408	7	2	1	f
459	1	408	7	2	0	f
458	1	408	6	3	4	f
456	1	408	6	3	3	f
454	1	408	6	3	2	f
452	1	408	6	3	1	f
450	1	408	6	3	0	f
477	1	408	8	2	4	f
475	1	408	8	2	3	f
473	1	408	8	2	2	f
471	1	408	8	2	1	f
469	1	408	8	2	0	f
468	1	408	7	3	4	f
466	1	408	7	3	3	f
464	1	408	7	3	2	f
462	1	408	7	3	1	f
460	1	408	7	3	0	f
487	1	408	9	2	4	f
485	1	408	9	2	3	f
483	1	408	9	2	2	f
481	1	408	9	2	1	f
479	1	408	9	2	0	f
\.


--
-- Name: zone_id_zone_seq_1; Type: SEQUENCE SET; Schema: campis; Owner: postgres
--

SELECT pg_catalog.setval('zone_id_zone_seq_1', 1, false);


--
-- PostgreSQL database dump complete
--

