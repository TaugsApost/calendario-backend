package br.com.taugs.calendario.utils;

import java.io.Serializable;

import javax.persistence.Entity;

public abstract class BaseEntity<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6835500277403557430L;

	protected static final int INITIAL_VALUE = 1;

	public static final String ID = "id";

	public abstract T getId();

	public abstract void setId(T id);
}
