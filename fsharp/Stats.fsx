#load "packages/FsLab/FsLab.fsx"

open MathNet.Numerics.Distributions
open MathNet.Numerics.LinearAlgebra
open MathNet.Numerics.Statistics

// Descriptive Statistics
type Quantiles = 
    {Min:float; Q1:float; Median:float; Q3:float; Max:float}

type Description = {Mean:float; Std:float; Qs:Quantiles}

let mean (xs: float seq) = Statistics.Mean(xs)
let std (xs: float seq) = Statistics.StandardDeviation(xs)
let variance (xs: float seq) = Statistics.Variance(xs)
let min = Seq.min
let max = Seq.max
let quantiles (xs: float seq) =
    let quantile = fun (quantile) -> Statistics.Percentile(xs, quantile)
    {Min=min xs; Q1=quantile 25; Median=quantile 50; Q3=quantile 75; Max=max xs}
let describe (xs: float seq) = {Mean=mean xs; Std=std xs; Qs=quantiles xs}

// Common Distribution Functions
type Dist =
    | Continuous of IContinuousDistribution
    | Discrete of IDiscreteDistribution

let rdist distribution nSamples = 
    match distribution with
    | (Continuous dist) -> DenseVector.random<float> nSamples dist
    | (Discrete dist) -> 
        dist.Samples()
        |> Seq.map float
        |> Seq.take nSamples
        |> DenseVector.ofSeq

let qdist distribution = 
    match distribution with
    | (Continuous dist) -> Vector.map dist.CumulativeDistribution
    | (Discrete dist) -> Vector.map dist.CumulativeDistribution

// Binomial Distribution
let binom prob nTrials = Discrete (Binomial(prob, nTrials))
let rbinom prob nTrials = binom prob nTrials |> rdist
let qbinom prob nTrials = binom prob nTrials |> qdist

// Normal Distribution
let norm mean std = Continuous (Normal(mean, std))
let rnorm mean std = norm mean std |> rdist
let qnorm mean std = norm mean std |> qdist

let stdNorm = norm 0.0 1.0
let rstdNorm = rdist stdNorm
let qstdNorm xs = qdist stdNorm xs

// Exponential Distribution
let exponential rate = Continuous (Exponential(rate))
let rexp rate = exponential rate |> rdist
let qexp rate = exponential rate |> qdist

// Gamma Distribution
let gamma shape rate = Continuous (Gamma(shape, rate))
let rgamma shape rate = gamma shape rate |> rdist
let qgamma shape rate = gamma shape rate |> qdist

// Cauchy Distribution
let cauchy location scale= Continuous (Cauchy (location, scale))
let rcauchy location scale = cauchy location scale |> rdist
let qcauchy location scale = cauchy location scale |> qdist

open System
let xs = rnorm 1.0 10.0 10000
mean xs |> Console.WriteLine
variance xs |> Console.WriteLine
rgamma 10.0 2.0 1000 |> describe |> Console.WriteLine
rexp 10.0 1000 |> describe |> Console.WriteLine
rstdNorm 1000 |> describe |> Console.WriteLine
rcauchy 10.0 2.0 1000 |> describe |> Console.WriteLine
