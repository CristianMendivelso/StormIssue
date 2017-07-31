/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yahoostorm;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.StormSubmitter;

public class HelloStorm {

	public static void main(String[] args) throws Exception{
		Config config = new Config();
		config.put("inputFile","/home/ubuntu/proyecto/storm/words.txt");
		config.setDebug(true);
		config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("line-reader-spout", new LineReaderSpout());
		builder.setBolt("word-spitter", new WordSpitterBolt()).shuffleGrouping("line-reader-spout");
		builder.setBolt("word-counter", new WordCounterBolt()).shuffleGrouping("word-spitter");
		
		config.setNumWorkers(3);
                StormSubmitter.submitTopology("word-counter", config, builder.createTopology());
                
	}

}
