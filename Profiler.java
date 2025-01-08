import java.util.function.BiFunction;
import java.util.function.Function;
import java.io.FileWriter;
import java.io.IOException;

public class Profiler {
        public static double analyse(Function<Double, Double> oneMethod, double p){
            double res = oneMethod.apply(p);
            return res;

        }
        static long globalTime = 0;
        public static double[] analyse(BiFunction<double[], double[], double[]> oneMethod, double[] x, double[] y, String method) {
            long startTime = timestamp();
            double[] result = oneMethod.apply(x, y);
            long duration = timestamp() - startTime;
            globalTime += duration;
            logResult(x.length, method, duration);
            System.out.print("Duration of " + method + " : " + duration+" ns ");
            return result;
        }

        private static void logResult(int length, String method, long duration) {
            try (FileWriter writer = new FileWriter("resultat.csv", true)) {
                writer.write(length + "," + method + "," + duration + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * Si clock0 est >0, retourne une chaîne de caractères
         * représentant la différence de temps depuis clock0.
         * @param clock0 instant initial
         * @return expression du temps écoulé depuis clock0
         */
        public static String timestamp(long clock0) {
            String result = null;

            if (clock0 > 0) {
                double elapsed = (System.nanoTime() - clock0) / 1e9;
                String unit = "s";
                if (elapsed < 1.0) {
                    elapsed *= 1000.0;
                    unit = "ms";
                }
                result = String.format("%.4g%s elapsed", elapsed, unit);
            }
            return result;
        }

        /**
         * retourne l'heure courante en ns.
         * @return
         */
        public static long timestamp() {
            return System.nanoTime();
        }
        public static long getGlobalTime(){
            return globalTime;
        }

}
