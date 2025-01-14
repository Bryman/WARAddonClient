package de.idrinth.waraddonclient.service;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Shedule {
  private final List<ScheduledExecutorService> services = new ArrayList<>();

  public void register(int wait, Runnable run) {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    scheduler.scheduleAtFixedRate(run, 0, wait, SECONDS);
    services.add(scheduler);
  }
}
