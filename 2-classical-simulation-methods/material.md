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

where $\Phi$ is the standard normal CDF. Note that this is not the most efficient method to sample correlated normal variables. See the [Box-Muller algorithm](https://en.wikipedia.org/wiki/Box%E2%80%93Muller_transform) below and [Cholesky decomposition](https://en.wikipedia.org/wiki/Cholesky_decomposition).

## Transformation Methods

When we are targeting an $\mathbb{X}$-valued $X \sim F_X$, there may be instances such that we could easily sample a $\mathbb{Y}$-valued $Y \sim F_Y$ and a function $\varphi: \mathbb{Y} \rightarrow \mathbb{X}$ that satisfy $\varphi(Y) \sim F_X$. In fact, this is something that we may routinely do for simulating normal variables, as we have seen in Example 3. Concretely, if we would like to target $X \sim \mathcal{N}(\mu, \sigma^2)$, we can achieve this by sampling $Z \sim \mathcal{N}(0, 1)$ and apply the transformation $\varphi(Z) = \mu + \sigma Z$.

For continuous random variables, we can work out the transformed PDF by using the **change-of-variable** technique if we know $\varphi^{-1}$. We have the following:

$$ Y \sim F_Y \quad X = \varphi(Y)$$
$$ \Rightarrow \pi_X(\boldsymbol x) = \pi_Y(\varphi^{-1}(\boldsymbol x)) \left| \dfrac{d}{d\boldsymbol x^T}\varphi^{-1}(\boldsymbol x) \right|$$

where $(\pi_X, \pi_Y)$ denotes the PDF of $X$ and $Y$ respectively.

### Example 4: Beta Distribution

Let $X_1 \sim \mathcal{G}(\alpha, 1)$ and $X_2 \sim \mathcal{G}(\beta, 1)$ where $\mathcal{G}$ denotes the gamma distribution. It can be shown that $Y = \dfrac{X_1}{X_1 + X_2}$ will follow $\mathcal{B}(\alpha, \beta)$ where $\mathcal{B}$ denotes the beta distribution. It is straightforward to prove this using the change-of-variable technique, and it is left for the reader.

### Example 5: Box-Muller Distribution

Let $U_1 \sim \mathcal{U}(0, 1)$ and $U_2 \sim \mathcal{U}(0, 1)$, then define:

$$
R = \sqrt{-2 \log(U_1)}, \quad \Theta = 2 \pi U_2
$$

We can show that 

$$
\begin{aligned}
X_1 = R \cos\Theta \sim \mathcal{N}(0, 1) \\
X_2 = R \sin\Theta \sim \mathcal{N}(0, 1)
\end{aligned}
$$

\textbf{Proof.} Following Example 1, we have $R^2 \sim \mathcal{E}\Big(\frac{1}{2}\Big)$ and $\Theta \sim \mathcal{U}(0, 2\pi)$. We can write the joint distribution of $(R^2, \Theta)$ as the following (we drop the subscripts to avoid clutter):

$$
\begin{aligned}
\pi(r^2, \theta)
&= \pi(r^2)\pi(\theta) \\
&= \Big\{ \dfrac{1}{2} \exp\big( -r^2 / 2 \big) \Big\}\times \Big\{ \dfrac{1}{2\pi} \Big\}
\end{aligned}
$$

We can then use the change-of-variable formula with the following mapping:

$$
\varphi \begin{pmatrix} r^2 \\ \theta \end{pmatrix}
= \begin{pmatrix} r\cos\theta \\ r\sin\theta \end{pmatrix}
$$

In particular, we can have an easier time by invoking the inverse function theorem to work out the determinant of the Jacobian:

$$
\left| \dfrac{\partial \varphi^{-1}}{\partial (x_1 \, x_2)} \right|
= \left| \dfrac{\partial \varphi}{\partial (r^2 \, \theta)} \right|^{-1}
= \det
\begin{pmatrix}
\dfrac{\cos \theta}{2r} & -r \sin \theta \\
\dfrac{\sin\theta}{2r} & r \cos\theta \\
\end{pmatrix} ^{-1}
= \Big(\dfrac{1}{2}\cos^2\theta + \dfrac{1}{2}\sin^2\theta \Big)^{-1} = 2
$$

Moreover, notice that $r^2 = x_1^2 + x_2^2$, so that the joint PDF of $(x_1 \, x_2)$ is:

$$
\begin{aligned}
\pi(x_1, x_2)
&= \pi(\varphi^{-1}(x_1, x_2)) \left| \dfrac{\partial \varphi^{-1}}{\partial (x_1 \, x_2)} \right| \\
&= \dfrac{1}{2\pi} \exp\Big( -\dfrac{x_1^2 + x_2^2}{2} \Big)
\end{aligned}
$$

which is the independent standard bivariate normal PDF.

## Rejection Sampling

## Exercises

In this set of exercises, we verify that the techniques outlined in this chapter are correct. To show that a random variable follows a certain distribution, we can draw samples and plot the histogram with the expected PDF overlaid. Alternatively, if the target distribution $\pi$ is known, we can carry out multiple Kolmogorov-Smirnov tests.

1. Verify Example 1. In particular, that $-\dfrac{\log(1 - U)}{\lambda}$ and $-\dfrac{\log(U)}{\lambda}$ both follow $\mathcal{E}(\lambda)$.
2. If $X \sim F_X$, what is the distribution of $Z = F_X(X)$? Verify this with $X \sim \mathcal{N}(0, 1)$ and $Z = \Phi(X)$.

## Challenging Exercises

In this set of exercises, we examine techniques relevant to this chapter that appear in the wild (i.e. in active research). In particular, the techniques covered below caused some buzz to the machine-learning literature when they were introduced.

### The Reparameterisation Trick

### The Gumbel-Softmax Trick

### The Stick-Breaking Process

## References
