package com.facturacion.facturacion_julio.DTO;

import org.springframework.stereotype.Component;

@Component
public class FacturaDTO {

    private String fecha;
    private double monto;
    private String detalle;
    private Long cliente;
    
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public Long getCliente() {
        return cliente;
    }
    public void setCliente(Long cliente) {
        this.cliente = cliente;
    }

    

}
