package com.dalong;

import com.hazelcast.jet.Traversers;
import com.hazelcast.jet.aggregate.AggregateOperations;
import com.hazelcast.jet.pipeline.Pipeline;
import com.hazelcast.jet.pipeline.Sinks;
import com.hazelcast.jet.pipeline.Sources;
import com.hazelcast.jet.pipeline.WindowDefinition;

import java.io.Serializable;

/**
 @author dalong
*/
public class MyPipe2 implements Serializable {

    Pipeline buildPipeline(String path) {
        Pipeline p = Pipeline.create();
        p.readFrom(Sources.files(path))
                .flatMap(line -> Traversers.traverseArray(line.toLowerCase().split("\\W+")))
                .filter(word -> !word.isEmpty())
                .groupingKey(word -> word)
                .aggregate(AggregateOperations.counting())
                .writeTo(Sinks.logger());
        return p;
    }
}
