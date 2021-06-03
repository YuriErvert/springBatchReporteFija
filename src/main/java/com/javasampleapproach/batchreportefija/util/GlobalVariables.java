package com.javasampleapproach.batchreportefija.util;

import org.springframework.stereotype.Service;

@Service
public class GlobalVariables {
	
	/**
	 * Querys
	 */
	public String queryReportBi = "SELECT * FROM ibmx_a07e6d02edaf552.view_reporte_fija_test";	
	public String queryReportBiTest = "SELECT * FROM ibmx_a07e6d02edaf552.view_report_fija_test";
	
	//public String queryReportBiFecha = "SELECT * FROM ibmx_a07e6d02edaf552.view_report_fija_fecha";
	public String queryReportBiFecha = "SELECT date_part('year'::text, a.fecha_solicitado) AS \"AÑO PREVENTA\"," + 
			"    f.canal_agrupado AS \"CANAL AGRUPADO\"," + 
			"    c.canal AS \"CANAL\"," + 
			"    b.user_canal_codigo AS \"CODIGO_CANAL\"," + 
			"    b.user_entidad AS \"CODIGO_ENTIDAD\"," + 
			"    b.user_cms_punto_venta AS \"CODIGO_PUNTO_DE_VENTA\"," + 
			"    b.user_atis AS \"CODIGO_VENDEDOR_ATIS\"," + 
			"    b.user_cms AS \"CODIGO_VENDEDOR_CMS\"," + 
			"    a.departamento AS \"DEPARTAMENTO_PETICION_VENTA\"," + 
			"    date_part('day'::text, a.fecha_solicitado) AS \"DIA PREVENTA\"," + 
			"    a.distrito AS \"DISTRITO_PETICION_VENTA\"," + 
			"    e.dni AS \"DOCUMENTO_VENDEDOR\"," + 
			"    b.user_entidad AS \"ENTIDAD\"," + 
			"    a.estado_solicitud AS \"ESTADO_PETICION_VENTA\"," + 
			"    a.fecha_solicitado::date AS \"FECHA_PREVENTA\"," + 
			"    date_part('month'::text, a.fecha_solicitado) AS \"MES PREVENTA\"," + 
			"    b.user_nombre AS \"NOMBRE_VENDEDOR\"," + 
			"    a.fecha_registrado - a.fecha_solicitado AS \"PLAZO_ATENCION_PREVENTA\"," + 
			"    a.fecha_instalado - a.fecha_solicitado AS \"PLAZO_LIQUIDACION_PREVENTA\"," + 
			"    a.fecha_registrado - a.fecha_solicitado AS \"PLAZO_DE_PREVENTA\"," + 
			"    b.user_region AS \"REGION_PETICION_EN_VENTA\"," + 
			"    b.address_provincia AS \"REGION_PUNTO_DE_VENTA\"," + 
			"    a.operacion_comercial AS \"TIPO_TRANSACCION\"," + 
			"    b.user_zonal AS \"ZONAL_PETICION_EN_VENTA\"," + 
			"    b.address_distrito AS \"ZONAL_PUNTO_DE_VENTA\"," + 
			"    a.id_visor," + 
			"    a.back," + 
			"    a.fecha_grabacion," + 
			"    a.telefono," + 
			"    a.telefono2," + 
			"    a.direccion," + 
			"    a.distrito," + 
			"    a.provincia," + 
			"    a.departamento," + 
			"    a.tipo_documento," + 
			"    a.dni," + 
			"    a.estado_solicitud," + 
			"    a.motivo_estado," + 
			"    a.operacion_comercial," + 
			"    a.nombre_producto," + 
			"    a.sub_producto," + 
			"    a.tipo_producto," + 
			"    a.fecha_de_llamada," + 
			"    a.codigo_pedido," + 
			"    a.correo," + 
			"    b.order_id," + 
			"    b.order_operation_commercial," + 
			"    b.order_gps_x," + 
			"    b.order_gps_y," + 
			"    b.user_atis," + 
			"    b.address_departamento_cod," + 
			"    b.address_departamento," + 
			"    b.address_provincia_cod," + 
			"    b.address_provincia," + 
			"    b.address_distrito_cod," + 
			"    b.address_distrito," + 
			"    b.address_principal," + 
			"    b.address_calle_atis," + 
			"    b.address_calle_nombre," + 
			"    b.address_calle_numero," + 
			"    b.address_cchh_codigo," + 
			"    b.address_cchh_tipo," + 
			"    b.address_cchh_nombre," + 
			"    b.address_cchh_comp_tip_1," + 
			"    b.address_cchh_comp_nom_1," + 
			"    b.address_cchh_comp_tip_2," + 
			"    b.address_cchh_comp_nom_2," + 
			"    b.address_cchh_comp_tip_3," + 
			"    b.address_cchh_comp_nom_3," + 
			"    b.address_cchh_comp_tip_4," + 
			"    b.address_cchh_comp_nom_4," + 
			"    b.address_cchh_comp_tip_5," + 
			"    b.address_cchh_comp_nom_5," + 
			"    b.address_via_comp_tip_1," + 
			"    b.address_via_comp_nom_1," + 
			"    b.address_via_comp_tip_2," + 
			"    b.address_via_comp_nom_2," + 
			"    b.address_via_comp_tip_3," + 
			"    b.address_via_comp_nom_3," + 
			"    b.address_via_comp_tip_4," + 
			"    b.address_via_comp_nom_4," + 
			"    b.address_via_comp_tip_5," + 
			"    b.address_via_comp_nom_5," + 
			"    b.address_via_comp_tip_6," + 
			"    b.address_via_comp_nom_6," + 
			"    b.address_via_comp_tip_7," + 
			"    b.address_via_comp_nom_7," + 
			"    b.address_referencia" + 
			"   FROM ibmx_a07e6d02edaf552.tdp_visor a," + 
			"    ibmx_a07e6d02edaf552.tdp_order b," + 
			"    ibmx_a07e6d02edaf552.\"order\" c," + 
			"    ibmx_a07e6d02edaf552.\"user\" e," + 
			"    ibmx_a07e6d02edaf552.tdp_grupo_canal f" + 
			"  WHERE a.id_visor::text = b.order_id::text AND c.id::text = b.order_id::text AND e.id::text = b.user_atis::text AND c.canal::text = f.canal::text AND date_part('year'::text, a.fecha_solicitado) = '2020'::double precision AND date_part('month'::text, a.fecha_solicitado) = '9'::double precision AND (date_part('day'::text, a.fecha_solicitado) = ANY (ARRAY['3'::double precision,'4'::double precision,'5'::double precision,'6'::double precision,'7'::double precision,'8'::double precision,'9'::double precision,'10'::double precision, '11'::double precision, '12'::double precision, '13'::double precision, '14'::double precision]))";
	
