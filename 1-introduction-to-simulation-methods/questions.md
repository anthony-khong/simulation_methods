# Exercise 1: Introduction To Simulation Methods

We can loosely show that a random variable has a standard normal distribution by sampling a large number of them and showing that the quantiles match with the normal CDF. In particular, the $(0.025, 0.15, 0.5, 0.85, 0.975)$ quantiles are roughly $(-1.96, -1.04, 0, 1.04, 1.96)$.

1. Let $X \sim \mathcal{U}(0, 1)$, where $\mathcal{U}$ denotes the continuous uniform distribution. Denote the sample mean of $N$ independently drawn $X$ as $\bar{X} = \dfrac{1}{N} \sum_{i=1}^N X_i$.

    * Show that the sample mean converges to a normal distribution as $N$ becomes large.
    * What is mean and variance of $\bar{X}$ as a function of $N$?

2. Let $X \sim \mathcal{E}(1)$ and $Y \sim \mathcal{G}(3, 2)$, where $\mathcal{E}$ and $\mathcal{G}$ denote the exponential and gamma distributions respectively. Let $(\bar{X}, \hat{\sigma}^2_X)$ and $(\bar{Y}, \hat{\sigma}^2_Y)$ be the sample mean and variance of $X$ and $Y$ respectively.

    * Show that $\hat{\delta} = \bar{X} - \bar{Y}$ is normally distributed as $N_X$ and $N_Y$ become large.
    * What is the mean and variance of $\hat{\delta}$ as a function of $(N_X, N_Y)$?

3. Let $X \sim \mathcal{C}$, where $\mathcal{C}$ denotes the standard Cauchy distribution. Show that the sample mean $\bar{X} = \dfrac{1}{N} \sum_{i=1}^N X_i$ does not converge to a normal distribution as $N$ becomes large.
