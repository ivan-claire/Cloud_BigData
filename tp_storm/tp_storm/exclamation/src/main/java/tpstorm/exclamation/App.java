package tpstorm.exclamation;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class App 
{
    public static void main( String[] args )
    {
    	/** 1st exercise
    	TopologyBuilder builder = exclamationTopology();
    	Config conf = new Config();
    	conf.setDebug(true);
    	conf.setNumWorkers(1);
    	
    	LocalCluster cluster = new LocalCluster();
    	cluster.submitTopology("exclamation", conf, builder.createTopology());
    	Utils.sleep(20000);
    	cluster.killTopology("exclamation");
    	cluster.shutdown();
    	 */
    	TopologyBuilder topologyBuilder = exclamationTopology();
    	Config conf = new Config();
    	conf.setDebug(true);
    	conf.setNumWorkers(2); // use two worker processes    	
    	
    	LocalCluster cluster = new LocalCluster();
    	cluster.submitTopology("exclamation", conf, topologyBuilder.createTopology());
    	Utils.sleep(20000);
    	cluster.killTopology("exclamation");
    	cluster.shutdown();
    }
    
    // defining the exclamation topology
    private static TopologyBuilder exclamationTopology() {
    	TopologyBuilder builder = new TopologyBuilder();
    	final String spoutName = "exclamationSpout";
    	final String boltName = "exclamationBolt";
    	
    	//builder.setSpout(spoutName, new ExclamationSpout(), 1);
    	//builder.setBolt(boltName, new ExclamationBolt(), 1).shuffleGrouping(spoutName); //shuffleGrouping-- connecting the sputs to the bolts
    	
    	builder.setSpout("blue-spout", new ExclamationSpout(), 2); // parallelism hint
    	
    	builder.setBolt("green-bolt", new ExclamationBolt(), 2)
    	.setNumTasks(4)
    	.shuffleGrouping("blue-spout");
    	
    	builder.setBolt("yellow-bolt", new ExclamationBolt(), 6)
    	.shuffleGrouping("green-bolt");
    	
    	return builder;
    }
}
