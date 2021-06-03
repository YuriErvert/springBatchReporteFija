package com.javasampleapproach.batchreportefija.dao.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.batchreportefija.dao.ReportDao;
import com.javasampleapproach.batchreportefija.model.Report;
import com.javasampleapproach.batchreportefija.util.GlobalVariables;

@Repository
public class ReportDaoImpl extends JdbcDaoSupport implements ReportDao{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	GlobalVariables gv;
	
	@PostConstruct
	  private void initialize() {
	    setDataSource(dataSource);
	  }
	
	private static final Logger log = LoggerFactory.getLogger(ReportDaoImpl.class);
	
	@Override
	  public List<Report> loadAllReports() {
	    //String sql = "SELECT * FROM ibmx_a07e6d02edaf552.view_reporte_fija_test limit 100";
	    //String sql = gv.queryReportBi;
	    String sql = gv.queryReportBi;
	    //String sql = gv.queryReportBiFecha;
	    //String sql = gv.queryReportBiTest;
	    List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
	    if(rows.size()>0) {
	    	System.out.println("Muestra de Dao: \n" + rows.get(0));
	    }else {
	    	System.out.println("Dao Vacío: No hay registros de la base de datos para este día");
	    }
	    
	    List<Report> result = new ArrayList<Report>();
	    for (Map<String, Object> row : rows) {
	      Report report = new Report();
	      report.setAnoPreventa((String) validationRow(row.get("AÑO PREVENTA") == null ? "" : row.get("AÑO PREVENTA").toString()));
			report.setCanalAgrupado((String) validationRow(row.get("CANAL AGRUPADO") == null ? "" : row.get("CANAL AGRUPADO").toString()));
			report.setCanal((String) validationRow(row.get("CANAL") == null ? "" : row.get("CANAL").toString()));
			report.setCodigoCanal((String) validationRow(row.get("CODIGO_CANAL") == null ? "" : row.get("CODIGO_CANAL").toString()));
			report.setCodigoEntidad((String) validationRow(row.get("CODIGO_ENTIDAD") == null ? "" : row.get("CODIGO_ENTIDAD").toString()));
			report.setCodigoPuntoVenta((String) validationRow(row.get("CODIGO_PUNTO_DE_VENTA") == null ? "" : row.get("CODIGO_PUNTO_DE_VENTA").toString()));
			report.setCodigoVendedorAtis((String) validationRow(row.get("CODIGO_VENDEDOR_ATIS") == null ? "" : row.get("CODIGO_VENDEDOR_ATIS").toString()));
			report.setCodigoVendedorCms((String) validationRow(row.get("CODIGO_VENDEDOR_CMS") == null ? "" : row.get("CODIGO_VENDEDOR_CMS").toString()));
			report.setDepartamentoPeticionVenta((String) validationRow(row.get("DEPARTAMENTO_PETICION_VENTA") == null ? "" : row.get("DEPARTAMENTO_PETICION_VENTA").toString()));
			report.setDiaPreventa((String) validationRow(row.get("PREVENTA") == null ? "" : row.get("PREVENTA").toString()));
			report.setDistritoPeticionVenta((String) validationRow(row.get("DISTRITO_PETICION_VENTA") == null ? "" : row.get("DISTRITO_PETICION_VENTA").toString()));
			report.setDocumentoVendedor((String) validationRow(row.get("DOCUMENTO_VENDEDOR") == null ? "" : row.get("DOCUMENTO_VENDEDOR").toString()));
			report.setEntidad((String) validationRow(row.get("ENTIDAD") == null ? "" : row.get("ENTIDAD").toString()));
			report.setEntidad((String) validationRow(row.get("ESTADO_PETICION_VENTA") == null ? "" : row.get("ESTADO_PETICION_VENTA").toString()));
			report.setFechaPreventa((String) validationRow(row.get("FECHA_PREVENTA") == null ? "" : row.get("FECHA_PREVENTA").toString()));
			report.setMesPreventa((String) validationRow(row.get("MES PREVENTA") == null ? "" : row.get("MES PREVENTA").toString()));
			report.setNombreVendedor((String) validationRow(row.get("NOMBRE_VENDEDOR") == null ? "" : row.get("NOMBRE_VENDEDOR").toString()));
			report.setPlazoAtencionPreventa((String) validationRow(row.get("PLAZO_ATENCION_PREVENTA") == null ? "" : row.get("PLAZO_ATENCION_PREVENTA").toString()));
			report.setPlazoLiquidaciónPreventa((String) validationRow(row.get("PLAZO_LIQUIDACION_PREVENTA") == null ? "" : row.get("PLAZO_LIQUIDACION_PREVENTA").toString()));
			report.setPlazoPreventa((String) validationRow(row.get("PLAZO_DE_PREVENTA") == null ? "" : row.get("PLAZO_DE_PREVENTA").toString()));
			report.setRegionPeticionVenta((String) validationRow(row.get("REGION_PETICION_EN_VENTA") == null ? "" : row.get("REGION_PETICION_EN_VENTA").toString()));
			report.setRegionPuntoVenta((String) validationRow(row.get("REGION_PUNTO_DE_VENTA") == null ? "" : row.get("REGION_PUNTO_DE_VENTA").toString()));
			report.setTipoTransaccion((String) validationRow(row.get("TIPO_TRANSACCION") == null ? "" : row.get("TIPO_TRANSACCION").toString()));
			report.setZonalPeticionVenta((String) validationRow(row.get("ZONAL_PETICION_EN_VENTA") == null ? "" : row.get("ZONAL_PETICION_EN_VENTA").toString()));
			report.setZonalPuntoVenta((String) validationRow(row.get("ZONAL_PUNTO_DE_VENTA") == null ? "" : row.get("ZONAL_PUNTO_DE_VENTA").toString()));
			report.setIdVisor((String) validationRow(row.get("id_visor") == null ? "" : row.get("id_visor").toString()));
			report.setBack((String) validationRow(row.get("back") == null ? "" : row.get("back").toString()));
			report.setFecha_grabacion((String) validationRow(row.get("fecha_grabacion") == null ? "" : row.get("fecha_grabacion").toString()));
			report.setTelefono((String) validationRow(row.get("telefono") == null ? "" : row.get("telefono").toString()));
			report.setTelefono2((String) validationRow(row.get("telefono2") == null ? "" : row.get("telefono2").toString()));
			report.setDireccion((String) validationRow(row.get("direccion") == null ? "" : row.get("direccion").toString()));
			report.setDistrito((String) validationRow(row.get("distrito") == null ? "" : row.get("distrito").toString()));
			report.setProvincia((String) validationRow(row.get("provincia") == null ? "" : row.get("provincia").toString()));
			report.setDepartamento((String) validationRow(row.get("departamento") == null ? "" : row.get("departamento").toString()));
			report.setProvincia((String) validationRow(row.get("tipo_documento") == null ? "" : row.get("tipo_documento").toString()));
			report.setDni((String) validationRow(row.get("dni") == null ? "" : row.get("dni").toString()));
			report.setEstado_solicitud((String) validationRow(row.get("estado_solicitud") == null ? "" : row.get("estado_solicitud").toString()));
			report.setMotivo_estado((String) validationRow(row.get("motivo_estado") == null ? "" : row.get("motivo_estado").toString()));
			report.setOperacion_comercial((String) validationRow(row.get("operacion_comercial") == null ? "" : row.get("operacion_comercial").toString()));
			report.setNombre_producto((String) validationRow(row.get("a.nombre_producto") == null ? "" : row.get("a.nombre_producto").toString()));
			report.setSub_producto((String) validationRow(row.get("sub_producto") == null ? "" : row.get("sub_producto").toString()));
			report.setTipo_producto((String) validationRow(row.get("tipo_producto") == null ? "" : row.get("tipo_producto").toString()));
			report.setFecha_de_llamada((String) validationRow(row.get("fecha_de_llamada") == null ? "" : row.get("fecha_de_llamada").toString()));
			report.setCodigo_pedido((String) validationRow(row.get("codigo_pedido") == null ? "" : row.get("codigo_pedido").toString()));
			report.setCorreo((String) validationRow(row.get("correo") == null ? "" : row.get("correo").toString()));
			report.setOrder_id((String) validationRow(row.get("order_id") == null ? "" : row.get("order_id").toString()));
			report.setOrder_operation_commercial((String) validationRow(row.get("order_operation_commercial") == null ? "" : row.get("order_operation_commercial").toString()));
			report.setOrder_gps_x((String) validationRow(row.get("order_gps_x") == null ? "" : row.get("order_gps_x").toString()));
			report.setOrder_gps_y((String) validationRow(row.get("order_gps_y") == null ? "" : row.get("order_gps_y").toString()));
			report.setUser_atis((String) validationRow(row.get("user_atis") == null ? "" : row.get("user_atis").toString()));
			report.setAddress_departamento_cod((String) validationRow(row.get("address_departamento_cod") == null ? "" : row.get("address_departamento_cod").toString()));
			report.setAddress_departamento((String) validationRow(row.get("address_departamento") == null ? "" : row.get("address_departamento").toString()));
			report.setAddress_provincia_cod((String) validationRow(row.get("address_provincia_cod") == null ? "" : row.get("address_provincia_cod").toString()));
			report.setAddress_provincia((String) validationRow(row.get("address_provincia") == null ? "" : row.get("address_provincia").toString()));
			report.setAddress_distrito_cod((String) validationRow(row.get("address_distrito_cod") == null ? "" : row.get("address_distrito_cod").toString()));
			report.setAddress_distrito((String) validationRow(row.get("address_distrito") == null ? "" : row.get("address_distrito").toString()));
			report.setAddress_principal((String) validationRow(row.get("address_principal") == null ? "" : row.get("address_principal").toString()));
			report.setAddress_calle_atis((String) validationRow(row.get("address_calle_atis") == null ? "" : row.get("address_calle_atis").toString()));
			report.setAddress_calle_nombre((String) validationRow(row.get("address_calle_nombre") == null ? "" : row.get("address_calle_nombre").toString()));
			report.setAddress_calle_numero((String) validationRow(row.get("address_calle_numero") == null ? "" : row.get("address_calle_numero").toString()));
			report.setAddress_cchh_codigo((String) validationRow(row.get("address_cchh_codigo") == null ? "" : row.get("address_cchh_codigo").toString()));
			report.setAddress_cchh_tipo((String) validationRow(row.get("address_cchh_tipo") == null ? "" : row.get("address_cchh_tipo").toString()));
			report.setAddress_cchh_nombre((String) validationRow(row.get("address_cchh_nombre") == null ? "" : row.get("address_cchh_nombre").toString()));
			report.setAddress_cchh_comp_tip_1((String) validationRow(row.get("address_cchh_comp_tip_1") == null ? "" : row.get("address_cchh_comp_tip_1").toString()));
			report.setAddress_cchh_comp_nom_1((String) validationRow(row.get("address_cchh_comp_nom_1") == null ? "" : row.get("address_cchh_comp_nom_1").toString()));
			report.setAddress_cchh_comp_tip_2((String) validationRow(row.get("address_cchh_comp_tip_2") == null ? "" : row.get("address_cchh_comp_tip_2").toString()));
			report.setAddress_cchh_comp_nom_2((String) validationRow(row.get("address_cchh_comp_nom_2") == null ? "" : row.get("address_cchh_comp_nom_2").toString()));
			report.setAddress_cchh_comp_tip_3((String) validationRow(row.get("address_cchh_comp_tip_3") == null ? "" : row.get("address_cchh_comp_tip_3").toString()));
			report.setAddress_cchh_comp_nom_3((String) validationRow(row.get("address_cchh_comp_nom_3") == null ? "" : row.get("address_cchh_comp_nom_3").toString()));
			report.setAddress_cchh_comp_tip_4((String) validationRow(row.get("address_cchh_comp_tip_4") == null ? "" : row.get("address_cchh_comp_tip_4").toString()));
			report.setAddress_cchh_comp_nom_4((String) validationRow(row.get("address_cchh_comp_nom_4") == null ? "" : row.get("address_cchh_comp_nom_4").toString()));
			report.setAddress_cchh_comp_tip_5((String) validationRow(row.get("address_cchh_comp_tip_5") == null ? "" : row.get("address_cchh_comp_tip_5").toString()));
			report.setAddress_cchh_comp_nom_5((String) validationRow(row.get("address_cchh_comp_nom_5") == null ? "" : row.get("address_cchh_comp_nom_5").toString()));
			report.setAddress_via_comp_tip_1((String) validationRow(row.get("address_via_comp_tip_1") == null ? "" : row.get("address_via_comp_tip_1").toString()));
			report.setAddress_via_comp_nom_1((String) validationRow(row.get("address_via_comp_nom_1") == null ? "" : row.get("address_via_comp_nom_1").toString()));
			report.setAddress_via_comp_tip_2((String) validationRow(row.get("address_via_comp_tip_2") == null ? "" : row.get("address_via_comp_tip_2").toString()));
			report.setAddress_via_comp_nom_2((String) validationRow(row.get("address_via_comp_nom_2") == null ? "" : row.get("address_via_comp_nom_2").toString()));
			report.setAddress_via_comp_tip_3((String) validationRow(row.get("address_via_comp_tip_3") == null ? "" : row.get("address_via_comp_tip_3").toString()));
			report.setAddress_via_comp_nom_3((String) validationRow(row.get("address_via_comp_nom_3") == null ? "" : row.get("address_via_comp_nom_3").toString()));
			report.setAddress_via_comp_tip_4((String) validationRow(row.get("address_via_comp_tip_4") == null ? "" : row.get("address_via_comp_tip_4").toString()));
			report.setAddress_via_comp_nom_4((String) validationRow(row.get("address_via_comp_nom_4") == null ? "" : row.get("address_via_comp_nom_4").toString()));
			report.setAddress_via_comp_tip_5((String) validationRow(row.get("address_via_comp_tip_5") == null ? "" : row.get("address_via_comp_tip_5").toString()));
			report.setAddress_via_comp_nom_5((String) validationRow(row.get("address_via_comp_nom_5") == null ? "" : row.get("address_via_comp_nom_5").toString()));
			report.setAddress_via_comp_tip_6((String) validationRow(row.get("address_via_comp_tip_6") == null ? "" : row.get("address_via_comp_tip_6").toString()));
			report.setAddress_via_comp_nom_6((String) validationRow(row.get("address_via_comp_nom_6") == null ? "" : row.get("address_via_comp_nom_6").toString()));
			report.setAddress_via_comp_tip_7((String) validationRow(row.get("address_via_comp_tip_7") == null ? "" : row.get("address_via_comp_tip_7").toString()));
			report.setAddress_via_comp_nom_7((String) validationRow(row.get("address_via_comp_nom_7") == null ? "" : row.get("address_via_comp_nom_7").toString()));
			report.setAddress_referencia((String) validationRow(row.get("address_referencia") == null ? "" : row.get("address_referencia").toString()));
	      result.add(report);
	    }
	 
	    return result;
	  }
	
	public String validationRow(String row) {
		if(row!=null) {
			return row;
		}else {
			return "";
		}
	}

}