	public String queryReportBiMensual = "SELECT * FROM ibmx_a07e6d02edaf552.view_report_fija_mensual";	
	public String queryLogReportBi = "INSERT INTO ibmx_a07e6d02edaf552.log_batch_reportefijabi"
									+ "(trace,status_proccess,file_name,executed_at)"
									+ " VALUES(?,?,?,?)";
	
	/**
	 * Batch Config
	 */
	public String[] fieldsReaderReportBi = {"anoPreventa",
    								"canalAgrupado",
    								"canal",
    								"codigoCanal",
    								"codigoEntidad",
    								"codigoPuntoVenta",
    								"codigoVendedorAtis",
    								"codigoVendedorCms",
    								"departamentoPeticionVenta",
    								"diaPreventa",
    								"distritoPeticionVenta",
    								"documentoVendedor",
    								"entidad",
    								"estadoPeticionVenta",
    								"fechaPreventa",
    								"mesPreventa",
    								"nombreVendedor",
    								"plazoAtencionPreventa",
    								"plazoLiquidaciónPreventa",
    								"plazoPreventa",
    								"regionPeticionVenta",
    								"regionPuntoVenta",
    								"tipoTransaccion",
    								"zonalPeticionVenta",
    								"zonalPuntoVenta",
    								"idVisor",
    								"back",
    								"fecha_grabacion",
    								"telefono",
    								"telefono2",
    								"direccion",
    								"distrito",
    								"provincia",
    								"departamento",
    								"tipo_documento",
    								"dni",
    								"estado_solicitud",
    								"motivo_estado",
    								"operacion_comercial",
    								"nombre_producto",
    								"sub_producto",
    								"tipo_producto",
    								"fecha_de_llamada",
    								"codigo_pedido",
    								"correo",
    								"order_id",
    								"order_operation_commercial",
    								"order_gps_x",
    								"order_gps_y",
    								"user_atis",
    								"address_departamento_cod",
    								"address_departamento",
    								"address_provincia_cod",
    								"address_provincia",
    								"address_distrito_cod",
    								"address_distrito",
    								"address_principal",
    								"address_calle_atis",
    								"address_calle_nombre",
    								"address_calle_numero",
    								"address_cchh_codigo",
    								"address_cchh_tipo",
    								"address_cchh_nombre",
    								"address_cchh_comp_tip_1",
    								"address_cchh_comp_nom_1",
    								"address_cchh_comp_tip_2",
    								"address_cchh_comp_nom_2",
    								"address_cchh_comp_tip_3",
    								"address_cchh_comp_nom_3",
    								"address_cchh_comp_tip_4",
    								"address_cchh_comp_nom_4",
    								"address_cchh_comp_tip_5",
    								"address_cchh_comp_nom_5",
    								"address_via_comp_tip_1",
    								"address_via_comp_nom_1",
    								"address_via_comp_tip_2",
    								"address_via_comp_nom_2",
    								"address_via_comp_tip_3",
    								"address_via_comp_nom_3",
    								"address_via_comp_tip_4",
    								"address_via_comp_nom_4",
    								"address_via_comp_tip_5",
    								"address_via_comp_nom_5",
    								"address_via_comp_tip_6",
    								"address_via_comp_nom_6",
    								"address_via_comp_tip_7",
    								"address_via_comp_nom_7",
    								"address_referencia"};
	
