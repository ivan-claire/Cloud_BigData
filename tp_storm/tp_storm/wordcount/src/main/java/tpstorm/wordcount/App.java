package tpstorm.wordcount;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	TopologyBuilder topologyBuilder = wordCountTopology();
    	Config conf = new Config();
    	conf.setDebug(true);
    	conf.setNumWorkers(2); // use two worker processes    	
    	
    	LocalCluster cluster = new LocalCluster();
    	cluster.submitTopology("counting words", conf, topologyBuilder.createTopology());
    	Utils.sleep(20000);
    	cluster.killTopology("counting words");
    	cluster.shutdown();
    }
    
 // defining the exclamation topology
    private static TopologyBuilder wordCountTopology() {
    	
    	TopologyBuilder builder = new TopologyBuilder();
    	
    	builder.setSpout("readSentence-spout", new ReadSentencesSpout(), 1); // parallelism hint
    	
    	builder.setBolt("splitting-bolt", new SplittingBolt(), 1)
    	.setNumTasks(4)
    	.shuffleGrouping("readSentence-spout");
    	
    	//All grouping and Global grouping
    	builder.setBolt("counting-bolt", new CountingBolt(), 2)
    	.fieldsGrouping("splitting-bolt", new Fields("WORD"));
    	
    	/*builder.setBolt("counting-bolt", new CountingBolt(), 1)
    	.shuffleGrouping("splitting-bolt");*/
    	
    	
    	return builder;
    }
}
