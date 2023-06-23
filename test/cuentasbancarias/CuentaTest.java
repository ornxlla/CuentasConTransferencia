package cuentasbancarias;

import static org.junit.Assert.*;

import org.junit.Test;

public class CuentaTest {

	
	@Test
	public void queSePuedaDepositarDineroDeUnaCuenta() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		assertTrue(cs.depositarDinero("1234", 300.0));
		
		Double valorEsperado = 300.0;
		Double valorObtenido = cs.getSaldo();
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test (expected = CuentaInexistenteException.class)
	public void queNoSePuedaDepositarDineroDeUnaCuenta() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		assertFalse(cs.depositarDinero("12345", 300.0));
	}
	
	@Test
	public void queSePuedaExtraerDineroDeLaCuentaSaldo() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		cs.depositarDinero("1234",300.0);
		assertTrue(cs.extraerDinero("1234",150.0));
	}

	@Test
	public void queNoSePuedaExtraerDineroDeLaCuentaSaldoPorSuperarElSaldo() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		cs.depositarDinero("1234", 300.0);
		assertFalse(cs.extraerDinero("1234", 300.1));
	}
	
	@Test
	public void queNoSePuedaExtraerDineroDeLaCuentaPorClaveIncorrecta() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		cs.depositarDinero("1234", 300.0);
		assertFalse(cs.extraerDinero("12345", 300.1));
	}
	
	@Test
	public void queSePuedaExtraerDineroDeUnaCuentaAhorro() throws CuentaInexistenteException {
		Cuenta ca = new CuentaAhorro("1234");
		ca.depositarDinero("1234", 100.0);
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
	
		Double valorEsperado = 64.0;
		Double valorObtenido = ca.getSaldo();
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queNoSePuedaExtraerDineroDeLaCuentaAhorroPorSuperarElSaldo() throws CuentaInexistenteException {
		Cuenta ca = new CuentaAhorro("1234");
		ca.depositarDinero("1234", 100.0);
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertTrue(ca.extraerDinero("1234", 5.0));
		assertFalse(ca.extraerDinero("1234", 70.0));	
		
	}
	
	@Test
	public void queSePuedaExtraerDineroDeUnaCuentaCorrienteConSaldoEnDescubierto() throws CuentaInexistenteException {
		Cuenta cc = new CuentaCorriente("1234");
		cc.depositarDinero("1234", 100.0);
		assertTrue(cc.extraerDinero("1234", 200.0));
		
	}
	
	@Test
	public void queSePuedaTransferirDineroAOtraCuenta() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		Cuenta cc = new CuentaCorriente("1235");
		
		cs.depositarDinero("1234", 1000.0);
		Transferencia t = cs.transferenciaBancaria("1234", 500.0, cc);
	
		Double valorEsperado = 500.0;
		Double valorObtenido = t.getCantidadQueSeTransfirio();
		assertEquals(valorEsperado, valorObtenido);
		
		Integer valorEsperadoDos = 1;
		Integer valorObtenidoDos = t.getNumeroDeTransferencia();
		assertEquals(valorEsperadoDos, valorObtenidoDos);
		
		Double valorEsperadoTres = 500.0;
		Double valorObtenidoTres = cc.getSaldo();
		assertEquals(valorEsperadoTres, valorObtenidoTres);
	}
	
	@Test (expected = CuentaInexistenteException.class)
	public void queNoSePuedaTransferirDineroAOtraCuenta() throws CuentaInexistenteException {
		Cuenta cs = new CuentaSueldo("1234");
		Cuenta cc = new CuentaCorriente("1235");
		
		cs.depositarDinero("1234", 1000.0);
		Transferencia t = cs.transferenciaBancaria("1237", 500.0, cc);
	}
}
