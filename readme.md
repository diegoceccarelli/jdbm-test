# JDBM3 issue #

[I'm trying](https://github.com/diegoceccarelli/jdbm-test/blob/master/src/test/java/it/cnr/isti/hpc/jdbm/TestJDBM.java) to map a list of [wikipedia articles](http://www.di.unipi.it/~ceccarel/enwiki-pages-titles.tsv.gz)  (2,000,000) in a list
of integers. 
The issue is that while on my mac this code works, on 2 different 
linux machines the test fails:

		[...]
		16:21:40.612 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - adding id 1999996 	 title reverberation_(record_label)   
		16:21:40.612 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - adding id 1999997 	 title dixie_lullaby   
		16:21:40.612 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - adding id 1999998 	 title category:health_in_grenada   
		16:21:40.612 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - adding id 1999999 	 title ventura_rodr??guez   
		16:21:43.053 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - adding id 2000000 	 title revolt_(3_colours_red_album)   
		16:21:43.054 [main] INFO  it.cnr.isti.hpc.jdbm.TestJDBM - commit..
		16:21:44.082 [main] INFO  it.cnr.isti.hpc.jdbm.TestJDBM - done
		16:21:44.082 [main] INFO  it.cnr.isti.hpc.jdbm.TestJDBM - get the map map
		16:21:44.135 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - anarchism -> 1
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - autism -> 2
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - albedo -> 3
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - alabama -> 4
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - achilles -> 5
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - abraham_lincoln -> 6
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - aristotle -> 7
		16:21:44.137 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - an_american_in_paris -> 8
		16:21:44.138 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - academy_award_for_best_art_direction -> 9
		16:21:44.138 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - academy_award -> 10
		16:21:44.138 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - actrius -> 11
		16:21:44.138 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - animalia_(book) -> 12
		16:21:44.138 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - international_atomic_time -> 13
		16:21:44.138 [main] DEBUG it.cnr.isti.hpc.jdbm.TestJDBM - altruism -> null
		Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 90.559 sec <<< FAILURE!

where am I wrong? 
:( 

## Test ##
if you want to test download the [titles](http://www.di.unipi.it/~ceccarel/enwiki-pages-titles.tsv.gz) in 
''src/test/resources''. 

## JDBM 2.4 ##
I also tried with JDBM 2.4, doing like in this [example](http://code.google.com/p/jdbm2/source/browse/trunk/src/examples/HugeData.java), but no way :(
 

[Diego Ceccarelli](http://www.di.unipi.it/~ceccarel) 2012


	
