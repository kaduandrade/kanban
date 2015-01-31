package com.kanban.util;

public class Util {
	
	public boolean ehInteiro( String s ) {
		
		// cria um array de char
		char[] c = s.toCharArray();
		boolean d = true;
		
		for ( int i = 0; i < c.length; i++ ) {
			
			// verifica se o char não é um dígito
			if ( !Character.isDigit( c[ i ] ) ) {
				
				d = false;
				break;
				
			}
			
		}
		
		return d;
		
	}
	
}