#load "packages/FsLab/FsLab.fsx"

open MathNet.Numerics.Statistics
open MathNet.Numerics.LinearAlgebra

let xs = DenseVector.randomStandard<float> 10

let mean (xs: float seq) = Statistics.Mean(xs)

let variance (xs: float seq) = Statistics.Variance(xs)
