package cuentasbancarias;

public class CuentaSueldo extends Cuenta {

	public CuentaSueldo(String clave) {
		super(clave);
	}

	@Override
	public Boolean extraerDinero(String clave, Double monto) throws CuentaInexistenteException {
		if(this.saldo >= monto && this.clave.equals(clave)) {
			this.saldo = this.saldo - monto;
			return true;
		} else if(this.saldo < monto) {
			return false;
		}
		 throw new CuentaInexistenteException("Esa cuenta no existe");
	}
	
	
}
