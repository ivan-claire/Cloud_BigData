package tpstorm.exclamation;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class ExclamationSpout extends BaseRichSpout {
    
	private SpoutOutputCollector _collector; 
	public static final String SPOUT_FIELD = "EXCLAMATION_FIELD";
	
	/**spout will generate a strem of tuples that will contain
	only a filed, sput_filed **/
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this._collector = collector;     // means through which we'll emit new tuples 
		
	}
	
    /**embeds actual logic of the spout;method called 
	continously to retrieve new tuples randomly to be pushed in the topology **/
	@Override
	public void nextTuple() {
		Utils.sleep(100);
		final String[] words = new String[] {"nathan", "mike", "jackson",
		"golda", "bertels"};
		final Random rand = new Random();
		final String word = words[rand.nextInt(words.length)];
		_collector.emit(new Values(word));
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields(SPOUT_FIELD)); // info about tuple the sput will emit
	}

}
