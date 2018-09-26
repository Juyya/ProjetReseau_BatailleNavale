package test;

public enum orientation {
    V,
    H;
    
    static orientation orientpour (int i){
    	switch(i){
    	case 0:
    		return V;
    	case 1:
    		return H;
    	default:
    		throw new IllegalArgumentException();
    	}
    }
}
