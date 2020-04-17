package mathcomp.oletsky.randomchooser;

import mathcomp.oletsky.mathhelper.VectMatr;

import java.util.Random;

/**
 * @author Oleksiy Oletsky
 *
 * A class for choosing random actions
 */

public class RandomChooser {

    /**
     *
     * @param d - probabilities of actions
     * @return chosen action
     */
    public static int chooseByProps(double[] d) {
        final double EPS=1.E-3;
        int n=d.length;
        double s=0.;
        for (int i=0; i<n; i++) {
            s+=d[i];
        }
        if ((s>1.+EPS)||(s<1.-EPS)) throw new RuntimeException("Not a probability distribution");
        int choice=0;
        double q=0;
        Random rnd = new Random();
        double r=rnd.nextDouble();
        for (int i=0; i<n; i++) {
            if (d[i]==0)  {
                choice++;
                continue;
            }
            q+=d[i];
            if (q>r) break;
            choice++;
        }
        return choice;
    }

    public static int bernoulliProbe(double p) {
        double r=Math.random();
        return (r<=p)?1:0;
    }

    public static double[] getProbsByValues(double[] vals) {
        int n=vals.length;
        double[] prs=new double[n];
        double sum = VectMatr.calculateSumOfComponents(vals);
        for (int i=0; i<n; i++) {
            prs[i]=vals[i]/sum;
        }
        return prs;

    }

    public static double[] calculateExpDistrib(double tau, double[] vect) {
        int n = vect.length;
        double[] res=new double[n];
        double sumFunc=0;
        for (int i=0; i<n; i++) {
            sumFunc+=Math.exp(-tau*vect[i]);
        }
        for (int i=0; i<n; i++) {
            res[i]=Math.exp(-tau*vect[i])/sumFunc;
        }
        return res;
    }

}
