#load "packages/FsLab/FsLab.fsx"

open MathNet.Numerics.LinearAlgebra

open MathNet.Numerics.Statistics

Statistics.Mean(xs)

Statistics.Variance(xs)

Statistics.percentileFunc xs 99

#load "Stats.fsx"

let mean (xs: float seq) = Statistics.Mean(xs)
let variance (xs: float seq) = Statistics.Variance(xs)

let xs = DenseVector.randomStandard<float> 10


type Shape = int | int * int

let rnorm shape = 
    match shape with
    | (n1, n2) -> DenseMatrix.randomStandard<float> n1 n2
    | n -> DenseMatrix.randomStandard<float> n 1
    | _ -> None
