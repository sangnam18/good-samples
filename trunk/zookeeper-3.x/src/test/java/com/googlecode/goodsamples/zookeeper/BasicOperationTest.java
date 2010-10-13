package com.googlecode.goodsamples.zookeeper;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Zookeeper must be running to execute this test. 
 *  </p>
 */

public class BasicOperationTest {
	private static final String HOST = "127.0.0.1:2181";
	private static ZooKeeper zooKeeper;

	final static byte[] NO_DATA = new byte[0];
	final static String ROOT = "/test";
	final static Integer INITIAL_VERSION = 0;
	final static ArrayList<ACL> NO_ACL = Ids.OPEN_ACL_UNSAFE;
	final static Object mutex = new Object();

	private boolean notified = false;

	@Test
	public void createNode() throws KeeperException, InterruptedException {
		String actualName = createSequencialNode(ROOT + "/" + "z_");
		Stat result = zooKeeper.exists(actualName, false);

		assertThat(result, is(notNullValue()));
		assertThat(result.getVersion(), is(INITIAL_VERSION));
	}

	@Test
	public void removeNode() throws KeeperException, InterruptedException {
		String actualName = createSequencialNode(ROOT + "/" + "z_");
		zooKeeper.delete(actualName, 0);

		assertThat(zooKeeper.exists(actualName, false), is(nullValue()));
	}

	@Test
	public void watchNode() throws KeeperException, InterruptedException {
		String actualName = createSequencialNode(ROOT + "/" + "w_");
		zooKeeper.exists(actualName, new WatchTestWatcher());

		synchronized (mutex) {
			zooKeeper.delete(actualName, 0);
			mutex.wait();
		}

		assertThat(notified, is(true));
	}

	private class WatchTestWatcher implements Watcher {
		@Override
		public void process(WatchedEvent event) {
			notified = true;
			synchronized (mutex) {
				mutex.notify();
			}			
		}
	}
	
	private String createSequencialNode(String prefix) throws KeeperException,
			InterruptedException {
		String actualName = zooKeeper.create(prefix, NO_DATA, NO_ACL,
				CreateMode.EPHEMERAL_SEQUENTIAL);
		return actualName;
	}
	
	private class DefaultWatcher implements Watcher {
		@Override
		public void process(WatchedEvent event) {
		}
	}
	
	@Before
	public void connectToZooKeeper() throws IOException, KeeperException,
			InterruptedException {
		zooKeeper = new ZooKeeper(HOST, 3000, new DefaultWatcher());

		if (zooKeeper.exists(ROOT, false) == null) {
			zooKeeper.create(ROOT, NO_DATA, Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
		}
	}

	@After
	public void disconnectFromZooKeeper() throws InterruptedException {
		zooKeeper.close();
	}
}
