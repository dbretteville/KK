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






    public static double[] crosscorrelationFFT(double[] sig1, double[] sig2) {
        int N = sig1.length;
        // Perform FFT
        Complex[] fftSig1 = FFT.forward(sig1);
        Complex[] fftSig2 = FFT.forward(sig2);

        // Element-wise multiplication with conjugate
        Complex[] product = new Complex[N];
        for (int i = 0; i < N; i++) {
            product[i] = fftSig1[i].multiply(fftSig2[i].conjugate());
        }

        // Perform inverse FFT
        double[] result = FFT.inverse(product);

        return result;
    }
}
