public class Caca {
public static double corr(double[] sig1, double[] sig2, int lag) {
        double sum = 0;
        for (int i = 0; i < sig1.length; i++) {
            int shiftedIndex = (i + lag) % sig2.length;
            sum += sig1[i] * sig2[shiftedIndex];
        }
        return sum;
    }

    public static double[] crosscorrelationNaive(double[] sig1, double[] sig2) {
        int N = sig1.length;
        double[] result = new double[N];
        for (int lag = 0; lag < N; lag++) {
            result[lag] = corr(sig1, sig2, lag);
        }
        return result;
    }

    public class CrossCorrelationOptimized {
        public static double[] crossCorrelation(double[] signal1, double[] signal2) {
            int len1 = signal1.length;
            int len2 = signal2.length;
            int maxOffset = len1 + len2 - 1; // Nombre total de décalages possibles
            double[] corr = new double[maxOffset];

            for (int offset = 0; offset < maxOffset; offset++) {
                double sum = 0.0;

                for (int i = 0; i < len1; i++) {
                    int j = offset - i; // Index du deuxième signal

                    if (j >= 0 && j < len2) {
                        sum += signal1[i] * signal2[j];
                    }
                }

                corr[offset] = sum;
            }



        }
