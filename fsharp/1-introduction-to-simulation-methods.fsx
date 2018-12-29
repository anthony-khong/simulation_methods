#load "packages/FsLab/FsLab.fsx"

open MathNet.Numerics.LinearAlgebra

open MathNet.Numerics.Statistics

let xs = DenseVector.randomStandard<float> 10

Statistics.Mean(xs)

Statistics.Variance(xs)

Statistics.percentileFunc xs 99


