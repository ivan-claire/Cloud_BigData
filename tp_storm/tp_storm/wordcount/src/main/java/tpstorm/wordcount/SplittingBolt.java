package tpstorm.wordcount;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 *In other words it has to received tuples from the Read Sentences Spout,  extract the strings
containing the sentence, split this strings to obtain an array of strings, one for each words.  Then it has
to emit a tuple for each of these strings.
 * */
public class SplittingBolt extends BaseRichBolt{
    
	private OutputCollector _collector; 
	//Storm needs to know something on the tuple the bolt will emit
	public static final String BOLT_FIELD = "WORD"; 
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this._collector = collector;  // means through which we'll emit new tuples 
				
	}

	@Override
	public void execute(Tuple input) {
		String line = null;
		
		//TODO retrieve the string from the received tuple.
		line = input.getStringByField(ReadSentencesSpout.SPOUT_FIELD);
		
		//TODO Split the sentence in words
		String[] words = line.split("\\s+");   //\\s+ is splitting one or multiple spaces 
		
		for (int i = 0; i < words.length; i++) {
		     words[i] = words[i].replaceAll("[^a-zA-Z ]", ""); //^a-zA-Z ]is replacing all values that are not a_zA_Z with space
		     words[i].toLowerCase();
		     
		   //TODO emit a tuple for each word in the String array words
		     _collector.emit(new Values(words[i])); //emitting new word
			
	    }        				
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
	
		declarer.declare(new Fields(BOLT_FIELD)); // info about tuple the sput will emit

	}

}
