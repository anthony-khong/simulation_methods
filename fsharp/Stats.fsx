#load "packages/FsLab/FsLab.fsx"

open MathNet.Numerics.Distributions
open MathNet.Numerics.LinearAlgebra
open MathNet.Numerics.Statistics

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

let rdist distribution nSamples = DenseVector.random<float> nSamples distribution

let rnorm mean std = rdist (Normal(mean, std))

let rexp rate = rdist (Exponential(rate))

let rgamma shape rate = rdist (Gamma(shape, rate))

let rcauchy location scale = rdist (Cauchy(location, scale))

let rstdNorm = rdist (Normal(0.0, 1.0))

open System
let xs = rnorm 1.0 10.0 10000
mean xs |> Console.WriteLine
variance xs |> Console.WriteLine
rgamma 10.0 2.0 1000 |> describe |> Console.WriteLine
rexp 10.0 1000 |> describe |> Console.WriteLine
rstdNorm 1000 |> describe |> Console.WriteLine
rcauchy 10.0 2.0 1000 |> describe |> Console.WriteLine
