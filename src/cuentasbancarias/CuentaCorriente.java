package cuentasbancarias;

public class CuentaCorriente extends Cuenta {
	public Double saldoADeber = 0.0;
	public Double saldoPrestado = 0.0;
	public Double saldoEnDescubierto = 150.0;
	
	public CuentaCorriente(String clave) {
		super(clave);
	}

	@Override
	public Boolean extraerDinero(String clave, Double monto) throws CuentaInexistenteException {
		if(this.saldo >= monto && this.clave.equals(clave)) {
			this.saldo = this.saldo - monto;
			return true;
		} else if (this.saldo < monto && this.saldoEnDescubierto + this.saldo >= monto  && this.clave.equals(clave)) {
			this.saldoPrestado = monto - this.saldo;
			this.saldoEnDescubierto = this.saldoEnDescubierto - this.saldoPrestado;
			this.saldoADeber = ((5 * this.saldoPrestado) / 100) + this.saldoPrestado;
			this.saldo = 0.0 - this.saldoADeber;
			return true;
		} else if(this.saldoEnDescubierto + this.saldo < monto && this.clave.equals(clave)) {
			return false;
			
		}
		throw new CuentaInexistenteException("Esa cuenta no existe");
	}

	public Double getSaldoADeber() {
		return saldoADeber;
	}



}

