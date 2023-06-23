package cuentasbancarias;

public interface Extraible {
	
	Boolean extraerDinero(String clave, Double monto) throws CuentaInexistenteException;
}
