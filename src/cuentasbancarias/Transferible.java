package cuentasbancarias;

public interface Transferible {
	Transferencia transferenciaBancaria(String clave, Double monto, Cuenta cuenta) throws CuentaInexistenteException;
}
