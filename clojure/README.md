# Getting Started

The easiest way to run the code is to [Install Docker](https://docs.docker.com/install/) and simply:

1. Build the container by running `$ ./run build-docker`. This can take several minutes.
2. Invoke the REPL by running `$ ./run dock repl`.
3. Send codes to the REPL by copy-pasting or other methods, such as [vim-slime](https://github.com/jpalardy/vim-slime).

If you are familiar with Clojure, you can install [lein](https://leiningen.org/) and run `$ lein repl` straightaway.

## Introduction to Clojure

The Clojure [website](https://clojure.org/) lists many [resources for learning Clojure](https://clojure.org/guides/getting_started). The highly-rate [Clojure For The Brave And True](https://www.braveclojure.com/clojure-for-the-brave-and-true/) can be read online for free.

If you come from an OO background, I recommend watching Rich Hickey's [lecture "Clojure for Java Programmers"](https://www.youtube.com/watch?v=P76Vbsk_3J0). Conversely, if you're coming from Lisp, he also gave the [lecture "Clojure for Lisp Programmers"](https://www.youtube.com/watch?v=cPNkH-7PRTk).

## Introduction to Incanter


## Why Clojure?

Although the linguae francae of statistics and machine learning are arguably R and Python, there are a few advantages to Clojure:

1. Clojure is a **dynamically typed** language, which allows for fast and flexible prototyping of samplers.
2. It is also a **functional-first** language, so that **immutability** is the default. Using exclusively pure functions makes sense for this project, because we are dealing solely with mathematical operations.
3. Finally, it is a **Lisp**. Many would appeal to its homoiconic property and thus its metaprogramming prowess. However, I just think that Lisp is **beautiful**.

I, therefore, concluded: why not Clojure?

![](https://vignette.wikia.nocookie.net/deathbattlefanon/images/1/17/Zoidberg.png/revision/latest?cb=20161220042212 "Why Not Clojure?"){:height="50%" width="50%"}
