I was wondering about Perl's performance against something running on a JVM. But something similarly expressive, not Java. Enter Clojure. The example programs have surprisingly similar wordcounts. Each contains a `huzza` function, interpolating the input string into a sentence. The `huzzatok` function returns a list of interpolated sentences for a list of strings. I measure the time of 4 things:

* Read the files under `/usr/bin` into a list
* Sort list of files, apply `huzzatok` to list
* Print each element of interpolated sorted list
* Overall runtime of program

The Clojure program
===================

    (use 'clojure.java.io)
    (defn huzza [x] (str "Tiplizz inet " x "!"))
    (defn huzzatok
      ([] nil)
      ([x] (if (coll? x) (map huzza x) (list (huzza x))))
      ([x & xs] (map huzza (conj xs x))))
    (time (def files (seq (.list (clojure.java.io/file "/usr/bin")))))
    (time (def sorted (huzzatok (sort files))))
    (time (run! println sorted))

The Perl program
================

    #!/usr/bin/perl -l
    use Time::HiRes qw(time);
    sub dt {
        my $t = time;
        my @r = shift()->(@_);
        print "\"Elapsed time: @{[ (time - $t) * 1000 ]} msecs\"";
        @r;
    }
    sub huzza { "Tiplizz inet $_[0]!" }
    sub huzzatok { map { huzza $_ } @_ }
    my @files = dt(sub { opendir DIR, "/usr/bin"; readdir DIR });
    my @sorted = dt(sub { huzzatok sort @files });
    dt(sub { print for @sorted });

The times [ms]
==============

|            | Clojure | Perl |
|------------|---------|------|
|read dir    |      329|     9|
|sort str    |       51|    15|
|print       |      751|    49|
|runtime real|     7004|   124|
|runtime user|    12976|    64|
|runtime sys |     1040|    36|

Observations
============

Perl IO reading is 30 times faster than the JVM.

Perl IO printing is 15 times faster.

Perl CPU intensive stuff is 3 times faster than Clojure.

Perl's compilation overhead is negligible, around 26ms.

Clojure's JVM startup and compilation overhead is a shocking 6s on 2 cores.

Notes
=====

The Clojure huzzatok function's complexity comes from imitating Perl sub's list-in list-out behaviour.

Clojurists, please correct my mistakes in the program, I'm very new to the language.

I used a fairly slow passively cooled AMD SoC-based Fitlet mini PC.
