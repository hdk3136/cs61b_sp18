/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static int flag;
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString[] stringArr = new synthesizer.GuitarString[37];
        for (int i = 0; i < 37; i += 1) {
            double frequency = 440.0 * Math.pow(2, (i - 24.0) / 12.0);
            stringArr[i] = new synthesizer.GuitarString(frequency);
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < 37; i += 1) {
                    if (key == keyboard.charAt(i)) {
                        stringArr[i].pluck();
                        flag = i;
                        break;
                    }
                }
            }
            /* compute the superposition of samples */
            double sample = stringArr[flag].sample();
            /* play the sample on standard audio */
            StdAudio.play(sample);
            /* advance the simulation of each guitar string by one step */
            stringArr[flag].tic();
        }
    }
}

