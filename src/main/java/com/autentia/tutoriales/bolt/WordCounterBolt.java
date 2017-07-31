package com.autentia.tutoriales.bolt;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.mutable.MutableInt;


import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class WordCounterBolt extends BaseRichBolt {

	private OutputCollector collector = null;
	private Map<String, MutableInt> words = null;

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.words = new HashMap<String, MutableInt>();
	}

	@Override
	public void execute(Tuple input) {
		final String word = input.getStringByField("word");
		MutableInt count = words.get(word);
		if (count == null) {
			count = new MutableInt();
		}
		count.increment();

		words.put(word, count);
		collector.emit(new Values(word, count));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "count"));
	}
}