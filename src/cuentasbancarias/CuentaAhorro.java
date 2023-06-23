package cuentasbancarias;

public class CuentaAhorro extends Cuenta {
	private Integer contador = 0;
	
	public CuentaAhorro(String clave) {
		super(clave);
	}

	@Override
	public Boolean extraerDinero(String clave, Double monto) throws CuentaInexistenteException {
		if(this.saldo >= monto && this.contador < 5 && this.clave.equals(clave)) {
			this.saldo = this.saldo - monto;
			this.contador = this.contador + 1;
			return true;
		} else if(this.saldo >= (monto + 6) && this.contador >= 5 && this.clave.equals(clave)) {
			this.saldo = this.saldo - monto - 6;
			this.contador = this.contador + 1;
			return true;
			
		} else if (this.saldo < (monto + 6)) {
			return false;
		} throw new CuentaInexistenteException("Esa cuenta no existe");
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

}
