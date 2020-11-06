package br.com.controleaula.framework;

import java.io.Serializable;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = -5400583355853923268L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
