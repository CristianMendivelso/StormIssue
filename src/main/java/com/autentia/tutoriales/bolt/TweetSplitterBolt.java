package com.autentia.tutoriales.bolt;

import java.util.Map;

import twitter4j.Status;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class TweetSplitterBolt extends BaseRichBolt {

	private OutputCollector collector = null;

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {
                String id = tuple.getSourceComponent();
                if (id.equals("twitterSpout")){
                    final Status tweet = (Status) tuple.getValueByField("status");
                    final String[] words = tweet.getText().split(" ");

                    for (String word : words) {
                            collector.emit(new Values(word));
                    }
                }
                else{
                    String sentence = tuple.getString(0);
                    String[] words = sentence.split(" ");
                    for(String word: words){
                            word = word.trim();
                            if(!word.isEmpty()){
                                    word = word.toLowerCase();
                                    collector.emit(new Values(word));
                            }
                    }
                    collector.ack(tuple);
                    }
        }

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
}