	public String exportFileHeaderReportBi = "ANO;"
			 +"CANAL AGRUPADO;"
			 +"CANAL;"
			 +"CODIGO_CANAL;"
			 +"CODIGO_ENTIDAD;"
			 +"CODIGO_PUNTO_DE_VENTA;"
			 +"CODIGO_VENDEDOR_ATIS;"
			 +"CODIGO_VENDEDOR_CMS;"
			 +"DEPARTAMENTO_PETICION_VENTA;"
			 +"PREVENTA;"
			 +"DISTRITO_PETICION_VENTA;"
			 +"DOCUMENTO_VENDEDOR;"
			 +"ENTIDAD;"
			 +"ESTADO_PETICION_VENTA;"
			 +"FECHA_PREVENTA;"
			 +"MES PREVENTA;"
			 +"NOMBRE_VENDEDOR;"
			 +"PLAZO_ATENCION_PREVENTA;"
			 +"PLAZO_LIQUIDACION_PREVENTA;"
			 +"PLAZO_DE_PREVENTA;"
			 +"REGION_PETICION_EN_VENTA;"
			 +"REGION_PUNTO_DE_VENTA;"
			 +"TIPO_TRANSACCION;"
			 +"ZONAL_PETICION_EN_VENTA;"
			 +"ZONAL_PUNTO_DE_VENTA;"
			 +"id_visor;"
			 +"back;"
			 +"fecha_grabacion;"
			 +"telefono;"
			 +"telefono2;"
			 +"direccion;"
			 +"distrito;"
			 +"provincia;"
			 +"departamento;"
			 +"tipo_documento;"
			 +"dni;"
			 +"estado_solicitud;"
			 +"motivo_estado;"
			 +"operacion_comercial;"
			 +"a.nombre_producto;"
			 +"sub_producto;"
			 +"tipo_producto;"
			 +"fecha_de_llamada;"
			 +"codigo_pedido;"
			 +"correo;"
			 +"order_id;"
			 +"order_operation_commercial;"
			 +"order_gps_x;"
			 +"order_gps_y;"
			 +"user_atis;"
			 +"address_departamento_cod;"
			 +"address_departamento;"
			 +"address_provincia_cod;"
			 +"address_provincia;"
			 +"address_distrito_cod;"
			 +"address_distrito;"
			 +"address_principal;"
			 +"address_calle_atis;"
			 +"address_calle_nombre;"
			 +"address_calle_numero;"
			 +"address_cchh_codigo;"
			 +"address_cchh_tipo;"
			 +"address_cchh_nombre;"
			 +"address_cchh_comp_tip_1;"
			 +"address_cchh_comp_nom_1;"
			 +"address_cchh_comp_tip_2;"
			 +"address_cchh_comp_nom_2;"
			 +"address_cchh_comp_tip_3;"
			 +"address_cchh_comp_nom_3;"
			 +"address_cchh_comp_tip_4;"
			 +"address_cchh_comp_nom_4;"
			 +"address_cchh_comp_tip_5;"
			 +"address_cchh_comp_nom_5;"
			 +"address_via_comp_tip_1;"
			 +"address_via_comp_nom_1;"
			 +"address_via_comp_tip_2;"
			 +"address_via_comp_nom_2;"
			 +"address_via_comp_tip_3;"
			 +"address_via_comp_nom_3;"
			 +"address_via_comp_tip_4;"
			 +"address_via_comp_nom_4;"
			 +"address_via_comp_tip_5;"
			 +"address_via_comp_nom_5;"
			 +"address_via_comp_tip_6;"
			 +"address_via_comp_nom_6;"
			 +"address_via_comp_tip_7;"
			 +"address_via_comp_nom_7;"
			 +"address_referencia";
}
