/*
 * Copyright 2001-2013 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalatest

import org.scalactic.Requirements._
import org.scalatest.time.{Seconds, Span}

/**
 * Arguments bundle passed to four of ScalaTest's lifecycle methods: <code>run</code>, <code>runNestedSuites</code>,
 * <code>runTests</code>, and <code>runTest</code>.
 *
 * <p>
 * The signatures of these methods, defined in trait <a href="Suite.html"><code>Suite</code></a>, are:
 * </p>
 *
 * <pre>
 * def run(testName: Option[String], args: Args)
 * def runNestedSuites(args: Args)
 * def runTests(testName: Option[String], args: Args)
 * def runTest(testName: String, args: Args)
 * </pre>
 *
 * <p>
 * The purpose of bundling these arguments into an <code>Args</code> object instead of passing them in individually is to make the signature
 * of these four lifecycle methods easier to read, write, and remember, as well as to make the methods more pleasant to override in user code.
 * </p>
 * 
 * @param reporter the <code>Reporter</code> to which results will be reported
 * @param stopper the <code>Stopper</code> that will be consulted to determine whether to stop execution early.
 * @param filter a <code>Filter</code> with which to filter tests based on their tags
 * @param configMap a <code>ConfigMap</code> of key-value pairs that can be used by the executing <code>Suite</code> of tests.
 * @param distributor an optional <code>Distributor</code>, into which to put nested <code>Suite</code>s to be executed
 *              by another entity, such as concurrently by a pool of threads. If <code>None</code>, nested <code>Suite</code>s will be executed sequentially.
 * @param tracker a <code>Tracker</code> tracking <code>Ordinal</code>s being fired by the current thread.
 * @param runTestInNewInstance a flag used to pass information between run methods
 *           in <a href="OneInstancePerTest.html"><code>OneInstancePerTest</code></a> and <a href="ParallelTestExecution.html"><code>ParallelTestExecution</code></a>.
 * @param distributedTestSorter an optional <a href="DistributedTestSorter.html"><code>DistributedTestSorter</code></a> used by <code>ParallelTestExecution</code> to sort the events
 *                              for the parallel-executed tests of one suite back into sequential order on the fly, with a timeout in case a test takes too long to complete
 * @param distributedSuiteSorter an optional <a href="DistributedSuiteSorter.html"><code>DistributedSuiteSorter</code></a> used by <code>ParallelTestExecution</code> to ensure the events
 *                              for the parallel-executed suites are sorted back into sequential order, with a timeout in case a suite takes to long to complete, even when tests are executed in parallel
 * @param runningSuites information about all the suites running in the current test run. The <code>List</code> may be empty if the current suite is the only suite in this run.
 * @throws NullArgumentException if any passed parameter is <code>null</code>.
 *
 */
case class Args(
  reporter: Reporter,
  stopper: Stopper = Stopper.default,
  filter: Filter = Filter.default,
  configMap: ConfigMap = ConfigMap.empty,
  distributor: Option[Distributor] = None,
  tracker: Tracker = Tracker.default,
  runningSuites: List[RunningSuite] = List.empty,
  runTestInNewInstance: Boolean = false,
  distributedTestSorter: Option[DistributedTestSorter] = None,
  distributedSuiteSorter: Option[DistributedSuiteSorter] = None
) {
    requireNonNull(reporter, stopper, filter, configMap, distributor, tracker,
                   distributedTestSorter, distributedSuiteSorter)
}
