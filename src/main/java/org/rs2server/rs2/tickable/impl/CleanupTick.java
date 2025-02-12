package org.rs2server.rs2.tickable.impl;

import org.rs2server.Server;
import org.rs2server.rs2.domain.service.api.EngineService;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.task.impl.CleanupTask;
import org.rs2server.rs2.tickable.Tickable;

/**
 * A tickable which runs periodically and performs tasks such as garbage
 * collection.
 * @author Graham Edgecombe
 *
 */
public class CleanupTick extends Tickable {

	/**
	 * The delay in ticks between consecutive cleanups.
	 */
	public static final int CLEANUP_CYCLE_TIME = 500;

	/**
	 * Creates the cleanup event to run every 5 minutes.
	 */
	public CleanupTick() {
		super(CLEANUP_CYCLE_TIME);
	}

	@Override
	public void execute() {
		engineService.offerTask(new CleanupTask());
	}

}
