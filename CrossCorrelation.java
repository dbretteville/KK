import java.util.Random;

class CrossCorrelation1 {

    public static double[] CrossCorrelation1(double[] signal1, double[] signal2) {
        int len1 = signal1.length;
        int len2 = signal2.length;
        int maxOffset = len1 + len2 - 1; // Nombre total de décalages possibles
        double[] corr = new double[maxOffset];

        double[] reversedSignal2 = new double[len2];
        for (int i = 0; i < len2; i++) {
            reversedSignal2[i] = signal2[len2 - 1 - i];
        }

        // Calcul de la corrélation pour chaque décalage
        for (int offset = 0; offset < maxOffset; offset++) {
            double sum = 0.0;

            for (int i = 0; i < len1; i++) {
                int j = offset - i;


                if (j >= 0 && j < len2) {
                    sum += signal1[i] * reversedSignal2[j];
                }
            }
            corr[offset] = sum;
        }
        return corr;
    }
}
class CrossCorrelation2 {
    public static double[] CrossCorrelation2(double[] signal1, double[] signal2) {
        int n1 = signal1.length;
        int n2 = signal2.length;
        int n = n1 + n2 - 1;

        double[] result = new double[n];

        // Calcul de la corrélation croisée pour les lags positifs
        for (int lag = 0; lag < n2; lag++) {
            double sum = 0;
            for (int i = 0; i < n1; i++) {
                if (i + lag < n2) {
                    sum += signal1[i] * signal2[i + lag];
                }
            }
            result[n1 - 1 + lag] = sum;
        }

        // Calcul de la corrélation croisée pour les lags négatifs
        for (int lag = 1; lag < n1; lag++) {
            double sum = 0;
            for (int i = 0; i < n2; i++) {
                if (i + lag < n1) {
                    sum += signal1[i + lag] * signal2[i];
                }
            }
            result[n1 - 1 - lag] = sum;
        }
        double[] resultFinal = new double[result.length];
        for (int i = 0; i < result.length; i++) {
            resultFinal[i] = result[result.length - i - 1];
        }

        return resultFinal;
    }

}

public class CrossCorrelation {
    public static void main(String[] args) {

        Random random = new Random();
        long OldGlobalTime = 0;
        for(int i=0;i<1000;i++) {
            System.out.print("Iteration number : ");
            System.out.println(i+1);
            int[] tab_size
                    = {1000,2000,4000,6000,8000,10000,12000,14000,15000,16000,18000,20000};
            for (int j = 0; j < tab_size.length; j++) {
                double[] sig1 = new double[tab_size[j]];
                double[] sig2 = new double[tab_size[j]];
                for (int k = 0; k < tab_size[j]; k++) {
                    sig1[k] = random.nextDouble();
                    sig2[k] = random.nextDouble();
                }

                double[] r1 = Profiler.analyse(CrossCorrelation1::CrossCorrelation1,
                        sig1, sig2, "crossCorrelation1");
                System.out.println("with signal length of "+tab_size[j]);
                double[] r2 = Profiler.analyse(CrossCorrelation2::CrossCorrelation2,
                        sig1, sig2, "crossCorrelation2");
                System.out.println("with signal length of "+tab_size[j]);
            }
            System.out.print("Iteration number ");
            System.out.print(i+1);
            System.out.println(" succeded in "
                    +(Profiler.getGlobalTime()-OldGlobalTime)/1e9+" s");
            OldGlobalTime = Profiler.getGlobalTime();
            System.out.println("");
        }
        System.out.println("temps total : "+Profiler.getGlobalTime()/1e9+" s");
    }
}




