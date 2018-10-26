package tpstorm.exclamation;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class App 
{
	public static void main(String[] args) throws AlreadyAliveException,
	
		InvalidTopologyException {
			
			TopologyBuilder builder = exclamationTopology();
			Config conf = new Config();
			conf.setNumWorkers(2);
			
			if (args != null && args.length > 0) {
			conf.setDebug(false);
			StormSubmitter.submitTopology(args[0], conf,
			builder.createTopology());
			
		} else {
			conf.setDebug(true);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("exclamation", conf,
			builder.createTopology());
			Utils.sleep(10000);
			cluster.killTopology("exclamation");
			cluster.shutdown();
		}
	}
    
    // defining the exclamation topology
    private static TopologyBuilder exclamationTopology() {
    	TopologyBuilder builder = new TopologyBuilder();
    	final String spoutName = "exclamationSpout";
    	final String boltName = "exclamationBolt";
    	
    	//builder.setSpout(spoutName, new ExclamationSpout(), 1);
    	//builder.setBolt(boltName, new ExclamationBolt(), 1).shuffleGrouping(spoutName); //shuffleGrouping-- connecting the sputs to the bolts
    	//HINT: You have to kill the topology, change the source code, rebuild the jar, upload the
    	//new jar and launch the topology again.
    	
    	builder.setSpout("blue-spout", new ExclamationSpout(), 2); // parallelism hint
    	
    	builder.setBolt("green-bolt", new ExclamationBolt(), 2)
    	.setNumTasks(4)
    	.shuffleGrouping("blue-spout");
    	
    	builder.setBolt("yellow-bolt", new ExclamationBolt(), 2)
    	.shuffleGrouping("green-bolt");
    	
    	return builder;
    }
}
