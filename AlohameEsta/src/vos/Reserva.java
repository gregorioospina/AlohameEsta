package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.*;

public class Reserva 
{
////////////////////////////////////////////
////////////////ATRIBUTOS///////////////////
////////////////////////////////////////////

	/**
	 *  Fecha y hora en la que se efectuo 
	 *  la reserva.
	 */
	@JsonProperty(value = "fechaInicio")
	private Date fechaInicio;
	
	/**
	 * Cancelado, booleano que especifica si ha sido 
	 * cancelada la reserva
	 */
	@JsonProperty(value = "cancelado")
	private Boolean cancelado;
	
	/**
	 * usuarioID del usuario que efectuo la reserva
	 */
	@JsonProperty(value = "usuarioID")
	private Long usuarioID;
	
	/**
	 * operadorID del operador ofreciendo el servicio 
	 */
	@JsonProperty(value = "operadorID")
	private Long operadorID;
	
	/**
	 * habitacionID reservado por el usuario
	 */
	@JsonProperty(value = "habitacionID")
	private Long habitacionID;

////////////////////////////////////////////
////////////////CONSTRUCTOR/////////////////
////////////////////////////////////////////

	public Reserva(@JsonProperty(value = "fechaInicio") Date fechaInicio, @JsonProperty(value = "cancelado") Boolean cancelado, @JsonProperty(value = "usuarioID")Long usuarioID, @JsonProperty(value = "operadorID") Long operadorID, @JsonProperty(value = "habitacionID") Long habitacionID)
	{
		this.fechaInicio = fechaInicio;
		this.cancelado = cancelado;
		this.usuarioID = usuarioID;
		this.operadorID = operadorID;
		this.habitacionID = habitacionID;
	}
	
	
////////////////////////////////////////////
////////////GETTERS AND SETTERS/////////////
////////////////////////////////////////////

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Long getOperadorID() {
		return operadorID;
	}

	public void setOperadorID(Long operadorID) {
		this.operadorID = operadorID;
	}

	public Long getHabitacionID() {
		return habitacionID;
	}

	public void setHabitacionID(Long habitacionID) {
		this.habitacionID = habitacionID;
	}
	
	
	
	
}
