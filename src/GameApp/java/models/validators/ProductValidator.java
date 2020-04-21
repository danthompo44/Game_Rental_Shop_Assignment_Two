package GameApp.java.models.validators;

import GameApp.java.general.exceptions.ProductException;

public class ProductValidator {
    private static StringBuilder errorsFound = new StringBuilder();

    public static void validate(String description, String cost) throws Exception{//creates exception string, uses booleans to decide when to add extra messages
        errorsFound.setLength(0);

        if(isNotNull("Description", description)){
            isEmpty("Description", description);
        }
        if(isNotNull("Cost", cost)){
            if(!isEmpty("Cost", cost)){
                isNotANumber("Cost", cost);
            }
        }
        if(errorsFound.length()>0){
            throw new ProductException(errorsFound.toString());
        }
    }

    private static boolean isNotNull(String attribute, String value){
        if(value==null){
            errorsFound.append(attribute).append(" can't be null!\n");
            return false;
        }
        return true;
    }

    private static boolean isEmpty(String attribute, String value){
        if(value.isEmpty()){
            errorsFound.append(attribute).append(" can't be empty!\n");
            return true;
        }
        return false;
    }

    private static void isNotANumber(String attribute, String value){
        try{
            Double.parseDouble(value);
        }
        catch (Exception e){
            errorsFound.append(attribute).append(" is not a number!");
        }
    }
}
