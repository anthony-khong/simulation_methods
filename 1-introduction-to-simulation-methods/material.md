# 1. Introduction To Simulation Methods

We are often required to solve integrals of the form:

$$ I = \int_{x \in \mathbb{X}} f(x) dx $$

where $f: \mathbb{X} \rightarrow \mathbb{R}$. If the codomain of $f$ is $\mathbb{R}^{+}$, this becomes the familiar problem of finding the area under the curve.

If the domain $\mathbb{X}$ is one-dimensional, in most cases, it is straightforward to find a good approximation by doing **numerical integration**. The simplest example of this would be to find a bounded interval $[a, b]$ where $a < b$, divide it into $m$ uniformly-spaced points and finally summing the function values at those points multiplied by the length between the points:

$$
\hat{I} = \delta \sum_{i=1}^m f\Big(i\dfrac{\delta}{2}\Big)
\quad \text{where } \delta=\dfrac{b -a}{m}
$$

Graphically, we are approximating the area of under the curve with the sum of the areas of the rectangles, where the width is given by $\dfrac{b-a}{m}$ (see Figure 1).

![Numerical Integration Illustration](figures/numerical_integration.png)

## Example I: Bayesian Statistics

## Example II: Statistical Mechanics

## Example III: Financial Mathematics
