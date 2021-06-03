package com.javasampleapproach.batchreportefija.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.javasampleapproach.batchreportefija.model.Report;

public class ReportrowMapper implements RowMapper<Report> {

	@Override
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
		Report report = new Report();
		report.setAnoPreventa(rs.getString("AÑO PREVENTA"));
		report.setCanalAgrupado(rs.getString("CANAL AGRUPADO"));
		report.setCanal(rs.getString("CANAL"));
		report.setCodigoCanal(rs.getString("CODIGO_CANAL"));
		report.setCodigoEntidad(rs.getString("CODIGO_ENTIDAD"));
		report.setCodigoPuntoVenta(rs.getString("CODIGO_PUNTO_DE_VENTA"));
		report.setCodigoVendedorAtis(rs.getString("CODIGO_VENDEDOR_ATIS"));
		report.setCodigoVendedorCms(rs.getString("CODIGO_VENDEDOR_CMS"));
		report.setDepartamentoPeticionVenta(rs.getString("DEPARTAMENTO_PETICION_VENTA"));
		report.setDiaPreventa("PREVENTA");
		report.setDistritoPeticionVenta("DISTRITO_PETICION_VENTA");
		report.setDocumentoVendedor(rs.getString("DOCUMENTO_VENDEDOR"));
		report.setEntidad(rs.getString("ENTIDAD"));
		report.setEntidad(rs.getString("ESTADO_PETICION_VENTA"));
		report.setFechaPreventa(rs.getString("FECHA_PREVENTA"));
		report.setMesPreventa(rs.getString("MES PREVENTA"));
		report.setNombreVendedor(rs.getString("NOMBRE_VENDEDOR"));
		report.setPlazoAtencionPreventa(rs.getString("PLAZO_ATENCION_PREVENTA"));
		report.setPlazoLiquidaciónPreventa(rs.getString("PLAZO_LIQUIDACION_PREVENTA"));
		report.setPlazoPreventa(rs.getString("PLAZO_DE_PREVENTA"));
		report.setRegionPeticionVenta(rs.getString("REGION_PETICION_EN_VENTA"));
		report.setRegionPuntoVenta(rs.getString("REGION_PUNTO_DE_VENTA"));
		report.setTipoTransaccion(rs.getString("TIPO_TRANSACCION"));
		report.setZonalPeticionVenta(rs.getString("ZONAL_PETICION_EN_VENTA"));
		report.setZonalPuntoVenta(rs.getString("ZONAL_PUNTO_DE_VENTA"));
		report.setIdVisor(rs.getString("id_visor"));
		report.setBack(rs.getString("back"));
		report.setFecha_grabacion(rs.getString("fecha_grabacion"));
		report.setTelefono(rs.getString("telefono"));
		report.setTelefono2(rs.getString("telefono2"));
		report.setDireccion(rs.getString("direccion"));
		report.setDistrito(rs.getString("distrito"));
		report.setProvincia(rs.getString("provincia"));
		report.setDepartamento(rs.getString("departamento"));
		report.setProvincia(rs.getString("tipo_documento"));
		report.setDni(rs.getString("dni"));
		report.setEstado_solicitud(rs.getString("estado_solicitud"));
		report.setMotivo_estado(rs.getString("motivo_estado"));
		report.setOperacion_comercial(rs.getString("operacion_comercial"));
		report.setNombre_producto(rs.getString("a.nombre_producto"));
		report.setSub_producto(rs.getString("sub_producto"));
		report.setTipo_producto(rs.getString("tipo_producto"));
		report.setFecha_de_llamada(rs.getString("fecha_de_llamada"));
		report.setCodigo_pedido(rs.getString("codigo_pedido"));
		report.setCorreo(rs.getString("correo"));
		report.setOrder_id(rs.getString("order_id"));
		report.setOrder_operation_commercial(rs.getString("order_operation_commercial"));
		report.setOrder_gps_x(rs.getString("order_gps_x"));
		report.setOrder_gps_y(rs.getString("order_gps_y"));
		report.setUser_atis(rs.getString("user_atis"));
		report.setAddress_departamento_cod(rs.getString("address_departamento_cod"));
		report.setAddress_departamento(rs.getString("address_departamento"));
		report.setAddress_provincia_cod(rs.getString("address_provincia_cod"));
		report.setAddress_provincia(rs.getString("address_provincia"));
		report.setAddress_distrito_cod(rs.getString("address_distrito_cod"));
		report.setAddress_distrito(rs.getString("address_distrito"));
		report.setAddress_principal(rs.getString("address_principal"));
		report.setAddress_calle_atis(rs.getString("address_calle_atis"));
		report.setAddress_calle_nombre(rs.getString("address_calle_nombre"));
		report.setAddress_calle_numero(rs.getString("address_calle_numero"));
		report.setAddress_cchh_codigo(rs.getString("address_cchh_codigo"));
		report.setAddress_cchh_tipo(rs.getString("address_cchh_tipo"));
		report.setAddress_cchh_nombre(rs.getString("address_cchh_nombre"));
		report.setAddress_cchh_comp_tip_1(rs.getString("address_cchh_comp_tip_1"));
		report.setAddress_cchh_comp_nom_1(rs.getString("address_cchh_comp_nom_1"));
		report.setAddress_cchh_comp_tip_2(rs.getString("address_cchh_comp_tip_2"));
		report.setAddress_cchh_comp_nom_2(rs.getString("address_cchh_comp_nom_2"));
		report.setAddress_cchh_comp_tip_3(rs.getString("address_cchh_comp_tip_3"));
		report.setAddress_cchh_comp_nom_3(rs.getString("address_cchh_comp_nom_3"));
		report.setAddress_cchh_comp_tip_4(rs.getString("address_cchh_comp_tip_4"));
		report.setAddress_cchh_comp_nom_4(rs.getString("address_cchh_comp_nom_4"));
		report.setAddress_cchh_comp_tip_5(rs.getString("address_cchh_comp_tip_5"));
		report.setAddress_cchh_comp_nom_5(rs.getString("address_cchh_comp_nom_5"));
		report.setAddress_via_comp_tip_1(rs.getString("address_via_comp_tip_1"));
		report.setAddress_via_comp_nom_1(rs.getString("address_via_comp_nom_1"));
		report.setAddress_via_comp_tip_2(rs.getString("address_via_comp_tip_2"));
		report.setAddress_via_comp_nom_2(rs.getString("address_via_comp_nom_2"));
		report.setAddress_via_comp_tip_3(rs.getString("address_via_comp_tip_3"));
		report.setAddress_via_comp_nom_3(rs.getString("address_via_comp_nom_3"));
		report.setAddress_via_comp_tip_4(rs.getString("address_via_comp_tip_4"));
		report.setAddress_via_comp_nom_4(rs.getString("address_via_comp_nom_4"));
		report.setAddress_via_comp_tip_5(rs.getString("address_via_comp_tip_5"));
		report.setAddress_via_comp_nom_5(rs.getString("address_via_comp_nom_5"));
		report.setAddress_via_comp_tip_6(rs.getString("address_via_comp_tip_6"));
		report.setAddress_via_comp_nom_6(rs.getString("address_via_comp_nom_6"));
		report.setAddress_via_comp_tip_7(rs.getString("address_via_comp_tip_7"));
		report.setAddress_via_comp_nom_7(rs.getString("address_via_comp_nom_7"));
		report.setAddress_referencia(rs.getString("address_referencia"));
		
		return report;
	}

}
