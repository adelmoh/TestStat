Statistics exercise
===================

In this folder you find about 20 Java classes which have been extracted from
MDCPartners’ business intelligence packages. Most have been simplified for this
exercise, and occasionally implementations are missing.

The context
-----------

The context of this exercise are descriptive statistics about clinical trials. A
clinical trial is represented by the `Trial` class. A trial takes place in a
number of sites (typically between 1 and 200) which are spread over the world.
You’ll see that the `Site` class has a reference to `Location` which itself has
references to (directly or indirectly) to `Province` and `Country`. A trial
enrolls patients at its sites. It is generally not known publicly how many
patients are actually enrolled by each individual site, so for our purposes
patients are distributed homogeneously over the sites.

There are 4 main clinical trial phases. Generally, later phase trials have
higher enrollment and require more sites in more countries.

Given that each week about 200 new clinical trials are reported across all
disease areas and phases, it is important to provide descriptive statistics
about trials of interest. This exercise contains the basic version of 2 of those
(in ta-Scan, we have over 100 classes implementing the `Statistics` interface).

The input of a statistic is an object implementing the `ResultContainer`
interface. For this context, it suffices to know that it contains a given set of
trials (for example, all trials of a certain disease area) and their associated
sites.

The output of a statistic can essentially be anything, wrapped in a
`StatisticsResult`. Generally, it is multidimensional data, and this is
reinformed by the presence of a `defaultSorter` method in the interface with a
dimension parameter.

The goal of the exercise 
-------------------------

There are two basic targets to reach. The first one is to implement a generic
system for an operation external to the statistics that is called `GROUP BY` in
SQL-speak, and apply it to the two statistics included. The second is to provide
a version of the enrollment statistic where the computation is done in multiple
threads. The Java JDK target is 1.8, and unit tests should be used to test all
relevant bits of code.

Write code that makes use of the most modern core Java constructs and libraries
to achieve targets one and two. Feel free to update and improve the provided
code as well. I’m going to be much more impressed by good code than by
extensively written documentation and comments.

### 1 The group-by operation

The `EnrollmentStatistics` class produces two values (`trialsStarted` and
`enrollment`) across the time dimension. Similarly, the `PhaseStatistics` class
produces three values across the `Phase` dimension. The target is to add a
second principal dimension based on properties of the trial, and, for this
exercise in particular, geographical properties of the sites of the trial. In
other words, we’d like to see the enrollment statistic extended to provide
enrollment over time per country, for each of the countries that the trials have
sites in. A “summary” row can then still show the worldwide values. We envision
a generic mechanism that can do this computation regardless of whether the
grouping is done per country, location, province, or any other aspect we’d like
to group by.

### 2 Multiple threads

This needs little explanation, except maybe that it would be good to generate a
sufficiently large input set to test the statistics computation to show that the
threading works. Correctness can be judged by comparing the outcome to that of
the single threaded version.

### 3 Code design

The code you’re writing is meant to be used by others, and will be in production
use for many years. So while extensibility and readability is important,
efficiency is too. Try to stick to JDK 1.8 classes, only use foreign libraries
when really necessary (`org.junit` comes to mind).
