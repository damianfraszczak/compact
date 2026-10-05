package pl.edu.wat.wcy.cop.app.client.ol.curve;
// Represents interpolation.

public class Interpolation {

    public static PointForInterpolation[] Calculate(PointForInterpolation[] aPoints, int r, int n, boolean aIsIncludeOriginalPoints) {
        if (aPoints.length <= 2) {
            PointForInterpolation[] newPoints = new PointForInterpolation[3];
            newPoints[0] = aPoints[0];
            newPoints[1] = aPoints[1];
            newPoints[2] = aPoints[0];
        }
//    	if (aPoints.length <= 2) {
//            throw new IllegalArgumentException("Number of control points must be greater than 2");
//        }
//
        if (n < 1) {
            throw new IllegalArgumentException("Number knots must be greater that 1");
        }

        PointForInterpolation[] pointsForInterpolation;
        if (aIsIncludeOriginalPoints) {
            pointsForInterpolation = recalculateVectors(aPoints, r, n, aPoints.length);
        } else {
            pointsForInterpolation = new PointForInterpolation[aPoints.length];
            System.arraycopy(aPoints, 0, pointsForInterpolation, 0, aPoints.length);
        }

        double[] qSpline = calculateQSpline(n, aPoints.length);
        return calculateSSpline(pointsForInterpolation, qSpline, r, n, aPoints.length);
    }

    private static double[] calculateQSpline(int n, int m) {
        int N = n * m;
        double[] qSpline = new double[N];

        for (int j = 0; j < N; ++j) {
            if (j >= 0 && j <= n - 1) {
                qSpline[j] = (1.0 * n - j) / n;
            }
            if (j >= n && j <= N - n) {
                qSpline[j] = 0;
            }
            if (j >= N - n + 1 && j <= N - 1) {
                qSpline[j] = (1.0 * j - N + n) / n;
            }
        }

        return qSpline;
    }


    private static PointForInterpolation[] calculateSSpline(PointForInterpolation[] aVectors, double[] aQSpline, int r, int n, int m) {
        int N = n * m;
        PointForInterpolation[][] sSpline = new PointForInterpolation[r + 1][];
        for (int i = 1; i <= r; ++i) {
            sSpline[i] = new PointForInterpolation[N];
        }

        for (int j = 0; j < N; ++j) {
            sSpline[1][j] = new PointForInterpolation(0, 0);
            for (int p = 0; p < m; ++p) {
                sSpline[1][j] = PointForInterpolation.add(sSpline[1][j], PointForInterpolation.multiply(aVectors[p], aQSpline[getPositiveIndex(j - p * n, N)]));
            }
        }

        for (int v = 2; v <= r; ++v) {
            for (int j = 0; j < N; ++j) {
                sSpline[v][j] = new PointForInterpolation(0, 0);
                for (int k = 0; k < N; ++k) {
                    sSpline[v][j] = PointForInterpolation.add(sSpline[v][j], PointForInterpolation.multiply(aQSpline[k], sSpline[v - 1][getPositiveIndex(j - k, N)]));
                }
                sSpline[v][j] = PointForInterpolation.divide(sSpline[v][j], n);
            }
        }

        return sSpline[r];
    }

    private static int getPositiveIndex(int j, int N) {
        if (j >= 0) {
            return j % N;
        }

        return N - 1 + ((j + 1) % N);
    }

    private static PointForInterpolation[] recalculateVectors(PointForInterpolation[] aPoints, int r, int n, int m) {
        int N = n * m;

        double[] tr = new double[m];
        tr[0] = 1;
        for (int k = 1; k < m; ++k) {
            for (int q = 0; q < n; ++q) {
                tr[k] += Math.pow(2 * n * Math.sin((Math.PI * (q * m + k)) / N), -2 * r);
            }
            tr[k] *= Math.pow(2 * Math.sin((Math.PI * k) / m), 2 * r);
        }

        PointForInterpolation[] zre = new PointForInterpolation[m];
        PointForInterpolation[] zim = new PointForInterpolation[m];
        for (int j = 0; j < m; ++j) {
            zre[j] = new PointForInterpolation(0, 0);
            zim[j] = new PointForInterpolation(0, 0);
            for (int k = 0; k < m; ++k) {
                zre[j] = PointForInterpolation.add(zre[j], PointForInterpolation.multiply(aPoints[k], Math.cos((-2 * Math.PI * j * k) / m)));
                zim[j] = PointForInterpolation.add(zim[j], PointForInterpolation.multiply(aPoints[k], Math.sin((-2 * Math.PI * j * k) / m)));
            }
        }

        PointForInterpolation[] result = new PointForInterpolation[m];
        for (int p = 0; p < m; ++p) {
            result[p] = new PointForInterpolation(0, 0);
            for (int k = 0; k < m; ++k) {
                PointForInterpolation d = PointForInterpolation.subtract((PointForInterpolation.multiply(zre[k], Math.cos((2 * Math.PI * k * p) / m))), (PointForInterpolation.multiply(zim[k], Math.sin((2 * Math.PI * k * p) / m))));
                d = PointForInterpolation.multiply(d, 1.0 / tr[k]);
                result[p] = PointForInterpolation.add(result[p], d);
            }
            result[p] = PointForInterpolation.divide(result[p], m);
        }

        return result;
    }
}
