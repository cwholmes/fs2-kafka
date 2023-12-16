package fs2.kafka.consumer

import scala.concurrent.duration.FiniteDuration

import org.apache.kafka.clients.consumer.OffsetAndTimestamp
import org.apache.kafka.common.{PartitionInfo, TopicPartition}

trait KafkaTopicsV2[F[_]] extends KafkaTopics[F] {

  /**
    * List topics.<br><br>
    *
    * Timeout is determined by `default.api.timeout.ms`, which is set using
    * [[ConsumerSettings#withDefaultApiTimeout]].
    */
  def listTopics: F[Map[String, List[PartitionInfo]]]

  /**
    * List topics.
    */
  def listTopics(timeout: FiniteDuration): F[Map[String, List[PartitionInfo]]]

  /**
    * Look up the offsets for the given partitions by timestamp. The returned offset for each
    * partition is the earliest offset whose timestamp is greater than or equal to the given
    * timestamp in the corresponding partition.<br><br>
    *
    * The consumer does not have to be assigned the partitions. If no messages exist yet for a
    * partition, it will not exist in the returned map.<br><br>
    *
    * Timeout is determined by `default.api.timeout.ms`, which is set using
    * [[ConsumerSettings#withDefaultApiTimeout]].
    */
  def offsetsForTimes(
    timestampsToSearch: Map[TopicPartition, Long]
  ): F[Map[TopicPartition, OffsetAndTimestamp]]

  /**
    * Look up the offsets for the given partitions by timestamp. The returned offset for each
    * partition is the earliest offset whose timestamp is greater than or equal to the given
    * timestamp in the corresponding partition.
    *
    * The consumer does not have to be assigned the partitions. If no messages exist yet for a
    * partition, it will not exist in the returned map.
    */
  def offsetsForTimes(
    timestampsToSearch: Map[TopicPartition, Long],
    timeout: FiniteDuration
  ): F[Map[TopicPartition, OffsetAndTimestamp]]

}
