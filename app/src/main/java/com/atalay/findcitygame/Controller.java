package com.atalay.findcitygame;

public class Controller {

    public String fixName(String name) {
        if (name.isEmpty()) {
            return "";
        } else {
            String tempName = name.substring(0, 1).toUpperCase() + name.substring(1);
            return tempName;
        }

    }
    public int  checkGivenLetterNumber(String comeCity){
        int givenLetterNumber;
        if(comeCity.length()>=5 && comeCity.length()<=7){givenLetterNumber = 1;}
        else if (comeCity.length()<10 && comeCity.length()>=8){givenLetterNumber = 2;}
        else if(comeCity.length()>10 ){givenLetterNumber = 3;}
        else{ givenLetterNumber = 0;}
        return  givenLetterNumber;
    }

}
