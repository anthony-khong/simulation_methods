---
header-includes:
  - \hypersetup{colorlinks=true,
            allbordercolors={0 0 0},
            pdfborderstyle={/S/U/W 1}}
---

# 1. Introduction To Simulation Methods

## Why Do We Need Simulation Methods?

We are often required to solve integrals of the form:

$$ I = \int_{x \in \mathbb{X}} f(x) dx $$

where $f: \mathbb{X} \rightarrow \mathbb{R}$. If the codomain of $f$ is $\mathbb{R}^{+}$, this becomes the familiar problem of finding the area under the curve.

If the domain $\mathbb{X}$ is one-dimensional, it may be easy to find a good approximation by doing **numerical integration**. Suppose the domain is the unit interval $[0, 1]$. We can divide the space into $n$ uniformly-spaced points and finally summing the function values at those points multiplied by the length between the points:

$$
\hat{I} = \delta \sum_{i=1}^m f\Big((i + 1/2) \delta \Big)
\quad \text{where } \delta=\dfrac{1}{m}
$$

Graphically, this procedure approximates the area of under the curve with the sum of the areas of the equal-width rectangles, as in Figure 1:

![Numerical Integration Illustration](figures/numerical_integration.png)

Under some regularity conditions, the approximation error is $O(m^{-1})$. However, what if the domain $\mathbb{X}$ is two-dimensional instead? Now we will need two summations - one for each dimension:

$$
\hat{I} = \delta \sum_{i=1}^m \sum_{j=1}^m f\Big((i + 1/2) \delta, (j + 1/2) \delta \Big)
\quad \text{where } \delta=\dfrac{1}{m^2}
$$

Now the approximation error is $O(m^{-\frac{1}{2}})$. And in general, we have an approximation error of $O(m^{-\frac{1}{d}})$ in $d$ dimensions. Therefore, in high-dimensional settings, this technique becomes increasingly difficult. **Simulation methods address this problem using Monte Carlo integration**.

## Example: Bayesian Statistics

In Bayesian statistics, the parameters we use to model a system are treated as random variables $\boldsymbol \theta$, where the set of all the possible values that the parameter can take is denoted by $\Theta$. We have some belief about the parameters prior to observing any data summarised in a probability distribution - we call this the **prior distribution** $\pi(\boldsymbol \theta)$. Given some parameter values $\boldsymbol\theta$, we observe some data $\mathcal{D}$ with probability $p(\mathcal{D} | \boldsymbol \theta)$. We use our observations $\mathcal{D}$ to refine our beliefs about the parameters using the Bayes rule:

$$ \pi(\boldsymbol \theta | \mathcal{D}) \propto p(\mathcal{D} | \boldsymbol\theta)\pi(\boldsymbol\theta) $$

The vast majority of Bayesian computations are devoted to approximating the expectation of some function $g$ over the posterior distribution $\pi(\boldsymbol \theta | \mathcal{D})$:

$$ I = \int_{\boldsymbol\theta \in \Theta} g(\boldsymbol \theta) \pi(\boldsymbol \theta | \mathcal{D}) d\boldsymbol\theta$$

It is extremely rare to have analytical solutions of such integrals except for contrived examples. Typically the parameter $\Theta$ is a high-dimensional space, so that deterministic numerical integration suffers from **the curse of dimensionality**. A common method to address this problem is to draw approximate samples from the posterior distribution using **MCMC** and then performing Monte Carlo integration. We will cover these cases in more detail later in the course.

# Exercises

In this set of exercise questions, we make sure that you have access to a good simulation library. We look into foundational statistics by exploring the central limit theorem and heavy-tailed distributions. Note that to show convergence to normality, it's enough to plot the histogram and superimposing the normal PDF.

1. Let $X \sim \mathcal{U}(0, 1)$, where $\mathcal{U}$ denotes the continuous uniform distribution. Denote the sample mean of $N$ independently drawn $X$ as $\bar{X} = \dfrac{1}{N} \sum_{i=1}^N X_i$.

    * Show that the sample mean converges to a normal distribution as $N$ becomes large.
    * What is mean and variance of $\bar{X}$ as a function of $N$?

2. Let $X \sim \mathcal{E}(1)$ and $Y \sim \mathcal{G}(3, 2)$, where $\mathcal{E}$ and $\mathcal{G}$ denote the exponential and gamma distributions respectively. Let $(\bar{X}, \hat{\sigma}^2_X)$ and $(\bar{Y}, \hat{\sigma}^2_Y)$ be the sample mean and variance of $X$ and $Y$ respectively.

    * Show that $\hat{\delta} = \bar{X} - \bar{Y}$ is normally distributed as $N_X$ and $N_Y$ become large.
    * Does $\hat{\delta}$ still converge to normality if $N_X = 2 and $N_Y$ becomes large?
    * What is the mean and variance of $\hat{\delta}$ as a function of $(N_X, N_Y)$?

3. Let $X \sim \mathcal{C}$, where $\mathcal{C}$ denotes the standard Cauchy distribution.

    * Show that the sample mean $\bar{X} = \dfrac{1}{N} \sum_{i=1}^N X_i$ does not converge to a normal distribution as $N$ becomes large.
    * Does the median converge to a normal distribution?
