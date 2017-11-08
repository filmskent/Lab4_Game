package ui;

public class ScoreParsingException extends Exception{

	private int errorType;
	
	public ScoreParsingException(int errorType){
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		if(errorType == 0){
			return "No record score";
		}
		if(errorType == 1){
			return "Wrong record format";
		}else{
			return "Error";
		}
	}
}
