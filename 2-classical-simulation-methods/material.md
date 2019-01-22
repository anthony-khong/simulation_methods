---
header-includes:
  - \hypersetup{colorlinks=true,
            allbordercolors={0 0 0},
            pdfborderstyle={/S/U/W 1}}
---

# 2. Classical Simulation Methods

In this chapter, we explore a few techniques to draw samples from some **target distribution** $\pi$.

## Inversion Method

Consider a random variable $X$ with a probability density function (PDF) $\pi$ and a cumulative probability distribution function (CDF) defined as follows:

$$ F_x(x) = \mathbb{P}(X \leq x) = \int_{z \leq x} \pi(z) dz $$

Under some regularity conditions on $F_x$, we can define its generalised inverse as:

$$ F_x^{-}(q) = \inf \{ x \in \mathbb{X} : F_x(x) \geq q \}$$

Loosely speaking, its the smallest $x$ in the state space of $X$ such that the CDF is at least $q$. Note that if $F_x$ is continuous, $F_x^- = F_x^{-1}$.

The **inversion method** states that, given a random uniform variable $U \sim \mathcal{U}(0, 1)$, the transformed variable $Z = F_x^{-}(U)$ has a CDF of $F_x$. In other words, Z has the same distribution as X or $Z \overset{d}{=} X$.

### Example 1: Exponential Distribution

Let $X \sim \mathcal{E}(\lambda)$, so that


<!--TODO: generalised inversion with multi-dimensional variables-->

## Transformation Methods

<!--TODO: add reparameterisation trick-->
<!--TODO: add link to a blog post about the Gumbel trick-->

## Sampling via Composition

## Rejection Sampling

# Exercises

In this set of exercises, we verify that the techniques outlined in this chapter are correct. To show that a random variable follows a certain distribution, we can draw samples and plot the histogram with the expected PDF overlaid. Alternatively, if the target distribution $\pi$ is known, we can carry out multiple Kolmogorov-Smirnov tests.

1. Verify Example 1. In particular, that $-\dfrac{\log(1 - U)}{\lambda}$ and $-\dfrac{\log(U)}{\lambda}$ both follow $\mathcal{E}(\lambda)$.
