package tpstorm.wordcount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class ReadSentencesSpout extends BaseRichSpout {
    
	private SpoutOutputCollector _collector; 
	public static final String SPOUT_FIELD = "SENTENCE";
	private Scanner scanner;
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		
		this._collector = collector;     // means through which we'll emit new tuples 
		
		ClassLoader classLoader = getClass().getClassLoader();
		
		File file = new File(classLoader.getResource("sentences.txt").getFile());
		System.out.println(file.getAbsolutePath());
		
		try {
			
			this.scanner = new Scanner(file);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}	
	}

	public void nextTuple() {
		
		Utils.sleep(100);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			_collector.emit(new Values(line));
			
			}
		
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields(SPOUT_FIELD)); // info about tuple the sput will emit		
	}
	
	public void close() {
		
		scanner.close();
	}

}
