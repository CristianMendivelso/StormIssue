package com.autentia.tutoriales.bolt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.mutable.MutableInt;


import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class CountPrinterBolt extends BaseRichBolt {

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
	}

	@Override
	public void execute(Tuple tuple) {
		final String word = tuple.getStringByField("word");
		final MutableInt count = (MutableInt) tuple.getValueByField("count");
		
                System.out.println(String.format("%s:%s", word, count.toString()));
                
            
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	}
}