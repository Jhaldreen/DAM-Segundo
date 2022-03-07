--   CREACI�N DE LAS TABLAS VUELOS Y PASAJEROS: 
DROP TABLE VUELOS CASCADE CONSTRAINTS;
DROP TABLE PASAJEROS CASCADE CONSTRAINTS;
-------------------------------------------------
CREATE TABLE VUELOS
( COD_VUELO VARCHAR2(10) PRIMARY KEY,
  HORA_SALIDA VARCHAR2(15),
  DESTINO VARCHAR2(15),
  PROCEDENCIA VARCHAR2(15),
  PLAZAS_FUMADOR NUMBER(3),
  PLAZAS_NO_FUMADOR NUMBER(3),
  PLAZAS_TURISTA NUMBER(3),
  PLAZAS_PRIMERA NUMBER(3)
);
-----------------------------------------------------------------

CREATE  TABLE PASAJEROS
(
  NUM  NUMBER(7),
  COD_VUELO VARCHAR2(10),
  TIPO_PLAZA VARCHAR2(2), -- 'TU': TURISTA, 'PR': PRIMERA
  FUMADOR VARCHAR2(2), -- 'SI' O 'NO'
  CONSTRAINT PK_PASAJEROS PRIMARY KEY(NUM, COD_VUELO),
  CONSTRAINT FK_PASAJEROS FOREIGN KEY(COD_VUELO) REFERENCES VUELOS
);
------------------------------------------------------------------
--------------- LLENAR VUELOS: -------------------------------

INSERT INTO VUELOS VALUES('IB-SP-4567','27/03/99-
10:30','PARIS','MADRID',100,100,160,40);
INSERT INTO VUELOS VALUES('IB-BA-46DC','28/03/99-
12:30','ROMA','MADRID',90,100,160,30);
INSERT INTO VUELOS VALUES('FR-DC-4667','28/03/99-
13:30','BRUSELAS','SEVILLA',90,100,160,30);
INSERT INTO VUELOS VALUES('AV-DC-347','29/03/99-
13:35','VALENCIA','ROMA',100,200,210,90);
INSERT INTO VUELOS VALUES('SP-DC-438','30/03/99-
09:20','MOSC�','SEVILLA',90,100,160,30);
INSERT INTO VUELOS VALUES('AI-D7-347','30/03/99-
13:35','BILBAO','MOSC�',100,200,210,90);
INSERT INTO VUELOS VALUES('IB-D5-347','01/04/99-
13:35','ZARAGOZA','PARIS',100,200,210,90);
INSERT INTO VUELOS VALUES('FR-DC7-247','01/04/99-15:35','CORDOBA','EL 
CAIRO',100,100,100,100);
INSERT INTO VUELOS VALUES('AV-DC9-233','01/04/99-
17:35','VALENCIA','SOF�A',100,100,100,100);
INSERT INTO VUELOS VALUES('FR-DC2-269','01/04/99-
19:00','C�RDOBA','MANILA',100,100,180,20);
INSERT INTO VUELOS VALUES('IB-98779','02/04/99-
08:00','MADRID','LIMA',200,100,250,50);
INSERT INTO VUELOS VALUES('AV-DC2-269','02/04/99-12:00','MADRID','LA 
HAYA',100,100,180,20);
INSERT INTO VUELOS VALUES('AI-1289-9','02/04/99-
14:30','BARCELONA','BONN',150,100,180,70);

-----------------------------------------------------------
------------- LLENAR PASAJEROS: ---------------------------
INSERT INTO PASAJEROS VALUES(123,'IB-SP-4567','TU','SI');
INSERT INTO PASAJEROS VALUES(124,'IB-SP-4567','PR','SI');
INSERT INTO PASAJEROS VALUES(125,'IB-SP-4567','PR','NO');
INSERT INTO PASAJEROS VALUES(126,'IB-BA-46DC','TU','SI');
INSERT INTO PASAJEROS VALUES(127,'IB-BA-46DC','PR','SI');
INSERT INTO PASAJEROS VALUES(128,'FR-DC-4667','TU','NO');
INSERT INTO PASAJEROS VALUES(129,'FR-DC-4667','TU','SI');

INSERT INTO PASAJEROS VALUES(130,'AV-DC9-233','TU','SI');
INSERT INTO PASAJEROS VALUES(131,'AV-DC9-233','TU','NO');
INSERT INTO PASAJEROS VALUES(132,'AV-DC9-233','PR','SI');
INSERT INTO PASAJEROS VALUES(133,'IB-D5-347','PR','SI');
INSERT INTO PASAJEROS VALUES(134,'IB-D5-347','PR','SI');
INSERT INTO PASAJEROS VALUES(135,'IB-D5-347','TU','NO');
INSERT INTO PASAJEROS VALUES(136,'IB-D5-347','TU','SI');

INSERT INTO PASAJEROS VALUES(137,'FR-DC-4667','TU','SI');
INSERT INTO PASAJEROS VALUES(138,'FR-DC-4667','TU','NO');
INSERT INTO PASAJEROS VALUES(139,'FR-DC-4667','PR','SI');
INSERT INTO PASAJEROS VALUES(126,'FR-DC-4667','PR','SI');

INSERT INTO PASAJEROS VALUES(130,'AV-DC2-269','TU','SI');
INSERT INTO PASAJEROS VALUES(131,'AV-DC2-269','TU','NO');
INSERT INTO PASAJEROS VALUES(132,'AV-DC2-269','PR','SI');
INSERT INTO PASAJEROS VALUES(133,'AI-1289-9','PR','SI');
INSERT INTO PASAJEROS VALUES(134,'AI-1289-9','PR','SI');
INSERT INTO PASAJEROS VALUES(135,'AI-1289-9','TU','NO');
INSERT INTO PASAJEROS VALUES(136,'AI-1289-9','TU','SI');

INSERT INTO PASAJEROS VALUES(137,'SP-DC-438','TU','SI');
INSERT INTO PASAJEROS VALUES(138,'SP-DC-438','TU','NO');
INSERT INTO PASAJEROS VALUES(139,'SP-DC-438','PR','SI');
INSERT INTO PASAJEROS VALUES(140,'SP-DC-438','PR','SI');
INSERT INTO PASAJEROS VALUES(141,'FR-DC7-247','PR','SI');
INSERT INTO PASAJEROS VALUES(142,'FR-DC7-247','TU','NO');
INSERT INTO PASAJEROS VALUES(143,'FR-DC7-247','TU','SI');

Commit;
-------------------------------------------------------------------

