package pl.edu.wat.wcy.cop.app.client.ol.curve;
// Represents b spline from bezier.

public class BSplineFromBezier {
    private int m;
    private double[] u;
    private double[] h;

    private double[] fX;
    private double[] gX;
    private double[] eX;
    private double[] pX;

    private double[] fY;
    private double[] gY;
    private double[] eY;
    private double[] pY;

    private double[] myX;
    private double[] myY;

    public BSplineFromBezier(double[] x, double[] y) {

        int countOfNodes = x.length;
        m = countOfNodes - 2;
        createU();
        createH();
        pX = x;
        pY = y;
        fX = createF(fX, pX);
        fY = createF(fY, pY);
        gX = createG(gX, pX);
        gY = createG(gY, pY);
        eX = createE(eX, pX, gX, fX);
        eY = createE(eY, pY, gY, fY);
        setArrays();
    }

    private void createU() {
        u = new double[m];
        for (int i = 0; i < m; i++) {
            u[i] = (double) i / m;
        }
    }

    private void createH() {
        h = new double[m + 1];
        for (int i = 0; i < m + 1; i++) {
            h[i] = u[i + 1] - u[i];
        }
    }

    private double[] createF(double[] f, double[] p) {
        f = new double[m - 1];
        f[0] = p[1];
        for (int i = 1; i < m - 2; i++) {
            f[i] = h[i] + h[i + 1];
            f[i] = f[i] / (h[i - 1] + h[i] + h[i + 1]);
            f[i] = f[i] * p[i + 1];

            f[i] += (h[i - 1] / (h[i - 1] + h[i] + h[i + 1])) * p[i + 2];
        }

        f[m - 2] = h[m - 2] / (h[m - 2] + h[m - 3]);
        f[m - 2] = f[m - 2] * p[m - 1];
        f[m - 2] += (h[m - 3] / (h[m - 2] + h[m - 3])) * p[m];
        return f;
    }

    private double[] createG(double[] g, double[] p) {
        g = new double[m - 1];
        g[0] = (h[1] / (h[0] + h[1])) * p[1];
        g[0] += (h[0] / (h[0] + h[1])) * p[2];
        for (int i = 1; i < m - 2; i++) {
            g[i] = h[i + 1];
            g[i] = g[i] / (h[i - 1] + h[i] + h[i + 1]);
            g[i] = g[i] * p[i + 1];

            g[i] += ((h[i - 1] + h[i]) / (h[i - 1] + h[i] + h[i + 1])) * p[i + 2];
        }
        g[m - 2] = p[m];
        return g;
    }

    private double[] createE(double[] e, double[] p,
                             double[] g, double[] f) {
        e = new double[m];
        e[0] = p[0];
        for (int i = 0; i < m - 2; i++) {
            e[i + 1] = (h[i + 1] / (h[i] + h[i + 1])) * g[i];
            e[i + 1] += (h[i] / (h[i] + h[i + 1])) * f[i + 1];
        }
        e[m - 1] = p[m + 1];
        return e;
    }

    private void setArrays() {
        int newSize = fX.length * 3 + 1;
        myX = new double[newSize];
        myY = new double[newSize];
        int forE = 0;
        int forF = 0;
        int forG = 0;
        for (int i = 0; i < newSize - 1; i++) {
            if (i % 3 == 0) {
                myX[i] = eX[forE];
                myY[i] = eY[forE];
                forE++;
            } else if (i % 3 == 1) {
                myX[i] = fX[forF];
                myY[i] = fY[forF];
                forF++;
            } else if (i % 3 == 2) {
                myX[i] = gX[forG];
                myY[i] = gY[forG];
                forG++;
            }
        }
        myX[myX.length - 1] = eX[eX.length - 1];
        myY[myY.length - 1] = eY[eY.length - 1];
    }

    public double[] getMyX() {
        return myX;
    }

    public double[] getMyY() {
        return myY;
    }
}
