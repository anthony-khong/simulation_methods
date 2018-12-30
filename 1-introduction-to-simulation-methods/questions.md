---
header-includes:
  - \hypersetup{colorlinks=true,
            allbordercolors={0 0 0},
            pdfborderstyle={/S/U/W 1}}
---

# Exercise 1: Introduction To Simulation Methods

We can loosely show that a random variable has a standard normal distribution by sampling a large number of them and showing that the quantiles match with the normal CDF. In particular, the $(0.025, 0.15, 0.5, 0.85, 0.975)$ quantiles are roughly $(-1.96, -1.04, 0, 1.04, 1.96)$. Use your answer in question 1 to show that the quantiles get tighter and tighter as the sample size increases.

1. Implement whuber's answer on Hahn and Meeker's (1991) solution of calculating confidence intervals on quantiles on this [stats.stackexchange thread](https://stats.stackexchange.com/questions/99829/how-to-obtain-a-confidence-interval-for-a-percentile).
 
2. Let $X \sim \mathcal{U}(0, 1)$, where $\mathcal{U}$ denotes the continuous uniform distribution. Denote the sample mean of $N$ independently drawn $X$ as $\bar{X} = \dfrac{1}{N} \sum_{i=1}^N X_i$.

    * Show that the sample mean converges to a normal distribution as $N$ becomes large.
    * What is mean and variance of $\bar{X}$ as a function of $N$?

3. Let $X \sim \mathcal{E}(1)$ and $Y \sim \mathcal{G}(3, 2)$, where $\mathcal{E}$ and $\mathcal{G}$ denote the exponential and gamma distributions respectively. Let $(\bar{X}, \hat{\sigma}^2_X)$ and $(\bar{Y}, \hat{\sigma}^2_Y)$ be the sample mean and variance of $X$ and $Y$ respectively.

    * Show that $\hat{\delta} = \bar{X} - \bar{Y}$ is normally distributed as $N_X$ and $N_Y$ become large.
    * What is the mean and variance of $\hat{\delta}$ as a function of $(N_X, N_Y)$?

4. Let $X \sim \mathcal{C}$, where $\mathcal{C}$ denotes the standard Cauchy distribution. Show that the sample mean $\bar{X} = \dfrac{1}{N} \sum_{i=1}^N X_i$ does not converge to a normal distribution as $N$ becomes large.
