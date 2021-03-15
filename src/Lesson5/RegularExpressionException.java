package Lesson5;

public class RegularExpressionException extends RuntimeException{

    private String regExp;
    public String getRegularExpression(){
        return regExp;
    }

    public RegularExpressionException(String message, String regularExpression){

        super(message);
        regExp=regularExpression;
    }
}
