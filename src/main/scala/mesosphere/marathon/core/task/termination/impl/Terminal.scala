package mesosphere.marathon.core.task.termination.impl

import mesosphere.marathon.core.event.InstanceChanged
import mesosphere.marathon.core.instance.Condition

// TODO(PODS): There are various similar Terminal extractors, Sets and functions – the NEED to be aligned
private[impl] object Terminal {

  private[this] val terminalStatus = Set(
    Condition.Error,
    Condition.Failed,
    Condition.Killed,
    Condition.Finished,
    Condition.Unreachable,
    Condition.Unknown,
    Condition.Gone,
    Condition.Dropped
  )

  def unapply(event: InstanceChanged): Option[InstanceChanged] = {
    if (terminalStatus(event.status)) Some(event) else None
  }
}
