import matplotlib.pyplot as plt
import numpy as np
from scipy.stats import beta

if __name__ == '__main__':
    fn = lambda xs: beta.pdf(xs, a=3.0, b=2.0)
    smooth_grid = np.linspace(0.01, 0.99, 1000)
    fig, axis = plt.subplots(figsize=(12, 5))
    axis.plot(smooth_grid, fn(smooth_grid), color='black')

    rough_grid = np.linspace(0.01, 0.99, 25)
    delta = np.diff(rough_grid)[0]
    axis.bar(rough_grid + delta/2, fn(rough_grid + delta/2),
             width=delta,
             color='grey',
             edgecolor='black',
             alpha=0.2, zorder=1)
    axis.set_xlim(0, 1)
    axis.set_ylim(0, 1.95)
    axis.set_ylabel(r'PDF of $\mathcal{B}(3, 2)$')
    fig.savefig('figures/numerical_integration.png')
