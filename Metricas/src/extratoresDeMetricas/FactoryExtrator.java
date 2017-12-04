package extratoresDeMetricas;

public class FactoryExtrator {
	
	public ExtratorDeMetricas[] getExtratores() {
		ExtratorDeMetricas[] extratores = {
				new ExtratorDeClasses(),
				new ExtratorDeLinhas()
		};
		
		return extratores;
	}

}
