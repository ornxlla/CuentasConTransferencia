package cuentasbancarias;

public interface Depositable {
	 Boolean depositarDinero(String clave, Double monto) throws CuentaInexistenteException;
}
