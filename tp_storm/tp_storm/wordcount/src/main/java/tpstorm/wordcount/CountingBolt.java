package tpstorm.wordcount;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class CountingBolt  extends BaseRichBolt{

	private OutputCollector _collector;
	private int word_counter = 0;
	//Storm needs to know something on the tuple the bolt will emit
	public static final String BOLT_FIELD = "WORD"; 
	public static final String BOLT_FIELD2 = "COUNT"; 
	public static final String BOLT_FIELD3 = "MAXIMUM"; 
	
	 Map <String, String> map = new HashMap <String, String> ();
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this._collector = collector;  // means through which we'll emit new tuples 
				
	}

	@Override
	public void execute(Tuple input) {
        String word = null;
        Map.Entry<String,String> maxEntry = null;
        
		//TODO retrieve the string from the received tuple.
		word = input.getStringByField(SplittingBolt.BOLT_FIELD);
		
		 if (map.containsKey(word)) {
	            int count = Integer.parseInt(map.get(word));
	            map.put(word, String.valueOf(count + 1));
	            
	        } else {
	            map.put(word, "1");
	        }
       
		for (Map.Entry<String, String> entry : map.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		    
		    if (maxEntry == null || Integer.parseInt(entry.getValue()) > Integer.parseInt(maxEntry.getValue()))
		    {
		        maxEntry = entry;
		    }
		    _collector.emit(new Values(entry.getKey(),entry.getValue(), "Trending Word: "+maxEntry.getKey())); //emitting new word
		}
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(BOLT_FIELD,BOLT_FIELD2)); // info about tuple the sput will emit

	}


}
