package com.seismaismais.rxtx;

import org.junit.Test;

import br.com.rino.rxtx.SerialCom;
import br.com.rino.rxtx.SerialComLeitura;

public class SerialComEscritaIntegrationTest extends SerialCom {
	
	@Test
	public void test(){     

        //Iniciando leitura serial
		SerialComLeitura serialEscrita = new SerialComLeitura("COM4", 9600, 0);
		serialEscrita.HabilitarEscrita();
		serialEscrita.ObterIdDaPorta();
		serialEscrita.AbrirPorta();
		serialEscrita.EnviarUmaString("#FLASHFORTE");
		serialEscrita.FecharCom();

    }

}
