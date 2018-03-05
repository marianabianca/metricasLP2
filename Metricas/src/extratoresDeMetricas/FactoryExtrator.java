package extratoresDeMetricas;

public class FactoryExtrator {
	
	public ExtratorDeMetricas[] getExtratores() {
		ExtratorDeMetricas[] extratores = {
				new ExtratorDeClasses(),
				new ExtratorDeLinhas(),
				new ExtratorDeMetodos(),
				new ExtratorDeTestes()
		};
		
		return extratores;
	}

}
