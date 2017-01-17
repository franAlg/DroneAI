package pruebas_fuzzy;

//import net.sourceforge.jFuzzyLogic.rule.Rule;

public class pruebaEvaluar {
	
	
	public static void main(String[] args) {
		float dist = 10.0f;
		float angObj = 100.9f;
		float angDest = 55.5f;
		
		float valorDevuelto;
		
		obstacleAvoidanceComentado OA = new obstacleAvoidanceComentado();
		valorDevuelto = OA.evaluar(dist, angObj, angDest);
		
		System.out.println("\n\n valor devuelto  " + valorDevuelto );
		
		valorDevuelto= comprobarFloat(valorDevuelto);
		
		System.out.println("\n valor devuelto: " + valorDevuelto );
		

		
		// Show each rule (and degree of support)
   //  	for( Rule r : OA.fis.getFunctionBlock("obstacleAvoidance").getFuzzyRuleBlock("No1").getRules() )
    //	   System.out.println(r);
		
		
		
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