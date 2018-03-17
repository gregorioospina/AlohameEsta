package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ViviendaUni extends Operador {

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "salasDeEstudioCosto")
	private Integer salasDeEstudioCosto;
	
	@JsonProperty(value = "restauranteCosto")
	private Integer restauranteCosto;
	
	@JsonProperty(value = "gimnasioCosto")
	private Integer gimnasioCosto;
	
	@JsonProperty(value = "ubicacion")
	private String ubicacion;
	
	@JsonProperty(value = "capacidad")
	private Short capacidad;

	/**
	 * @param idOperador
	 * @param cupoTotal
	 * @param correo
	 * @param nombre
	 * @param id
	 * @param salasDeEstudioCosto
	 * @param restauranteCosto
	 * @param gimnasioCosto
	 * @param ubicacion
	 * @param capacidad
	 */
	public ViviendaUni(@JsonProperty(value = "idOperador")Long idOperador, @JsonProperty(value = "cupoTotal")Integer cupoTotal,@JsonProperty(value = "correo") String correo, @JsonProperty(value = "nombre")String nombre, @JsonProperty(value = "id")Long id,
			@JsonProperty(value = "salasDeEstudioCosto") Integer salasDeEstudioCosto, @JsonProperty(value = "restauranteCosto")Integer restauranteCosto, @JsonProperty(value = "gimansioCosto")Integer gimnasioCosto,@JsonProperty(value = "ubicacion") String ubicacion,
			Short capacidad) {
		super(idOperador, cupoTotal, correo, nombre);
		this.id = id;
		this.salasDeEstudioCosto = salasDeEstudioCosto;
		this.restauranteCosto = restauranteCosto;
		this.gimnasioCosto = gimnasioCosto;
		this.ubicacion = ubicacion;
		this.capacidad = capacidad;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the salasDeEstudioCosto
	 */
	public Integer getSalasDeEstudioCosto() {
		return salasDeEstudioCosto;
	}

	/**
	 * @param salasDeEstudioCosto the salasDeEstudioCosto to set
	 */
	public void setSalasDeEstudioCosto(Integer salasDeEstudioCosto) {
		this.salasDeEstudioCosto = salasDeEstudioCosto;
	}

	/**
	 * @return the restauranteCosto
	 */
	public Integer getRestauranteCosto() {
		return restauranteCosto;
	}

	/**
	 * @param restauranteCosto the restauranteCosto to set
	 */
	public void setRestauranteCosto(Integer restauranteCosto) {
		this.restauranteCosto = restauranteCosto;
	}

	/**
	 * @return the gimnasioCosto
	 */
	public Integer getGimnasioCosto() {
		return gimnasioCosto;
	}

	/**
	 * @param gimnasioCosto the gimnasioCosto to set
	 */
	public void setGimnasioCosto(Integer gimnasioCosto) {
		this.gimnasioCosto = gimnasioCosto;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the capacidad
	 */
	public Short getCapacidad() {
		return capacidad;
	}

	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(Short capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
}
