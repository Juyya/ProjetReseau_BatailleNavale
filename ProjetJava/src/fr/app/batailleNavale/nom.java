package fr.app.batailleNavale;

public enum nom {
    PORTEAVION,
    CROISEUR,
    CONTRETORPILLEUR,
    SOUSMARIN,
    TORPILLEUR;

    static nom nompour (int i){
    	switch(i){
    		case 0:
    			return PORTEAVION;
    		case 1:
    			return CROISEUR;
    		case 2: 
    			return CONTRETORPILLEUR;
    		case 3:
    			return SOUSMARIN;
    		case 4:
    			return TORPILLEUR;
    		default:
    			throw new IllegalArgumentException();
    	}
    }
}
