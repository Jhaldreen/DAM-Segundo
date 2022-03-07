<?xml version="1.0" encoding="UTF-8"?>

<helpset>
  <!-- Titulo -->
  <title>Creacion y uso del componente</title>

  <maps>
    <!-- Página a visualizar por defecto -->
    <homeID>Introduccion1</homeID>
    
    <!-- Fichero map -->
    <mapref location="map.jhm" />
  </maps>

  <!-- Las Vistas que deseamos mostrar en la ayuda -->

  <view>
    <!-- Nombre de la vista. En esta caso Introducion compenentes -->
    <name>Introducion Componentes</name>
    <!-- Etiqueta asociada a la tabla de contenidos -->
    <label>index</label>
    <!-- Camino a la clase navegador -->
    <type>javax.help.TOCView</type>
    <!-- El fichero que con la tabla de contenidos -->
    <data>toc.xml</data>
  </view>

  <view xml:lang="es" mergetype="javax.help.SortMerge">
    <name>Index</name>
   <label>EL Indice</label>
   <type>javax.help.IndexView</type>
   <data>indice.xml</data>
  </view>

  <view xml:lang="es">
    <!-- Búsquedas -->
    <name>Buscar</name>
    <!-- El tooltiptext -->
    <label>Buscar</label>
    <type>javax.help.SearchView</type>
    <data engine="com.sun.java.help.search.DefaultSearchEngine">
      JavaHelpSearch
    </data>
  </view>

  <presentation default=true>
    <name>Página principal</name>
    <size width="400" height="400" />
    <location x="200" y="200" />
    <title>Ayuda JavaHelp</title>
    <toolbar>
      <helpaction>javax.help.BackAction</helpaction>
      <helpaction>javax.help.ForwardAction</helpaction>
      <helpaction image="homeicon">javax.help.HomeAction</helpaction>
    </toolbar>
  </presentation>
</helpset> 
