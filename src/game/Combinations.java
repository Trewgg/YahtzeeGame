package game;

public class Combinations {

    boolean OnePair(int k1, int k2, int k3, int k4, int k5){
        return (k1 == k2) || (k1 == k3) || (k1 == k4) || (k1 == k5) ||
               (k2 == k3) || (k2 == k4) || (k2 == k5) ||
               (k3 == k4) || (k3 == k5) ||
               (k4 == k5);
    }

    boolean TwoPair(int k1, int k2, int k3, int k4, int k5){
        return (k1 == k2 && k3 == k4) ||
               (k1 == k2 && k4 == k5) ||
               (k1 == k3 && k2 == k4) ||
               (k1 == k3 && k4 == k5) ||
               (k1 == k4 && k2 == k3) ||
               (k1 == k4 && k3 == k5) ||
               (k1 == k5 && k2 == k3) ||
               (k1 == k5 && k3 == k4) ||
               (k2 == k3 && k4 == k5) ||
               (k3 == k4 && k2 == k5);
    }

    boolean Triple(int k1, int k2, int k3, int k4, int k5){
        return (k1 == k2 && k1 == k3) ||
               (k1 == k3 && k1 == k4) ||
               (k1 == k4 && k1 == k5) ||
               (k2 == k3 && k2 == k4) ||
               (k2 == k4 && k2 == k5) ||
               (k3 == k4 && k3 == k5);
    }

    boolean ShortStreet(int k1, int k2, int k3, int k4, int k5){
        return (k1 == 1 && k2 == 2 && k3 == 3 && k4 == 4) ||
               (k1 == 2 && k2 == 3 && k3 == 4 && k4 == 5) ||
               (k1 == 3 && k2 == 4 && k3 == 5 && k4 == 6) ;
    }

    boolean LongStreet(int k1, int k2, int k3, int k4, int k5){
        return (k1 == 1 && k2 == 2 && k3 == 3 && k4 == 4 && k5 == 5) ||
               (k1 == 2 && k2 == 3 && k3 == 4 && k4 == 5 && k5 == 6);
    }

    boolean FullHouse(int k1, int k2, int k3, int k4, int k5) {
        return Triple(k1, k2, k3, k4, k5) && TwoPair(k1, k2, k3, k4, k5);
    }

    boolean Kare(int k1, int k2, int k3, int k4, int k5){
        return (k1 == k2 && k1 == k3  && k1 == k4) ||
               (k1 == k3 && k1 == k4  && k1 == k5) ||
               (k1 == k2 && k1 == k3  && k2 == k5) ||
               (k1 == k2 && k1 == k4  && k2 == k5) ||
               (k2 == k3 && k2 == k4  && k2 == k5);
    }

    boolean Poker(int k1, int k2, int k3, int k4, int k5){
        return (k1 == k2 && k1 == k3 && k1 == k4  && k1 == k5);
    }

}
