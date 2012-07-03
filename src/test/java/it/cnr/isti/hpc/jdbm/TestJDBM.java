/**
 *  Copyright 2012 Diego Ceccarelli
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package it.cnr.isti.hpc.jdbm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import junit.framework.Assert;

import org.apache.jdbm.DB;
import org.apache.jdbm.DBMaker;
import org.junit.Test;

/**
 * TestJDBM.java
 *
 * @author Diego Ceccarelli, diego.ceccarelli@isti.cnr.it
 * created on 03/lug/2012
 */
public class TestJDBM {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(TestJDBM.class);
	
	private final static String TEST_FILE = "enwiki-pages-titles.tsv.gz";
	private final static String DB_FILE = "/tmp/test.db";
	private final static String TEST_COLLECTION = "test";
	
	private void loadMap(){
		InputStream is = this.getClass().getResourceAsStream("/"+TEST_FILE);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader( new GZIPInputStream(is)));
		} catch (IOException e1) {
			fail();
		}
		String line = "";
		Integer id = 1;
		DB db = null;
		logger.info("creating map");
		db = DBMaker.openFile(DB_FILE)
				.make();
		
		Map<String, Integer> article2id = db.createTreeMap(TEST_COLLECTION);
		try {
			while ( (line = br.readLine())!= null){
				logger.debug("adding id {} \t title {}   ",id,line);
				article2id.put(line,id++);
				if (id % 100000 == 0) db.commit();
				
				if (id > 2000000) break;
			}
		} catch (IOException e) {
			logger.error("reading the title file ({})",e.toString());
			logger.error("commit..");
			db.commit();
			System.exit(1);
		}
		logger.info("commit..");
		db.commit();
		db.close();
		logger.info("done");
	}

	@Test
	public void test() {
		loadMap();
		InputStream is = this.getClass().getResourceAsStream("/"+TEST_FILE);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is)));
		} catch (IOException e1) {
			fail();
		}
		String line = "";
		Integer id = 1;
		DB db = null;
		logger.info("get the map map");
		db = DBMaker.openFile(DB_FILE).make();
		
		Map<String, Integer> article2id = db.getTreeMap(TEST_COLLECTION);
		//System.out.println(article2id.get("list_of_atlas_shrugged_characters"));
		try {
			while ((line = br.readLine()) != null) {
				logger.debug("{} -> {}", line, article2id.get(line));
				Assert.assertTrue(article2id.get(line) != null);
				id ++;
				if (id > 2000000) break;
			}
		} catch (IOException e) {
			logger.error("reading the title file ({})", e.toString());
			fail();
		}
		
	}

}
