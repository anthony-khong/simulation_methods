# Simulation Methods

This is a series of blog posts on simulation methods based on a statistics course called Advanced Simulation Methods (ASM) that I took in the University of Oxford between January and March 2014. The course was taught by [Rémi Bardenet](http://rbardenet.github.io/) and [Pierre Jacob](https://sites.google.com/site/pierrejacob/). My understanding and appreciation of much of the content of the course only materialised when I wrote my dissertation supervised on Bayesian neural networks by [Yee Whye Teh](https://www.stats.ox.ac.uk/~teh/) and my research project on exact Hamiltonian Monte Carlo with [Louis Aslett](http://www.louisaslett.com/).

## Plan of Attack

1. Introduction to Simulation Methods
2. Classical Simulation Methods
3. Importance Sampling
4. Markov Chains
5. Gibbs Sampler
6. Metropolis Hastings
7. Reversible Jump MCMC
8. Sequential Importance Sampling
9. Hidden Markov Models
10. Stochastic Gradient MCMC
11. Hamiltonian Monte Carlo
12. Exact HMC

Each chapter comes with its own exercises and answers. I will be focusing more on computing and implementing the samplers rather than the mathematics. I will be using [Clojure](https://clojure.org/) in the answers. Contributions for other languages are welcomed!

## Who Is This For?

Simulation methods is an indispensable technique for approximating intractable high-dimensional integrals. It is particularly useful for **Bayesian computations**, and as such, many of the examples are drawn from the Bayesian literature. The course is meant to be doable but challenging for people with a basic knowledge of statistics, namely random variables, expectations, central-limit theorem and the law of large numbers. The course is for practitioners, so that the questions at the end of each lecture are effectively programming exercises that consolidate the material discussed in the lecture notes.

## Creating the PDFs

The lecture notes are written in Markdown format. It is intended to be compiled to PDF using Pandoc. If you already have Pandoc, you can simply compile the Markdown files in their own directory.

[Install Docker](https://docs.docker.com/install/), and simply run the command `./run` in the root directory of the repository. **WARNING:** this can take a while, download a lot of data and take up almost 1GB of container size!

## Why Did I Decide To Write This?

Some courses are life-changing. This happened to be one of such courses for me. ASM made me work intensively on computational statistics, and looking back, it undoubtedly set the trajectory of my career and brought me to where I am today. Aside from emotional attachments, the course was good fun and extremely enriching. Therefore, I am making it a mission to share it with everyone!
