package com.dalong;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import com.hazelcast.jet.Traversers;
import com.hazelcast.jet.aggregate.AggregateOperations;
import com.hazelcast.jet.config.JetClientConfig;
import com.hazelcast.jet.config.JetConfig;
import com.hazelcast.jet.config.JobConfig;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.jet.pipeline.WindowDefinition;

public class Application {
    public static void main(String[] args) {
        String path = "/var/opt/mybooks";

        // batch mode

        BatchMode(path);

        // stream mode

        // StreamMode(path);
    }
    //
    private static void StreamMode(String path) {
        JetClientConfig config = new JetClientConfig();
        config.getNetworkConfig().addAddress("127.0.0.1:5701");
        JetInstance jet = Jet.newJetClient(config);
//        JetInstance jet = Jet.bootstrappedInstance();
        MyPipe mypipe = new MyPipe();
        JobConfig jobConfig =new JobConfig();
        jobConfig.addClass(MyPipe.class);
        jet.newJob(mypipe.buildPipeline(path),jobConfig).join();
    }
    private static void BatchMode(String path) {
        JetClientConfig config = new JetClientConfig();
        config.getNetworkConfig().addAddress("jet:5701");
        JetInstance jet = Jet.newJetClient(config);
//        JetInstance jet = Jet.bootstrappedInstance();
        MyPipe2 mypipe2 = new MyPipe2();
        JobConfig jobConfig =new JobConfig();
        jobConfig.addClass(MyPipe2.class);
        jet.newJob(mypipe2.buildPipeline(path),jobConfig).join();
    }
}
