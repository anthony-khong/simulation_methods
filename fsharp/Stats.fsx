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

type Dist =
    | Continuous of IContinuousDistribution
    | Discrete of IDiscreteDistribution

let probability distribution =
    match distribution with
    | (Continuous d) -> Vector.map d.CumulativeDistribution
    | (Discrete d) -> Vector.map d.CumulativeDistribution

let density distribution =
    match distribution with
    | (Continuous d) -> Vector.map d.Density
    | (Discrete d) -> Vector.map (fun x -> d.Probability(int x))

let random distribution nSamples = 
    match distribution with
    | (Continuous d) -> DenseVector.random<float> nSamples d
    | (Discrete d) -> 
        d.Samples()
        |> Seq.map float
        |> Seq.take nSamples
        |> DenseVector.ofSeq

// Binomial Distribution
let binom prob nTrials = Discrete (Binomial(prob, nTrials))
let rbinom prob nTrials = binom prob nTrials |> random
let pbinom prob nTrials = binom prob nTrials |> probability
let dbinom prob nTrials = binom prob nTrials |> density
let qbinom prob nTrials =
    let quantile qtl =
        seq { 0 .. nTrials }
        |> Seq.skipWhile (fun x -> Binomial.CDF(prob, nTrials, float x) < qtl)
        |> Seq.head
        |> float
    Vector.map quantile

// Discrete Uniform
let ints low high = Discrete (DiscreteUniform(low, high))
let rints low high = ints low high |> random
let pints low high = ints low high |> probability
let dints low high = ints low high |> density
let qints low high = 
    let length = float (high - low + 1)
    let quantile qtl = qtl*length + (float low) - 1.0 |> ceil
    Vector.map quantile

// Continuous Uniform
let unif low high = Continuous (ContinuousUniform(low, high))
let runif low high = unif low high |> random
let punif low high = unif low high |> probability
let dunif low high = unif low high |> density
let qunif low high = 
    let dist = ContinuousUniform(low, high)
    Vector.map dist.InverseCumulativeDistribution

let stdUnif = unif 0.0 1.0
let rstdUnif = random stdUnif
let pstdUnif xs = probability stdUnif xs
let dstdUnif xs = density stdUnif xs
let qstdUnif xs = 
    let dist = ContinuousUniform(0.0, 1.0)
    Vector.map dist.InverseCumulativeDistribution xs

// Normal Distribution
let norm mean std = Continuous (Normal(mean, std))
let rnorm mean std = norm mean std |> random
let pnorm mean std = norm mean std |> probability
let dnorm mean std = norm mean std |> density
let qnorm mean std = 
    let dist = Normal(mean, std)
    Vector.map dist.InverseCumulativeDistribution

let stdNorm = norm 0.0 1.0
let rstdNorm = random stdNorm
let pstdNorm xs = probability stdNorm xs
let dstdNorm xs = density stdNorm xs
let qstdNorm xs = 
    let dist = Normal(0.0, 1.0)
    Vector.map dist.InverseCumulativeDistribution xs

// Exponential Distribution
let exponential rate = Continuous (Exponential(rate))
let rexp rate = exponential rate |> random
let pexp rate = exponential rate |> probability
let dexp rate = exponential rate |> density
let qexp rate =
    let dist = Exponential(rate)
    Vector.map dist.InverseCumulativeDistribution

// Gamma Distribution
let gamma shape rate = Continuous (Gamma(shape, rate))
let rgamma shape rate = gamma shape rate |> random
let pgamma shape rate = gamma shape rate |> probability
let dgamma shape rate = gamma shape rate |> density
let qgamma shape rate = 
    let dist = Gamma(shape, rate)
    Vector.map dist.InverseCumulativeDistribution

// Cauchy Distribution
let cauchy location scale = Continuous (Cauchy (location, scale))
let rcauchy location scale = cauchy location scale |> random
let pcauchy location scale = cauchy location scale |> probability
let dcauchy location scale = cauchy location scale |> density
let qcauchy location scale =
    let dist = Cauchy(location, scale)
    Vector.map dist.InverseCumulativeDistribution

// Sample Operations
// TODO: standardise

