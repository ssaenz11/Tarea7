import java.util.ArrayList;
import java.util.Scanner;

public class Curso 
{

	private ArrayList ordenDeMayorAsistencia;

	private boolean[] conjuntoDiasOfrecidos;

	private boolean[][] conjuntoPersonasDias;

	private int minDiasADictar;

	private int numPersonas;

	public Curso(int diasOfrecidos, int cantidadPersonas)
	{
		conjuntoDiasOfrecidos = new boolean[diasOfrecidos];

		conjuntoPersonasDias = new boolean[cantidadPersonas][diasOfrecidos];

		ordenDeMayorAsistencia = new ArrayList();

		numPersonas = cantidadPersonas;

		minDiasADictar = 0;
	}

	/**
	 * Método que ordena las filas con mayor cantidad 
	 * de personas que pueden asistir en un solo día
	 * 
	 */

	public void organizarDiasMayorMenor(ArrayList ordenDeMayorAsistencia)
	{

		for(int i = 0; i< conjuntoDiasOfrecidos.length; i++)
		{
			int n =0;


			for(int j = 0; j<getNumPersonas(); j++)
			{

				if(conjuntoPersonasDias[j][i]== true) n++; System.out.println(n+"entro");
			}
			Dia dia = new Dia(i,n);
			ordenDeMayorAsistencia.add(dia);
		}
	}

	public static void sort(ArrayList<Dia> list) {
		for (int size = list.size(); size != 1; --size) {
			for (int i = 0; i < size - 1; i++) {
				Dia temp1 = list.get(i + 1);
				Dia temp2 = list.get(i);
				if (temp2.getCantidadAsistencia() < temp1.getCantidadAsistencia()) {
					list.set(i, temp1);
					list.set(i + 1, temp2);
				}
			}
		}
	}

	public int buscarDiasRestantes(ArrayList list)
	{
//		Dia dia0 = (Dia) ordenDeMayorAsistencia.get(0);

		Dia dia = (Dia) ordenDeMayorAsistencia.get(0);

		
		
			int cantidad =1;

			for(int i = 1; i< ordenDeMayorAsistencia.size(); i++)
			{
				Dia dia1 = (Dia) ordenDeMayorAsistencia.get(i);

				int numeroDia = dia1.getDia();



				if(conjuntoDiasOfrecidos[numeroDia]!= false) 
				{
					int restante = numPersonas- dia.getCantidadAsistencia();
					
					System.out.println("restantes"+restante);

					if(restante == 0)
					{
						return 1;
					}

					for(int j = 0; j<list.size(); j++)
					{
						int x = (int) list.get(j);
						if(conjuntoPersonasDias[x][numeroDia]== true)
						{
							restante--;
							list.remove(j);
						}

						if(restante == 0) return cantidad++;

					}

				}
			}
		
		return minDiasADictar;
	}





	public int minDias()
	{
		int diasMin=0;

		organizarDiasMayorMenor(ordenDeMayorAsistencia);


		sort(ordenDeMayorAsistencia);

		//		for(int i = 0;i<ordenDeMayorAsistencia.size();i++){
		//			Dia d = (Dia) ordenDeMayorAsistencia.get(i);
		//			System.out.println(d.getCantidadAsistencia()+"cantidad " +d.getDia()+ " día");
		//		}

		Dia d = (Dia) getOrdenDeMayorAsistencia().get(0);

		ArrayList list = new ArrayList<>();
		int dia = d.getDia();

		for(int i = 0; i< numPersonas; i++)
		{
			if(conjuntoPersonasDias[i][dia]== false)
			{
				list.add(i);
			}
		}

		diasMin = buscarDiasRestantes( list);

		return diasMin ;
	}





	public boolean[] getConjuntoDiasOfrecidos()
	{
		return conjuntoDiasOfrecidos;
	}

	public void setConjuntoDiasOfrecidos(boolean[] conjuntoDiasOfrecidos) 
	{
		this.conjuntoDiasOfrecidos = conjuntoDiasOfrecidos;
	}

	public boolean[][] getConjuntoPersonasDias() {
		return conjuntoPersonasDias;
	}

	public void setConjuntoPersonasDias(boolean[][] conjuntoPersonasDias) {
		this.conjuntoPersonasDias = conjuntoPersonasDias;
	}

	public int getMinDiasADictar() 
	{
		return minDiasADictar;
	}

	public void setMinDiasADictar(int numDiasADictar) 
	{
		this.minDiasADictar = numDiasADictar;
	}

	public ArrayList getOrdenDeMayorAsistencia() 
	{
		return ordenDeMayorAsistencia;
	}
	public void setOrdenDeMayorAsistencia(ArrayList ordenDeMayorAsistencia)
	{
		this.ordenDeMayorAsistencia = ordenDeMayorAsistencia;
	}
	public int getNumPersonas() {
		return numPersonas;
	}
	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}










	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		System.out.println("Cuántas personas están interesadas en el curso:");

		Scanner sc = new Scanner(System.in);
		int source = sc.nextInt();

		System.out.println("Cuántos días considera para hacer los cursos:");

		Scanner sc1 = new Scanner(System.in);
		int source1 = sc.nextInt();

		Curso curso = new Curso(source1, source);

		System.out.println("Se está generando aleatoriamente los días sugerido para el curos y los días sugeridos por las personas");


		// se generá el arreglo de 
		for(int i = 0; i<curso.getConjuntoDiasOfrecidos().length; i++)
		{
			long ram = Math.round(Math.random());

			if( ram %2 == 0 )
			{
				curso.getConjuntoDiasOfrecidos()[i] = true;
			}
			else
			{
				curso.getConjuntoDiasOfrecidos()[i] = false;
			}
		}



		System.out.println("Por favor espere...");

		for(int i = 0; i<source; i++)
		{
			for (int j = 0; j< source1; j++)
			{
				long ram = Math.round(Math.random());

				if( ram %2 == 0 )
				{
					curso.getConjuntoPersonasDias()[i][j] = true;
				}
				else if(ram %2 != 0) 
				{
					curso.getConjuntoPersonasDias()[i][j] = false;
				}
			}		
		}

		//		for(int i = 0; i<source; i++)
		//		{
		//			for (int j = 0; j< source1; j++)
		//			{
		//				System.out.println(j+" "+ curso.getConjuntoPersonasDias()[j][i]+" "+ i);
		//			}		
		//		}

		System.out.println("Se necesita un total de: "+ curso.minDias()+ " días");

	}

}
