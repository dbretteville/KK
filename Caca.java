public class Caca {

    public static double[] crossCorrelationsimple(double[] signal1, double[] signal2) {
        int len1 = signal1.length;
        int len2 = signal2.length;
        int maxOffset = len1 + len2 - 1; // Nombre total de décalages possibles
        double[] corr = new double[maxOffset];

        // Calcul de la corrélation pour chaque décalage
        for (int offset = 0; offset < maxOffset; offset++) {
            double sum = 0.0;

            for (int i = 0; i < len1; i++) {
                int j = offset - i; // Index du second signal

                // Vérifier si l'indice j est dans les limites de signal2
                if (j >= 0 && j < len2) {
                    sum += signal1[i] * signal2[j];
                }
            }
            corr[offset] = sum; // Stocker la valeur de corrélation pour ce décalage
        }
        System.out.println("testzfo");
        return corr;
    }

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
        return corr;
    }

    public static void main(String[] args) {
        System.out.println("hello");
        String wav1 = args[0];
        String wav2 = args[1];
        double[] inputWav1 = StdAudio.read(wav1);
        double[] inputWav2 = StdAudio.read(wav2);
        System.out.println("zknzvevjne");
        double[] res1 = crossCorrelationsimple(inputWav1,inputWav2);
        //double[] res2 = crossCorrelation(inputWav1,inputWav2);

        System.out.println("test");

        System.out.println("cross corelation naive");
        for (int i = 0; i < res1.length; i++) {
            System.out.println(res1[i]);
        }
/*
        System.out.println("cross corelation optimise");
        for (int i = 0; i < res2.length; i++) {
            System.out.println(res2[i]);
        }*/
    }

}




