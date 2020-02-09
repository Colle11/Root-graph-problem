
public class MisurazioneTempi {
	
	// attributi di classe
	private static final double k = 0.05; // errore tollerato massimo percentuale del 5%
	private static final double za = 1.96; // valore funzione di distribuzione normale per alpha = 0.05
	private static final double deltaPerc = 0.01; // delta percentuale
	
	// attributi di istanza
	private int dimensione;
	private Graph grafo;
	private long tMin;
	private Graph grafoClone;
	
	// MisurazioneTempi costruttore
	public MisurazioneTempi(int dimBase, int incremento, int volta){
		dimensione = dimBase + incremento * volta;
		grafo = RandomGraphGenerator.getRandomGraph(dimensione);
		grafoClone = null;
	}
	
	// metodo per la determinazione del tempo medio con un errore minore di delta
	public void misurazione(){
		System.out.println("Dimensione del grafo - Nodi: " + dimensione + " Archi: " + grafo.getNumEdges());
		
		int gran = granularita();
		System.out.println("Granularita del sistema: " + gran);
		
		tMin = (long) (gran/k);
		System.out.println("Tempo minimo per un errore massimo percentuale del " + (k*100) + "%: " + tMin);
		
		long t = 0;
		long sum2 = 0;
		int cn = 0;
		int n = 10;
		long m;
		double e;
		double delta;
		
		do{
			for(int i=1; i<=n; i++){
				m = tempoMedioNetto();
				t += m;
				sum2 += m * m;
			}
			
			cn += n;
			e = t/cn;
			delta = (za/Math.sqrt(cn)) * Math.sqrt((sum2/cn) - e*e);
			System.err.println("Dimensione del grafo - Nodi: " + dimensione + " Archi: " + grafo.getNumEdges());
			System.err.println(delta + " > " + (e*deltaPerc));
		}while(delta > (e * deltaPerc));
		
		System.out.println("Cardinalità del campione: " + cn);
		System.out.println();
		System.out.println("Tempo medio di esecuzione: " + e);
		System.out.println("Intervallo di confidenza al 95%: " + delta);
		System.out.println();
		System.out.println("************************************************************");
		System.out.println();
	}
	
	// metodo per calcolare la granularita del sistema
	private int granularita(){
		long t0 = System.currentTimeMillis();
		long t1 = System.currentTimeMillis();
		
		while(t1 == t0){
			t1 = System.currentTimeMillis();
		}
		
		return (int) (t1-t0);
	}
	
	// metodo per il calcolo del tempo medio netto
	private long tempoMedioNetto(){
		long t0;
		long t1;
		long tTara = 0; // tempo totale di esecuzione della tara
		long tNetto = 0; // tempo totale di esecuzione del netto
		long tLordo; // tempo totale di esecuzione del lordo
		long tMedio;
		
		int ripTara = calcolaRipTara();
		int ripLordo = ripTara;
		
		for(int i=1; i<=ripTara; i++){
			t0 = System.currentTimeMillis();
			tara();
			t1 = System.currentTimeMillis();
			tTara += t1 - t0;
		
			t0 = System.currentTimeMillis();
			netto();
			t1 = System.currentTimeMillis();
			tNetto += t1 - t0;
		}
		
		tLordo = tNetto + tTara;
		tMedio = (tLordo / ripLordo) - (tTara / ripTara); // tempo medio di esecuzione
		
		return tMedio;
	}
	
	// metodo per la copia dei dati di input (tara)
	private void tara(){
		grafoClone = grafo.cloneGraph();
	}
	
	// metodo per l'esecuzione dell'algoritmo vero e proprio (netto)
	private void netto(){		
		Graph gSCC = grafoClone.doTarjanSCC();
		RootAndEdges re = gSCC.getRootAndMinEdges();
		grafoClone.addRootAndMinEdges(re);
		grafoClone.doBFS();
	}
	
	// metodo per calcolare il valore rip per la tara
	private int calcolaRipTara(){
		long t0 = 0;
		long t1 = 0;
		int rip = 1;
			
		while((t1-t0) <= tMin){
			rip = rip * 2; // stima di rip con crescita esponenziale
				
			t0 = System.currentTimeMillis();
			for(int i=1; i<=rip; i++){
				tara();
			}
			t1 = System.currentTimeMillis();
		}
			
		int max = rip;
		int min = rip/2;
		int cicliErrati = 5;
			
		while((max-min) >= cicliErrati){
			rip = (max+min)/2; // valore mediano
				
			t0 = System.currentTimeMillis();			
			for(int i=1; i<=rip; i++){
				tara();
			}
			t1 = System.currentTimeMillis();
				
			if((t1-t0) <= tMin){
				min = rip;
			}else{
				max = rip;
			}			
		}
			
		return max;
	}
	
}
