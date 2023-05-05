package usermicroservice.fidelitypoints;

public class FidelityPoints {

    public static  int calculatingFidelityPoints(Double cart){
        int fidelityPoints = 0;

        if (cart >= 20 && cart < 30){
            fidelityPoints += 1;
        }else if (cart >= 30 && cart < 50 ){
            fidelityPoints += 3;

        }else if (cart >= 50 && cart < 100 ){
            fidelityPoints += 5;
        }else if (cart >= 100){
            fidelityPoints += 10;
        }
        return fidelityPoints;
    }
}
