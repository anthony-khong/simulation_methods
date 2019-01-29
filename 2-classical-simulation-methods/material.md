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

$$ F_X(x) = \mathbb{P}(X \leq x) = \int_{z \leq x} \pi(z) dz $$

Under some regularity conditions on $F_X$, we can define its generalised inverse as:

$$ F_X^{-}(q) = \inf \{ x \in \mathbb{X} : F_X(x) \geq q \}$$

Loosely speaking, its the smallest $x$ in the state space of $X$ such that the CDF is at least $q$. Note that if $F_X$ is continuous, $F_X^- = F_X^{-1}$.

The **inversion method** states that, given a random uniform variable $U \sim \mathcal{U}(0, 1)$, the transformed variable $Z = F_X^{-}(U)$ has a CDF of $F_X$. In other words, Z has the same distribution as X or $Z \overset{d}{=} X$. This is straightforward to show:

$$
\begin{aligned}
F_Z(Z \leq x)
&= F_U(F_X^{-}(U) \leq x) &\text{(Substitute the definition of } Z \text{)}\\
&= F_U(U \leq F_X(x)) &\text{(} F_X \text{ is increasing)}\\
&= F_X(x) \Rightarrow Z \sim F_X
\end{aligned}
$$

### Example 1: Exponential Distribution

Let $X \sim \mathcal{E}(\lambda)$ where $\mathcal{E}$ denotes the exponential distribution, so that $F_X(x) = 1 - \exp(-\lambda x)$. By finding the inverse of the PDF:

$$F_X^{-}(q) = F_X^{-1}(q) = -\dfrac{\log(1 - q)}{\lambda}$$

we can conclude that if $U \sim \mathcal{U}(0, 1)$ then $Z = \dfrac{\log(1 - U)}{\lambda} \sim \mathcal{E}(\lambda)$. Moreover, since $1 - U \overset{d}{=} U$, we also have the following equality $Z' = \dfrac{\log(U)}{\lambda} \sim \mathcal(E)(\lambda)$.

### Example 2: Discrete Distribution

Let $X$ be a discrete random variable where $\mathbb{P}(X = x_k) = \pi_k$ for $k = 1, \ldots , K$. So that its CDF can be written as:

$$F_X(x) = \mathbb{P}(X \leq x) = \sum_{k: x \leq x_k} \pi_k$$

Therefore, its generalised inverse can be written as:

$$F_X^{-}(q) = \inf \{ x_k : F_X(x_k) \geq q \}$$

Given $U \sim \mathcal{U}(0, 1)$, the variable $Z = F_X^{-}(U) \overset{d}{=} X$.

### Example 3: Correlated Normals

Suppose we have

$$ 
\begin{pmatrix} X \\ Y \end{pmatrix}
~ \sim \mathcal{N}\Big(
\begin{pmatrix} \mu_X \\ \mu_Y \end{pmatrix}
,
\begin{pmatrix} \sigma_X^2 & \rho\sigma_{X}\sigma_{Y} \\ \rho\sigma_{X}\sigma_Y & \sigma_Y^2 \end{pmatrix}
\Big)
$$

Then we can rely on **sampling via composition** which states that if $X \sim F_X$ and $Y \sim F_{Y | X}$ then $(X, Y) \sim F_{X, Y}$. In this case, we can work out the marginal and conditional exactly:

$$
X \sim \mathcal{N}(\mu_X, \sigma_X^2)
\quad
Y | X \sim \mathcal{N} (\mu_{Y|X}, \sigma_{Y|X}^2 )
$$

where

$$
\mu_{Y|X} = \mu_Y + \rho\dfrac{\sigma_{Y}}{\sigma_X}(X - \mu_X)
\quad
\sigma_{Y|X}^2 = \sigma_Y^2(1 - \rho^2) 
$$

Therefore, we can sample X and Y by doing the following

$$
\begin{aligned}
X = \mu_X + \sigma_X \Phi^{-1}(U_1) && U_1 \sim \mathcal{U}(0, 1) \\
Y = \mu_{Y|X} + \sigma_{Y|X} \Phi^{-1}(U_2) && U_2 \sim \mathcal{U}(0, 1) \\
\end{aligned}
$$

where $\Phi$ is the standard normal CDF. Note that this is not the most efficient method to sample correlated normal variables. See the [Box-Muller algorithm](https://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform) and [Cholesky decomposition](https://en.wikipedia.org/wiki/Cholesky_decomposition).

## Transformation Methods

<!--TODO: add reparameterisation trick-->
<!--TODO: add link to a blog post about the Gumbel trick-->

## Rejection Sampling

# Exercises

In this set of exercises, we verify that the techniques outlined in this chapter are correct. To show that a random variable follows a certain distribution, we can draw samples and plot the histogram with the expected PDF overlaid. Alternatively, if the target distribution $\pi$ is known, we can carry out multiple Kolmogorov-Smirnov tests.

1. Verify Example 1. In particular, that $-\dfrac{\log(1 - U)}{\lambda}$ and $-\dfrac{\log(U)}{\lambda}$ both follow $\mathcal{E}(\lambda)$.
