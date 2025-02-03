package ao.co.intellectus.servico.cafold;

import org.springframework.stereotype.Service;

@Service
public class BiometricoService {
	/*
	@Autowired
	private BiometricoRepository biometricoRepository;

	private NBioBSPJNI.FIR_HANDLE impressaoCapturada;

	public boolean temErro(NBioBSPJNI bsp) {
		return bsp.IsErrorOccured();
	}

	public boolean temDispositivo(NBioBSPJNI bsp) {
		NBioBSPJNI.DEVICE_ENUM_INFO dispositivos = bsp.new DEVICE_ENUM_INFO();
		bsp.EnumerateDevice(dispositivos);
		return (dispositivos.DeviceCount != 0);
	}

	private NBioBSPJNI ligarDispositivo(NBioBSPJNI bsp) {
		bsp.OpenDevice();
		return bsp;
	}

	public NBioBSPJNI capturarImpressao(NBioBSPJNI bsp) {
		impressaoCapturada = bsp.new FIR_HANDLE();
		bsp = this.ligarDispositivo(bsp);
		bsp.Capture(impressaoCapturada);
		return bsp;
	}

	public NBioBSPJNI.FIR_HANDLE getImpressaoCapturada() {
		return impressaoCapturada;
	}

	public boolean salvarImpressao(NBioBSPJNI bsp) {
		NBioBSPJNI.FIR_HANDLE impressao = bsp.new FIR_HANDLE();
		bsp.Capture(impressao);
		if (this.temErro(bsp)) {
			return false;
		} else {
			NBioBSPJNI.FIR_TEXTENCODE impressaoEmTexto = bsp.new FIR_TEXTENCODE();
			bsp.GetTextFIRFromHandle(impressao, impressaoEmTexto);
			Biometrico biometrico = new Biometrico();
			biometrico.setNome("Francisco Lourenço");
			biometrico.setImpressaoEmTexto(impressaoEmTexto.TextFIR);
			this.biometricoRepository.save(biometrico);
			return true;
		}
	}

	public Biometrico pesquisarImpressao(NBioBSPJNI bsp) {
		NBioBSPJNI.INPUT_FIR inputFIR = bsp.new INPUT_FIR();
		Boolean resultado = new Boolean(false);
		NBioBSPJNI.FIR_PAYLOAD payload = bsp.new FIR_PAYLOAD();
		for (Biometrico biometrico : this.biometricoRepository.findAll()) {
			NBioBSPJNI.FIR_TEXTENCODE impressaoEmTexto = bsp.new FIR_TEXTENCODE();
			impressaoEmTexto.TextFIR = biometrico.getImpressaoEmTexto();
			inputFIR.SetTextFIR(impressaoEmTexto);
			bsp.Verify(inputFIR, resultado, payload);
			if (resultado) {
				return biometrico;
			}
		}
		return null;
	}

	public void desligar(NBioBSPJNI bsp) {
		bsp.dispose();
		bsp = null;
	}

	 */
}
