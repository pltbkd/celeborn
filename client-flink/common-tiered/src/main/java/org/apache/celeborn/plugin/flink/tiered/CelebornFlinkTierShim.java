package org.apache.celeborn.plugin.flink.tiered;

import org.apache.flink.api.common.JobID;
import org.apache.flink.runtime.io.network.partition.hybrid.tiered.tier.TierShuffleHandler;
import org.apache.flink.runtime.shuffle.JobShuffleContext;

import org.apache.celeborn.plugin.flink.CelebornFlinkShim;

/** Shim for Celeborn and Flink with tiered shuffle. */
public abstract class CelebornFlinkTierShim extends CelebornFlinkShim {

  public static JobShuffleContext getJobShuffleContext(
      JobID jobID, TierShuffleHandler tierShuffleHandler) {
    return ((CelebornFlinkTierShim) getInstance())
        .getJobShuffleContextImpl(jobID, tierShuffleHandler);
  }

  protected abstract JobShuffleContext getJobShuffleContextImpl(
      JobID jobID, TierShuffleHandler tierShuffleHandler);
}
