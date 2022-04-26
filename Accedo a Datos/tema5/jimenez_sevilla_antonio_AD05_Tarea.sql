/*1. Definir un tipo varray de dimensión 3 para contener los teléfonos*/

CREATE TYPE telefono AS VARRAY ( 3 ) OF VARCHAR2(15);


/*2. Crear los tipos dirección, cliente, producto y línea de venta*/

/*Dirección*/

CREATE TYPE direccion AS OBJECT (
    calle       VARCHAR2(50),
    poblacion   VARCHAR2(50),
    codpostal   NUMBER(5),
    provincia   VARCHAR(40)
);

/*Cliente*/

CREATE TYPE cliente AS OBJECT (
    idcliente     NUMBER,
    nombre        VARCHAR2(50),
    direccion_t   direccion,
    nif           VARCHAR2(9),
    telefono_t    telefono
);

/*Producto*/

CREATE TYPE producto AS OBJECT (
    idproducto    NUMBER,
    descripcion   VARCHAR2(80),
    pvp           NUMBER,
    stockactual   NUMBER
);

/*Línea de venta*/

CREATE TYPE lineaventa AS OBJECT (
    numerolinea   NUMBER,
    producto_t    REF producto,
    cantidad      NUMBER
);


/*3. Crear un tipo tabla anidada para contener las líneas de una venta*/

CREATE TYPE lineaventas AS
    TABLE OF lineaventa;


/*4.Crear un tipo venta para los datos de las ventas, cada venta tendrá un atributo
LINEAS del tipo tabla anidada definida anteriormente:*/

CREATE TYPE venta AS OBJECT (
    idventa      NUMBER,
    idcliente    REF cliente,
    lineas       lineaventas,
    fechaventa   DATE,

MEMBER FUNCTION total_venta RETURN NUMBER
);


/*5. Crea el cuerpo del tipo anterior, teniendo en cuenta que se definirá la
función miembro TOTAL_VENTA que calcula el total de la venta de las líneas de 
venta que forman parte de una venta, contara el número de elementos de una tabla
o de un array y devolverá el número de lineas que tiene la venta.*/

CREATE OR REPLACE TYPE BODY venta AS
    MEMBER FUNCTION total_venta RETURN NUMBER IS
        total     NUMBER := 0;
        linea     lineaventa;
        product   producto;
    BEGIN
        FOR i IN 1..lineas.count LOOP
            linea := lineas(i);
            SELECT deref(linea.producto_t)
            INTO product
            FROM dual;

            total := total + linea.cantidad * product.pvp;
        END LOOP;

        RETURN total;
    END;

END;  


/*6. Crear las tablas donde almacenar los objetos de la aplicación. 
Se creará una tabla para clientes, otra para productos y otra para las ventas,
en dichas tablas se definirán las oportunas claves primarias.*/

CREATE TABLE clientes_t OF cliente (
    idcliente PRIMARY KEY,
    nif UNIQUE
);

CREATE TABLE productos_t OF producto (
    idproducto PRIMARY KEY
);

CREATE TABLE ventas_t OF venta (
    idventa PRIMARY KEY
)
NESTED TABLE lineas STORE AS lineas_t;

/*7.Inserta dos clientes y cinco productos.*/

/*Cliente 1*/

INSERT INTO clientes_t VALUES (1,'Antonio',direccion('Calle Federico numero 5','Santander','39009','Cantabria'),'00000000H',telefono('654987321'));

/*Cliente 2*/

INSERT INTO clientes_t VALUES (2, 'Sara',direccion('Calle Falsa 123','Vigo','36224','Pontevedra'), '12365478J',telefono('555654321'));

/*Productos*/

INSERT INTO productos_t VALUES (1, 'JacksonRR',700,10);

INSERT INTO productos_t VALUES ( 2, 'Marshall JCM800',1500,5);

INSERT INTO productos_t VALUES (3,'Peavy 5150',1100,50);

INSERT INTO productos_t VALUES (4,'Guitarra Faith',15,100);

INSERT INTO productos_t VALUES (5,'Cables XLR',6,500);





/*8.Insertar en TABLA_VENTAS la venta con IDVENTA 1 para el IDCLIENTE 1*/

