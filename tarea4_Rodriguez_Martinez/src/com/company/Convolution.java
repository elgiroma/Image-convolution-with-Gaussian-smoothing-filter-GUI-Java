package com.company;

//----------------------------------------------------------------+
//                  Eliud Gilberto Rodríguez Martínez             |
//                Laboratorio de visión y procesamiento           |
//                              Tarea 4                           |
//----------------------------------------------------------------+


import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.BufferedImageOp;

public class Convolution {

    // Calculates gaussian 2d
    public static double gaussian2D(double x, double y, double A, double x0, double y0, double sigma)
    {
        double v2 = 2 * sigma * sigma;   // 2 * variance
        double t1 = (x - x0) * (x - x0) / v2;
        double t2 = (y - y0) * (y - y0) / v2;

        return A * Math.exp(-(t1 + t2));
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    // Calculates gaussian filter
    public static double[][] gaussianFilter(int size, double sigma)
    {
        double[][] filter = new double[size][size];
        int offset = size / 2;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                filter[i][j] = gaussian2D(i - offset, j - offset,
                        1, 0, 0, sigma);
            }
        }

        return filter;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    // Converts double 2d array to a float one
    public static float[][] double_to_float(double[][] filter, int size){
        float[][] float_filter = new float[size][size];
        for (int w = 0; size > w; w++) {
            for (int h = 0; size > h; h++) {
                float_filter[w][h] = (float) filter[w][h];
            }
        }
        return float_filter;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    // Converts float 2d array to 1d array
    public static float[] convert(float[][] filter){
        float matrix[] = new float[filter.length * filter[0].length];
        int z=0;
        for(int row=0;row<filter.length;row++){
            for(int col=0;col<filter[row].length;col++){
                matrix[z]= filter[row][col];
                z++;
            }
        }
        return matrix;
    }

    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    public BufferedImage convolution(BufferedImage a, int size, double sigma){

        double[][] filter = gaussianFilter(size, sigma);
        double sum = Arrays.stream(filter).flatMapToDouble(Arrays::stream).sum();

        // Normalization
        for (int row = 0; row < filter.length; row++) {
            for (int col = 0; col < filter[row].length; col++) {
                filter[row][col] /= sum;
            }
        }

        sum = Arrays.stream(filter).flatMapToDouble(Arrays::stream).sum();

        // Converting double 2d array to float 1d array
        float[][] float_filter = double_to_float(filter,size);
        float[] matrix = convert(float_filter);

        // Buffered image
        BufferedImage test_image = new BufferedImage( a.getWidth(), a.getHeight(), a.getType() );

        // Image convolution
        BufferedImageOp op = new ConvolveOp(new Kernel(size,size, matrix));
        test_image = op.filter( a, test_image );

        return test_image;
    }
}
