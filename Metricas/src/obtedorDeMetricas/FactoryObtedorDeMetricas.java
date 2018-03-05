package obtedorDeMetricas;

public class FactoryObtedorDeMetricas {
	
	public ObtedorDeMetricas[] getObtedoresDeMetricas() {
		ObtedorDeMetricas[] obtedoresDeMetricas = {
				new ObtedorDeClasses(),
				new ObtedorDeLinhas(),
				new ObtedorDeMetodos(),
				new ObtedorDeTestes()
		};
		
		return obtedoresDeMetricas;
	}

}
