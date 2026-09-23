using System;
using Neuron = System.Collections.Generic.List<System.Double>;
using Layer = System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>;
using Weights = System.Collections.Generic.List<System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>>;

using Matrix = System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>;
using Vector = System.Collections.Generic.List<System.Double>;

public class NN
{
    public int[] topology;
    public Weights weights;
    public Matrix input;
    public Matrix y;
    public Matrix output;

    public float mutationRate = 0.2f;

    public NN(Matrix x, Matrix y_)
    {
        topology = new int[] { 2, 2, 1 };
        weights = new Weights();
        input = x;
        y = y_;
        output = Util.createZeroMatrix(y.Count, y[0].Count);

        randomiseWeights();
    }

    public NN(double[][] x, double[][] y_)
    {
        topology = new int[] { 2, 2, 1 };
        weights = new Weights();
        input = x.ToExtList();
        y = y_.ToExtList();
        output = Util.createZeroMatrix(y.Count, y[0].Count);

        randomiseWeights();
    }

    public NN(int[] top)
    {
        topology = top;
        weights = new Weights();
        output = Util.createZeroMatrix(1, 4);

        randomiseWeights();
    }

    public NN clone() {
        NN clone = new NN(topology);
        clone.weights = Util.cloneWeights(weights);
        return clone;
    }

    public Layer feedForward()
    {
        var lastOut = input;

        for (int i = 0; i < topology.Length - 1; i++)
        {
            Layer val = Util.matMultiply(lastOut, weights[i]);
            lastOut = Util.sigmoid(val);
        }

        output = lastOut;

        return output;
    }

    public Layer feedForwardW(Weights weightsIn)
    {
        var lastOut = input;

        for (int i = 0; i < topology.Length - 1; i++)
        {
            lastOut = Util.sigmoid(Util.matMultiply(lastOut, weightsIn[i]));
        }

        return lastOut;
    }

    public void randomiseWeights()
    {
        weights = new Weights();

        for (int l = 0; l < topology.Length - 1; l++)
        {
            weights.Add(new Layer());
            for (int n = 0; n < topology[l]; n++)
            {
                weights[l].Add(new Neuron());
                for (int w = 0; w < topology[l + 1]; w++)
                {
                    weights[l][n].Add(Util.randomFloatRange(-1.0, 1.0));
                }
            }
        }
    }

    public Vector predict(Vector input)
    {
        Layer lastOut = new Layer() { input };

        for (int i = 0; i < topology.Length - 1; i++)
        {
            Layer val = Util.matMultiply(lastOut, weights[i]);
            lastOut = Util.sigmoid(val);
        }

        return lastOut[0];
    }

    public void mutate()
    {
        for (int l = 0; l < weights.Count; l++)
        {
            for (int i = 0; i < weights[l].Count; i++)
            {
                for (int j = 0; j < weights[l][i].Count; j++)
                {
                    if(UnityEngine.Random.Range(0f, 1f) < mutationRate) {
                        var deltaWeight = Util.gaussianNoise();
                        weights[l][i][j] += deltaWeight;
                    }
                }
            }
        }
    }

    public void ehc(int nEpochs)
    {
        Console.WriteLine("Training");

        double errorGoal = 0.0;

        var weightsChamp = Util.cloneWeights(weights);
        var outputChamp = feedForwardW(weightsChamp);
        double errorChamp = Util.meanSquaredError(y, outputChamp);

        int counter = 0;

        while ((errorGoal <= errorChamp) && (counter <= nEpochs))
        {
            var weightsMutant = Util.cloneWeights(weightsChamp);
            double stepSize = 0.01 * Util.gaussianNoise();

            for (int l = 0; l < weights.Count; l++)
            {
                for (int i = 0; i < weights[l].Count; i++)
                {
                    for (int j = 0; j < weights[l][i].Count; j++)
                    {
                        var deltaWeight = stepSize * Util.gaussianNoise();
                        weightsMutant[l][i][j] += deltaWeight;
                    }
                }
            }

            var outputMutant = feedForwardW(weightsMutant);
            var errorMutant = Util.meanSquaredError(y, outputMutant);
            if (errorMutant < errorChamp)
            {
                weightsChamp = Util.cloneWeights(weightsMutant);
                errorChamp = errorMutant;
                //Console.WriteLine("Best error: {0}", errorChamp);
            }

            counter++;
        }

        weights = Util.cloneWeights(weightsChamp);
    }
}
