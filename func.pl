#!/usr/bin/perl -l
use List::Util qw(shuffle);
use Time::HiRes qw(time);
sub timed {
    my $t = time;
    my @r = shift()->(@_);
    print "\"Elapsed time: ", (time - $t) * 1000, " msecs\"";
    @r;
}

sub huzza { "Tiplizz inet $_[0]!" }
sub huzzatok { map { huzza $_ } @_ }
sub ls { opendir DIR, shift; readdir DIR }
sub shufsort { my ($n, @l) = @_; @l = sort(shuffle(@l)) for 1 .. $n; @l; }

my @files = timed(sub { ls "/usr/bin" });
my @sorted = timed(sub { huzzatok sort @files });
my @sortedk = timed(sub { huzzatok shufsort 1000, @files });
timed(sub { print for @sortedk });
