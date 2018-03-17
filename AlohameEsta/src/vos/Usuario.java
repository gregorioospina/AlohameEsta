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
	
	/**
	 * Metodo constructr de la clase usuario
	 * <b> post: </b> Crea el usuario con los valores de parametro.
	 * @param codigo
	 * @param nombre
	 */
	public Usuario(@JsonProperty(value = "codigoUniandes") Long codigo, @JsonProperty(value = "nombre") String nombre)
	{
		this.codigoUniandes = codigo;
		this.nombre = nombre;
	}
	
////////////////////////////////////////////
////////////GETTERS AND SETTERS/////////////
////////////////////////////////////////////

	/**
	 * @return the codigoUniandes
	 */
	public Long getCodigoUniandes() {
		return codigoUniandes;
	}


	/**
	 * @param codigoUniandes the codigoUniandes to set
	 */
	public void setCodigoUniandes(Long codigoUniandes) {
		this.codigoUniandes = codigoUniandes;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
