package br.uece.eesdevops.profilems.web.entity;

public interface DTO<T,D> {
	public T toDomain();
	public D toDTO(T entity);
}
