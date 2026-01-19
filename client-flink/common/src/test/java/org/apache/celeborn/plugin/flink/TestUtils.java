package org.apache.celeborn.plugin.flink;

import java.util.concurrent.CompletableFuture;

import org.apache.flink.api.common.JobID;
import org.apache.flink.runtime.shuffle.JobShuffleContext;

public class TestUtils {

  public static JobShuffleContext createJobShuffleContext(JobID jobId) {
    return (JobShuffleContext)
        java.lang.reflect.Proxy.newProxyInstance(
            JobShuffleContext.class.getClassLoader(),
            new Class<?>[] {JobShuffleContext.class},
            (proxy, method, args) -> {
              if (method.getName().equals("getJobId")) {
                return jobId;
              } else if (method.getName().equals("stopTrackingAndReleasePartitions")) {
                return CompletableFuture.completedFuture(null);
              } else if (method.getName().equals("getPartitionWithMetrics")) {
                return CompletableFuture.completedFuture(null);
              } else if (method.getName().equals("notifyPartitionRecoveryStarted")) {
                return null;
              } else {
                throw new UnsupportedOperationException();
              }
            });
  }
}
