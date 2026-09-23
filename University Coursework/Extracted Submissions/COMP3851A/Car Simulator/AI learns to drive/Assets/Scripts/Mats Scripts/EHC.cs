using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using Weight = System.Double;
using Neuron = System.Collections.Generic.List<System.Double>;
using Layer = System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>;
using Weights = System.Collections.Generic.List<System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>>;

using Matrix = System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>;
using Vector = System.Collections.Generic.List<System.Double>;

public static class Extensions
{
    /*public static double[][] ToExtArray(this List<List<Double>> list)
    {
        return list.Select(a => a.ToArray()).ToArray();
    }*/

    public static List<Double> ToList(this double[] array)
    {
        List<Double> ret = new List<Double>();

        for(int i = 0; i < array.Length; i++)
        {
            ret.Add(array[i]);
        }

        return ret;
    }

    public static Vector getColumn(this Layer m, int col)
    {
        Vector ret = new Vector();

        for (int row = 0; row < m.Count; row++)
        {
            ret.Add(m[row][col]);
        }

        return ret;
    }

    public static Vector getRow(this Layer m, int row)
    {
        return m[row];
    }

    public static void print(this Layer m)
    {
        for (int row = 0; row < m.Count; row++)
        {
            Console.Write("|");
            for (int col = 0; col < m[0].Count; col++)
            {
                Console.Write(" {0}", m[row][col]);
            }
            Console.WriteLine(" |");
        }
        Console.WriteLine("");
    }

    public static void print(this Weights w)
    {
        foreach (var val in w) val.print();
    }

    public static List<List<Double>> ToExtList(this double[][] array)
    {
        List<List<Double>> list = new List<List<Double>>();
        for (int i = 0; i < array.Length; i++)
        {
            List<Double> l = array[i].ToList();
            list.Add(l);
        }

        return list;
    }
}

public class Util
{
    static System.Random random = new System.Random();

    public static Layer createZeroMatrix(int nRows, int nCols)
    {
        Layer ret = new Layer();

        for (int row = 0; row < nRows; row++)
        {
            ret.Add(new Vector());
            for (int col = 0; col < nCols; col++)
            {
                ret[row].Add(0.0);
            }
        }

        return ret;
    }

    public static double meanSquaredError(double[][] target, double[][] y)
    {
        int n = target.Length;

        double sum = 0.0;
        for (int i = 0; i < n; i++)
        {
            sum += Math.Pow((target[i][0] - y[i][0]), 2);
        }

        return 1.0 / n * sum;
    }

    public static double meanSquaredError(Matrix target, Matrix y)
    {
        int n = target.Count;

        double sum = 0.0;
        for (int i = 0; i < n; i++)
        {
            sum += Math.Pow((target[i][0] - y[i][0]), 2);
        }

        return 1.0 / n * sum;
    }

    public static double dot(Vector a, Vector b)
    {
        double total = 0.0;
        for (int i = 0; i < a.Count; i++)
        {
            total += a[i] * b[i];
        }

        return total;
    }

    public static Layer matMultiply(Layer m1, Layer m2)
    {
        int nRows = m1.Count;
        int nCols = m2[0].Count;

        Layer ret = new Layer();

        for (int row = 0; row < nRows; row++)
        {
            ret.Add(new Vector());
            for (int col = 0; col < nCols; col++)
            {
                ret[row].Add(dot(m1.getRow(row), m2.getColumn(col)));
            }
        }

        return ret;
    }

    public static double sigmoid(double x)
    {
        return 1.0 / (1.0 + Math.Exp(-x));
    }

    public static Layer sigmoid(Layer m)
    {
        Layer newM = new Layer();

        for (int row = 0; row < m.Count; row++)
        {
            newM.Add(new Vector());
            for (int col = 0; col < m[0].Count; col++)
            {
                newM[row].Add(sigmoid(m[row][col]));
            }
        }

        return newM;
    }

    public static double tanh(double x)
    {
        var e2x = Math.Exp(2*x);
        return (e2x-1)/(e2x+1);
    }

    public static Layer tanh(Layer m)
    {
        Layer newM = new Layer();

        for (int row = 0; row < m.Count; row++)
        {
            newM.Add(new Vector());
            for (int col = 0; col < m[0].Count; col++)
            {
                newM[row].Add(tanh(m[row][col]));
            }
        }

        return newM;
    }

    public static List<double> getRandomDouble(int num, double min = -1.0, double max = 1.0)
    {
        List<double> values = new List<double>();
        for (int i = 0; i < num; i++)
        {
            values.Add(randomFloatRange(min, max));
        }

        return values;
    }

    public static double randomFloatRange(double min, double max)
    {
        return random.NextDouble() * (max - min) + min;
    }

    public static Weights cloneWeights(Weights weights)
    {
        Weights newWeights = new Weights();
        for (int l = 0; l < weights.Count; l++)
        {
            newWeights.Add(new Layer());
            for (int n = 0; n < weights[l].Count; n++)
            {
                newWeights[l].Add(new Neuron());
                for (int w = 0; w < weights[l][n].Count; w++)
                {
                    newWeights[l][n].Add(weights[l][n][w]);
                }
            }
        }

        return newWeights;
    }

    public static double gaussianNoise(double mu = 0, double sigma = 1)
    {
        double u1 = 1.0 - random.NextDouble();
        double u2 = 1.0 - random.NextDouble();
        double randStdNormal = Math.Sqrt(-2.0 * Math.Log(u1)) * Math.Sin(2.0 * Math.PI * u2);
        double randNormal = mu + sigma * randStdNormal;

        return randNormal;
    }
}

public class EHC : MonoBehaviour
{
    NN nn;
    public GameObject car;
    
    private SensorController carSensorController;
    private MyCarController carController;

    void Start()
    {
        carSensorController = car.GetComponent<SensorController>();
        carController = car.GetComponent<MyCarController>();
        carController.controlledByPlayer = false;

        var topology = new int[] {2, 5, 1};
        nn = new NN(topology);
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        float forward = carSensorController.getDistanceFromSensorByName("FORWARD");
        float backward = carSensorController.getDistanceFromSensorByName("BACKWARD");
        float left = carSensorController.getDistanceFromSensorByName("LEFT");
        float right = carSensorController.getDistanceFromSensorByName("RIGHT");

        //Debug.Log("right: " + right);

        Vector inputs = new Vector {forward, backward, left, right};
        Debug.Log("Inputs: " + inputs[0]);

        var outputs = nn.predict(inputs);

        Debug.Log("outputs: " + outputs);
    }
}
