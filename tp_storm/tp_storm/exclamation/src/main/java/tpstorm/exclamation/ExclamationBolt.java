package tpstorm.exclamation;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class ExclamationBolt extends BaseRichBolt{
    
	private OutputCollector _collector; 
	//Storm needs to know something on the tuple the bolt will emit
	public static final String BOLT_FIELD = "EXCLAMATION_FIELD"; 
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this._collector = collector;  // means through which we'll emit new tuples 
		
		}
    /**embeds the actual logic of the Bolt. 
     *  For each tuple sent to this bolt, Storm will call 
     *  this method and pass the tuple as the argument.
     * ***/
	@Override
	public void execute(Tuple input) {
		String word = input.getStringByField(ExclamationSpout.SPOUT_FIELD);
		String newWord = word + "!";
		_collector.emit(new Values(newWord)); //emitting new word
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(BOLT_FIELD)); // info about tuple the sput will emit
		
	}

	
}
