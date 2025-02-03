package ao.co.intellectus.servico.guias;

public class GerarNumeroDeGuia {
	public static String gerarNumeroGuia(String definitivo, Integer lectivo, Long proximoNumeroInteiro) {
		if(proximoNumeroInteiro>=1      && proximoNumeroInteiro<=9)
			definitivo=lectivo+"00000"  +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10     && proximoNumeroInteiro<=99)
			definitivo=lectivo+"0000"   +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100    && proximoNumeroInteiro<=999)
			definitivo=lectivo+"000"    +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=1000   && proximoNumeroInteiro<=9999)
			definitivo=lectivo+"00"     +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10000  && proximoNumeroInteiro<=99999)
			definitivo=lectivo+"0"      +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100000 && proximoNumeroInteiro<=999999)
			definitivo=lectivo          +proximoNumeroInteiro.toString();
		return definitivo;
	}
}
