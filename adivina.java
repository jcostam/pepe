import  java.util.Random;
   import  java.util.Scanner;

   public  class  Adivina  {
	public  static  void  main(String[]  args)  throws  java.io.IOException  { 
 		Random  generador  =  new  Random();
		Scanner  scanner  =  new  Scanner(System.in);
		int  numero_ordenador  =  generador.nextInt(100)  +  1;
		int  numero_humano;
		int  intentos  =  0;
		System.out.println( "Adivina  el  número  del  1  al  100" );
		while  (intentos  <  3)  {
			// Pregunta  número 
			numero_humano  =  scanner.nextInt();
			// incrementamos los intentos			
intentos++;
			// Da  pistas
			if (numero_ordenador  <  numero_humano)  
				{ System.out.println( "Menos" );
			} else  if (numero_ordenador  >  numero_humano)  
				{ System.out.println( "Mas" );
			} else  { 
				System.out.println( "¡¡CORRECTO!!" );
				return;
			}
		}
		System.out.println( "NOOOOO,  ¡HAS  FRACASADO! --> El numero es:"+numero_ordenador );
	}
   }
