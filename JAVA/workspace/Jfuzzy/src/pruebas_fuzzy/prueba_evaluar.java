package pruebas_fuzzy;

import fuzzy.obstacleAvoidance;

public class prueba_evaluar {
	
	
	public static void main(String[] args) {
		float dist = 9.1f;
		float angObj = -53.33f;
		float angDest = -300f;
		
		float valorDevuelto;
		
		obstacleAvoidance OA = new obstacleAvoidance();
		valorDevuelto = OA.evaluar(dist, angObj, angDest);
		
		System.out.println("\n\n\n valor devuelto shurmanico: " + valorDevuelto );
		
		valorDevuelto = -44f;
		System.out.println("\n\n\n valor devuelto shurmanico: " + valorDevuelto );
		valorDevuelto= comprobarFloat(valorDevuelto);
		
		System.out.println("\n\n\n valor devuelto shurmanico: " + valorDevuelto );
		
		
		
	}
	
	private static float comprobarFloat(final float angDrone) {
		float out =0.1f;
		String convertido = String.valueOf(angDrone);
		
		convertido= convertido.replace(".0",".1");	
		
		out= stringToFloat(convertido);
		
		if ((out %1.0)==0){
			out = out + 0.2f;
		}
		
		return out;
	}

	private static float stringToFloat (final String convertir){
		float out = 0.1f;
		String convertido;
		int posicionDecimal;
		
		posicionDecimal = convertir.indexOf('.')+2;
		convertido= convertir.substring(0, posicionDecimal);
		
		try{
			out = Float.parseFloat(convertido);
		} catch (NumberFormatException e){
			return out=6789f;
		}
		
		return out;		
	}
	

}
