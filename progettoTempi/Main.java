
public class Main {

	public static void main(String[] args) {
		for(int i=0; i<16; i++){
			MisurazioneTempi mt = new MisurazioneTempi(500, 100, i);
			mt.misurazione();
		}
	}
	
}