INSERT INTO ventas_t
    SELECT 1,REF(cli),lineaventas(),
    SYSDATE
    FROM clientes_t cli
    WHERE cli.idcliente = 1; 


/*9.Insertar en TABLA_VENTAS dos l�neas de venta para el IDVENTA 1 para 
los productos 1 (la CANTIDAD es 1) y 2 (la CANTIDAD es 2)*/

INSERT INTO TABLE (
    SELECT v.lineas
    FROM ventas_t v
    WHERE v.idventa = 1)
    ( SELECT1, REF(p),1
    FROM productos_t p
      WHERE p.idproducto = 1
);

INSERT INTO TABLE (
    SELECT v.lineas
    FROM ventas_t v
    WHERE v.idventa = 1)
    ( SELECT 2,REF(p),2
    FROM productos_t p
    WHERE p.idproducto = 2 
);


/*10.Insertar en TABLA_VENTAS la venta con IDVENTA 2 para el IDCLIENTE*/

INSERT INTO ventas_t
    SELECT 2,REF(cli),lineaventas(),
        SYSDATE
    FROM clientes_t cli
    WHERE idcliente = 1;


/*11. Insertar en TABLA_VENTAS tres líneas de venta para el IDVENTA 2 para 
los productos 1 (la CANTIDAD es 2), 4 (la CANTIDAD es 1) y 5 (la CANTIDAD es 4)*/

INSERT INTO TABLE (
    SELECT v.lineas
    FROM ventas_t v
    WHERE v.idventa = 2)
    ( SELECT 1,REF(p),2
      FROM productos_t p
      WHERE p.idproducto = 1
);

INSERT INTO TABLE (
    SELECT v.lineas
    FROM ventas_t v
    WHERE v.idventa = 2)
    ( SELECT 2,REF(p),1
      FROM productos_t p
      WHERE p.idproducto = 4
);

INSERT INTO TABLE (
    SELECT v.lineas
    FROM ventas_t v
    WHERE v.idventa = 2)
    ( SELECT 3,REF(p),4
    FROM productos_t p
    WHERE p.idproducto = 5
    );


/*12.Realizar un procedimiento que recibiendo el identificador visualice los datos de la venta.*/

CREATE OR REPLACE PROCEDURE DATOS_VENTA (ID NUMBER) AS 
 PRECIO NUMBER; 
 TOTAL_VENTA NUMBER; 
 
CLI CLIENTE:=CLIENTE(NULL,NULL,NULL,NULL, NULL); 
 FECHA DATE;
 CURSOR CUR IS 
 SELECT NUMEROLINEA LIN, DEREF(producto_t) PROD, CANTIDAD FROM THE
 (SELECT T.LINEAS FROM VENTAS_T T WHERE IDVENTA=ID);

BEGIN 
 SELECT DEREF(IDCLIENTE), FECHAVENTA, V.TOTAL_VENTA() 
INTO CLI, FECHA, TOTAL_VENTA 
 FROM VENTAS_T V WHERE IDVENTA = ID; 
DBMS_OUTPUT.PUT_LINE('NUMERO DE VENTA: '||ID|| 
' | Fecha de venta: '|| FECHA); 
DBMS_OUTPUT.PUT_LINE('CLIENTE: '||CLI.NOMBRE); 
 DBMS_OUTPUT.PUT_LINE('DIRECCION: '||CLI.DIRECCION_T.CALLE); 
DBMS_OUTPUT.PUT_LINE('*********************************************'); 
DBMS_OUTPUT.PUT_LINE('LINEA |DESCRIPCION| PRECIO | UD | TOTAL') ;
FOR i IN CUR LOOP 
PRECIO:= i.CANTIDAD * i.PROD.PVP; 
DBMS_OUTPUT.PUT_LINE('  ' || i.LIN||'-   '|| i.PROD.DESCRIPCION ||' |'|| 
 i.PROD.PVP||'  '|| i.CANTIDAD||' ud. '||PRECIO || ' '); 
END LOOP; 
 DBMS_OUTPUT.PUT_LINE('Total Venta: '||TOTAL_VENTA); 
END DATOS_VENTA; 
/

BEGIN
    DATOS_VENTA(3);
END;
/