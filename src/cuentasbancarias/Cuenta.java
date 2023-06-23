package cuentasbancarias;

import java.util.Objects;

public abstract   class Cuenta implements Extraible, Depositable, Transferible {
	Integer contadorTransferencias = 0;
	protected String clave;
	protected Double saldo = 0.0;

	public Cuenta(String clave) {
		this.clave = clave;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public Double getSaldo() {
		return saldo;
	}

	@Override
	public abstract Boolean extraerDinero(String clave, Double monto) throws CuentaInexistenteException;

	@Override
	public Boolean depositarDinero(String clave, Double monto) throws CuentaInexistenteException {
		if(this.clave.equals(clave)) {
			this.saldo = this.saldo + monto;
			return true;
		}
		throw new CuentaInexistenteException("Esa cuenta no existe");
	}

	@Override
	public int hashCode() {
		return Objects.hash(clave);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(clave, other.clave);
	}

	@Override
	public Transferencia transferenciaBancaria(String clave, Double monto, Cuenta cuenta) throws CuentaInexistenteException {
		if(this.saldo >= monto && this.clave.equals(clave)) {
			this.contadorTransferencias++;
			Transferencia transferencia = new Transferencia();
			transferencia.setNumeroDeTransferencia(this.contadorTransferencias);
			transferencia.setCantidadQueSeTransfirio(monto);
			this.saldo = this.saldo - monto;
			cuenta.saldo = cuenta.saldo + monto;
			return transferencia;
		} else if (this.saldo >= monto && !this.clave.equals(clave)) {
			throw new CuentaInexistenteException("Esa cuenta no existe");
		}
		return null;
	}


	
}
