package vos;

import org.codehaus.jackson.annotate.*;

public class Usuario 
{
////////////////////////////////////////////
////////////////ATRIBUTOS///////////////////
////////////////////////////////////////////
	
	/**
	 * Codigo uniandes del usuario
	 */
	@JsonProperty(value = "codigoUniandes")
	private Long codigoUniandes;
	
	/**
	 * Nombre del usuario
	 */
	@JsonProperty(value = "nombre")
	private String nombre;
	
	
////////////////////////////////////////////
////////////////CONSTRUCTOR/////////////////
////////////////////////////////////////////
	
	public Usuario(@JsonProperty(value = "codigoUniandes") Long codigo, @JsonProperty(value = "nombre") String nombre)
	{
		this.codigoUniandes = codigo;
		this.nombre = nombre;
	}
	
////////////////////////////////////////////
////////////GETTERS AND SETTERS/////////////
////////////////////////////////////////////

	
	public Long getCodigoUniandes() {
		return codigoUniandes;
	}

	public void setCodigoUniandes(Long codigoUniandes) {
		this.codigoUniandes = codigoUniandes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
